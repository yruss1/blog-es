package com.xu.blog.service.Impl;

import com.xu.blog.entity.CommentVo;
import com.xu.blog.entity.QuestVo;
import com.xu.blog.entity.mysql.Comment;
import com.xu.blog.entity.mysql.Quest;
import com.xu.blog.repository.CommentRepository;
import com.xu.blog.repository.QuestRepository;
import com.xu.blog.service.QuestService;
import org.springframework.stereotype.Service;

/**
 * @author 11582
 */
@Service
public class QuestServiceImpl implements QuestService {

    private final CommentRepository commentRepository;
    private final QuestRepository questRepository;

    public QuestServiceImpl(CommentRepository commentRepository, QuestRepository questRepository) {
        this.commentRepository = commentRepository;
        this.questRepository = questRepository;
    }

    @Override
    public void questNew(QuestVo vo) {
        Quest quest = new Quest();
        quest.setExpertId(vo.getExpertId());
        quest.setMessage(vo.getMessage());
        quest.setUserId(vo.getUserId());
        quest.setTime(System.currentTimeMillis());
        questRepository.save(quest);
    }

    @Override
    public void comment(CommentVo commentVo) {
        Comment comment = new Comment();
        comment.setQuestId(commentVo.getQuestId());
        comment.setMessage(commentVo.getMessage());
        comment.setUserId(commentVo.getUserId());
        comment.setTime(System.currentTimeMillis());
        commentRepository.save(comment);
    }
}
