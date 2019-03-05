package com.pp.member.mapper;

import com.pp.member.domain.MemberAddressDomainExample;
import com.pp.member.domain.MemberAddressDomain;

import java.util.List;

public interface MemberAddressDomainMapper {
    int countByExample(MemberAddressDomainExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberAddressDomain record);

    int insertSelective(MemberAddressDomain record);

    List<MemberAddressDomain> selectByExample(MemberAddressDomainExample example);

    MemberAddressDomain selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MemberAddressDomain record);

    int updateByPrimaryKey(MemberAddressDomain record);
}