package com.pp.healthy.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//@Api("错误页面")
@Controller
@RequestMapping("/error")
@Slf4j
public class ErrorController {

    @ApiOperation("404")
    @GetMapping("/404")
    public String to404(){
        return "error/404";
    }

    @ApiOperation("405")
    @GetMapping("/405")
    public String to405(){
        return "error/405";
    }

}
