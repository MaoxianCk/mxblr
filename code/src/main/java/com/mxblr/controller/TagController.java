package com.mxblr.controller;

import com.mxblr.data.vo.TagVO;
import com.mxblr.response.CommonReturnType;
import com.mxblr.service.TagService;
import com.mxblr.utils.MyLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Ck
 * #date 2019/05/23 22:11
 */
@RequestMapping("/user/tag")
@RestController
public class TagController extends BaseController {
    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    /**
     * @author Ck
     * 获取分类列表
     */
    @GetMapping("/getTagList")
    @ResponseBody
    public CommonReturnType getTagList() {
        MyLog.info("Request : /user/tag/getTagList");
        List<TagVO> list;
        list = tagService.getTagList();
        return CommonReturnType.create(list);
    }
}
