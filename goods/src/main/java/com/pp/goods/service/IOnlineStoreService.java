package com.pp.goods.service;

import com.github.pagehelper.PageInfo;
import com.pp.goods.domain.OnlineStoreDomain;

public interface IOnlineStoreService {

    int save(OnlineStoreDomain domain);

    int delete(String id);

    OnlineStoreDomain get(String id);

    PageInfo<OnlineStoreDomain> page(OnlineStoreDomain domain, Integer start, Integer pageSize);

}
