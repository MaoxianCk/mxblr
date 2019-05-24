package com.mxblr.service;

import com.mxblr.data.vo.ArticleInfoListVO;
import com.mxblr.data.vo.ArticleVO;
import com.mxblr.error.BusinessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ck
 * #date 2019/05/24 14:09
 */
@Service
public interface ArticleService {
    /**
     * @author Ck
     * 获取首页显示的文章列表
     */
    List<ArticleInfoListVO> getArticleInfoList();

    /**
     * @author Ck
     * 根据标签（分类）获取文章列表
     */
    List<ArticleInfoListVO> getArticleInfoListByTagId(Integer id);

    /**
     * @author Ck
     * 根据文章头的id号获取文章的所有信息（包含前后文章的id号）
     */
    ArticleVO getArticleByArticleId(Integer id) throws BusinessException;
}
