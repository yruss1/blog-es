package com.xu.blog.service.Impl;

import com.xu.blog.common.exception.BusinessException;
import com.xu.blog.entity.UserDto;
import com.xu.blog.entity.mysql.User;
import com.xu.blog.repository.CommentRepository;
import com.xu.blog.repository.MysqlBlogRepository;
import com.xu.blog.repository.QuestRepository;
import com.xu.blog.repository.UserRepository;
import com.xu.blog.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author 11582
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MysqlBlogRepository blogRepository;
    private final QuestRepository questRepository;
    private final CommentRepository commentRepository;

    public UserServiceImpl(UserRepository userRepository, MysqlBlogRepository blogRepository, QuestRepository questRepository, CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.blogRepository = blogRepository;
        this.questRepository = questRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public User doLogin(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public UserDto userInfo(String id) {
        Optional<User> byId = userRepository.findById(id);
        if(byId.isPresent()){
            User user = byId.get();
            UserDto userDto = new UserDto();
            userDto.setUserName(user.getUserName());
            userDto.setId(user.getId());
            userDto.setIdentityType(user.getIdentityType());
            userDto.setBlogList(blogRepository.findByAuthor(user.getUserName()));
            userDto.setQuestList(questRepository.findQuestByExpertId(user.getId()));
            userDto.setCommentList(commentRepository.findCommentByUserId(user.getId()));
            return userDto;
        }else {
            throw new BusinessException("用户不存在，请检查！");
        }

    }
}
