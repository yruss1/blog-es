package com.xu.blog.controller;


import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.xu.blog.common.Result;
import com.xu.blog.entity.BlogVo;
import com.xu.blog.entity.QuestVo;
import com.xu.blog.entity.UserDto;
import com.xu.blog.entity.mysql.Comment;
import com.xu.blog.entity.mysql.User;
import com.xu.blog.service.BlogService;
import com.xu.blog.service.QuestService;
import com.xu.blog.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author 11582
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private static final String USER_NAME = "userName";
    private static final String PASSWORD = "password";

    private final UserService userService;
    private final BlogService blogService;
    private final QuestService questService;

    public UserController(UserService userService, BlogService blogService, QuestService questService) {
        this.userService = userService;
        this.blogService = blogService;
        this.questService = questService;
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

    @SaCheckLogin
    @GetMapping("/info")
    public Result<UserDto> userInfo(@RequestParam String id){
        return Result.ok(userService.userInfo(id));
    }

    @SaCheckLogin
    @PostMapping("/blog/add")
    public Result<String> add(@RequestBody BlogVo vo){
        blogService.addBlog(vo);
        return Result.ok();
    }

    @SaCheckLogin
    @PostMapping("/quest/new")
    public Result<String> questNew(@RequestBody QuestVo questVo){
        questService.questNew(questVo);
        return Result.ok();
    }

    @SaCheckLogin
    @PostMapping("/quest/comment")
    public Result<String> comment(@RequestBody Comment comment){
        questService.comment(comment);
        return Result.ok();
    }

}
