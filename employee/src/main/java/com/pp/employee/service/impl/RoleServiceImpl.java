package com.pp.employee.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pp.base.constant.ErrorConstant;
import com.pp.base.enums.EnumBoolean;
import com.pp.base.exception.BusinessException;
import com.pp.base.service.ICodeGenerateService;
import com.pp.employee.domain.AdminRoleDomainExample;
import com.pp.employee.mapper.AdminRoleDomainMapper;
import com.pp.employee.service.IRoleMenuSerivce;
import com.pp.employee.service.IRoleService;
import com.pp.employee.domain.AdminRoleDomain;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private AdminRoleDomainMapper adminRoleDomainMapper;
    @Autowired
    private ICodeGenerateService codeGenerateService;
    @Autowired
    private IRoleMenuSerivce roleMenuSerivce;

    @Override
    @Transactional
    public int save(AdminRoleDomain domain) {
        if(StringUtils.isNotBlank(domain.getId())){
            domain.updateOperation();

            roleMenuSerivce.deleteByRoleId(domain.getId());

            adminRoleDomainMapper.updateByPrimaryKeySelective(domain);
        }else{
            domain.setId(codeGenerateService.makeRoleCode());
            domain.insertOperation();

            adminRoleDomainMapper.insertSelective(domain);
        }
        roleMenuSerivce.save(domain.getId(), new ArrayList<>(Arrays.asList(domain.getMenuIds().split(","))));
        return 1;
    }

    @Override
    @Transactional
    public int delete(List<String> ids) {
        if(CollectionUtils.isEmpty(ids)){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        int i = 0;

        for(String id: ids){
            AdminRoleDomain domain = new AdminRoleDomain();
            domain.setId(id);
            domain.setHasDeleted(EnumBoolean.TRUE.getVal());
            i += adminRoleDomainMapper.deleteByPrimaryKey(id);

            // 删除role menu
            roleMenuSerivce.deleteByRoleId(id);
        }
        return i;
    }

    @Override
    public AdminRoleDomain get(String id) {
        if(StringUtils.isBlank(id)){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }

        return adminRoleDomainMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<AdminRoleDomain> get(AdminRoleDomain domain) {
        if(null == domain){
            domain = new AdminRoleDomain();
        }
        if(null == domain.getHasDeleted()){
            domain.setHasDeleted(EnumBoolean.FALSE.getVal());
        }

        AdminRoleDomainExample example = new AdminRoleDomainExample();
        AdminRoleDomainExample.Criteria criteria = example.createCriteria();

        if(StringUtils.isNotBlank(domain.getName())){
            criteria.andNameLike("%" + domain.getName() + "%");
        }
        if(StringUtils.isNotBlank(domain.getCode())){
            criteria.andCodeEqualTo(domain.getCode());
        }
        if(StringUtils.isNotBlank(domain.getCompanyId())){
            criteria.andCompanyIdEqualTo(domain.getCompanyId());
        }

        example.setOrderByClause(" create_time desc ");

        return adminRoleDomainMapper.selectByExample(example);
    }

    @Override
    public PageInfo<AdminRoleDomain> page(AdminRoleDomain domain, Integer start, Integer pageSize) {
        PageHelper.startPage(start, pageSize);
        List<AdminRoleDomain> list = this.get(domain);
        return new PageInfo<>(list);
    }
}
