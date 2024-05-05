package com.xu.blog.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author 11582
 */
@ApiModel("问题实体")
public class QuestVo {

    @ApiModelProperty("专家id")
    private String receiverId;
    @ApiModelProperty("问题信息")
    private String sendMessage;

    public QuestVo() {
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getSendMessage() {
        return sendMessage;
    }

    public void setSendMessage(String sendMessage) {
        this.sendMessage = sendMessage;
    }

    public QuestVo(String receiverId, String sendMessage) {
        this.receiverId = receiverId;
        this.sendMessage = sendMessage;
    }
}
