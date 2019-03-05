package com.pp.frontend.controller;

import com.pp.base.common.RJResponse;
import com.pp.base.constant.WebConst;
import com.pp.base.exception.BusinessException;
import com.pp.member.domain.MemberDomain;
import com.pp.member.service.IMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api("用户注册")
@Controller
@RequestMapping("register")
@Slf4j
public class RegisterController extends FSBaseController{

    @Autowired
    private IMemberService memberService;

    @ApiOperation("进入登录页")
    @GetMapping(value="")
    public String toRegister(Model mdel){
        return "login/register";
    }

    @ApiOperation("注册")
    @PostMapping(value = "/register_pc")
    @ResponseBody
    public RJResponse register_pc(
            HttpServletRequest request,
            HttpServletResponse response,
            @ApiParam(name = "phone", value = "用户名", required = true)
            @RequestParam(name = "phone", required = true)
                    String phone,
            @ApiParam(name = "password", value = "密码", required = true)
            @RequestParam(name = "password", required = true)
                    String password,
            @ApiParam(name = "confirmPassword", value = "确认密码", required = true)
            @RequestParam(name = "confirmPassword", required = true)
                    String confirmPassword,
            @ApiParam(name = "email", value = "邮箱", required = true)
            @RequestParam(name = "email", required = false)
                    String email
    ){
        try {

            if(StringUtils.isBlank(password)){
                throw new BusinessException("请设置用户密码");
            }
            if(!password.equals(confirmPassword)){
                throw new BusinessException("密码和确认密码不一致");
            }

            // 重置密码
            MemberDomain domain = new MemberDomain();
            domain.setPhoneNumber(phone);
            domain.setPassWordText(password);
            domain.setEmail(email);
            memberService.register(domain);
            // 登录
            MemberDomain userInfo = memberService.login(phone, password);

            request.getSession().setAttribute(WebConst.LOGIN_SESSION_KEY_MEMER, userInfo);
            request.getSession().setMaxInactiveInterval(1 * 60 * 60);
        } catch (Exception e) {
            log.error(e.getMessage());
            String msg = exceptionMsg(e, "注册失败");
            return RJResponse.fail(msg);
        }
        return RJResponse.success();
    }

    @ApiOperation("注册")
    @PostMapping(value = "/register")
    @ResponseBody
    public RJResponse register(
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
        try {
            // TODO 校验短信验证码是否正确

            // 重置密码
            MemberDomain domain = new MemberDomain();
            domain.setPhoneNumber(phone);
            domain.setPassWordText(password);
            memberService.register(domain);
            // 登录
            MemberDomain userInfo = memberService.login(phone, password);

            request.getSession().setAttribute(WebConst.LOGIN_SESSION_KEY_MEMER, userInfo);
            request.getSession().setMaxInactiveInterval(1 * 60 * 60);
        } catch (Exception e) {
            log.error(e.getMessage());
            String msg = exceptionMsg(e, "注册失败");
            return RJResponse.fail(msg);
        }
        return RJResponse.success();
    }

}
