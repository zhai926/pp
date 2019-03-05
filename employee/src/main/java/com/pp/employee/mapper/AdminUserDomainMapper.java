package com.pp.employee.mapper;

import com.pp.employee.domain.AdminUserDomain;
import com.pp.employee.domain.AdminUserDomainExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminUserDomainMapper {
    int countByExample(AdminUserDomainExample example);

    int deleteByPrimaryKey(String id);

    int insert(AdminUserDomain record);

    int insertSelective(AdminUserDomain record);

    List<AdminUserDomain> selectByExample(AdminUserDomainExample example);

    AdminUserDomain selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AdminUserDomain record);

    int updateByPrimaryKey(AdminUserDomain record);

    /**
     * 根据用户名和密码获取用户信息
     * @param username
     * @param password
     * @return
     */
    AdminUserDomain getUserInfoByCond(@Param("username") String username, @Param("password") String password);

}