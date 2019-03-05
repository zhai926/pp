package com.pp.employee.mapper;

import com.pp.employee.domain.AdminRoleMenuDomainExample;
import com.pp.employee.domain.AdminRoleMenuDomain;

import java.util.List;

public interface AdminRoleMenuDomainMapper {
    int countByExample(AdminRoleMenuDomainExample example);

    int deleteByPrimaryKey(String id);

    int insert(AdminRoleMenuDomain record);

    int insertSelective(AdminRoleMenuDomain record);

    List<AdminRoleMenuDomain> selectByExample(AdminRoleMenuDomainExample example);

    AdminRoleMenuDomain selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AdminRoleMenuDomain record);

    int updateByPrimaryKey(AdminRoleMenuDomain record);
}