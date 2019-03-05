package com.pp.goods.enums;

import com.pp.base.constant.ErrorConstant;
import com.pp.base.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;

public enum EnumGoodsGiftStatus {
    ON("N", "上架"),
    OFF("F", "下架");

    private String code;
    private String desc;

    private EnumGoodsGiftStatus(String code, String desc) {
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
            throw BusinessException.withErrorCode("EnumGoodsGiftStatus:" + ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        for (EnumGoodsGiftStatus em: EnumGoodsGiftStatus.values()){
            if(code.equals(em.getCode())){
                return em.getDesc();
            }
        }
        throw BusinessException.withErrorCode("EnumGoodsGiftStatus:" + ErrorConstant.Common.NOT_FOND);
    }
}
