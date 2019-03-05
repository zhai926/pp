package com.pp.mobile.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.pp.base.constant.WebConst;
import com.pp.base.service.IRedisService;
import com.pp.member.domain.MemberDomain;
import com.pp.mobile.annotation.PassToken;
import com.pp.mobile.annotation.UserLoginToken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    private IRedisService redisService;

    @Value("${spring.profiles.active}")
    private String env;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token
        if(StringUtils.isBlank(token)){
            token = httpServletRequest.getHeader("Access-Token");
        }
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(UserLoginToken.class)) {
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if (userLoginToken.required()) {
                // 执行认证
                if (token == null) {
                    throw new RuntimeException("无token，请重新登录");
                }

                if(!redisService.exists(token)){
                    throw new RuntimeException("无token，请重新登录");
                }

                Object obj = redisService.get(token);
                if(null == obj){
                    throw new RuntimeException("请重新登录");
                }
                // 获取 token 中的 user id
                String userId;
                try {
                    userId = JWT.decode(token).getAudience().get(0);
                } catch (JWTDecodeException j) {
                    throw new RuntimeException("401");
                }

                MemberDomain cacheUser =  JSONObject.parseObject(obj.toString(), MemberDomain.class);
                if(!userId.equals(cacheUser.getId())){
                    if(null == obj){
                        throw new RuntimeException("授权失败，请重新登录");
                    }
                }

                // 验证 token
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(cacheUser.getPassWord())).build();
                try {
                    jwtVerifier.verify(token);

                    // TODO 先放到session中
                    httpServletRequest.getSession().setAttribute(WebConst.LOGIN_SESSION_KEY_MEMER, cacheUser);
                    // session 12个小时
                    httpServletRequest.getSession().setMaxInactiveInterval(12 * 60 * 60);

                } catch (JWTVerificationException e) {
                    throw new RuntimeException("401");
                }
                return true;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }
}
