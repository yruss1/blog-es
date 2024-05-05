package com.xu.blog.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author 11582
 */
@ApiModel("回复实体")
public class ReplyVo {
    @ApiModelProperty("问题id")
    private Integer id;
    @ApiModelProperty("专家名")
    private String receiverName;
    @ApiModelProperty("回复信息")
    private String replyMessage;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReplyMessage() {
        return replyMessage;
    }

    public void setReplyMessage(String replyMessage) {
        this.replyMessage = replyMessage;
    }
}
