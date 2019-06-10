package com.mxblr.service;

import com.mxblr.data.vo.ArticleCommentAddVO;
import com.mxblr.data.vo.ArticleCommentViewVO;
import com.mxblr.data.vo.admin.AdminArticleCommentVO;
import com.mxblr.error.BusinessException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Ck
 * #date 2019/05/24 21:14
 */
@Service
public interface ArticleCommentService {
    /**
     * @author Ck
     * 根据文章id获取评论信息
     */
    List<ArticleCommentViewVO> getCommentsByArticleId(Integer id);

    /**
     * @author Ck
     * 获取后台的评论列表
     */
    List<AdminArticleCommentVO> getCommentsWithArticle();

    /**
     * @author Lrh
     * 增加评论
     */
    void addComment(ArticleCommentAddVO articleCommentAddVO, HttpServletRequest request) throws BusinessException;

    /**
     * @author Kny
     * 审核评论和逻辑删除评论
     */
    void modifyCommentStatus(Integer commentId, Byte status) throws BusinessException;
}
