package com.pp.article.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pp.article.domain.ArticleContentDomain;
import com.pp.article.domain.ArticleContentDomainExample;
import com.pp.article.mapper.ArticleContentDomainMapper;
import com.pp.article.service.IArticleContentService;
import com.pp.base.constant.ErrorConstant;
import com.pp.base.enums.EnumBoolean;
import com.pp.base.exception.BusinessException;
import com.pp.base.service.ICodeGenerateService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class ArticleContentServiceImpl implements IArticleContentService {

    @Autowired
    private ArticleContentDomainMapper articleContentDomainMapper;
    @Autowired
    private ICodeGenerateService codeGenerateService;

    @Override
    public int save(ArticleContentDomain domain) {
        if(StringUtils.isBlank(domain.getId())){
            domain.setId(codeGenerateService.makeArticleContentCode());
            domain.setHasDeleted(EnumBoolean.FALSE.getVal());
            domain.insertOperation();
            return articleContentDomainMapper.insertSelective(domain);
        }else{
            domain.updateOperation();
            return articleContentDomainMapper.updateByPrimaryKeySelective(domain);
        }
    }

    @Override
    public int delete(String id) {
        if(StringUtils.isBlank(id)){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        return articleContentDomainMapper.deleteByPrimaryKey(id);
    }

    @Override
    public ArticleContentDomain get(String id) {
        if(StringUtils.isBlank(id)){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        return articleContentDomainMapper.selectByPrimaryKey(id);
    }

    @Override
    public ArticleContentDomain getDefaultByMenuId(String menuId) {
        ArticleContentDomainExample example = new ArticleContentDomainExample();
        example.createCriteria().andMenuIdEqualTo(menuId);
        List<ArticleContentDomain> list = articleContentDomainMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        return list.get(0);
    }

    @Override
    public PageInfo<ArticleContentDomain> page(ArticleContentDomain domain, Integer start, Integer pageSize) {
        if(null == domain){
            domain = new ArticleContentDomain();
        }

        ArticleContentDomainExample example = new ArticleContentDomainExample();
        ArticleContentDomainExample.Criteria criteria = example.createCriteria();
        ArticleContentDomainExample.Criteria criteriaOR = example.createCriteria();

        if(StringUtils.isNotBlank(domain.getMenuId())){
            criteria.andMenuIdEqualTo(domain.getMenuId());
            criteriaOR.andMenuIdEqualTo(domain.getMenuId());
        }
        if(StringUtils.isNotBlank(domain.getName())){
            criteria.andNameLike("%" + domain.getName() + "%");
            criteriaOR.andNameLike("%" + domain.getName() + "%");
        }
        if(StringUtils.isNotBlank(domain.getContent())){
            criteria.andContentLike("%" + domain.getContent() + "%");
            criteriaOR.andContentLike("%" + domain.getContent() + "%");
        }

        // 参数这里不一样
        if(StringUtils.isNotBlank(domain.getKeyword())){
            criteria.andNameLike("%" + domain.getKeyword() + "%");
            criteriaOR.andContentLike("%" + domain.getKeyword() + "%");
        }

        example.or(criteriaOR);

        example.setOrderByClause(" create_time desc ");

        PageHelper.startPage(start, pageSize);
        List<ArticleContentDomain> list =  articleContentDomainMapper.selectByExample(example);
        return new PageInfo<>(list);
    }

}
