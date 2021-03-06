package com.mxblr.service.impl;

import com.mxblr.dao.ArticleCommentDOMapper;
import com.mxblr.dao.ArticleContentDOMapper;
import com.mxblr.dao.ArticleDOMapper;
import com.mxblr.data.dataObject.ArticleDO;
import com.mxblr.data.vo.admin.AdminArticleAddVO;
import com.mxblr.data.vo.admin.AdminArticleInfoVO;
import com.mxblr.data.vo.user.ArticleInfoVO;
import com.mxblr.data.vo.user.ArticleVO;
import com.mxblr.data.vo.admin.AdminArticleModifyVO;
import com.mxblr.error.BusinessException;
import com.mxblr.error.EmBusinessErr;
import com.mxblr.service.ArticleService;
import com.mxblr.utils.MyLog;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
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
     * 获取首页显示的文章列表
     */
    @Override
    public List<ArticleInfoVO> getArticleInfoList() throws BusinessException {
        try {
            return articleDOMapper.selectArticleInfoListVO();
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(EmBusinessErr.UNKNOWN_ERROR);
        }
    }

    /**
     * 获取后台显示的文章列表
     */
    @Override
    public List<AdminArticleInfoVO> getAdminArticleInfoList() throws BusinessException {
        try {
            return articleDOMapper.selectAdminArticleInfoList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(EmBusinessErr.UNKNOWN_ERROR);
        }
    }

    /**
     * 根据标签（分类）获取文章列表
     */
    @Override
    public List<ArticleInfoVO> getArticleInfoListByTagId(Integer id) throws BusinessException {
        try {
            return articleDOMapper.selectArticleInfoByTagId(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(EmBusinessErr.UNKNOWN_ERROR);
        }
    }

    /**
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
            e.printStackTrace();
            throw new BusinessException(EmBusinessErr.ARTICLE_NOT_EXISTS);
        }
        return articleVO;
    }

    /**
     * 增加文章
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addArticle(AdminArticleAddVO addArticleVO, Integer userId) throws BusinessException {
        try {
            addArticleVO.setUserId(userId);
            Date now = new Date(System.currentTimeMillis());
            addArticleVO.setCreatedTime(now);
            Calendar cal = Calendar.getInstance();
            cal.setTime(now);
            addArticleVO.setCreatedMonth(((byte) (cal.get(Calendar.MONTH) + 1)));
            addArticleVO.setModifiedTime(now);
            addArticleVO.setStatus(true);
            articleDOMapper.addArticle(addArticleVO);
            Integer articleId = addArticleVO.getArticleId();
            MyLog.debug("添加文章，文章信息头自增id为" + articleId);
            articleContentDOMapper.addArticleContent(articleId, addArticleVO.getContent());
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(EmBusinessErr.ARTICLE_ADD_ERROR);
        }
    }


    /**
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
     * 修改文章
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void modifyArticle(AdminArticleModifyVO adminArticleModifyVO) throws BusinessException {
        try {
            //修改文件头
            ArticleDO articleDO = new ArticleDO();
            BeanUtils.copyProperties(adminArticleModifyVO, articleDO);
            articleDO.setCreatedMonth(null);
            articleDO.setCreatedTime(null);
            articleDO.setModifiedTime(new Date(System.currentTimeMillis()));
            articleDOMapper.updateByPrimaryKeySelective(articleDO);

            //修改文本内容
            articleContentDOMapper.updateByArticleId(adminArticleModifyVO.getArticleId(), adminArticleModifyVO.getContent());
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(EmBusinessErr.ARTICLE_UPDATE_ERROR);
        }
    }
}
