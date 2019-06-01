package com.mxblr.service;

import com.mxblr.data.dataObject.UserInfoDO;
import com.mxblr.error.BusinessException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Ck
 * #date 2019/05/28 19:54
 */
public interface UserService {
    /**
     * @author Ck
     * 用户登录
     */
    UserInfoDO userLogin(String account, String password, HttpServletRequest request) throws BusinessException;

    /**
     * @author Ck
     * 管理员增加用户, 新加的用户只能是写手, 编辑只能由超管手动修改, 超管只能手动修改数据库得到
     */
    void addUser(String account, String password) throws BusinessException;

    /**
     * @author Ck
     * 管理员修改用户角色, 超管只能修改用户为编辑或者写手, 并且不能修改超管
     */
    void modifyRole(Integer userId, byte role) throws BusinessException;

    /**
     * @author Ck
     * 用户修改密码
     */
    void modifyPassword(Integer userId, String password) throws BusinessException;

    /**
     * @author Ck
     * 用户修改信息
     */
    void modifyUserInfo(Integer userId, UserInfoDO userInfoDO) throws BusinessException;
}