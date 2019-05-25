package com.mxblr.service;

import com.mxblr.data.vo.ArticleCommentAdminVO;
import com.mxblr.data.vo.ArticleCommentViewVO;
import org.springframework.stereotype.Service;

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
    List<ArticleCommentAdminVO> getCommentsWithArticle();
}
