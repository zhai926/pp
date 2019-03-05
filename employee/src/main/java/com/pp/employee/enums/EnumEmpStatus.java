package com.pp.employee.enums;

import com.pp.base.constant.ErrorConstant;
import com.pp.base.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;

public enum EnumEmpStatus {

    PROBATION("2", "试用期"),
    ON("3", "在职"),
    TRAINEE("1", "实习"),
    OFF("4", "已离职");

    private String code;
    private String desc;

    private EnumEmpStatus(String code, String desc) {
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
            throw BusinessException.withErrorCode("EnumEmpStatus:" + ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        for (EnumEmpStatus em: EnumEmpStatus.values()){
            if(code.equals(em.getCode())){
                return em.getDesc();
            }
        }
        throw BusinessException.withErrorCode("EnumEmpStatus:" + ErrorConstant.Common.NOT_FOND);
    }
}