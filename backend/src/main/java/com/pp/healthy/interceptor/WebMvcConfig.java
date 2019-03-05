package com.pp.healthy.interceptor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 向MVC中添加自定义组件
 */
//@Component
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

//    @Autowired
//    private BaseInterceptor baseInterceptor;
    @Autowired
    private AuthenticationInterceptor authenticationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(baseInterceptor);
        registry.addInterceptor(authenticationInterceptor).addPathPatterns("/**");    // 拦截所有请求，通过判断是否有 @LoginRequired 注解 决定是否需要登录
    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/admin/**")
//                .addResourceLocations("/public", "classpath:/admin/")
//                .setCachePeriod(31556926);
//    }

}
