package com.mxblr.controller.user;

import com.mxblr.controller.BaseController;
import com.mxblr.data.vo.user.ArticleCommentAddVO;
import com.mxblr.data.vo.user.ArticleCommentVO;
import com.mxblr.error.BusinessException;
import com.mxblr.error.EmBusinessErr;
import com.mxblr.response.CommonReturnType;
import com.mxblr.service.ArticleCommentService;
import com.mxblr.utils.MyLog;
import com.mxblr.validator.MyValidation;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * #date 2019/05/24 21:13
 */
@RequestMapping("/user/articleComment/")
@RestController
public class ArticleCommentController extends BaseController {
    private final ArticleCommentService articleCommentService;

    public ArticleCommentController(ArticleCommentService articleCommentService) {
        this.articleCommentService = articleCommentService;
    }

    /**
     * 根据文章id获取评论列表
     */
    @GetMapping("getArticleCommentByArticleId")
    @ResponseBody
    public CommonReturnType getArticleCommentByArticleId(Integer articleId) throws BusinessException {
        MyValidation.checkIntNull(articleId);
        MyLog.info("Request : /user/articleComment/getArticleCommentByArticleId\t[ ArticleId: " + articleId + " ]");
        List<ArticleCommentVO> list = articleCommentService.getCommentsByArticleId(articleId);
        return CommonReturnType.create(list);
    }

    /**
     * 增加评论
     */
    @PostMapping("addArticleComment")
    @ResponseBody
    public CommonReturnType addArticleComment(@RequestBody @Valid ArticleCommentAddVO articleCommentAddVO,
                                              BindingResult result,
                                              HttpServletRequest request) throws BusinessException {
        MyValidation.validateObject(EmBusinessErr.COMMENT_ADD_ERROR, result);
        MyLog.info("Request : /user/articleComment/addArticleComment\t[ ArticleCommentAddVO: " + articleCommentAddVO + " ]");
        articleCommentService.addComment(articleCommentAddVO, request);
        return CommonReturnType.create(null);
    }
}
