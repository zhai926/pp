package com.pp.employee.service;

import com.pp.employee.domain.AdminUserDomain;

public interface IAdminUserService {

    int saveUser(AdminUserDomain user);

    /**
     * @Description: 更改用户信息
     * @param user
     */
    int updateUserInfo(AdminUserDomain user);

    /**
     * @Description: 根据主键编号获取用户信息
     * @param id 主键
     */
    AdminUserDomain getUserInfoById(String id);

    AdminUserDomain getUserInfoByAccount(String username);

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return
     */
    AdminUserDomain login(String username, String password);

    AdminUserDomain login(String username);

}
