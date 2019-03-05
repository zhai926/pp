package com.pp.healthy.controller.video;

import com.github.pagehelper.PageInfo;
import com.pp.base.common.RJResponse;
import com.pp.base.controller.BaseController;
import com.pp.base.enums.EnumBoolean;
import com.pp.base.exception.BusinessException;
import com.pp.healthy.annotation.UserLoginToken;
import com.pp.healthy.domain.CommonRequestBo;
import com.pp.video.domain.VideoDomain;
import com.pp.video.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zhaihuilin on 2019/2/27 13:57.
 */
@CrossOrigin
@Slf4j
@Api("视频管理")
@RestController
@RequestMapping("/video")
public class VideoController extends BaseController {

   @Autowired
   private VideoService videoService;


   /**
    * 视频新增 【视频链接的形式】
    * @param commonRequestBo
    * @return
    */
   @UserLoginToken
   @ApiOperation("视频保存")
   @PostMapping("/save")
   public RJResponse save(
           @RequestBody CommonRequestBo commonRequestBo
   ){
      try {
         VideoDomain domain  = new VideoDomain();
         domain.setVideoName(commonRequestBo.getVideoName());
         domain.setVideoPath(commonRequestBo.getVideoPath());
         domain.setVideoUrl(commonRequestBo.getVideoUrl());
         //保存
         videoService.save(domain);
         return RJResponse.success("保存成功");
      }catch (Exception e){
         System.out.println("错误的原因:" + e.getMessage());
         return RJResponse.fail(exceptionMsg(e, "保存失败"));
      }

   }


   @UserLoginToken
   @ApiOperation("视频删除")
   @PostMapping("/delete/{id}")
   public RJResponse delete(@ApiParam(name = "id", value = "编号", required = true) @PathVariable String id)  {

      if (StringUtils.isBlank(id)) {
         throw new BusinessException("请重新选择");
      }
      try {
         videoService.delete(id);
         return RJResponse.success("删除成功");
      } catch (Exception e) {
         return RJResponse.fail(exceptionMsg(e, "删除失败"));
      }
   }


   /**
    * 详细
    * @return
    */
   @UserLoginToken
   @ApiOperation("详细")
   @PostMapping(value = "/detail/{id}")
   public RJResponse detail(@ApiParam(name = "id", value = "轮播编码", required = true) @PathVariable String id) {
      try {
         VideoDomain domain = videoService.get(id);
         return RJResponse.success(domain);
      } catch (Exception e) {
         return RJResponse.fail(exceptionMsg(e, "查询失败"));
      }
   }


   /**
    * 分页查询
    * @return
    */
   @UserLoginToken
   @ApiOperation("分页查询")
   @PostMapping(value = "/page")
   public RJResponse page(
           @RequestBody CommonRequestBo commonRequestBo
   ) {
      try {
         VideoDomain domain = new VideoDomain();
         if(StringUtils.isNotBlank(commonRequestBo.getVideoName())){
            domain.setVideoName(commonRequestBo.getVideoName());
         }
         domain.setIsdel(EnumBoolean.FALSE.getVal());
         PageInfo<VideoDomain> pageInfo = videoService.page(domain, commonRequestBo.getPageNum(), commonRequestBo.getPageSize());
         // 重新封装pageInfo
         return RJResponse.page(pageInfo);
      } catch (Exception e) {
         return RJResponse.fail(exceptionMsg(e, "查询失败"));
      }
   }


}
