package com.pp.goods.mapper;

import com.pp.goods.domain.OnlineStoreDomain;
import com.pp.goods.domain.OnlineStoreDomainExample;
import java.util.List;

public interface OnlineStoreDomainMapper {
    int countByExample(OnlineStoreDomainExample example);

    int deleteByPrimaryKey(String id);

    int insert(OnlineStoreDomain record);

    int insertSelective(OnlineStoreDomain record);

    List<OnlineStoreDomain> selectByExample(OnlineStoreDomainExample example);

    OnlineStoreDomain selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OnlineStoreDomain record);

    int updateByPrimaryKey(OnlineStoreDomain record);
}