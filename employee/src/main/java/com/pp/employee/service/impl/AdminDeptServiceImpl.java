package com.pp.employee.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pp.base.constant.ErrorConstant;
import com.pp.base.exception.BusinessException;
import com.pp.base.service.ICodeGenerateService;
import com.pp.employee.domain.AdminDeptDomain;
import com.pp.employee.mapper.AdminDeptDomainMapper;
import com.pp.employee.service.IAdminDeptService;
import com.pp.employee.domain.AdminDeptDomainExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminDeptServiceImpl implements IAdminDeptService {

    @Autowired
    private AdminDeptDomainMapper adminDeptDomainMapper;
    @Autowired
    private ICodeGenerateService codeGenerateService;

    @Override
    @Transactional
    public int save(AdminDeptDomain dept) {
        if(StringUtils.isBlank(dept.getId())){
            dept.insertOperation();
            dept.setId(codeGenerateService.makeDeptCode());
            return adminDeptDomainMapper.insertSelective(dept);
        }else{
            dept.updateOperation();
            return adminDeptDomainMapper.updateByPrimaryKeySelective(dept);
        }
    }

    @Override
    public AdminDeptDomain get(String id) {
        if(StringUtils.isBlank(id)){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        return adminDeptDomainMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<AdminDeptDomain> get(AdminDeptDomain dept) {
        if(null == dept){
            dept = new AdminDeptDomain();
        }
        AdminDeptDomainExample example = new AdminDeptDomainExample();
        AdminDeptDomainExample.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotBlank(dept.getId())){
            criteria.andIdEqualTo(dept.getId());
        }
        if(StringUtils.isNotBlank(dept.getDeptType())){
            criteria.andDeptTypeEqualTo(dept.getDeptType());
        }
        if(StringUtils.isNotBlank(dept.getName())){
            criteria.andNameLike(dept.getName());
        }
        if(StringUtils.isNotBlank(dept.getCompanyId())){
            criteria.andCompanyIdEqualTo(dept.getCompanyId());
        }
        List<AdminDeptDomain> list = adminDeptDomainMapper.selectByExample(example);
        return list;
    }

    @Override
    public PageInfo<AdminDeptDomain> page(AdminDeptDomain dept, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        if(null == dept) {
            dept = new AdminDeptDomain();
        }
        dept.setHasDeleted(Boolean.FALSE);
        List<AdminDeptDomain> list = this.get(dept);

        PageInfo<AdminDeptDomain> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
