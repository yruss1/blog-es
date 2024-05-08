package com.xu.blog.controller;


import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.xu.blog.common.Result;
import com.xu.blog.entity.dto.UserInfoDto;
import com.xu.blog.entity.dto.UserOrgDto;
import com.xu.blog.entity.mysql.User;
import com.xu.blog.entity.vo.*;
import com.xu.blog.service.BlogService;
import com.xu.blog.service.QuestService;
import com.xu.blog.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    public UserController(UserService userService,
                          BlogService blogService,
                          QuestService questService) {
        this.userService = userService;
        this.blogService = blogService;
        this.questService = questService;
    }

    @PostMapping("/doLogin")
    @ApiOperation("用户登录")
    public SaResult doLogin(@RequestBody Map<String, String> map){
        User user = userService.doLogin(map.get(USER_NAME));
        if (user != null
                && map.get(PASSWORD).equals(user.getPassword())){
            StpUtil.login(user.getId());
            return SaResult.data(StpUtil.getTokenInfo());
        }
        return SaResult.error("登录失败,用户名或密码错误");
    }

    @PostMapping("/register")
    @ApiOperation("用户注册")
    public Result<String> register(@RequestBody RegisterVo registerVo){
        if (!ObjectUtils.isNotEmpty(registerVo)){
            return Result.error("非法的参数，请检查");
        }else {
            return Result.info(userService.doRegister(registerVo));
        }
    }

    @GetMapping("/checkUsername/{username}")
    @ApiOperation("检查用户名是否合法")
    public Result<String> checkUsername(@PathVariable("username") String username){
        return Result.info(userService.checkUsername(username));
    }

    @SaCheckLogin
    @GetMapping("/logout")
    @ApiOperation("用户登出")
    public SaResult logout(){
        StpUtil.logout(StpUtil.getLoginIdAsString());
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
    public Result<UserInfoDto> userInfo(){
        UserInfoDto userInfoDto = userService.userInfo(StpUtil.getLoginIdAsLong());
        if (ObjectUtils.isNotEmpty(userInfoDto)){
            return Result.ok(userInfoDto);
        }else {
            return Result.ok(null,"查询用户信息失败！");
        }
    }

    @GetMapping("/infoByUsername/{userName}")
    @ApiOperation("获取用户信息")
    public Result<UserInfoDto> infoByUsername(@PathVariable("userName") String userName){
        UserInfoDto userInfoDto = userService.userInfo(userName);
        if (ObjectUtils.isNotEmpty(userInfoDto)){
            return Result.ok(userInfoDto);
        }else {
            return Result.ok(null,"查询用户信息失败！");
        }

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
        int res = questService.questNew(questVo);
        if (res == 0){
            return Result.error("发布失败,请检查参数！");
        }
        return Result.ok(String.valueOf(res));
    }

    @SaCheckLogin
    @PostMapping("/quest/reply")
    @ApiOperation("回复提问")
    public Result<String> reply(@RequestBody ReplyVo replyVo){
        return Result.info(questService.reply(replyVo));
    }

    @SaCheckLogin
    @PostMapping("/quest/comment")
    @ApiOperation("发布评论")
    public Result<String> comment(@RequestBody CommentVo commentVo){
        return Result.ok(questService.comment(commentVo));
    }

    @SaCheckLogin
    @GetMapping("/organization/{org}")
    @ApiOperation("查看该用户关系")
    public Result<List<UserOrgDto>> selectByOrg(@PathVariable("org") String org){
        return Result.ok(userService.selectByOrg(org, StpUtil.getLoginIdAsLong()));
    }

}
