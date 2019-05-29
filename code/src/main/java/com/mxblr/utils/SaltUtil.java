package com.mxblr.utils;

import java.util.Random;

/**
 * @author Ck
 * #date 2019/05/28 21:06
 */
public final class SaltUtil {
    /**
     * 获取制定长度32的密码，包含大小写字母、数字和特殊字符，四种的任意三种
     */
    public static String getRandomSalt32() {
        final int len = 32;
        String result;
        while (true) {
            result = makeRandomSalt(len);
            if (result.matches(".*[a-z]+.*") && result.matches(".*[A-Z]+.*") &&
                    result.matches(".*\\d+.*") && result.matches(".*[~;<@#:>%^]+.*")) {
                return result;
            }
        }
    }

    /**
     * 随机密码生成
     */
    private static String makeRandomSalt(int len) {
        final char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890~;<@#:>%^".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random r = new Random();
        for (int x = 0; x < len; ++x) {
            sb.append(chars[r.nextInt(chars.length)]);
        }
        return sb.toString();
    }
}
