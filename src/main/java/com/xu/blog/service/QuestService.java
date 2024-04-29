package com.xu.blog.service;

import com.xu.blog.entity.QuestVo;
import com.xu.blog.entity.mysql.Comment;

/**
 * @author 11582
 */
public interface QuestService {

    /**
     * 新提问
     * @param vo 提问对象
     * @return 是否成功
     */
    String questNew(QuestVo vo);

    /**
     * 追加提问
     * @param comment 评论对象
     * @return 是否成功
     */
    String comment(Comment comment);

}
