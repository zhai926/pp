package com.pp.infrastructure.mapper;

import com.pp.infrastructure.domain.SysAddressCodeDomain;
import com.pp.infrastructure.domain.SysAddressCodeDomainExample;

import java.util.List;

public interface SysAddressCodeDomainMapper {
    int countByExample(SysAddressCodeDomainExample example);

    int deleteByPrimaryKey(String code);

    int insert(SysAddressCodeDomain record);

    int insertSelective(SysAddressCodeDomain record);

    List<SysAddressCodeDomain> selectByExample(SysAddressCodeDomainExample example);

    SysAddressCodeDomain selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(SysAddressCodeDomain record);

    int updateByPrimaryKey(SysAddressCodeDomain record);
}