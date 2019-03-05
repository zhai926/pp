package com.pp.base.domain;

import com.pp.base.constant.ErrorConstant;
import com.pp.base.constant.WebConst;
import com.pp.base.exception.BusinessException;
import com.pp.base.utils.DateFormatUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.Date;

public class BaseDomain implements Serializable {

    private static final long serialVersionUID = 1568387692161193316L;

    private String id;

    private String createCode;

    private String createName;

    private Date createTime;

    private String modifyCode;

    private String modifyName;

    private Date modifyTime;

    private Boolean hasDeleted;

    private String empId;

    private String empName;

    /**
     * 操作员手机号 TODO add session
     */
    private String empPhone;

    /**
     * 当前操作人员所属部门
     */
    private String deptId;

    private String deptName;

    /**
     * 创建者当时所属部门
     */
    private String createDeptId;

    private String createDeptName;

    private String companyId;

    private String companyName;

    private String bagPre;

    private String subBagPre;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateCode() {
        return createCode;
    }

    public void setCreateCode(String createCode) {
        this.createCode = createCode;
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

    public String getModifyCode() {
        return modifyCode;
    }

    public void setModifyCode(String modifyCode) {
        this.modifyCode = modifyCode;
    }

    public String getModifyName() {
        return modifyName;
    }

    public void setModifyName(String modifyName) {
        this.modifyName = modifyName;
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

    public String getEmpId() {
        return this.empId = empId == null ? null : empId.trim();
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpPhone() {
        return empPhone;
    }

    public void setEmpPhone(String empPhone) {
        this.empPhone = empPhone;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getBagPre() {
        return bagPre;
    }

    public void setBagPre(String bagPre) {
        this.bagPre = bagPre;
    }

    public String getSubBagPre() {
        return subBagPre;
    }

    public void setSubBagPre(String subBagPre) {
        this.subBagPre = subBagPre;
    }

    public static String getCompanyCode(){
        BaseDomain user = getEmp();
        return user.getCompanyId();
    }

    public static String getComName(){
        BaseDomain user = getEmp();
        return user.getCompanyName();
    }

    /**
     * 主袋前缀
     * @return
     */
    public static String getBagPreCode(){
        return getEmp().getBagPre();
    }

    /**
     * 副袋前缀
     * @return
     */
    public static String getSubBagPreCode(){
        return getEmp().getSubBagPre();
    }

    public static String getEmpCode(){
        BaseDomain user = getEmp();
        return user.getEmpId();
    }

    public static BaseDomain getEmp(){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
//        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
//        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
//        HttpServletRequest request = sra.getRequest();
        HttpSession session = request.getSession();
        if (null == session) {
            throw BusinessException.withErrorCode(ErrorConstant.Auth.NOT_LOGIN);
        }
        BaseDomain user = (BaseDomain) session.getAttribute(WebConst.LOGIN_SESSION_KEY);
        return user;
    }

    public void insertOperation(/*BaseDomain ce*/) {
        if (this.getCreateTime() == null) {
            this.setCreateTime(DateFormatUtil.getCurrentDateTime());
        }
        this.setModifyTime(DateFormatUtil.getCurrentDateTime());
        //String empCode = getEmpCode();
        BaseDomain user = getEmp();
        if (StringUtils.isNotEmpty(user.getEmpId())) {
            if(StringUtils.isBlank(this.getCompanyId())){
                this.setCompanyId(user.getCompanyId());
            }
            this.setCreateDeptId(user.getDeptId());
            this.setCreateCode(user.getEmpId());
            this.setModifyCode(user.getEmpId());
        }
    }

    public void updateOperation(/*BaseDomain ce*/) {
        this.setModifyTime(DateFormatUtil.getCurrentDateTime());
        String empCode = getEmpCode();
        if (StringUtils.isNotEmpty(empCode)) {
            //设置更新用户的编码
            this.setModifyCode(empCode);
        }
    }
}
