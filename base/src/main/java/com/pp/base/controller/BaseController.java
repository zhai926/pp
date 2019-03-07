package com.pp.base.controller;

import com.pp.base.constant.ErrorConstant;
import com.pp.base.constant.WebConst;
import com.pp.base.exception.BusinessException;
import com.pp.base.utils.MapCache;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 */
@Slf4j
public abstract class BaseController {

//    protected static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    protected MapCache cache = MapCache.single();

//    /**
//     * 获取请求绑定的登录对象
//     * @param request
//     * @return
//     */
//    public UserDomain user(HttpServletRequest request) {
//        return TaleUtils.getLoginUser(request);
//    }
//
//    public Integer getUid(HttpServletRequest request){
//        return this.user(request).getUid();
//    }

    /**
     * 返回异常信息
     * @param e
     * @param msg
     * @return
     */
    protected String exceptionMsg(Exception e, String msg) {
        if (e instanceof BusinessException) {
            msg = StringUtils.isBlank(e.getMessage()) ? ((BusinessException) e).getErrorCodeMsg() : e.getMessage();
        }else{
            msg = "系统错误";
        }
        log.error(msg);
        return msg;
    }

    /**
     * 数组转字符串
     *
     * @param arr
     * @return
     */
    public String join(String[] arr) {
        StringBuilder ret = new StringBuilder();
        String[] var3 = arr;
        int var4 = arr.length;

        for (int var5 = 0; var5 < var4; ++var5) {
            String item = var3[var5];
            ret.append(',').append(item);
        }

        return ret.length() > 0 ? ret.substring(1) : ret.toString();
    }


    public Object getMemberObj(){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        System.out.println("请求的url...." + request.getRequestURI());
//        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
//        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
//        HttpServletRequest request = sra.getRequest();
        HttpSession session = request.getSession();
        if (null == session) {
            System.out.println("用户未登录....");
            throw BusinessException.withErrorCode(ErrorConstant.Auth.NOT_LOGIN);
        }
        return session.getAttribute(WebConst.LOGIN_SESSION_KEY_MEMER);
    }
}
