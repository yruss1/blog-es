package com.xu.blog.entity.mysql;

import com.xu.blog.entity.vo.BlogVo;
import lombok.Data;

import javax.persistence.*;
import java.text.SimpleDateFormat;


/**
 * @author 11582
 */
@Data
@Table(name = "t_blog")
@Entity
public class MysqlBlog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;
    @Column(name = "Title")
    private String title;
    @Column(name = "Summary")
    private String summary;
    @Column(name = "Author")
    private String author;
    @Column(name = "View_count")
    private Integer viewCount;
    @Column(name = "Dig_count")
    private Integer digCount;
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
        this.digCount = 0;
        this.viewCount = 0;
        this.createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
    }

    public MysqlBlog(Integer id, String title, String summary, String author, Integer viewCount, Integer digCount, String content, String createTime, String updateTime) {
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
