package com.xu.blog.entity.mysql;

import com.xu.blog.entity.BlogVo;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


/**
 * @author 11582
 */
@Data
@Table(name = "t_blog")
@Entity
public class MysqlBlog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "simple-uuid")
    @GenericGenerator(name = "simple-uuid", strategy = "com.xu.blog.common.config.SimpleUuidGenerator")
    @Column(name = "Id")
    private String id;
    @Column(name = "Title")
    private String title;
    @Column(name = "Summary")
    private String summary;
    @Column(name = "Author")
    private String author;
    @Column(name = "View_count")
    private String viewCount;
    @Column(name = "Dig_count")
    private String digCount;
    @Column(columnDefinition = "mediumtext", name = "content")
    private String content;
    @Column(name = "create_time")
    private String createTime;
    @Column(name = "update_time")
    private String updateTime;

    public MysqlBlog(BlogVo vo){
        this.author = vo.getAuthor();
        this.title = vo.getTitle();
        this.summary = vo.getSummary();
        this.content = vo.getContent();
    }

    public MysqlBlog(String id, String title, String summary, String author, String viewCount, String digCount, String content, String createTime, String updateTime) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.author = author;
        this.viewCount = viewCount;
        this.digCount = digCount;
        this.content = content;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public MysqlBlog() {

    }
}
