package com.pp.healthy.interceptor;

import com.pp.base.exception.BusinessException;
import com.pp.healthy.utils.AdminCommons;
import com.pp.healthy.utils.Commons;
import com.pp.base.constant.WebConst;
import com.pp.base.enums.EnumTypes;
import com.pp.base.utils.MapCache;
import com.pp.base.utils.TaleUtils;
import com.pp.base.utils.UUID;
import com.pp.employee.domain.AdminMenuDomain;
import com.pp.employee.domain.AdminUserDomain;
import com.pp.employee.service.IAdminUserService;
import com.pp.employee.service.IMenuService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 自定义拦截器
 */
@Component
public class BaseInterceptor implements HandlerInterceptor {
    private static final Logger LOGGE = LoggerFactory.getLogger(BaseInterceptor.class);
    private static final String USER_AGENT = "user-agent";

    @Autowired
    private IAdminUserService userService;
    @Autowired
    private IMenuService menuService;
    @Autowired
    private AdminCommons adminCommons;
    @Autowired
    private Commons commons;

    private MapCache cache = MapCache.single();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String uri = request.getRequestURI();

//        LOGGE.info("UserAgent: {}   ---------   用户访问地址: {}, 来路地址: {}",
//                request.getHeader(USER_AGENT),  uri, IPKit.getIpAddrByRequest(request));
//        LOGGE.debug("test debug {}", "test debug");
//        LOGGE.warn("test warn {}", "test warn");
//        LOGGE.error("test error {}", "test error");

        //请求拦截处理
        AdminUserDomain user = getLoginUser(request);
        if (null == user) {
            String uid = TaleUtils.getCookieUid(request);
            if (null != uid) {
                //这里还是有安全隐患,cookie是可以伪造的
                // TODO redis
                user = userService.getUserInfoById(uid);
                request.getSession().setAttribute(WebConst.LOGIN_SESSION_KEY, user);
                request.getSession().setMaxInactiveInterval(12 * 60 * 60);
            }
        }

        if(null ==user // !uri.startsWith("/emp/login") && emp 限制登录
                && !uri.startsWith("/admin/login")
                && !uri.startsWith("/admin/logout")
                && !uri.startsWith("/admin/css") && !uri.startsWith("/admin/images")
                && !uri.startsWith("/admin/js") && !uri.startsWith("/admin/plugins")
                && !uri.startsWith("/admin/editormd")
                && !uri.startsWith("/file")
                && !uri.startsWith("/error")
                && !uri.startsWith("/test")
                && !uri.startsWith("/admin/do_get_login")
                && !uri.startsWith("/v2/api-docs")
                ) {
            //response.sendRedirect(request.getContextPath() + "/admin/login");
            throw BusinessException.withErrorCode("请登录");
//            return false;
        }

//        if(!uri.startsWith("/admin/login")  && null == user){
//            response.sendRedirect(request.getContextPath() + "/admin/login");
//            return false;
//        }
//        if (uri.startsWith("/admin") && !uri.startsWith("/admin/login")  && null == user
//                && !uri.startsWith("/admin/css") && !uri.startsWith("/admin/images")
//                && !uri.startsWith("/admin/js") && !uri.startsWith("/admin/plugins")
//                && !uri.startsWith("/admin/editormd")) {
//            response.sendRedirect(request.getContextPath() + "/admin/login");
//            return false;
//        }
        //设置get请求的token
        if (request.getMethod().equals("GET")) {
            String pp_token = UUID.UU64();
            // 默认存储30分钟
            cache.hset(EnumTypes.CSRF_TOKEN.getType(), pp_token, uri, 30 * 60);
            request.setAttribute("_csrf_token", pp_token);
        }

        // 可以到任意界面, 控制按钮 url 查询 menu\
//        String url = uri.split("\\?")[0];
//        if(null != user && StringUtils.isNotBlank(user.getEmpId())){
//            List<AdminMenuDomain> buttons = menuService.getButtons(url, user.getEmpId());
//            if(!CollectionUtils.isEmpty(buttons)){
//                request.setAttribute("buttons", buttons);
//            }
//        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
//        OptionsDomain ov = optionService.getOptionByName("site_record");
        httpServletRequest.setAttribute("commons", commons);//一些工具类和公共方法
//        httpServletRequest.setAttribute("option", ov);
        httpServletRequest.setAttribute("adminCommons", adminCommons);
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
    public AdminUserDomain getLoginUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (null == session) {
            return null;
        }
        return (AdminUserDomain) session.getAttribute(WebConst.LOGIN_SESSION_KEY);
    }
}
