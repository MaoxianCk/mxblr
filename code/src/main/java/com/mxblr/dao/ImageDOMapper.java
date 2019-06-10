package com.mxblr.dao;

import com.mxblr.data.dataObject.ImageDO;
import com.mxblr.data.vo.ImageVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ImageDOMapper {
    int deleteByPrimaryKey(Integer imageId);

    int insert(ImageDO record);

    int insertSelective(ImageDO record);

    ImageDO selectByPrimaryKey(Integer imageId);

    int updateByPrimaryKeySelective(ImageDO record);

    int updateByPrimaryKeyWithBLOBs(ImageDO record);

    int updateByPrimaryKey(ImageDO record);

    /**
     * @author Kny
     * 查询图片列表
     */
    @Select("select * from image")
    List<ImageVO> getImageList();

    /**
     * @author Kny
     * 批量删除图片
     */
    void batchDeleteImage(List<Integer> id);
}