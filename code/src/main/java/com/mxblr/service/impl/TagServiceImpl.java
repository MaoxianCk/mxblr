package com.mxblr.service.impl;

import com.mxblr.dao.TagDOMapper;
import com.mxblr.data.vo.TagVO;
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
}
