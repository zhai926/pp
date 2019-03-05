package com.pp.member.service.impl;

import com.pp.base.enums.EnumBoolean;
import com.pp.base.service.ICodeGenerateService;
import com.pp.base.utils.DateFormatUtil;
import com.pp.member.domain.MemberAddressDomain;
import com.pp.member.domain.MemberAddressDomainExample;
import com.pp.member.mapper.MemberAddressDomainMapper;
import com.pp.member.service.IMemberAddressService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Slf4j
@Service
public class MemberAddressServiceImpl implements IMemberAddressService {

    @Autowired
    private MemberAddressDomainMapper memberAddressDomainMapper;
    @Autowired
    private ICodeGenerateService codeGenerateService;

    @Override
    public int save(MemberAddressDomain domain) {
        if(null == domain.getId()){
            domain.setAddressId(codeGenerateService.makeArticleMenuCode());
            domain.setHasDeleted(EnumBoolean.FALSE.getVal());
            domain.setCreateTime(DateFormatUtil.getCurrentDateTime());
            domain.setUpdateTime(DateFormatUtil.getCurrentDateTime());
            return memberAddressDomainMapper.insertSelective(domain);
        }else{
            domain.setUpdateTime(DateFormatUtil.getCurrentDateTime());
            return memberAddressDomainMapper.updateByPrimaryKeySelective(domain);
        }
    }

    @Override
    public MemberAddressDomain get(Integer id) {
        return memberAddressDomainMapper.selectByPrimaryKey(id);
    }

    @Override
    public MemberAddressDomain get(String addressId) {
        MemberAddressDomain domain = new MemberAddressDomain();
        domain.setAddressId(addressId);
        List<MemberAddressDomain> list = this.get(domain);
        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        return list.get(0);
    }

    @Override
    public MemberAddressDomain getDefault(String memberId) {
        MemberAddressDomain addressDomain = new MemberAddressDomain();
        addressDomain.setMemberId(memberId);
        // delfault_flag Y status Y
// TODO


        return null;
    }

    @Override
    public List<MemberAddressDomain> getByMemberId(String memberId) {
        MemberAddressDomain domain = new MemberAddressDomain();
        domain.setMemberId(memberId);
        return get(domain);
    }

    @Override
    public List<MemberAddressDomain> get(MemberAddressDomain domain) {
        if(null == domain){
            domain = new MemberAddressDomain();
        }

        MemberAddressDomainExample example = new MemberAddressDomainExample();
        MemberAddressDomainExample.Criteria criteria = example.createCriteria();

        if(StringUtils.isNotBlank(domain.getMemberId())){
            criteria.andMemberIdEqualTo(domain.getMemberId());
        }
        if(StringUtils.isNotBlank(domain.getAddressId())){
            criteria.andAddressIdEqualTo(domain.getAddressId());
        }

        return memberAddressDomainMapper.selectByExample(example);
    }

    @Override
    public int delete(Integer id) {
        return memberAddressDomainMapper.deleteByPrimaryKey(id);
    }
}
