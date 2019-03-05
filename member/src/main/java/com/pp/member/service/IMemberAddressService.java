package com.pp.member.service;

import com.pp.member.domain.MemberAddressDomain;

import java.util.List;

public interface IMemberAddressService {

    int save(MemberAddressDomain domain);

    MemberAddressDomain get(Integer id);

    MemberAddressDomain get(String addressId);

    MemberAddressDomain getDefault(String memberId);

    List<MemberAddressDomain> getByMemberId(String memberId);

    List<MemberAddressDomain> get(MemberAddressDomain domain);

    int delete(Integer id);

}
