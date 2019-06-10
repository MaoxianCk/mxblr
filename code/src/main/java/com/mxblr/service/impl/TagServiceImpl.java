package com.mxblr.service.impl;

import com.mxblr.dao.TagDOMapper;
import com.mxblr.data.vo.TagVO;
import com.mxblr.error.BusinessException;
import com.mxblr.error.EmBusinessErr;
import com.mxblr.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ck
 * #date 2019/05/23 21:54
 */
@Service
public class TagServiceImpl implements TagService {
    private final TagDOMapper tagDOMapper;

    @Autowired
    public TagServiceImpl(TagDOMapper tagDOMapper) {
        this.tagDOMapper = tagDOMapper;
    }

    /**
     * @author Ck
     * 获取所有要显示的分类
     */
    @Override
    public List<TagVO> getTagList() {
        return tagDOMapper.selectTags();
    }

    /**
     * @author Ck
     * 增
     */
    @Override
    public void addTag(String name) throws BusinessException {
        try {
            tagDOMapper.insertTag(name);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(EmBusinessErr.TAG_ADD_ERROR);
        }
    }

    /**
     * @author Ck
     * 删
     */
    @Override
    public void deleteTag(List<Integer> tagId) throws BusinessException {
        try {
            tagDOMapper.batchDeleteTag(tagId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(EmBusinessErr.TAG_DELETE_ERROR);
        }
    }

    /**
     * @author Ck
     * 改
     */
    @Override
    public void modifyTag(Integer tagId, String name) throws BusinessException {
        try {
            tagDOMapper.updateTag(tagId, name);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(EmBusinessErr.TAG_MODIFIED_ERROR);
        }
    }
}
