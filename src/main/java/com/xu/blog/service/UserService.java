package com.xu.blog.service;

import com.xu.blog.entity.mysql.User;

/**
 * @author 11582
 */
public interface UserService {

    /**
     * 用户登录
     * @param userName 用户名
     * @return 用户实体
     */
    User doLogin(String userName);

}
