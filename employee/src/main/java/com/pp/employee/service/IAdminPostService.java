package com.pp.employee.service;

import com.github.pagehelper.PageInfo;
import com.pp.employee.domain.AdminPostDomain;

import java.util.List;

public interface IAdminPostService {

    int save(AdminPostDomain post);

    AdminPostDomain get(String id);

    List<AdminPostDomain> get(AdminPostDomain post);

    PageInfo<AdminPostDomain> page (AdminPostDomain post, Integer page, Integer pageSize);

}
