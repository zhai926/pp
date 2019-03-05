package com.pp.mobile.controller;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.pp.base.common.RJResponse;
import com.pp.base.constant.ErrorConstant;
import com.pp.base.constant.WebConst;
import com.pp.base.exception.BusinessException;
import com.pp.base.service.IRedisService;
import com.pp.base.utils.TaleUtils;
import com.pp.member.domain.MemberDomain;
import com.pp.member.service.IMemberService;
import com.pp.mobile.domain.CommonRequestBo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.concurrent.TimeUnit;

@CrossOrigin
@Api("用户登录")
@RestController
@RequestMapping("login")
@Slf4j
public class LoginController extends FSBaseController {

    @Autowired
    private IMemberService memberService;
    @Autowired
    private IRedisService redisService;

    @Value("${spring.profiles.active}")
    private String env;

    @ApiOperation("注册")
    @PostMapping(value = "/register")
    public RJResponse register(
            HttpServletRequest request,
            @RequestBody CommonRequestBo commonRequestBo
            /*@ApiParam(name = "phone", value = "用户名", required = true)
            @RequestParam(name = "phone", required = true)
                    String phone,
            @ApiParam(name = "password", value = "密码", required = true)
            @RequestParam(name = "password", required = true)
                    String password,
            @ApiParam(name = "smscode", value = "短信验证码", required = true)
            @RequestParam(name = "smscode", required = false)
                    String smscode*/
    ){
        try {
            if(StringUtils.isBlank(commonRequestBo.getPhone())){
                throw new BusinessException("用户名不能为空");
            }
            if(StringUtils.isBlank(commonRequestBo.getPassword())){
                throw new BusinessException("密码不能为空");
            }
            if(StringUtils.isBlank(commonRequestBo.getSmsCode())){
                throw new BusinessException("短信验证码不能为空");
            }

            MemberDomain domain = memberService.getByPhone(commonRequestBo.getPhone());
            if(null != domain){
                throw BusinessException.withErrorCode("用户已存在，请登录！");
            }

            // TODO 校验短信验证码是否正确

            // 重置密码
            domain = new MemberDomain();
            domain.setPhoneNumber(commonRequestBo.getPhone());
            domain.setPassWordText(commonRequestBo.getPassword());
            memberService.register(domain);
           /* // 登录
            MemberDomain userInfo = memberService.login(commonRequestBo.getPhone(), commonRequestBo.getPassword());

            request.getSession().setAttribute(WebConst.LOGIN_SESSION_KEY_MEMER, userInfo);
            request.getSession().setMaxInactiveInterval(1 * 60 * 60);
            String token = getToken(userInfo);

            if("dev".equals(env)){
//                Long ex = 10L;
//                redisService.set(token, userInfo, ex, TimeUnit.SECONDS);
//                redisService.set(token, userInfo);
                redisService.set(token, JSONObject.toJSONString(userInfo));
            }else{
                Long ex = 8 * 60 * 60L;
                redisService.set(token, JSONObject.toJSONString(userInfo), ex, TimeUnit.SECONDS);
//                redisService.set(token, userInfo, ex, TimeUnit.SECONDS);
            }*/

            RJResponse rjResponse = new RJResponse(true);
//            rjResponse.setData(token);
            rjResponse.setMessage("注册成功，请登录！");
            return rjResponse;

        } catch (Exception e) {
            log.error(e.getMessage());
            String msg = exceptionMsg(e, "注册失败");
            return RJResponse.fail(msg);
        }
    }

    @ApiOperation("登录")
    @PostMapping(value = "/reset_pass")
    public RJResponse resetPass(
            HttpServletRequest request,
           @RequestBody CommonRequestBo commonRequestBo
            /*HttpServletResponse response,
            @ApiParam(name = "phone", value = "用户名", required = true)
            @RequestParam(name = "phone", required = true)
                    String phone,
            @ApiParam(name = "password", value = "密码", required = true)
            @RequestParam(name = "password", required = true)
                    String password,
            @ApiParam(name = "smscode", value = "短信验证码", required = true)
            @RequestParam(name = "smscode", required = false)
                    String smscode*/
    ){
        try {
            if(StringUtils.isBlank(commonRequestBo.getPhone())){
                throw new BusinessException("手机不能为空");
            }
            if(StringUtils.isBlank(commonRequestBo.getPassword())){
                throw new BusinessException("密码不能为空");
            }
            if(StringUtils.isBlank(commonRequestBo.getSmsCode())){
                throw new BusinessException("短信验证码不能为空");
            }

            // TODO 校验短信验证码是否正确

            // 重置密码

            MemberDomain domain = memberService.getByPhone(commonRequestBo.getPhone());
            if(null == domain){
                throw BusinessException.withErrorCode("用户不存在");
            }
            domain.setPassWordText(commonRequestBo.getPassword());
            domain.setPassWord(TaleUtils.MD5encode(commonRequestBo.getPassword()));
            memberService.update(domain);

            // 登录
            MemberDomain userInfo = memberService.login(commonRequestBo.getPhone(), commonRequestBo.getPassword());

            if(true == userInfo.getHasDeleted()){
                throw BusinessException.withErrorCode("用户已删除");
            }

            /*request.getSession().setAttribute(WebConst.LOGIN_SESSION_KEY_MEMER, userInfo);
            request.getSession().setMaxInactiveInterval(1 * 60 * 60);
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
            }*/

            RJResponse rjResponse = new RJResponse(true);
//            rjResponse.setData(token);
            rjResponse.setMessage("密码修改成功，请登录！");
            return rjResponse;

        } catch (Exception e) {
            String msg = exceptionMsg(e, "登录失败");
            return RJResponse.fail(msg);
        }
    }

