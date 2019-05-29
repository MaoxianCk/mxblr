package com.mxblr.dao;

import com.mxblr.data.dataObject.ArticleContentDO;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
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

    /**
     * @author Ck
     * 添加文章内容
     */
    @Insert("INSERT INTO article_content (article_id, content)\n" +
            "VALUES\n" +
            "	(#{articleId}, #{content})")
    void addArticleContent(Integer articleId, String content);

    /**
     * @author Ck
     * 删除文章内容
     */
    @Delete("DELETE\n" +
            "FROM\n" +
            "	article_content\n" +
            "WHERE\n" +
            "	article_id = #{articleId}")
    void deleteByArticleId(Integer articleId);

    /**
     * @author Ck
     * 修改文章内容
     */
    @Update("UPDATE article_content\n" +
            "SET content = #{id}\n" +
            "WHERE\n" +
            "	article_id = #{content}")
    void updateByArticleId(Integer id,String content);
}