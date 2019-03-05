package com.pp.article.mapper;

import com.pp.article.domain.ArticleMenuDomain;
import com.pp.article.domain.ArticleMenuDomainExample;
import java.util.List;

public interface ArticleMenuDomainMapper {
    int countByExample(ArticleMenuDomainExample example);

    int deleteByPrimaryKey(String id);

    int insert(ArticleMenuDomain record);

    int insertSelective(ArticleMenuDomain record);

    List<ArticleMenuDomain> selectByExample(ArticleMenuDomainExample example);

    ArticleMenuDomain selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ArticleMenuDomain record);

    int updateByPrimaryKey(ArticleMenuDomain record);
}