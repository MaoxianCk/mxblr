package com.mxblr.dao;

import com.mxblr.data.dataObject.BlogOptionDO;

public interface BlogOptionDOMapper {
    int deleteByPrimaryKey(Integer optionId);

    int insert(BlogOptionDO record);

    int insertSelective(BlogOptionDO record);

    BlogOptionDO selectByPrimaryKey(Integer optionId);

    int updateByPrimaryKeySelective(BlogOptionDO record);

    int updateByPrimaryKey(BlogOptionDO record);
}