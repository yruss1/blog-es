package com.xu.blog.entity.dto;

import com.xu.blog.entity.mysql.Comment;
import com.xu.blog.entity.mysql.MysqlBlog;
import com.xu.blog.entity.mysql.Quest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author 11582
 */
@ApiModel("用户信息实体类")
public class UserDto {
    @ApiModelProperty("用户id")
    private String id;
    @ApiModelProperty("用户名")
    private String userName;
    @ApiModelProperty("用户身份类型")
    private String identityType;
    @ApiModelProperty("用户组织")
    private String organization;
    @ApiModelProperty("用户提问历史(普通用户身份)")
    private List<Quest> questList;
    @ApiModelProperty("用户博客历史(专家用户身份)")
    private List<MysqlBlog> blogList;
    @ApiModelProperty("用户评论历史")
    private List<Comment> commentList;
    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIdentityType() {
        return identityType;
    }

    public void setIdentityType(String identityType) {
        this.identityType = identityType;
    }

    public List<Quest> getQuestList() {
        return questList;
    }

    public void setQuestList(List<Quest> questList) {
        this.questList = questList;
    }

    public List<MysqlBlog> getBlogList() {
        return blogList;
    }

    public void setBlogList(List<MysqlBlog> blogList) {
        this.blogList = blogList;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public UserDto(String id, String userName, String identityType, List<Quest> questList, List<MysqlBlog> blogList, List<Comment> commentList) {
        this.id = id;
        this.userName = userName;
        this.identityType = identityType;
        this.questList = questList;
        this.blogList = blogList;
        this.commentList = commentList;
    }

    public UserDto() {
    }
}
