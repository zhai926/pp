package com.pp.employee.mapper;

import com.pp.employee.domain.AdminMenuDomain;
import com.pp.employee.domain.AdminMenuDomainExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminMenuDomainMapper {
    int countByExample(AdminMenuDomainExample example);

    int deleteByPrimaryKey(String id);

    int insert(AdminMenuDomain record);

    int insertSelective(AdminMenuDomain record);

    List<AdminMenuDomain> selectByExample(AdminMenuDomainExample example);

    AdminMenuDomain selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AdminMenuDomain record);

    int updateByPrimaryKey(AdminMenuDomain record);

    List<AdminMenuDomain> selectByRoleId(String roleId);

    List<AdminMenuDomain> selectByEmpId(@Param("userId") String empId, @Param("type") String type);

    List<AdminMenuDomain> selectButtons(@Param("url") String url, @Param("empId") String empId);
}