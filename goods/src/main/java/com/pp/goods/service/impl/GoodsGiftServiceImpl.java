package com.pp.goods.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pp.article.domain.CoreFileDomain;
import com.pp.article.domain.FileLinkDomain;
import com.pp.article.service.IFileLinkService;
import com.pp.article.service.IFileService;
import com.pp.base.constant.ErrorConstant;
import com.pp.base.enums.EnumBoolean;
import com.pp.base.exception.BusinessException;
import com.pp.base.service.ICodeGenerateService;
import com.pp.goods.domain.GoodsGiftDomain;
import com.pp.goods.domain.GoodsGiftDomainExample;
import com.pp.goods.mapper.GoodsGiftDomainMapper;
import com.pp.goods.service.IGoodsGiftService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class GoodsGiftServiceImpl implements IGoodsGiftService {

    @Autowired
    private GoodsGiftDomainMapper goodsGiftDomainMapper;
    @Autowired
    private ICodeGenerateService codeGenerateService;
    @Autowired
    private IFileService fileService;
    @Autowired
    private IFileLinkService fileLinkService;

    @Override
    public int save(MultipartFile multipartFile, String path, GoodsGiftDomain domain) throws IOException {

        String id = this.save(domain);
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

    @Override
    public String save(GoodsGiftDomain domain){
        if(StringUtils.isBlank(domain.getId())){
            domain.setId(codeGenerateService.makeGoodsGiftCode());
//            domain.setCreateTime(DateFormatUtil.getCurrentDateTime());
//            domain.setModifyTime(DateFormatUtil.getCurrentDateTime());
            domain.insertOperation();
            goodsGiftDomainMapper.insertSelective(domain);
        }else{
            goodsGiftDomainMapper.updateByPrimaryKeySelective(domain);
        }
        return domain.getId();
    }

    @Override
    @Transactional
    public String save(String fileId, GoodsGiftDomain domain) {
        FileLinkDomain fileLinkDomain = null;
        if(StringUtils.isNotBlank(fileId)){
            fileLinkDomain = fileLinkService.getByFileId(fileId);
        }

        String id = this.save(domain);

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
        return id;
    }

    @Override
    public int delete(String id) {
        if(StringUtils.isBlank(id)){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }

        fileLinkService.deleteByRefId(id);
        return goodsGiftDomainMapper.deleteByPrimaryKey(id);
    }

    @Override
    public GoodsGiftDomain get(String id){
        return goodsGiftDomainMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<GoodsGiftDomain> page(GoodsGiftDomain domain, Integer start, Integer pageSize) {
        if(null == domain){
            domain = new GoodsGiftDomain();
        }

        GoodsGiftDomainExample example = new GoodsGiftDomainExample();
        GoodsGiftDomainExample.Criteria criteria = example.createCriteria();

        if(StringUtils.isNotBlank(domain.getType())){
            criteria.andTypeEqualTo(domain.getType());
        }
        if(StringUtils.isNotBlank(domain.getName())){
            criteria.andNameLike("%" + domain.getName() + "%");
        }
        if(StringUtils.isNotBlank(domain.getContent())){
            criteria.andContentLike("%" + domain.getContent() + "%");
        }
        if(StringUtils.isNotBlank(domain.getUrl())){
            criteria.andUrlLike("%" + domain.getUrl() + "%");
        }
        if(null != domain.getHasDeleted()){
            criteria.andHasDeletedEqualTo(domain.getHasDeleted());
        }

        example.setOrderByClause(" order_by asc ");

        PageHelper.startPage(start, pageSize);
        List<GoodsGiftDomain> list = goodsGiftDomainMapper.selectByExample(example);
        return new PageInfo<>(list);
    }
}
