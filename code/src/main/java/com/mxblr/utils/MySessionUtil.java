package com.mxblr.utils;

import com.mxblr.error.BusinessException;
import com.mxblr.error.EmBusinessErr;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * Session相关类
 */
public final class MySessionUtil {

    /**
     * 获取request对象
     */
    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }

    /**
     * 获取response对象
     */
    public static HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getResponse();
    }

    /**
     * 获取session对象
     */
    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    /**
     * 获取session中的属性值
     */
    public static Object getAttribute(String key) throws BusinessException {
        HttpSession session = getSession();
        if (session.getAttribute(key) == null) {
            MyLog.error("session没有\"" + key + "\"属性");
            throw new BusinessException(EmBusinessErr.PARAMETER_INVALIDATION_ERROR, "session没有\"" + key + "\"属性");
        }
        return session.getAttribute(key);
    }

    /**
     * 在session中存入值
     */
    public static void setAttribute(String key, Object value) {
        getSession().setAttribute(key, value);
    }

    /**
     * 删除session中的属性
     */
    public static void removeAttribute(String key) {
        getSession().removeAttribute(key);
    }

    /**
     * 获取服务器路径
     */
    static String getRealPath() {
        return getSession().getServletContext().getRealPath("/");
    }

}
