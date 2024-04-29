package com.xu.blog.entity;

import com.xu.blog.entity.mysql.Comment;
import com.xu.blog.entity.mysql.MysqlBlog;
import com.xu.blog.entity.mysql.Quest;

import java.util.List;

/**
 * @author 11582
 */
public class UserDto {

    private String id;
    private String userName;
    private String identityType;
    private List<Quest> questList;
    private List<MysqlBlog> blogList;
    private List<Comment> commentList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIdentityType() {
        return identityType;
    }

    public void setIdentityType(String identityType) {
        this.identityType = identityType;
    }

    public List<Quest> getQuestList() {
        return questList;
    }

    public void setQuestList(List<Quest> questList) {
        this.questList = questList;
    }

    public List<MysqlBlog> getBlogList() {
        return blogList;
    }

    public void setBlogList(List<MysqlBlog> blogList) {
        this.blogList = blogList;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public UserDto(String id, String userName, String identityType, List<Quest> questList, List<MysqlBlog> blogList, List<Comment> commentList) {
        this.id = id;
        this.userName = userName;
        this.identityType = identityType;
        this.questList = questList;
        this.blogList = blogList;
        this.commentList = commentList;
    }

    public UserDto() {
    }
}
