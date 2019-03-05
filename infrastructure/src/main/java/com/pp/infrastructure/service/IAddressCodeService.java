package com.pp.infrastructure.service;

import com.pp.infrastructure.domain.SysAddressCodeDomain;

import java.util.List;
import java.util.Map;

public interface IAddressCodeService {


    List<SysAddressCodeDomain> getByUpCode(String code);

    List<SysAddressCodeDomain> get(SysAddressCodeDomain domain);

    SysAddressCodeDomain get(String code);

    /**
     * 向上查找直到某一type
     * @param code
     * @param type com.pp.infrastructure.enums.EnumAddressCodeType
     * @return
     */
    SysAddressCodeDomain getUp(String code, String type);

    /**
     * Map<type, domain></type,>
     * @param code
     * @param type
     * @return
     */
    Map<String, SysAddressCodeDomain> getUpMap(String code, String type);
}
