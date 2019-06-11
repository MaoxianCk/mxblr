package com.mxblr.controller.admin;

import com.mxblr.controller.BaseController;
import com.mxblr.data.vo.admin.AdminArticleCommentVO;
import com.mxblr.error.BusinessException;
import com.mxblr.response.CommonReturnType;
import com.mxblr.service.ArticleCommentService;
import com.mxblr.utils.MyLog;
import com.mxblr.validator.MyValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/admin/comment")
@RestController
public class AdminCommentController extends BaseController {
    private final ArticleCommentService articleCommentService;

    @Autowired
    public AdminCommentController(ArticleCommentService articleCommentService) {
        this.articleCommentService = articleCommentService;
    }

    /**
     * 获取评论列表带标题
     */
    @GetMapping("getArticleCommentWithArticle")
    @ResponseBody
    public CommonReturnType getArticleCommentWithArticle() {
        MyLog.info("Request : /admin/comment/getArticleCommentWithArticle");
        List<AdminArticleCommentVO> list = articleCommentService.getCommentsWithArticle();
        return CommonReturnType.create(list);
    }

    /**
     * 通过改变status审核评论和逻辑删除评论
     */
    @PostMapping("modifyCommentStatus")
    @ResponseBody
    public CommonReturnType modifyCommentStatus(@RequestParam(name = "commentId") Integer commentId,
                                                @RequestParam(name = "status") Byte status) throws BusinessException {
        MyValidation.checkIntNull(commentId);
        MyLog.info("Request : /admin/comment/modifyCommentStatus");
        articleCommentService.modifyCommentStatus(commentId, status);
        return CommonReturnType.create(null);
    }
}





