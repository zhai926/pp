package com.pp.video.domain;

/**
 * 上传信息
 * Created by zhaihuilin on 2019/1/4 15:57.
 */
public class UploadInfo {
  /**
   * 链接地址
   */
  private String url;

  /**
   * 后缀名
   */
  private String suffix;

  /**
   * 大小
   */
  private long size;


  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getSuffix() {
    return suffix;
  }

  public void setSuffix(String suffix) {
    this.suffix = suffix;
  }

  public long getSize() {
    return size;
  }

  public void setSize(long size) {
    this.size = size;
  }
}
