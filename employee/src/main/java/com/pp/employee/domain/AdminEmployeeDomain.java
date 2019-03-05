package com.pp.employee.domain;

import com.pp.base.domain.BaseDomain;
import com.pp.base.enums.EnumSexType;
import com.pp.employee.enums.EnumCardType;
import com.pp.employee.enums.EnumEmpStatus;
import org.apache.commons.lang3.StringUtils;

public class AdminEmployeeDomain extends BaseDomain {
//    private String id;

    private String status;

    private String name;

    private Integer sex;

    private String mobile;

    private String cardType;

    private String cardNum;

    private String account;

    private String address;

    private String provinceCode;

    private String cityCode;

    private String areaCode;

    private String streetCode;

    private String provinceName;

    private String cityName;

    private String areaName;

    private String streetName;

    private String postId;

//    private String deptId;
//
//    private String createCode;
//
//    private Date createTime;
//
//    private Date modifyTime;
//
//    private String modifyCode;
//
//    private Boolean hasDeleted;

    /****************************************************************************/

    private String password;

    private String secure;

    private String icon;

    private String statusName;

    private String cardTypeName;

    private String postName;

//    private String deptName;
//
    private Boolean hasPassword;

    private String userId;

    private String sexName;

//    private String createName;

//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id == null ? null : id.trim();
//    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType == null ? null : cardType.trim();
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum == null ? null : cardNum.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode == null ? null : provinceCode.trim();
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    public String getStreetCode() {
        return streetCode;
    }

    public void setStreetCode(String streetCode) {
        this.streetCode = streetCode == null ? null : streetCode.trim();
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName == null ? null : provinceName.trim();
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName == null ? null : streetName.trim();
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId == null ? null : postId.trim();
    }

    public Boolean getHasPassword() {
        return hasPassword;
    }

    public void setHasPassword(Boolean hasPassword) {
        this.hasPassword = hasPassword;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecure() {
        return secure;
    }

    public void setSecure(String secure) {
        this.secure = secure;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getStatusName() {
        if(StringUtils.isNotBlank(getStatus())){
            return EnumEmpStatus.getDesc(getStatus());
        }
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getCardTypeName() {
        if(StringUtils.isNotBlank(getCardType())){
            return EnumCardType.getDesc(getCardType());
        }
        return cardTypeName;
    }

    public void setCardTypeName(String cardTypeName) {
        this.cardTypeName = cardTypeName;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getSexName() {
        if(null != sex){
            return EnumSexType.get(sex.toString()).getName();
        }
        return sexName;
    }

    public void setSexName(String sexName) {
        this.sexName = sexName;
    }

    //    public String getDeptName() {
//        return deptName;
//    }
//
//    public void setDeptName(String deptName) {
//        this.deptName = deptName;
//    }

//    public String getCreateName() {
//        return createName;
//    }
//
//    public void setCreateName(String createName) {
//        this.createName = createName;
//    }
}