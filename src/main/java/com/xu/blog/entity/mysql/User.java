package com.xu.blog.entity.mysql;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author 11582
 */
@Data
@Table(name = "t_user")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "simple-uuid")
    @GenericGenerator(name = "simple-uuid", strategy = "com.xu.blog.common.config.SimpleUuidGenerator")
    @Column(name = "id")
    private String id;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "password")
    private String password;
    @Column(name = "organization")
    private String organization;

}
