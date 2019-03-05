package com.pp.article.mapper;

import com.pp.article.domain.ArticleContentDomain;
import com.pp.article.domain.ArticleContentDomainExample;
import java.util.List;

public interface ArticleContentDomainMapper {
    int countByExample(ArticleContentDomainExample example);

    int deleteByPrimaryKey(String id);

    int insert(ArticleContentDomain record);

    int insertSelective(ArticleContentDomain record);

    List<ArticleContentDomain> selectByExample(ArticleContentDomainExample example);

    ArticleContentDomain selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ArticleContentDomain record);

    int updateByPrimaryKey(ArticleContentDomain record);
}