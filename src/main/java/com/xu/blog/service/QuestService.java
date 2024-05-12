package com.xu.blog.service;

import com.xu.blog.entity.dto.QuestDto;
import com.xu.blog.entity.mysql.Quest;
import com.xu.blog.entity.vo.CommentVo;
import com.xu.blog.entity.vo.QuestVo;
import com.xu.blog.entity.vo.ReplyVo;

import java.util.List;
import java.util.Map;

/**
 * @author 11582
 */
public interface QuestService {

    /**
     * 新提问
     * @param vo 提问对象
     * @return 是否成功
     */
    int questNew(QuestVo vo);

    /**
     * 追加提问
     * @param comment 评论对象
     * @return 是否成功
     */
    String comment(CommentVo comment);

    /**
     * 回复
     * @param replyVo 回复实体
     * @return 字符串
     */
    String reply(ReplyVo replyVo);

    /**
     * 查询所有问答
     * @return 问答list
     */
    List<Quest> selectAll();

    /**
     * 根据id查询问答
     * @param id 问答id
     * @return 问答
     */
    QuestDto selectById(Integer id);

    /**
     * 根据关键词查询博客
     * @param keyword 关键词
     * @param pageNum 页码
     * @param pageSize 分页大小
     * @return map集合
     */
    Map<String, Object> search(String keyword,
                               int pageNum,
                               int pageSize);

}
