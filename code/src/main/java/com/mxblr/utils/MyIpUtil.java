package com.mxblr.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Ck
 * #date 2019/05/28 23:00
 */
public class MyIpUtil {
    public static Integer getIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if (index != -1) {
                return ipToLong(ip.substring(0, index));
            } else {
                return ipToLong(ip);
            }
        }
        ip = request.getHeader("X-Real-IP");
        if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            return ipToLong(ip);
        }
        return ipToLong(request.getRemoteAddr());
    }

    public static Integer ipToLong(String strIp) {
        try {
            int[] ip = new int[4];
            //先找到IP地址字符串中.的位置
            int position1 = strIp.indexOf(".");
            int position2 = strIp.indexOf(".", position1 + 1);
            int position3 = strIp.indexOf(".", position2 + 1);
            //将每个.之间的字符串转换成整型
            ip[0] = Integer.parseInt(strIp.substring(0, position1));
            ip[1] = Integer.parseInt(strIp.substring(position1 + 1, position2));
            ip[2] = Integer.parseInt(strIp.substring(position2 + 1, position3));
            ip[3] = Integer.parseInt(strIp.substring(position3 + 1));
            return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];
        } catch (Exception e) {
            return null;
        }
    }
}
