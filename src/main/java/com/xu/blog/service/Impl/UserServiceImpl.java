package com.xu.blog.service.Impl;

import com.xu.blog.common.exception.BusinessException;
import com.xu.blog.entity.dto.UserDto;
import com.xu.blog.entity.mysql.User;
import com.xu.blog.entity.vo.RegisterVo;
import com.xu.blog.repository.CommentRepository;
import com.xu.blog.repository.MysqlBlogRepository;
import com.xu.blog.repository.QuestRepository;
import com.xu.blog.repository.UserRepository;
import com.xu.blog.service.UserService;
import org.apache.commons.lang3.ObjectUtils;
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
    public UserDto userInfo(Long id) {
        Optional<User> byId = userRepository.findById(id);
        if(byId.isPresent()){
            User user = byId.get();
            UserDto userDto = new UserDto();
            userDto.setUserName(user.getUserName());
            userDto.setId(user.getId());
            userDto.setOrganization(user.getOrganization());
            userDto.setBlogList(blogRepository.findByAuthor(user.getUserName()));
            userDto.setQuestList(questRepository.findQuestBySenderName(user.getUserName()));
            userDto.setCommentList(commentRepository.findCommentByUserId(user.getId()));
            userDto.setReplyList(questRepository.findQuestByReceiverNameAndReplyMessageIsNotNull(user.getUserName()));
            userDto.setReceiveQuestList(questRepository.findQuestByReceiverNameAndReplyMessageIsNull(user.getUserName()));
            return userDto;
        }else {
            throw new BusinessException("用户不存在，请检查！");
        }

    }

    @Override
    public String doRegister(RegisterVo registerVo) {
        User user = new User();
        try {
            user.setUserName(registerVo.getUserName());
            user.setOrganization(registerVo.getOrganization());
            user.setPassword(registerVo.getPassword());
        } catch (Exception e){
            return "注册失败，请检查输入的参数";
        }
        User save = userRepository.save(user);
        if (ObjectUtils.isNotEmpty(save)){
            return "注册成功";
        }
        return "注册失败";
    }

    @Override
    public String checkUsername(String username) {
        User byUserName = userRepository.findByUserName(username);
        if (ObjectUtils.isNotEmpty(byUserName)){
            return "用户名不可用";
        }else {
            return "用户名可用";
        }
    }
}
