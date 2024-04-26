package com.xu.blog.service.Impl;

import com.xu.blog.entity.mysql.User;
import com.xu.blog.repository.UserRepository;
import com.xu.blog.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author 11582
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User doLogin(String userName) {
        return userRepository.findByUserName(userName);
    }
}
