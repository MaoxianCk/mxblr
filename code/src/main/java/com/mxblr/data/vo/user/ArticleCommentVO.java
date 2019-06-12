package com.mxblr.data.vo.user;

/**
 * #date 2019/05/24 21:09
 * 被允许的评论信息
 */
public class ArticleCommentVO {
    private String name;

    private String email;

    private String site;

    private String content;

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
