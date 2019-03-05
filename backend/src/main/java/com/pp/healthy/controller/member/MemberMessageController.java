package com.pp.healthy.controller.member;

import com.github.pagehelper.PageInfo;
import com.pp.base.common.RJResponse;
import com.pp.base.controller.BaseController;
import com.pp.base.enums.EnumBoolean;
import com.pp.base.exception.BusinessException;
import com.pp.healthy.annotation.UserLoginToken;
import com.pp.healthy.domain.CommonRequestBo;
import com.pp.member.domain.MemberMessageDomain;
import com.pp.member.service.IMemberMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin
@Api("用户留言管理")
@RestController
@RequestMapping("/member_message")
@Slf4j
public class MemberMessageController extends BaseController {

    @Autowired
    private IMemberMessageService memberMessageService;

    /**
     * 详细
     * @return
     */
    @UserLoginToken
    @ApiOperation("详细")
    @PostMapping(value = "/detail/{id}")
    public RJResponse detail(@ApiParam(name = "id", value = "编码", required = true) @PathVariable String id) {
        try {
            MemberMessageDomain domain = memberMessageService.get(id);
            return RJResponse.success(domain);
        } catch (Exception e) {
            return RJResponse.fail(exceptionMsg(e, "查询失败"));
        }
    }

    @UserLoginToken
    @ApiOperation("用户留言分页")
    @RequestMapping("/page")
//    @ResponseBody
    public RJResponse page(
            @RequestBody CommonRequestBo commonRequestBo
            /*Model model,
                           @ApiParam(name = "memberId", value = "用户编码", required = false)
                           @RequestParam(name = "memberId", required = false) String memberId,
                           @ApiParam(name = "content", value = "内容", required = false)
                           @RequestParam(name = "content", required = false) String content,
                           @ApiParam(name = "page", value = "页数", required = false)
                           @RequestParam(name = "page", required = false, defaultValue = "1")
                                   Integer page,
                           @ApiParam(name = "limit", value = "每页数量", required = false)
                           @RequestParam(name = "limit", required = false, defaultValue = "20")
                                   Integer limit*/
    ){
        try{
            MemberMessageDomain domain = new MemberMessageDomain();
//            if(StringUtils.isNotBlank(commonRequestBo.getMe)){
//                domain.setMemberId(memberId);
////            model.addAttribute("memberId", memberId);
//            }
//            if (StringUtils.isNotBlank(content)){
//                domain.setContent(content);
//            }

            domain.setHasDeleted(EnumBoolean.FALSE.getVal());

            PageInfo pageInfo = memberMessageService.page(domain, commonRequestBo.getPageNum(), commonRequestBo.getPageSize());
            return RJResponse.page(pageInfo);
        }catch (Exception e){
            String msg = "用户列表查询JSON失败";
            log.error(e.getMessage());
            if(e instanceof BusinessException){
                msg = StringUtils.isNotBlank(e.getMessage()) ? e.getMessage() : msg;
            }else{
                log.error(msg, e);
            }
            return RJResponse.fail(msg);
        }
    }

}
