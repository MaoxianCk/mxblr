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
            throw new BusinessException(EmBusinessErr.PARAMETER_INVALIDATION_ERROR, "登录状态错误");
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

    /**
     * 检查是否管理员登录
     * 判断方法：检查role属性
     */
    public static void checkSessionRole(HttpServletRequest request) throws BusinessException {
        HttpSession session = request.getSession();
        Byte role = (Byte) getAttribute(request, Constants.SESSION_USER_ROLE);
        Integer id = (Integer) getAttribute(request, Constants.SESSION_USER_ID);
        if (role != Constants.USER_ROLE_SUPER_ADMIN && role != Constants.USER_ROLE_ADMIN && role != Constants.USER_ROLE_WRITER) {
            MyLog.error("角色权限异常 role : " + role);
            throw new BusinessException(EmBusinessErr.PARAMETER_INVALIDATION_ERROR, "登录状态错误");
        }
    }
}
