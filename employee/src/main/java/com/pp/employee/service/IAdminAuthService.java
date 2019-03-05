package com.pp.employee.service;

import com.github.pagehelper.PageInfo;
import com.pp.employee.domain.AdminAuthDomain;
import com.pp.employee.dto.SpecialAuthDto;

import java.util.List;

public interface IAdminAuthService {

    int save(AdminAuthDomain domain);

    void saveRedis(AdminAuthDomain domain);

    void delRedis(String empId);

    AdminAuthDomain get(String id);

    AdminAuthDomain getByEmpId(String empId);

    List<AdminAuthDomain> get(AdminAuthDomain domain);

    PageInfo<AdminAuthDomain> page(AdminAuthDomain domain, Integer start, Integer pageSize);

    SpecialAuthDto getSpecialAuth(String empId);
}
