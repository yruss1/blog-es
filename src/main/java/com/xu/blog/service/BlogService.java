package com.xu.blog.service;

import com.xu.blog.entity.BlogVo;

/**
 * @author 11582
 */
public interface BlogService {

    /**
     *  发布博客
     * @param vo 博文
     */
    void addBlog(BlogVo vo);

}
