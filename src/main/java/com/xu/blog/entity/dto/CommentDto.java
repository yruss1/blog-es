package com.xu.blog.entity.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @author 11582
 */
public class CommentDto implements Serializable {

    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("上级评论id")
    private Integer pid;

    @ApiModelProperty("评论用户id")
    private Long userId;

    @ApiModelProperty("评论用户名")
    private String username;

    @ApiModelProperty("评论内容")
    private String message;

    @ApiModelProperty("评论时间")
    private String time;

    @ApiModelProperty("子回复")
    private List<CommentDto> commentChildList;

    @ApiModelProperty("回复的id")
    private Integer replyId;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<CommentDto> getCommentChildList() {
        return commentChildList;
    }

    public void setCommentChildList(List<CommentDto> commentChildList) {
        this.commentChildList = commentChildList;
    }
}
