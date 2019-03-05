package com.pp.mobile.controller.member;

import com.github.pagehelper.PageInfo;
import com.pp.base.common.RJResponse;
import com.pp.base.constant.ErrorConstant;
import com.pp.base.exception.BusinessException;
import com.pp.mobile.annotation.UserLoginToken;
import com.pp.mobile.controller.FSBaseController;
import com.pp.member.domain.MemberDomain;
import com.pp.member.domain.MemberSignDomain;
import com.pp.member.service.IMemberSignService;
import com.pp.mobile.domain.CommonRequestBo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@Api("用户留言管理")
@RestController
@RequestMapping("/member/sign")
@Slf4j
public class MemberSignController extends FSBaseController {

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
    @ApiOperation("签到保存")
    @PostMapping("/save")
    public RJResponse save()  {

        try {
            memberSignService.save(getMemberId());

            return RJResponse.success("保存成功");
        } catch (Exception e) {
            return RJResponse.fail(exceptionMsg(e, "保存失败"));
        }
    }


    /**
     * 分页查询
     * @return
     */
    @UserLoginToken
    @ApiOperation("分页查询")
    @PostMapping(value = "/page")
    @ResponseBody
    public RJResponse page(
            @RequestBody CommonRequestBo commonRequestBo
            /*@ApiParam(name = "page", value = "页数", required = false) @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            @ApiParam(name = "pageSize", value = "每页数量", required = false) @RequestParam(name = "pageSize", required = false, defaultValue = "20") Integer pageSize*/
    ) {
        try {
            MemberSignDomain domain = new MemberSignDomain();
            domain.setMemberId(getMemberId());

            PageInfo<MemberSignDomain> pageInfo = memberSignService.page(domain, commonRequestBo.getPageNum(), commonRequestBo.getPageSize());

            // 重新封装pageInfo
            return RJResponse.page(pageInfo);
        } catch (Exception e) {
            return RJResponse.fail(exceptionMsg(e, "查询失败"));
        }
    }
}
