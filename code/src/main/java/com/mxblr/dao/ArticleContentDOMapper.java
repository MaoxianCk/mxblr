package com.mxblr.dao;

import com.mxblr.data.dataObject.ArticleContentDO;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface ArticleContentDOMapper {
    int deleteByPrimaryKey(Integer articleContentId);

    int insert(ArticleContentDO record);

    int insertSelective(ArticleContentDO record);

    ArticleContentDO selectByPrimaryKey(Integer articleContentId);

    int updateByPrimaryKeySelective(ArticleContentDO record);

    int updateByPrimaryKeyWithBLOBs(ArticleContentDO record);

    int updateByPrimaryKey(ArticleContentDO record);

    @Insert("INSERT INTO article_content (article_id, content)\n" +
            "VALUES\n" +
            "	(#{articleId}, #{content})")
    void addArticleContent(Integer articleId, String content);

    @Delete("DELETE\n" +
            "FROM\n" +
            "	article_content\n" +
            "WHERE\n" +
            "	article_id = #{articleId}")
    void deleteByArticleId(Integer articleId);
}