package com.pp.frontend.controller.member;

import com.github.pagehelper.PageInfo;
import com.pp.base.common.RJResponse;
import com.pp.base.constant.ErrorConstant;
import com.pp.base.enums.EnumBoolean;
import com.pp.base.exception.BusinessException;
import com.pp.frontend.controller.FSBaseController;
import com.pp.goods.domain.MemberGiftDomain;
import com.pp.goods.enums.EnumMemberGiftStatus;
import com.pp.goods.service.IMemberGiftService;
import com.pp.member.domain.MemberAddressDomain;
import com.pp.member.domain.MemberDomain;
import com.pp.member.service.IMemberAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Api("礼品发货管理")
@Controller
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
    @ApiOperation("兑换")
    @GetMapping(value = "/exchange/{id}/{addressId}")
    @ResponseBody
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
    }

    /**
     * 详细
     * @return
     */
    @ApiOperation("详细")
    @GetMapping(value = "/detail/{id}")
    @ResponseBody
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
    @ApiOperation("分页查询")
    @GetMapping(value = "/list")
    @ResponseBody
    public RJResponse list(@ApiParam(name = "page", value = "页数", required = false)
                           @RequestParam(name = "page", required = false, defaultValue = "1")
                                   Integer page,
                           @ApiParam(name = "pageSize", value = "每页数量", required = false)
                           @RequestParam(name = "pageSize", required = false, defaultValue = "20")
                                   Integer pageSize) {
        try {
            MemberGiftDomain domain = new MemberGiftDomain();
            domain.setMemberId(getMemberId());
            domain.setHasDeleted(EnumBoolean.FALSE.getVal());

            PageInfo<MemberGiftDomain> pageInfo = memberGiftService.page(domain, page, pageSize);

            // 重新封装pageInfo
            return RJResponse.page(pageInfo);
        } catch (Exception e) {
            return RJResponse.fail(exceptionMsg(e, "查询失败"));
        }
    }

    /**
     * 分页查询
     * @return
     */
    @ApiOperation("分页查询")
    @GetMapping(value = "/page")
    public String page(Model model, @ApiParam(name = "page", value = "页数", required = false)
                           @RequestParam(name = "page", required = false, defaultValue = "1")
                                   Integer page,
                       @ApiParam(name = "pageSize", value = "每页数量", required = false)
                           @RequestParam(name = "pageSize", required = false, defaultValue = "20")
                                   Integer pageSize) {
        try {
            MemberGiftDomain domain = new MemberGiftDomain();
            domain.setMemberId(getMemberId());
            domain.setHasDeleted(EnumBoolean.FALSE.getVal());

            PageInfo<MemberGiftDomain> pageInfo = memberGiftService.page(domain, page, pageSize);

            model.addAttribute("page", pageInfo);
            // TODO 页面
            return "";
        } catch (Exception e) {
            throw new BusinessException("系统异常");
        }
    }

}
