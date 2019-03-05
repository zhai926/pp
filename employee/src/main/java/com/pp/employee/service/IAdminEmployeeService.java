package com.pp.employee.service;

import com.github.pagehelper.PageInfo;
import com.pp.employee.domain.AdminEmployeeDomain;

import java.util.List;
import java.util.Map;

public interface IAdminEmployeeService {

    /**
     * 保存user
     * @param employeeDomain
     * @return
     */
    int save(AdminEmployeeDomain employeeDomain);

    /**
     * 创建emp 创建user
     * 校验 mobile account cardNum 是否已存在
     * @param employeeDomain
     * @return
     */
    int register(AdminEmployeeDomain employeeDomain);

    AdminEmployeeDomain get(String id);

    List<AdminEmployeeDomain> getByRoleId(String roleId);

    AdminEmployeeDomain getFromRedis(String id);
    /**
     * 查询一个 emp user
     * @param emp
     * @return
     */
    AdminEmployeeDomain getOne(AdminEmployeeDomain emp);

    List<AdminEmployeeDomain> get(AdminEmployeeDomain emp);

    /**
     * 分页查询emp user
     * @param map
     * @param page
     * @param limit
     * @return
     */
    PageInfo<AdminEmployeeDomain> page(Map map, Integer page, Integer limit);

    PageInfo<AdminEmployeeDomain> page(AdminEmployeeDomain domain, Integer page, Integer limit);
}
