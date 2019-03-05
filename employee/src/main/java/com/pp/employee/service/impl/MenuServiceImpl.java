package com.pp.employee.service.impl;

import com.pp.base.constant.ErrorConstant;
import com.pp.base.enums.EnumBoolean;
import com.pp.base.enums.EnumONOFF;
import com.pp.base.exception.BusinessException;
import com.pp.base.service.ICodeGenerateService;
import com.pp.base.utils.Node;
import com.pp.employee.domain.AdminMenuDomain;
import com.pp.employee.domain.AdminMenuDomainExample;
import com.pp.employee.mapper.AdminMenuDomainMapper;
import com.pp.employee.service.IMenuService;
import com.pp.employee.service.IRoleMenuSerivce;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class MenuServiceImpl implements IMenuService {

    @Autowired
    private AdminMenuDomainMapper adminMenuDomainMapper;
    @Autowired
    private ICodeGenerateService codeGenerateService;
    @Autowired
    private IRoleMenuSerivce roleMenuSerivce;

    @Override
    @Transactional
    public int save(AdminMenuDomain domain) {
        if(StringUtils.isBlank(domain.getId())){
            domain.insertOperation();
            domain.setId(codeGenerateService.makeMenuCode());
            return adminMenuDomainMapper.insertSelective(domain);
        }else{
            domain.updateOperation();
            return adminMenuDomainMapper.updateByPrimaryKeySelective(domain);
        }
    }

    @Override
    @Transactional
    public int delete(List<String> ids) {
        if(CollectionUtils.isEmpty(ids)){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        int i = 0;

        for(String id: ids){
            AdminMenuDomain domain = new AdminMenuDomain();
            domain.setId(id);
            domain.setHasDeleted(EnumBoolean.TRUE.getVal());
            i += adminMenuDomainMapper.deleteByPrimaryKey(id);

            // 删除role menu
            roleMenuSerivce.deleteByMenuId(id);
        }
        return i;
    }

    @Override
    public AdminMenuDomain get(String id) {
        if(StringUtils.isBlank(id)){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        return adminMenuDomainMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<AdminMenuDomain> get(AdminMenuDomain domain) {
        if(null == domain){
            domain = new AdminMenuDomain();
        }
        if(null == domain.getHasDeleted()){
            domain.setHasDeleted(EnumBoolean.FALSE.getVal());
        }

        AdminMenuDomainExample example = new AdminMenuDomainExample();
        AdminMenuDomainExample.Criteria criteria = example.createCriteria();

        if(StringUtils.isNotBlank(domain.getName())){
            criteria.andNameLike("%" + domain.getName() + "%");
        }
        if(StringUtils.isNotBlank(domain.getCode())){
            criteria.andCodeEqualTo(domain.getCode());
        }
        if(StringUtils.isNotBlank(domain.getParentId())){
            criteria.andParentIdEqualTo(domain.getParentId());
        }
        if(null != domain.getHasMenu()){
            criteria.andHasMenuEqualTo(domain.getHasMenu());
        }
        if(StringUtils.isNotBlank(domain.getUrl())){
            criteria.andUrlEqualTo(domain.getUrl());
        }
        if(StringUtils.isNotBlank(domain.getStatus())){
            criteria.andStatusEqualTo(domain.getStatus());
        }
        if(null != domain.getHasDeleted()){
            criteria.andHasDeletedEqualTo(domain.getHasDeleted());
        }

        example.setOrderByClause(" level asc ");

        return adminMenuDomainMapper.selectByExample(example);
    }

    @Override
    public List<AdminMenuDomain> getByRoleId(String roleId){
        if(StringUtils.isBlank(roleId)){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        return adminMenuDomainMapper.selectByRoleId(roleId);
    }

    @Override
    public List<AdminMenuDomain> getByEmpId(String empId) {
        if(StringUtils.isBlank(empId)){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        return getByEmpId(empId, "'1','2'");
    }

    public List<AdminMenuDomain> getByEmpId(String empId, String types) {
        if(StringUtils.isBlank(empId)){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        return adminMenuDomainMapper.selectByEmpId(empId, types);
    }

    @Override
    public AdminMenuDomain getByUrl(String url) {
        if(StringUtils.isBlank(url)){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        AdminMenuDomain menu = new AdminMenuDomain();
        menu.setUrl(url);
        List<AdminMenuDomain> list = get(menu);

        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<AdminMenuDomain> getButtons(String url, String empId) {
        if(StringUtils.isBlank(url) || StringUtils.isBlank(empId)){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_IS_EMPTY);
        }
        return adminMenuDomainMapper.selectButtons(url, empId);
    }

    @Override
    public List<AdminMenuDomain> getDown(String upId) {
        AdminMenuDomain domain = new AdminMenuDomain();
        domain.setParentId(upId);
        domain.setHasDeleted(EnumBoolean.FALSE.getVal());
        domain.setStatus(EnumONOFF.ON.getCode());
        return this.get(domain);
    }

    @Override
    public List<Node> getNodeTree(String pid, String empId) {
        AdminMenuDomain domain = new AdminMenuDomain();
        domain.setHasDeleted(EnumBoolean.FALSE.getVal());
//        if(StringUtils.isNotBlank(pid)){
//            domain.setParentId(pid);
//        }
        List<AdminMenuDomain> list = null;
        if(StringUtils.isBlank(empId)){
            list = this.get(domain);
        }else{
            list = this.getByEmpId(empId, "'1','2','3'");
        }

        if(!CollectionUtils.isEmpty(list)){
            List<Node> nodes = new ArrayList<>(list.size());
            for(AdminMenuDomain item: list){
                Node node = new Node(item.getId(), item.getParentId(), "", item.getName(), "", item.getUrl(), item.getDescription(), item.getType(), "");
                nodes.add(node);
            }
            return Node.buildTree(nodes, pid);
        }
        return null;
    }


}
