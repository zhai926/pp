package com.pp.frontend.controller.article;

import com.github.pagehelper.PageInfo;
import com.pp.article.domain.ArticleContentDomain;
import com.pp.article.domain.ArticleMenuDomain;
import com.pp.article.enums.EnumArticleMenuType;
import com.pp.article.service.IArticleContentService;
import com.pp.article.service.IArticleMenuService;
import com.pp.base.common.RJResponse;
import com.pp.base.constant.ErrorConstant;
import com.pp.base.enums.EnumBoolean;
import com.pp.base.exception.BusinessException;
import com.pp.frontend.controller.FSBaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Api("文章 1企业文章 2分类导航 3营养健康解决方案")
@Controller
@RequestMapping("article")
public class ArticleController extends FSBaseController {

    @Autowired
    private IArticleMenuService articleMenuService;
    @Autowired
    private IArticleContentService articleContentService;

    /**
     * 关于我们
     * @return
     */
    @ApiOperation("分页查询")
    @GetMapping(value = "/about_us")
    public String aboutUs(Model model, @ApiParam(name = "name", value = "名称") @RequestParam(name = "name", required = false) String name,
                              @ApiParam(name = "page", value = "页数", required = false)
                               @RequestParam(name = "page", required = false, defaultValue = "1")
                                       Integer page,
                              @ApiParam(name = "pageSize", value = "每页数量", required = false)
                               @RequestParam(name = "pageSize", required = false, defaultValue = "20")
                                       Integer pageSize) {
        try {
            ArticleMenuDomain domain = new ArticleMenuDomain();

            domain.setType(EnumArticleMenuType.COM.getCode());
            domain.setHasDeleted(EnumBoolean.FALSE.getVal());

            List<ArticleMenuDomain> list = articleMenuService.get(domain); //articleMenuService.page(domain, page, pageSize);

            if(!CollectionUtils.isEmpty(list)){
                for(ArticleMenuDomain amd: list){
                    ArticleContentDomain con = articleContentService.getDefaultByMenuId(amd.getId());
                    if(null != con){
                        //System.out.println("获取：" + new String(con.getContent().getBytes(),"utf-8"));
                        amd.setDefaultContent(new String(con.getContent().getBytes(),"utf-8"));
                    }
                }
            }

            model.addAttribute("menuList", list);
            return "article/about_us";
        } catch (Exception e) {
            throw new BusinessException("请联系管理员");
        }
    }

