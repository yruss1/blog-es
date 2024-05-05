package com.xu.blog.service;

import com.xu.blog.entity.vo.CommentVo;
import com.xu.blog.entity.vo.QuestVo;
import com.xu.blog.entity.vo.ReplyVo;

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
    void comment(CommentVo comment);

    /**
     * 回复
     * @param replyVo 回复实体
     * @return 字符串
     */
    String reply(ReplyVo replyVo);

}
