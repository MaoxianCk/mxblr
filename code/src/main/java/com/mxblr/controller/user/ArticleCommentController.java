package com.mxblr.controller.user;

import com.mxblr.data.vo.ArticleCommentAdminVO;
import com.mxblr.data.vo.ArticleCommentViewVO;
import com.mxblr.error.BusinessException;
import com.mxblr.response.CommonReturnType;
import com.mxblr.service.ArticleCommentService;
import com.mxblr.utils.MyLog;
import com.mxblr.validator.MyValidation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Ck
 * #date 2019/05/24 21:13
 */
@RequestMapping("/user/articleComment")
@RestController
public class ArticleCommentController {
    private final ArticleCommentService articleCommentService;

    public ArticleCommentController(ArticleCommentService articleCommentService) {
        this.articleCommentService = articleCommentService;
    }

    /**
     * @author Ck
     * 根据文章id获取评论列表
     */
    @GetMapping("getArticleCommentByArticleId")
    @ResponseBody
    public CommonReturnType getArticleCommentByArticleId(Integer id) throws BusinessException {
        MyValidation.checkIntNull(id);
        MyLog.info("Request : /user/article/getArticleCommentByArticleId\t[ id: " + id + " ]");
        List<ArticleCommentViewVO> list = articleCommentService.getCommentsByArticleId(id);
        return CommonReturnType.create(list);
    }

    /**
     * @author Ck
     * 获取评论列表带标题
     */
    @GetMapping("getArticleCommentWithArticle")
    @ResponseBody
    public CommonReturnType getArticleCommentWithArticle(){
        MyLog.info("Request : /user/article/getArticleCommentWithArticle");
        List<ArticleCommentAdminVO> list = articleCommentService.getCommentsWithArticle();
        return CommonReturnType.create(list);
    }


}
