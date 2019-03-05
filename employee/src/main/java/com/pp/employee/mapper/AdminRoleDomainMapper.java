package com.pp.employee.mapper;

import com.pp.employee.domain.AdminRoleDomainExample;
import com.pp.employee.domain.AdminRoleDomain;

import java.util.List;

public interface AdminRoleDomainMapper {
    int countByExample(AdminRoleDomainExample example);

    int deleteByPrimaryKey(String id);

    int insert(AdminRoleDomain record);

    int insertSelective(AdminRoleDomain record);

    List<AdminRoleDomain> selectByExample(AdminRoleDomainExample example);

    AdminRoleDomain selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AdminRoleDomain record);

    int updateByPrimaryKey(AdminRoleDomain record);
}