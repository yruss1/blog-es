package com.xu.blog.entity;

/**
 * @author 11582
 */
public class BlogVo {

    private String title;
    private String summary;
    private String author;
    private String content;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public BlogVo(String title) {
        this.title = title;
    }

    public BlogVo(String title, String summary, String author, String content) {
        this.title = title;
        this.summary = summary;
        this.author = author;
        this.content = content;
    }
}
