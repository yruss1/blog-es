package com.xu.blog.entity.dto;

import com.xu.blog.entity.mysql.Comment;
import com.xu.blog.entity.mysql.MysqlBlog;
import com.xu.blog.entity.mysql.Quest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author 11582
 */
@ApiModel("用户信息实体类")
public class UserDto {
    @ApiModelProperty("用户id")
    private Long id;
    @ApiModelProperty("用户名")
    private String userName;
    @ApiModelProperty("用户组织")
    private String organization;
    @ApiModelProperty("用户提问历史")
    private List<Quest> questList;
    @ApiModelProperty("用户回复提问历史")
    private List<Quest> replyList;
    @ApiModelProperty("用户博客历史")
    private List<MysqlBlog> blogList;
    @ApiModelProperty("用户评论历史")
    private List<Comment> commentList;
    @ApiModelProperty("用户收到的提问历史")
    private List<Quest> receiveQuestList;

    public List<Quest> getReceiveQuestList() {
        return receiveQuestList;
    }

    public void setReceiveQuestList(List<Quest> receiveQuestList) {
        this.receiveQuestList = receiveQuestList;
    }

    public List<Quest> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<Quest> replyList) {
        this.replyList = replyList;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public UserDto() {
    }
}
