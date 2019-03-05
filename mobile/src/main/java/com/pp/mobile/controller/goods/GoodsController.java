package com.pp.mobile.controller.goods;

import com.github.pagehelper.PageInfo;
import com.pp.base.common.RJResponse;
import com.pp.base.constant.ErrorConstant;
import com.pp.base.enums.EnumBoolean;
import com.pp.base.exception.BusinessException;
import com.pp.base.utils.DateFormatUtil;
import com.pp.mobile.annotation.UserLoginToken;
import com.pp.mobile.controller.FSBaseController;
import com.pp.goods.domain.GoodsGiftDomain;
import com.pp.goods.domain.MemberGiftDomain;
import com.pp.goods.enums.EnumGoodsGiftType;
import com.pp.goods.enums.EnumMemberGiftStatus;
import com.pp.goods.service.IGoodsGiftService;
import com.pp.goods.service.IMemberGiftService;
import com.pp.member.domain.MemberDomain;
import com.pp.member.service.IMemberService;
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
@Api("商品查询")
@RestController
@RequestMapping("goods")
public class GoodsController extends FSBaseController {

    @Autowired
    private IGoodsGiftService goodsGiftService;
    @Autowired
    private IMemberGiftService memberGiftService;
    @Autowired
    private IMemberService memberService;

    public String getMemberId(){
        Object obj = getMemberObj();
        if(null == obj){
            throw BusinessException.withErrorCode(ErrorConstant.Auth.NOT_LOGIN);
        }
        MemberDomain member = (MemberDomain) obj;
        return member.getMemberId();
    }

    /**
     * 分页查询
     * @return
     */
//    @UserLoginToken
    @ApiOperation("分页查询")
    @PostMapping(value = "/page")
    public RJResponse page(
            @RequestBody CommonRequestBo commonRequestBo
            /*@ApiParam(name = "name", value = "名称") @RequestParam(name = "name", required = false) String name,
                           @ApiParam(name = "content", value = "内容查询") @RequestParam(name = "content", required = false) String content,
                           @ApiParam(name = "type", value = "类别(1商品2礼品)", required = true) @RequestParam(name = "type", required = true) String type,
                           @ApiParam(name = "page", value = "页数", required = false)
                           @RequestParam(name = "page", required = false, defaultValue = "1")
                                   Integer page,
                           @ApiParam(name = "pageSize", value = "每页数量", required = false)
                           @RequestParam(name = "pageSize", required = false, defaultValue = "4")
                                   Integer pageSize*/
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

            if(!CollectionUtils.isEmpty(pageInfo.getList())){

                for(GoodsGiftDomain gg : pageInfo.getList())
                    if(EnumGoodsGiftType.GIFT.getCode().equals(gg.getType())){
                        List<MemberGiftDomain> list = memberGiftService.get(gg.getId(), getMemberId());
                        if(CollectionUtils.isEmpty(list)){
                            gg.setHasTake(EnumBoolean.FALSE.getVal());
                            gg.setHasTakeStr("未领取");
                        }else{
                            gg.setHasTake(EnumBoolean.TRUE.getVal());
                            gg.setHasTakeStr("已领取");
                        }
                    }
            }

            // 重新封装pageInfo
            return RJResponse.page(pageInfo);
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
    @PostMapping(value = "/detail/{id}")
    public RJResponse detail(@ApiParam(name = "id", value = "goodsId", required = true) @PathVariable String id) {
        try {

            GoodsGiftDomain domain = goodsGiftService.get(id);

            if(EnumGoodsGiftType.GIFT.getCode().equals(domain.getType())){
                List<MemberGiftDomain> list = memberGiftService.get(domain.getId(), getMemberId());
                if(CollectionUtils.isEmpty(list)){
                    domain.setHasTake(EnumBoolean.FALSE.getVal());
                    domain.setHasTakeStr("未领取");
                }else{
                    domain.setHasTake(EnumBoolean.TRUE.getVal());
                    domain.setHasTakeStr("已领取");
                }
            }
            return RJResponse.success(domain);
        } catch (Exception e) {
            return RJResponse.fail(exceptionMsg(e, "查询失败"));
        }
    }

    /**
     * 详细
     * @return
     */
    @UserLoginToken
    @ApiOperation("详细")
    @PostMapping(value = "/take/{id}")
    public RJResponse take(@ApiParam(name = "id", value = "goodsId", required = true) @PathVariable String id) {
        try {

            GoodsGiftDomain domain = goodsGiftService.get(id);
            if(null == domain){
                return RJResponse.fail(ErrorConstant.Common.NOT_FOND);
            }
            if(!EnumGoodsGiftType.GIFT.getCode().equals(domain.getType())){
                return RJResponse.fail("不是礼品不能领取");
            }

            String memberId = getMemberId();

            List<MemberGiftDomain> list = memberGiftService.get(domain.getId(), memberId);
            if(CollectionUtils.isEmpty(list)){
                MemberDomain member = memberService.get(memberId);
                // 领取的时候才有奖励
//                MemberAddressDomain address = memberAddressService.getDefault(memberId);
//                private String addressId;
//                private String contactName;
//                private String contactPhone;
//                private String addressDetail;

                MemberGiftDomain gift = new MemberGiftDomain();
                gift.setStatus(EnumMemberGiftStatus.INITIAL.getCode());
                gift.setGiftId(domain.getId());
                gift.setGiftName(domain.getName());
                gift.setGiftNum(1);
                gift.setMemberId(memberId);
                gift.setMemberName(member.getMemberName());
                gift.setCreateCode(memberId);
                gift.setModifyCode(memberId);
                gift.setCreateTime(DateFormatUtil.getCurrentDateTime());
                gift.setModifyTime(DateFormatUtil.getCurrentDateTime());
                memberGiftService.save(gift);
            }else{
                return RJResponse.fail("您已经领取改奖励");
            }
            return RJResponse.success("领取成功");
        } catch (Exception e) {
            return RJResponse.fail(exceptionMsg(e, "领取失败"));
        }
    }

}
