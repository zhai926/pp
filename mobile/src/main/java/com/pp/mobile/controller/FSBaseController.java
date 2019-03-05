package com.pp.mobile.controller;

import com.pp.base.controller.BaseController;
import com.pp.member.domain.MemberDomain;

public abstract class FSBaseController extends BaseController {


    public MemberDomain getMember(){
        Object obj = getMemberObj();
        if(null != obj){
            return (MemberDomain) obj;
        }
        return null;
    }

}
