package com.pp.healthy.controller;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.pp.base.common.RJResponse;
import com.pp.base.constant.ErrorConstant;
import com.pp.base.constant.WebConst;
import com.pp.base.controller.BaseController;
import com.pp.base.exception.BusinessException;
import com.pp.base.service.IRedisService;
import com.pp.base.utils.CodeRedisHelper;
import com.pp.base.utils.TaleUtils;
import com.pp.employee.domain.AdminEmployeeDomain;
import com.pp.employee.domain.AdminUserDomain;
import com.pp.employee.enums.EnumEmpStatus;
import com.pp.employee.service.IAdminEmployeeService;
import com.pp.employee.service.IAdminUserService;
import com.pp.healthy.domain.CommonRequestBo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


@CrossOrigin
@Api("登录登出接口")
@Controller
@RequestMapping("/")
@Slf4j
public class LoginController extends BaseController {

    @Autowired
    private IAdminUserService userService;
    @Autowired
    private IAdminEmployeeService employeeService;
    @Autowired
    private IRedisService redisService;

    @Value("${spring.profiles.active}")
    private String env;

//    @ApiOperation("跳转后台登录页")
//    @GetMapping("admin/login")
//    public String login(){
//        return "admin/login";
//    }

    @ApiOperation("登录")
    @GetMapping(value = "admin/do_get_login")
    @ResponseBody
    public RJResponse doGetLogin(
            HttpServletRequest request,
            HttpServletResponse response,
            @ApiParam(name = "username", value = "用户名", required = true)
            @RequestParam(name = "username", required = true)
                    String username,
            @ApiParam(name = "password", value = "密码", required = false)
            @RequestParam(name = "password", required = false)
                    String password,
            @ApiParam(name = "remeber_me", value = "记住我", required = false)
            @RequestParam(name = "remeber_me", required = false)
                    String remeber_me
    ){
        Integer error_count = cache.get("login_error_count");
        try {
            AdminUserDomain userInfo = null;
            if(StringUtils.isNotBlank(password)){
                userInfo = userService.login(username, password);
            }else{
                // 判断是否需要密码登录
                if(this.isNeedPassword(username)){
                    throw BusinessException.withErrorCode(ErrorConstant.Auth.USERNAME_PASSWORD_IS_EMPTY);
                }
                userInfo = userService.login(username);
            }

            // 查询emp信息
            AdminEmployeeDomain emp = employeeService.get(userInfo.getEmpId());

            if(null == emp){
                throw BusinessException.withErrorCode("用户信息异常，请联系管理员");
            }

            if(EnumEmpStatus.OFF.getCode().equals(emp.getStatus())){
                throw BusinessException.withErrorCode("员工已离职");
            }
            if(true == emp.getHasDeleted()){
                throw BusinessException.withErrorCode("员工已删除");
            }


            request.getSession().setAttribute(WebConst.LOGIN_SESSION_KEY, userInfo);
            // session 12个小时
            request.getSession().setMaxInactiveInterval(12 * 60 * 60);
            if (StringUtils.isNotBlank(remeber_me)) {
                TaleUtils.setCookie(response, userInfo.getId());
            }
//            logService.addLog(LogActions.LOGIN.getAction(), null, request.getRemoteAddr(), userInfo.getUid());
        } catch (Exception e) {
            log.error(e.getMessage());
            error_count = null == error_count ? 1 : error_count + 1;
            if (error_count > 3) {
                return RJResponse.fail("您输入密码已经错误超过3次，请10分钟后尝试");
            }
            cache.set("login_error_count", error_count, 10 * 60);
            String msg =  exceptionMsg(e, "登录失败");
            return RJResponse.fail(msg);
        }

        return RJResponse.success();

    }

