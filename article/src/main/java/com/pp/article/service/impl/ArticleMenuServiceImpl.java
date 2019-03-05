package com.pp.article.service.impl;

import com.pp.article.domain.ArticleMenuDomain;
import com.pp.article.domain.ArticleMenuDomainExample;
import com.pp.article.mapper.ArticleMenuDomainMapper;
import com.pp.article.service.IArticleMenuService;
import com.pp.base.constant.ErrorConstant;
import com.pp.base.enums.EnumBoolean;
import com.pp.base.exception.BusinessException;
import com.pp.base.service.ICodeGenerateService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleMenuServiceImpl implements IArticleMenuService {

    @Autowired
    private ArticleMenuDomainMapper articleMenuDomainMapper;
    @Autowired
    private ICodeGenerateService codeGenerateService;

    @Override
    public int save(ArticleMenuDomain domain) {
        if(StringUtils.isBlank(domain.getId())){
            domain.setId(codeGenerateService.makeArticleMenuCode());
            domain.setHasDeleted(EnumBoolean.FALSE.getVal());
            domain.insertOperation();
            return articleMenuDomainMapper.insertSelective(domain);
        }else{
            domain.updateOperation();
            return articleMenuDomainMapper.updateByPrimaryKeySelective(domain);
        }
    }

    @Override
    public int delete(String id){
        if(StringUtils.isBlank(id)){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        return articleMenuDomainMapper.deleteByPrimaryKey(id);
    }

    @Override
    public ArticleMenuDomain get(String id) {
        if(StringUtils.isBlank(id)){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        return articleMenuDomainMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ArticleMenuDomain> get(ArticleMenuDomain domain) {
        if(null == domain){
            domain = new ArticleMenuDomain();
        }

        ArticleMenuDomainExample example = new ArticleMenuDomainExample();
        ArticleMenuDomainExample.Criteria criteria = example.createCriteria();

        if(StringUtils.isNotBlank(domain.getName())){
            criteria.andNameLike("%" + domain.getName() + "%");
        }
        if(StringUtils.isNotBlank(domain.getType())){
            criteria.andTypeEqualTo(domain.getType());
        }

        example.setOrderByClause(" order_by asc ");

        return articleMenuDomainMapper.selectByExample(example);
    }
}
