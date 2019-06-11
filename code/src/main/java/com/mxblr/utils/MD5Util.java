package com.mxblr.utils;

import com.mxblr.error.BusinessException;
import com.mxblr.error.EmBusinessErr;

import java.math.BigInteger;
import java.security.MessageDigest;

public final class MD5Util {
    public static String getMD5(String str) throws BusinessException {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            String md5 = new BigInteger(1, md.digest()).toString(16);
            //BigInteger会把0省略掉，需补全至32位
            return fillMD5(md5);
        } catch (Exception e) {
            MyLog.error("md5加密失败\n" + MyExceptionUtil.getErrorMsg(e));
            throw new BusinessException(EmBusinessErr.PARAMETER_INVALIDATION_ERROR, "md5加密失败");
        }
    }

    /**
     * 补齐32位
     */
    private static String fillMD5(String md5) {
        return md5.length() == 32 ? md5 : fillMD5("0" + md5);
    }

    public static void main(String[] args) throws Exception {
        String str = "456as4fsda869-*23/4+23q-*4/asgdfg-*435";// b5c465303ac45860973eee102905999c
        System.out.println("md5:" + getMD5(str));
        System.out.println("length:" + getMD5(str).length());
    }
}
