package com.mxblr.data.vo.admin;

import java.util.Date;

/**
 * @author Kny
 * 2019/06/09
 */
public class AdminArticleCommentVO {
    private Integer commentId;

    private Integer articleId;

    private String name;

    private String email;

    private String site;

    private Byte status;

    private String ip;

    private Date createdTime;

    private String content;

    private String title;

    public AdminArticleCommentVO(Integer commentId, Integer articleId, String name, String email, String site, Byte status, Date createdTime, String content, String title) {
        this.commentId = commentId;
        this.articleId = articleId;
        this.name = name;
        this.email = email;
        this.site = site;
        this.status = status;
        this.createdTime = createdTime;
        this.content = content;
        this.title = title;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

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

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
