package com.xu.blog.service;

import com.xu.blog.entity.dto.BlogDto;
import com.xu.blog.entity.mysql.MysqlBlog;
import com.xu.blog.entity.vo.BlogVo;

import java.util.List;
import java.util.Map;

/**
 * @author 11582
 */
public interface BlogService {

    /**
     *  发布博客
     * @param vo 博文
     */
    void addBlog(BlogVo vo);

    /**
     *  查看所有博客
     * @return 博客集合
     */
    List<MysqlBlog> selectAll();

    /**
     * 根据关键词查询博客
     * @param keyword 关键词
     * @param pageNum 页码
     * @param pageSize 分页大小
     * @return map集合
     */
    Map<String, Object> search(String keyword,
                               int pageNum,
                               int pageSize);

    /**
     * 根据博客id查找博客
     * @param id 博客id
     * @return 博客信息
     */
    BlogDto selectById(Integer id);

}
