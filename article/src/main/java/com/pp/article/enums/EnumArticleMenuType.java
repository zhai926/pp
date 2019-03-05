package com.pp.article.enums;

import com.pp.base.constant.ErrorConstant;
import com.pp.base.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;

public enum EnumArticleMenuType {

    COM("1", "企业文章"),
    CATE("2", "分类导航"),
    HEALTH("3", "营养健康解决方案");

    private String code;
    private String desc;

    private EnumArticleMenuType(String code, String desc) {
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
            throw BusinessException.withErrorCode("EnumArticleMenuType:" + ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        for (EnumArticleMenuType em: EnumArticleMenuType.values()){
            if(code.equals(em.getCode())){
                return em.getDesc();
            }
        }
        throw BusinessException.withErrorCode("EnumArticleMenuType:" + ErrorConstant.Common.NOT_FOND);
    }
}
