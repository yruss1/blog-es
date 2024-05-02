package com.xu.blog.service;

import com.xu.blog.entity.dto.UserDto;
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

    /**
     *  返回用户信息
     * @param id 用户id
     * @return 用户信息实体
     */
    UserDto userInfo(String id);

}
