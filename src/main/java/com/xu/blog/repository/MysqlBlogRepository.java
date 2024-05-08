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
public interface MysqlBlogRepository extends JpaRepository<MysqlBlog, Integer> {

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

    /**
     * 根据作者查询博客
     * @param author 作者
     * @return 博客
     */
    List<MysqlBlog> findByAuthor(String author);

    /**
     * 根据博客id查找博客标题
     * @param id 博客id
     * @return 博客标题
     */
    @Query("select e.title from MysqlBlog e where e.id = :id ")
    String findTitleById(Integer id);

}
