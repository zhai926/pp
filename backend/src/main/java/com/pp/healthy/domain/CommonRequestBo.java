package com.pp.healthy.domain;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
public class CommonRequestBo {


    //视频
    private String videoId;
    private String videoName;
    private String videoPath;
    private String videoUrl;
    private Date createTime;
    private Boolean isdel;
    private Date updateTime;
    private String fileSize;


    /**
     * 登录
     */
    private String username;
    private String password;

    /**
     * 文章菜单
     */
    private String defaultFlg;
    private String type;

    /**
     * 文章内容
     */
    private String id;
    private String menuId;
    private String content;
    private String name;
    private Integer orderBy;

    /**
     * goods
     */
    private String url;

    /**
     * 用户礼品
     */
    private String memberName;



    /**
     * 分页
     */
    private Integer pageNum = 1;
    private Integer pageSize= 50;

    private String fileId;
    private MultipartFile file;
}
