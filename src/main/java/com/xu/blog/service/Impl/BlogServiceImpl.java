package com.xu.blog.service.Impl;

import com.xu.blog.entity.BlogVo;
import com.xu.blog.entity.mysql.MysqlBlog;
import com.xu.blog.repository.MysqlBlogRepository;
import com.xu.blog.service.BlogService;
import org.springframework.stereotype.Service;

/**
 * @author 11582
 */
@Service
public class BlogServiceImpl implements BlogService {

    private final MysqlBlogRepository mysqlBlogRepository;

    public BlogServiceImpl(MysqlBlogRepository mysqlBlogRepository) {
        this.mysqlBlogRepository = mysqlBlogRepository;
    }

    @Override
    public void addBlog(BlogVo vo) {
        mysqlBlogRepository.save(new MysqlBlog(vo));
    }
}
