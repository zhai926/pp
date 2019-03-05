package com.pp.video.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pp.base.constant.ErrorConstant;
import com.pp.base.enums.EnumBoolean;
import com.pp.base.exception.BusinessException;
import com.pp.base.service.ICodeGenerateService;
import com.pp.video.domain.VideoDomain;
import com.pp.video.domain.VideoDomainExample;
import com.pp.video.mapper.VideoDomainMapper;
import com.pp.video.service.VideoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by zhaihuilin on 2019/2/27 13:16.
 */
@Service
public class VideoServiceImpl implements VideoService {

  @Autowired
  private VideoDomainMapper videoDomainMapper;

  @Autowired
  private ICodeGenerateService codeGenerateService;

  //新增
  @Override
  public int save(VideoDomain domain) {
    //判断编号是否为空
    if (StringUtils.isBlank(domain.getVideoId())){
         domain.setVideoId(codeGenerateService.makeVideoCode());
         domain.setIsdel(EnumBoolean.FALSE.getVal());
         domain.setCreateTime(new Date());
         return videoDomainMapper.insertSelective(domain);//保存
    }else {
         domain.setUpdateTime(new Date());
         return videoDomainMapper.updateByPrimaryKeySelective(domain);
    }
  }

  //删除
  @Override
  public int delete(String videoId) {
    if(StringUtils.isBlank(videoId)){
      throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
    }
    return videoDomainMapper.deleteByPrimaryKey(videoId);
  }

  //根据编号进行查询
  @Override
  public VideoDomain get(String videoId) {
    if(StringUtils.isBlank(videoId)){
      throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
    }
    return videoDomainMapper.selectByPrimaryKey(videoId);
  }

  //查询
  @Override
  public PageInfo<VideoDomain> page(VideoDomain domain, Integer start, Integer pageSize) {
    if (domain == null){
       domain = new VideoDomain();
    }

    VideoDomainExample example = new VideoDomainExample();
    VideoDomainExample.Criteria  criteria  = example.createCriteria();
    VideoDomainExample.Criteria  criteriaOR  = example.createCriteria();

    //判断
    if (StringUtils.isNotBlank(domain.getVideoName())){
      criteria.andVideoNameLike("%"+domain.getVideoName()+"%");
      criteriaOR.andVideoNameLike("%"+domain.getVideoName()+"%");
    }

    example.or(criteriaOR);
    example.setOrderByClause(" order_by asc");

    PageHelper.startPage(start, pageSize);

    List<VideoDomain> list =videoDomainMapper.selectByExample(example);
    //声明一个数组
    List<VideoDomain> newList = new ArrayList<>();
    //获取最后一个对象
    if (list!=null && list.size()>0){
         newList.add(list.get(list.size() -1));
    }
    if (newList!=null && newList.size()>0){
      return new PageInfo<>(newList);
    }
    return new PageInfo<>(list);
  }

  @Override
  public List<VideoDomain> list(VideoDomain domain) {
    if (domain == null){
      domain = new VideoDomain();
    }

    VideoDomainExample example = new VideoDomainExample();
    VideoDomainExample.Criteria  criteria  = example.createCriteria();
    VideoDomainExample.Criteria  criteriaOR  = example.createCriteria();

    //判断
    if (StringUtils.isNotBlank(domain.getVideoName())){
      criteria.andVideoNameLike("%"+domain.getVideoName()+"%");
      criteriaOR.andVideoNameLike("%"+domain.getVideoName()+"%");
    }

    example.or(criteriaOR);
    example.setOrderByClause(" order_by asc");

    List<VideoDomain> list =videoDomainMapper.selectByExample(example);
    //声明一个数组
    List<VideoDomain> newList = new ArrayList<>();
    //获取最后一个对象
    if (list!=null && list.size()>0){
      newList.add(list.get(list.size() -1));
    }
    if (newList!=null && newList.size()>0){
      return newList;
    }
    return newList;
  }
}
