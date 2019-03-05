package com.pp.goods.domain;

import com.pp.base.domain.BaseDomain;

import java.util.Date;

public class OnlineStoreDomain extends BaseDomain {
//    private String id;

    private String name;

    private String url;

    private String remark;

//    private String createCode;
//
//    private Date createTime;
//
//    private String modifyCode;
//
//    private Date modifyTime;
//
//    private Boolean hasDeleted;

//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id == null ? null : id.trim();
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

//    public String getCreateCode() {
//        return createCode;
//    }
//
//    public void setCreateCode(String createCode) {
//        this.createCode = createCode == null ? null : createCode.trim();
//    }
//
//    public Date getCreateTime() {
//        return createTime;
//    }
//
//    public void setCreateTime(Date createTime) {
//        this.createTime = createTime;
//    }
//
//    public String getModifyCode() {
//        return modifyCode;
//    }
//
//    public void setModifyCode(String modifyCode) {
//        this.modifyCode = modifyCode == null ? null : modifyCode.trim();
//    }
//
//    public Date getModifyTime() {
//        return modifyTime;
//    }
//
//    public void setModifyTime(Date modifyTime) {
//        this.modifyTime = modifyTime;
//    }
//
//    public Boolean getHasDeleted() {
//        return hasDeleted;
//    }
//
//    public void setHasDeleted(Boolean hasDeleted) {
//        this.hasDeleted = hasDeleted;
//    }
}