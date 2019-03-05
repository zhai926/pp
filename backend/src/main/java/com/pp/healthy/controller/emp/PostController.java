package com.pp.healthy.controller.emp;

import com.github.pagehelper.PageInfo;
import com.pp.base.common.RJResponse;
import com.pp.base.constant.WebConst;
import com.pp.base.controller.BaseController;
import com.pp.base.domain.BaseDomain;
import com.pp.base.enums.EnumONOFF;
import com.pp.base.exception.BusinessException;
import com.pp.employee.domain.AdminPostDomain;
import com.pp.employee.service.IAdminPostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@Api("岗位管理")
@Controller
@RequestMapping("/admin/post")
@Slf4j
public class PostController extends BaseController {


    @Autowired
    private IAdminPostService postService;

    @ApiOperation("岗位列表")
    @GetMapping("")
    public String page(HttpServletRequest request, HttpServletResponse response,
                       @ApiParam(name = "name", value = "岗位名称", required = false)
                       @RequestParam(name = "name", required = false)
                               String name,
                       @ApiParam(name = "page", value = "页数", required = false)
                       @RequestParam(name = "page", required = false, defaultValue = "1")
                               Integer page,
                       @ApiParam(name = "limit", value = "每页数量", required = false)
                       @RequestParam(name = "limit", required = false, defaultValue = "20")
                               Integer limit){

        AdminPostDomain domain  = new AdminPostDomain();
        domain.setName(name);

        if(!WebConst.SUPER_COMPANY_ID.equals(BaseDomain.getCompanyCode())) {
            domain.setCompanyId(BaseDomain.getCompanyCode());
        }

        PageInfo<AdminPostDomain> pageInfo = postService.page(domain, page, limit);
        request.setAttribute("page", pageInfo);
        return "admin/post/list";
    }

    @ApiOperation("增加岗位页面")
    @GetMapping("add")
    public String add(Model model) {
        model.addAttribute("post", new AdminPostDomain());
        this.toAdd(model);
        return "admin/post/add";
    }

    @ApiOperation("保存岗位信息")
    @PostMapping("save")
    @ResponseBody
    public RJResponse save(@ApiParam(name = "post", value = "岗位对象", required = true)
                           @ModelAttribute(name = "post") AdminPostDomain post
    ){
        String msg = "添加岗位成功";
        try{
            postService.save(post);
        }catch (Exception e){
            msg = "添加岗位失败";
            log.error(e.getMessage());
            if(e instanceof BusinessException){
                msg = StringUtils.isNotBlank(e.getMessage()) ? e.getMessage() : msg;
            }else{
                log.error(msg, e);
            }
            return RJResponse.fail(msg);
        }
        return RJResponse.success(msg);
    }

    @ApiOperation("岗位编辑页")
    @GetMapping(value = "/{id}")
    public String edit( @ApiParam(name = "id", value = "岗位编号", required = true) @PathVariable String id,
                        Model model){
        AdminPostDomain post = postService.get(id);
        model.addAttribute("post", post);
        this.toAdd(model);
        return "admin/post/add";
    }

    private void toAdd(Model model) {
        model.addAttribute("statusList", EnumONOFF.values());
    }


}
