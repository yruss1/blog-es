package com.xu.blog.service.Impl;

import com.xu.blog.entity.dto.UserCommentDto;
import com.xu.blog.entity.dto.UserInfoDto;
import com.xu.blog.entity.dto.UserOrgDto;
import com.xu.blog.entity.mysql.Comment;
import com.xu.blog.entity.mysql.User;
import com.xu.blog.entity.vo.RegisterVo;
import com.xu.blog.repository.CommentRepository;
import com.xu.blog.repository.MysqlBlogRepository;
import com.xu.blog.repository.QuestRepository;
import com.xu.blog.repository.UserRepository;
import com.xu.blog.service.UserService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
    public UserInfoDto userInfo(Long id) {
        Optional<User> byId = userRepository.findById(id);
        if(byId.isPresent()){
            User user = byId.get();
            UserInfoDto userInfoDto = new UserInfoDto();
            userInfoDto.setUserName(user.getUserName());
            userInfoDto.setId(user.getId());
            userInfoDto.setOrganization(user.getOrganization());
            userInfoDto.setBlogList(blogRepository.findByAuthor(user.getUserName()));
            userInfoDto.setQuestList(questRepository.findQuestBySenderName(user.getUserName()));
            List<Comment> commentList = commentRepository.findCommentByUserId(user.getId());
            List<UserCommentDto> userCommentDtoList = new ArrayList<>();
            for (Comment comment : commentList){
                UserCommentDto userCommentDto = new UserCommentDto();
                BeanUtils.copyProperties(comment, userCommentDto);
                userCommentDto.setBlogTitle(blogRepository.findTitleById(comment.getBlogId()));
                userCommentDtoList.add(userCommentDto);
            }
            userInfoDto.setCommentList(userCommentDtoList);
            userInfoDto.setReplyList(questRepository.findQuestByReceiverNameAndReplyMessageIsNotNull(user.getUserName()));
            userInfoDto.setReceiveQuestList(questRepository.findQuestByReceiverNameAndReplyMessageIsNull(user.getUserName()));
            return userInfoDto;
        }
        return null;

    }

    @Override
    public UserInfoDto userInfo(String userName) {
        User user = userRepository.findByUserName(userName);
        UserInfoDto userInfoDto = new UserInfoDto();
        if (ObjectUtils.isNotEmpty(user)){
            userInfoDto.setUserName(user.getUserName());
            userInfoDto.setId(user.getId());
            userInfoDto.setOrganization(user.getOrganization());
            userInfoDto.setBlogList(blogRepository.findByAuthor(user.getUserName()));
            userInfoDto.setQuestList(questRepository.findQuestBySenderName(user.getUserName()));
            List<Comment> commentList = commentRepository.findCommentByUserId(user.getId());
            List<UserCommentDto> userCommentDtoList = new ArrayList<>();
            for (Comment comment : commentList){
                UserCommentDto userCommentDto = new UserCommentDto();
                BeanUtils.copyProperties(comment, userCommentDto);
                userCommentDto.setBlogTitle(blogRepository.findTitleById(comment.getBlogId()));
                userCommentDtoList.add(userCommentDto);
            }
            userInfoDto.setCommentList(userCommentDtoList);
            userInfoDto.setReplyList(questRepository.findQuestByReceiverNameAndReplyMessageIsNotNull(user.getUserName()));
            userInfoDto.setReceiveQuestList(questRepository.findQuestByReceiverNameAndReplyMessageIsNull(user.getUserName()));
            return userInfoDto;
        }
        return null;
    }

    @Override
    public String doRegister(RegisterVo registerVo) {
        if (ObjectUtils.anyNull(registerVo.getUserName(), registerVo.getOrganization(), registerVo.getPassword())){
            return "注册失败，请检查输入的参数";
        }
        User user = new User();
        user.setUserName(registerVo.getUserName());
        user.setOrganization(registerVo.getOrganization());
        user.setPassword(registerVo.getPassword());
        User save = userRepository.save(user);
        if (ObjectUtils.isNotEmpty(save)){
            return "注册成功";
        }
        return "注册失败,请重试";
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

    @Override
    public List<UserOrgDto> selectByOrg(String org, Long userId) {
        return null;
    }
}
