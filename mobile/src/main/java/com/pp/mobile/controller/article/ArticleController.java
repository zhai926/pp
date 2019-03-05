package com.pp.mobile.controller.article;

import com.github.pagehelper.PageInfo;
import com.pp.article.domain.ArticleContentDomain;
import com.pp.article.domain.ArticleMenuDomain;
import com.pp.article.service.IArticleContentService;
import com.pp.article.service.IArticleMenuService;
import com.pp.base.common.RJResponse;
import com.pp.base.constant.ErrorConstant;
import com.pp.base.enums.EnumBoolean;
import com.pp.mobile.annotation.UserLoginToken;
import com.pp.mobile.controller.FSBaseController;
import com.pp.mobile.domain.CommonRequestBo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@Slf4j
@Api("文章 1企业文章 2分类导航 3营养健康解决方案")
@RestController
@RequestMapping("article")
public class ArticleController extends FSBaseController {

    @Autowired
    private IArticleMenuService articleMenuService;
    @Autowired
    private IArticleContentService articleContentService;

    /**
     * 分页查询
     * @return
     */
//    @UserLoginToken
    @ApiOperation("查询")
    @PostMapping(value = "/menu_list")
    public RJResponse menu(
            @RequestBody CommonRequestBo commonRequestBo
    ) {
        try {
            ArticleMenuDomain domain = new ArticleMenuDomain();

            if(StringUtils.isBlank(commonRequestBo.getType())){
                return RJResponse.fail(ErrorConstant.Common.PARAM_IS_EMPTY);
            }

            domain.setType(commonRequestBo.getType());
            if(StringUtils.isNotBlank(commonRequestBo.getName())){
                domain.setName(commonRequestBo.getName());
            }
            domain.setHasDeleted(EnumBoolean.FALSE.getVal());

            List<ArticleMenuDomain> menuList = articleMenuService.get(domain); //articleMenuService.page(domain, page, pageSize);

            if(!CollectionUtils.isEmpty(menuList)){
                for(ArticleMenuDomain m: menuList){
                    ArticleContentDomain consearch = new ArticleContentDomain();
                    consearch.setMenuId(m.getId());
                    domain.setHasDeleted(EnumBoolean.FALSE.getVal());
                    PageInfo<ArticleContentDomain> pageInfo = articleContentService.page(consearch, 1, 4);
                    m.setConList(pageInfo.getList());
                }
            }

            // 重新封装pageInfo
            return RJResponse.success(menuList);
        } catch (Exception e) {
            return RJResponse.fail(exceptionMsg(e, "查询失败"));
        }
    }

    /**
     * 分页查询
     * @return
     */
//    @UserLoginToken
    @ApiOperation("分页查询")
    @PostMapping(value = "/menu_page")
    public RJResponse menuPage(
            @RequestBody CommonRequestBo commonRequestBo
            /*@ApiParam(name = "name", value = "名称") @RequestParam(name = "name", required = false) String name,
                           @ApiParam(name = "type", value = "类别( 1企业文章 2分类导航 3营养健康解决方案)", required = true)
                               @RequestParam(name = "type", required = true) String type,
                           @ApiParam(name = "page", value = "页数", required = false)
                           @RequestParam(name = "page", required = false, defaultValue = "1")
                                   Integer page,
                           @ApiParam(name = "pageSize", value = "每页数量", required = false)
                           @RequestParam(name = "pageSize", required = false, defaultValue = "20")
                                   Integer pageSize*/
            ) {
        try {
            ArticleMenuDomain domain = new ArticleMenuDomain();

            if(StringUtils.isBlank(commonRequestBo.getType())){
                return RJResponse.fail(ErrorConstant.Common.PARAM_IS_EMPTY);
            }

            domain.setType(commonRequestBo.getType());
            if(StringUtils.isNotBlank(commonRequestBo.getName())){
                domain.setName(commonRequestBo.getName());
            }
            domain.setHasDeleted(EnumBoolean.FALSE.getVal());

            List<ArticleMenuDomain> pageInfo = articleMenuService.get(domain); //articleMenuService.page(domain, page, pageSize);

            // 重新封装pageInfo
            return RJResponse.success(pageInfo);
        } catch (Exception e) {
            return RJResponse.fail(exceptionMsg(e, "查询失败"));
        }
    }

    /**
     * 分页查询
     * @return
     */
//    @UserLoginToken
    @ApiOperation("分页查询")
    @PostMapping(value = "/content_page")
    public RJResponse contentPage(
            @RequestBody CommonRequestBo commonRequestBo
            /*@ApiParam(name = "name", value = "名称") @RequestParam(name = "name", required = false) String name,
                           @ApiParam(name = "content", value = "内容查询") @RequestParam(name = "content", required = false) String content,
                           @ApiParam(name = "menuId", value = "文章类别", required = true)
                               @RequestParam(name = "menuId", required = true) String menuId,
                           @ApiParam(name = "page", value = "页数", required = false)
                           @RequestParam(name = "page", required = false, defaultValue = "1")
                                   Integer page,
                           @ApiParam(name = "pageSize", value = "每页数量", required = false)
                           @RequestParam(name = "pageSize", required = false, defaultValue = "20")
                                   Integer pageSize*/
    ) {
        try {
            ArticleContentDomain domain = new ArticleContentDomain();

            if(StringUtils.isNotBlank(commonRequestBo.getMenuId())){
                domain.setMenuId(commonRequestBo.getMenuId());
            }
            if(StringUtils.isNotBlank(commonRequestBo.getName())){
                domain.setName(commonRequestBo.getName());
            }
            domain.setHasDeleted(EnumBoolean.FALSE.getVal());

            PageInfo<ArticleContentDomain> pageInfo = articleContentService.page(domain, commonRequestBo.getPageNum(), commonRequestBo.getPageSize());

            // 重新封装pageInfo
            return RJResponse.success(pageInfo);
        } catch (Exception e) {
            return RJResponse.fail(exceptionMsg(e, "查询失败"));
        }
    }

    /**
     * 详细
     * @return
     */
//    @UserLoginToken
    @ApiOperation("详细")
    @PostMapping(value = "/content/{id}")
    public RJResponse detail(@ApiParam(name = "id", value = "文章内容id", required = true) @PathVariable String id) {
        try {

            ArticleContentDomain domain = articleContentService.get(id);

            return RJResponse.success(domain);
        } catch (Exception e) {
            return RJResponse.fail(exceptionMsg(e, "查询失败"));
        }
    }



}
