package com.xu.blog.repository;

import com.xu.blog.entity.mysql.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 11582
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    /**
     * 根据用户id查找他的评论
     * @param userId 用户id
     * @return 评论列表
     */
    List<Comment> findCommentByUserId(Long userId);

    /**
     * 根据博客id查找所有评论
     * @param blogId 博客id
     * @return 所有评论
     */
    List<Comment> findCommentByBlogId(Integer blogId);

}
