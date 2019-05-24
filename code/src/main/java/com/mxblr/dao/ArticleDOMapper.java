package com.mxblr.dao;

import com.mxblr.data.dataObject.ArticleDO;
import com.mxblr.data.vo.ArticleInfoListVO;
import com.mxblr.data.vo.ArticleVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ArticleDOMapper {
    int deleteByPrimaryKey(Integer articleId);

    int insert(ArticleDO record);

    int insertSelective(ArticleDO record);

    ArticleDO selectByPrimaryKey(Integer articleId);

    int updateByPrimaryKeySelective(ArticleDO record);

    int updateByPrimaryKey(ArticleDO record);

    /**
     * @author Ck
     * 查找所有文章头信息，用于首页信息的显示
     */
    @Select("SELECT\n" +
            "	article_id,\n" +
            "	article_content_id,\n" +
            "	title,\n" +
            "	image_url,\n" +
            "	summary,\n" +
            "	NAME,\n" +
            "	article.created_time,\n" +
            "	article.modified_time\n" +
            "FROM\n" +
            "	article,\n" +
            "	user_info\n" +
            "WHERE\n" +
            "	article.user_id = user_info.user_id")
    List<ArticleInfoListVO> selectArticleInfoListVO();

    /**
     * @author Ck
     * 根据文章头id号查找文章的具体信息
     */
    @Select("SELECT\n" +
            "	article.article_id,\n" +
            "	article_content_id,\n" +
            "	title,\n" +
            "	image_url,\n" +
            "	summary,\n" +
            "	NAME,\n" +
            "	article.created_time,\n" +
            "	article.modified_time,\n" +
            "	content\n" +
            "FROM\n" +
            "	article,\n" +
            "	user_info,\n" +
            "	article_content\n" +
            "WHERE\n" +
            "	article.user_id = user_info.user_id\n" +
            "AND article.article_id = article_content.article_id\n" +
            "AND article.article_id = #{id}")
    ArticleVO selectArticleById(Integer id);
}