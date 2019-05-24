package com.mxblr.service.impl;

import com.mxblr.dao.ArticleCommentDOMapper;
import com.mxblr.data.vo.ArticleCommentViewVO;
import com.mxblr.service.ArticleCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ck
 * #date 2019/05/24 21:16
 */
@Service
public class ArticleCommentServiceInpl implements ArticleCommentService {
    private final ArticleCommentDOMapper articleCommentDOMapper;

    @Autowired
    public ArticleCommentServiceInpl(ArticleCommentDOMapper articleCommentDOMapper) {
        this.articleCommentDOMapper = articleCommentDOMapper;
    }

    /**
     * @author Ck
     * 根据文章id获取评论信息
     */
    @Override
    public List<ArticleCommentViewVO> getCommentsByArticleId(Integer id) {
        return articleCommentDOMapper.selectByArticleId(id);
    }
}
