package com.pp.healthy.controller.goods;

import com.github.pagehelper.PageInfo;
import com.pp.base.common.RJResponse;
import com.pp.base.constant.ErrorConstant;
import com.pp.base.controller.BaseController;
import com.pp.base.enums.EnumBoolean;
import com.pp.base.exception.BusinessException;
import com.pp.base.utils.DateFormatUtil;
import com.pp.goods.domain.GoodsGiftDomain;
import com.pp.goods.enums.EnumGoodsGiftStatus;
import com.pp.goods.enums.EnumGoodsGiftType;
import com.pp.goods.service.IGoodsGiftService;
import com.pp.healthy.annotation.UserLoginToken;
import com.pp.healthy.domain.CommonRequestBo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@CrossOrigin
@Slf4j
@Api("商品管理")
@RestController
@RequestMapping("/goods")
public class GoodsGiftController extends BaseController {

    @Autowired
    private IGoodsGiftService goodsGiftService;

    /**
     * 在配置文件中配置的文件保存路径
     */
    @Value("${web.upload-path}")
    private String location;

    @UserLoginToken
    @ApiOperation("商品保存")
    @PostMapping("/save")
    public RJResponse save(
            @RequestBody CommonRequestBo commonRequestBo
//            , @ApiParam(name = "file", value = "图片", required = true) @RequestParam(name = "file", required = false ) MultipartFile multipartFile
//                           @ApiParam(name = "id", value = "id", required = false) @RequestParam(name = "id", required = false, defaultValue = "") String id,
//                           @ApiParam(name = "url", value = "url", required = false) @RequestParam(name = "url", required = false, defaultValue = "") String url,
//                           @ApiParam(name = "type", value = "类别1商品,2礼品", required = false) @RequestParam(name = "type", required = false, defaultValue = "") String type,
//                           @ApiParam(name = "name", value = "名称", required = false) @RequestParam(name = "name", required = false, defaultValue = "") String name,
//                           @ApiParam(name = "content", value = "文章内容", required = false) @RequestParam(name = "content", required = false,  defaultValue = "") String content,
//                           @ApiParam(name = "orderBy", value = "排序", required = false) @RequestParam(name = "orderBy", required = false) Integer orderBy
    )  {

//        if (null == commonRequestBo.getFile() || commonRequestBo.getFile().isEmpty() || StringUtils.isBlank(commonRequestBo.getFile().getOriginalFilename())) {
//            throw BusinessException.withErrorCode("请上传文件");
//        }
//        String contentType = commonRequestBo.getFile().getContentType();
//        if (!contentType.contains("")) {
//            throw BusinessException.withErrorCode("文件类型错误");
//        }

        if(!commonRequestBo.getType().equals(EnumGoodsGiftType.GOODS.getCode()) && !commonRequestBo.getType().equals(EnumGoodsGiftType.GIFT.getCode())){
            throw BusinessException.withErrorCode("type 参数不正确");
        }

        try {
//            String root_fileName = commonRequestBo.getFile().getOriginalFilename();
//            String filePath = location + DateFormatUtil.getCurrentDate(DateFormatUtil.DateTimeFormatDay);
//            log.info("上传图片:name={},type={}", root_fileName, contentType);
//            log.info("文件保存路径={}", filePath);

            GoodsGiftDomain domain = new GoodsGiftDomain();
            domain.setId(commonRequestBo.getId());
            domain.setUrl(commonRequestBo.getUrl());
            domain.setType(commonRequestBo.getType());
            domain.setName(commonRequestBo.getName());
            domain.setContent(commonRequestBo.getContent());
            domain.setOrderBy(commonRequestBo.getOrderBy());
            if(StringUtils.isBlank(commonRequestBo.getId())){
                domain.setStatus(EnumGoodsGiftStatus.ON.getCode());
            }

//            goodsGiftService.save(commonRequestBo.getFile(), filePath, domain);
            goodsGiftService.save(commonRequestBo.getFileId(), domain);

            return RJResponse.success("保存成功");
        } catch (Exception e) {
            return RJResponse.fail(exceptionMsg(e, "保存失败"));
        }
    }

