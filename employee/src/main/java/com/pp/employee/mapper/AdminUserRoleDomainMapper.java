package com.pp.employee.mapper;

import com.pp.employee.domain.AdminUserRoleDomain;
import com.pp.employee.domain.AdminUserRoleDomainExample;

import java.util.List;

public interface AdminUserRoleDomainMapper {
    int countByExample(AdminUserRoleDomainExample example);

    int deleteByPrimaryKey(String id);

    int insert(AdminUserRoleDomain record);

    int insertSelective(AdminUserRoleDomain record);

    List<AdminUserRoleDomain> selectByExample(AdminUserRoleDomainExample example);

    AdminUserRoleDomain selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AdminUserRoleDomain record);

    int updateByPrimaryKey(AdminUserRoleDomain record);
}