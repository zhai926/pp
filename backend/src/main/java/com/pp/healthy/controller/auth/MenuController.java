package com.pp.healthy.controller.auth;

import com.pp.base.common.RJResponse;
import com.pp.base.constant.ErrorConstant;
import com.pp.base.controller.BaseController;
import com.pp.base.enums.EnumBoolean;
import com.pp.base.enums.EnumONOFF;
import com.pp.base.exception.BusinessException;
import com.pp.base.utils.Node;
import com.pp.employee.domain.AdminMenuDomain;
import com.pp.employee.enums.EnumMenuType;
import com.pp.employee.service.IMenuService;
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

import java.util.Arrays;
import java.util.List;

//@Api("菜单管理")
@Slf4j
@Controller
@RequestMapping("admin/menu")
public class MenuController extends BaseController {

    @Autowired
    private IMenuService menuService;

    @ApiOperation("menu")
    @GetMapping("")
    public String page(Model model){

        List<Node> nodes = menuService.getNodeTree("0", null);

        model.addAttribute("nodes", nodes);

        return "admin/menu/list";
    }

    @ApiOperation("menu tree json")
    @GetMapping("list")
    @ResponseBody
    public RJResponse list(Model model){
        try{
            List<Node> nodes = menuService.getNodeTree("0", null);

            return RJResponse.success(nodes);
        }catch (Exception e){
            return RJResponse.fail(exceptionMsg(e, "获取数据失败"));
        }
    }

    @ApiOperation("menu tree json")
    @GetMapping("detail/{id}")
    @ResponseBody
    public RJResponse list(Model model, @ApiParam(name = "id", value = "菜单编码", required = true) @PathVariable String id){
        try{
            AdminMenuDomain domain = menuService.get(id);
            AdminMenuDomain parent = menuService.get(domain.getParentId());
            if(null != parent){
                domain.setParentId(parent.getId());
                domain.setParentName(parent.getName());
            }
            domain.setTypeName(EnumMenuType.getDesc(domain.getType()));

            return RJResponse.success(domain);
        }catch (Exception e){
            return RJResponse.fail(exceptionMsg(e, "获取数据失败"));
        }
    }


    @GetMapping("add")
    public String add(Model model,
                      @ApiParam(name = "pNodeId", value = "上级节点", required = false)
    @RequestParam(name = "pNodeId", required = false) String pNodeId){

        toAdd(model);

        AdminMenuDomain parent = menuService.get(pNodeId);
        AdminMenuDomain domain = new AdminMenuDomain();
        domain.setParentId(parent.getId());
        domain.setParentName(parent.getName());
        model.addAttribute("domain", domain);

        return "admin/menu/add";
    }

    @ApiOperation("保存菜单")
    @PostMapping("save")
    @ResponseBody
    public RJResponse save(@ApiParam(name = "domain", value = "菜单对象", required = true) @ModelAttribute(name = "domain")AdminMenuDomain domain){
        String msg = "菜单保存成功";
        try{

            domain.setHasDeleted(EnumBoolean.FALSE.getVal());
            if(EnumMenuType.FOLDER.getCode().equals(domain.getType())){
                domain.setHasMenu(EnumBoolean.FALSE.getVal());
            }else{
                domain.setHasMenu(EnumBoolean.TRUE.getVal());
            }
            if(!EnumMenuType.MENU.getCode().equals(domain.getType())){
                domain.setUrl(null);
            }

            menuService.save(domain);

            return RJResponse.success(msg);
        }catch (Exception e){
            log.error(e.getMessage());
            msg = exceptionMsg(e, "保存菜单失败");
            return RJResponse.fail(msg);
        }
    }

    @ApiOperation("删除菜单")
    @PostMapping("delete")
    @ResponseBody
    public RJResponse delete(@ApiParam(name = "ids", value = "ids", required = true)String ids){
        try{
            if(StringUtils.isBlank(ids)){
                throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
            }
            int i = menuService.delete(Arrays.asList(ids.split(",")));
            return RJResponse.success("共删除" + i + "条数据");
        }catch (Exception e){
            return RJResponse.fail(exceptionMsg(e, "数据删除失败"));
        }
    }

    private void toAdd(Model model){
        model.addAttribute("types", EnumMenuType.values());
        model.addAttribute("statuses", EnumONOFF.values());
    }

}
