package com.mxblr.error;

public enum EmBusinessErr implements CommonError {
    PARAMETER_INVALIDATION_ERROR(101, "参数不合法"),
    UNKNOWN_ERROR(102, "未知错误"),
    PERMISSION_DENIED(103, "无权限"),
    NOT_LOGIN_USER(104, "未登录"),

    //用户相关错误
    USER_ERROR(200, "账号异常"),
    USER_NOT_EXIST(201, "用户不存在"),
    USER_LOGIN_FAILED(202, "用户名或密码不正确"),
    USER_REGISTER_ERR_ACCOUNT_REPEAT(203, "账号已存在"),
    USER_LOGIN_TIMES_ERROR(204, "登录次数过多"),
    USER_INFO_MODIFY_ERROR(205, "用户信息修改失败"),
    USER_PASSWORD_MODIFY_ERROR(206, "修改用户密码失败"),
    USER_ROLE_MODIFY_ERROR(207, "修改用户权限失败"),
    USER_DELETE_ERROR(208, "删除用户失败"),

    //文章相关错误
    ARTICLE_NOT_EXISTS(301, "文章不存在"),
    ARTICLE_ADD_ERROR(302, "创建文章失败"),
    ARTICLE_UPDATE_ERROR(303, "修改文章失败"),
    ARTICLE_DELETE_ERROR(304, "删除文章失败"),

    //分类相关错误
    TAG_ADD_ERROR(401, "分类添加失败"),
    TAG_MODIFIED_ERROR(402, "分类修改失败"),
    TAG_DELETE_ERROR(403, "分类删除失败"),

    //图片相关错误
    IMAGE_ADD_ERROR(501, "图片上传失败"),
    IMAGE_DELETE_ERROR(502, "图片删除失败"),

    //评论相关错误
    COMMENT_DELETE_ERROR(601, "评论删除失败"),
    COMMENT_ADD_ERROR(602, "添加评论失败"),
    COMMENT_STATUS_MODIFY_ERROR(603, "评论状态修改失败"),
    ;

    private int errCode;
    private String msg;

    EmBusinessErr(int errCode, String msg) {
        this.errCode = errCode;
        this.msg = msg;
    }

    @Override
    public int getErrCode() {
        return this.errCode;
    }

    @Override
    public String getErrMsg() {
        return this.msg;
    }

    @Override
    public void setErrMsg(String msg) {
        this.msg = msg;
    }
}
