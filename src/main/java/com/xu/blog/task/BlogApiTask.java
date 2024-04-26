package com.xu.blog.task;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.xu.blog.entity.RawBlog;
import com.xu.blog.entity.mysql.MysqlBlog;
import com.xu.blog.repository.MysqlBlogRepository;
import com.xu.blog.task.restApi.BlogRestClient;
import com.xu.blog.common.util.GsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static com.xu.blog.common.config.ApiConfig.*;


@EnableScheduling
@EnableAsync
@Service
public class BlogApiTask {

    @Value("${blog.clientId}")
    public String clientId;
    @Value("${blog.clientSecret}")
    public String ClientSecret;
    private static final Logger logger = LoggerFactory.getLogger(BlogApiTask.class);
    private final ConcurrentHashMap<String, MysqlBlog> map = new ConcurrentHashMap<>();
    private final MysqlBlogRepository mysqlBlogRepository;

    public BlogApiTask(MysqlBlogRepository mysqlBlogRepository) {
        this.mysqlBlogRepository = mysqlBlogRepository;
    }

//    @PostConstruct
    public void getArticlesTask(){
        BlogRestClient client = RestApiClientFactory.newInstance(
                clientId,
                ClientSecret)
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
                                RawBlog rawBlog = GsonUtil.fromJson(
                                        GsonUtil.toJsonString(jsonElement),
                                        RawBlog.class
                                );
                                client.getArticleById(
                                        rawBlog.getId(),
                                        token,
                                        response2 -> {
                                            MysqlBlog blog = new MysqlBlog();
                                            blog.setContent(GsonUtil.toJsonString(response2));
                                            long currentTimeMillis = System.currentTimeMillis();
                                            blog.setCreateTime(GsonUtil.toJsonString(currentTimeMillis));
                                            blog.setUpdateTime(GsonUtil.toJsonString(currentTimeMillis));
                                            blog.setId(rawBlog.getId());
                                            blog.setTitle(rawBlog.getTitle());
                                            blog.setSummary(rawBlog.getSummary());
                                            blog.setAuthor(rawBlog.getAuthor());
                                            blog.setViewCount(rawBlog.getViewCount());
                                            blog.setDigCount(rawBlog.getDiggCount());
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
