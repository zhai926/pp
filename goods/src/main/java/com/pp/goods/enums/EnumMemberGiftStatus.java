package com.pp.goods.enums;

import com.pp.base.constant.ErrorConstant;
import com.pp.base.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;

public enum EnumMemberGiftStatus {
    /**
     * INITIAL 初始-未发货
     * SEND 已发货
     */
    INITIAL("I", "未兑换"),
    DUIHUAN("D", "已兑换"),
    SEND("S", "已发货");

    private String code;
    private String desc;

    private EnumMemberGiftStatus(String code, String desc) {
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
        for (EnumMemberGiftStatus em: EnumMemberGiftStatus.values()){
            if(code.equals(em.getCode())){
                return em.getDesc();
            }
        }
        throw BusinessException.withErrorCode("EnumGoodsGiftStatus:" + ErrorConstant.Common.NOT_FOND);
    }
}
