package com.mxblr.controller.admin;

import com.mxblr.controller.BaseController;
import com.mxblr.data.dataObject.ArticleDO;
import com.mxblr.data.vo.admin.AdminArticleAddVO;
import com.mxblr.data.vo.admin.AdminArticleInfoVO;
import com.mxblr.data.vo.admin.AdminArticleModifyVO;
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
import java.util.List;

/**
 * #date 2019/05/24 22:39
 */
@RequestMapping("/admin/article/")
@RestController
public class AdminArticleController extends BaseController {
    private final ArticleService articleService;

    @Autowired
    public AdminArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    /**
     * 获取后台的文章列表
     */
    @GetMapping("getAdminArticleInfoList")
    @ResponseBody
    public CommonReturnType getAdminArticleInfoList() throws BusinessException {
        MyLog.info("Request : /admin/article/getAdminArticleInfoList");
        List<AdminArticleInfoVO> list = articleService.getAdminArticleInfoList();
        return CommonReturnType.create(list);
    }

    /**
     * 增加文章
     */
    @PostMapping("addArticle")
    @ResponseBody
    public CommonReturnType addArticle(@RequestBody @Valid AdminArticleAddVO adminArticleAddVO,
                                       BindingResult result,
                                       HttpServletRequest httpServletRequest) throws BusinessException {
        MyValidation.validateObject(EmBusinessErr.ARTICLE_ADD_ERROR, result);
        MyLog.info("Request : /admin/article/addArticle");
        //获取用户id
        Integer userId = (Integer) MySessionUtil.getAttribute(httpServletRequest, Constants.SESSION_USER_ID);
        MyValidation.checkIntNull(userId);
        articleService.addArticle(adminArticleAddVO, userId);
        return CommonReturnType.create(null);
    }

    /**
     * 彻底删除文章
     */
    @PostMapping("deleteArticle")
    @ResponseBody
    public CommonReturnType deleteArticle(@RequestParam(name = "articleId") Integer articleId,
                                          HttpServletRequest httpServletRequest) throws BusinessException {
        //只有编辑和超级管理员可以彻底删除文章
        Byte userRole = (Byte) MySessionUtil.getAttribute(httpServletRequest, Constants.SESSION_USER_ROLE);
        MyValidation.checkPermission(userRole, Constants.USER_ROLE_SUPER_ADMIN, Constants.USER_ROLE_ADMIN);
        MyValidation.checkIntNull(articleId);
        MyLog.info("Request : /admin/article/deleteArticle\t[ articleId: " + articleId + " ]");
        articleService.deleteArticle(articleId);
        return CommonReturnType.create(null);
    }

    /**
     * 设置文章状态
     */
    @PostMapping("modifyArticleStatus")
    @ResponseBody
    public CommonReturnType modifyArticleStatus(@RequestParam(name = "articleId") Integer articleId,
                                                @RequestParam(name = "status") Byte status,
                                                HttpServletRequest httpServletRequest) throws BusinessException {
        //只有编辑和超级管理员可以彻底设置文章状态
        Byte userRole = (Byte) MySessionUtil.getAttribute(httpServletRequest, Constants.SESSION_USER_ROLE);
        MyValidation.checkPermission(userRole, Constants.USER_ROLE_SUPER_ADMIN, Constants.USER_ROLE_ADMIN);
        MyValidation.checkIntNull(articleId);
        checkStatus(status);
        MyLog.info("Request : /admin/article/modifyArticleStatus\t[ articleId: " + articleId + " status: " + status + " ]");
        articleService.modifyArticleStatus(articleId, status);
        return CommonReturnType.create(null);
    }

    /**
     * 修改文章
     */
    @PostMapping("modifyArticle")
    @ResponseBody
    public CommonReturnType modifyArticle(@RequestBody @Valid AdminArticleModifyVO adminArticleModifyVO,
                                          BindingResult result,
                                          HttpServletRequest httpServletRequest) throws BusinessException {
        Byte role = (Byte) MySessionUtil.getAttribute(httpServletRequest, Constants.SESSION_USER_ROLE);
        Integer userId = (Integer) MySessionUtil.getAttribute(httpServletRequest, Constants.SESSION_USER_ID);
        MyValidation.checkIntNull(adminArticleModifyVO.getArticleId());
        MyValidation.validateObject(EmBusinessErr.ARTICLE_UPDATE_ERROR, result);
        //TODO 普通写手只能修改自己的文章
        MyLog.info("Request : /admin/article/modifyArticle");
        ArticleDO articleDO = articleService.getArticleById(adminArticleModifyVO.getArticleId());
        if (role == Constants.USER_ROLE_SUPER_ADMIN || role == Constants.USER_ROLE_ADMIN || articleDO.getUserId().equals(userId)) {
            articleService.modifyArticle(adminArticleModifyVO);
        }
        return CommonReturnType.create(null);
    }

    /**
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
