package com.xu.blog.controller;

import com.xu.blog.common.Result;
import com.xu.blog.entity.es.EsBlog;
import com.xu.blog.entity.mysql.MysqlBlog;
import com.xu.blog.repository.EsBlogRepository;
import com.xu.blog.repository.MysqlBlogRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api("检索模块")
public class DataController {
    private final MysqlBlogRepository mysqlBlogRepository;
    private final EsBlogRepository esBlogRepository;

    public DataController(MysqlBlogRepository mysqlBlogRepository, EsBlogRepository esBlogRepository) {
        this.mysqlBlogRepository = mysqlBlogRepository;
        this.esBlogRepository = esBlogRepository;
    }


    @GetMapping("/blogs")
    @ApiOperation("通过mysql获取全部博客")
    public Result<List<MysqlBlog>> blogList() {
        return Result.ok(mysqlBlogRepository.queryAll());
    }

    @PostMapping("/search")
    @ApiOperation("检索，目前支持es")
    public Result<Map<String, Object>> search(@RequestParam String keyword) {
        Map<String, Object> map = new HashMap<>();
        // 统计耗时
        StopWatch watch = new StopWatch();
        watch.start();

        BoolQueryBuilder builder = QueryBuilders.boolQuery();
        builder.should(QueryBuilders.matchPhraseQuery("title", keyword));
        builder.should(QueryBuilders.matchPhraseQuery("content", keyword));
        Page<EsBlog> search = (Page<EsBlog>) esBlogRepository.search(builder);
        List<EsBlog> content = search.getContent();
        map.put("list", content);

        watch.stop();
        // 计算耗时
        long millis = watch.getTotalTimeMillis();
        map.put("duration", millis);
        return Result.ok(map);
    }

    @GetMapping("/blog/{id}")
    @ApiOperation("查看具体博文")
    public Result<Object> blog(@PathVariable String id) {
        Optional<MysqlBlog> byId = mysqlBlogRepository.findById(id);
        return byId.<Result<Object>>map(Result::ok).orElseGet(() -> Result.error("博客不存在！"));
    }

}
