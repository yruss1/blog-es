package com.xu.blog.repository;

import com.xu.blog.entity.mysql.Quest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 11582
 */
@Repository
public interface QuestRepository extends JpaRepository<Quest, String> {

    /**
     * 根据专家id找对应的问题
     * @param receiverId 专家id
     * @return 和专家相关的问题列表
     */
    List<Quest> findQuestByReceiverId(String receiverId);

    /**
     * 根据提问者id获取问题
     * @param senderId 提问者id
     * @return 问题列表
     */
    List<Quest> findQuestBySenderId(String senderId);

}
