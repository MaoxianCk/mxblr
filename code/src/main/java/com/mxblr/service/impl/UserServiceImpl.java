package com.mxblr.service.impl;

import com.mxblr.dao.LoginRecordDOMapper;
import com.mxblr.dao.UserDOMapper;
import com.mxblr.dao.UserInfoDOMapper;
import com.mxblr.data.dataObject.LoginRecordDO;
import com.mxblr.data.dataObject.UserDO;
import com.mxblr.data.dataObject.UserInfoDO;
import com.mxblr.data.mo.UserMO;
import com.mxblr.error.BusinessException;
import com.mxblr.error.EmBusinessErr;
import com.mxblr.service.UserService;
import com.mxblr.utils.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @author Ck
 * #date 2019/05/28 20:35
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserDOMapper userDOMapper;
    private final UserInfoDOMapper userInfoDOMapper;
    private final LoginRecordDOMapper loginRecordDOMapper;

    public UserServiceImpl(UserDOMapper userDOMapper, UserInfoDOMapper userInfoDOMapper, LoginRecordDOMapper loginRecordDOMapper) {
        this.userDOMapper = userDOMapper;
        this.userInfoDOMapper = userInfoDOMapper;
        this.loginRecordDOMapper = loginRecordDOMapper;
    }


    /**
     * @author Ck
     * 用户登录
     */
    @Override
    public void userLogin(String account, String password, HttpServletRequest request) throws BusinessException {
        UserDO userDO;
        List<UserDO> list = userDOMapper.selectByAccount(account);
        if (list.isEmpty()) {
            throw new BusinessException(EmBusinessErr.USER_LOGIN_FAILED);
        }
        if (list.size() > 1) {
            throw new BusinessException(EmBusinessErr.USER_ERROR);
        }

        try {
            //验证密码 如果在这里没有报错说明登录成功
            userDO = list.get(0);
            String salt = userDO.getSalt();
            password = MyPasswordUtil.getPassword(password, salt);
            if (!password.equals(userDO.getPassword())) {
                handleLoginCnt(request);
                throw new BusinessException((EmBusinessErr.USER_LOGIN_FAILED));
            }
        } catch (Exception e) {
            handleLoginCnt(request);
            throw new BusinessException((EmBusinessErr.USER_LOGIN_FAILED));
        }

        //添加登录记录
        LoginRecordDO record = new LoginRecordDO();
        record.setUserId(userDO.getUserId());
        record.setLoginTime(new Date(System.currentTimeMillis()));
        Integer ip = MyIpUtil.getIp(request);
        record.setIp(ip);
        loginRecordDOMapper.insert(record);

        //添加session
        UserInfoDO userInfoDO = userInfoDOMapper.selectByUserId(userDO.getUserId());
        MySessionUtil.setAttribute(request, Constants.SESSION_USER_ID, userDO.getUserId());
        MySessionUtil.setAttribute(request, Constants.SESSION_USER_ROLE, userInfoDO.getRole());

        //TODO cookie记住登录状态
    }

    /**
     * @author Ck
     * 管理员增加用户, 新加的用户只能是写手, 编辑只能由超管手动修改, 超管只能手动修改数据库得到
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addUser(String account, String password) throws BusinessException {
        if (!userDOMapper.selectByAccount(account).isEmpty()) {
            throw new BusinessException(EmBusinessErr.USER_REGISTER_ERR_ACCOUNT_REPEAT);
        }
        //获取盐并加密
        String salt = SaltUtil.getRandomSalt32();
        password = MyPasswordUtil.getPassword(password, salt);
        //存入账号表并获取自增id
        UserMO userMO = new UserMO(account, password, salt);
        userDOMapper.insertUser(userMO);
        int userId = userMO.getUserId();

        //创建用户信息表并设置基础信息
        UserInfoDO userInfoDO = new UserInfoDO();
        userInfoDO.setCount(0);
        Date now = new Date(System.currentTimeMillis());
        userInfoDO.setCreatedTime(now);
        userInfoDO.setModifiedTime(now);
        userInfoDO.setRole(Constants.USER_ROLE_WRITER);
        userInfoDO.setUserId(userId);

        userInfoDOMapper.insert(userInfoDO);
    }

    /**
     * @author Ck
     * 管理员修改用户角色, 超管只能修改用户为编辑或者写手, 并且不能修改超管
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void modifyUserInfo(Integer userId, UserInfoDO userInfoDO) throws BusinessException {

    }

    /**
     * @author Ck
     * 用户修改密码
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void modifyRole(Integer userId, byte role) throws BusinessException {

    }

    /**
     * @author Ck
     * 用户修改信息
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void modifyPassword(Integer userId, String password) {

    }

    /**
     * @author Ck
     * 用户登录次数控制，目前使用session记录，连续登录3次后禁止并发送邮件通知超级管理员
     * TODO 使用持久化cnt
     */
    private void handleLoginCnt(HttpServletRequest request) throws BusinessException {
        Integer cnt;
        try {
            cnt = (Integer) MySessionUtil.getAttribute(request, Constants.SESSION_USER_LOGIN_CNT);
        } catch (Exception e) {
            cnt = 0;
        }
        cnt++;
        if (cnt >= 3) {
            //TODO 邮件通知
            throw new BusinessException(EmBusinessErr.USER_LOGIN_FAILED);
        } else {
            MySessionUtil.setAttribute(request, Constants.SESSION_USER_LOGIN_CNT, cnt);
        }
    }
}