    @ApiOperation("登录")
    @PostMapping(value = "admin/login")
    @ResponseBody
    public RJResponse toLogin(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestBody CommonRequestBo commonRequestBo
    ){
        try {
//            if(null == user || StringUtils.isBlank(user.getAccount()) || StringUtils.isBlank(user.getPasswork())){
//                throw BusinessException.withErrorCode("用户名或密码为空");
//            }
//
//            AdminUserDomain userInfo = userService.login(user.getAccount(), user.getPasswork());

            if(StringUtils.isBlank(commonRequestBo.getUsername()) || StringUtils.isBlank(commonRequestBo.getPassword())){
                throw BusinessException.withErrorCode("用户名或密码为空");
            }

            AdminUserDomain userInfo = userService.login(commonRequestBo.getUsername(), commonRequestBo.getPassword());

            // 查询emp信息
            AdminEmployeeDomain emp = employeeService.get(userInfo.getEmpId());

            if(null == emp){
                throw BusinessException.withErrorCode("用户信息异常，请联系管理员");
            }

            if(EnumEmpStatus.OFF.getCode().equals(emp.getStatus())){
                throw BusinessException.withErrorCode("员工已离职");
            }
            if(true == emp.getHasDeleted()){
                throw BusinessException.withErrorCode("员工已删除");
            }

            request.getSession().setAttribute(WebConst.LOGIN_SESSION_KEY, userInfo);
            // session 12个小时
            request.getSession().setMaxInactiveInterval(12 * 60 * 60);

            String token = getToken(userInfo);
//            JSONObject jsonObject=new JSONObject();
//            jsonObject.put("token", token);


            if("dev".equals(env)){
//                Long ex = 10L;
//                redisService.set(token, userInfo, ex, TimeUnit.SECONDS);
//                redisService.set(token, userInfo);
                redisService.set(token, JSONObject.toJSONString(userInfo));
            }else{
                Long ex = 8 * 60 * 60L;
                redisService.set(token, JSONObject.toJSONString(userInfo), ex, TimeUnit.SECONDS);
//                redisService.set(token, userInfo, ex, TimeUnit.SECONDS);
            }

            RJResponse rjResponse = new RJResponse(true);
            rjResponse.setData(token);
            return rjResponse;
//            if (StringUtils.isNotBlank(remeber_me)) {
//                TaleUtils.setCookie(response, userInfo.getId());
//            }
//            logService.addLog(LogActions.LOGIN.getAction(), null, request.getRemoteAddr(), userInfo.getUid());
        } catch (Exception e) {
            String msg =  exceptionMsg(e, "登录失败");
            log.error(msg);
            return RJResponse.fail(msg);
        }

    }

    private boolean isNeedPassword(String username) {
        AdminUserDomain user = userService.getUserInfoByAccount(username);
        if(null != user){
            return user.getHasPassword();
        }
        return true;
    }

    /**
     * 注销
     *
     * @param session
     * @param response
     */
    @ApiOperation("登出")
    @PostMapping("admin/logout")
    @ResponseBody
    public RJResponse logout(HttpServletRequest request, HttpSession session, HttpServletResponse response) {
        try {
            String token = request.getHeader("Access-Token");
            if(StringUtils.isNotBlank(token)){
                redisService.remove(token);
            }
            session.removeAttribute(WebConst.LOGIN_SESSION_KEY);
            Cookie cookie = new Cookie(WebConst.USER_IN_COOKIE, "");
            cookie.setValue(null);
            cookie.setMaxAge(0);// 立即销毁cookie
            cookie.setPath("/");
            response.addCookie(cookie);

//            response.sendRedirect("/admin/login");
        } catch (Exception e) {
            return RJResponse.fail("退出失败");
        }
        return RJResponse.fail("退出成功");
    }

    /**
     * 注销
     *
     * @param session
     * @param response
     */
    @RequestMapping("admin/out")
    public void out(HttpSession session, HttpServletResponse response, org.apache.catalina.servlet4preview.http.HttpServletRequest request) {
        session.removeAttribute(WebConst.LOGIN_SESSION_KEY);
        Cookie cookie = new Cookie(WebConst.USER_IN_COOKIE, "");
        cookie.setValue(null);
        cookie.setMaxAge(0);// 立即销毁cookie
        cookie.setPath("/");
        response.addCookie(cookie);
        try {
            response.sendRedirect("/admin/login");
        } catch (IOException e) {
            e.printStackTrace();
            log.error("注销失败", e);
        }
    }

    public String getToken(AdminUserDomain user) {
        String token="";
        token= JWT.create().withAudience(user.getId()).sign(Algorithm.HMAC256(user.getPasswork()));
        return token;
    }
}
