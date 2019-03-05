package com.pp.base.service;

import java.util.Date;

/**
 * 编号生成器
 */
public interface ICodeGenerateService {

    /**
     * 生成人员编号
     *
     * @return
     */
    String makeEmpCode();


    /**
     * 创建订单编码
     *
     * @return
     */
    String makeOrderCode();

    String makeOrderItemCode();

    String makeOrderCode(Date date);

    /**
     * 部门编码生成器
     *
     * @return
     */
    String makeDeptCode();

    /**
     * 岗位编码生成器
     *
     * @return
     */
    String makePostCode();

    /**
     * 图片名字生成器
     *
     * @return
     */
    String makeImageName();

    String makeFileCode();

    String makeRoleCode();

    String makeRoleMenuCode();

    String makeMenuCode();

    String makeUserCode();

    String makeUserRoleCode();

    String makeSerialNumber();

    String makeSmsPoolCode();
    /**
     * 通用code生成器
     * @return
     */
    String makeCommonCode();

    String makeGoodsTypeCode();

    String makeGoodsCateCode();

    String makeGoodsGiftCode();

    String makePackgeId();

    String makePackageHistoryId();

    String makeMemberGiftCode();

    String makeMemberMessageCode();

    /**
     * 小区编码
     * @return
     */
    String makeHouseparkCode();
    String makeBuildingCode();
    String makeUnitCode();
    String makeFloorCode();
    String makeDoorCode();

    String makeCompanyCode();

    String makeOnlineStoreCode();

    String makeArticleMenuCode();
    String makeArticleContentCode();

    //视频编号生成
    String makeVideoCode();

}
