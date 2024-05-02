package com.xu.blog.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author 11582
 */
@ApiModel("博客实体")
public class BlogVo {
    @ApiModelProperty("博客标题")
    private String title;
    @ApiModelProperty("概括")
    private String summary;
    @ApiModelProperty("作者")
    private String author;
    @ApiModelProperty("内容")
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
