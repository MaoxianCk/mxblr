package com.mxblr.service;

import com.mxblr.data.vo.TagVO;
import com.mxblr.error.BusinessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ck
 * #date 2019/05/23 21:52
 */
@Service
public interface TagService {

    /**
     * @author Ck
     * 获取所有要显示的分类
     */
    List<TagVO> getTagList();

    /**
     * @author Ck
     * 增
     */
    void addTag(String name) throws BusinessException;

    /**
     * @author Ck
     * 删
     */
    void deleteTag(List<Integer> tagId) throws BusinessException;

    /**
     * @author Ck
     * 改
     */
    void modifyTag(Integer tagId, String name) throws BusinessException;
}
