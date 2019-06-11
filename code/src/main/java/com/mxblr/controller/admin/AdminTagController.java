package com.mxblr.controller.admin;

import com.mxblr.controller.BaseController;
import com.mxblr.data.vo.admin.AdminTagVO;
import com.mxblr.error.BusinessException;
import com.mxblr.response.CommonReturnType;
import com.mxblr.service.TagService;
import com.mxblr.utils.MyLog;
import com.mxblr.validator.MyValidation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * #date 2019/06/04 15:47
 */
@RequestMapping("/admin/tag/")
@RestController
public class AdminTagController extends BaseController {
    private final TagService tagService;

    public AdminTagController(TagService tagService) {
        this.tagService = tagService;
    }

    /**
     * 获取分类列表
     */
    @GetMapping("getTagList")
    @ResponseBody
    public CommonReturnType getTagList() {
        MyLog.info("Request : /admin/tag/getTagList");
        List<AdminTagVO> list;
        list = tagService.getTagList();
        return CommonReturnType.create(list);
    }

    /**
     * 添加分类标签
     */
    @PostMapping("addTag")
    @ResponseBody
    public CommonReturnType addTag(@RequestParam(name = "name") String name) throws BusinessException {
        MyValidation.checkStrNull(name);
        MyLog.info("Request : /admin/tag/addTag\t[ name: " + name + " ]");
        tagService.addTag(name);
        return CommonReturnType.create(null);
    }

    /**
     * 删除分类标签
     */
    @PostMapping("deleteTag")
    @ResponseBody
    public CommonReturnType deleteTag(@RequestBody List<Integer> tagId) throws BusinessException {
        //MyValidation.
        MyLog.info("Request : /admin/tag/deleteTag\t[ tagId: " + tagId + " ]");
        tagService.deleteTag(tagId);
        return CommonReturnType.create(null);
    }

    /**
     * 修改分类标签
     */
    @PostMapping("modifyTag")
    @ResponseBody
    public CommonReturnType modifyTag(@RequestParam(name = "tagId") Integer tagId,
                                      @RequestParam(name = "name") String name) throws BusinessException {
        MyValidation.checkIntNull(tagId);
        MyValidation.checkStrNull(name);
        MyLog.info("Request : /admin/tag/modifyTag\t[ tagId: " + tagId + ", name: " + name + " ]");
        tagService.modifyTag(tagId, name);
        return CommonReturnType.create(null);
    }
}
