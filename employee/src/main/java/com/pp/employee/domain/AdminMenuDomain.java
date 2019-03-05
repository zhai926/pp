package com.pp.employee.domain;

import com.pp.base.domain.BaseDomain;

public class AdminMenuDomain extends BaseDomain {
//    private String id;

    private String name;

    private String code;

    private String description;

    private String parentId;

    private Boolean hasMenu;

    private String url;

    private String status;

    private String type;

    private Integer level;

//    private Boolean hasDeleted;
//
//    private Date createTime;
//
//    private String createCode;
//
//    private Date modifyTime;
//
//    private String modifyCode;

//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id == null ? null : id.trim();
//    }

    private String parentName;

    private String typeName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public Boolean getHasMenu() {
        return hasMenu;
    }

    public void setHasMenu(Boolean hasMenu) {
        this.hasMenu = hasMenu;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
    //    public Boolean getHasDeleted() {
//        return hasDeleted;
//    }
//
//    public void setHasDeleted(Boolean hasDeleted) {
//        this.hasDeleted = hasDeleted;
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
//    public String getCreateCode() {
//        return createCode;
//    }
//
//    public void setCreateCode(String createCode) {
//        this.createCode = createCode == null ? null : createCode.trim();
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
//    public String getModifyCode() {
//        return modifyCode;
//    }
//
//    public void setModifyCode(String modifyCode) {
//        this.modifyCode = modifyCode == null ? null : modifyCode.trim();
//    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getTypeName() {
//        if(StringUtils.isNotBlank(type) && StringUtils.isBlank(type)){
//            return EnumMenuType.getDesc(type);
//        }
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}