package com.xu.blog.entity.mysql;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author 11582
 */
@Data
@Table(name = "t_quest")
@Entity
public class Quest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "simple-uuid")
    @GenericGenerator(name = "simple-uuid", strategy = "com.xu.blog.common.config.SimpleUuidGenerator")
    @Column(name = "id")
    private Integer id;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "expert_id")
    private String expertId;
    @Column(name = "message")
    private String message;
    @Column(name = "time")
    private Long time;

}
