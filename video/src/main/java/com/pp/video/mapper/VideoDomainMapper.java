package com.pp.video.mapper;

import com.pp.video.domain.VideoDomain;
import com.pp.video.domain.VideoDomainExample;
import java.util.List;

public interface VideoDomainMapper {
    long countByExample(VideoDomainExample example);

    int deleteByPrimaryKey(String videoId);

    int insert(VideoDomain record);

    int insertSelective(VideoDomain record);

    List<VideoDomain> selectByExample(VideoDomainExample example);

    VideoDomain selectByPrimaryKey(String videoId);

    int updateByPrimaryKeySelective(VideoDomain record);

    int updateByPrimaryKey(VideoDomain record);
}