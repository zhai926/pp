package com.pp.employee.mapper;

import com.pp.employee.domain.AdminDeptDomain;
import com.pp.employee.domain.AdminDeptDomainExample;

import java.util.List;

public interface AdminDeptDomainMapper {
    int countByExample(AdminDeptDomainExample example);

    int deleteByPrimaryKey(String id);

    int insert(AdminDeptDomain record);

    int insertSelective(AdminDeptDomain record);

    List<AdminDeptDomain> selectByExample(AdminDeptDomainExample example);

    AdminDeptDomain selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AdminDeptDomain record);

    int updateByPrimaryKey(AdminDeptDomain record);
}