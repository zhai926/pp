package com.pp.member.service;

import com.github.pagehelper.PageInfo;
import com.pp.member.domain.MemberMessageDomain;

public interface IMemberMessageService {

    int save(MemberMessageDomain domain);

    MemberMessageDomain get(String id);

    PageInfo<MemberMessageDomain> page(MemberMessageDomain domain, Integer start, Integer pageSize);

}
