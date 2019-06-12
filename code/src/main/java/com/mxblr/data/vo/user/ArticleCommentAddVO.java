package com.mxblr.data.vo.user;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 2019/06/09
 */
public class ArticleCommentAddVO {
    private Integer articleId;

    @NotNull(message = "昵称不能为空")
    @NotEmpty(message = "昵称不能为空")
    private String name;

    private String email;

    private String site;

    @NotNull(message = "评论内容不能为空")
    @NotEmpty(message = "评论内容不能为空")
    private String content;

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
