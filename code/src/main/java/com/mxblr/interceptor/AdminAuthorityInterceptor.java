package com.mxblr.interceptor;

import com.mxblr.error.BusinessException;
import com.mxblr.error.EmBusinessErr;
import com.mxblr.utils.Constants;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 2019/05/15
 * 超级管理员权限拦截器
 * 只允许超级管理员通过拦截器
 */
@Component
public class AdminAuthorityInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Byte role = (Byte) request.getSession().getAttribute(Constants.SESSION_USER_ROLE);
        //超级管理员才能通过拦截器
        if (role != null && role == Constants.USER_ROLE_SUPER_ADMIN) {
            return true;
        } else {
            throw new BusinessException(EmBusinessErr.PERMISSION_DENIED);
        }
    }
}
