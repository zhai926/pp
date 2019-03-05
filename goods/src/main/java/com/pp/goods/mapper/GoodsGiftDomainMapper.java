package com.pp.goods.mapper;

import com.pp.goods.domain.GoodsGiftDomain;
import com.pp.goods.domain.GoodsGiftDomainExample;
import java.util.List;

public interface GoodsGiftDomainMapper {
    int countByExample(GoodsGiftDomainExample example);

    int deleteByPrimaryKey(String id);

    int insert(GoodsGiftDomain record);

    int insertSelective(GoodsGiftDomain record);

    List<GoodsGiftDomain> selectByExample(GoodsGiftDomainExample example);

    GoodsGiftDomain selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GoodsGiftDomain record);

    int updateByPrimaryKey(GoodsGiftDomain record);
}