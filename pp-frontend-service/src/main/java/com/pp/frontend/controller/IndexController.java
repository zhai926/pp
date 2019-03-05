package com.pp.frontend.controller;

import com.github.pagehelper.PageInfo;
import com.pp.article.domain.RotateDomain;
import com.pp.article.service.IRotateService;
import com.pp.goods.domain.GoodsGiftDomain;
import com.pp.goods.service.IGoodsGiftService;
import com.pp.video.domain.VideoDomain;
import com.pp.video.service.VideoService;
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

import java.util.List;

@Api("首页")
@Controller
@RequestMapping("")
@Slf4j
public class IndexController extends FSBaseController {


    @Autowired
    private Environment env;
    @Autowired
    private IRotateService rotateService;
    @Autowired
    private IGoodsGiftService goodsGiftService;

    @Autowired
    private VideoService videoService;

    @GetMapping("")
    public String ind(Model model) {
        return "redirect:index";
    }

    @GetMapping("env")
    @ApiOperation("查看配置环境")
    @ResponseBody
    public String env(){

        log.info("检查配置环境");
        return "spring boot success ! and profile is ==>"+
                env.getProperty("spring.profiles.active")+"=====>"+
                env.getProperty("server.port");
    }


/*

		#backend api

    location /backend {
            #root   html;
            #index  index.html index.htm;
        proxy_set_header Host $host;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_pass http://127.0.0.1:8001;
    }


		#mobile page
    location /m {
			#root /usr/local/pp/page/mobile;
            #try_files $uri $uri/ index.html index.htm;
        proxy_set_header Host $host;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_pass http://127.0.0.1:8003;
    }

    location /mobile {
            #root   html;
            #index  index.html index.htm;
        proxy_set_header Host $host;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_pass http://127.0.0.1:8003;
    }
*/



    @GetMapping("index")
    @ApiOperation("首页")
    public String index(Model model){

        model.addAttribute("msg", "欢迎来到养生堂");

        RotateDomain rotateDomain = new RotateDomain();
        rotateDomain.setHasDeleted(false);
        PageInfo<RotateDomain> rotateDomainList = rotateService.page(rotateDomain, 1, 10);
        model.addAttribute("rotateList", rotateDomainList.getList());

        GoodsGiftDomain goodsGiftDomain = new GoodsGiftDomain();
        goodsGiftDomain.setHasDeleted(false);
        // TODO
//        goodsGiftDomain.setType();
        PageInfo<GoodsGiftDomain> goodsGiftDomainPageInfo = goodsGiftService.page(goodsGiftDomain, 1, 4);
        model.addAttribute("goodsGiftList", goodsGiftDomainPageInfo);

        //获取视频列表
        VideoDomain videoDomain  =  new VideoDomain();
        videoDomain.setIsdel(false);
        List<VideoDomain> videoList =videoService.list(videoDomain);

        model.addAttribute("videoList",videoList);
        return "index";
    }


}