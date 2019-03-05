package com.pp.goods.service;

import com.github.pagehelper.PageInfo;
import com.pp.goods.domain.GoodsGiftDomain;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IGoodsGiftService {
    int save(MultipartFile multipartFile, String path, GoodsGiftDomain domain) throws IOException;

    String save(GoodsGiftDomain domain);

    String save(String fileId, GoodsGiftDomain domain);

    int delete(String id);

    GoodsGiftDomain get(String id);

    PageInfo<GoodsGiftDomain> page(GoodsGiftDomain domain, Integer start, Integer pageSize);
}
