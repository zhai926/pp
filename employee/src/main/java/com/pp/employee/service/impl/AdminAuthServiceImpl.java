package com.pp.employee.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pp.base.constant.ErrorConstant;
import com.pp.base.exception.BusinessException;
import com.pp.base.service.ICodeGenerateService;
import com.pp.base.service.IRedisService;
import com.pp.base.utils.CodeRedisHelper;
import com.pp.employee.domain.AdminAuthDomainExample;
import com.pp.employee.mapper.AdminAuthDomainMapper;
import com.pp.employee.service.IAdminAuthService;
import com.pp.employee.domain.AdminAuthDomain;
import com.pp.employee.dto.SpecialAuthDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class AdminAuthServiceImpl implements IAdminAuthService {

    @Autowired
    private AdminAuthDomainMapper adminAuthDomainMapper;
    @Autowired
    private ICodeGenerateService codeGenerateService;
    @Autowired
    private IRedisService redisService;

    @Override
    public int save(AdminAuthDomain domain) {
        int i = 0;
        if(StringUtils.isBlank(domain.getId())){
            domain.insertOperation();
            domain.setHasDeleted(Boolean.FALSE);
            domain.setId(codeGenerateService.makeRoleCode());
            i = adminAuthDomainMapper.insertSelective(domain);
        }else{
            // 如果人员不一致,把之前人员删除,redis
                AdminAuthDomain old = this.get(domain.getId());
                if(!old.getEmpId().equals(domain.getEmpId())){
                    this.delRedis(old.getEmpId());
                }

            domain.updateOperation();
            i = adminAuthDomainMapper.updateByPrimaryKeySelective(domain);
        }
        // controller 中保存 saveRedis(domain);
        return i;
    }

    @Override
    public void saveRedis(AdminAuthDomain domain){
        redisService.set(CodeRedisHelper.special_auth + domain.getEmpId(), JSONObject.toJSONString(domain));
    }

    @Override
    public void delRedis(String empId) {
        if(redisService.exists(CodeRedisHelper.special_auth+empId)){
            redisService.remove(CodeRedisHelper.special_auth + empId);
        }
    }

    /**
     * 如果redis null, from db
     * @param empId
     * @return
     */
//    public AdminAuthDomain getFromRedisById(String id) {
//        if(StringUtils.isBlank(id)){
//            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
//        }
//        Object obj = redisService.get(CodeRedisHelper.special_auth + id);
//        if(null == obj){
//            AdminAuthDomain doamin = adminAuthDomainMapper.selectByPrimaryKey(id);
//            saveRedis(doamin);
//            return doamin;
//        }
//        throw BusinessException.withErrorCode(ErrorConstant.Common.NOT_FOND);
//    }
    public AdminAuthDomain getFromRedisByEmpId(String empId) {
        if(StringUtils.isBlank(empId)){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        Object obj = redisService.get(CodeRedisHelper.special_auth + empId);
        if(null == obj){
            AdminAuthDomain domain = new AdminAuthDomain();
            domain.setEmpId(empId);
            domain.setHasDeleted(Boolean.FALSE);
            List<AdminAuthDomain> list = this.get(domain);
            if(CollectionUtils.isEmpty(list)){
                return null;
            }
                saveRedis(list.get(0));
            return list.get(0);
        }
        return JSONObject.parseObject(obj.toString(), AdminAuthDomain.class);
    }

    @Override
    public AdminAuthDomain get(String id) {
//        return this.getFromRedisById(id);
        return adminAuthDomainMapper.selectByPrimaryKey(id);
    }

    @Override
    public AdminAuthDomain getByEmpId(String empId) {
        return this.getFromRedisByEmpId(empId);
    }

    @Override
    public List<AdminAuthDomain> get(AdminAuthDomain domain) {
        if (null == domain){
            domain = new AdminAuthDomain();
        }

        AdminAuthDomainExample example = new AdminAuthDomainExample();
        AdminAuthDomainExample.Criteria criteria = example.createCriteria();

        if(StringUtils.isNotBlank(domain.getEmpId())){
            criteria.andEmpIdEqualTo(domain.getEmpId());
        }
        if(null != domain.getHasDeleted()){
            criteria.andHasDeletedEqualTo(domain.getHasDeleted());
        }

        example.setOrderByClause(" create_time desc ");

        List<AdminAuthDomain> list = adminAuthDomainMapper.selectByExample(example);
        return list;
    }

    @Override
    public PageInfo<AdminAuthDomain> page(AdminAuthDomain domain, Integer start, Integer pageSize) {
        PageHelper.startPage(start, pageSize);

        if(null == domain){
            domain = new AdminAuthDomain();
        }
        if(null == domain.getHasDeleted()){
            domain.setHasDeleted(Boolean.FALSE);
        }
        List<AdminAuthDomain> list = this.get(domain);

        return new PageInfo<>(list);
    }

    @Override
    public SpecialAuthDto getSpecialAuth(String empId) {
        AdminAuthDomain auth = this.getByEmpId(empId);
        if(null == auth){
            return new SpecialAuthDto();
        }
        return auth.getSpecialAuth();
    }
}
