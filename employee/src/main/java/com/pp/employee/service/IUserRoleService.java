package com.pp.employee.service;

import com.pp.employee.domain.AdminUserRoleDomain;

import java.util.List;

public interface IUserRoleService {

    int  save(String userId, String roleId);

    int save(String userId, List<String> roleIds);

    int save(List<String> empIds, String roleId);

    int delete(AdminUserRoleDomain domain);

    int delete(List<String> ids);

    int deleteByUserId(String userId);

    int deleteByRoleId(String roleId);

    List<AdminUserRoleDomain> get(String userId, String roleId);

    List<AdminUserRoleDomain> get(AdminUserRoleDomain domain);

    List<AdminUserRoleDomain> getByUserId(String userId);

    List<AdminUserRoleDomain> getByRoleId(String roleId);

}
