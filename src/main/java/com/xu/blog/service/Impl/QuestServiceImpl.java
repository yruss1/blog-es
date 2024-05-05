package com.xu.blog.service.Impl;

import com.xu.blog.entity.mysql.Comment;
import com.xu.blog.entity.mysql.Quest;
import com.xu.blog.entity.vo.CommentVo;
import com.xu.blog.entity.vo.QuestVo;
import com.xu.blog.entity.vo.ReplyVo;
import com.xu.blog.repository.CommentRepository;
import com.xu.blog.repository.QuestRepository;
import com.xu.blog.service.QuestService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
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
        if (ObjectUtils.isNotEmpty(vo)
                && StringUtils.isNotEmpty(vo.getReceiverName())
                && StringUtils.isNotEmpty(vo.getSenderName())
                && StringUtils.isNotEmpty(vo.getSendMessage())
        ){
            Quest quest = new Quest();
            quest.setReceiverName(vo.getReceiverName());
            quest.setSendMessage(vo.getSendMessage());
            quest.setSenderName(vo.getSenderName());
            quest.setSendTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()));
            Quest save = questRepository.save(quest);
            if (ObjectUtils.isNotEmpty(save) && save.getId() != null){
                return save.getId();
            }
        }
        return 0;
    }

    @Override
    public String comment(CommentVo commentVo) {
        Comment comment = new Comment();
        comment.setBlogId(commentVo.getBlogId());
        comment.setMessage(commentVo.getMessage());
        comment.setUserId(commentVo.getUserId());
        comment.setParentId(commentVo.getParentId());
        comment.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()));
        Comment save = commentRepository.save(comment);
        if (save.getId() != null){
            return "评论成功";
        }
        return "评论失败";
    }

    @Override
    public String reply(ReplyVo replyVo) {
        Optional<Quest> byId = questRepository.findById(replyVo.getId());
        if (byId.isPresent()) {
            Quest quest = byId.get();
            quest.setReceiverName(replyVo.getReceiverName());
            quest.setReplyMessage(replyVo.getReplyMessage());
            quest.setReplyTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()));
            Quest save = questRepository.save(quest);
            if (!save.getReplyMessage().isEmpty()) {
                return "回复成功";
            }
        }
        return "回复失败";
    }

}
