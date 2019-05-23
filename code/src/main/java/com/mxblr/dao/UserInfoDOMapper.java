package com.mxblr.dao;

import com.mxblr.data.dataObject.UserInfoDO;

public interface UserInfoDOMapper {
    int deleteByPrimaryKey(Integer userInfoId);

    int insert(UserInfoDO record);

    int insertSelective(UserInfoDO record);

    UserInfoDO selectByPrimaryKey(Integer userInfoId);

    int updateByPrimaryKeySelective(UserInfoDO record);

    int updateByPrimaryKey(UserInfoDO record);
}