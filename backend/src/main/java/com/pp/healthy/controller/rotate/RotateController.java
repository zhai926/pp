package com.pp.healthy.controller.rotate;

import com.github.pagehelper.PageInfo;
import com.pp.article.domain.RotateDomain;
import com.pp.article.service.IRotateService;
import com.pp.base.common.RJResponse;
import com.pp.base.controller.BaseController;
import com.pp.base.enums.EnumBoolean;
import com.pp.base.exception.BusinessException;
import com.pp.healthy.annotation.UserLoginToken;
import com.pp.healthy.domain.CommonRequestBo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@Slf4j
@Api("轮播图管理")
@RestController
@RequestMapping("/rotate")
public class RotateController extends BaseController {

    @Autowired
    private IRotateService rotateService;

    /**
     * 在配置文件中配置的文件保存路径
     */
    @Value("${web.upload-path}")
    private String location;

    @UserLoginToken
    @ApiOperation("轮播图保存")
    @PostMapping("/save")
    public RJResponse save(
            @RequestBody CommonRequestBo commonRequestBo
//            @ApiParam(name = "file", value = "文件对象", required = true) @RequestParam(name = "file", required = false ) MultipartFile multipartFile
//                           @ApiParam(name = "id", value = "轮播图对象", required = false) @RequestParam(name = "id", required = false, defaultValue = "") String id,
//                           @ApiParam(name = "url", value = "轮播图对象", required = false) @RequestParam(name = "url", required = false, defaultValue = "") String url,
//                           @ApiParam(name = "content", value = "轮播图对象", required = false) @RequestParam(name = "content", required = false,  defaultValue = "") String content
    )  {

//        if (null == commonRequestBo.getFile() || commonRequestBo.getFile().isEmpty() || StringUtils.isBlank(commonRequestBo.getFile().getOriginalFilename())) {
//            throw BusinessException.withErrorCode("请上传文件");
//        }
//        String contentType = commonRequestBo.getFile().getContentType();
//        if (!contentType.contains("")) {
//            throw BusinessException.withErrorCode("文件类型错误");
//        }
        try {
//            String root_fileName = commonRequestBo.getFile().getOriginalFilename();
//            String filePath = location + DateFormatUtil.getCurrentDate(DateFormatUtil.DateTimeFormatDay);
//            log.info("上传图片:name={},type={}", root_fileName, contentType);
//            log.info("文件保存路径={}", filePath);

            RotateDomain domain = new RotateDomain();
            domain.setId(commonRequestBo.getId());
            domain.setUrl(commonRequestBo.getUrl());
            domain.setContent(commonRequestBo.getContent());
            domain.setFileId(commonRequestBo.getFileId());

//            rotateService.save(commonRequestBo.getFile(), filePath, domain);
            rotateService.save(commonRequestBo.getFileId(), domain);

            return RJResponse.success("保存成功");
        } catch (Exception e) {
            System.out.println("错误的原因:" + e.getMessage());
            return RJResponse.fail(exceptionMsg(e, "保存失败"));
        }
    }

    @UserLoginToken
    @ApiOperation("轮播图删除")
    @PostMapping("/delete/{id}")
    public RJResponse delete(@ApiParam(name = "id", value = "轮播编码", required = true) @PathVariable String id)  {

        if (StringUtils.isBlank(id)) {
            throw BusinessException.withErrorCode("请重新选择");
        }
        try {
            rotateService.delete(id);
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
            RotateDomain domain = rotateService.get(id);
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
//            @ApiParam(name = "content", value = "内容查询") @RequestParam(name = "content", required = false) String content,
//                           @ApiParam(name = "page", value = "页数", required = false)
//                           @RequestParam(name = "page", required = false, defaultValue = "1")
//                                   Integer page,
//                           @ApiParam(name = "pageSize", value = "每页数量", required = false)
//                               @RequestParam(name = "pageSize", required = false, defaultValue = "20")
//                                       Integer pageSize
    ) {
        try {
            RotateDomain domain = new RotateDomain();
            if(StringUtils.isNotBlank(commonRequestBo.getContent())){
                domain.setContent(commonRequestBo.getContent());
            }
            domain.setHasDeleted(EnumBoolean.FALSE.getVal());

            PageInfo<RotateDomain> pageInfo = rotateService.page(domain, commonRequestBo.getPageNum(), commonRequestBo.getPageSize());

            // 重新封装pageInfo
            return RJResponse.page(pageInfo);
        } catch (Exception e) {
            return RJResponse.fail(exceptionMsg(e, "查询失败"));
        }
    }
}
