package com.xu.blog.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author 11582
 */
@ApiModel("问题实体")
public class QuestVo {
    @ApiModelProperty("问题id")
    private Integer id;
    @ApiModelProperty("用户id")
    private String userId;
    @ApiModelProperty("专家id")
    private String expertId;
    @ApiModelProperty("问题信息")
    private String message;
    @ApiModelProperty("发布时间")
    private Long time;

    public QuestVo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getExpertId() {
        return expertId;
    }

    public void setExpertId(String expertId) {
        this.expertId = expertId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public QuestVo(Integer id, String userId, String expertId, String message, Long time) {
        this.id = id;
        this.userId = userId;
        this.expertId = expertId;
        this.message = message;
        this.time = time;
    }
}
