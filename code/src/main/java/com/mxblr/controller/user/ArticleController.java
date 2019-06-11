package com.mxblr.controller.user;

import com.mxblr.controller.BaseController;
import com.mxblr.data.vo.user.ArticleInfoVO;
import com.mxblr.data.vo.user.ArticleVO;
import com.mxblr.error.BusinessException;
import com.mxblr.response.CommonReturnType;
import com.mxblr.service.ArticleService;
import com.mxblr.utils.MyLog;
import com.mxblr.validator.MyValidation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * #date 2019/05/24 14:12
 */
@RequestMapping("/user/article/")
@RestController("ArticleController")
public class ArticleController extends BaseController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    /**
     * 获取首页文章列表
     */
    @GetMapping("getArticleInfoList")
    @ResponseBody
    public CommonReturnType getArticleInfoList() throws BusinessException {
        MyLog.info("Request : /user/article/getArticleInfoList");
        List<ArticleInfoVO> list;
        list = articleService.getArticleInfoList();
        return CommonReturnType.create(list);
    }

    /**
     * 根据标签（分类）获取文章列表
     */
    @GetMapping("getArticleInfoListByTagId")
    @ResponseBody
    public CommonReturnType getArticleInfoListByTagId(Integer id) throws BusinessException {
        MyValidation.checkIntNull(id);
        MyLog.info("Request : /user/article/getArticleInfoListByTagId\t[ id: " + id + " ]");
        List<ArticleInfoVO> list = articleService.getArticleInfoListByTagId(id);
        return CommonReturnType.create(list);
    }

    /**
     * 根据文章头id号获取文章的具体信息
     */
    @GetMapping("getArticleById")
    @ResponseBody
    public CommonReturnType getArticleById(@RequestParam(name = "articleId") Integer articleId) throws BusinessException {
        MyValidation.checkIntNull(articleId);
        MyLog.info("Request : /user/article/getArticleById\t[ id: " + articleId + " ]");
        ArticleVO articleVO = articleService.getArticleByArticleId(articleId);
        return CommonReturnType.create(articleVO);
    }
}
