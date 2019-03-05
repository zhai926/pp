package com.pp.frontend.controller.member;

import com.pp.base.common.RJResponse;
import com.pp.base.constant.ErrorConstant;
import com.pp.base.enums.EnumBoolean;
import com.pp.base.exception.BusinessException;
import com.pp.frontend.controller.FSBaseController;
import com.pp.member.domain.MemberDomain;
import com.pp.member.domain.MemberMessageDomain;
import com.pp.member.service.IMemberMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @ApiOperation("留言保存")
    @PostMapping("/save")
    public RJResponse save(
            @ApiParam(name = "title", value = "标题", required = false) @RequestParam(name = "title", required = false, defaultValue = "") String title,
            @ApiParam(name = "content", value = "内容", required = false) @RequestParam(name = "content", required = false, defaultValue = "") String content
    )  {

        try {
            if(StringUtils.isBlank(title) || StringUtils.isBlank(content)){
                throw new BusinessException(ErrorConstant.Common.PARAM_IS_EMPTY);
            }
            MemberMessageDomain domain = new MemberMessageDomain();
            domain.setStatus(EnumBoolean.FALSE.getStrCode());
            domain.setTitle(title);
            domain.setContent(content);
            domain.setMemberId(getMemberId());
            memberMessageService.save(domain);

            return RJResponse.success("保存成功");
        } catch (Exception e) {
            return RJResponse.fail(exceptionMsg(e, "保存失败"));
        }
    }

}
