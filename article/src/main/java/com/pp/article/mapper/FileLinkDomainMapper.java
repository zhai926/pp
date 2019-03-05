package com.pp.article.mapper;

import com.pp.article.domain.FileLinkDomain;
import com.pp.article.domain.FileLinkDomainExample;
import java.util.List;

public interface FileLinkDomainMapper {
    int countByExample(FileLinkDomainExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FileLinkDomain record);

    int insertSelective(FileLinkDomain record);

    List<FileLinkDomain> selectByExample(FileLinkDomainExample example);

    FileLinkDomain selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FileLinkDomain record);

    int updateByPrimaryKey(FileLinkDomain record);
}