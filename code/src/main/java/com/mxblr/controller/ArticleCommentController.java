package com.mxblr.controller;

import com.mxblr.data.vo.ArticleCommentViewVO;
import com.mxblr.error.BusinessException;
import com.mxblr.response.CommonReturnType;
import com.mxblr.service.ArticleCommentService;
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

    @GetMapping("getArticleCommentByArticleId")
    @ResponseBody
    public CommonReturnType getArticleCommentByArticleId(Integer id) throws BusinessException {
        MyValidation.checkIntNull(id);
        List<ArticleCommentViewVO> list = articleCommentService.getCommentsByArticleId(id);
        return CommonReturnType.create(list);
    }

}
