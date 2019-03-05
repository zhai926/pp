package com.pp.mobile.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.pp.base.common.RJResponse;
import com.pp.mobile.domain.CommonRequestBo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

@CrossOrigin
@Api("验证接口,图片,短信")
@Controller
@RequestMapping("check")
@Slf4j
public class CheckController {

    @Autowired
    DefaultKaptcha defaultKaptcha;

    @ApiOperation("生成验证图片")
    @GetMapping("/make_pic_code")
    public void defaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception{
            byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            //生产验证码字符串并保存到session中
            String createText = defaultKaptcha.createText();
            httpServletRequest.getSession().setAttribute("pic_vrify_code", createText);
            //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream =
                httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();
    }

    @ApiOperation("检验图片验证码")
    @PostMapping("/valid_pic_code")
    @ResponseBody
    public RJResponse imgvrifyControllerDefaultKaptcha(HttpServletRequest httpServletRequest,
                                                       @RequestBody CommonRequestBo commonRequestBo
                                                       ){
        String captchaId = (String) httpServletRequest.getSession().getAttribute("pic_vrify_code");
//        String parameter = httpServletRequest.getParameter("vrifyCode");
        System.out.println("Session  vrifyCode "+captchaId+" form vrifyCode "+commonRequestBo.getImgCode());

        if (!captchaId.equals(commonRequestBo.getImgCode())) {
            return RJResponse.fail("验证码错误");
        }
        return RJResponse.success("success");
    }

}
