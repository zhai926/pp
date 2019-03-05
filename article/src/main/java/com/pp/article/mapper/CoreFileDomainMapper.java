package com.pp.article.mapper;

import com.pp.article.domain.CoreFileDomain;
import com.pp.article.domain.CoreFileDomainExample;
import java.util.List;

public interface CoreFileDomainMapper {
    int countByExample(CoreFileDomainExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CoreFileDomain record);

    int insertSelective(CoreFileDomain record);

    List<CoreFileDomain> selectByExample(CoreFileDomainExample example);

    CoreFileDomain selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CoreFileDomain record);

    int updateByPrimaryKey(CoreFileDomain record);
}