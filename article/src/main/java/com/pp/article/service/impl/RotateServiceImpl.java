package com.pp.article.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pp.article.domain.CoreFileDomain;
import com.pp.article.domain.FileLinkDomain;
import com.pp.article.domain.RotateDomain;
import com.pp.article.domain.RotateDomainExample;
import com.pp.article.mapper.RotateDomainMapper;
import com.pp.article.service.IFileLinkService;
import com.pp.article.service.IFileService;
import com.pp.article.service.IRotateService;
import com.pp.base.constant.ErrorConstant;
import com.pp.base.enums.EnumBoolean;
import com.pp.base.exception.BusinessException;
import com.pp.base.service.ICodeGenerateService;
import com.pp.base.utils.DateFormatUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class RotateServiceImpl implements IRotateService {

    @Autowired
    private RotateDomainMapper rotateDomainMapper;
    @Autowired
    private ICodeGenerateService codeGenerateService;
    @Autowired
    private IFileService fileService;
    @Autowired
    private IFileLinkService fileLinkService;

    @Override
    @Transactional
    public int save(MultipartFile multipartFile, String path, RotateDomain domain) throws IOException {

        String id = domain.getId();
        if(StringUtils.isBlank(id)){
            id = codeGenerateService.makeRoleCode();
            domain.setId(id);
//            domain.setCreateTime(DateFormatUtil.getCurrentDateTime());
//            domain.setModifyTime(DateFormatUtil.getCurrentDateTime());
            domain.insertOperation();
            rotateDomainMapper.insertSelective(domain);
        }else{
            rotateDomainMapper.updateByPrimaryKeySelective(domain);
        }

        // 保存文件
        if(StringUtils.isBlank(domain.getFileId())){
            CoreFileDomain fileDomain = fileService.save(multipartFile, path);
            FileLinkDomain fileLinkDomain = new FileLinkDomain();
            fileLinkDomain.setFileId(fileDomain.getFileId());
            fileLinkDomain.setRefId(id);
            fileLinkDomain.setHasDeleted(EnumBoolean.FALSE.getVal());
            fileLinkService.save(fileLinkDomain);
        }
        return 1;
    }

    @Transactional
    @Override
    public int save(String fileId, RotateDomain domain) {
        FileLinkDomain fileLinkDomain = null;
        if(StringUtils.isNotBlank(fileId)){
            fileLinkDomain = fileLinkService.getByFileId(fileId);
        }

        String id = domain.getId();
        if(StringUtils.isBlank(id)){
            id = codeGenerateService.makeRoleCode();
            domain.setId(id);
//            domain.setCreateTime(DateFormatUtil.getCurrentDateTime());
//            domain.setModifyTime(DateFormatUtil.getCurrentDateTime());
            domain.insertOperation();
            rotateDomainMapper.insertSelective(domain);
        }else{
            rotateDomainMapper.updateByPrimaryKeySelective(domain);
        }


        if(null != fileLinkDomain){
            fileLinkDomain.setRefId(id);
            fileLinkService.update(fileLinkDomain);
        }else{
            // 新增图片时 删除之前的图片
            if(StringUtils.isNotBlank(fileId)){
                fileLinkService.deleteByRefId(id);
                fileLinkDomain = new FileLinkDomain();
                fileLinkDomain.setFileId(fileId);
                fileLinkDomain.setRefId(id);
                fileLinkDomain.setHasDeleted(EnumBoolean.FALSE.getVal());
                fileLinkService.save(fileLinkDomain);
            }
        }
        return 1;
    }



    @Override
    public int delete(String id) {
        if(StringUtils.isBlank(id)){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }

        fileLinkService.deleteByRefId(id);
        return rotateDomainMapper.deleteByPrimaryKey(id);
    }

    @Override
    public RotateDomain get(String id){
        return rotateDomainMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<RotateDomain> page(RotateDomain domain, Integer start, Integer pageSize) {
        if(null == domain){
            domain = new RotateDomain();
        }

        RotateDomainExample example = new RotateDomainExample();
        RotateDomainExample.Criteria criteria = example.createCriteria();

        if(StringUtils.isNotBlank(domain.getContent())){
            criteria.andContentLike("%" + domain.getContent() + "%");
        }
        if(StringUtils.isNotBlank(domain.getUrl())){
            criteria.andUrlLike("%" + domain.getUrl() + "%");
        }
        if(null != domain.getHasDeleted()){
            criteria.andHasDeletedEqualTo(domain.getHasDeleted());
        }

        example.setOrderByClause(" id asc ");

        PageHelper.startPage(start, pageSize);
        List<RotateDomain> list = rotateDomainMapper.selectByExample(example);
        return new PageInfo<>(list);
    }
}
