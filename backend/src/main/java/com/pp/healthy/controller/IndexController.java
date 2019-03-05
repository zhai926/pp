package com.pp.healthy.controller;

import com.pp.base.controller.BaseController;
import com.pp.employee.service.IMenuService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@Api("后台首页")
@Controller("adminIndexController")
@RequestMapping("/")
@Slf4j
public class IndexController extends BaseController {

/*    @ApiOperation("进入首页")
    @GetMapping(value = {""})
    public String index(HttpServletRequest request, HttpServletResponse response, Model model){
        return "index";
    }

    @ApiOperation("进入首页")
    @GetMapping(value = {"admin"})
    public String admin(HttpServletRequest request, HttpServletResponse response, Model model){
        return "index";
    }

    @ApiOperation("进入首页")
    @GetMapping(value = {"admin/index"})
    public String adminIndex(HttpServletRequest request, HttpServletResponse response, Model model){
        return "index";
    }*/
}
