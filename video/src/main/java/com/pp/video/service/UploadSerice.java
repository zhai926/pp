package com.pp.video.service;

import com.pp.video.domain.VideoDomain;
import org.springframework.web.multipart.MultipartFile;

/**
 * 上传视频文件
 * Created by zhaihuilin on 2019/2/27 14:40.
 */

public interface UploadSerice {
  /**
   * 上传单个
   * @return
   */
  VideoDomain upload(MultipartFile multipartFile, String path);

}
