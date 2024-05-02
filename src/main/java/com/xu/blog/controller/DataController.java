package com.xu.blog.controller;

import com.xu.blog.common.Result;
import com.xu.blog.entity.dto.BlogDto;
import com.xu.blog.entity.mysql.MysqlBlog;
import com.xu.blog.service.BlogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author 11582
 */
@RestController
@Api("检索模块")
public class DataController {

    private final BlogService blogService;

    public DataController(BlogService blogService) {
        this.blogService = blogService;
    }


    @GetMapping("/blogs")
    @ApiOperation("通过mysql获取全部博客")
    public Result<List<MysqlBlog>> blogList() {
        return Result.ok(blogService.selectAll());
    }

    @GetMapping("/search")
    @ApiOperation("检索，目前支持es")
    public Result<Map<String, Object>> search(@RequestParam("keyword") String keyword,
                                              @RequestParam("pageNum") int pageNum,
                                              @RequestParam("pageSize") int pageSize) {

        return Result.ok(blogService.search(keyword, pageNum, pageSize));
    }

    @GetMapping("/blog")
    @ApiOperation("查看具体博文")
    public Result<BlogDto> blog(@RequestParam("id") String id) {
        BlogDto blogDto = blogService.selectById(id);
        if (blogDto != null){
            return Result.ok(blogDto);
        }
        return Result.error("博客不存在！");
    }

}
