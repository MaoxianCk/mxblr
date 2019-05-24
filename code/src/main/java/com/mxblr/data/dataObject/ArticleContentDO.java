package com.mxblr.data.dataObject;

public class ArticleContentDO {
    private Integer articleContentId;

    private Integer articleId;

    private String content;

    public Integer getArticleContentId() {
        return articleContentId;
    }

    public void setArticleContentId(Integer articleContentId) {
        this.articleContentId = articleContentId;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}