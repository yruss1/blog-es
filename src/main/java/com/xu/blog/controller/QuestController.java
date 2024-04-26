package com.xu.blog.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.xu.blog.common.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author 11582
 */
@RestController
@RequestMapping("/quest")
public class QuestController {

    @SaCheckLogin
    @PostMapping("/new")
    public Result<String> questNew(@RequestBody Map<String, String> map){

        return Result.ok();
    }

    @SaCheckLogin
    @PostMapping("/comment")
    public Result<String> comment(@RequestBody Map<String, String> map){

        return Result.ok();
    }

}
