package com.pp.article.mapper;

import com.pp.article.domain.RotateDomain;
import com.pp.article.domain.RotateDomainExample;
import java.util.List;

public interface RotateDomainMapper {
    int countByExample(RotateDomainExample example);

    int deleteByPrimaryKey(String id);

    int insert(RotateDomain record);

    int insertSelective(RotateDomain record);

    List<RotateDomain> selectByExample(RotateDomainExample example);

    RotateDomain selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RotateDomain record);

    int updateByPrimaryKey(RotateDomain record);
}