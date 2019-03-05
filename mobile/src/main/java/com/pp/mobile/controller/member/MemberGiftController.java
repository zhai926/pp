package com.pp.mobile.controller.member;

import com.github.pagehelper.PageInfo;
import com.pp.base.common.RJResponse;
import com.pp.base.constant.ErrorConstant;
import com.pp.base.enums.EnumBoolean;
import com.pp.base.exception.BusinessException;
import com.pp.mobile.annotation.UserLoginToken;
import com.pp.mobile.controller.FSBaseController;
import com.pp.goods.domain.MemberGiftDomain;
import com.pp.goods.enums.EnumMemberGiftStatus;
import com.pp.goods.service.IMemberGiftService;
import com.pp.member.domain.MemberAddressDomain;
import com.pp.member.domain.MemberDomain;
import com.pp.member.service.IMemberAddressService;
import com.pp.mobile.domain.CommonRequestBo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@Slf4j
@Api("礼品发货管理")
@RestController
@RequestMapping("/member/gift")
public class MemberGiftController extends FSBaseController {

    @Autowired
    private IMemberGiftService memberGiftService;
    @Autowired
    private IMemberAddressService memberAddressService;

    public String getMemberId(){
        Object obj = getMemberObj();
        if(null == obj){
            throw BusinessException.withErrorCode(ErrorConstant.Auth.NOT_LOGIN);
        }
        MemberDomain member = (MemberDomain) obj;
        return member.getMemberId();
    }

    /**
     * 兑换
     * @return
     */
/*    @UserLoginToken
    @ApiOperation("兑换")
    @PostMapping(value = "/exchange/{id}/{addressId}")
    public RJResponse exchange(@ApiParam(name = "id", value = "编码", required = true) @PathVariable String id,
                               @ApiParam(name = "addressId", value = "地址编码", required = true) @PathVariable String addressId) {
        try {
            MemberGiftDomain domain = memberGiftService.get(id);
            if(EnumMemberGiftStatus.INITIAL.getCode().equals(domain.getStatus())){
                domain.setStatus(EnumMemberGiftStatus.DUIHUAN.getCode());
                MemberAddressDomain memberAddressDomain = memberAddressService.get(addressId);
                domain.setAddressId(addressId);
                domain.setContactName(memberAddressDomain.getContactName());
                domain.setContactPhone(memberAddressDomain.getContactPhone());
                domain.setAddressDetail(memberAddressDomain.getAddressDetail());
            }else{
                return RJResponse.fail("您已兑换改奖品");
            }

            memberGiftService.save(domain);
            return RJResponse.success("兑换成功");
        } catch (Exception e) {
            return RJResponse.fail(exceptionMsg(e, "兑换失败"));
        }
    }*/

    /**
     * 兑换
     * @return
     */
    @UserLoginToken
    @ApiOperation("兑换")
    @PostMapping(value = "/exchange")
    public RJResponse exc(@RequestBody CommonRequestBo commonRequestBo) {
        try {
            if(StringUtils.isBlank(commonRequestBo.getId())){
                throw new BusinessException("请选择礼品");
            }
            if(StringUtils.isBlank(commonRequestBo.getAddressId())){
                throw new BusinessException("请选择收货地址");
            }

            MemberGiftDomain domain = memberGiftService.get(commonRequestBo.getId());
            if(EnumMemberGiftStatus.INITIAL.getCode().equals(domain.getStatus())){
                domain.setStatus(EnumMemberGiftStatus.DUIHUAN.getCode());
                MemberAddressDomain memberAddressDomain = memberAddressService.get(commonRequestBo.getAddressId());
                domain.setAddressId(commonRequestBo.getAddressId());
                domain.setContactName(memberAddressDomain.getContactName());
                domain.setContactPhone(memberAddressDomain.getContactPhone());
                domain.setAddressDetail(memberAddressDomain.getAddressDetail());
            }else{
                return RJResponse.fail("您已兑换改奖品");
            }

            memberGiftService.save(domain);
            return RJResponse.success("兑换成功");
        } catch (Exception e) {
            return RJResponse.fail(exceptionMsg(e, "兑换失败"));
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
            /*@ApiParam(name = "page", value = "页数", required = false)
                           @RequestParam(name = "page", required = false, defaultValue = "1")
                                   Integer page,
                           @ApiParam(name = "pageSize", value = "每页数量", required = false)
                           @RequestParam(name = "pageSize", required = false, defaultValue = "20")
                                   Integer pageSize*/
            ) {
        try {
            MemberGiftDomain domain = new MemberGiftDomain();
            domain.setMemberId(getMemberId());
            domain.setHasDeleted(EnumBoolean.FALSE.getVal());

            PageInfo<MemberGiftDomain> pageInfo = memberGiftService.page(domain, commonRequestBo.getPageNum(), commonRequestBo.getPageSize());

            // 重新封装pageInfo
            return RJResponse.page(pageInfo);
        } catch (Exception e) {
            return RJResponse.fail(exceptionMsg(e, "查询失败"));
        }
    }

}