    /**
     * 健康顾问
     * @return
     */
    @ApiOperation("健康顾问")
    @GetMapping(value = "/healthy")
    public String healthy(Model model,
                          @ApiParam(name = "name", value = "名称") @RequestParam(name = "name", required = false) String name,
                          @ApiParam(name = "keyword", value = "名称") @RequestParam(name = "keyword", required = false) String keyword,
                          @ApiParam(name = "menuId", value = "类别( 1企业文章 2分类导航 3营养健康解决方案)", required = true)
                          @RequestParam(name = "menuId", required = false) String menuId,
                          @ApiParam(name = "page", value = "页数", required = false)
                          @RequestParam(name = "page", required = false, defaultValue = "1")
                                  Integer page,
                          @ApiParam(name = "pageSize", value = "每页数量", required = false)
                          @RequestParam(name = "pageSize", required = false, defaultValue = "20")
                                  Integer pageSize) {
        try {
            ArticleMenuDomain domain = new ArticleMenuDomain();
            domain.setType(EnumArticleMenuType.HEALTH.getCode());
            domain.setHasDeleted(EnumBoolean.FALSE.getVal());
            List<ArticleMenuDomain> healthMenuList = articleMenuService.get(domain); //articleMenuService.page(domain, page, pageSize);

//            Map<String, List<ArticleContentDomain>> map = new HashMap<>();
            if(!CollectionUtils.isEmpty(healthMenuList)){
                for(ArticleMenuDomain item: healthMenuList){
                    ArticleContentDomain contentDomain = new ArticleContentDomain();
                    contentDomain.setHasDeleted(false);
                    contentDomain.setMenuId(item.getId());
                    contentDomain.setKeyword(keyword);
                    PageInfo<ArticleContentDomain> pageInfo = articleContentService.page(contentDomain, 1, 6);
//                    map.put(item.getId(), pageInfo.getList());
                    item.setConList(pageInfo.getList());
                }
            }

            domain.setType(EnumArticleMenuType.CATE.getCode());
            List<ArticleMenuDomain> cateMenuList = articleMenuService.get(domain); //articleMenuService.page(domain, page, pageSize);

            if(!CollectionUtils.isEmpty(cateMenuList)){

                for(ArticleMenuDomain item: cateMenuList){
                    ArticleContentDomain contentDomain = new ArticleContentDomain();
                    contentDomain.setHasDeleted(EnumBoolean.FALSE.getVal());
                    contentDomain.setMenuId(item.getId());
                    contentDomain.setKeyword(keyword);
                    PageInfo<ArticleContentDomain> contentPage = articleContentService.page(contentDomain, page, pageSize);
                    item.setConPage(contentPage);
                }
                /*ArticleContentDomain contentDomain = new ArticleContentDomain();
                contentDomain.setHasDeleted(EnumBoolean.FALSE.getVal());
                if(StringUtils.isBlank(menuId)){
                    contentDomain.setMenuId(cateMenuList.get(0).getId());
                }else{
                    contentDomain.setMenuId(menuId);
                }
                PageInfo<ArticleContentDomain> contentPage = articleContentService.page(contentDomain, page, pageSize);
                model.addAttribute("contentPage", contentPage);*/
            }

            model.addAttribute("healthMenuList", healthMenuList);
//            model.addAttribute("healthContentMap", map);

            model.addAttribute("cateMenuList", cateMenuList);

            model.addAttribute("keyword", keyword);
            return "article/healthy";
        } catch (Exception e) {
            throw new BusinessException("请联系管理员");
        }
    }

    /**
     * 健康顾问
     * @return
     */
    @ApiOperation("健康顾问")
    @GetMapping(value = "/healthy_type")
    public String healthyType(Model model, @ApiParam(name = "name", value = "名称") @RequestParam(name = "name", required = false) String name,
                          @ApiParam(name = "menuType", value = "类别( 1企业文章 2分类导航 3营养健康解决方案)", required = true)
                          @RequestParam(name = "menuType", required = false) String menuType,
                          @ApiParam(name = "menuId", value = "类别( 1企业文章 2分类导航 3营养健康解决方案)", required = true)
                          @RequestParam(name = "menuId", required = false) String menuId,
                          @ApiParam(name = "contentId", value = "文章编码", required = false)
                          @RequestParam(name = "contentId", required = false) String contentId,
                          @ApiParam(name = "page", value = "页数", required = false)
                          @RequestParam(name = "page", required = false, defaultValue = "1")
                                  Integer page,
                          @ApiParam(name = "pageSize", value = "每页数量", required = false)
                          @RequestParam(name = "pageSize", required = false, defaultValue = "20")
                                  Integer pageSize) {
        try {
            if(StringUtils.isBlank(menuType) || StringUtils.isBlank(menuId)){
                throw new BusinessException(ErrorConstant.Common.PARAM_IS_EMPTY);
            }

            ArticleMenuDomain menuDomain = new ArticleMenuDomain();
            menuDomain.setHasDeleted(false);
            menuDomain.setType(menuType);

            List<ArticleMenuDomain> list = articleMenuService.get(menuDomain);

            if(!CollectionUtils.isEmpty(list)){
                for(ArticleMenuDomain item: list){
                    if(menuId.equals(item.getId()) && StringUtils.isNotBlank(contentId)){
                        ArticleContentDomain contentDomain = articleContentService.get(contentId);
                        item.setDefaultName(contentDomain.getName());
                        item.setDefaultContent(StringUtils.isBlank(contentDomain.getContent()) ? "" : contentDomain.getContent());
                    }else{
                        ArticleContentDomain contentDomain = new ArticleContentDomain();
                        contentDomain.setMenuId(item.getId());
                        contentDomain.setHasDeleted(false);
                        PageInfo<ArticleContentDomain> contentDomainPageInfo = articleContentService.page(contentDomain, page, pageSize);
                        item.setConPage(contentDomainPageInfo);
                    }
                }
            }

            model.addAttribute("menuList", list);

            model.addAttribute("menuId", menuId);

            // contentId 空 则 文章list 否则文章详细
            /*if(StringUtils.isBlank(contentId)){
                ArticleContentDomain contentDomain = new ArticleContentDomain();
                contentDomain.setMenuId(menuId);
                contentDomain.setHasDeleted(false);
                PageInfo<ArticleContentDomain> contentDomainPageInfo = articleContentService.page(contentDomain, page, pageSize);
                model.addAttribute("contentPage", contentDomainPageInfo);
            }else{
                ArticleContentDomain contentDomain = articleContentService.get(contentId);
                model.addAttribute("content", contentDomain);
            }*/

            return "article/healthy_type";
        } catch (Exception e) {
            throw new BusinessException("请联系管理员");
        }
    }

