package com.pp.mobile.controller.member;

import com.alibaba.fastjson.JSONObject;
import com.pp.base.common.RJResponse;
import com.pp.base.constant.ErrorConstant;
import com.pp.base.exception.BusinessException;
import com.pp.base.utils.DateFormatUtil;
import com.pp.member.domain.MemberSignDomain;
import com.pp.member.service.IMemberSignService;
import com.pp.mobile.annotation.UserLoginToken;
import com.pp.mobile.controller.FSBaseController;
import com.pp.member.domain.MemberDomain;
import com.pp.member.service.IMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@Slf4j
@Api("用户")
@RestController
@RequestMapping("member")
public class MemberController extends FSBaseController {

    @Autowired
    private IMemberService memberService;
    @Autowired
    private IMemberSignService memberSignService;

    public String getMemberId(){
        Object obj = getMemberObj();
        if(null == obj){
            throw BusinessException.withErrorCode(ErrorConstant.Auth.NOT_LOGIN);
        }
        MemberDomain member = (MemberDomain) obj;
        return member.getMemberId();
    }

    @UserLoginToken
    @ApiOperation("会员中心")
    @PostMapping(value="center")
    public RJResponse center(){
        try {
            String memberId = getMemberId();
            MemberDomain memberDomain = memberService.get(memberId);
            List<MemberSignDomain> memberSignDomains = memberSignService.get(memberId);
            MemberSignDomain sign = memberSignService.get(memberId, DateFormatUtil.getCurrentDate(DateFormatUtil.DateTimeFormatDay));

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("phone", memberDomain.getPhoneNumber());
            jsonObject.put("sign", CollectionUtils.isEmpty(memberSignDomains) ? 0 : memberSignDomains.size());
            jsonObject.put("isSign", null ==sign ? false : true);


            return RJResponse.success(jsonObject);
        } catch (Exception e) {
            return RJResponse.fail(exceptionMsg(e, "查询失败"));
        }
    }


}
