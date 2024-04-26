package com.xu.blog.repository;

import com.xu.blog.entity.mysql.MysqlBlog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 11582
 */
@Repository
public interface MysqlBlogRepository extends JpaRepository<MysqlBlog, String> {

    /**
     * 创建时间倒序查询博客
     *
     * @return 博客列表
     */
    @Query("select e from MysqlBlog e order by e.createTime desc ")
    List<MysqlBlog> queryAll();

    /**
     * 模糊查询
     *
     * @param keyword 关键字
     * @return 博客列表
     */
    @Query("select e from MysqlBlog e where e.title like concat('%',:keyword,'%') or e.content like concat('%',:keyword,'%')")
    List<MysqlBlog> queryBlog(@Param("keyword") String keyword);
}
