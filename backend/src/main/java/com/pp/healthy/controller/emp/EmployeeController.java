package com.pp.healthy.controller.emp;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.pp.base.common.RJResponse;
import com.pp.base.constant.WebConst;
import com.pp.base.controller.BaseController;
import com.pp.base.domain.BaseDomain;
import com.pp.base.enums.EnumBoolean;
import com.pp.base.enums.EnumONOFF;
import com.pp.base.enums.EnumSexType;
import com.pp.base.exception.BusinessException;
import com.pp.base.service.IRedisService;
import com.pp.base.utils.CodeRedisHelper;
import com.pp.employee.domain.AdminDeptDomain;
import com.pp.employee.domain.AdminEmployeeDomain;
import com.pp.employee.domain.AdminPostDomain;
import com.pp.employee.enums.EnumCardType;
import com.pp.employee.enums.EnumEmpStatus;
import com.pp.employee.service.IAdminDeptService;
import com.pp.employee.service.IAdminEmployeeService;
import com.pp.employee.service.IAdminPostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api("用户管理")
@Controller
@RequestMapping("/admin/emp")
@Slf4j
public class EmployeeController extends BaseController {

    @Autowired
    private IAdminEmployeeService employeeService;
    @Autowired
    private IRedisService redisService;

    @ApiOperation("员工列表")
    @GetMapping("")
    public String page(HttpServletRequest request, HttpServletResponse response,
                       @ApiParam(name = "name", value = "员工姓名", required = false)
                       @RequestParam(name = "name", required = false)
                               String name,
                       @ApiParam(name = "page", value = "页数", required = false)
                       @RequestParam(name = "page", required = false, defaultValue = "1")
                               Integer page,
                       @ApiParam(name = "limit", value = "每页数量", required = false)
                       @RequestParam(name = "limit", required = false, defaultValue = "20")
                               Integer limit){

        AdminEmployeeDomain domain = new AdminEmployeeDomain();
        domain.setName(name);

        if(!WebConst.SUPER_COMPANY_ID.equals(BaseDomain.getCompanyCode())) {
            domain.setCompanyId(BaseDomain.getCompanyCode());
        }

        PageInfo<AdminEmployeeDomain> pageInfo = employeeService.page(domain, page, limit);
        request.setAttribute("page", pageInfo);
        return "admin/emp/list";
    }


    @ApiOperation("员工列表查询JSON")
    @RequestMapping("list")
    @ResponseBody
    public RJResponse list(Model model,
                           @ApiParam(name = "id", value = "员工编码", required = false)
                           @RequestParam(name = "id", required = false) String id,
                           @ApiParam(name = "name", value = "用户名", required = false)
                           @RequestParam(name = "name", required = false) String name,
                           @ApiParam(name = "mobile", value = "用户昵称", required = false)
                           @RequestParam(name = "mobile", required = false) String mobile,
                           @ApiParam(name = "account", value = "用户真实姓名", required = false)
                           @RequestParam(name = "account", required = false) String account,
                           @ApiParam(name = "page", value = "页数", required = false)
                           @RequestParam(name = "page", required = false, defaultValue = "1")
                                   Integer page,
                           @ApiParam(name = "limit", value = "每页数量", required = false)
                           @RequestParam(name = "limit", required = false, defaultValue = "20")
                                   Integer limit
    ){
        try{
            AdminEmployeeDomain domain = new AdminEmployeeDomain();
            if (StringUtils.isNotBlank(id)){
                domain.setId(id);
//            model.addAttribute("id", id);
            }
            if(StringUtils.isNotBlank(name)){
                domain.setName(name);
//            model.addAttribute("name", name);
            }
            if(StringUtils.isNotBlank(mobile)){
                domain.setMobile(mobile);
//            model.addAttribute("mobile", mobile);
            }
            if(StringUtils.isNotBlank(account)){
                domain.setAccount(account);
//            model.addAttribute("account", account);
            }

            domain.setHasDeleted(EnumBoolean.FALSE.getVal());

//            if(!WebConst.SUPER_COMPANY_ID.equals(BaseDomain.getCompanyCode())) {
            domain.setCompanyId(BaseDomain.getCompanyCode());
//            }

            PageInfo pageInfo = employeeService.page(domain, page, limit);
            return RJResponse.success(pageInfo);
        }catch (Exception e){
            String msg = "员工列表查询JSON失败";
            log.error(e.getMessage());
            if(e instanceof BusinessException){
                msg = StringUtils.isNotBlank(e.getMessage()) ? e.getMessage() : msg;
            }else{
                log.error(msg, e);
            }
            return RJResponse.fail(msg);
        }
    }

    @ApiOperation("增加员工页面")
    @GetMapping("add")
    public String add(Model model) {
        model.addAttribute("emp", new AdminEmployeeDomain());
        this.toAdd(model);
        return "admin/emp/add";
    }

    @ApiOperation("保存员工信息")
    @PostMapping("add")
//    @ResponseBody
    public String add(Model model,
                      @ApiParam(name = "emp", value = "员工对象", required = true)
                      @ModelAttribute(name = "emp") AdminEmployeeDomain emp
    ){
        try{
            employeeService.register(emp);
        }catch (Exception e){
            String msg = "添加人员失败";
            log.error(e.getMessage());
            if(e instanceof BusinessException){
                msg = e.getMessage();
            }else{
                log.error(msg, e);
            }
            model.addAttribute("emp", emp);
            return "admin/emp/add";
            //return RJResponse.fail(msg);
        }
        return "redirect:/admin/emp/list";
        //return RJResponse.success();
        // TODO test response.sendRedirect("/admin/emp/list");
    }

