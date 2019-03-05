package com.pp.employee.mapper;

import com.pp.employee.domain.AdminAuthDomainExample;
import com.pp.employee.domain.AdminAuthDomain;

import java.util.List;

public interface AdminAuthDomainMapper {
    int countByExample(AdminAuthDomainExample example);

    int deleteByPrimaryKey(String id);

    int insert(AdminAuthDomain record);

    int insertSelective(AdminAuthDomain record);

    List<AdminAuthDomain> selectByExample(AdminAuthDomainExample example);

    AdminAuthDomain selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AdminAuthDomain record);

    int updateByPrimaryKey(AdminAuthDomain record);
}