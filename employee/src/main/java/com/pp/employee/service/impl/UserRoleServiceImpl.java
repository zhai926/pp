package com.pp.employee.service.impl;

import com.pp.base.constant.ErrorConstant;
import com.pp.base.enums.EnumBoolean;
import com.pp.base.exception.BusinessException;
import com.pp.base.service.ICodeGenerateService;
import com.pp.employee.domain.AdminUserRoleDomain;
import com.pp.employee.mapper.AdminUserRoleDomainMapper;
import com.pp.employee.service.IUserRoleService;
import com.pp.employee.domain.AdminUserRoleDomainExample;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Slf4j
@Service
public class UserRoleServiceImpl implements IUserRoleService {

    @Autowired
    private AdminUserRoleDomainMapper adminUserRoleDomainMapper;
    @Autowired
    private ICodeGenerateService codeGenerateService;

    @Override
    public int  save(String userId, String roleId) {
        AdminUserRoleDomain domain = new AdminUserRoleDomain();
        domain.setId(codeGenerateService.makeUserRoleCode());
        domain.setUserId(userId);
        domain.setRoleId(roleId);
        domain.setHasDeleted(EnumBoolean.FALSE.getVal());
        domain.insertOperation();
        return adminUserRoleDomainMapper.insertSelective(domain);
    }

    @Override
    public int save(String userId, List<String> roleIds) {
        if(StringUtils.isBlank(userId) || CollectionUtils.isEmpty(roleIds)){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }

        int i = 0;
        for(String roleId: roleIds){
            if(StringUtils.isBlank(roleId)){
                continue;
            }
            List<AdminUserRoleDomain> list = this.get(userId, roleId);
            if(CollectionUtils.isEmpty(list)){
                i += save(userId, roleId);
            }
        }

        return i;
    }

    @Override
    public int save(List<String> empIds, String roleId) {
        if(StringUtils.isBlank(roleId) || CollectionUtils.isEmpty(empIds)){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }

        int i = 0;
        for(String empId: empIds){
            if(StringUtils.isBlank(empId)){
                continue;
            }
            List<AdminUserRoleDomain> list = this.get(empId, roleId);
            if(CollectionUtils.isEmpty(list)){
                i += save(empId, roleId);
            }
        }
        return i;
    }

    @Override
    public int delete(AdminUserRoleDomain domain) {
        domain.setHasDeleted(EnumBoolean.TRUE.getVal());
        domain.updateOperation();
        return adminUserRoleDomainMapper.updateByPrimaryKeySelective(domain);
    }

    @Override
    public int delete(List<String> ids) {
        if(CollectionUtils.isEmpty(ids)){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        int i = 0;

        for(String id: ids){
            AdminUserRoleDomain domain = new AdminUserRoleDomain();
            domain.setId(id);
            i += this.delete(domain);
        }

        return i;
    }

    @Override
    public int deleteByUserId(String userId) {
        if(StringUtils.isBlank(userId)){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        int i = 0;

        List<AdminUserRoleDomain> list = this.getByUserId(userId);
        if(!CollectionUtils.isEmpty(list)){
            for(AdminUserRoleDomain domain: list){
                i += this.delete(domain);
            }
        }

        return i;
    }

    @Override
    public int deleteByRoleId(String roleId) {
        if(StringUtils.isBlank(roleId)){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        int i = 0;

        List<AdminUserRoleDomain> list = this.getByRoleId(roleId);
        if(!CollectionUtils.isEmpty(list)){
            for(AdminUserRoleDomain domain: list){
                i += this.delete(domain);
            }
        }

        return i;
    }

    @Override
    public List<AdminUserRoleDomain> get(String userId, String roleId) {
        if(StringUtils.isBlank(roleId) || StringUtils.isBlank(userId)){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }

        AdminUserRoleDomain domain = new AdminUserRoleDomain();
        domain.setRoleId(roleId);
        domain.setUserId(userId);
        domain.setHasDeleted(EnumBoolean.FALSE.getVal());
        return this.get(domain);
    }

    @Override
    public List<AdminUserRoleDomain> getByUserId(String userId) {
        if(StringUtils.isBlank(userId)){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        AdminUserRoleDomain domain = new AdminUserRoleDomain();
        domain.setUserId(userId);
        domain.setHasDeleted(EnumBoolean.FALSE.getVal());
        return this.get(domain);
    }

    @Override
    public List<AdminUserRoleDomain> getByRoleId(String roleId) {
        if(StringUtils.isBlank(roleId)){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        AdminUserRoleDomain domain = new AdminUserRoleDomain();
        domain.setRoleId(roleId);
        domain.setHasDeleted(EnumBoolean.FALSE.getVal());
        return this.get(domain);
    }

    @Override
    public List<AdminUserRoleDomain> get(AdminUserRoleDomain domain) {
        if(null == domain){
            domain = new AdminUserRoleDomain();
        }
        if(null == domain.getHasDeleted()){
            domain.setHasDeleted(EnumBoolean.FALSE.getVal());
        }

        AdminUserRoleDomainExample example = new AdminUserRoleDomainExample();
        AdminUserRoleDomainExample.Criteria criteria = example.createCriteria();

        if(StringUtils.isNotBlank(domain.getRoleId())){
            criteria.andRoleIdEqualTo(domain.getRoleId());
        }
        if(StringUtils.isNotBlank(domain.getUserId())){
            criteria.andUserIdEqualTo(domain.getUserId());
        }

        criteria.andHasDeletedEqualTo(domain.getHasDeleted());

        example.setOrderByClause(" create_time desc ");
        return adminUserRoleDomainMapper.selectByExample(example);
    }
}