    @ApiOperation("保存员工信息")
    @PostMapping("save")
    @ResponseBody
    public RJResponse save(Model model,
                           @ApiParam(name = "emp", value = "员工对象", required = true)
                           @ModelAttribute(name = "emp") AdminEmployeeDomain emp,
                           HttpServletRequest request
    ){
        String msg = "添加人员成功";
        try{
            employeeService.register(emp);

            AdminEmployeeDomain temp = employeeService.get(emp.getId());
            redisService.set(CodeRedisHelper.emp_info+emp.getId(), JSONObject.toJSONString(temp));
        }catch (Exception e){
            msg = "添加人员失败";
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

    @ApiOperation("人员编辑页")
    @GetMapping(value = "/{id}")
    public String edit( @ApiParam(name = "id", value = "人员编号", required = true) @PathVariable String id,
                        Model model){
        AdminEmployeeDomain emp = employeeService.getFromRedis(id);
        model.addAttribute("emp", emp);
        this.toAdd(model);
        return "admin/emp/add";
    }

    @ApiOperation("查询角色下人员")
    @GetMapping(value = "/role/{id}")
    public String role( @ApiParam(name = "id", value = "角色Id", required = true) @PathVariable String id,
                        Model model){
        if(StringUtils.isBlank(id)){
            return "admin/emp/user_role";
        }
        model.addAttribute("roleId", id);
        model.addAttribute("page", employeeService.getByRoleId(id));
        return "admin/emp/user_role";
    }

    @ApiOperation("人员选择页")
    @GetMapping(value = "choose_user_list")
    public String choose_user_list(Model model,
                                   @ApiParam(name = "name", value = "员工姓名", required = false)
                                   @RequestParam(name = "name", required = false)
                                           String name,
                                   @ApiParam(name = "postId", value = "岗位", required = false)
                                   @RequestParam(name = "postId", required = false)
                                           String postId,
                                   @ApiParam(name = "postName", value = "岗位", required = false)
                                   @RequestParam(name = "postName", required = false)
                                           String postName,
                                   @ApiParam(name = "page", value = "页数", required = false)
                                   @RequestParam(name = "page", required = false, defaultValue = "1")
                                           Integer page,
                                   @ApiParam(name = "limit", value = "每页数量", required = false)
                                   @RequestParam(name = "limit", required = false, defaultValue = "20")
                                           Integer limit){

        AdminEmployeeDomain domain = new AdminEmployeeDomain();

        if(StringUtils.isNotBlank(name)){
            domain.setName(name);
            model.addAttribute("name", name);
        }
        if(StringUtils.isNotBlank(postId)){
            domain.setPostId(postId);
            model.addAttribute("postId", postId);
        }else{
            // 查询postName 岗位
            if(StringUtils.isNotBlank(postName)){
                AdminPostDomain postDomain = new AdminPostDomain();
                postDomain.setCompanyId(BaseDomain.getCompanyCode());
                postDomain.setName(postName);
                List<AdminPostDomain> list = postService.get(postDomain);
                if(!CollectionUtils.isEmpty(list)){
                    domain.setPostId(list.get(0).getId());
                    model.addAttribute("postId", list.get(0).getId());
                }
            }
        }

        if(!WebConst.SUPER_COMPANY_ID.equals(BaseDomain.getCompanyCode())) {
            domain.setCompanyId(BaseDomain.getCompanyCode());
        }




        PageInfo<AdminEmployeeDomain> pageInfo = employeeService.page(domain, page, limit);
        model.addAttribute("page", pageInfo);

        AdminPostDomain adminPostDomain = new AdminPostDomain();
        adminPostDomain.setHasDeleted(Boolean.FALSE);
        adminPostDomain.setStatus(EnumONOFF.ON.getCode());
        adminPostDomain.setCompanyId(BaseDomain.getCompanyCode());
        if(!WebConst.SUPER_COMPANY_ID.equals(BaseDomain.getCompanyCode())) {
            adminPostDomain.setCompanyId(BaseDomain.getCompanyCode());
        }
        if(!WebConst.SUPER_COMPANY_ID.equals(BaseDomain.getCompanyCode())) {
            adminPostDomain.setCompanyId(BaseDomain.getCompanyCode());
        }
        model.addAttribute("postList", postService.get(adminPostDomain));

        return "admin/emp/choose_user_list";
    }

    @Autowired
    private IAdminPostService postService;
    @Autowired
    private IAdminDeptService deptService;

    private void toAdd(Model model) {
        model.addAttribute("sexList", EnumSexType.values());
        model.addAttribute("cardTypeList", EnumCardType.values());
        model.addAttribute("statusList", EnumEmpStatus.values());
        model.addAttribute("ONOFFList", EnumONOFF.values());

        AdminPostDomain post = new AdminPostDomain();
        post.setHasDeleted(Boolean.FALSE);
        post.setStatus(EnumONOFF.ON.getCode());
//        if(!WebConst.SUPER_COMPANY_ID.equals(BaseDomain.getCompanyCode())) {
        post.setCompanyId(BaseDomain.getCompanyCode());
//        }
        model.addAttribute("postList", postService.get(post));

        AdminDeptDomain dept = new AdminDeptDomain();
        dept.setHasDeleted(Boolean.FALSE);
        if(!WebConst.SUPER_COMPANY_ID.equals(BaseDomain.getCompanyCode())) {
            dept.setCompanyId(BaseDomain.getCompanyCode());
        }
        model.addAttribute("deptList", deptService.get(dept));
    }

}
