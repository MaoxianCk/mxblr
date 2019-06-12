package com.mxblr.data.vo.admin;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * #date 2019/05/24 22:46
 */
public class AdminArticleAddVO {
    private Integer articleId;

    @NotNull(message = "标题不能为空")
    @NotEmpty(message = "标题不能为空")
    private String title;

    private Integer imageId;

    private String summary;

    private Integer userId;

    @NotNull(message = "必须选择一个标签（分类）")
    private Integer tagId;

    private Date createdTime;

    private Byte createdMonth;

    private Date modifiedTime;

    private Boolean status;

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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Byte getCreatedMonth() {
        return createdMonth;
    }

    public void setCreatedMonth(Byte createdMonth) {
        this.createdMonth = createdMonth;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
