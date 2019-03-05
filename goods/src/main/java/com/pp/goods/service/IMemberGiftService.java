package com.pp.goods.service;

import com.github.pagehelper.PageInfo;
import com.pp.goods.domain.MemberGiftDomain;

import java.util.List;

public interface IMemberGiftService {

    int save(MemberGiftDomain domain);

    MemberGiftDomain get(String id);

    List<MemberGiftDomain> get(String giftId, String memberId);

    List<MemberGiftDomain> getByMemberId(String memberId);

    PageInfo<MemberGiftDomain> page(MemberGiftDomain domain, Integer page, Integer pageSize);
}
