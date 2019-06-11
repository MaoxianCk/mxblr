package com.mxblr.controller.user;

import com.mxblr.controller.BaseController;
import com.mxblr.data.vo.admin.AdminTagVO;
import com.mxblr.response.CommonReturnType;
import com.mxblr.service.TagService;
import com.mxblr.utils.MyLog;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * #date 2019/05/23 22:11
 */
@RequestMapping("/user/tag/")
@RestController
public class TagController extends BaseController {
    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    /**
     * 获取分类列表
     */
    @GetMapping("getTagList")
    @ResponseBody
    public CommonReturnType getTagList() {
        MyLog.info("Request : /user/tag/getTagList");
        List<AdminTagVO> list = tagService.getTagList();
        return CommonReturnType.create(list);
    }
}
