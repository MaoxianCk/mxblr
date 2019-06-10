package com.mxblr.dao;

import com.mxblr.data.dataObject.ArticleCommentDO;
import com.mxblr.data.mo.admin.AdminArticleCommentMO;
import com.mxblr.data.vo.ArticleCommentViewVO;
import org.apache.ibatis.annotations.*;
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
            "AND status = 1")
    List<ArticleCommentViewVO> selectByArticleId(Integer id);

    /**
     * @author Ck
     * 根据文章id逻辑删除评论
     */
    @Update("update\n" +
            "article_comment\n" +
            "set\n" +
            "status = 2\n" +
            "WHERE\n" +
            "   article_id = #{articleId}")
    void deleteByArticleId(Integer articleId);

    /**
     * @author Ck
     * 查找所有评论和对应的文章的题目和id
     */
    @Select("SELECT\n" +
            "	comment_id,\n" +
            "   article.article_id,\n" +
            "	name,\n" +
            "	email,\n" +
            "	site,\n" +
            "	content,\n" +
            "	article_comment.status,\n" +
            "	ip,\n" +
            "	article_comment.created_time,\n" +
            "	title\n" +
            "FROM\n" +
            "	article,\n" +
            "	article_comment\n" +
            "WHERE\n" +
            "	article.article_id = article_comment.article_id\n" +
            "AND article_comment.status != 2")
    List<AdminArticleCommentMO> selectWithArticle();

    /**
     * @author Kny
     * 通过改变status审核评论和逻辑删除评论
     */
    @Update("update article_comment set status = #{status} where comment_id = #{commentId}")
    void updateStatusByCommentId(Integer commentId, Byte status);
}