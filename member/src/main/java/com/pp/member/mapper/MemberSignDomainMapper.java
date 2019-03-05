package com.pp.member.mapper;

import com.pp.member.domain.MemberSignDomain;
import com.pp.member.domain.MemberSignDomainExample;
import java.util.List;

public interface MemberSignDomainMapper {
    int countByExample(MemberSignDomainExample example);

    int deleteByPrimaryKey(String id);

    int insert(MemberSignDomain record);

    int insertSelective(MemberSignDomain record);

    List<MemberSignDomain> selectByExample(MemberSignDomainExample example);

    MemberSignDomain selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MemberSignDomain record);

    int updateByPrimaryKey(MemberSignDomain record);
}