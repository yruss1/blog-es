package com.xu.blog.service;

import com.xu.blog.entity.mysql.User;

public interface UserService {

    User doLogin(String userName);

}
