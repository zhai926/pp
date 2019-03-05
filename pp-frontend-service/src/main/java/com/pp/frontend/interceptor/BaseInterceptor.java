package com.pp.frontend.interceptor;

import com.pp.base.constant.WebConst;
import com.pp.base.enums.EnumTypes;
import com.pp.base.utils.MapCache;
import com.pp.base.utils.TaleUtils;
import com.pp.base.utils.UUID;
import com.pp.member.domain.MemberDomain;
import com.pp.member.service.IMemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 自定义拦截器
 */
@Component
public class BaseInterceptor implements HandlerInterceptor {
    private static final Logger LOGGE = LoggerFactory.getLogger(BaseInterceptor.class);
    private static final String USER_AGENT = "user-agent";

//    @Autowired
//    private AdminCommons adminCommons;
//    @Autowired
//    private Commons commons;

    @Autowired
    private IMemberService memberService;

    private MapCache cache = MapCache.single();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String uri = request.getRequestURI();
        //请求拦截处理
        MemberDomain user = getLoginUser(request);
        if (null == user) {
            String uid = TaleUtils.getCookieUid(request);
            if (null != uid) {
                //这里还是有安全隐患,cookie是可以伪造的
                // TODO redis
                user = memberService.get(uid);
                MemberDomain loginUser = memberService.login(user.getPhoneNumber(), user.getPassWordText());
                request.getSession().setAttribute(WebConst.LOGIN_SESSION_KEY_MEMER, loginUser);
                request.getSession().setMaxInactiveInterval(1 * 60 * 60);
            }
        }

        if(null != user && (uri.equals("/login") || uri.equals("/register"))){
            response.sendRedirect(request.getContextPath() + "/member/center");
            return false;
        }

        if(null == user &&
                (uri.startsWith("/member/") ||
                        uri.startsWith("/member") ||
                        uri.startsWith("member/") ||
                        uri.startsWith("member"))) {
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }

        //设置get请求的token
        if (request.getMethod().equals("GET")) {
            String pp_token = UUID.UU64();
            // 默认存储30分钟
            cache.hset(EnumTypes.CSRF_TOKEN.getType(), pp_token, uri, 30 * 60);
            request.setAttribute("_csrf_token", pp_token);
        }


        if(null != user){
            request.setAttribute("PPM", user.getMemberId());
            request.setAttribute("PPMM", user.getPhoneNumber());
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
//        OptionsDomain ov = optionService.getOptionByName("site_record");
//        httpServletRequest.setAttribute("commons", commons);//一些工具类和公共方法
//        httpServletRequest.setAttribute("option", ov);
//        httpServletRequest.setAttribute("adminCommons", adminCommons);
//        initSiteConfig(httpServletRequest);

    }

//    private void initSiteConfig(HttpServletRequest request) {
//        if (WebConst.initConfig.isEmpty()){
//            List<OptionsDomain> options = optionService.getOptions();
//            Map<String, String> querys = new HashMap<>();
//            options.forEach(option -> {
//                querys.put(option.getName(), option.getValue());
//            });
//            WebConst.initConfig = querys;
//        }
//    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }


    /**
     * 返回当前登录用户
     *
     * @return
     */
    public MemberDomain getLoginUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (null == session) {
            return null;
        }
        return (MemberDomain) session.getAttribute(WebConst.LOGIN_SESSION_KEY_MEMER);
    }
}
