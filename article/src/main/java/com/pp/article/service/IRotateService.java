package com.pp.article.service;

import com.github.pagehelper.PageInfo;
import com.pp.article.domain.CoreFileDomain;
import com.pp.article.domain.RotateDomain;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IRotateService {

    int save(MultipartFile multipartFile, String path, RotateDomain domain) throws IOException;

    /**
     * 保存轮播图, 更新fileService refId
     * @param fileId
     * @param domain
     * @return
     */
    int save(String fileId, RotateDomain domain);

    int delete(String id);

    RotateDomain get(String id);

    PageInfo<RotateDomain> page(RotateDomain domain, Integer start, Integer pageSize);
}
