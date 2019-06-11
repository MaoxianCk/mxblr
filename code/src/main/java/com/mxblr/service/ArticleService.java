package com.mxblr.service;

import com.mxblr.data.dataObject.ArticleDO;
import com.mxblr.data.vo.user.ArticleInfoVO;
import com.mxblr.data.vo.admin.AdminArticleAddVO;
import com.mxblr.data.vo.admin.AdminArticleInfoVO;
import com.mxblr.data.vo.user.ArticleVO;
import com.mxblr.data.vo.admin.AdminArticleModifyVO;
import com.mxblr.error.BusinessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * #date 2019/05/24 14:09
 */
@Service
public interface ArticleService {
    /**
     * 根据id获取文章
     */
    ArticleDO getArticleById(Integer id);

    /**
     * 获取首页显示的文章列表
     */
    List<ArticleInfoVO> getArticleInfoList() throws BusinessException;

    /**
     * 获取后台显示的文章列表
     */
    List<AdminArticleInfoVO> getAdminArticleInfoList() throws BusinessException;

    /**
     * 根据标签（分类）获取文章列表
     */
    List<ArticleInfoVO> getArticleInfoListByTagId(Integer id) throws BusinessException;

    /**
     * 根据文章头的id号获取文章的所有信息（包含前后文章的id号）
     */
    ArticleVO getArticleByArticleId(Integer id) throws BusinessException;

    /**
     * 增加文章
     */
    void addArticle(AdminArticleAddVO adminArticleAddVO, Integer userId) throws BusinessException;

    /**
     * 删除文章
     */
    void deleteArticle(Integer articleId) throws BusinessException;

    /**
     * 修改文章的状态
     */
    void modifyArticleStatus(Integer articleId, Byte status) throws BusinessException;

    /**
     * 修改文章
     */
    void modifyArticle(AdminArticleModifyVO adminArticleModifyVO) throws BusinessException;
}
