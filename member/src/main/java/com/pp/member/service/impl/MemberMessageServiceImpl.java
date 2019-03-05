package com.pp.member.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pp.base.constant.ErrorConstant;
import com.pp.base.enums.EnumBoolean;
import com.pp.base.exception.BusinessException;
import com.pp.base.service.ICodeGenerateService;
import com.pp.base.utils.DateFormatUtil;
import com.pp.member.domain.MemberMessageDomain;
import com.pp.member.domain.MemberMessageDomainExample;
import com.pp.member.mapper.MemberMessageDomainMapper;
import com.pp.member.service.IMemberMessageService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberMessageServiceImpl implements IMemberMessageService {

    @Autowired
    private MemberMessageDomainMapper memberMessageDomainMapper;
    @Autowired
    private ICodeGenerateService codeGenerateService;

    @Override
    public int save(MemberMessageDomain domain) {
        if(StringUtils.isBlank(domain.getId())){
            domain.setId(codeGenerateService.makeMemberMessageCode());
            domain.setHasDeleted(EnumBoolean.FALSE.getVal());
//            domain.insertOperation();
            domain.setCreateTime(DateFormatUtil.getCurrentDateTime());
            domain.setModifyTime(DateFormatUtil.getCurrentDateTime());
            return memberMessageDomainMapper.insertSelective(domain);
        }else{
//            domain.updateOperation();
            domain.setModifyTime(DateFormatUtil.getCurrentDateTime());
            return memberMessageDomainMapper.updateByPrimaryKeySelective(domain);
        }
    }

    @Override
    public MemberMessageDomain get(String id) {
        if(StringUtils.isBlank(id)){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        return memberMessageDomainMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<MemberMessageDomain> page(MemberMessageDomain domain, Integer start, Integer pageSize) {
        if(null == domain){
            domain = new MemberMessageDomain();
        }

        MemberMessageDomainExample example = new MemberMessageDomainExample();
        MemberMessageDomainExample.Criteria criteria = example.createCriteria();

        if(StringUtils.isNotBlank(domain.getContent())){
            criteria.andContentLike("%" + domain.getContent() + "%");
        }

        example.setOrderByClause(" create_time desc ");

        PageHelper.startPage(start, pageSize);
        List<MemberMessageDomain> list = memberMessageDomainMapper.selectByExample(example);
        return new PageInfo<>(list);
    }
}
