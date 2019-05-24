package com.mxblr.dao;

import com.mxblr.data.dataObject.BlogOptionDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface BlogOptionDOMapper {
    int deleteByPrimaryKey(Integer optionId);

    int insert(BlogOptionDO record);

    int insertSelective(BlogOptionDO record);

    BlogOptionDO selectByPrimaryKey(Integer optionId);

    int updateByPrimaryKeySelective(BlogOptionDO record);

    int updateByPrimaryKey(BlogOptionDO record);
}