package com.mxblr.dao;

import com.mxblr.data.dataObject.ArticleCommentDO;
import com.mxblr.data.vo.ArticleCommentViewVO;
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

    int updateByPrimaryKeyWithBLOBs(ArticleCommentDO record);

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
}