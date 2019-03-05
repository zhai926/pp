package com.pp.frontend.controller;

import com.pp.base.common.RJResponse;
import com.pp.base.constant.ErrorConstant;
import com.pp.base.constant.WebConst;
import com.pp.base.enums.EnumBoolean;
import com.pp.base.exception.BusinessException;
import com.pp.base.utils.TaleUtils;
import com.pp.member.domain.MemberDomain;
import com.pp.member.service.IMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Api("用户登录")
@Controller
@RequestMapping("login")
@Slf4j
public class LoginController extends FSBaseController {

    @Autowired
    private IMemberService memberService;

    @ApiOperation("跳转登录页")
    @GetMapping("")
    public String login(){
        return "login/login";
    }

    @ApiOperation("登录")
    @PostMapping(value = "/reset_pass")
    @ResponseBody
    public RJResponse resetPass(
            HttpServletRequest request,
            HttpServletResponse response,
            @ApiParam(name = "phone", value = "用户名", required = true)
            @RequestParam(name = "phone", required = true)
                    String phone,
            @ApiParam(name = "password", value = "密码", required = true)
            @RequestParam(name = "password", required = true)
                    String password,
            @ApiParam(name = "smscode", value = "短信验证码", required = true)
            @RequestParam(name = "smscode", required = false)
                    String smscode
    ){
        Integer error_count = cache.get("login_error_count");
        try {
            // TODO 校验短信验证码是否正确


            // 重置密码
            MemberDomain domain = memberService.getByPhone(phone);
            if(null == domain){
                throw BusinessException.withErrorCode("用户不存在");
            }
            domain.setPassWordText(password);
            domain.setPassWord(TaleUtils.MD5encode(password));
            memberService.update(domain);

            // 登录
            MemberDomain userInfo = memberService.login(phone, password);

            if(true == userInfo.getHasDeleted()){
                throw BusinessException.withErrorCode("用户已删除");
            }

            request.getSession().setAttribute(WebConst.LOGIN_SESSION_KEY_MEMER, userInfo);
            request.getSession().setMaxInactiveInterval(1 * 60 * 60);
        } catch (Exception e) {
            log.error(e.getMessage());
            error_count = null == error_count ? 1 : error_count + 1;
//            if (error_count > 3) {
//                return RJResponse.fail("您输入密码已经错误超过3次，请10分钟后尝试");
//            }
            cache.set("login_error_count", error_count, 10 * 60);
            String msg = exceptionMsg(e, "登录失败");
            return RJResponse.fail(msg);
        }
        return RJResponse.success();
    }

    @ApiOperation("验证手机号邮箱是否存在")
    @PostMapping("/un_valid")
    @ResponseBody
    public boolean un_valid(
            @ApiParam(name = "type", value = "phone,email", required = true)
            @RequestParam(name = "type", required = true) String type,
            @ApiParam(name = "val", value = "用户名", required = true)
            @RequestParam(name = "val", required = true) String val){

        if(StringUtils.isBlank(type) || StringUtils.isBlank(val)){
            throw new BusinessException(ErrorConstant.Common.PARAM_IS_EMPTY);
        }

        if("phone".equals(type)){
            MemberDomain domain = memberService.getByPhone(val);
            return null == domain ? true : false;
        }else{
            try{
                MemberDomain domain = memberService.getByEmail(val);
                return null == domain ? true : false;
            }catch (Exception e){
                log.error("验证email异常", e);
                return false;
            }
        }
    }

    @ApiOperation("登录")
    @PostMapping(value = "/login")
    @ResponseBody
    public RJResponse toLogin(
            HttpServletRequest request,
            HttpServletResponse response,
            @ApiParam(name = "phone", value = "用户名", required = true)
            @RequestParam(name = "phone", required = true)
                    String phone,
            @ApiParam(name = "password", value = "密码", required = true)
            @RequestParam(name = "password", required = true)
                    String password,
            @ApiParam(name = "validImg", value = "是否要图片验证Y/N", required = true)
            @RequestParam(name = "validImg", required = true)
                    String validImg,
            @ApiParam(name = "img", value = "图片验证码", required = true)
            @RequestParam(name = "img", required = false)
                    String img
    ){
        Integer error_count = cache.get("login_error_count");
        try {
            if(EnumBoolean.TRUE.getStrCode().equals(validImg)){
                if(StringUtils.isBlank(img)){
                    throw new BusinessException("图片验证码不能为空");
                }else{
                    // 校验图片验证码
                    String captchaId = (String) request.getSession().getAttribute("pic_vrify_code");
//        String parameter = httpServletRequest.getParameter("vrifyCode");
                    System.out.println("Session  vrifyCode "+captchaId+" form vrifyCode "+img);

                    if (!img.equals(captchaId)) {
                        throw BusinessException.withErrorCode("图片验证码错误");
                    }
                }
            }

            MemberDomain userInfo = memberService.login(phone, password);

            if(true == userInfo.getHasDeleted()){
                throw BusinessException.withErrorCode("用户已删除");
            }

            request.getSession().setAttribute(WebConst.LOGIN_SESSION_KEY_MEMER, userInfo);
            request.getSession().setMaxInactiveInterval(1 * 60 * 60);
//            if (StringUtils.isNotBlank(remeber_me)) {
//                TaleUtils.setCookie(response, userInfo.getMemberId());
//            }
//            logService.addLog(LogActions.LOGIN.getAction(), null, request.getRemoteAddr(), userInfo.getUid());
        } catch (Exception e) {
            log.error(e.getMessage());
            error_count = null == error_count ? 1 : error_count + 1;
//            if (error_count > 3) {
//                return RJResponse.fail("您输入密码已经错误超过3次，请10分钟后尝试");
//            }
            cache.set("login_error_count", error_count, 10 * 60);

            String msg = exceptionMsg(e, "登录失败");
            return RJResponse.fail(msg);
        }

        return RJResponse.success();

    }

    @ApiOperation("登出")
    @GetMapping("out")
    @ResponseBody
    public RJResponse out(HttpSession session, HttpServletResponse response, org.apache.catalina.servlet4preview.http.HttpServletRequest request) {
        try {
            log_out(session, response);
        } catch (Exception e) {
            return RJResponse.fail("退出失败");
        }
        return RJResponse.fail("退出成功");
    }

    private void log_out(HttpSession session, HttpServletResponse response){
        session.removeAttribute(WebConst.LOGIN_SESSION_KEY_MEMER);
        Cookie cookie = new Cookie(WebConst.USER_IN_COOKIE, "");
        cookie.setValue(null);
        cookie.setMaxAge(0);// 立即销毁cookie
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    /**
     * 注销
     *
     * @param session
     * @param response
     */
    @RequestMapping("/logout")
    public void logout(HttpSession session, HttpServletResponse response, org.apache.catalina.servlet4preview.http.HttpServletRequest request) {
        log_out(session, response);
        try {
            response.sendRedirect("/");
        } catch (IOException e) {
            e.printStackTrace();
            log.error("注销失败", e);
        }
    }

}
