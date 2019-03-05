package com.pp.employee.service;

import com.github.pagehelper.PageInfo;
import com.pp.employee.domain.AdminDeptDomain;

import java.util.List;

public interface IAdminDeptService {

    int save(AdminDeptDomain dept);

    AdminDeptDomain get(String id);

    List<AdminDeptDomain> get(AdminDeptDomain dept);

    PageInfo<AdminDeptDomain> page(AdminDeptDomain dept, Integer page, Integer pageSize);

}
