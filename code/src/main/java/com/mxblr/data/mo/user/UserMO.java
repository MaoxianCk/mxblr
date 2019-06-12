package com.mxblr.data.mo.user;

/**
 * #date 2019/05/28 21:55
 */
public class UserMO {
    private Integer userId;

    private String account;

    private String password;

    private String salt;

    public UserMO() {
    }

    public UserMO(String account, String password, String salt) {
        this.account = account;
        this.password = password;
        this.salt = salt;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }
}
