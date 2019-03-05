package com.pp.employee.domain;

import com.pp.base.domain.BaseDomain;

public class AdminDeptDomain extends BaseDomain {
//    private String id;

    private String name;

    private String deptType;

    private Integer deptLevel;

    private String parentId;

    private String leaderId;

    private String description;

//    private Date createTime;
//
//    private String createCode;
//
//    private Date modifyTime;
//
//    private String modifyCode;
//
//    private Boolean hasDeleted;

    /*****************************************************************************/

//    private String createName;

    private String leaderName;

    private String parentName;

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

    public String getDeptType() {
        return deptType;
    }

    public void setDeptType(String deptType) {
        this.deptType = deptType == null ? null : deptType.trim();
    }

    public Integer getDeptLevel() {
        return deptLevel;
    }

    public void setDeptLevel(Integer deptLevel) {
        this.deptLevel = deptLevel;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(String leaderId) {
        this.leaderId = leaderId == null ? null : leaderId.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

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
//
//    public Boolean getHasDeleted() {
//        return hasDeleted;
//    }
//
//    public void setHasDeleted(Boolean hasDeleted) {
//        this.hasDeleted = hasDeleted;
//    }
//
//    public String getCreateName() {
//        return createName;
//    }
//
//    public void setCreateName(String createName) {
//        this.createName = createName;
//    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
}