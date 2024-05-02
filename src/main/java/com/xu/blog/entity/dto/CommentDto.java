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
    private String userId;

    @ApiModelProperty("评论内容")
    private String message;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @ApiModelProperty("评论时间")
    private String time;

    @ApiModelProperty("子回复")
    private List<CommentDto> commentChildList;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
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
