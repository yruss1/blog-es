package com.xu.blog.entity.mysql;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "simple-uuid")
    @GenericGenerator(name = "simple-uuid", strategy = "com.xu.blog.common.config.SimpleUuidGenerator")
    @Column(name = "id")
    private Integer id;
    @Column(name = "quest_id")
    private String questId;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "message")
    private String message;
    @Column(name = "time")
    private Long time;
    @Column(name = "blog_id")
    private String blogId;
    @Column(name = "parent_id")
    private Integer parentId;

}
