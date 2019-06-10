package com.mxblr.controller.admin;

import com.mxblr.controller.user.BaseController;
import com.mxblr.data.vo.ImageVO;
import com.mxblr.error.BusinessException;
import com.mxblr.error.EmBusinessErr;
import com.mxblr.response.CommonReturnType;
import com.mxblr.service.ImageService;
import com.mxblr.utils.MyLog;
import com.mxblr.validator.MyValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Kny
 * 2019/06/07
 */
@RequestMapping("/admin/image/")
@RestController
public class AdminImageController extends BaseController {
    private final ImageService imageService;

    @Autowired
    public AdminImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    /**
     * @author Kny
     * 获取图片列表
     */
    @GetMapping("getImageList")
    @ResponseBody
    public CommonReturnType getImageList() {
        MyLog.info("Request : /admin/image/getImageList");
        List<ImageVO> list = imageService.getImageList();
        return CommonReturnType.create(list);
    }

    /**
     * @author Kny
     * 图片上传
     */
    @PostMapping("addImage")
    @ResponseBody
    public CommonReturnType addImage(@RequestBody @Valid ImageVO imageVO,
                                     BindingResult result) throws BusinessException {
        MyValidation.validateObject(EmBusinessErr.IMAGE_ADD_ERROR, result);
        MyLog.info("Request : /admin/image/addImage\t[ ImageVO: " + imageVO + " ]");
        imageService.addImage(imageVO);
        return CommonReturnType.create(null);
    }

    /**
     * @author Kny
     * 删除图片
     */
    @PostMapping("deleteImage")
    @ResponseBody
    public CommonReturnType deleteImage(@RequestBody List<Integer> id) throws BusinessException {
        MyLog.info("Request : /admin/image/deleteImage\t[ Id: " + id + " ]");
        imageService.deleteImage(id);
        return CommonReturnType.create(null);
    }
}
