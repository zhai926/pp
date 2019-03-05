package com.pp.member.mapper;

import com.pp.member.domain.MemberMessageDomain;
import com.pp.member.domain.MemberMessageDomainExample;
import java.util.List;

public interface MemberMessageDomainMapper {
    int countByExample(MemberMessageDomainExample example);

    int deleteByPrimaryKey(String id);

    int insert(MemberMessageDomain record);

    int insertSelective(MemberMessageDomain record);

    List<MemberMessageDomain> selectByExample(MemberMessageDomainExample example);

    MemberMessageDomain selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MemberMessageDomain record);

    int updateByPrimaryKey(MemberMessageDomain record);
}