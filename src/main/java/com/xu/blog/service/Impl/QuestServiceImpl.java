package com.xu.blog.service.Impl;

import com.xu.blog.common.exception.BusinessException;
import com.xu.blog.entity.dto.QuestDto;
import com.xu.blog.entity.es.EsQuest;
import com.xu.blog.entity.mysql.Comment;
import com.xu.blog.entity.mysql.Quest;
import com.xu.blog.entity.vo.CommentVo;
import com.xu.blog.entity.vo.QuestVo;
import com.xu.blog.entity.vo.ReplyVo;
import com.xu.blog.mapper.EsResultMapper;
import com.xu.blog.repository.CommentRepository;
import com.xu.blog.repository.QuestRepository;
import com.xu.blog.repository.UserRepository;
import com.xu.blog.service.QuestService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author 11582
 */
@Service
@Slf4j
public class QuestServiceImpl implements QuestService {

    private final CommentRepository commentRepository;
    private final QuestRepository questRepository;
    private final UserRepository userRepository;
    private final ElasticsearchTemplate elasticsearchTemplate;
    private final EsResultMapper esResultMapper;
    public QuestServiceImpl(CommentRepository commentRepository, QuestRepository questRepository, UserRepository userRepository, ElasticsearchTemplate elasticsearchTemplate, EsResultMapper esResultMapper) {
        this.commentRepository = commentRepository;
        this.questRepository = questRepository;
        this.userRepository = userRepository;
        this.elasticsearchTemplate = elasticsearchTemplate;
        this.esResultMapper = esResultMapper;
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
        if (ObjectUtils.anyNull(commentVo.getBlogId(), commentVo.getMessage(), commentVo.getUserId())){
            throw new BusinessException("参数不合法");
        }
        comment.setBlogId(commentVo.getBlogId());
        comment.setMessage(commentVo.getMessage());
        comment.setUserId(commentVo.getUserId());
        comment.setParentId(Math.max(commentVo.getParentId(), 0));
        comment.setReplyId(Math.max(commentVo.getReplyId(), 0));
        comment.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()));
        Comment save = commentRepository.save(comment);
        if (save.getId() != null){
            return String.valueOf(save.getId());
        }else {
            throw new BusinessException("评论失败！");
        }
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

    @Override
    public List<Quest> selectAll() {
        return questRepository.findAll();
    }

    @Override
    public QuestDto selectById(Integer id) {
        Quest quest;
        Optional<Quest> byId = questRepository.findById(id);
        QuestDto questDto = new QuestDto();
        if (byId.isPresent()){
            quest = byId.get();
            if (ObjectUtils.anyNull(
                    quest.getReceiverName(),
                    quest.getSenderName(),
                    quest.getSendMessage())){
                return null;
            }
            BeanUtils.copyProperties(quest, questDto);
            List<String> nameList = questRepository.findReceiverNameBySenderNameAndReceiverNameIsNotAndSenderName(quest.getSenderName());
            List<String> orgList = userRepository.findUserNameByOrganization(userRepository.findOrganizationByUserName(quest.getSenderName()), quest.getSenderName());
            nameList.addAll(orgList);
            questDto.setRelation(nameList);
        }
        return questDto;
    }

    @Override
    public Map<String, Object> search(String keyword, int pageNum, int pageSize) {
        Map<String, Object> map = new LinkedHashMap<>();
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        BoolQueryBuilder builder = QueryBuilders.boolQuery();
        if (StringUtils.isNotEmpty(keyword)){
            builder.should(QueryBuilders.matchPhraseQuery("send_message", keyword));
            builder.should(QueryBuilders.matchPhraseQuery("reply_message", keyword));
        }
        HighlightBuilder.Field contentField = new HighlightBuilder.Field("send_message").preTags("<font style='color:red'>").postTags("</font>");
        HighlightBuilder.Field titleField = new HighlightBuilder.Field("reply_message").preTags("<font style='color:red'>").postTags("</font>");
        SearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(builder)
                .withPageable(pageable)
                .withHighlightFields(titleField, contentField)
                .build();
        AggregatedPage<EsQuest> esQuests = elasticsearchTemplate.queryForPage(query, EsQuest.class, esResultMapper);
        List<EsQuest> content = esQuests.getContent();
        log.info("{}", content);
        map.put("total",esQuests.getTotalElements());
        map.put("totalPages",esQuests.getTotalPages());
        map.put("list", content);
        return map;
    }

}
