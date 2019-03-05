package com.pp.article.service.impl;

import com.pp.article.domain.CoreFileDomain;
import com.pp.article.domain.CoreFileDomainExample;
import com.pp.article.mapper.CoreFileDomainMapper;
import com.pp.article.service.IFileService;
import com.pp.article.service.ImageUtil;
import com.pp.base.constant.ErrorConstant;
import com.pp.base.domain.BaseDomain;
import com.pp.base.enums.EnumBoolean;
import com.pp.base.exception.BusinessException;
import com.pp.base.service.ICodeGenerateService;
import com.pp.base.utils.DateFormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class FileServiceImpl implements IFileService {

    @Autowired
    private CoreFileDomainMapper coreFileDomainMapper;
    @Autowired
    private ICodeGenerateService codeGenerateService;

    @Override
    public CoreFileDomain save(MultipartFile multipartFile, String path) throws IOException {
        String fileName = multipartFile.getOriginalFilename();
        String suffix = fileName.split("\\.")[1];
        String contentType = multipartFile.getContentType();
        String fileCode = codeGenerateService.makeFileCode();
        String returnName = ImageUtil.saveImg(multipartFile, path, fileCode);

        String filePath = path + File.separator + returnName;

        CoreFileDomain domain = new CoreFileDomain();
        domain.setFileId(fileCode);
        domain.setFileType(suffix);
        domain.setFileTypeDesc(contentType);
        domain.setFileName(fileName);
        domain.setFilePath(filePath);
        domain.setStatus(EnumBoolean.TRUE.getStrCode());
        if(null != BaseDomain.getEmp()){
            domain.setCreateBy(BaseDomain.getEmpCode());
            domain.setUpdateBy(domain.getCreateBy());
        }else{
            domain.setCreateBy("000");
            domain.setUpdateBy("000");
        }
        domain.setCreateTime(DateFormatUtil.getCurrentDateTime());
        domain.setUpdateTime(DateFormatUtil.getCurrentDateTime());
        domain.setIsDeleted(EnumBoolean.FALSE.getValNum());

        coreFileDomainMapper.insertSelective(domain);

        return domain;
    }

    @Override
    public CoreFileDomain get(String fileCode){

        CoreFileDomainExample example = new CoreFileDomainExample();
        example.createCriteria().andFileIdEqualTo(fileCode);
        List<CoreFileDomain> list = coreFileDomainMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(list)){
            //throw BusinessException.withErrorCode(ErrorConstant.Common.NOT_FOND);
            return null;
        }
        return list.get(0);
    }

    @Override
    public int delete(Integer fileCode) {
        return coreFileDomainMapper.deleteByPrimaryKey(fileCode);
    }
}
