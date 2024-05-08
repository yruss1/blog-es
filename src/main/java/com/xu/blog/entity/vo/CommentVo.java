package com.xu.blog.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author 11582
 */
@ApiModel("评论实体")
public class CommentVo {

    @ApiModelProperty("博客id")
    private Integer blogId;
    @ApiModelProperty("用户id")
    private Long userId;
    @ApiModelProperty("评论内容")
    private String message;
    @ApiModelProperty("父评论id")
    private Integer parentId;
    @ApiModelProperty("回复id")
    private Integer replyId;

    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }



    public Integer getBlogId() {
        return blogId;
    }

    public CommentVo() {
    }

    public CommentVo(Integer blogId, Long userId, String message) {
        this.blogId = blogId;
        this.userId = userId;
        this.message = message;
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
}
