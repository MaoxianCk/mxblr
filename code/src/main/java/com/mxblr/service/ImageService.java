package com.mxblr.service;

import com.mxblr.data.dataObject.ImageDO;
import com.mxblr.data.vo.ImageVO;
import com.mxblr.error.BusinessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Kny
 * 2019/06/07
 */
@Service
public interface ImageService {
    /**
     * @author Kny
     * 获取图片列表
     */
    List<ImageVO> getImageList();

    /**
     * @author Kny
     * 上传图片
     */
    void addImage(ImageVO imageVO) throws BusinessException;

    /**
     * @author Kny
     * 删除图片
     */
    void deleteImage(List<Integer> id) throws BusinessException;
}
