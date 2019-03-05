package com.pp.article.service;

import com.github.pagehelper.PageInfo;
import com.pp.article.domain.ArticleContentDomain;

import java.util.List;

public interface IArticleContentService {

    int save(ArticleContentDomain domain);

    int delete(String id);

    ArticleContentDomain get(String id);

    ArticleContentDomain getDefaultByMenuId(String menuId);

    PageInfo<ArticleContentDomain> page(ArticleContentDomain domain, Integer start, Integer pageSize);
}
