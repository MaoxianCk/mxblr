package com.mxblr.dao;

import com.mxblr.data.dataObject.LoginRecordDO;

public interface LoginRecordDOMapper {
    int deleteByPrimaryKey(Integer loginRecordId);

    int insert(LoginRecordDO record);

    int insertSelective(LoginRecordDO record);

    LoginRecordDO selectByPrimaryKey(Integer loginRecordId);

    int updateByPrimaryKeySelective(LoginRecordDO record);

    int updateByPrimaryKey(LoginRecordDO record);
}