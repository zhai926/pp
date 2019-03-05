package com.pp.employee.domain;

import com.alibaba.fastjson.JSONObject;
import com.pp.base.domain.BaseDomain;
import com.pp.employee.dto.SpecialAuthDto;
import org.apache.commons.lang3.StringUtils;

/**
 * 特殊权限
 */
public class AdminAuthDomain extends BaseDomain {
//    private String id;
//
//    private String empId;

    private String authContent;

    private String authDesc;

//    private String createCode;

//    private Date createTime;
//
//    private String modifyCode;
//
//    private Date modifyTime;
//
//    private Boolean hasDeleted;


//    public String getEmpId() {
//        return empId;
//    }
//
//    public void setEmpId(String empId) {
//        this.empId = empId == null ? null : empId.trim();
//    }

    private SpecialAuthDto specialAuth;

    public String getAuthContent() {
        return authContent;
    }

    public void setAuthContent(String authContent) {
        this.authContent = authContent == null ? null : authContent.trim();
    }

    public String getAuthDesc() {
        return authDesc;
    }

    public void setAuthDesc(String authDesc) {
        this.authDesc = authDesc == null ? null : authDesc.trim();
    }

    /**
     *
     * @return
     */
    public SpecialAuthDto getSpecialAuth() {
        if(StringUtils.isBlank(authContent)){
            return new SpecialAuthDto();
        }
        return JSONObject.parseObject(authContent, SpecialAuthDto.class);
    }

    public void setSpecialAuth(SpecialAuthDto specialAuth) {
        this.specialAuth = specialAuth;
    }
}