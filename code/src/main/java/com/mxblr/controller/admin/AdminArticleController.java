package com.mxblr.controller.admin;

import com.mxblr.data.dataObject.ArticleDO;
import com.mxblr.data.vo.AddArticleVO;
import com.mxblr.error.BusinessException;
import com.mxblr.error.EmBusinessErr;
import com.mxblr.response.CommonReturnType;
import com.mxblr.service.ArticleService;
import com.mxblr.utils.Constants;
import com.mxblr.utils.MyLog;
import com.mxblr.utils.MySessionUtil;
import com.mxblr.validator.MyValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author Ck
 * #date 2019/05/24 22:39
 */
@RequestMapping("/admin/article/")
@RestController
public class AdminArticleController {
    private final ArticleService articleService;

    @Autowired
    public AdminArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    /**
     * @author Ck
     * 增加文章
     */
    @PostMapping("addArticle")
    @ResponseBody
    public CommonReturnType addArticle(@RequestBody @Valid AddArticleVO addArticleVO, BindingResult result, HttpServletRequest httpServletRequest) throws BusinessException {
        MyLog.info("Request : /admin/article/addArticle");
        MyValidation.validateObject(EmBusinessErr.ARTICLE_ADD_ERROR, result);
        //获取用户id
        Integer userId = (Integer) MySessionUtil.getAttribute(httpServletRequest, Constants.SESSION_USER_ID);
        MyValidation.checkIntNull(userId);
        articleService.addArticle(addArticleVO, userId);
        return CommonReturnType.create(null);
    }

    /**
     * @author Ck
     * 彻底删除文章
     */
    @DeleteMapping("deleteArticle")
    @ResponseBody
    public CommonReturnType deleteArticle(Integer articleId) throws BusinessException {
        MyValidation.checkIntNull(articleId);
        MyLog.info("Request : /admin/article/deleteArticle\t[ id:" + articleId + " ]");
        //TODO 只有编辑和超级管理员可以彻底删除文章
        articleService.deleteArticle(articleId);
        return CommonReturnType.create(null);
    }

    /**
     * @author Ck
     * 设置文章状态
     */
    @PutMapping("modifyArticleStatus")
    @ResponseBody
    public CommonReturnType modifyArticleStatus(Integer articleId, Byte status) throws BusinessException {
        //TODO 只有编辑和超级管理员可以修改文章状态
        MyValidation.checkIntNull(articleId);
        checkStatus(status);
        MyLog.info("Request : /admin/article/modifyArticleStatus\t[ id:" + articleId + " status:+" + status + " ]");
        articleService.modifyArticleStatus(articleId, status);
        return CommonReturnType.create(null);
    }

    /**
     * @author Ck
     * 修改文章
     */
    @PutMapping("modifyArticle")
    @ResponseBody
    public CommonReturnType modifyArticle(@RequestBody @Valid AddArticleVO addArticleVO, BindingResult result, HttpServletRequest httpServletRequest) throws BusinessException {
        Byte role = (Byte) MySessionUtil.getAttribute(httpServletRequest, Constants.SESSION_USER_ROLE);
        Integer userId = (Integer) MySessionUtil.getAttribute(httpServletRequest, Constants.SESSION_USER_ID);
        MyValidation.checkIntNull(addArticleVO.getArticleId());
        MyValidation.validateObject(EmBusinessErr.ARTICLE_UPDATE_ERROR, result);
        MyLog.info("Request : /admin/article/modifyArticle");
        ArticleDO articleDO = articleService.getArticleById(addArticleVO.getArticleId());
        if (role == Constants.USER_ROLE_SUPER_ADMIN || role == Constants.USER_ROLE_ADMIN || articleDO.getUserId().equals(userId)) {
            articleService.modifyArticle(addArticleVO);
        }
        return CommonReturnType.create(null);
    }

    /**
     * @author Ck
     * 判断状态信息是否合法
     */
    private boolean checkStatus(Byte... status) throws BusinessException {
        for (Byte val : status) {
            if (val == null || !(val == Constants.ARTICLE_STATUS_DRAFT || val == Constants.ARTICLE_STATUS_PUBLISHED || val == Constants.ARTICLE_STATUS_DELETED)) {
                throw new BusinessException(EmBusinessErr.PARAMETER_INVALIDATION_ERROR);
            }
        }
        return true;
    }
}
