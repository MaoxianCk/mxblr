package com.mxblr.dao;

import com.mxblr.data.dataObject.ArticleDO;
import com.mxblr.data.vo.AddArticleVO;
import com.mxblr.data.vo.ArticleInfoListVO;
import com.mxblr.data.vo.ArticleVO;
import org.apache.ibatis.annotations.*;
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
            "	article.user_id = user_info.user_id\n" +
            "AND article.status = 1\n" +
            "ORDER BY\n" +
            "	article.created_time")
    List<ArticleInfoListVO> selectArticleInfoListVO();

    /**
     * @author Ck
     * 根据文章头id号查找文章的具体信息
     */
    @Select("SELECT\n" +
            "	article.article_id,\n" +
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
            "AND article.status = 1\n" +
            "AND article.article_id = article_content.article_id\n" +
            "AND article.article_id = #{id}")
    @Results({
            @Result(property="articleInfo.articleId",column="article_id"),
            @Result(property="articleInfo.title",column="title"),
            @Result(property="articleInfo.imageUrl",column="image_url"),
            @Result(property="articleInfo.summary",column="summary"),
            @Result(property="articleInfo.name",column="NAME"),
            @Result(property="articleInfo.createdTime",column="created_time"),
            @Result(property="articleInfo.modifiedTime",column="modified_time"),
            @Result(property="content",column="content")
    })
    ArticleVO selectArticleById(Integer id);

    /**
     * @author Ck
     * 根据标签（分类）查找所有文章头信息
     */
    @Select("SELECT\n" +
            "	article_id,\n" +
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
            "	article.user_id = user_info.user_id\n" +
            "AND article.status = 1\n" +
            "AND tag_id = #{id}\n" +
            "ORDER BY\n" +
            "	article.created_time")
    List<ArticleInfoListVO> selectArticleInfoByTagId(Integer id);

    /**
     * @author Ck
     * 添加一条文章信息并获取id
     */
    @Insert("INSERT INTO article (\n" +
            "	title,\n" +
            "	image_url,\n" +
            "	summary,\n" +
            "	user_id,\n" +
            "	tag_id,\n" +
            "	created_time,\n" +
            "	created_month,\n" +
            "	modified_time,\n" +
            "	STATUS\n" +
            ")\n" +
            "VALUES\n" +
            "	(\n" +
            "		#{title},\n" +
            "		#{imageUrl},\n" +
            "		#{summary},\n" +
            "		#{userId},\n" +
            "		#{tagId},\n" +
            "		#{createdTime},\n" +
            "		#{createdMonth},\n" +
            "		#{modifiedTime},\n" +
            "		#{status}\n" +
            "	)")
    @Options(useGeneratedKeys = true, keyProperty = "addArticleVO.articleId")
    void addArticle(AddArticleVO addArticleVO);

    /**
     * @author Ck
     * 根据id修改文章状态
     */
    @Update("UPDATE article\n" +
            "SET STATUS = #{status}\n" +
            "WHERE\n" +
            "	article_id = #{articleId}")
    void updateStatusByArticleId(Integer articleId,Byte status);
}