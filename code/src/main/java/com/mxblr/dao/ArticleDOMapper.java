package com.mxblr.dao;

import com.mxblr.data.dataObject.ArticleDO;

public interface ArticleDOMapper {
    int deleteByPrimaryKey(Integer articleId);

    int insert(ArticleDO record);

    int insertSelective(ArticleDO record);

    ArticleDO selectByPrimaryKey(Integer articleId);

    int updateByPrimaryKeySelective(ArticleDO record);

    int updateByPrimaryKey(ArticleDO record);
}