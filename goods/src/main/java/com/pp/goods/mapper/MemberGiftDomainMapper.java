package com.pp.goods.mapper;

import com.pp.goods.domain.MemberGiftDomain;
import com.pp.goods.domain.MemberGiftDomainExample;
import java.util.List;

public interface MemberGiftDomainMapper {
    int countByExample(MemberGiftDomainExample example);

    int deleteByPrimaryKey(String id);

    int insert(MemberGiftDomain record);

    int insertSelective(MemberGiftDomain record);

    List<MemberGiftDomain> selectByExample(MemberGiftDomainExample example);

    MemberGiftDomain selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MemberGiftDomain record);

    int updateByPrimaryKey(MemberGiftDomain record);
}