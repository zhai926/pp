package com.pp.employee.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pp.base.constant.ErrorConstant;
import com.pp.base.exception.BusinessException;
import com.pp.base.service.ICodeGenerateService;
import com.pp.base.service.IRedisService;
import com.pp.base.utils.CodeRedisHelper;
import com.pp.base.utils.DateKit;
import com.pp.base.utils.TaleUtils;
import com.pp.employee.domain.AdminUserDomain;
import com.pp.employee.mapper.AdminEmployeeDomainMapper;
import com.pp.employee.service.IAdminEmployeeService;
import com.pp.employee.service.IAdminUserService;
import com.pp.employee.domain.AdminEmployeeDomain;
import com.pp.employee.domain.AdminEmployeeDomainExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminEmployeeServiceImpl implements IAdminEmployeeService {

    @Autowired
    private AdminEmployeeDomainMapper employeeDomainMapper;
    @Autowired
    private IAdminUserService userService;
    @Autowired
    private ICodeGenerateService codeGenerateService;
    @Autowired
    private IRedisService redisService;

    @Transactional
    @Override
    public int save(AdminEmployeeDomain employeeDomain) {
        if(StringUtils.isBlank(employeeDomain.getId())){
            employeeDomain.setId(codeGenerateService.makeEmpCode());
            employeeDomain.setHasDeleted(Boolean.FALSE);
            employeeDomain.insertOperation();
            return employeeDomainMapper.insertSelective(employeeDomain);
        }else{
            employeeDomain.updateOperation();
            return employeeDomainMapper.updateByPrimaryKeySelective(employeeDomain);
        }
    }

    @Transactional
    @Override
    public int register(AdminEmployeeDomain employeeDomain) {
//        Map map = new HashMap<>();
//        map.put("mobile", employeeDomain.getMobile());
        AdminEmployeeDomain search = new AdminEmployeeDomain();
        search.setMobile(employeeDomain.getMobile());
        AdminEmployeeDomain hasExist = this.getOne(search);
        if(null != hasExist && (StringUtils.isBlank(employeeDomain.getId()) || (StringUtils.isNotBlank(employeeDomain.getId()) && !employeeDomain.getId().equals(hasExist.getId())))){
            throw BusinessException.withErrorCode(ErrorConstant.Common.MOBILE_EXIST);
        }
//        map.clear();
        //map.put("account", employeeDomain.getAccount());
        search = new AdminEmployeeDomain();
        search.setAccount(employeeDomain.getAccount());
        hasExist = this.getOne(search);
        if(null != hasExist && (StringUtils.isBlank(employeeDomain.getId()) || (StringUtils.isNotBlank(employeeDomain.getId()) && !employeeDomain.getId().equals(hasExist.getId())))){
            throw BusinessException.withErrorCode(ErrorConstant.Common.ACCOUNT_EXIST);
        }
//        map.clear();
        //map.put("cardNum", employeeDomain.getCardNum());
        search = new AdminEmployeeDomain();
        search.setCardNum(employeeDomain.getCardNum());
        hasExist = this.getOne(search);
        if(null != hasExist && (StringUtils.isBlank(employeeDomain.getId()) || (StringUtils.isNotBlank(employeeDomain.getId()) && !employeeDomain.getId().equals(hasExist.getId())))){
            throw BusinessException.withErrorCode(ErrorConstant.Common.CARD_EXIST);
        }

        save(employeeDomain);
        AdminUserDomain user = convert2User(employeeDomain);
        return userService.saveUser(user);
    }

    @Override
    public AdminEmployeeDomain get(String id) {
        return employeeDomainMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<AdminEmployeeDomain> getByRoleId(String roleId) {
        if(StringUtils.isBlank(roleId)){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        return employeeDomainMapper.selectByRoleId(roleId);
    }

    @Override
    public AdminEmployeeDomain getFromRedis(String id) {
        if(StringUtils.isBlank(id)){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }

        Object obj = redisService.get(CodeRedisHelper.emp_info+id);
        if(null == obj){
            AdminEmployeeDomain domain = employeeDomainMapper.selectByPrimaryKey(id);
            if(null != domain){
                redisService.set(CodeRedisHelper.emp_info+id, JSONObject.toJSONString(domain));
                return domain;
            }
        }
        return JSONObject.parseObject(obj.toString(), AdminEmployeeDomain.class);
    }

    @Override
    public AdminEmployeeDomain getOne(AdminEmployeeDomain emp) {
        if(null == emp){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        emp.setHasDeleted(Boolean.FALSE);
        PageInfo<AdminEmployeeDomain> page = page(emp, 1,10);
        if(null == page || CollectionUtils.isEmpty(page.getList())){
            return null;
        }
        return page.getList().get(0);
    }

    @Override
    public List<AdminEmployeeDomain> get(AdminEmployeeDomain domain) {
        if(null == domain){
            domain = new AdminEmployeeDomain();
        }
        AdminEmployeeDomainExample example = new AdminEmployeeDomainExample();
        AdminEmployeeDomainExample.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotBlank(domain.getStatus())){
            criteria.andStatusEqualTo(domain.getStatus());
        }
        if(StringUtils.isNotBlank(domain.getName())){
            criteria.andNameLike("%" + domain.getName() + "%");
        }
        if(null != domain.getSex()){
            criteria.andSexEqualTo(domain.getSex());
        }
        if(StringUtils.isNotBlank(domain.getMobile())){
            criteria.andMobileEqualTo(domain.getMobile());
        }
        if(StringUtils.isNotBlank(domain.getCardNum())){
            criteria.andCardNumEqualTo(domain.getCardNum());
        }
        if(StringUtils.isNotBlank(domain.getAccount())){
            criteria.andAccountEqualTo(domain.getAccount());
        }
        if(StringUtils.isNotBlank(domain.getProvinceCode())){
            criteria.andProvinceCodeEqualTo(domain.getProvinceCode());
        }
        if(StringUtils.isNotBlank(domain.getCityCode())){
            criteria.andCityCodeEqualTo(domain.getCityCode());
        }
        if(StringUtils.isNotBlank(domain.getAreaCode())){
            criteria.andAreaCodeEqualTo(domain.getAreaCode());
        }
        if(StringUtils.isNotBlank(domain.getStreetCode())){
            criteria.andStreetCodeEqualTo(domain.getStreetCode());
        }
        if(StringUtils.isNotBlank(domain.getPostId())){
            criteria.andPostIdEqualTo(domain.getPostId());
        }
        if(StringUtils.isNotBlank(domain.getDeptId())){
            criteria.andDeptIdEqualTo(domain.getDeptId());
        }
        if(null != domain.getHasDeleted()){
            criteria.andHasDeletedEqualTo(domain.getHasDeleted());
        }
        if(StringUtils.isNotBlank(domain.getCompanyId())){
            criteria.andCompanyIdEqualTo(domain.getCompanyId());
        }

        example.setOrderByClause(" create_time desc ");
        List<AdminEmployeeDomain> list =  employeeDomainMapper.selectByExample(example);
        return list;
    }

    @Override
    public PageInfo<AdminEmployeeDomain> page(Map map, Integer page, Integer limit) {
        if(null == map){
            map = new HashMap();
        }
        if(!map.containsKey("orderBy")){
            map.put("orderBy", "modify_time");
            map.put("order", Boolean.FALSE);
        }
        PageHelper.startPage(page, limit);
        // 使用 selectByMap 缺少sql
        List<AdminEmployeeDomain> list = employeeDomainMapper.selectByMap(map);
        PageInfo<AdminEmployeeDomain> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
    @Override
    public PageInfo<AdminEmployeeDomain> page(AdminEmployeeDomain domain, Integer page, Integer limit) {
        PageHelper.startPage(page, limit);

        List<AdminEmployeeDomain> list = this.get(domain);
        PageInfo<AdminEmployeeDomain> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    private AdminUserDomain convert2User(AdminEmployeeDomain emp){
        AdminUserDomain user = new AdminUserDomain();
        user.setEmpId(emp.getId());
        user.setName(emp.getName());
        user.setMobile(emp.getMobile());
        user.setCardType(emp.getCardType());
        user.setCardNum(emp.getCardNum());
        user.setAccount(emp.getAccount());
        user.setPasswork(emp.getPassword());
        user.setSecure(emp.getSecure());
        user.setIcon(emp.getIcon());
        user.setCreateTime(DateKit.getNow());
        user.setModifyTime(DateKit.getNow());
        user.setHasDeleted(Boolean.FALSE);
        user.setHasPassword(emp.getHasPassword());
        user.setId(emp.getUserId());
        user.setCompanyId(emp.getCompanyId());
        if(StringUtils.isNotBlank(emp.getPassword())){
            user.setSecure(emp.getPassword());
            user.setPasswork(TaleUtils.MD5encode(emp.getPassword()));
        }
        return user;
    }
}
