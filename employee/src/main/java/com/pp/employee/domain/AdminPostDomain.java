package com.pp.employee.domain;

import com.pp.base.domain.BaseDomain;
import com.pp.base.enums.EnumONOFF;
import org.apache.commons.lang3.StringUtils;

public class AdminPostDomain extends BaseDomain {

    private String name;

    private String status;

    private String description;

    private Integer postLevel;

    private String statusName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getPostLevel() {
        return postLevel;
    }

    public void setPostLevel(Integer postLevel) {
        this.postLevel = postLevel;
    }

    public String getStatusName() {
        if(StringUtils.isNotBlank(status)){
            return EnumONOFF.get(status).getDesc();
        }
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}