    /**
     * 分页查询
     * @return
     */
    @ApiOperation("分页查询")
    @GetMapping(value = "/menu_page")
    @ResponseBody
    public RJResponse menuPage(@ApiParam(name = "name", value = "名称") @RequestParam(name = "name", required = false) String name,
                           @ApiParam(name = "type", value = "类别( 1企业文章 2分类导航 3营养健康解决方案)", required = true)
                               @RequestParam(name = "type", required = true) String type,
                           @ApiParam(name = "page", value = "页数", required = false)
                           @RequestParam(name = "page", required = false, defaultValue = "1")
                                   Integer page,
                           @ApiParam(name = "pageSize", value = "每页数量", required = false)
                           @RequestParam(name = "pageSize", required = false, defaultValue = "20")
                                   Integer pageSize) {
        try {
            ArticleMenuDomain domain = new ArticleMenuDomain();

            if(StringUtils.isBlank(type)){
                return RJResponse.fail(ErrorConstant.Common.PARAM_IS_EMPTY);
            }

            domain.setType(type);
            if(StringUtils.isNotBlank(name)){
                domain.setName(name);
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
    @ApiOperation("分页查询")
    @GetMapping(value = "/content_page")
    @ResponseBody
    public RJResponse contentPage(@ApiParam(name = "name", value = "名称") @RequestParam(name = "name", required = false) String name,
                           @ApiParam(name = "content", value = "内容查询") @RequestParam(name = "content", required = false) String content,
                           @ApiParam(name = "menuId", value = "文章类别", required = true)
                               @RequestParam(name = "menuId", required = true) String menuId,
                           @ApiParam(name = "page", value = "页数", required = false)
                           @RequestParam(name = "page", required = false, defaultValue = "1")
                                   Integer page,
                           @ApiParam(name = "pageSize", value = "每页数量", required = false)
                           @RequestParam(name = "pageSize", required = false, defaultValue = "20")
                                   Integer pageSize) {
        try {
            ArticleContentDomain domain = new ArticleContentDomain();

            if(StringUtils.isNotBlank(menuId)){
                domain.setMenuId(menuId);
            }
            if(StringUtils.isNotBlank(name)){
                domain.setName(name);
            }
            domain.setHasDeleted(EnumBoolean.FALSE.getVal());

            PageInfo<ArticleContentDomain> pageInfo = articleContentService.page(domain, page, pageSize);

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
    @ApiOperation("详细")
    @GetMapping(value = "/content/{id}")
    public RJResponse detail(@ApiParam(name = "id", value = "文章内容id", required = true) @PathVariable String id) {
        try {

            ArticleContentDomain domain = articleContentService.get(id);

            return RJResponse.success(domain);
        } catch (Exception e) {
            return RJResponse.fail(exceptionMsg(e, "查询失败"));
        }
    }



}
