package com.pp.video.service;

import com.github.pagehelper.PageInfo;
import com.pp.video.domain.VideoDomain;

import java.util.List;

/**
 * Created by zhaihuilin on 2019/2/27 13:07.
 */
public interface VideoService {

    //上传视频文件
    //VideoDomain  upload(MultipartFile multipartFile, String path);

    //新增
    int save(VideoDomain domain);

    //删除
    int delete(String videoId);

    //通过编号进行获取
    VideoDomain get(String videoId);

    //获取所有的列表
    List<VideoDomain> list(VideoDomain domain);

    //分页
    PageInfo<VideoDomain> page(VideoDomain domain, Integer start, Integer pageSize);
}
