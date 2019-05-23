package com.mxblr.dao;

import com.mxblr.data.dataObject.ArticleContentDO;

public interface ArticleContentDOMapper {
    int deleteByPrimaryKey(Integer articleContentId);

    int insert(ArticleContentDO record);

    int insertSelective(ArticleContentDO record);

    ArticleContentDO selectByPrimaryKey(Integer articleContentId);

    int updateByPrimaryKeySelective(ArticleContentDO record);

    int updateByPrimaryKeyWithBLOBs(ArticleContentDO record);
}