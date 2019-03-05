package com.pp.infrastructure.service.impl;

import com.pp.base.constant.ErrorConstant;
import com.pp.base.enums.EnumBoolean;
import com.pp.base.exception.BusinessException;
import com.pp.infrastructure.domain.SysAddressCodeDomain;
import com.pp.infrastructure.mapper.SysAddressCodeDomainMapper;
import com.pp.infrastructure.service.IAddressCodeService;
import com.pp.infrastructure.domain.SysAddressCodeDomainExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AddressCodeServiceImpl implements IAddressCodeService {

    @Autowired
    private SysAddressCodeDomainMapper sysAddressCodeDomainMapper;


    @Override
    public List<SysAddressCodeDomain> getByUpCode(String code) {
        if(StringUtils.isBlank(code)){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }

        SysAddressCodeDomain domain = new SysAddressCodeDomain();
        domain.setUpCode(code);

        return this.get(domain);
    }

    @Override
    public List<SysAddressCodeDomain> get(SysAddressCodeDomain domain) {
        if(null == domain){
            domain = new SysAddressCodeDomain();
        }

        SysAddressCodeDomainExample example = new SysAddressCodeDomainExample();
        SysAddressCodeDomainExample.Criteria criteria = example.createCriteria();

        if(StringUtils.isNotBlank(domain.getCode())){
            criteria.andCodeEqualTo(domain.getCode());
        }
        if(StringUtils.isNotBlank(domain.getName())){
            criteria.andNameLessThan("%" + domain.getName() + "%");
        }
        if(StringUtils.isNotBlank(domain.getFullName())){
            criteria.andFullNameEqualTo(domain.getFullName());
        }
        if(StringUtils.isNotBlank(domain.getUpCode())){
            criteria.andUpCodeEqualTo(domain.getUpCode());
        }
        if(StringUtils.isNotBlank(domain.getType())){
            criteria.andTypeEqualTo(domain.getType());
        }
        if(StringUtils.isNotBlank(domain.getStatus())){
            criteria.andStatusEqualTo(domain.getStatus());
        }

        // 查询可用地址
        criteria.andStatusEqualTo(EnumBoolean.TRUE.getStrCode());

        example.setOrderByClause(" code asc ");

        return sysAddressCodeDomainMapper.selectByExample(example);
    }

    @Override
    public SysAddressCodeDomain get(String code) {
        if(StringUtils.isBlank(code)){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        return sysAddressCodeDomainMapper.selectByPrimaryKey(code);
    }

    @Override
    public SysAddressCodeDomain getUp(String code, String type) {
        SysAddressCodeDomain domain = this.get(code);

        if(StringUtils.isBlank(type)){
            return domain;
        }


        while(null != domain && !type.equals(domain.getType())){
            domain = this.get(domain.getUpCode());
        }
//        if(null == domain){
//            return null;
//        }
//
//        if(StringUtils.isNotBlank(type) && !domain.getType().equals(type)){
//            domain = this.getUp(domain.getCode(), type);
//        }
        return domain;
    }

    @Override
    public Map<String, SysAddressCodeDomain> getUpMap(String code, String type) {
        SysAddressCodeDomain domain = this.get(code);
        if(StringUtils.isBlank(type)){
//            map.put(domain.getType(), domain);
//            return map;
            return null;
        }

        Map<String, SysAddressCodeDomain> map = new HashMap<>();
        while( null != domain && !type.equals(domain.getType())){
            map.put(domain.getType(), domain);
            //map.putAll(this.getUpMap(domain.getUpCode(), type));
            domain = this.get(domain.getUpCode());
        }
        map.put(domain.getType(), domain);
        return map;
    }

}
