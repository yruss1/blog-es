package com.xu.blog.entity.mysql;

import lombok.Data;

import javax.persistence.*;

/**
 * @author 11582
 */
@Data
@Table(name = "t_quest")
@Entity
public class Quest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "sender_name")
    private String senderName;
    @Column(name = "receiver_name")
    private String receiverName;
    @Column(name = "send_message")
    private String sendMessage;
    @Column(name = "send_time")
    private String sendTime;
    @Column(name = "reply_message")
    private String replyMessage;
    @Column(name = "reply_time")
    private String replyTime;

}
