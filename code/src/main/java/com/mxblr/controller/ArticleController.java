package com.mxblr.controller;

import com.mxblr.data.vo.ArticleInfoListVO;
import com.mxblr.data.vo.ArticleVO;
import com.mxblr.error.BusinessException;
import com.mxblr.response.CommonReturnType;
import com.mxblr.service.ArticleService;
import com.mxblr.utils.MyLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Ck
 * #date 2019/05/24 14:12
 */

@RequestMapping("/user/article")
@RestController
public class ArticleController extends BaseController {
    private ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    /**
     * @author Ck
     * 获取首页文章列表
     */
    @GetMapping("/getArticleInfoList")
    @ResponseBody
    public CommonReturnType getArticleInfoList() {
        MyLog.info("Request : /user/article/getArticleInfoList");
        List<ArticleInfoListVO> list;
        list = articleService.getArticleInfoList();
        return CommonReturnType.create(list);
    }

    /**
     * @author Ck
     * 根据文章头id号获取文章的具体信息
     */
    @GetMapping("getArticleById")
    @ResponseBody
    public CommonReturnType getArticleById(Integer id) throws BusinessException {
        MyLog.info("Request : /user/article/getArticleById\t[ id:" + id + " ]");
        ArticleVO articleVO = articleService.getArticleByArticleId(id);
        return CommonReturnType.create(articleVO);
    }
}
