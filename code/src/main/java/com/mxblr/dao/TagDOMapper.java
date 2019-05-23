package com.mxblr.dao;

import com.mxblr.data.dataObject.TagDO;
import com.mxblr.data.vo.TagVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface TagDOMapper {
    int deleteByPrimaryKey(Integer tagId);

    int insert(TagDO record);

    int insertSelective(TagDO record);

    TagDO selectByPrimaryKey(Integer tagId);

    int updateByPrimaryKeySelective(TagDO record);

    int updateByPrimaryKey(TagDO record);

    @Select("select tag_id,name from tag where view = true")
    List<TagVO> selectTags();
}