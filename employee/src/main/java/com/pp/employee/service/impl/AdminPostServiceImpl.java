package com.pp.employee.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pp.base.constant.ErrorConstant;
import com.pp.base.exception.BusinessException;
import com.pp.base.service.ICodeGenerateService;
import com.pp.employee.mapper.AdminPostDomainMapper;
import com.pp.employee.service.IAdminPostService;
import com.pp.employee.domain.AdminPostDomain;
import com.pp.employee.domain.AdminPostDomainExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminPostServiceImpl implements IAdminPostService {

    @Autowired
    private AdminPostDomainMapper adminPostDomainMapper;
    @Autowired
    private ICodeGenerateService codeGenerateService;

    @Override
    @Transactional
    public int save(AdminPostDomain post) {
        if(StringUtils.isBlank(post.getId())){
            post.setHasDeleted(Boolean.FALSE);
            post.insertOperation();
            post.setId(codeGenerateService.makePostCode());
            return adminPostDomainMapper.insertSelective(post);
        }else{
            post.updateOperation();
            return adminPostDomainMapper.updateByPrimaryKeySelective(post);
        }
    }

    @Override
    public AdminPostDomain get(String id) {
        if(StringUtils.isBlank(id)){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }

        return adminPostDomainMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<AdminPostDomain> get(AdminPostDomain post) {
        if(null == post){
            post = new AdminPostDomain();
        }
        AdminPostDomainExample example = new AdminPostDomainExample();
        AdminPostDomainExample.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotBlank(post.getId())){
            criteria.andIdEqualTo(post.getId());
        }
        if(StringUtils.isNotBlank(post.getName())){
            criteria.andNameLike("%" + post.getName() + "%");
        }
        if(StringUtils.isNotBlank(post.getCompanyId())){
            criteria.andCompanyIdEqualTo(post.getCompanyId());
        }
        example.setOrderByClause(" create_time desc ");
        List<AdminPostDomain> list = adminPostDomainMapper.selectByExample(example);
        return list;
    }

    @Override
    public PageInfo<AdminPostDomain> page(AdminPostDomain post, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        if(null == post) {
            post = new AdminPostDomain();
        }
        post.setHasDeleted(Boolean.FALSE);

        List<AdminPostDomain> list = this.get(post);
        PageInfo<AdminPostDomain> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
