package com.pp.article.service;

import com.pp.article.domain.CoreFileDomain;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IFileService {

    CoreFileDomain save(MultipartFile multipartFile, String path) throws IOException;

    CoreFileDomain get(String fileCode);

    int delete(Integer fileCode);
}
