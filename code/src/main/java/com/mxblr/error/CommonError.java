package com.mxblr.error;

public interface CommonError {
    public int getErrCode();

    public String getErrMsg();

    public void setErrMsg(String msg);
}
