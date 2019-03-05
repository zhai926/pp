package com.pp.article.service.impl;

import com.pp.article.domain.CoreFileDomain;
import com.pp.article.domain.FileLinkDomain;
import com.pp.article.domain.FileLinkDomainExample;
import com.pp.article.mapper.FileLinkDomainMapper;
import com.pp.article.service.IFileLinkService;
import com.pp.article.service.IFileService;
import com.pp.base.constant.ErrorConstant;
import com.pp.base.domain.BaseDomain;
import com.pp.base.enums.EnumBoolean;
import com.pp.base.exception.BusinessException;
import com.pp.base.utils.DateFormatUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.util.List;

@Slf4j
@Service
public class FileLinkServiceImpl implements IFileLinkService {

    @Autowired
    private FileLinkDomainMapper fileLinkDomainMapper;
    @Autowired
    private IFileService fileService;

    @Override
    public int save(FileLinkDomain domain) {
        domain.setHasDeleted(EnumBoolean.FALSE.getVal());
        domain.setCreateCode(BaseDomain.getEmpCode());
        domain.setCreateTime(DateFormatUtil.getCurrentDateTime());
        domain.setModifyCode(BaseDomain.getEmpCode());
        domain.setModifyTime(DateFormatUtil.getCurrentDateTime());
        return fileLinkDomainMapper.insertSelective(domain);
    }

    @Override
    public int update(FileLinkDomain domain) {
        domain.setModifyCode(BaseDomain.getEmpCode());
        domain.setModifyTime(DateFormatUtil.getCurrentDateTime());
        return fileLinkDomainMapper.updateByPrimaryKeySelective(domain);
    }

    @Override
    public int deleteByRefId(String refId) {
        if(StringUtils.isBlank(refId)){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }

        int i =0 ;
        FileLinkDomainExample example = new FileLinkDomainExample();
        example.createCriteria().andRefIdEqualTo(refId);

        List<FileLinkDomain> list = fileLinkDomainMapper.selectByExample(example);
        if(!CollectionUtils.isEmpty(list)){
            // 删除本地文件,如果存在
            //删除db
            for(FileLinkDomain domain: list){
                CoreFileDomain fileDomain = fileService.get(domain.getFileId());
                if(null == fileDomain){
                    continue;
                }

                if(StringUtils.isBlank(fileDomain.getFilePath())){
                    fileService.delete(fileDomain.getId());
                    i += fileLinkDomainMapper.deleteByPrimaryKey(domain.getId());
                    continue;
                }
                String filePath = fileDomain.getFilePath();
                File file = new File(filePath);
                if(file.exists() && file.isFile()){
                    file.delete();
                }
                fileService.delete(fileDomain.getId());

                i += fileLinkDomainMapper.deleteByPrimaryKey(domain.getId());
            }
        }
        return i;
    }

    @Override
    public FileLinkDomain getByFileId(String fileId) {
        if(StringUtils.isBlank(fileId)){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        FileLinkDomainExample example = new FileLinkDomainExample();
        example.createCriteria().andFileIdEqualTo(fileId);
        List<FileLinkDomain> list = fileLinkDomainMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        return list.get(0);

    }

}
