package com.xu.blog.controller;

import com.xu.blog.common.Result;
import com.xu.blog.entity.dto.BlogDto;
import com.xu.blog.entity.dto.QuestDto;
import com.xu.blog.entity.mysql.MysqlBlog;
import com.xu.blog.entity.mysql.Quest;
import com.xu.blog.service.BlogService;
import com.xu.blog.service.QuestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 11582
 */
@RestController
@Api("检索模块")
public class DataController {

    private final BlogService blogService;
    private final QuestService questService;

    public DataController(BlogService blogService, QuestService questService) {
        this.blogService = blogService;
        this.questService = questService;
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

    @GetMapping("/searchQuest")
    @ApiOperation("检索，目前支持es")
    public Result<Map<String, Object>> searchQuest(@RequestParam("keyword") String keyword,
                                              @RequestParam("pageNum") int pageNum,
                                              @RequestParam("pageSize") int pageSize) {
        return Result.ok(questService.search(keyword, pageNum, pageSize));
    }

    @GetMapping("/searchAll")
    @ApiOperation("检索，目前支持es")
    public Result<Map<String, Object>> searchAll(@RequestParam("keyword") String keyword,
                                                   @RequestParam("pageNum") int pageNum,
                                                   @RequestParam("pageSize") int pageSize) {
        Map<String, Object> questMap = questService.search(keyword, pageNum, pageSize);
        Map<String, Object> blogMap = blogService.search(keyword, pageNum, pageSize);
        Map<String, Object> res = new HashMap<>(16);
        res.put("total", (long)questMap.get("total") + (long)blogMap.get("total"));
        res.put("totalPages", (int)questMap.get("totalPages") + (int)blogMap.get("totalPages"));
        res.put("questList", questMap.get("list"));
        res.put("blogList", blogMap.get("list"));
        return Result.ok(res);
    }

    @GetMapping("/blog/{id}")
    @ApiOperation("查看具体博文")
    public Result<BlogDto> blog(@PathVariable("id") Integer id) {
        BlogDto blogDto = blogService.selectById(id);
        if (blogDto != null){
            return Result.ok(blogDto);
        }
        return Result.error("博客不存在！");
    }

    @GetMapping("/quests")
    @ApiOperation("通过mysql获取全部问答")
    public Result<List<Quest>> questList() {
        return Result.ok(questService.selectAll());
    }


    @GetMapping("/quest/{id}")
    @ApiOperation("查看具体问答")
    public Result<QuestDto> quest(@PathVariable("id") Integer id) {
        QuestDto quest = questService.selectById(id);
        if (quest != null){
            return Result.ok(quest);
        }
        return Result.error("问答不存在！");
    }

}
