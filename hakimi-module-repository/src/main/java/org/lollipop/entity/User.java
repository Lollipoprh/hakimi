package org.lollipop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author lollipop
 * @since 2026-03-19
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 账号
     */
    private String userAccount;

    /**
     * 密码
     */
    private String userPassword;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 用户简介
     */
    private String userProfile;

    /**
     * 用户角色：user/admin
     */
    private String userRole;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除
     */
    @TableLogic
    private Byte isDeleted;

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public User setUserAccount(String userAccount) {
        this.userAccount = userAccount;
        return this;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public User setUserPassword(String userPassword) {
        this.userPassword = userPassword;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public User setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public User setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
        return this;
    }

    public String getUserProfile() {
        return userProfile;
    }

    public User setUserProfile(String userProfile) {
        this.userProfile = userProfile;
        return this;
    }

    public String getUserRole() {
        return userRole;
    }

    public User setUserRole(String userRole) {
        this.userRole = userRole;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public User setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public User setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public User setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
        "id = " + id +
        ", userAccount = " + userAccount +
        ", userPassword = " + userPassword +
        ", userName = " + userName +
        ", userAvatar = " + userAvatar +
        ", userProfile = " + userProfile +
        ", userRole = " + userRole +
        ", createTime = " + createTime +
        ", updateTime = " + updateTime +
        ", isDeleted = " + isDeleted +
        "}";
    }
}
