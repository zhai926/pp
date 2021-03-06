package com.pp.frontend.controller.rotate;

import com.github.pagehelper.PageInfo;
import com.pp.article.domain.RotateDomain;
import com.pp.article.service.IRotateService;
import com.pp.base.common.RJResponse;
import com.pp.base.enums.EnumBoolean;
import com.pp.frontend.controller.FSBaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Api("pc/mobile 轮播图")
@Controller
@RequestMapping("rotate")
public class RotateController extends FSBaseController {

    @Autowired
    private IRotateService rotateService;

    /**
     * 分页查询
     * @return
     */
    @ApiOperation("分页查询")
    @GetMapping(value = "/page")
    @ResponseBody
    public RJResponse page(@ApiParam(name = "content", value = "内容查询") @RequestParam(name = "content", required = false) String content,
                           @ApiParam(name = "page", value = "页数", required = false)
                           @RequestParam(name = "page", required = false, defaultValue = "1")
                                   Integer page,
                           @ApiParam(name = "pageSize", value = "每页数量", required = false)
                           @RequestParam(name = "pageSize", required = false, defaultValue = "3")
                                   Integer pageSize) {
        try {
            RotateDomain domain = new RotateDomain();
//            if(StringUtils.isNotBlank(content)){
//                domain.setContent(content);
//            }
            domain.setHasDeleted(EnumBoolean.FALSE.getVal());

            PageInfo<RotateDomain> pageInfo = rotateService.page(domain, page, pageSize);

            // 重新封装pageInfo
            return RJResponse.page(pageInfo);
        } catch (Exception e) {
            return RJResponse.fail(exceptionMsg(e, "查询失败"));
        }
    }
}
