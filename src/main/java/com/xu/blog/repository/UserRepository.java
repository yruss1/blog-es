package com.xu.blog.repository;

import com.xu.blog.entity.mysql.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 11582
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 用户信息
     * @param userName 用户名
     * @return 用户实体
     */
    User findByUserName(String userName);

    /**
     * 用户组织内其他成员
     * @param organization 组织
     * @return 用户列表
     */
    List<User> findUserByOrganization(String organization);

}
