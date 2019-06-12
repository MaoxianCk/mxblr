package com.mxblr.data.vo.admin;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 2019/06/10
 */
public class AdminArticleModifyVO {
    private Integer articleId;

    @NotNull(message = "标题不能为空")
    @NotEmpty(message = "标题不能为空")
    private String title;

    private Integer imageId;

    @NotNull(message = "必须选择一个标签（分类）")
    private Integer tagId;

    @NotNull(message = "文本不能为空")
    @NotEmpty(message = "文本不能为空")
    private String content;

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
