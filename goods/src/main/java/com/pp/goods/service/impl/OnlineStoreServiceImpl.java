package com.pp.goods.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pp.base.constant.ErrorConstant;
import com.pp.base.enums.EnumBoolean;
import com.pp.base.exception.BusinessException;
import com.pp.base.service.ICodeGenerateService;
import com.pp.goods.domain.OnlineStoreDomain;
import com.pp.goods.domain.OnlineStoreDomainExample;
import com.pp.goods.mapper.OnlineStoreDomainMapper;
import com.pp.goods.service.IOnlineStoreService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OnlineStoreServiceImpl implements IOnlineStoreService {

    @Autowired
    private OnlineStoreDomainMapper onlineStoreDomainMapper;
    @Autowired
    private ICodeGenerateService codeGenerateService;

    @Override
    public int save(OnlineStoreDomain domain) {
        if(StringUtils.isBlank(domain.getId())){
            domain.setHasDeleted(EnumBoolean.FALSE.getVal());
            domain.setId(codeGenerateService.makeOnlineStoreCode());
            domain.insertOperation();
            return onlineStoreDomainMapper.insertSelective(domain);
        }else{
            domain.updateOperation();
            return onlineStoreDomainMapper.updateByPrimaryKeySelective(domain);
        }
    }

    @Override
    public int delete(String id) {
        if(StringUtils.isBlank(id)){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        return onlineStoreDomainMapper.deleteByPrimaryKey(id);
    }

    @Override
    public OnlineStoreDomain get(String id) {
        if(StringUtils.isBlank(id)){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        return onlineStoreDomainMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<OnlineStoreDomain> page(OnlineStoreDomain domain, Integer start, Integer pageSize) {
        if(null == domain){
            domain = new OnlineStoreDomain();
        }

        OnlineStoreDomainExample example = new OnlineStoreDomainExample();
        OnlineStoreDomainExample.Criteria criteria = example.createCriteria();

        if(StringUtils.isNotBlank(domain.getName())){
            criteria.andNameLike("%" + domain.getName() + "%");
        }
        if(StringUtils.isNotBlank(domain.getRemark())){
            criteria.andRemarkLike("%" + domain.getRemark() + "%");
        }
        if(StringUtils.isNotBlank(domain.getUrl())){
            criteria.andUrlLike("%" + domain.getUrl() + "%");
        }

        example.setOrderByClause(" id asc ");

        PageHelper.startPage(start, pageSize);
        List<OnlineStoreDomain> list = onlineStoreDomainMapper.selectByExample(example);
        return new PageInfo<>(list);
    }
}
