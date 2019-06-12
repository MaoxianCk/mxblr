package com.mxblr.data.vo.user;

/**
 * #date 2019/05/24 14:40
 * 文章内容，及对应的上下文信息
 */
public class ArticleVO {
    //文章头
    private ArticleInfoVO articleInfo;

    //文本内容
    private String content;

    //上一篇文章的id
    private String lastArticleId;
    //上一篇文章的标题
    private String lastArticleTitle;

    //下一篇文章的id
    private String nextArticleId;
    //下一篇文章的标题
    private String nextArticleTitle;

    public ArticleInfoVO getArticleInfo() {
        return articleInfo;
    }

    public void setArticleInfo(ArticleInfoVO articleInfo) {
        this.articleInfo = articleInfo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLastArticleId() {
        return lastArticleId;
    }

    public void setLastArticleId(String lastArticleId) {
        this.lastArticleId = lastArticleId;
    }

    public String getLastArticleTitle() {
        return lastArticleTitle;
    }

    public void setLastArticleTitle(String lastArticleTitle) {
        this.lastArticleTitle = lastArticleTitle;
    }

    public String getNextArticleId() {
        return nextArticleId;
    }

    public void setNextArticleId(String nextArticleId) {
        this.nextArticleId = nextArticleId;
    }

    public String getNextArticleTitle() {
        return nextArticleTitle;
    }

    public void setNextArticleTitle(String nextArticleTitle) {
        this.nextArticleTitle = nextArticleTitle;
    }

    @Override
    public String toString() {
        return "ArticleVO{" +
                "articleInfo=" + articleInfo +
                ", content='" + content + '\'' +
                ", lastArticleId='" + lastArticleId + '\'' +
                ", lastArticleTitle='" + lastArticleTitle + '\'' +
                ", nextArticleId='" + nextArticleId + '\'' +
                ", nextArticleTitle='" + nextArticleTitle + '\'' +
                '}';
    }
}
