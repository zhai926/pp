package com.pp.healthy.controller;

import com.pp.base.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api("首页")
@Controller
@RequestMapping("/admin/home")
public class HomeController extends BaseController {

    @ApiOperation("首页页面")
    @GetMapping("")
    public String page(HttpServletRequest request, HttpServletResponse response){
        return "admin/home";
    }


    @Autowired
    private Environment env;

    @RequestMapping("/env")
    @ResponseBody
    public String env(){
        return "spring boot success ! and profile is ==>"+
                env.getProperty("spring.profiles.active")+"=====>"+
                env.getProperty("server.port");
    }


}
