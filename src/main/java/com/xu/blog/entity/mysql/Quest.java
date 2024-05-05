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
    @Column(name = "sender_id")
    private String senderId;
    @Column(name = "receiver_id")
    private String receiverId;
    @Column(name = "send_message")
    private String sendMessage;
    @Column(name = "send_time")
    private Long sendTime;
    @Column(name = "reply_message")
    private String replyMessage;
    @Column(name = "reply_time")
    private Long replyTime;

}
