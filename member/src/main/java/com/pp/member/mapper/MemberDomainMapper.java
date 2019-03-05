package com.pp.member.mapper;

import com.pp.member.domain.MemberDomain;
import com.pp.member.domain.MemberDomainExample;

import java.util.List;

public interface MemberDomainMapper {
    int countByExample(MemberDomainExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberDomain record);

    int insertSelective(MemberDomain record);

    List<MemberDomain> selectByExample(MemberDomainExample example);

    MemberDomain selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MemberDomain record);

    int updateByPrimaryKey(MemberDomain record);
}