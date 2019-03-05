package com.pp.base.enums;

import com.pp.base.constant.ErrorConstant;
import com.pp.base.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;

public enum EnumSexType {
    MAN("0", "男"),
    WOMAN("1", "女");

    private String code;
    private String name;

    private EnumSexType(String code, String name) {
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

    public static EnumSexType get(String code) {
        if(StringUtils.isBlank(code)){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        for (EnumSexType em: EnumSexType.values()) {
            if(code.equals(em.getCode())){
                return em;
            }
        }
        throw BusinessException.withErrorCode("EnumSexType" + ErrorConstant.Common.NOT_FOND);
    }
}
