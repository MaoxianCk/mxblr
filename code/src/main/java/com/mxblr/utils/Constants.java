package com.mxblr.utils;

public final class Constants {
    //是否测试，是则自动填充session的必要信息,仅开发中测试
    public static final boolean TEST = true;

    public static final String SESSION_USER_ID = "userId";
    public static final String SESSION_USER_ROLE = "userRole";
    public static final String SESSION_USER_LOGIN_CNT = "userLoginCnt";

    public static final String MAIL_AUTHOR = "785315332@qq.com";

    //超级管理员
    public static final byte USER_ROLE_SUPER_ADMIN = 0;
    //编辑（除了不能修改设置）
    public static final byte USER_ROLE_ADMIN = 1;
    //写手（只能写和改自己的文， 不能独立发布）
    public static final byte USER_ROLE_WRITER = 2;


    //文章状态为草稿
    public static final byte ARTICLE_STATUS_DRAFT = 0;
    //文章状态为已发表
    public static final byte ARTICLE_STATUS_PUBLISHED = 1;
    //文章状态为逻辑删除
    public static final byte ARTICLE_STATUS_DELETED = 2;
}
