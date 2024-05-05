package com.xu.blog.service;

import com.xu.blog.entity.dto.UserDto;
import com.xu.blog.entity.mysql.User;
import com.xu.blog.entity.vo.RegisterVo;

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

    /**
     * 返回字符串
     * @param registerVo 注册信息
     * @return 字符串
     */
    String doRegister(RegisterVo registerVo);

    /**
     * 查看用户名是否合法
     * @param username 用户名
     * @return 字符串
     */
    String checkUsername(String username);

}
