package com.xu.blog.entity.dto;

import java.util.List;

/**
 * @author 11582
 */
public class BlogDto {

    private String title;
    private String summary;
    private String author;
    private Integer viewCount;
    private Integer digCount;
    private String content;
    private String createTime;
    private String updateTime;
    private List<CommentDto> comments;
    private List<String> relation;

    public List<String> getRelation() {
        return relation;
    }

    public void setRelation(List<String> relation) {
        this.relation = relation;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getDigCount() {
        return digCount;
    }

    public void setDigCount(Integer digCount) {
        this.digCount = digCount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public List<CommentDto> getComments() {
        return comments;
    }

    public void setComments(List<CommentDto> comments) {
        this.comments = comments;
    }
}
