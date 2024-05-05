package com.xu.blog.task;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.xu.blog.common.util.GsonUtil;
import com.xu.blog.common.util.HtmlUtils;
import com.xu.blog.entity.vo.RawBlogVo;
import com.xu.blog.entity.mysql.MysqlBlog;
import com.xu.blog.repository.MysqlBlogRepository;
import com.xu.blog.task.restApi.BlogRestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static com.xu.blog.common.config.ApiConfig.*;


/**
 * @author 11582
 */
@EnableScheduling
@EnableAsync
@Service
public class BlogApiTask {

    @Value("${blog.clientId}")
    public String clientId;
    @Value("${blog.clientSecret}")
    public String clientSecret;
    private static final Logger logger = LoggerFactory.getLogger(BlogApiTask.class);
    private final ConcurrentHashMap<Integer, MysqlBlog> map = new ConcurrentHashMap<>();
    private final MysqlBlogRepository mysqlBlogRepository;

    public BlogApiTask(MysqlBlogRepository mysqlBlogRepository) {
        this.mysqlBlogRepository = mysqlBlogRepository;
    }

//    @PostConstruct
    public void getArticlesTask(){
        BlogRestClient client = RestApiClientFactory.newInstance(
                clientId,
                clientSecret)
                .newBlogRestClient();
        client.getAccessToken(response ->  {
            JsonObject jsonObject = GsonUtil.fromJson(GsonUtil.toJsonString(response), JsonObject.class);
            StringBuilder stringBuilder = new StringBuilder(jsonObject.get(ACCESS_TOKEN).toString());
            String token = AUTH_PREFIX + stringBuilder.substring(1, stringBuilder.length() - 1);
            for (int i = 1; i <= 50; i++) {
                client.getArticles(i + "",
                        DEFAULT_PAGE_SIZE,
                        token,
                        response1 -> {
                            JsonArray jsonArray = GsonUtil.fromJson(
                                    GsonUtil.toJsonString(response1),
                                    JsonArray.class
                            );
                            for (JsonElement jsonElement : jsonArray){
                                RawBlogVo rawBlogVo = GsonUtil.fromJson(
                                        GsonUtil.toJsonString(jsonElement),
                                        RawBlogVo.class
                                );
                                client.getArticleById(
                                        String.valueOf(rawBlogVo.getId()),
                                        token,
                                        response2 -> {
                                            MysqlBlog blog = new MysqlBlog();
                                            blog.setContent(GsonUtil.toJsonString(response2));
                                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                                            SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                            Date d = null;
                                            try {
                                                d = sdf.parse(rawBlogVo.getCreateTime());
                                            } catch (ParseException e) {
                                                e.printStackTrace();
                                            }
                                            String formattedTime = output.format(d);
                                            blog.setCreateTime(formattedTime);
                                            blog.setUpdateTime(formattedTime);
                                            blog.setId(rawBlogVo.getId());
                                            blog.setTitle(rawBlogVo.getTitle());
                                            blog.setSummary(rawBlogVo.getSummary());
                                            blog.setAuthor(rawBlogVo.getAuthor());
                                            blog.setViewCount(rawBlogVo.getViewCount());
                                            blog.setDigCount(rawBlogVo.getDiggCount());
                                            map.put(blog.getId(), blog);
                                            logger.info("{}",blog);
                                        });
                            }
                        });
            }

        });
        saveAll();
    }
    private void saveAll(){
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
        Runnable task = () -> {
            if (!map.isEmpty()){
                logger.info("总共获取文章数量为：{}",map.size());
                for (MysqlBlog blog : map.values()){
                    logger.info("id：{}的文章点赞数量为{}", blog.getId(), blog.getDigCount());
                    blog.setContent(HtmlUtils.Html2Text(blog.getContent()));
                    mysqlBlogRepository.save(blog);
                }
            }
        };
        // 延迟1秒后执行任务
        executor.schedule(task, 10, TimeUnit.SECONDS);

        // 关闭执行器
        executor.shutdown();

    }

}
