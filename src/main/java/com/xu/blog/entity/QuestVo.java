package com.xu.blog.entity;

/**
 * @author 11582
 */
public class QuestVo {

    private Integer id;
    private String userId;
    private String expertId;
    private String message;
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
