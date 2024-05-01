package com.xu.blog.controller;


import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.xu.blog.common.Result;
import com.xu.blog.entity.BlogVo;
import com.xu.blog.entity.CommentVo;
import com.xu.blog.entity.QuestVo;
import com.xu.blog.entity.UserDto;
import com.xu.blog.entity.mysql.User;
import com.xu.blog.service.BlogService;
import com.xu.blog.service.QuestService;
import com.xu.blog.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author 11582
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户模块")
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
    @ApiOperation("用户登录")
    public SaResult doLogin(@RequestBody Map<String, String> map){
        User user = userService.doLogin(map.get(USER_NAME));
        if (map.get(PASSWORD).equals(user.getPassword())){
            StpUtil.login(user.getId());
            return SaResult.data(StpUtil.getTokenInfo());
        }
        return SaResult.error("登录失败,用户名或密码错误");
    }
    @SaCheckLogin
    @GetMapping("/logout/{id}")
    @ApiOperation("用户登出")
    public SaResult logout(@PathVariable String id){
        StpUtil.logout(id);
        return SaResult.ok();
    }

    @SaCheckLogin
    @GetMapping("/checkLogin")
    @ApiOperation("检查用户登录状态")
    public SaResult checkLogin(){
        return SaResult.data(StpUtil.isLogin());
    }

    @SaCheckLogin
    @GetMapping("/info")
    @ApiOperation("获取用户信息")
    public Result<UserDto> userInfo(@RequestParam String id){
        return Result.ok(userService.userInfo(id));
    }

    @SaCheckLogin
    @PostMapping("/blog/add")
    @ApiOperation("发布博客")
    public Result<String> add(@RequestBody BlogVo vo){
        blogService.addBlog(vo);
        return Result.ok();
    }

    @SaCheckLogin
    @PostMapping("/quest/new")
    @ApiOperation("发布提问")
    public Result<String> questNew(@RequestBody QuestVo questVo){
        questService.questNew(questVo);
        return Result.ok();
    }

    @SaCheckLogin
    @PostMapping("/quest/comment")
    @ApiOperation("发布评论")
    public Result<String> comment(@RequestBody CommentVo commentVo){
        questService.comment(commentVo);
        return Result.ok();
    }

}
