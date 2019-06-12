package com.mxblr.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * 2019/05/15
 * web信息配置类
 */
@Configuration
public class MyWebConfig implements WebMvcConfigurer {
    @Resource
    private UserLoginInterceptor userLoginInterceptor;
    @Resource
    private AdminAuthorityInterceptor adminAuthorityInterceptor;

    /**
     * 拦截器注册
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /*用户登录拦截器*/
        registry.addInterceptor(userLoginInterceptor)
                .addPathPatterns("/admin/**");

        /*超级管理员拦截器*/
        //超级管理员权限才能访问的路径
        String[] adminAuthorityPath = {
                "/admin/user/*",};

        //超级管理员拦截器注册
        registry.addInterceptor(adminAuthorityInterceptor)
                .addPathPatterns(adminAuthorityPath);
    }
}
