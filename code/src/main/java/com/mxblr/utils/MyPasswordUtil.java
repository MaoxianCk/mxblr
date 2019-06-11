package com.mxblr.utils;

import com.mxblr.error.BusinessException;

/**
 * #date 2019/05/28 21:35
 */
public final class MyPasswordUtil {
    /**
     * 根据盐和密码获取二次加密后的密码
     */
    public static String getPassword(String psw, String salt) throws BusinessException {
        psw = MD5Util.getMD5(psw);
        //将盐拆分成两个字符串加在原md5密码两侧再加密
        //分割点为12
        final int spitLoc = 12;
        String left = salt.substring(0, spitLoc);
        String right = salt.substring(spitLoc);

        String code = MD5Util.getMD5(left + psw + right);
        return code;
    }
}
