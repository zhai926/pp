package com.pp.employee.enums;

import com.pp.base.constant.ErrorConstant;
import com.pp.base.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;

public enum EnumMenuType {

    FOLDER("1", "文件夹"),
    MENU("2", "菜单"),
    BUTTON("3", "按钮");

    private String code;
    private String desc;

    private EnumMenuType(String code, String desc) {
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
            throw BusinessException.withErrorCode("EnumMenuType:" + ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        for (EnumMenuType em: EnumMenuType.values()){
            if(code.equals(em.getCode())){
                return em.getDesc();
            }
        }
        throw BusinessException.withErrorCode("EnumMenuType:" + ErrorConstant.Common.NOT_FOND);
    }
}
