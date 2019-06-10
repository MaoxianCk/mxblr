package com.mxblr.dao;

import com.mxblr.data.dataObject.TagDO;
import com.mxblr.data.vo.TagVO;
import org.apache.ibatis.annotations.*;
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

    @Insert("insert into tag (name,view) values(#{name},1)")
    void insertTag(String name);

    @Delete("delete from tag where tag_id = #{id}")
    void deleteTag(Integer id);

    @Update("update tag set name = #{name} where tag_id = #{id}")
    void updateTag(Integer id, String name);

    /**
     * @author Kny
     * 批量删除分类标签
     */
    void batchDeleteTag(List<Integer> idList);
}