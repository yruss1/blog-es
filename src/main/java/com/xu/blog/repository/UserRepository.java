package com.xu.blog.repository;

import com.xu.blog.entity.mysql.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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
     * 根据用户组织返回用户名
     * @param organization 组织
     * @param userName 用户名
     * @return 用户名列表
     */
    @Query(value = "select u.user_name from t_user u where u.organization = ?1 and u.user_name != ?2", nativeQuery = true)
    List<String> findUserNameByOrganization(String organization, String userName);

    /**
     * 根据用户id返回用户名
     * @param id 用户id
     * @return 用户名
     */
    @Query(value = "select u.user_name from t_user u where u.id = ?1", nativeQuery = true)
    String findUsernameById(Long id);

}
