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
     * @param expertId 专家id
     * @return 和专家相关的问题列表
     */
    List<Quest> findQuestByExpertId(String expertId);

}
