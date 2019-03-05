package com.pp.healthy.controller.file;

import com.pp.base.common.RJResponse;
import com.pp.base.constant.ErrorConstant;
import com.pp.base.exception.BusinessException;
import com.pp.video.domain.VideoDomain;
import com.pp.video.service.UploadSerice;
import com.pp.video.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by zhaihuilin on 2019/2/27 15:38.
 */
@CrossOrigin
@Slf4j
@Api("上传管理")
@Controller
@RequestMapping("/upload")
public class UploadController {

  @Autowired
  private UploadSerice uploadService;

  @Autowired
  private VideoService videoService;

  /**
   * 链接地址
   */
  @Value(value = "${upload.baseurl}")
  public String BASE_URL;

  @Value("${web.upload-path}")
  private String location;

  @PostMapping(value = "/upload")
  @ResponseBody
  @ApiOperation("文件上传")
  public RJResponse upload(MultipartFile file){
    VideoDomain uploadInfo = uploadService.upload(file,location);
    if(uploadInfo != null){
      return RJResponse.success("文件保存成功",uploadInfo);
    }
    return RJResponse.success("上传失败。",null);
  }

  /**
   * IO流读取图片
   * @return
   */
  @ApiOperation("读取视频")
  @GetMapping(value = "/video/{fileCode}")
  public void IoReadVideo(@PathVariable String fileCode, HttpServletRequest request, HttpServletResponse response) throws IOException {
    ServletOutputStream out = null;
    FileInputStream ips = null;
    try {

      if(StringUtils.isBlank(fileCode)){
        throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
      }

      if("null".equals(fileCode)){
        log.error("-------------------- 视频编码空 -------------");
        return;
      }
      VideoDomain videoDomain = videoService.get(fileCode);

      //获取视频存放路径
      ips = new FileInputStream(new File(videoDomain.getVideoPath()));
      response.setHeader("Cache-Control", "no-store");
      response.setHeader("Pragma", "no-cache");
      response.setDateHeader("Expires", 0);
      //response.setContentType("video/mpeg4");
      out = response.getOutputStream();
      //读取文件流
      int len = 0;
      byte[] buffer = new byte[1024 * 10];
      while ((len = ips.read(buffer)) != -1){
        out.write(buffer,0,len);
      }
      out.flush();
    }catch (Exception e){
      e.printStackTrace();
    }finally {
      if(null != out){
        out.close();
      }
      if(null != ips){
        ips.close();
      }
    }
  }







}