    @UserLoginToken
    @ApiOperation("排序向前一个")
    @PostMapping("/up/{id}")
    public RJResponse up(@ApiParam(name = "id", value = "编码", required = true) @PathVariable String id)  {

        if (StringUtils.isBlank(id)) {
            throw new BusinessException("请重新选择");
        }
        try {
            GoodsGiftDomain domain = goodsGiftService.get(id);
            if(null == domain){
                throw new BusinessException(ErrorConstant.Common.NOT_FOND);
            }
            int orderBy = domain.getOrderBy();
            orderBy = orderBy <= 2 ? 1 : orderBy - 1;

            domain.setOrderBy(orderBy);

            goodsGiftService.save(domain);
            return RJResponse.success("排序成功");
        } catch (Exception e) {
            return RJResponse.fail(exceptionMsg(e, "排序失败"));
        }
    }

    @UserLoginToken
    @ApiOperation("排序向后一个")
    @PostMapping("/down/{id}")
    public RJResponse down(@ApiParam(name = "id", value = "编码", required = true) @PathVariable String id)  {

        if (StringUtils.isBlank(id)) {
            throw new BusinessException("请重新选择");
        }
        try {
            GoodsGiftDomain domain = goodsGiftService.get(id);
            if(null == domain){
                throw new BusinessException(ErrorConstant.Common.NOT_FOND);
            }

            domain.setOrderBy(domain.getOrderBy() + 1);

            goodsGiftService.save(domain);
            return RJResponse.success("排序成功");
        } catch (Exception e) {
            return RJResponse.fail(exceptionMsg(e, "排序失败"));
        }
    }

    @UserLoginToken
    @ApiOperation("商品删除")
    @PostMapping("/delete/{id}")
    public RJResponse delete(@ApiParam(name = "id", value = "轮播编码", required = true) @PathVariable String id)  {

        if (StringUtils.isBlank(id)) {
            throw BusinessException.withErrorCode("请重新选择");
        }
        try {
            goodsGiftService.delete(id);
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
            GoodsGiftDomain domain = goodsGiftService.get(id);
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
//            @ApiParam(name = "name", value = "名称") @RequestParam(name = "name", required = false) String name,
//                           @ApiParam(name = "content", value = "内容查询") @RequestParam(name = "content", required = false) String content,
//                           @ApiParam(name = "type", value = "类别(1商品2礼品)", required = true) @RequestParam(name = "type", required = true) String type,
//                           @ApiParam(name = "page", value = "页数", required = false)
//                           @RequestParam(name = "page", required = false, defaultValue = "1")
//                                   Integer page,
//                           @ApiParam(name = "pageSize", value = "每页数量", required = false)
//                           @RequestParam(name = "pageSize", required = false, defaultValue = "20")
//                                   Integer pageSize
    ) {
        try {
            GoodsGiftDomain domain = new GoodsGiftDomain();

            if(StringUtils.isBlank(commonRequestBo.getType())){
                return RJResponse.fail(ErrorConstant.Common.PARAM_IS_EMPTY);
            }
            domain.setType(commonRequestBo.getType());

            if(StringUtils.isNotBlank(commonRequestBo.getName())){
                domain.setName(commonRequestBo.getName());
            }
            if(StringUtils.isNotBlank(commonRequestBo.getContent())){
                domain.setContent(commonRequestBo.getContent());
            }
            domain.setHasDeleted(EnumBoolean.FALSE.getVal());

            PageInfo<GoodsGiftDomain> pageInfo = goodsGiftService.page(domain, commonRequestBo.getPageNum(), commonRequestBo.getPageSize());

            // 重新封装pageInfo
            return RJResponse.page(pageInfo);
        } catch (Exception e) {
            return RJResponse.fail(exceptionMsg(e, "查询失败"));
        }
    }

}
