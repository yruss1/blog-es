package com.xu.blog.controller;

import com.xu.blog.common.Result;
import com.xu.blog.common.exception.BusinessException;
import com.xu.blog.entity.es.EsBlog;
import com.xu.blog.entity.mysql.MysqlBlog;
import com.xu.blog.repository.EsBlogRepository;
import com.xu.blog.repository.MysqlBlogRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.Page;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author 11582
 */
@RestController
@Slf4j
public class DataController {
    private static final String MYSQL = "mysql";
    private static final String ES = "es";
    private final MysqlBlogRepository mysqlBlogRepository;
    private final EsBlogRepository esBlogRepository;

    public DataController(MysqlBlogRepository mysqlBlogRepository, EsBlogRepository esBlogRepository) {
        this.mysqlBlogRepository = mysqlBlogRepository;
        this.esBlogRepository = esBlogRepository;
    }


    @GetMapping("/blogs")
    public Object blogList() {
        return mysqlBlogRepository.queryAll();
    }

    @PostMapping("/search")
    public Object search(@RequestBody Param param) {
        Map<String, Object> map = new HashMap<>();
        // 统计耗时
        StopWatch watch = new StopWatch();
        watch.start();
        String type = param.getType();
        // mysql 的搜索
        if (MYSQL.equals(type)) {
            List<MysqlBlog> mysqlBlogs = mysqlBlogRepository.queryBlog(param.getKeyword());
            map.put("list", mysqlBlogs);
            // es 的搜索
        } else if (ES.equals(type)) {
            BoolQueryBuilder builder = QueryBuilders.boolQuery();
            builder.should(QueryBuilders.matchPhraseQuery("title", param.getKeyword()));
            builder.should(QueryBuilders.matchPhraseQuery("content", param.getKeyword()));
            String s = builder.toString();
            log.info("s={}", s);
            Page<EsBlog> search = (Page<EsBlog>) esBlogRepository.search(builder);
            List<EsBlog> content = search.getContent();
            map.put("list", content);

        } else {
            throw new BusinessException("错误的搜索类型！请检查");
        }
        watch.stop();
        // 计算耗时
        long millis = watch.getTotalTimeMillis();
        map.put("duration", millis);
        return map;
    }

    @GetMapping("/blog/{id}")
    public Object blog(@PathVariable String id) {
        Optional<MysqlBlog> byId = mysqlBlogRepository.findById(id);
        if (byId.isPresent()){
            return byId.get();
        }else {
            return Result.error("博客不存在！");
        }


    }


    @Data
    private static class Param {
        private String type;
        private String keyword;
    }
}
