package com.xu.blog.service;

import com.xu.blog.entity.dto.UserInfoDto;
import com.xu.blog.entity.dto.UserOrgDto;
import com.xu.blog.entity.mysql.User;
import com.xu.blog.entity.vo.RegisterVo;

import java.util.List;

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
    UserInfoDto userInfo(Long id);

    /**
     *  返回用户信息
     * @param userName 用户userName
     * @return 用户信息实体
     */
    UserInfoDto userInfo(String userName);

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

    /**
     * 根据组织查找用户
     * @param org 组织
     * @param userId 用户id
     * @return 用户组织的其他用户
     */
    List<UserOrgDto> selectByOrg(String org, Long userId);

}
