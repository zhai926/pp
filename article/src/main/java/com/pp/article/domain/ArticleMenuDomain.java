package com.pp.article.domain;

import com.github.pagehelper.PageInfo;
import com.pp.base.domain.BaseDomain;

import java.util.List;

public class ArticleMenuDomain extends BaseDomain {
//    private String id;

    private Integer orderBy;

    private String defaultFlg;

    private String name;

    private String type;

    private String cate;

    private String status;

    private String defaultName;

    private String defaultContent;

    private List<ArticleContentDomain> conList;

    private PageInfo<ArticleContentDomain> conPage;

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

    public Integer getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(Integer orderBy) {
        this.orderBy = orderBy;
    }

    public String getDefaultFlg() {
        return defaultFlg;
    }

    public void setDefaultFlg(String defaultFlg) {
        this.defaultFlg = defaultFlg == null ? null : defaultFlg.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getCate() {
        return cate;
    }

    public void setCate(String cate) {
        this.cate = cate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getDefaultName() {
        return defaultName;
    }

    public void setDefaultName(String defaultName) {
        this.defaultName = defaultName;
    }

    public String getDefaultContent() {
        return defaultContent;
    }

    public void setDefaultContent(String defaultContent) {
        this.defaultContent = defaultContent;
    }

    public List<ArticleContentDomain> getConList() {
        return conList;
    }

    public void setConList(List<ArticleContentDomain> conList) {
        this.conList = conList;
    }

    public PageInfo<ArticleContentDomain> getConPage() {
        return conPage;
    }

    public void setConPage(PageInfo<ArticleContentDomain> conPage) {
        this.conPage = conPage;
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