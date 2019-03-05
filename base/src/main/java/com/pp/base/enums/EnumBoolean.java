package com.pp.base.enums;

import com.pp.base.constant.ErrorConstant;
import com.pp.base.exception.BusinessException;

public enum EnumBoolean {

    TRUE(Boolean.TRUE, 1, "Y"),
    FALSE(Boolean.FALSE, 0, "N");

    private Boolean val;

    private Integer valNum;

    private String strCode;

    private EnumBoolean(Boolean val, Integer valNum, String strCode){
        this.val = val;
        this.valNum = valNum;
        this.strCode = strCode;
    }

    public Boolean getVal() {
        return val;
    }

    public void setVal(Boolean val) {
        this.val = val;
    }

    public Integer getValNum() {
        return valNum;
    }

    public void setValNum(Integer valNum) {
        this.valNum = valNum;
    }

    public String getStrCode() {
        return strCode;
    }

    public void setStrCode(String strCode) {
        this.strCode = strCode;
    }

    public static EnumBoolean get(Boolean val) {
        if(null == val){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        for(EnumBoolean em: EnumBoolean.values()){
            if(val == em.val){
                return em;
            }
        }
        throw BusinessException.withErrorCode("EnumBoolean" + ErrorConstant.Common.NOT_FOND);
    }
}
