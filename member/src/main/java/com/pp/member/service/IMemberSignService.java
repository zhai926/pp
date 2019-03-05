package com.pp.member.service;

import com.github.pagehelper.PageInfo;
import com.pp.member.domain.MemberSignDomain;

import java.util.List;

public interface IMemberSignService {

    int save(String memberId);

    MemberSignDomain get(String memberId, String date);

    List<MemberSignDomain> get(String memberId);

    PageInfo<MemberSignDomain> page(MemberSignDomain domain, Integer start, Integer pageSize);

}
