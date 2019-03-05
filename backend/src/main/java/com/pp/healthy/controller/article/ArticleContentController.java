package com.pp.healthy.controller.article;

import com.github.pagehelper.PageInfo;
import com.pp.article.domain.ArticleContentDomain;
import com.pp.article.service.IArticleContentService;
import com.pp.base.common.RJResponse;
import com.pp.base.constant.ErrorConstant;
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
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@Slf4j
@Api("文章管理")
@RestController
@RequestMapping("/article_content")
public class ArticleContentController extends BaseController {

    @Autowired
    private IArticleContentService articleContentService;

    @UserLoginToken
    @ApiOperation("文章保存")
    @PostMapping("/save")
    public RJResponse save(
            @RequestBody CommonRequestBo commonRequestBo
//            @ApiParam(name = "id", value = "编码", required = false) @RequestParam(name = "id", required = false, defaultValue = "") String id,
//            @ApiParam(name = "menuId", value = "文章菜单", required = false) @RequestParam(name = "menuId", required = false, defaultValue = "") String menuId,
//            @ApiParam(name = "content", value = "文章内容", required = false) @RequestParam(name = "content", required = false, defaultValue = "") String content,
//            @ApiParam(name = "name", value = "菜单名称", required = false) @RequestParam(name = "name", required = false, defaultValue = "") String name,
//            @ApiParam(name = "orderBy", value = "排序", required = false) @RequestParam(name = "orderBy", required = true, defaultValue = "") Integer orderBy
    )  {

        try {
            if(StringUtils.isBlank(commonRequestBo.getMenuId()) || StringUtils.isBlank(commonRequestBo.getName()) || StringUtils.isBlank(commonRequestBo.getContent())){
                throw new BusinessException(ErrorConstant.Common.PARAM_IS_EMPTY);
            }
            ArticleContentDomain domain = new ArticleContentDomain();
            domain.setId(commonRequestBo.getId());
            domain.setStatus(EnumBoolean.FALSE.getStrCode());
            domain.setMenuId(commonRequestBo.getMenuId());
            domain.setContent(commonRequestBo.getContent());
            domain.setName(commonRequestBo.getName());
            domain.setOrderBy(commonRequestBo.getOrderBy());
            articleContentService.save(domain);

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
            ArticleContentDomain domain = articleContentService.get(id);
            if(null == domain){
                throw new BusinessException(ErrorConstant.Common.NOT_FOND);
            }
            int orderBy = domain.getOrderBy();
            orderBy = orderBy <= 2 ? 1 : orderBy - 1;

            domain.setOrderBy(orderBy);

            articleContentService.save(domain);
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
            ArticleContentDomain domain = articleContentService.get(id);
            if(null == domain){
                throw new BusinessException(ErrorConstant.Common.NOT_FOND);
            }

            domain.setOrderBy(domain.getOrderBy() + 1);

            articleContentService.save(domain);
            return RJResponse.success("排序成功");
        } catch (Exception e) {
            return RJResponse.fail(exceptionMsg(e, "排序失败"));
        }
    }

    //@UserLoginToken
    @ApiOperation("文章删除")
    @PostMapping("/delete/{id}")
    public RJResponse delete(@ApiParam(name = "id", value = "编码", required = true) @PathVariable String id)  {

        if (StringUtils.isBlank(id)) {
            throw new BusinessException("请重新选择");
        }
        try {
            articleContentService.delete(id);
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
            ArticleContentDomain domain = articleContentService.get(id);
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
    @ApiOperation("查询")
    @PostMapping(value = "page")
    public RJResponse page(
            @RequestBody CommonRequestBo commonRequestBo
//            @ApiParam(name = "name", value = "内容查询") @RequestParam(name = "name", required = false) String name,
//                           @ApiParam(name = "page", value = "页数", required = false)
//                           @RequestParam(name = "page", required = false, defaultValue = "1")
//                                   Integer page,
//                           @ApiParam(name = "pageSize", value = "每页数量", required = false)
//                           @RequestParam(name = "pageSize", required = false, defaultValue = "20")
//                                   Integer pageSize
    ) {
        try {
            ArticleContentDomain domain = new ArticleContentDomain();
            if(StringUtils.isBlank(commonRequestBo.getMenuId())){
                throw new BusinessException(ErrorConstant.Common.PARAM_IS_EMPTY);
            }

            if(StringUtils.isNotBlank(commonRequestBo.getName())){
                domain.setName(commonRequestBo.getName());
            }
            domain.setMenuId(commonRequestBo.getMenuId());
            domain.setHasDeleted(EnumBoolean.FALSE.getVal());

            PageInfo<ArticleContentDomain> pageInfo = articleContentService.page(domain, commonRequestBo.getPageNum(), commonRequestBo.getPageSize());

            // 重新封装pageInfo
            return RJResponse.page(pageInfo);
        } catch (Exception e) {
            return RJResponse.fail(exceptionMsg(e, "查询失败"));
        }
    }
}
