package com.pp.infrastructure.enums;

import com.pp.base.constant.ErrorConstant;
import com.pp.base.exception.BusinessException;

public enum EnumAddressCodeType {

    COUNTRY("1", "国家"),
    PROVINCE("2", "省份"),
    CITY("3", "城市"),
    REGION("4", "区域"),
    STREET("5", "街道");

    private String code;
    private String name;

    EnumAddressCodeType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static EnumAddressCodeType get(String val) {
        if(null == val){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        for(EnumAddressCodeType em: EnumAddressCodeType.values()){
            if(val.equals(em.code)){
                return em;
            }
        }
        throw BusinessException.withErrorCode("EnumAddressCodeType" + ErrorConstant.Common.NOT_FOND);
    }
}
