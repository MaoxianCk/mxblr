package com.mxblr.utils;

public final class Constants {
    public static final String SESSION_USER_ID = "userId";
    public static final String SESSION_USER_ROLE = "userRole";

    //超级管理员
    public static final byte USER_ROLE_SUPER_ADMIN = 0;
    //编辑（除了不能修改设置）
    public static final byte USER_ROLE_ADMIN = 1;
    //写手（只能写和改自己的文， 不能独立发布）
    public static final byte USER_ROLE_WRITTER = 2;


    //文章状态为草稿
    public static final byte ARTICLE_STATUS_DRAFT = 0;
    //文章状态为已发表
    public static final byte ARTICLE_STATUS_PUBLISHED = 1;
    //文章状态为逻辑删除
    public static final byte ARTICLE_STATUS_DELETED = 2;
}
