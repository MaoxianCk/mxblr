package com.mxblr.service;

import com.mxblr.data.vo.admin.AdminTagVO;
import com.mxblr.error.BusinessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * #date 2019/05/23 21:52
 */
@Service
public interface TagService {

    /**
     * 获取所有要显示的分类
     */
    List<AdminTagVO> getTagList();

    /**
     * 增
     */
    void addTag(String name) throws BusinessException;

    /**
     * 删
     */
    void deleteTag(List<Integer> tagId) throws BusinessException;

    /**
     * 改
     */
    void modifyTag(Integer tagId, String name) throws BusinessException;
}
