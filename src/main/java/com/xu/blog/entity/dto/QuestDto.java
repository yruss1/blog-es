package com.xu.blog.entity.dto;

import java.util.List;

/**
 * @author 11582
 */
public class QuestDto {

    private Integer id;
    private String senderName;
    private String receiverName;
    private String sendMessage;
    private String sendTime;
    private String replyMessage;
    private String replyTime;
    private List<String> relation;

    public QuestDto(Integer id, String senderName, String receiverName, String sendMessage, String sendTime, String replyMessage, String replyTime, List<String> relation) {
        this.id = id;
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.sendMessage = sendMessage;
        this.sendTime = sendTime;
        this.replyMessage = replyMessage;
        this.replyTime = replyTime;
        this.relation = relation;
    }

    public QuestDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getReplyMessage() {
        return replyMessage;
    }

    public void setReplyMessage(String replyMessage) {
        this.replyMessage = replyMessage;
    }

    public String getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(String replyTime) {
        this.replyTime = replyTime;
    }

    public List<String> getRelation() {
        return relation;
    }

    public void setRelation(List<String> relation) {
        this.relation = relation;
    }
}
