package com.mxblr.data.dataObject;

import java.util.Date;

public class LoginRecordDO {
    private Integer loginRecordId;

    private Integer userId;

    private Date loginTime;

    private Integer ip;

    public Integer getLoginRecordId() {
        return loginRecordId;
    }

    public void setLoginRecordId(Integer loginRecordId) {
        this.loginRecordId = loginRecordId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Integer getIp() {
        return ip;
    }

    public void setIp(Integer ip) {
        this.ip = ip;
    }
}