package com.mxblr.service.impl;

import com.mxblr.dao.ArticleCommentDOMapper;
import com.mxblr.dao.ArticleContentDOMapper;
import com.mxblr.dao.ArticleDOMapper;
import com.mxblr.data.dataObject.ArticleDO;
import com.mxblr.data.vo.AddArticleVO;
import com.mxblr.data.vo.ArticleInfoListVO;
import com.mxblr.data.vo.ArticleVO;
import com.mxblr.error.BusinessException;
import com.mxblr.error.EmBusinessErr;
import com.mxblr.service.ArticleService;
import com.mxblr.utils.MyLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Ck
 * #date 2019/05/24 14:14
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    private ArticleDOMapper articleDOMapper;
    private ArticleContentDOMapper articleContentDOMapper;
    private ArticleCommentDOMapper articleCommentDOMapper;

    @Autowired
    public ArticleServiceImpl(ArticleDOMapper articleDOMapper, ArticleContentDOMapper articleContentDOMapper, ArticleCommentDOMapper articleCommentDOMapper) {
        this.articleDOMapper = articleDOMapper;
        this.articleContentDOMapper = articleContentDOMapper;
        this.articleCommentDOMapper = articleCommentDOMapper;
    }

    @Override
    public ArticleDO getArticleById(Integer id) {
        return articleDOMapper.selectByPrimaryKey(id);
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

    /**
     * @author Ck
     * 增加文章
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addArticle(AddArticleVO addArticleVO, Integer userId) throws BusinessException {
        try {
            articleDOMapper.addArticle(addArticleVO);
            Integer articleId = addArticleVO.getArticleId();
            MyLog.debug("添加文章，文章信息头自增id为" + articleId);
            articleContentDOMapper.addArticleContent(articleId, addArticleVO.getContent());
        } catch (Exception e) {
            throw new BusinessException(EmBusinessErr.ARTICLE_ADD_ERROR);
        }
    }


    /**
     * @author Ck
     * 删除文章
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteArticle(Integer articleId) throws BusinessException {
        try {
            //彻底删除一篇文章时，同时删除文章头，文章内容，文章对应的评论
            articleDOMapper.deleteByPrimaryKey(articleId);
            articleContentDOMapper.deleteByArticleId(articleId);
            articleCommentDOMapper.deleteByArticleId(articleId);
        } catch (Exception e) {
            throw new BusinessException(EmBusinessErr.ARTICLE_DELETE_ERROR);
        }
    }


    /**
     * @author Ck
     * 修改文章的状态
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void modifyArticleStatus(Integer articleId, Byte status) throws BusinessException {
        try {
            articleDOMapper.updateStatusByArticleId(articleId, status);
        } catch (Exception e) {
            throw new BusinessException(EmBusinessErr.ARTICLE_DELETE_ERROR);
        }
    }

    /**
     * @author Ck
     * 修改文章
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void modifyArticle(AddArticleVO addArticleVO) throws BusinessException {
        try {
            //TODO 修改文章头和文章信息
        } catch (Exception e) {
            throw new BusinessException(EmBusinessErr.ARTICLE_UPDATE_ERROR);
        }
    }
}
