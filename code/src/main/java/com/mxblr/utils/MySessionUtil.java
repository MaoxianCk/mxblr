package com.mxblr.utils;

import com.mxblr.error.BusinessException;
import com.mxblr.error.EmBusinessErr;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Session相关类
 */
public final class MySessionUtil {


    /**
     * 获取session中的属性值
     */
    public static Object getAttribute(HttpServletRequest request, String key) throws BusinessException {
        HttpSession session = request.getSession();
        if (session.getAttribute(key) == null) {
            MyLog.error("session没有\"" + key + "\"属性");
            throw new BusinessException(EmBusinessErr.PARAMETER_INVALIDATION_ERROR, "session没有\"" + key + "\"属性");
        }
        return session.getAttribute(key);
    }

    /**
     * 在session中存入值
     */
    public static void setAttribute(HttpServletRequest request, String key, Object value) {
        HttpSession session = request.getSession();
        session.setAttribute(key, value);
    }

    /**
     * 删除session中的属性
     */
    public static void removeAttribute(HttpServletRequest request, String key) {
        HttpSession session = request.getSession();
        session.removeAttribute(key);
    }

}
