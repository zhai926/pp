package com.pp.article.service;

import com.pp.base.utils.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@Slf4j
public class ImageUtil {


    /**
     * 保存文件，直接以multipartFile形式
     * @param multipartFile
     * @param path 文件保存绝对路径
     * @return 返回文件名
     * @throws IOException
     */
    public static String saveImg(MultipartFile multipartFile, String path, String fileId) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        String originalFilename = multipartFile.getOriginalFilename();
        log.info("------------ fileName "+ originalFilename);
        FileInputStream fileInputStream = (FileInputStream) multipartFile.getInputStream();
//        String fileName = UUID.UU32()+ "." + originalFilename.split(".")[1];
        String fileName = fileId+ "." + originalFilename.split("\\.")[1];
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path + File.separator + fileName));
        byte[] bs = new byte[1024];
        int len;
        while ((len = fileInputStream.read(bs)) != -1) {
            bos.write(bs, 0, len);
        }
        bos.flush();
        bos.close();
        return fileName;
    }

}
