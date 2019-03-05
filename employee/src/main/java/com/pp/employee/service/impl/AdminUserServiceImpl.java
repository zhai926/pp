package com.pp.employee.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.pp.base.constant.ErrorConstant;
import com.pp.base.exception.BusinessException;
import com.pp.base.service.ICodeGenerateService;
import com.pp.base.service.IRedisService;
import com.pp.base.utils.CodeRedisHelper;
import com.pp.base.utils.TaleUtils;
import com.pp.employee.domain.AdminUserDomain;
import com.pp.employee.mapper.AdminUserDomainMapper;
import com.pp.employee.service.IAdminUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Service
public class AdminUserServiceImpl implements IAdminUserService {

    @Autowired
    private AdminUserDomainMapper userDomainMapper;
    @Autowired
    private ICodeGenerateService codeGenerateService;
    @Autowired
    private IRedisService redisService;

    @Transactional
    @Override
    public int saveUser(AdminUserDomain user) {
        if(StringUtils.isBlank(user.getId())){
            user.setId(codeGenerateService.makeUserCode());
            user.setHasDeleted(Boolean.FALSE);
            user.insertOperation();
            return userDomainMapper.insertSelective(user);
        }else{
            user.updateOperation();
            return userDomainMapper.updateByPrimaryKeySelective(user);
        }
    }

    @Transactional
    @Override
    public int updateUserInfo(AdminUserDomain user) {
        if (StringUtils.isBlank(user.getId())){
            throw BusinessException.withErrorCode("用户编号不可能为空");
        }
        return userDomainMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public AdminUserDomain getUserInfoById(String id) {
        return userDomainMapper.selectByPrimaryKey(id);
    }

    @Override
    public AdminUserDomain getUserInfoByAccount(String username) {
        if(StringUtils.isBlank(username)){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        return userDomainMapper.getUserInfoByCond(username, null);
    }

    @Override
    public AdminUserDomain login(String username, String password) {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)){
            throw BusinessException.withErrorCode(ErrorConstant.Auth.USERNAME_PASSWORD_IS_EMPTY);
        }

        String pwd = TaleUtils.MD5encode(password);
        AdminUserDomain user = userDomainMapper.getUserInfoByCond(username, pwd);
        if (null == user){
            throw BusinessException.withErrorCode(ErrorConstant.Auth.USERNAME_PASSWORD_ERROR);
        }
        saveRedis(user);
        return user;
    }

    @Override
    public AdminUserDomain login(String username) {
        if(StringUtils.isBlank(username)){
            throw BusinessException.withErrorCode(ErrorConstant.Auth.USERNAME_PASSWORD_ERROR);
        }
        AdminUserDomain user = userDomainMapper.getUserInfoByCond(username, null);
        if(null == user){
            throw BusinessException.withErrorCode(ErrorConstant.Auth.USERNAME_PASSWORD_ERROR);
        }

        


        saveRedis(user);
        return user;
    }

    void saveRedis(AdminUserDomain user){
        // 保存redis 12 h
        redisService.set(CodeRedisHelper.login_info+user.getId(), JSONObject.toJSONString(user), 12L, TimeUnit.HOURS);
    }

}
