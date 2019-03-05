package com.pp.mobile.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api("首页")
@Controller
@RequestMapping("")
@Slf4j
public class IndexController extends FSBaseController {


    @Autowired
    private Environment env;

/*    @GetMapping("")
    public String ind(Model model) {
        return "index";
    }

    @ApiOperation("进入首页")
    @GetMapping(value = {"mobile"})
    public String mobile(HttpServletRequest request, HttpServletResponse response, Model model){
        return "index";
    }

    @ApiOperation("进入首页")
    @GetMapping(value = {"mobile/index"})
    public String mobileIndex(HttpServletRequest request, HttpServletResponse response, Model model){
        return "index";
    }*/


    @GetMapping("env")
    @ApiOperation("查看配置环境")
    @ResponseBody
    public String env(){

        log.info("检查配置环境");
        return "spring boot success ! and profile is ==>"+
                env.getProperty("spring.profiles.active")+"=====>"+
                env.getProperty("server.port");
    }

}