package com.pp.member.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pp.base.constant.ErrorConstant;
import com.pp.base.exception.BusinessException;
import com.pp.base.service.ICodeGenerateService;
import com.pp.base.utils.DateFormatUtil;
import com.pp.member.domain.MemberSignDomain;
import com.pp.member.domain.MemberSignDomainExample;
import com.pp.member.mapper.MemberSignDomainMapper;
import com.pp.member.service.IMemberSignService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class MemberSignServiceImpl implements IMemberSignService {

    @Autowired
    private MemberSignDomainMapper memberSignDomainMapper;
    @Autowired
    private ICodeGenerateService codeGenerateService;

    @Override
    public int save(String memberId) {
        MemberSignDomain domain = new MemberSignDomain();
        domain.setId(codeGenerateService.makeMemberMessageCode());
        domain.setMemberId(memberId);
        domain.setCreateTime(DateFormatUtil.getCurrentDateTime());
        domain.setCreateDate(DateFormatUtil.getCurrentDate(DateFormatUtil.DateTimeFormatDay));
        return memberSignDomainMapper.insertSelective(domain);
    }

    @Override
    public MemberSignDomain get(String memberId, String date) {
        if(StringUtils.isBlank(memberId) || StringUtils.isBlank(date)){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }

        MemberSignDomainExample example = new MemberSignDomainExample();
        example.createCriteria().andMemberIdEqualTo(memberId).andCreateDateEqualTo(date);
        List<MemberSignDomain> list = memberSignDomainMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<MemberSignDomain> get(String memberId) {
        MemberSignDomainExample example = new MemberSignDomainExample();
        example.createCriteria().andMemberIdEqualTo(memberId);
        List<MemberSignDomain> list = memberSignDomainMapper.selectByExample(example);
        return list;
    }

    @Override
    public PageInfo<MemberSignDomain> page(MemberSignDomain domain, Integer start, Integer pageSize) {

        MemberSignDomainExample example = new MemberSignDomainExample();
        example.createCriteria().andMemberIdEqualTo(domain.getMemberId());

        PageHelper.startPage(start, pageSize);
        List<MemberSignDomain> list = memberSignDomainMapper.selectByExample(example);
        return new PageInfo<>(list);
    }
}
