package com.xu.blog.entity.mysql;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author 11582
 */
@Data
@Table(name = "t_comment")
@Entity
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "message")
    private String message;
    @Column(name = "time")
    private String time;
    @Column(name = "blog_id")
    private Integer blogId;
    @Column(name = "parent_id")
    private Integer parentId;
    @Column(name = "reply_id")
    private Integer replyId;

}
