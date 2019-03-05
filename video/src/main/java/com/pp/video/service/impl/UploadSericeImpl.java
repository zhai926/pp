package com.pp.video.service.impl;

import com.pp.video.domain.VideoDomain;
import com.pp.video.service.UploadSerice;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.apache.http.util.TextUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;

/**
 * Created by zhaihuilin on 2019/2/27 14:41.
 */
@Service
@Log4j2
public class UploadSericeImpl implements UploadSerice {

  private SimpleDateFormat folder = new SimpleDateFormat("yyyy-MM-dd");
  // 文件最大500M
  private static long upload_maxsize = 5000 * 1024 * 1024;
  // 文件允许格式
  private static String[] allowFiles = { ".rar", ".doc", ".docx", ".zip", ".pdf", ".txt", ".swf", ".xlsx", ".gif",
          ".png", ".jpg", ".jpeg", ".bmp", ".xls", ".mp4", ".flv", ".ppt", ".avi", ".mpg", ".wmv", ".3gp", ".mov",
          ".asf", ".asx", ".vob", ".wmv9", ".rm", ".rmvb" };
  // 允许转码的视频格式（ffmpeg）
  private static String[] allowFLV = { ".avi", ".mpg", ".wmv", ".3gp", ".mov", ".asf", ".asx", ".vob" ,".mkv"};
  // 允许的视频转码格式(mencoder)
  private static String[] allowAVI = { ".wmv9", ".rm", ".rmvb" };


  @Override
  public VideoDomain upload(MultipartFile multipartFile, String path) {
    VideoDomain videoDomain  = new VideoDomain();
    Date date = new Date();
    boolean bflag = false;
    //生成时间
    String dirPath = folder.format(date);
    String fileName = multipartFile.getOriginalFilename().toString();
    log.info("上传的文件名称：" + fileName);
    String videoName = null;
    //1.判断文件不能为空
    if (multipartFile.getSize() != 0 && !multipartFile.isEmpty()) {
         bflag = true;
         //第二步.判断上传的文件大小是否超过了设置的最大值
         if (multipartFile.getSize()<=upload_maxsize){
           bflag = true;
           //第三步: 判断上传的文件类型
           if (this.checkFileType(fileName)){
              bflag = true;
           }else{
              bflag = false;
              log.info("文件的格式不正确...");
           }
         }else{
           bflag = false;
           log.info("上传的文件超过了设定的大小");
         }
    }else{
      bflag = false;
      log.info("文件为空");
    }

    //2.开始处理文件
    if (bflag){
        //创建上传文件地址
        createDirectory(path + dirPath);
        //获取文件的名称
        String name = fileName.substring(0, fileName.lastIndexOf("."));
        System.out.println("不带后缀的文件名称：" + name);
        //新的文件名
        String newFileName = this.getName(name);
        log.info("生成的新的文件名:" + newFileName);
        //获取文件的扩展名
        String fileEnd = this.getFileExt(fileName);
        log.info("获取的文件的后缀名:" + fileEnd);
        //生成 videoName
        videoName = newFileName + fileEnd;
        //文件的绝对路劲
        String filePath = path +  dirPath + "/" + videoName;
        log.info("文件的绝对路劲是:" + filePath);
        File file = new File(filePath);
        //获取文件的大小
        String filesize = this.getSize(file);
        log.info("文件的大小:" + filesize);
        try {
          //判断文件是否存在
          if (!file.exists()){
            file.createNewFile();
            FileUtils.writeByteArrayToFile(file ,multipartFile.getBytes());
          }
        }catch (Exception e){
          e.printStackTrace();
        }
        // 3. 保存文件
        videoDomain.setType(fileEnd); //文件类型
        videoDomain.setVideoName(fileName);//文件名称
        videoDomain.setVideoPath(filePath); //文件路劲
        videoDomain.setFileSize(filesize);//文件大小
        videoDomain.setCreateTime(new Date());
    }else{
      return null;
    }
    return videoDomain;
  }


  /**
   * 判断路径是否创建
   * @param path
   * @return
   */
  public boolean createDirectory(String path) {
    boolean falg = true;
    File file = new File(path);
    if(!file.exists()){
      file.mkdirs();
    }else{
      if(!file.isDirectory()){
        file.mkdirs();
      }
    }
    return falg;
  }



  /**
   * 文件类型判断
   *
   * @param fileName
   * @return
   */
  private boolean checkFileType(String fileName) {
    Iterator<String> type = Arrays.asList(allowFiles).iterator();
    while (type.hasNext()) {
      String ext = type.next();
      if (fileName.toLowerCase().endsWith(ext)) {
        return true;
      }
    }
    return false;
  }


  /**
   * 视频类型判断(flv)
   *
   * @param
   * @return
   */
  private boolean checkMediaType(String fileEnd) {
    Iterator<String> type = Arrays.asList(allowFLV).iterator();
    while (type.hasNext()) {
      String ext = type.next();
      if (fileEnd.equals(ext)) {
        return true;
      }
    }
    return false;
  }


  /**
   * 视频类型判断(AVI)
   *
   * @param
   * @return
   */
  private boolean checkAVIType(String fileEnd) {
    Iterator<String> type = Arrays.asList(allowAVI).iterator();
    while (type.hasNext()) {
      String ext = type.next();
      if (fileEnd.equals(ext)) {
        return true;
      }
    }
    return false;
  }


  /**
   * 获取文件扩展名
   *
   * @return string
   */
  private String getFileExt(String fileName) {
    return fileName.substring(fileName.lastIndexOf("."));
  }


  /**
   * 依据原始文件名生成新文件名
   * UUID：全局唯一标识符，由一个十六位的数字组成,由三部分组成：当前日期和时间、时钟序列、全局唯一的IEEE机器识别号
   * @return string
   */
  private String getName(String fileName) {
    Random random = new Random();
    return "" + random.nextInt(10000) + System.currentTimeMillis();
  }


  /**
   * 文件大小，返回kb.mb
   * 
   * @return
   */
  private String getSize(File file) {
    String size = "";
    long fileLength = file.length();
    DecimalFormat df = new DecimalFormat("#.00");
    if (fileLength < 1024) {
      size = df.format((double) fileLength) + "BT";
    } else if (fileLength < 1048576) {
      size = df.format((double) fileLength / 1024) + "KB";
    } else if (fileLength < 1073741824) {
      size = df.format((double) fileLength / 1048576) + "MB";
    } else {
      size = df.format((double) fileLength / 1073741824) + "GB";
    }
    return size;
  }




  /**
   * Get the Mime Type from a File
   * @param fileName 文件名
   * @return 返回MIME类型
   * thx https://www.oschina.net/question/571282_223549
   * add by fengwenhua 2017年5月3日09:55:01
   */
  private static String getMimeType(String fileName) {
    FileNameMap fileNameMap = URLConnection.getFileNameMap();
    String type = fileNameMap.getContentTypeFor(fileName);
    return type;
  }

  /**
   * 根据文件后缀名判断 文件是否是视频文件
   * @param fileName 文件名
   * @return 是否是视频文件
   */
  public static boolean isVedioFile(String fileName){
    String mimeType = getMimeType(fileName);
    if (!TextUtils.isEmpty(fileName)){
      return true;
    }
    return false;
  }




























































}
