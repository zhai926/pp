package com.pp.healthy.controller.file;

import com.pp.article.domain.CoreFileDomain;
import com.pp.article.service.IFileService;
import com.pp.base.common.RJResponse;
import com.pp.base.constant.ErrorConstant;
import com.pp.base.exception.BusinessException;
import com.pp.base.utils.DateFormatUtil;
import com.pp.healthy.domain.CommonRequestBo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


@CrossOrigin
@Slf4j
@Api("文件管理")
@Controller
@RequestMapping("/file")
public class FileController {

    @Autowired
    private IFileService fileService;

    /**
     * 在配置文件中配置的文件保存路径
     */
    @Value("${web.upload-path}")
    private String location;

    @ApiOperation("文件上传")
    @PostMapping("/upload")
    @ResponseBody
    public RJResponse uploadImg(@RequestBody CommonRequestBo commonRequestBo)  {
        if (commonRequestBo.getFile().isEmpty() || StringUtils.isBlank(commonRequestBo.getFile().getOriginalFilename())) {
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        String contentType = commonRequestBo.getFile().getContentType();
        if (!contentType.contains("")) {
            throw BusinessException.withErrorCode("文件类型错误");
        }
        String root_fileName = commonRequestBo.getFile().getOriginalFilename();
        log.info("上传图片:name={},type={}", root_fileName, contentType);
        //处理图片
//        User currentUser = userService.getCurrentUser();
        //获取路径
//        String return_path = ImageUtil.getFilePath(currentUser);
        String filePath = location + DateFormatUtil.getCurrentDate(DateFormatUtil.DateTimeFormatDay);
        log.info("文件保存路径={}", filePath);
//        String file_name = null;
        try {
            //file_name = ImageUtil.saveImg(multipartFile, filePath);
            CoreFileDomain coreFileDomain = fileService.save(commonRequestBo.getFile(), filePath);

            CoreFileDomain newFile = new CoreFileDomain();
            newFile.setFileId(coreFileDomain.getFileId());

            return RJResponse.success("文件保存成功",newFile);
        } catch (IOException e) {
            return RJResponse.fail("文件保存失败");
        }
    }

    @ApiOperation("文件上传")
    @PostMapping("/save")
    @ResponseBody
    public RJResponse save(@RequestParam("file") MultipartFile multipartFile)  {
        if (multipartFile.isEmpty() || StringUtils.isBlank(multipartFile.getOriginalFilename())) {
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        String contentType = multipartFile.getContentType();
        if (!contentType.contains("")) {
            throw BusinessException.withErrorCode("文件类型错误");
        }
        String root_fileName = multipartFile.getOriginalFilename();
        log.info("上传图片:name={},type={}", root_fileName, contentType);
        //处理图片
//        User currentUser = userService.getCurrentUser();
        //获取路径
//        String return_path = ImageUtil.getFilePath(currentUser);
        String filePath = location + DateFormatUtil.getCurrentDate(DateFormatUtil.DateTimeFormatDay);
        log.info("文件保存路径={}", filePath);
//        String file_name = null;
        try {
            //file_name = ImageUtil.saveImg(multipartFile, filePath);
            CoreFileDomain coreFileDomain = fileService.save(multipartFile, filePath);

            CoreFileDomain newFile = new CoreFileDomain();
            newFile.setFileId(coreFileDomain.getFileId());

            return RJResponse.success("文件保存成功",newFile);
//            MarkDVo markDVo = new MarkDVo();
//            markDVo.setSuccess(0);
//            if(StringUtils.isNotBlank(file_name)){
//                markDVo.setSuccess(1);
//                markDVo.setMessage("上传成功");
//                markDVo.setUrl(return_path+File.separator+file_name);
//                markDVo.setCallback(callback);
//            }
        } catch (IOException e) {
            //throw BusinessException.withErrorCode("文件保存失败");
            return RJResponse.fail("文件保存失败");
        }
    }

    // 静态资源访问 例  http://localhost:7002/2018-09-25/F20180925000834a1.jpg

    /**
     * IO流读取图片
     * @return
     */
    @ApiOperation("读取验证图片")
    @GetMapping(value = "/img/{fileCode}")
    public void IoReadImage(@PathVariable String fileCode, HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletOutputStream out = null;
        FileInputStream ips = null;
        try {

            if(StringUtils.isBlank(fileCode)){
                throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
            }

            CoreFileDomain coreFileDomain = fileService.get(fileCode);
            if(null == coreFileDomain){
                return;
            }

            //获取图片存放路径
//            String imgPath = Constans.FOLDER_IMAGE + imgName;
            ips = new FileInputStream(new File(coreFileDomain.getFilePath()));
            //response.setContentType("multipart/form-data"); // 浏览器直接访问 提示下载
            response.setHeader("Cache-Control", "no-store");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/jpeg");
            out = response.getOutputStream();
            //读取文件流
            int len = 0;
            byte[] buffer = new byte[1024 * 10];
            while ((len = ips.read(buffer)) != -1){
                out.write(buffer,0,len);
            }
            out.flush();

//            response.setHeader("Cache-Control", "no-store");
//            response.setHeader("Pragma", "no-cache");
//            response.setDateHeader("Expires", 0);
//            response.setContentType("image/jpeg");
//            ServletOutputStream responseOutputStream = response.getOutputStream();
//            responseOutputStream.write(captchaChallengeAsJpeg);
//            responseOutputStream.flush();
//            responseOutputStream.close();

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
