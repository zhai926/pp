package com.pp.article.service;

import com.pp.article.domain.ArticleMenuDomain;

import java.util.List;

public interface IArticleMenuService {

    int save(ArticleMenuDomain domain);

    int delete(String id);

    ArticleMenuDomain get(String id);

    List<ArticleMenuDomain> get(ArticleMenuDomain domain);
}
