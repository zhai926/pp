package com.pp.mobile.controller.member;

import com.pp.base.common.RJResponse;
import com.pp.base.constant.ErrorConstant;
import com.pp.base.enums.EnumBoolean;
import com.pp.base.exception.BusinessException;
import com.pp.mobile.annotation.UserLoginToken;
import com.pp.mobile.controller.FSBaseController;
import com.pp.member.domain.MemberDomain;
import com.pp.member.domain.MemberMessageDomain;
import com.pp.member.service.IMemberMessageService;
import com.pp.mobile.domain.CommonRequestBo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@Api("用户留言管理")
@RestController
@RequestMapping("/message")// /member
@Slf4j
public class MemberMessageController extends FSBaseController {

    @Autowired
    private IMemberMessageService memberMessageService;

    public String getMemberId(){
        MemberDomain member = getMember();
        if(null ==member){
            return null;
        }
        return member.getMemberId();
    }

    @UserLoginToken
    @ApiOperation("留言保存")
    @PostMapping("/save")
    public RJResponse save(
            @RequestBody CommonRequestBo commonRequestBo
            /*@ApiParam(name = "title", value = "标题", required = false) @RequestParam(name = "title", required = false, defaultValue = "") String title,
            @ApiParam(name = "content", value = "内容", required = false) @RequestParam(name = "content", required = false, defaultValue = "") String content*/
    )  {

        try {
            if(StringUtils.isBlank(commonRequestBo.getTitle()) || StringUtils.isBlank(commonRequestBo.getContent())){
                throw new BusinessException(ErrorConstant.Common.PARAM_IS_EMPTY);
            }
            MemberMessageDomain domain = new MemberMessageDomain();
            domain.setStatus(EnumBoolean.FALSE.getStrCode());
            domain.setTitle(commonRequestBo.getTitle());
            domain.setContent(commonRequestBo.getContent());
            domain.setMemberId(getMemberId());
            memberMessageService.save(domain);

            return RJResponse.success("保存成功");
        } catch (Exception e) {
            return RJResponse.fail(exceptionMsg(e, "保存失败"));
        }
    }

}
