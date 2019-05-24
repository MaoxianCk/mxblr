package com.mxblr.dao;

import com.mxblr.data.dataObject.NoticeEmailDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface NoticeEmailDOMapper {
    int deleteByPrimaryKey(Integer emailId);

    int insert(NoticeEmailDO record);

    int insertSelective(NoticeEmailDO record);

    NoticeEmailDO selectByPrimaryKey(Integer emailId);

    int updateByPrimaryKeySelective(NoticeEmailDO record);

    int updateByPrimaryKey(NoticeEmailDO record);
}