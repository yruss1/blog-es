package com.xu.blog.controller;

import com.xu.blog.entity.mysql.MysqlBlog;
import com.xu.blog.repository.MysqlBlogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@Slf4j
public class IndexController {
    @Autowired
    MysqlBlogRepository mysqlBlogRepository;

    @GetMapping("/")
    public String index() {
        List<MysqlBlog> all = mysqlBlogRepository.findAll();
        log.info("【查询所有的博客数据】all={}", all.size());
        return "index.html";

    }
}
