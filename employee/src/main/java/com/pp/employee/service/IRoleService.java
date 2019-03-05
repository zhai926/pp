package com.pp.employee.service;

import com.github.pagehelper.PageInfo;
import com.pp.employee.domain.AdminRoleDomain;

import java.util.List;

public interface IRoleService {

    int save(AdminRoleDomain domain);

    int delete(List<String> ids);

    AdminRoleDomain get(String id);

    List<AdminRoleDomain> get(AdminRoleDomain domain);

    PageInfo<AdminRoleDomain> page(AdminRoleDomain domain, Integer start, Integer pageSize);

}
