package com.pp.employee.service;

import com.pp.employee.domain.AdminRoleMenuDomain;

import java.util.List;

public interface IRoleMenuSerivce {

    int save(String roleId, List<String> menuId);

    int delete(String id);

    int delete(List<String> ids);

    int delete(AdminRoleMenuDomain domain);

    int deleteByRoleId(String roleId);

    int deleteByMenuId(String menuId);

    List<AdminRoleMenuDomain> getByRoleId(String roleId);

    List<AdminRoleMenuDomain> getByMenuId(String menuId);

    List<AdminRoleMenuDomain> get(String roleId, String menuId);

    List<AdminRoleMenuDomain> get(AdminRoleMenuDomain domain);
}
