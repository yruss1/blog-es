package com.xu.blog.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author 11582
 */
@ApiModel("问题实体")
public class QuestVo {

    @ApiModelProperty("用户id")
    private String userId;
    @ApiModelProperty("专家id")
    private String expertId;
    @ApiModelProperty("问题信息")
    private String message;

    public QuestVo() {
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

    public QuestVo(String userId, String expertId, String message) {
        this.userId = userId;
        this.expertId = expertId;
        this.message = message;
    }
}
