package com.pp.member.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pp.base.constant.ErrorConstant;
import com.pp.base.enums.EnumBoolean;
import com.pp.base.exception.BusinessException;
import com.pp.base.service.ICodeGenerateService;
import com.pp.base.utils.DateFormatUtil;
import com.pp.base.utils.TaleUtils;
import com.pp.member.domain.MemberDomain;
import com.pp.member.domain.MemberDomainExample;
import com.pp.member.mapper.MemberDomainMapper;
import com.pp.member.service.IMemberService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class MemberServiceImpl implements IMemberService {

    @Autowired
    private MemberDomainMapper memberDomainMapper;
    @Autowired
    private ICodeGenerateService codeGenerateService;

    @Override
    @Transactional
    public int update(MemberDomain domain) {
        domain.setUpdateTime(DateFormatUtil.getCurrentDateTime());
        return memberDomainMapper.updateByPrimaryKeySelective(domain);
    }

    @Override
    public MemberDomain get(String memberId) {
        if(StringUtils.isBlank(memberId)){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        MemberDomain domain = new MemberDomain();
        domain.setMemberId(memberId);
        List<MemberDomain> list = this.get(domain);
        if(CollectionUtils.isEmpty(list)){
            //throw BusinessException.withErrorCode(ErrorConstant.Common.CAN_NOT_FIND_PARAM_TO_CONTIUNE);
            return null;
        }
        if(list.size() > 1){
            throw BusinessException.withErrorCode(ErrorConstant.Common.FIND_MORE_RECORD);
        }
        return list.get(0);
    }

    @Override
    public MemberDomain getByPhone(String phoneNumber) {
        if(StringUtils.isBlank(phoneNumber)){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        MemberDomain domain = new MemberDomain();
        domain.setPhoneNumber(phoneNumber);
        List<MemberDomain> list = this.get(domain);
        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        if(list.size() > 1){
            throw BusinessException.withErrorCode(ErrorConstant.Common.FIND_MORE_RECORD);
        }
        return list.get(0);
    }

    @Override
    public MemberDomain getByEmail(String email) {
        if(StringUtils.isBlank(email)){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        MemberDomain domain = new MemberDomain();
        domain.setEmail(email);
        List<MemberDomain> list = this.get(domain);
        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        if(list.size() > 1){
            throw BusinessException.withErrorCode(ErrorConstant.Common.FIND_MORE_RECORD);
        }
        return list.get(0);
    }

    @Override
    public List<MemberDomain> get(MemberDomain domain) {
        if(null == domain){
            domain = new MemberDomain();
        }
        MemberDomainExample example = new MemberDomainExample();
        MemberDomainExample.Criteria criteria = example.createCriteria();

        if(null != domain.getId()){
            criteria.andIdEqualTo(domain.getId());
        }
        if(StringUtils.isNotBlank(domain.getMemberId())){
            criteria.andMemberIdEqualTo(domain.getMemberId());
        }
        if(StringUtils.isNotBlank(domain.getMemberType())){
            criteria.andMemberTypeEqualTo(domain.getMemberType());
        }
        if(StringUtils.isNotBlank(domain.getMemberName())){
            criteria.andMemberNameLike("%" + domain.getMemberName() + "%");
        }
        if(StringUtils.isNotBlank(domain.getMemberNickName())){
            criteria.andMemberNickNameLike("%" + domain.getMemberNickName() + "%");
        }
        if(StringUtils.isNotBlank(domain.getRealName())){
            criteria.andRealNameLike("%" + domain.getRealName() + "%");
        }
        if(StringUtils.isNotBlank(domain.getIdType())){
            criteria.andIdTypeEqualTo(domain.getIdType());
        }
        if(StringUtils.isNotBlank(domain.getIdNumber())){
            criteria.andIdNumberEqualTo(domain.getIdNumber());
        }
        if(StringUtils.isNotBlank(domain.getProvince())){
            criteria.andProvinceLike("%" + domain.getProvince() + "%");
        }
        if(StringUtils.isNotBlank(domain.getCity())){
            criteria.andCityLike("%" + domain.getCity() + "%");
        }
        if(StringUtils.isNotBlank(domain.getArea())){
            criteria.andAreaLike("%" + domain.getArea() + "%");
        }
        if(StringUtils.isNotBlank(domain.getAddress())){
            criteria.andAddressLike("%" + domain.getAddress() + "%");
        }
        if(StringUtils.isNotBlank(domain.getPhoneNumber())){
            criteria.andPhoneNumberEqualTo(domain.getPhoneNumber());
        }
        if(StringUtils.isNotBlank(domain.getEmail())){
            criteria.andEmailEqualTo(domain.getEmail());
        }
        if(null != domain.getCreateTimeBegin()) {
            criteria.andCreateTimeGreaterThanOrEqualTo(domain.getCreateTimeBegin());
        }
        if(null != domain.getCreateTimeEnd()){
            criteria.andCreateTimeLessThanOrEqualTo(domain.getCreateTimeEnd());
        }

        example.setOrderByClause(" create_time desc");
        List<MemberDomain> list = memberDomainMapper.selectByExample(example);
        return list;
    }

    @Override
    public PageInfo<MemberDomain> page(MemberDomain domain, Integer start, Integer pageSize) {
        PageHelper.startPage(start, pageSize);
        List<MemberDomain> list = this.get(domain);
        return new PageInfo<>(list);
    }

    @Override
    public MemberDomain register(MemberDomain domain) {
        if(StringUtils.isBlank(domain.getPhoneNumber()) || StringUtils.isBlank(domain.getPassWordText())){
            throw BusinessException.withErrorCode(ErrorConstant.Auth.USERNAME_PASSWORD_IS_EMPTY);
        }

        // 手机号是否已被注册
        MemberDomain exist = getByPhone(domain.getPhoneNumber());
        if(null != exist){
            throw BusinessException.withErrorCode("该手机号已注册,请登录");
        }

        String pwd = TaleUtils.MD5encode(domain.getPassWordText());
        domain.setPassWord(pwd);
        domain.setMemberId(codeGenerateService.makeMenuCode());
        domain.setCreateTime(DateFormatUtil.getCurrentDateTime());
        domain.setUpdateTime(DateFormatUtil.getCurrentDateTime());
        domain.setHasDeleted(EnumBoolean.FALSE.getVal());
        memberDomainMapper.insertSelective(domain);
        return domain;
    }

    @Override
    @Transactional
    public MemberDomain login(String phone, String pass) {
        if(StringUtils.isBlank(phone) || StringUtils.isBlank(pass)){
            throw BusinessException.withErrorCode(ErrorConstant.Auth.USERNAME_PASSWORD_IS_EMPTY);
        }
        String pwd = TaleUtils.MD5encode(pass);

        MemberDomainExample example = new MemberDomainExample();
        example.createCriteria().andPhoneNumberEqualTo(phone).andPassWordEqualTo(pwd);
        List<MemberDomain> list = memberDomainMapper.selectByExample(example);

        if(CollectionUtils.isEmpty(list)){
            throw BusinessException.withErrorCode(ErrorConstant.Auth.USERNAME_PASSWORD_ERROR);
        }

        MemberDomain domain = list.get(0);

        MemberDomain loginUser = new MemberDomain();
//        loginUser.setId(domain.getId());
        loginUser.setMemberId(domain.getMemberId());
        loginUser.setMemberName(domain.getMemberName());
        loginUser.setPhoneNumber(domain.getPhoneNumber());
        loginUser.setEmail(domain.getEmail());
        loginUser.setHasDeleted(domain.getHasDeleted());
        return loginUser;
    }

}
