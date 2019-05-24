package com.mxblr.dao;

import com.mxblr.data.dataObject.ArticleCommentDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface ArticleCommentDOMapper {
    int deleteByPrimaryKey(Integer commentId);

    int insert(ArticleCommentDO record);

    int insertSelective(ArticleCommentDO record);

    ArticleCommentDO selectByPrimaryKey(Integer commentId);

    int updateByPrimaryKeySelective(ArticleCommentDO record);

    int updateByPrimaryKeyWithBLOBs(ArticleCommentDO record);

    int updateByPrimaryKey(ArticleCommentDO record);
}