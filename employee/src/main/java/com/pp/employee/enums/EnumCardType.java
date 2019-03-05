package com.pp.employee.enums;

import com.pp.base.constant.ErrorConstant;
import com.pp.base.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;

public enum EnumCardType {

    TRAINEE("1", "身份证"),
    PROBATION("2", "护照"),
    ON("3", "驾驶证"),
    OFF("4", "学生证");

    private String code;
    private String desc;

    private EnumCardType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static String getDesc(String code) {
        if(StringUtils.isBlank(code)){
            throw BusinessException.withErrorCode("EnumCardType:" + ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        for (EnumCardType em: EnumCardType.values()){
            if(code.equals(em.getCode())){
                return em.getDesc();
            }
        }
        throw BusinessException.withErrorCode("EnumCardType:" + ErrorConstant.Common.NOT_FOND);
    }
}