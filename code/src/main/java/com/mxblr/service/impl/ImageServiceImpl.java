package com.mxblr.service.impl;

import com.mxblr.dao.ImageDOMapper;
import com.mxblr.data.dataObject.ImageDO;
import com.mxblr.data.vo.admin.AdminImageVO;
import com.mxblr.error.BusinessException;
import com.mxblr.error.EmBusinessErr;
import com.mxblr.service.ImageService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 2019/06/07
 */
@Service
public class ImageServiceImpl implements ImageService {
    private final ImageDOMapper imageDOMapper;

    @Autowired
    public ImageServiceImpl(ImageDOMapper imageDOMapper) {
        this.imageDOMapper = imageDOMapper;
    }

    /**
     * 获取图片列表
     */
    @Override
    public List<AdminImageVO> getImageList() {
        return imageDOMapper.getImageList();
    }

    /**
     * 上传图片
     */
    @Override
    public void addImage(AdminImageVO adminImageVO) throws BusinessException {
        try {
            ImageDO imageDO = new ImageDO();
            BeanUtils.copyProperties(adminImageVO, imageDO);
            imageDOMapper.insertSelective(imageDO);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(EmBusinessErr.IMAGE_ADD_ERROR);
        }
    }

    /**
     * 删除图片
     */
    @Override
    public void deleteImage(List<Integer> id) throws BusinessException {
        try {
            imageDOMapper.batchDeleteImage(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(EmBusinessErr.IMAGE_DELETE_ERROR);
        }
    }
}
