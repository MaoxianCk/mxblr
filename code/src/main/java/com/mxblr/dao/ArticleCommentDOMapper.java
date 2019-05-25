package com.mxblr.dao;

import com.mxblr.data.dataObject.ArticleCommentDO;
import com.mxblr.data.vo.ArticleCommentAdminVO;
import com.mxblr.data.vo.ArticleCommentViewVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ArticleCommentDOMapper {
    int deleteByPrimaryKey(Integer commentId);

    int insert(ArticleCommentDO record);

    int insertSelective(ArticleCommentDO record);

    ArticleCommentDO selectByPrimaryKey(Integer commentId);

    int updateByPrimaryKeySelective(ArticleCommentDO record);

    int updateByPrimaryKey(ArticleCommentDO record);

    /**
     * @author Ck
     * 根据文章id获取评论信息
     */
    @Select("SELECT\n" +
            "	NAME,\n" +
            "	email,\n" +
            "	site,\n" +
            "	content\n" +
            "FROM\n" +
            "	article_comment\n" +
            "WHERE\n" +
            "	article_id = #{id}\n" +
            "AND STATUS = 1")
    List<ArticleCommentViewVO> selectByArticleId(Integer id);

    /**
     * @author Ck
     * 根据文章id删除评论
     */
    @Delete("DELETE\n" +
            "FROM\n" +
            "	article_comment\n" +
            "WHERE\n" +
            "	article_id = #{articleId}")
    void deleteByArticleId(Integer articleId);

    /**
     * @author Ck
     * 查找所有评论和对应的文章的题目和id
     */
    @Select("SELECT\n" +
            "	comment_id,\n" +
            "	NAME,\n" +
            "	email,\n" +
            "	site,\n" +
            "	content,\n" +
            "	article_comment. STATUS,\n" +
            "	ip,\n" +
            "	article_comment.created_time,\n" +
            "	title\n" +
            "FROM\n" +
            "	article,\n" +
            "	article_comment\n" +
            "WHERE\n" +
            "	article.article_id = article_comment.article_id")
    List<ArticleCommentAdminVO> selectWithArticle();
}