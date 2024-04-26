package com.xu.blog.repository;

import com.xu.blog.entity.mysql.Quest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 11582
 */
@Repository
public interface QuestRepository extends JpaRepository<Quest, String> {
}
