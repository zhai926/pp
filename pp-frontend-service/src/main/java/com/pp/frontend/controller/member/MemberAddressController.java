package com.pp.frontend.controller.member;

import com.pp.base.common.RJResponse;
import com.pp.base.constant.ErrorConstant;
import com.pp.base.enums.EnumBoolean;
import com.pp.base.exception.BusinessException;
import com.pp.frontend.controller.FSBaseController;
import com.pp.member.domain.MemberAddressDomain;
import com.pp.member.domain.MemberDomain;
import com.pp.member.service.IMemberAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@Api("用户地址")
@Controller
@RequestMapping("member/address")
public class MemberAddressController extends FSBaseController {

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

    @ApiOperation("地址保存")
    @PostMapping("/save")
    @ResponseBody
    public RJResponse save(
            @ApiParam(name = "id", value = "id", required = false) @RequestParam(name = "id", required = false) Integer id,
            @ApiParam(name = "contactName", value = "姓名", required = false) @RequestParam(name = "contactName", required = false) String contactName,
            @ApiParam(name = "contactPhone", value = "联系手机", required = false) @RequestParam(name = "contactPhone", required = false) String contactPhone,
            @ApiParam(name = "provinceName", value = "内容", required = false) @RequestParam(name = "provinceName", required = false) String provinceName,
            @ApiParam(name = "cityName", value = "内容", required = false) @RequestParam(name = "cityName", required = false) String cityName,
            @ApiParam(name = "areaName", value = "内容", required = false) @RequestParam(name = "areaName", required = false) String areaName,
            @ApiParam(name = "addressDetail", value = "详细地址", required = false) @RequestParam(name = "addressDetail", required = false) String addressDetail
    )  {

        try {
            if(StringUtils.isBlank(contactName) || StringUtils.isBlank(contactPhone) || StringUtils.isBlank(addressDetail)){
                throw new BusinessException(ErrorConstant.Common.PARAM_IS_EMPTY);
            }
            MemberAddressDomain domain = null;
            if(null != id){
                domain = memberAddressService.get(id);
            }else{
                domain = new MemberAddressDomain();
            }
            domain.setStatus(EnumBoolean.FALSE.getStrCode());
            domain.setMemberId(getMemberId());
            domain.setContactName(contactName);
            domain.setContactPhone(contactPhone);
            domain.setProvinceName(provinceName);
            domain.setCityName(cityName);
            domain.setAreaName(areaName);
            domain.setAddressDetail(addressDetail);
            memberAddressService.save(domain);
            return RJResponse.success(domain.getId() + "_" + domain.getAddressId());
        } catch (Exception e) {
            return RJResponse.fail(exceptionMsg(e, "保存失败"));
        }
    }

    @ApiOperation("地址删除")
    @PostMapping("/delete/{id}")
    @ResponseBody
    public RJResponse delete(@ApiParam(name = "id", value = "轮播编码", required = true) @PathVariable Integer id)  {

        if (null == id) {
            throw BusinessException.withErrorCode("请重新选择");
        }
        try {
            memberAddressService.delete(id);
            return RJResponse.success("删除成功");
        } catch (Exception e) {
            return RJResponse.fail(exceptionMsg(e, "删除失败"));
        }
    }

    /**
     * 详细
     * @return
     */
    @ApiOperation("详细")
    @GetMapping(value = "/detail/{id}")
    public RJResponse detail(@ApiParam(name = "id", value = "轮播编码", required = true) @PathVariable Integer id) {
        try {
            MemberAddressDomain domain = memberAddressService.get(id);
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
    @GetMapping(value = "/page")
    @ResponseBody
    public RJResponse page(@ApiParam(name = "page", value = "页数", required = false) @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
                           @ApiParam(name = "pageSize", value = "每页数量", required = false) @RequestParam(name = "pageSize", required = false, defaultValue = "20") Integer pageSize
    ) {
        try {
            MemberAddressDomain domain = new MemberAddressDomain();
            domain.setMemberId(getMemberId());

            List<MemberAddressDomain> list = memberAddressService.get(domain);
            // 重新封装pageInfo
            return RJResponse.success(list);
        } catch (Exception e) {
            return RJResponse.fail(exceptionMsg(e, "查询失败"));
        }
    }

}
