package com.mxblr.error;

public enum EmBusinessErr implements CommonError {
    PARAMETER_INVALIDATION_ERROR(101, "参数不合法"),
    UNKNOWN_ERROR(102, "未知错误"),
    PERMISSION_DENIED(103,"无权限"),
    NOT_LOGIN_USER(104,"未登录"),

    //用户相关错误
    USER_NOT_EXIST(201, "用户不存在"),
    USER_LOGIN_FAILED(202, "用户名或密码不正确"),
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
