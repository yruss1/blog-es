package com.xu.blog.service.Impl;

import cn.dev33.satoken.stp.StpUtil;
import com.xu.blog.entity.mysql.Comment;
import com.xu.blog.entity.mysql.Quest;
import com.xu.blog.entity.vo.CommentVo;
import com.xu.blog.entity.vo.QuestVo;
import com.xu.blog.entity.vo.ReplyVo;
import com.xu.blog.repository.CommentRepository;
import com.xu.blog.repository.QuestRepository;
import com.xu.blog.service.QuestService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    public int questNew(QuestVo vo) {
        Quest quest = new Quest();
        quest.setReceiverId(vo.getReceiverId());
        quest.setSendMessage(vo.getSendMessage());
        quest.setSenderId(StpUtil.getLoginIdAsString());
        quest.setSendTime(System.currentTimeMillis());
        Quest save = questRepository.save(quest);
        if (ObjectUtils.isNotEmpty(save) && save.getId() != null){
            return save.getId();
        }else {
            return 0;
        }
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

    @Override
    public String reply(ReplyVo replyVo) {
        Optional<Quest> byId = questRepository.findById(replyVo.getId());
        if (byId.isPresent()){
            Quest quest = byId.get();
            quest.setReplyMessage(replyVo.getReplyMessage());
            quest.setReplyTime(System.currentTimeMillis());
            questRepository.save(quest);
            return "回复成功";
        }else {
            return "回复失败";
        }
    }
}
