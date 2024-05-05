package com.xu.blog.entity.mysql;

import lombok.Data;

import javax.persistence.*;

/**
 * @author 11582
 */
@Data
@Table(name = "t_user")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "password")
    private String password;
    @Column(name = "organization")
    private String organization;

}
