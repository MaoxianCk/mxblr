package com.mxblr.service;

import com.mxblr.data.vo.admin.AdminImageVO;
import com.mxblr.error.BusinessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 2019/06/07
 */
@Service
public interface ImageService {
    /**
     * 获取图片列表
     */
    List<AdminImageVO> getImageList();

    /**
     * 上传图片
     */
    void addImage(AdminImageVO adminImageVO) throws BusinessException;

    /**
     * 删除图片
     */
    void deleteImage(List<Integer> id) throws BusinessException;
}
