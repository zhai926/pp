package com.pp.employee.mapper;

import com.pp.employee.domain.AdminEmployeeDomain;
import com.pp.employee.domain.AdminEmployeeDomainExample;

import java.util.List;
import java.util.Map;

public interface AdminEmployeeDomainMapper {
    int countByExample(AdminEmployeeDomainExample example);

    int deleteByPrimaryKey(String id);

    int insert(AdminEmployeeDomain record);

    int insertSelective(AdminEmployeeDomain record);

    List<AdminEmployeeDomain> selectByExample(AdminEmployeeDomainExample example);

    AdminEmployeeDomain selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AdminEmployeeDomain record);

    int updateByPrimaryKey(AdminEmployeeDomain record);

    List<AdminEmployeeDomain> selectByMap(Map map);

    List<AdminEmployeeDomain> selectByRoleId(String roleId);
}