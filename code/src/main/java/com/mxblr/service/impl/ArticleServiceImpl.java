package com.mxblr.service.impl;

import com.mxblr.dao.ArticleDOMapper;
import com.mxblr.data.vo.ArticleInfoListVO;
import com.mxblr.data.vo.ArticleVO;
import com.mxblr.error.BusinessException;
import com.mxblr.error.EmBusinessErr;
import com.mxblr.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ck
 * #date 2019/05/24 14:14
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    private ArticleDOMapper articleDOMapper;

    @Autowired
    public ArticleServiceImpl(ArticleDOMapper articleDOMapper) {
        this.articleDOMapper = articleDOMapper;
    }

    /**
     * @author Ck
     * 获取首页显示的文章列表
     */
    @Override
    public List<ArticleInfoListVO> getArticleInfoList() {
        return articleDOMapper.selectArticleInfoListVO();
    }

    /**
     * @author Ck
     * 根据标签（分类）获取文章列表
     */
    @Override
    public List<ArticleInfoListVO> getArticleInfoListByTagId(Integer id) {
        return articleDOMapper.selectArticleInfoByTagId(id);
    }

    /**
     * @author Ck
     * 根据文章头的id号获取文章的所有信息（包含前后文章的id号）
     */
    @Override
    public ArticleVO getArticleByArticleId(Integer id) throws BusinessException {
        ArticleVO articleVO;
        try {
            articleVO = articleDOMapper.selectArticleById(id);
            if (articleVO == null) {
                throw new BusinessException(EmBusinessErr.ARTICLE_NOT_EXISTS);
            }
        } catch (Exception e) {
            throw new BusinessException(EmBusinessErr.ARTICLE_NOT_EXISTS);
        }
        return articleVO;
    }


}
