package com.pp.goods.domain;

import com.pp.base.domain.BaseDomain;
import com.pp.goods.enums.EnumMemberGiftStatus;
import org.apache.commons.lang3.StringUtils;

public class MemberGiftDomain extends BaseDomain {
//    private String id;

    private String status;

    private String giftId;

    private String giftName;

    private Integer giftNum;

    private String memberId;

    private String memberName;

    private String addressId;

    private String contactName;

    private String contactPhone;

    private String addressDetail;


    private String statusName;


    private String fileId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getGiftId() {
        return giftId;
    }

    public void setGiftId(String giftId) {
        this.giftId = giftId == null ? null : giftId.trim();
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName == null ? null : giftName.trim();
    }

    public Integer getGiftNum() {
        return giftNum;
    }

    public void setGiftNum(Integer giftNum) {
        this.giftNum = giftNum;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId == null ? null : memberId.trim();
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName == null ? null : memberName.trim();
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId == null ? null : addressId.trim();
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName == null ? null : contactName.trim();
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone == null ? null : contactPhone.trim();
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail == null ? null : addressDetail.trim();
    }

    public String getStatusName() {
        if(StringUtils.isNotBlank(status)){
            return EnumMemberGiftStatus.getDesc(status);
        }
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
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