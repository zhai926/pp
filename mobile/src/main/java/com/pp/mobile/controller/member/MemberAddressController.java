package com.pp.mobile.controller.member;

import com.pp.base.common.RJResponse;
import com.pp.base.constant.ErrorConstant;
import com.pp.base.enums.EnumBoolean;
import com.pp.base.exception.BusinessException;
import com.pp.mobile.annotation.UserLoginToken;
import com.pp.mobile.controller.FSBaseController;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@Slf4j
@Api("用户地址")
@RestController
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

    @UserLoginToken
    @ApiOperation("地址保存")
    @PostMapping("/save")
    public RJResponse save(
            @RequestBody CommonRequestBo commonRequestBo
           /* @ApiParam(name = "id", value = "id", required = false) @RequestParam(name = "id", required = false) Integer id,
            @ApiParam(name = "contactName", value = "姓名", required = false) @RequestParam(name = "contactName", required = false) String contactName,
            @ApiParam(name = "contactPhone", value = "联系手机", required = false) @RequestParam(name = "contactPhone", required = false) String contactPhone,
            @ApiParam(name = "provinceName", value = "内容", required = false) @RequestParam(name = "provinceName", required = false) String provinceName,
            @ApiParam(name = "cityName", value = "内容", required = false) @RequestParam(name = "cityName", required = false) String cityName,
            @ApiParam(name = "areaName", value = "内容", required = false) @RequestParam(name = "areaName", required = false) String areaName,
            @ApiParam(name = "addressDetail", value = "详细地址", required = false) @RequestParam(name = "addressDetail", required = false) String addressDetail*/
    )  {

        try {
            if(StringUtils.isBlank(commonRequestBo.getContactName()) || StringUtils.isBlank(commonRequestBo.getContactPhone())
                    || StringUtils.isBlank(commonRequestBo.getAddressDetail())){
                throw new BusinessException(ErrorConstant.Common.PARAM_IS_EMPTY);
            }
            MemberAddressDomain domain = null;
            if(StringUtils.isNotBlank(commonRequestBo.getId())){
                domain = memberAddressService.get(Integer.valueOf(commonRequestBo.getId()));
            }else{
                domain = new MemberAddressDomain();
            }
            domain.setStatus(EnumBoolean.FALSE.getStrCode());
            domain.setMemberId(getMemberId());
            domain.setContactName(commonRequestBo.getContactName());
            domain.setContactPhone(commonRequestBo.getContactPhone());
            domain.setProvinceName(commonRequestBo.getProvinceName());
            domain.setCityName(commonRequestBo.getCityName());
            domain.setAreaName(commonRequestBo.getAreaName());
            domain.setAddressDetail(commonRequestBo.getAddressDetail());
            memberAddressService.save(domain);
            return RJResponse.success(domain.getId() + "_" + domain.getAddressId());
        } catch (Exception e) {
            return RJResponse.fail(exceptionMsg(e, "保存失败"));
        }
    }

    @UserLoginToken
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
    @UserLoginToken
    @ApiOperation("详细")
    @PostMapping(value = "/detail/{id}")
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
    @UserLoginToken
    @ApiOperation("分页查询")
    @PostMapping(value = "/list")
    public RJResponse list(
            @RequestBody CommonRequestBo commonRequestBo
            /*@ApiParam(name = "page", value = "页数", required = false) @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
                           @ApiParam(name = "pageSize", value = "每页数量", required = false) @RequestParam(name = "pageSize", required = false, defaultValue = "20") Integer pageSize*/
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
