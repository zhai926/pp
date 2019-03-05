package com.pp.healthy.controller.goods;

import com.github.pagehelper.PageInfo;
import com.pp.base.common.RJResponse;
import com.pp.base.controller.BaseController;
import com.pp.base.enums.EnumBoolean;
import com.pp.base.exception.BusinessException;
import com.pp.goods.domain.OnlineStoreDomain;
import com.pp.goods.service.IOnlineStoreService;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin
@Slf4j
@Api("商城管理")
@RestController
@RequestMapping("/store")
public class OnlineStoreController extends BaseController {

    @Autowired
    private IOnlineStoreService storeService;

    @UserLoginToken
    @ApiOperation("商城保存")
    @PostMapping("/save")
    public RJResponse save(
            @RequestBody CommonRequestBo commonRequestBo
//                           @ApiParam(name = "id", value = "商城对象", required = false) @RequestParam(name = "id", required = false, defaultValue = "") String id,
//                           @ApiParam(name = "url", value = "商城对象", required = false) @RequestParam(name = "url", required = true, defaultValue = "") String url
    )  {

        try {

            OnlineStoreDomain domain = new OnlineStoreDomain();
            domain.setId(commonRequestBo.getId());
            domain.setName(commonRequestBo.getName());
            domain.setUrl(commonRequestBo.getUrl());
            storeService.save(domain);

            return RJResponse.success("保存成功");
        } catch (Exception e) {
            return RJResponse.fail(exceptionMsg(e, "保存失败"));
        }
    }

    @UserLoginToken
    @ApiOperation("商城删除")
    @PostMapping("/delete/{id}")
    public RJResponse delete(@ApiParam(name = "id", value = "编码", required = true) @PathVariable String id)  {

        if (StringUtils.isBlank(id)) {
            throw BusinessException.withErrorCode("请重新选择");
        }
        try {
            storeService.delete(id);
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
    public RJResponse detail(@ApiParam(name = "id", value = "轮播编码", required = true) @PathVariable String id) {
        try {
            OnlineStoreDomain domain = storeService.get(id);
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
//            @ApiParam(name = "url", value = "内容查询") @RequestParam(name = "url", required = false) String url,
//                           @ApiParam(name = "page", value = "页数", required = false)
//                           @RequestParam(name = "page", required = false, defaultValue = "1")
//                                   Integer page,
//                           @ApiParam(name = "pageSize", value = "每页数量", required = false)
//                           @RequestParam(name = "pageSize", required = false, defaultValue = "20")
//                                   Integer pageSize
    ) {
        try {
            OnlineStoreDomain domain = new OnlineStoreDomain();
            if(StringUtils.isNotBlank(commonRequestBo.getUrl())){
                domain.setUrl(commonRequestBo.getUrl());
            }
            domain.setHasDeleted(EnumBoolean.FALSE.getVal());

            PageInfo<OnlineStoreDomain> pageInfo = storeService.page(domain, commonRequestBo.getPageNum(), commonRequestBo.getPageSize());

            // 重新封装pageInfo
            return RJResponse.page(pageInfo);
        } catch (Exception e) {
            return RJResponse.fail(exceptionMsg(e, "查询失败"));
        }
    }
}
