package com.xu.blog.controller;

import com.xu.blog.entity.mysql.MysqlBlog;
import com.xu.blog.repository.MysqlBlogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author 11582
 */
@Controller
@Slf4j
public class IndexController {

    private final MysqlBlogRepository mysqlBlogRepository;

    public IndexController(MysqlBlogRepository mysqlBlogRepository) {
        this.mysqlBlogRepository = mysqlBlogRepository;
    }

    @GetMapping("/")
    public String index() {
        List<MysqlBlog> all = mysqlBlogRepository.findAll();
        log.info("【查询所有的博客数据】all={}", all.size());
        return "index.html";

    }
}
