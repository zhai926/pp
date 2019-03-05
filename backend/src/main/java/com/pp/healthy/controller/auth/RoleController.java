package com.pp.healthy.controller.auth;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.pp.base.common.RJResponse;
import com.pp.base.constant.ErrorConstant;
import com.pp.base.controller.BaseController;
import com.pp.base.domain.BaseDomain;
import com.pp.base.exception.BusinessException;
import com.pp.base.utils.Node;
import com.pp.employee.domain.AdminMenuDomain;
import com.pp.employee.domain.AdminRoleDomain;
import com.pp.employee.dto.SpecialAuthDto;
import com.pp.employee.service.IAdminAuthService;
import com.pp.employee.service.IMenuService;
import com.pp.employee.service.IRoleService;
import com.pp.employee.service.IUserRoleService;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//@Api("角色管理")
@Slf4j
@Controller
@RequestMapping("admin/role")
public class RoleController extends BaseController {

    @Autowired
    private IRoleService roleService;
    @Autowired
    private IMenuService menuService;
    @Autowired
    private IUserRoleService userRoleService;
    @Autowired
    private IAdminAuthService adminAuthService;

    @ApiOperation("")
    @GetMapping("")
    public String page(Model model,
                       @ApiParam(name = "name", value = "名称", required = false)
                       @RequestParam(name = "name", required = false) String name,
                       @ApiParam(name = "page", value = "页数", required = false)
                       @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
                       @ApiParam(name = "limit", value = "每页数量", required = false)
                       @RequestParam(name = "limit", required = false, defaultValue = "20") Integer limit){

        AdminRoleDomain domain = new AdminRoleDomain();
        if(StringUtils.isNotBlank(name)){
            domain.setName(name);
            model.addAttribute("name", name);
        }

        domain.setCompanyId(BaseDomain.getCompanyCode());
//        PageInfo<AdminRoleDomain> pageInfo = roleService.page(domain, page, limit);
//
//        model.addAttribute("page", pageInfo);

        return "admin/role/list";
    }

    @ApiOperation("")
    @GetMapping("list")
    @ResponseBody
    public JSONObject list(@ApiParam(name = "name", value = "名称", required = false)
                           @RequestParam(name = "name", required = false) String name,
                           @ApiParam(name = "page", value = "页数", required = false)
                           @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
                           @ApiParam(name = "limit", value = "每页数量", required = false)
                           @RequestParam(name = "limit", required = false, defaultValue = "20") Integer limit){

        AdminRoleDomain domain = new AdminRoleDomain();
        if(StringUtils.isNotBlank(name)){
            domain.setName(name);
        }

        domain.setCompanyId(BaseDomain.getCompanyCode());

        PageInfo<AdminRoleDomain> pageInfo = roleService.page(domain, page, limit);
        JSONObject jsonObject= new JSONObject();
        jsonObject.put("total", pageInfo.getTotal());
        jsonObject.put("rows", pageInfo.getList());
        return jsonObject;
    }

    @ApiOperation("")
    @GetMapping("add")
    public String add(Model model){

        toAdd(model);

        model.addAttribute("domain", new AdminRoleDomain());

        return "admin/role/add";
    }

    @ApiOperation("edit role page")
    @GetMapping("{id}")
    public String list(Model model, @ApiParam(name = "id", value = "角色编码", required = true) @PathVariable String id){
        AdminRoleDomain domain = roleService.get(id);
        // 保存menuids menunames
        List<AdminMenuDomain> menuDomainList = menuService.getByRoleId(id);

        if(!CollectionUtils.isEmpty(menuDomainList)){
            List<String> menuIds = new ArrayList<>();
            List<String> menuNames = new ArrayList<>();
            for(AdminMenuDomain menu: menuDomainList){
                menuIds.add(menu.getId());
                menuNames.add(menu.getName());
            }
            domain.setMenuIds(StringUtils.join(menuIds.toArray(), ","));
            domain.setMenuNames(StringUtils.join(menuNames.toArray(), ","));
        }
        toAdd(model);

        model.addAttribute("domain", domain);

        return "admin/role/add";
    }

    @ApiOperation("保存角色")
    @PostMapping("save")
    @ResponseBody
    public RJResponse save(@ApiParam(name = "domain", value = "角色对象", required = true) @ModelAttribute(name = "domain")AdminRoleDomain domain){
        String msg = "角色保存成功";
        try{
            if(StringUtils.isBlank(domain.getName()) || StringUtils.isBlank(domain.getMenuIds()) || StringUtils.isBlank(domain.getMenuNames())){
                throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
            }

            roleService.save(domain);

            return RJResponse.success(msg);
        }catch (Exception e){
            log.error(e.getMessage());
            msg = exceptionMsg(e, "保存角色失败");
            return RJResponse.fail(msg);
        }
    }

    @ApiOperation("删除角色")
    @PostMapping("delete")
    @ResponseBody
    public RJResponse delete(@ApiParam(name = "ids", value = "ids", required = true)String ids){
        try{
            if(StringUtils.isBlank(ids)){
                throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
            }
            int i = roleService.delete(Arrays.asList(ids.split(",")));
            return RJResponse.success("共删除" + i + "条数据");
        }catch (Exception e){
            return RJResponse.fail(exceptionMsg(e, "数据删除失败"));
        }
    }

    @ApiOperation("设置人员角色")
    @PostMapping("set_user_role")
    @ResponseBody
    public RJResponse setUserRole(@ApiParam(name = "roleId", value = "roleId", required = true)String roleId,
                                  @ApiParam(name = "empIds", value = "empIds", required = true)String empIds){
        try{
            if(StringUtils.isBlank(roleId) || StringUtils.isBlank(empIds)){
                throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
            }

            userRoleService.save(new ArrayList<>(Arrays.asList(empIds.split(","))), roleId);

            return RJResponse.success("权限设置成功");
        }catch (Exception e){
            return RJResponse.fail(exceptionMsg(e, "设置权限失败"));
        }
    }

    private void toAdd(Model model){
        // 判断特殊权限
        String empCode = BaseDomain.getEmpCode();
        SpecialAuthDto auth = adminAuthService.getSpecialAuth(empCode);
        String empId = null;
        if(!auth.isMenuAll()){
            empId = BaseDomain.getEmpCode();
        }

        List<Node> nodes = menuService.getNodeTree("0", empId);
        model.addAttribute("nodes", nodes);
    }

}
