package com.xu.blog.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author 11582
 */
@ApiModel("问题实体")
public class QuestVo {


    @ApiModelProperty("用户名")
    private String senderName;
    @ApiModelProperty("专家名")
    private String receiverName;
    @ApiModelProperty("问题信息")
    private String sendMessage;

    public QuestVo() {
    }
    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }
    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getSendMessage() {
        return sendMessage;
    }

    public void setSendMessage(String sendMessage) {
        this.sendMessage = sendMessage;
    }

}
