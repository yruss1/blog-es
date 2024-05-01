package com.xu.blog.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author 11582
 */
@ApiModel("评论实体")
public class CommentVo {

    @ApiModelProperty("问题id")
    private String questId;
    @ApiModelProperty("用户id")
    private String userId;
    @ApiModelProperty("评论内容")
    private String message;

    public String getQuestId() {
        return questId;
    }

    public CommentVo() {
    }

    public CommentVo(String questId, String userId, String message) {
        this.questId = questId;
        this.userId = userId;
        this.message = message;
    }

    public void setQuestId(String questId) {
        this.questId = questId;
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
}