    @ApiOperation("登录")
    @PostMapping(value = "/login")
    public RJResponse toLogin(
            HttpServletRequest request,
            @RequestBody CommonRequestBo commonRequestBo
            /*HttpServletResponse response,
            @ApiParam(name = "phone", value = "用户名", required = true)
            @RequestParam(name = "phone", required = true)
                    String phone,
            @ApiParam(name = "password", value = "密码", required = true)
            @RequestParam(name = "password", required = true)
                    String password*/
    ){
        try {

            if(StringUtils.isBlank(commonRequestBo.getPhone())){
                throw new BusinessException("用户名不能为空");
            }
            if(StringUtils.isBlank(commonRequestBo.getPassword())){
                throw new BusinessException("密码不能为空");
            }

            MemberDomain userInfo = memberService.login(commonRequestBo.getPhone(), commonRequestBo.getPassword());

            if(true == userInfo.getHasDeleted()){
                throw BusinessException.withErrorCode("用户已删除");
            }

            request.getSession().setAttribute(WebConst.LOGIN_SESSION_KEY_MEMER, userInfo);
            request.getSession().setMaxInactiveInterval(1 * 60 * 60);

            userInfo.setPassWord(TaleUtils.MD5encode(commonRequestBo.getPassword()));
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

        } catch (Exception e) {
            String msg = exceptionMsg(e, "登录失败");
            return RJResponse.fail(msg);
        }
    }

    @ApiOperation("登出")
    @PostMapping("logout")
    public RJResponse logout(HttpSession session, HttpServletResponse response, HttpServletRequest request) {
        try {
            log_out(request, session, response);
        } catch (Exception e) {
            return RJResponse.fail("退出失败");
        }
        return RJResponse.fail("退出成功");
    }

    @ApiOperation("验证手机号邮箱是否存在")
    @PostMapping("/un_valid")
    @ResponseBody
    public boolean un_valid(
            @RequestBody CommonRequestBo commonRequestBo
            /*@ApiParam(name = "type", value = "phone,email", required = true)
            @RequestParam(name = "type", required = true) String type,
            @ApiParam(name = "val", value = "用户名", required = true)
            @RequestParam(name = "val", required = true) String val*/
    ){

        if(StringUtils.isBlank(commonRequestBo.getType()) || StringUtils.isBlank(commonRequestBo.getVal())){
            throw new BusinessException(ErrorConstant.Common.PARAM_IS_EMPTY);
        }

        if("phone".equals(commonRequestBo.getType())){
            MemberDomain domain = memberService.getByPhone(commonRequestBo.getPhone());
            return null == domain ? true : false;
        }else{
            try{
                MemberDomain domain = memberService.getByEmail(commonRequestBo.getVal());
                return null == domain ? true : false;
            }catch (Exception e){
                log.error("验证email异常", e);
                return false;
            }
        }
    }

    private void log_out(HttpServletRequest request, HttpSession session, HttpServletResponse response){
        String token = request.getHeader("Access-Token");
        if(StringUtils.isNotBlank(token)){
            redisService.remove(token);
        }
        session.removeAttribute(WebConst.LOGIN_SESSION_KEY_MEMER);
        Cookie cookie = new Cookie(WebConst.USER_IN_COOKIE, "");
        cookie.setValue(null);
        cookie.setMaxAge(0);// 立即销毁cookie
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public String getToken(MemberDomain user) {
        String token="";
        token= JWT.create().withAudience(user.getMemberId()).sign(Algorithm.HMAC256(user.getPassWord()));
        return token;
    }

}
