package com.mxblr.service.impl;

import com.mxblr.dao.ArticleCommentDOMapper;
import com.mxblr.data.dataObject.ArticleCommentDO;
import com.mxblr.data.mo.admin.AdminArticleCommentMO;
import com.mxblr.data.vo.user.ArticleCommentAddVO;
import com.mxblr.data.vo.user.ArticleCommentVO;
import com.mxblr.data.vo.admin.AdminArticleCommentVO;
import com.mxblr.error.BusinessException;
import com.mxblr.error.EmBusinessErr;
import com.mxblr.service.ArticleCommentService;
import com.mxblr.utils.Constants;
import com.mxblr.utils.MyIpUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * #date 2019/05/24 21:16
 */
@Service
public class ArticleCommentServiceImpl implements ArticleCommentService {
    private final ArticleCommentDOMapper articleCommentDOMapper;

    @Autowired
    public ArticleCommentServiceImpl(ArticleCommentDOMapper articleCommentDOMapper) {
        this.articleCommentDOMapper = articleCommentDOMapper;
    }

    /**
     * 根据文章id获取评论信息
     */
    @Override
    public List<ArticleCommentVO> getCommentsByArticleId(Integer articleId) {
        return articleCommentDOMapper.selectByArticleId(articleId);
    }

    /**
     * 获取后台的评论列表
     */
    @Override
    public List<AdminArticleCommentVO> getCommentsWithArticle() {
        List<AdminArticleCommentMO> adminArticleCommentMOS = articleCommentDOMapper.selectWithArticle();
        List<AdminArticleCommentVO> adminArticleCommentVOS = new ArrayList<>();

        for (AdminArticleCommentMO item : adminArticleCommentMOS) {
            AdminArticleCommentVO adminArticleCommentVO = new AdminArticleCommentVO(item.getCommentId(),
                    item.getArticleId(),
                    item.getName(),
                    item.getEmail(),
                    item.getSite(),
                    item.getStatus(),
                    item.getCreatedTime(),
                    item.getContent(),
                    item.getTitle());
            adminArticleCommentVO.setIp(MyIpUtil.longToIp(item.getIp()));
            adminArticleCommentVOS.add(adminArticleCommentVO);
        }

        return adminArticleCommentVOS;
    }

    /**
     * 增加评论
     */
    @Override
    public void addComment(ArticleCommentAddVO articleCommentAddVO, HttpServletRequest request) throws BusinessException {
        ArticleCommentDO articleCommentDO = new ArticleCommentDO();
        BeanUtils.copyProperties(articleCommentAddVO, articleCommentDO);

        Byte status = Constants.COMMENT_STATUS_NOT_APPROVE;
        articleCommentDO.setStatus(status);
        Integer ip = MyIpUtil.getIp(request);
        articleCommentDO.setIp(ip);
        Date createTime = new Timestamp(System.currentTimeMillis());
        articleCommentDO.setCreatedTime(createTime);

        try {
            articleCommentDOMapper.insertSelective(articleCommentDO);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(EmBusinessErr.COMMENT_ADD_ERROR);
        }
    }

    /**
     * 审核评论和逻辑删除评论
     */
    @Override
    public void modifyCommentStatus(Integer commentId, Byte status) throws BusinessException {
        try {
            articleCommentDOMapper.updateStatusByCommentId(commentId, status);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(EmBusinessErr.COMMENT_STATUS_MODIFY_ERROR);
        }
    }
}























