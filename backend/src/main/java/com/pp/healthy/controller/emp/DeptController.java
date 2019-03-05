package com.pp.healthy.controller.emp;

import com.github.pagehelper.PageInfo;
import com.pp.base.common.RJResponse;
import com.pp.base.constant.WebConst;
import com.pp.base.controller.BaseController;
import com.pp.base.domain.BaseDomain;
import com.pp.base.exception.BusinessException;
import com.pp.employee.domain.AdminDeptDomain;
import com.pp.employee.domain.AdminEmployeeDomain;
import com.pp.employee.service.IAdminDeptService;
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


//@Api("部门管理")
@Controller
@RequestMapping("admin/dept")
@Slf4j
public class DeptController extends BaseController {

    @Autowired
    private IAdminDeptService deptService;
    @Autowired
    private IAdminEmployeeService employeeService;

    @ApiOperation("部门列表")
    @GetMapping("")
    public String page(Model model,
                       @ApiParam(name = "name", value = "部门名称", required = false)
                       @RequestParam(name = "name", required = false) String name,
                       @ApiParam(name = "page", value = "当前页数", required = false)
                       @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
                       @ApiParam(name = "limit", value = "每页数量", required = false)
                       @RequestParam(name = "limit", required = false, defaultValue = "20") Integer limit
                       ){
        AdminDeptDomain domain = new AdminDeptDomain();
        domain.setName(name);

        if(!WebConst.SUPER_COMPANY_ID.equals(BaseDomain.getCompanyCode())) {
            domain.setCompanyId(BaseDomain.getCompanyCode());
        }

        PageInfo<AdminDeptDomain> pageInfo = deptService.page(domain, page, limit);
        model.addAttribute("page", pageInfo);
        return "admin/dept/list";
    }

    @ApiOperation("增加部门页面")
    @GetMapping("add")
    public String add(Model model) {
        model.addAttribute("dept", new AdminDeptDomain());
        this.toAdd(model);
        return "admin/dept/add";
    }

    @ApiOperation("保存部门信息")
    @PostMapping("save")
    @ResponseBody
    public RJResponse save(@ApiParam(name = "dept", value = "部门对象", required = true)
                           @ModelAttribute(name = "dept") AdminDeptDomain dept
    ){
        String msg = "添加部门成功";
        try{
            deptService.save(dept);
        }catch (Exception e){
            msg = "添加部门失败";
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

    @ApiOperation("部门编辑页")
    @GetMapping(value = "/{id}")
    public String edit( @ApiParam(name = "id", value = "部门编号", required = true) @PathVariable String id,
                       Model model){
        AdminDeptDomain dept = deptService.get(id);
        model.addAttribute("dept", dept);
        this.toAdd(model);
        return "admin/dept/add";
    }

    private void toAdd(Model model){
        AdminDeptDomain params = new AdminDeptDomain();
        params.setHasDeleted(Boolean.FALSE);
        if(!WebConst.SUPER_COMPANY_ID.equals(BaseDomain.getCompanyCode())) {
            params.setCompanyId(BaseDomain.getCompanyCode());
        }
        model.addAttribute("deptList", deptService.get(params));

        AdminEmployeeDomain emp = new AdminEmployeeDomain();
        emp.setHasDeleted(Boolean.FALSE);
        if(!WebConst.SUPER_COMPANY_ID.equals(BaseDomain.getCompanyCode())) {
            emp.setCompanyId(BaseDomain.getCompanyCode());
        }
        model.addAttribute("empList", employeeService.get(emp));
    }

}
