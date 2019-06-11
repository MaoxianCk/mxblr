package com.mxblr.dao;

import com.mxblr.data.dataObject.ArticleDO;
import com.mxblr.data.vo.user.ArticleInfoVO;
import com.mxblr.data.vo.admin.AdminArticleAddVO;
import com.mxblr.data.vo.admin.AdminArticleInfoVO;
import com.mxblr.data.vo.user.ArticleVO;
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
     * 查找所有文章头信息，用于首页信息的显示
     */
    @Select("SELECT\n" +
            "	article_id,\n" +
            "	title,\n" +
            "	summary,\n" +
            "	user_info.NAME user_name,\n" +
            "	article.created_time,\n" +
            "	article.modified_time,\n" +
            "	image\n" +
            "FROM\n" +
            "	article,\n" +
            "	user_info,\n" +
            "   image\n" +
            "WHERE\n" +
            "	article.user_id = user_info.user_id\n" +
            "AND article.image_id = image.image_id\n" +
            "AND article.status = 1\n" +
            "ORDER BY\n" +
            "	article.created_time desc")
    List<ArticleInfoVO> selectArticleInfoListVO();

    /**
     * 查找所有文章列表，用于后台管理界面显示所有文章
     */
    @Select("SELECT\n" +
            "	article.article_id,\n" +
            "	title,\n" +
            "	article.user_id,\n" +
            "	user_info.`name` user_name,\n" +
            "	article.tag_id,\n" +
            "	tag.`name` tag_name,\n" +
            "	article.created_time,\n" +
            "	article.modified_time,\n" +
            "	article.`status`\n" +
            "FROM\n" +
            "	article,\n" +
            "	user_info,\n" +
            "	tag\n" +
            "WHERE\n" +
            "	article.user_id = `user_info`.user_id\n" +
            "AND article.tag_id = tag.tag_id\n" +
            "ORDER BY\n" +
            "	article.modified_time DESC")
    List<AdminArticleInfoVO> selectAdminArticleInfoList();

    /**
     * 根据文章头id号查找文章的具体信息
     */
    @Select("SELECT\n" +
            "	article.article_id,\n" +
            "	article.tag_id,\n" +
            "	article.image_id,\n" +
            "	title,\n" +
            "	image,\n" +
            "	summary,\n" +
            "	user_info.NAME,\n" +
            "	article.created_time,\n" +
            "	article.modified_time,\n" +
            "	content\n" +
            "FROM\n" +
            "	article,\n" +
            "	user_info,\n" +
            "   image,\n" +
            "	article_content\n" +
            "WHERE\n" +
            "	article.user_id = user_info.user_id\n" +
            "AND article.status = 1\n" +
            "AND article.image_id = image.image_id\n" +
            "AND article.article_id = article_content.article_id\n" +
            "AND article.article_id = #{id}")
    @Results({
            @Result(property = "articleInfo.articleId", column = "article_id"),
            @Result(property = "articleInfo.tagId", column = "tag_id"),
            @Result(property = "articleInfo.imageId", column = "image_id"),
            @Result(property = "articleInfo.title", column = "title"),
            @Result(property = "articleInfo.image", column = "image"),
            @Result(property = "articleInfo.summary", column = "summary"),
            @Result(property = "articleInfo.userName", column = "NAME"),
            @Result(property = "articleInfo.createdTime", column = "created_time"),
            @Result(property = "articleInfo.modifiedTime", column = "modified_time"),
            @Result(property = "content", column = "content")
    })
    ArticleVO selectArticleById(Integer id);

    /**
     * 根据标签（分类）查找所有文章头信息
     */
    @Select("SELECT\n" +
            "	article_id,\n" +
            "	title,\n" +
            "	image,\n" +
            "	summary,\n" +
            "	user_info.NAME,\n" +
            "	article.created_time,\n" +
            "	article.modified_time\n" +
            "FROM\n" +
            "	article,\n" +
            "   image,\n" +
            "	user_info\n" +
            "WHERE\n" +
            "	article.user_id = user_info.user_id\n" +
            "AND article.image_id = image.image_id\n" +
            "AND article.status = 1\n" +
            "AND tag_id = #{id}\n" +
            "ORDER BY\n" +
            "	article.created_time")
    List<ArticleInfoVO> selectArticleInfoByTagId(Integer id);

    /**
     * 添加一条文章信息并获取id
     */
    @Insert("INSERT INTO article (\n" +
            "	title,\n" +
            "	image_id,\n" +
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
            "		#{imageId},\n" +
            "		#{summary},\n" +
            "		#{userId},\n" +
            "		#{tagId},\n" +
            "		#{createdTime},\n" +
            "		#{createdMonth},\n" +
            "		#{modifiedTime},\n" +
            "		#{status}\n" +
            "	)")
    @Options(useGeneratedKeys = true, keyProperty = "articleId")
    void addArticle(AdminArticleAddVO adminArticleAddVO);

    /**
     * 根据id修改文章状态
     */
    @Update("UPDATE article\n" +
            "SET STATUS = #{status}\n" +
            "WHERE\n" +
            "	article_id = #{articleId}")
    void updateStatusByArticleId(Integer articleId, Byte status);
}