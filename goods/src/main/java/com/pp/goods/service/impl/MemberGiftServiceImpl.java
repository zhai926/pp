package com.pp.goods.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pp.base.constant.ErrorConstant;
import com.pp.base.enums.EnumBoolean;
import com.pp.base.exception.BusinessException;
import com.pp.base.service.ICodeGenerateService;
import com.pp.goods.domain.MemberGiftDomain;
import com.pp.goods.domain.MemberGiftDomainExample;
import com.pp.goods.mapper.MemberGiftDomainMapper;
import com.pp.goods.service.IMemberGiftService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberGiftServiceImpl implements IMemberGiftService {

    @Autowired
    private MemberGiftDomainMapper memberGiftDomainMapper;
    @Autowired
    private ICodeGenerateService codeGenerateService;

    @Override
    public int save(MemberGiftDomain domain) {
        if(StringUtils.isBlank(domain.getId())){
            domain.setId(codeGenerateService.makeMemberGiftCode());
            domain.setHasDeleted(EnumBoolean.FALSE.getVal());
//            domain.insertOperation();
            return memberGiftDomainMapper.insertSelective(domain);
        }else {
//            domain.updateOperation();
            return memberGiftDomainMapper.updateByPrimaryKeySelective(domain);
        }
    }

    @Override
    public MemberGiftDomain get(String id) {
        if(StringUtils.isBlank(id)){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        return memberGiftDomainMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<MemberGiftDomain> get(String giftId, String memberId) {
        if(StringUtils.isBlank(giftId) || StringUtils.isBlank(memberId)){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        MemberGiftDomainExample example = new MemberGiftDomainExample();
        example.createCriteria().andGiftIdEqualTo(giftId).andMemberIdEqualTo(memberId);
        List<MemberGiftDomain> list = memberGiftDomainMapper.selectByExample(example);
        return list;
    }

    @Override
    public List<MemberGiftDomain> getByMemberId(String memberId) {
        if(StringUtils.isBlank(memberId)){
            //throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
            return null;
        }
        MemberGiftDomainExample example = new MemberGiftDomainExample();
        example.createCriteria().andMemberIdEqualTo(memberId);
        List<MemberGiftDomain> list = memberGiftDomainMapper.selectByExample(example);
        return list;
    }

    @Override
    public PageInfo<MemberGiftDomain> page(MemberGiftDomain domain, Integer page, Integer pageSize) {
        if(null == domain){
            domain = new MemberGiftDomain();
        }

        MemberGiftDomainExample example = new MemberGiftDomainExample();
        MemberGiftDomainExample.Criteria criteria = example.createCriteria();

        if(StringUtils.isNotBlank(domain.getAddressDetail())){
            criteria.andAddressDetailLike("%" + domain.getAddressDetail() + "%");
        }
        if(StringUtils.isNotBlank(domain.getContactName())){
            criteria.andContactNameLike("%" + domain.getContactName() + "%");
        }
        if(StringUtils.isNotBlank(domain.getContactPhone())){
            criteria.andContactPhoneLike("%" + domain.getContactPhone() + "%");
        }
        if(StringUtils.isNotBlank(domain.getGiftName())){
            criteria.andGiftNameLike("%" + domain.getGiftName() + "%");
        }
        if(StringUtils.isNotBlank(domain.getMemberName())){
            criteria.andMemberNameLike("%" + domain.getMemberName() + "%");
        }

        example.setOrderByClause(" id asc ");

        PageHelper.startPage(page, pageSize);
        List<MemberGiftDomain> list = memberGiftDomainMapper.selectByExample(example);
        return new PageInfo<>(list);
    }
}
