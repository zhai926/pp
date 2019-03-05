package com.pp.healthy.controller.member;

import com.github.pagehelper.PageInfo;
import com.pp.base.common.RJResponse;
import com.pp.base.constant.ErrorConstant;
import com.pp.base.controller.BaseController;
import com.pp.base.enums.EnumBoolean;
import com.pp.base.exception.BusinessException;
import com.pp.healthy.annotation.UserLoginToken;
import com.pp.healthy.domain.CommonRequestBo;
import com.pp.member.domain.MemberDomain;
import com.pp.member.service.IMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@CrossOrigin
@Api("用户管理")
@Controller
@RequestMapping("/member")
@Slf4j
public class MemberController extends BaseController {

@Autowired
private IMemberService memberService;

    @UserLoginToken
    @ApiOperation("用户列表查询JSON")
    @RequestMapping("page")
    @ResponseBody
    public RJResponse page(
            @RequestBody CommonRequestBo commonRequestBo
//                           @ApiParam(name = "memberName", value = "用户名", required = false)
//                           @RequestParam(name = "memberName", required = false) String memberName,
//                           @ApiParam(name = "page", value = "页数", required = false)
//                               @RequestParam(name = "page", required = false, defaultValue = "1")
//                                       Integer page,
//                           @ApiParam(name = "limit", value = "每页数量", required = false)
//                               @RequestParam(name = "limit", required = false, defaultValue = "20")
//                                       Integer limit
    ){
       try{
           MemberDomain domain = new MemberDomain();
           if (StringUtils.isNotBlank(commonRequestBo.getMemberName())){
               domain.setMemberName(commonRequestBo.getMemberName());
           }
           domain.setHasDeleted(EnumBoolean.FALSE.getVal());

           PageInfo pageInfo = memberService.page(domain, commonRequestBo.getPageNum(), commonRequestBo.getPageSize());
           return RJResponse.page(pageInfo);
       }catch (Exception e){
           String msg = "用户列表查询JSON失败";
           log.error(e.getMessage());
           if(e instanceof BusinessException){
               msg = StringUtils.isNotBlank(e.getMessage()) ? e.getMessage() : msg;
           }else{
               log.error(msg, e);
           }
           return RJResponse.fail(msg);
       }
    }
}

