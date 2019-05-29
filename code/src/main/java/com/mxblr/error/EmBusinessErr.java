package com.mxblr.error;

public enum EmBusinessErr implements CommonError {
    PARAMETER_INVALIDATION_ERROR(101, "参数不合法"),
    UNKNOWN_ERROR(102, "未知错误"),
    PERMISSION_DENIED(103,"无权限"),
    NOT_LOGIN_USER(104,"未登录"),

    //用户相关错误
    USER_ERROR(200,"账号异常"),
    USER_NOT_EXIST(201, "用户不存在"),
    USER_LOGIN_FAILED(202, "用户名或密码不正确"),
    USER_REGISTER_ERR_ACCOUNT_REPEAT(203,"账号已存在"),
    User_LOGIN_TIMES_ERROR(204,"登录次数过多"),

    //文章相关错误
    ARTICLE_NOT_EXISTS(301,"文章不存在"),
    ARTICLE_ADD_ERROR(302,"创建文章失败"),
    ARTICLE_UPDATE_ERROR(303,"修改文章失败"),
    ARTICLE_DELETE_ERROR(304,"删除文章失败"),


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
