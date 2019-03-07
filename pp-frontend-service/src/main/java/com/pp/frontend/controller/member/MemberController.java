package com.pp.frontend.controller.member;

import com.pp.frontend.controller.FSBaseController;
import com.pp.goods.domain.MemberGiftDomain;
import com.pp.goods.service.IMemberGiftService;
import com.pp.member.domain.MemberAddressDomain;
import com.pp.member.domain.MemberDomain;
import com.pp.member.service.IMemberAddressService;
import com.pp.member.service.IMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Slf4j
@Api("用户")
@Controller
@RequestMapping("/member")
public class MemberController extends FSBaseController {

    @Autowired
    private IMemberService memberService;
    @Autowired
    private IMemberGiftService memberGiftService;
    @Autowired
    private IMemberAddressService memberAddressService;


    @ApiOperation("会员中心")
    @GetMapping(value="/center")
    public String center(Model model){
        MemberDomain member = getMember();

        // 领取奖励
        List<MemberGiftDomain> memberGiftList = memberGiftService.getByMemberId(member.getMemberId());
        // 收货地址
        List<MemberAddressDomain> memberAddressList = memberAddressService.getByMemberId(member.getMemberId());

        model.addAttribute("memberGiftList", memberGiftList);
        model.addAttribute("memberAddressList", memberAddressList);
        return "member/center";
    }


}
