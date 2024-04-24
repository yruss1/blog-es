package com.xu.blog.controller;


import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.xu.blog.entity.mysql.User;
import com.xu.blog.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserLoginController {

    private final UserService userService;

    public UserLoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/doLogin/{userName}/{password}")
    public SaResult doLogin(@PathVariable String userName,@PathVariable String password){
        User user = userService.doLogin(userName);
        if (password.equals(user.getPassword())){
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
