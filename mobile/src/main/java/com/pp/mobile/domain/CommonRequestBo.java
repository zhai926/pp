package com.pp.mobile.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@ApiModel
@Data
public class CommonRequestBo {

    /**
     * 登录
     */
    @ApiModelProperty(value = "用户名,手机号")
    private String phone;
    @ApiModelProperty(value = "登录密码")
    private String password;

    @ApiModelProperty(value = "手机短信码")
    private String smsCode;


    private String val;
    private String type;

    @ApiModelProperty(value = "主键ID")
    private String id;

    /**
     * 增加地址
     */
    @ApiModelProperty(value = "联系人姓名")
    private String contactName;
    @ApiModelProperty(value = "联系人手机号")
    private String contactPhone;
    @ApiModelProperty(value = "省名称")
    private String provinceName;
    @ApiModelProperty(value = "城市名称")
    private String cityName;
    @ApiModelProperty(value = "区域名称")
    private String areaName;
    @ApiModelProperty(value = "详细地址")
    private String addressDetail;
    @ApiModelProperty(value = "地址Id")
    private String addressId;

    @ApiModelProperty(value = "*名称")
    private String name;

    @ApiModelProperty(value = "menuId")
    private String menuId;
    /**
     * 留言
     */
    @ApiModelProperty(value = "留言标题, title")
    private String title;
    @ApiModelProperty(value = "留言内容,content")
    private String content;

    /**
     * 分页
     */
    private Integer pageNum = 1;
    private Integer pageSize= 20;

    /**
     * 图片验证码
     */
    @ApiModelProperty(value = "图片验证码")
    private String imgCode;

    @ApiModelProperty(value = "文件CODE")
    private String fileId;
}
