package com.pp.employee.mapper;

import com.pp.employee.domain.AdminPostDomain;
import com.pp.employee.domain.AdminPostDomainExample;

import java.util.List;

public interface AdminPostDomainMapper {
    int deleteByPrimaryKey(String id);

    int insert(AdminPostDomain record);

    int insertSelective(AdminPostDomain record);

    List<AdminPostDomain> selectByExample(AdminPostDomainExample example);

    AdminPostDomain selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AdminPostDomain record);

    int updateByPrimaryKey(AdminPostDomain record);
}