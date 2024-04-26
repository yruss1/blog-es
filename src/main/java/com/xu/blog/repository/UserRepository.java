package com.xu.blog.repository;

import com.xu.blog.entity.mysql.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 11582
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {

    /**
     * 用户信息
     * @param userName 用户名
     * @return 用户实体
     */
    User findByUserName(String userName);

}
