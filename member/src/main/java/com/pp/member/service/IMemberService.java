package com.pp.member.service;

import com.github.pagehelper.PageInfo;
import com.pp.member.domain.MemberDomain;

import java.util.List;

public interface IMemberService {

    int update(MemberDomain domain);

    MemberDomain get(String memberId);

    MemberDomain getByPhone(String phoneNumber);

    MemberDomain getByEmail(String email);

    List<MemberDomain> get(MemberDomain domain);

    PageInfo<MemberDomain> page(MemberDomain domain, Integer start, Integer pageSize);

    MemberDomain register(MemberDomain domain);

    MemberDomain login(String phone, String pass);
}
