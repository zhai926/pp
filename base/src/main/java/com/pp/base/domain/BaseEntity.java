package com.pp.base.domain;

import com.pp.base.utils.DateFormatUtil;
import org.apache.commons.lang3.StringUtils;
import java.io.Serializable;
import java.util.Date;

public class BaseEntity implements Serializable {

    private static final long serialVersionUID = -225912080170622411L;

    private Integer id;

    private String createBy;

    private String createName;

    private Date createTime;

    private String updateBy;

    private String updateByName;

    private Date modifyTime;

    private Date updateTime;

    private Boolean hasDeleted;

    private Integer isDeleted;

    private String empId;

    private String empName;

    private String empPhone;

    private String deptId;

    private String deptName;


    /**
     * 创建者当时所属部门
     */
    private String createDeptId;

    private String createDeptName;

    private String companyId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateByName() {
        return updateByName;
    }

    public void setUpdateByName(String updateByName) {
        this.updateByName = updateByName;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Boolean getHasDeleted() {
        return hasDeleted;
    }

    public void setHasDeleted(Boolean hasDeleted) {
        this.hasDeleted = hasDeleted;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

//    public String getEmpCode(){
//        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = requestAttributes.getRequest();
////        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
////        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
////        HttpServletRequest request = sra.getRequest();
//        HttpSession session = request.getSession();
//        if (null == session) {
//            throw BusinessException.withErrorCode(ErrorConstant.Auth.NOT_LOGIN);
//        }
//        BaseDomain user = (BaseDomain) session.getAttribute(WebConst.LOGIN_SESSION_KEY);
//        return user.getEmpId();
//    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpPhone() {
        return empPhone;
    }

    public void setEmpPhone(String empPhone) {
        this.empPhone = empPhone;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getCreateDeptId() {
        return createDeptId;
    }

    public void setCreateDeptId(String createDeptId) {
        this.createDeptId = createDeptId;
    }

    public String getCreateDeptName() {
        return createDeptName;
    }

    public void setCreateDeptName(String createDeptName) {
        this.createDeptName = createDeptName;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public void insertOperation(/*BaseDomain ce*/) {
        if (this.getCreateTime() == null) {
            this.setCreateTime(DateFormatUtil.getCurrentDateTime());
        }
        this.setModifyTime(DateFormatUtil.getCurrentDateTime());
        this.setUpdateTime(DateFormatUtil.getCurrentDateTime());
        BaseDomain emp = BaseDomain.getEmp();
        if (null != emp) {
            this.setCompanyId(emp.getCompanyId());
            this.setCreateDeptId(emp.getDeptId());
            this.setCreateBy(emp.getEmpId());
            this.setUpdateBy(emp.getEmpId());
        }
    }

    public void updateOperation(/*BaseDomain ce*/) {
        this.setModifyTime(DateFormatUtil.getCurrentDateTime());
        this.setModifyTime(DateFormatUtil.getCurrentDateTime());
        String empCode = BaseDomain.getEmpCode();
        if (StringUtils.isNotEmpty(empCode)) {
            //设置更新用户的编码
            this.setUpdateBy(empCode);
        }
    }
}
