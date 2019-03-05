package com.pp.employee.service;

import com.pp.base.utils.Node;
import com.pp.employee.domain.AdminMenuDomain;

import java.util.List;

public interface IMenuService {

    int save(AdminMenuDomain domain);

    int delete(List<String> ids);

    AdminMenuDomain get(String id);

    List<AdminMenuDomain> get(AdminMenuDomain domain);

    List<AdminMenuDomain> getByRoleId(String roleId);

    List<AdminMenuDomain> getByEmpId(String empId);

    AdminMenuDomain getByUrl(String url);

    List<AdminMenuDomain> getButtons(String url, String empId);

    List<AdminMenuDomain> getDown(String upId);


    List<Node> getNodeTree(String pid, String empId);
}
