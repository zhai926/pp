package com.pp.employee.service.impl;

import com.pp.base.constant.ErrorConstant;
import com.pp.base.enums.EnumBoolean;
import com.pp.base.exception.BusinessException;
import com.pp.base.service.ICodeGenerateService;
import com.pp.employee.domain.AdminRoleMenuDomainExample;
import com.pp.employee.mapper.AdminRoleMenuDomainMapper;
import com.pp.employee.service.IRoleMenuSerivce;
import com.pp.employee.domain.AdminRoleMenuDomain;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Slf4j
@Service
public class RoleMenuServiceImpl implements IRoleMenuSerivce {

    @Autowired
    private AdminRoleMenuDomainMapper adminRoleMenuDomainMapper;
    @Autowired
    private ICodeGenerateService codeGenerateService;

    @Override
    public int save(String roleId, List<String> menuIds) {
        if(StringUtils.isBlank(roleId) || CollectionUtils.isEmpty(menuIds)){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }

        int i = 0;
        for(String menuId: menuIds){
            List<AdminRoleMenuDomain> hasExist = this.get(roleId, menuId);
            if(CollectionUtils.isEmpty(hasExist)){
                AdminRoleMenuDomain domain = new AdminRoleMenuDomain();
                domain.setId(codeGenerateService.makeRoleMenuCode());
                domain.setRoleId(roleId);
                domain.setMenuId(menuId);
                domain.setHasDeleted(EnumBoolean.FALSE.getVal());
                domain.insertOperation();
                i += adminRoleMenuDomainMapper.insertSelective(domain);
            }
        }

        return i;
    }

    @Override
    public int delete(String id) {
        if(StringUtils.isBlank(id)){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        return adminRoleMenuDomainMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int delete(List<String> ids) {
        if(CollectionUtils.isEmpty(ids)){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        int i = 0;

        for(String id: ids){
            AdminRoleMenuDomain domain = new AdminRoleMenuDomain();
            domain.setId(id);
            i += this.delete(domain);
        }

        return i;
    }

    @Override
    public int delete(AdminRoleMenuDomain domain) {
        domain.setHasDeleted(EnumBoolean.TRUE.getVal());
        domain.updateOperation();
        return adminRoleMenuDomainMapper.updateByPrimaryKeySelective(domain);
    }

    @Override
    public int deleteByRoleId(String roleId) {
        if(StringUtils.isBlank(roleId)){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        int i = 0;

        List<AdminRoleMenuDomain> list = this.getByRoleId(roleId);
        if(!CollectionUtils.isEmpty(list)){
            for(AdminRoleMenuDomain domain: list){
                //i += this.delete(domain);
                i += this.delete(domain.getId());
            }
        }

        return i;
    }

    @Override
    public int deleteByMenuId(String menuId) {
        if(StringUtils.isBlank(menuId)){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        int i = 0;

        List<AdminRoleMenuDomain> list = this.getByMenuId(menuId);
        if(!CollectionUtils.isEmpty(list)){
            for(AdminRoleMenuDomain domain: list){
                i += this.delete(domain);
            }
        }

        return i;
    }

    @Override
    public List<AdminRoleMenuDomain> getByRoleId(String roleId) {
        if(StringUtils.isBlank(roleId)){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        AdminRoleMenuDomain domain = new AdminRoleMenuDomain();
        domain.setRoleId(roleId);
        domain.setHasDeleted(EnumBoolean.FALSE.getVal());
        return this.get(domain);
    }

    @Override
    public List<AdminRoleMenuDomain> getByMenuId(String menuId) {
        if(StringUtils.isBlank(menuId)){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }

        AdminRoleMenuDomain domain = new AdminRoleMenuDomain();
        domain.setMenuId(menuId);
        domain.setHasDeleted(EnumBoolean.FALSE.getVal());
        return this.get(domain);
    }

    @Override
    public List<AdminRoleMenuDomain> get(String roleId, String menuId) {
        if(StringUtils.isBlank(roleId) || StringUtils.isBlank(menuId)){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }

        AdminRoleMenuDomain domain = new AdminRoleMenuDomain();
        domain.setRoleId(roleId);
        domain.setMenuId(menuId);
        domain.setHasDeleted(EnumBoolean.FALSE.getVal());
        return this.get(domain);
    }

    @Override
    public List<AdminRoleMenuDomain> get(AdminRoleMenuDomain domain) {
        if(null == domain){
            domain = new AdminRoleMenuDomain();
        }
        if(null == domain.getHasDeleted()){
            domain.setHasDeleted(EnumBoolean.FALSE.getVal());
        }

        AdminRoleMenuDomainExample example = new AdminRoleMenuDomainExample();
        AdminRoleMenuDomainExample.Criteria criteria = example.createCriteria();

        if(StringUtils.isNotBlank(domain.getRoleId())){
            criteria.andRoleIdEqualTo(domain.getRoleId());
        }
        if(StringUtils.isNotBlank(domain.getMenuId())){
            criteria.andMenuIdEqualTo(domain.getMenuId());
        }

        criteria.andHasDeletedEqualTo(domain.getHasDeleted());

        example.setOrderByClause(" create_time desc ");
        return adminRoleMenuDomainMapper.selectByExample(example);
    }
}
