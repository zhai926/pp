package com.pp.goods.enums;

import com.pp.base.constant.ErrorConstant;
import com.pp.base.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;

public enum EnumGoodsGiftType {

    GOODS("1", "商品"),
    GIFT("2", "礼品");

    private String code;
    private String desc;

    private EnumGoodsGiftType(String code, String desc) {
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
            throw BusinessException.withErrorCode("EnumGoodsGiftType:" + ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        for (EnumGoodsGiftType em: EnumGoodsGiftType.values()){
            if(code.equals(em.getCode())){
                return em.getDesc();
            }
        }
        throw BusinessException.withErrorCode("EnumGoodsGiftType:" + ErrorConstant.Common.NOT_FOND);
    }

}
