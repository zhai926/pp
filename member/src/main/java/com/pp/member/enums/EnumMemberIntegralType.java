package com.pp.member.enums;

import com.pp.base.constant.ErrorConstant;
import com.pp.base.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;

public enum EnumMemberIntegralType {

    AO("AO", "预约订单"),
    ORDER("O", "盘点");

    private String code;

    private String name;

    private EnumMemberIntegralType(String code, String name){
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


    public static EnumMemberIntegralType get(String code){
        if(StringUtils.isBlank(code)){
            throw BusinessException.withErrorCode(ErrorConstant.Common.CAN_NOT_FIND_PARAM_TO_CONTIUNE);
        }
        for(EnumMemberIntegralType em: EnumMemberIntegralType.values()){
            if(code.equals(em.getCode())){
                return em;
            }
        }
        throw BusinessException.withErrorCode("EnumMemberIntegralType " + ErrorConstant.Common.NOT_FOND);
    }
}
