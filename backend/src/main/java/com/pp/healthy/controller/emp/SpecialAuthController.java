package com.pp.healthy.controller.emp;

import com.github.pagehelper.PageInfo;
import com.pp.base.common.RJResponse;
import com.pp.base.exception.BusinessException;
import com.pp.employee.domain.AdminAuthDomain;
import com.pp.employee.domain.AdminEmployeeDomain;
import com.pp.employee.service.IAdminAuthService;
import com.pp.employee.service.IAdminEmployeeService;
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

//@Api("特殊权限管理")
@Controller
@RequestMapping("/admin/special_auth")
@Slf4j
public class SpecialAuthController {

    @Autowired
    private IAdminAuthService adminAuthService;
    @Autowired
    private IAdminEmployeeService employeeService;


    @ApiOperation("特殊权限分页列表")
    @GetMapping("")
    public String page(Model model,
                       @ApiParam(name = "id", value="编码", required = false)
                       @RequestParam(name = "id", required = false) String id,
                       @ApiParam(name = "empId", value="业务员编码", required = false)
                       @RequestParam(name = "empId", required = false) String empId,
                       @ApiParam(name = "empName", value="业务员姓名", required = false)
                       @RequestParam(name = "empName", required = false) String empName, @ApiParam(name = "page", value = "页数", required = false)
                       @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
                       @ApiParam(name = "limit", value = "每页数量", required = false)
                       @RequestParam(name = "limit", required = false, defaultValue = "20") Integer limit
    ){

        AdminAuthDomain domain = new AdminAuthDomain();
        if(StringUtils.isNotBlank(id)){
            domain.setId(id);
            model.addAttribute("id", id);
        }
        if(StringUtils.isNotBlank(empId)){
            domain.setEmpId(empId);
            model.addAttribute("empId", empId);
        }

// empName
        PageInfo<AdminAuthDomain> pageInfo = adminAuthService.page(domain, page, limit);
        model.addAttribute("page", pageInfo);
        return "admin/special_auth/list";
    }

    @ApiOperation("增加特殊权限页面")
    @GetMapping("add")
    public String add(Model model){
        model.addAttribute("auth", new AdminAuthDomain());
        return "admin/special_auth/add";
    }

    @ApiOperation("保存人员特殊权限")
    @PostMapping("save")
    @ResponseBody
    public RJResponse save(@ApiParam(name = "auth", value = "特殊权限对象", required = true) @ModelAttribute(name = "auth") AdminAuthDomain domain){
        String msg = "保存人员特殊权限成功";
        try{
            if(StringUtils.isBlank(domain.getEmpId())){
                return RJResponse.fail("请选择业务员");
            }

            // 判断是否有业务员
            AdminAuthDomain exist = adminAuthService.getByEmpId(domain.getEmpId());
            if(null != exist){
                if(StringUtils.isBlank(domain.getId()) || !domain.getId().equals(exist.getId())){
                    return RJResponse.fail("该业务员已添加特殊权限，请修改！");
                }
            }


            adminAuthService.save(domain);

            AdminAuthDomain temp = adminAuthService.get(domain.getId());
            AdminEmployeeDomain emp = employeeService.getFromRedis(temp.getEmpId());
            // 保存redis
            temp.setEmpName(emp.getName());
            adminAuthService.saveRedis(temp);
            return RJResponse.success(msg);
        }catch (Exception e){
            msg = "保存人员特殊权限失败";
            log.error(e.getMessage());
            if(e instanceof BusinessException){
                msg = StringUtils.isNotBlank(e.getMessage()) ? e.getMessage() : msg;
            }else{
                log.error(msg, e);
            }
            return RJResponse.fail(msg);
        }
    }


    @ApiOperation("特殊权限修改页面")
    @GetMapping("/{empId}")
    public String toAdd(Model model,
                        @ApiParam(name = "empId", value = "业务员编码")
                        @PathVariable("empId") String empId) {
        AdminAuthDomain domain = adminAuthService.getByEmpId(empId);
        model.addAttribute("auth", domain);
        return "admin/special_auth/add";
    }


}
