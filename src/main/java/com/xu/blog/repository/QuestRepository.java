package com.xu.blog.repository;

import com.xu.blog.entity.mysql.Quest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 11582
 */
@Repository
public interface QuestRepository extends JpaRepository<Quest, Integer> {

    /**
     * 根据专家id找对应的问题已回复
     * @param receiverName 专家用户名
     * @return 和专家相关的问题列表
     */
    List<Quest> findQuestByReceiverNameAndReplyMessageIsNotNull(String receiverName);

    /**
     * 根据专家id找对应的问题 未回复
     * @param receiverName 专家用户名
     * @return 和专家相关的问题列表
     */
    List<Quest> findQuestByReceiverNameAndReplyMessageIsNull(String receiverName);

    /**
     * 根据提问者id获取问题
     * @param senderName 提问者用户名
     * @return 问题列表
     */
    List<Quest> findQuestBySenderName(String senderName);

    /**
     * 根据被提问用户找被提问用户
     * @param senderName 提问用户名
     * @return 被提问的用户名list
     */
    List<String> findReceiverNameBySenderName(String senderName);

}
