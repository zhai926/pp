package com.pp.healthy.controller.goods;

import com.github.pagehelper.PageInfo;
import com.pp.base.common.RJResponse;
import com.pp.base.controller.BaseController;
import com.pp.base.enums.EnumBoolean;
import com.pp.goods.domain.MemberGiftDomain;
import com.pp.goods.enums.EnumMemberGiftStatus;
import com.pp.goods.service.IMemberGiftService;
import com.pp.healthy.annotation.UserLoginToken;
import com.pp.healthy.domain.CommonRequestBo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin
@Slf4j
@Api("礼品发货管理")
@RestController
@RequestMapping("/member_gift")
public class MemberGiftController extends BaseController {

    @Autowired
    private IMemberGiftService memberGiftService;

    /**
     * 发货
     * @return
     */
    @UserLoginToken
    @ApiOperation("发货")
    @PostMapping(value = "/send/{id}")
    public RJResponse send(@ApiParam(name = "id", value = "编码", required = true) @PathVariable String id) {
        try {
            MemberGiftDomain domain = memberGiftService.get(id);
            if(EnumMemberGiftStatus.SEND.getCode().equals(domain.getStatus())){
                return RJResponse.fail("该订单已发货");
            }
            domain.setStatus(EnumMemberGiftStatus.SEND.getCode());
            memberGiftService.save(domain);
            return RJResponse.success("发货成功");
        } catch (Exception e) {
            return RJResponse.fail(exceptionMsg(e, "发货失败"));
        }
    }

    /**
     * 详细
     * @return
     */
    @UserLoginToken
    @ApiOperation("详细")
    @PostMapping(value = "/detail/{id}")
    public RJResponse detail(@ApiParam(name = "id", value = "编码", required = true) @PathVariable String id) {
        try {
            MemberGiftDomain domain = memberGiftService.get(id);
            return RJResponse.success(domain);
        } catch (Exception e) {
            return RJResponse.fail(exceptionMsg(e, "查询失败"));
        }
    }

    /**
     * 分页查询
     * @return
     */
    @UserLoginToken
    @ApiOperation("分页查询")
    @PostMapping(value = "/page")
    public RJResponse page(
            @RequestBody CommonRequestBo commonRequestBo
//            @ApiParam(name = "memberName", value = "用户名") @RequestParam(name = "memberName", required = false) String memberName,
//                           @ApiParam(name = "page", value = "页数", required = false)
//                           @RequestParam(name = "page", required = false, defaultValue = "1")
//                                   Integer page,
//                           @ApiParam(name = "pageSize", value = "每页数量", required = false)
//                           @RequestParam(name = "pageSize", required = false, defaultValue = "20")
//                                   Integer pageSize
    ) {
        try {
            MemberGiftDomain domain = new MemberGiftDomain();
            if(StringUtils.isNotBlank(commonRequestBo.getMemberName())){
                domain.setMemberName(commonRequestBo.getMemberName());
            }
            domain.setHasDeleted(EnumBoolean.FALSE.getVal());

            PageInfo<MemberGiftDomain> pageInfo = memberGiftService.page(domain, commonRequestBo.getPageNum(), commonRequestBo.getPageSize());

            // 重新封装pageInfo
            return RJResponse.page(pageInfo);
        } catch (Exception e) {
            return RJResponse.fail(exceptionMsg(e, "查询失败"));
        }
    }

}
