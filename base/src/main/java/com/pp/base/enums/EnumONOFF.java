package com.pp.base.enums;

import com.pp.base.constant.ErrorConstant;
import com.pp.base.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;

public enum EnumONOFF {
    ON("1", "ON", "启用", "是"),
    OFF("0", "OFF", "禁用", "否");

    private String code;
    private String name;
    private String desc;
    private String alias;

    private EnumONOFF(String code, String name, String desc, String alias) {
        this.code = code;
        this.name = name;
        this.desc = desc;
        this.alias = alias;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public static EnumONOFF get(String code){
        if(StringUtils.isBlank(code)){
//            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        for (EnumONOFF em: EnumONOFF.values()){
            if(code.equals(em.getCode())){
                return em;
            }
        }
//        throw BusinessException.withErrorCode("EnumONOFF" + ErrorConstant.Common.NOT_FOND);
        throw BusinessException.withErrorCode("EnumONOFF" + ErrorConstant.Common.NOT_FOND);
    }
}
