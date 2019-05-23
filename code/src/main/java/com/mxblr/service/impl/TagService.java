package com.mxblr.service.impl;

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
}
