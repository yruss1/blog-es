package com.xu.blog.controller;


import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.xu.blog.entity.mysql.User;
import com.xu.blog.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author 11582
 */
@RestController
@RequestMapping("/user")
public class UserLoginController {

    private static final String USER_NAME = "userName";
    private static final String PASSWORD = "password";

    private final UserService userService;

    public UserLoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/doLogin")
    public SaResult doLogin(@RequestBody Map<String, String> map){
        User user = userService.doLogin(map.get(USER_NAME));
        if (map.get(PASSWORD).equals(user.getPassword())){
            StpUtil.login(user.getId());
            return SaResult.ok();
        }
        return SaResult.error("登录失败,用户名或密码错误");
    }
    @SaCheckLogin
    @GetMapping("/logout/{id}")
    public SaResult logout(@PathVariable String id){
        StpUtil.logout(id);
        return SaResult.ok();
    }

    @SaCheckLogin
    @GetMapping("/checkLogin")
    public SaResult checkLogin(){
        return SaResult.ok();
    }

}
