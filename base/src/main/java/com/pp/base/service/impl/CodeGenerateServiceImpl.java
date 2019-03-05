package com.pp.base.service.impl;

import com.pp.base.service.ICodeGenerateService;
import com.pp.base.service.IRedisService;
import com.pp.base.utils.CodeRedisHelper;
import com.pp.base.utils.DateFormatUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 */
@Service
public class CodeGenerateServiceImpl implements ICodeGenerateService {

    @Autowired
    private IRedisService redisService;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//  private SimpleDateFormat sdf1 = new SimpleDateFormat("yyMMdd");

    @Override
    public String makeEmpCode() {
        long id = redisService.increment(CodeRedisHelper.seq_emp_code);
        StringBuilder sb = new StringBuilder();
        sb.append("E");
        sb.append(sdf.format(new Date()));
        sb.append(StringUtils.leftPad(String.valueOf(id), 4, "0"));
        sb.append(uuidRandom());
        return sb.toString();
    }

    @Override
    public String makeOrderCode() {
        long id = redisService.increment(CodeRedisHelper.order_code);
        StringBuilder sb = new StringBuilder();
        sb.append("O");
        sb.append(sdf.format(new Date()));
        sb.append(StringUtils.leftPad(String.valueOf(id), 5, "0"));
        sb.append(uuidRandom());
        return sb.toString();
    }

    @Override
    public String makeOrderItemCode(){
        long id = redisService.increment(CodeRedisHelper.order_code);
        StringBuilder sb = new StringBuilder();
        sb.append("OI");
        sb.append(sdf.format(new Date()));
        sb.append(StringUtils.leftPad(String.valueOf(id), 5, "0"));
        sb.append(uuidRandom());
        return sb.toString();
    }

    @Override
    public String makeOrderCode(Date date) {
        long id = redisService.increment(CodeRedisHelper.order_code);
        StringBuilder sb = new StringBuilder();
        sb.append("O");
        sb.append(sdf.format(date));
        sb.append(StringUtils.leftPad(String.valueOf(id), 5, "0"));
        sb.append(uuidRandom());
        return sb.toString();
    }

    @Override
    public String makeDeptCode() {
        long id = redisService.increment(CodeRedisHelper.dept_code);
        StringBuilder sb = new StringBuilder();
        sb.append("DP");
        sb.append(sdf.format(new Date()));
        sb.append(StringUtils.leftPad(String.valueOf(id), 4, "0"));
        sb.append(uuidRandom());
        return sb.toString();
    }

    @Override
    public String makePostCode() {
        long id = redisService.increment(CodeRedisHelper.post_code);
        StringBuilder sb = new StringBuilder();
        sb.append("PT");
        sb.append(sdf.format(new Date()));
        sb.append(StringUtils.leftPad(String.valueOf(id), 4, "0"));
        sb.append(uuidRandom());
        return sb.toString();
    }

    @Override
    public String makeImageName() {
        long id = redisService.increment(CodeRedisHelper.image_code);
        StringBuilder sb = new StringBuilder();
        sb.append("IMG");
        sb.append(sdf.format(new Date()));
        sb.append(StringUtils.leftPad(String.valueOf(id), 4, "0"));
        sb.append(uuidRandom());
        return sb.toString();
    }

    @Override
    public String makeFileCode(){
        //long id = redisService.increment(CodeRedisHelper.image_code);
        long id = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        sb.append("F");
        sb.append(sdf.format(new Date()));
        sb.append(StringUtils.leftPad(String.valueOf(id), 4, "0"));
        sb.append(uuidRandom());
        return sb.toString();
    }

    @Override
    public String makeRoleCode() {
        long id = redisService.increment(CodeRedisHelper.role_code);
        StringBuilder sb = new StringBuilder();
        sb.append("R");
        sb.append(sdf.format(new Date()));
        sb.append(StringUtils.leftPad(String.valueOf(id), 4, "0"));
        sb.append(uuidRandom());
        return sb.toString();
    }

    @Override
    public String makeRoleMenuCode(){
        long id = redisService.increment(CodeRedisHelper.role_code);
        StringBuilder sb = new StringBuilder();
        sb.append("RM");
        sb.append(sdf.format(new Date()));
        sb.append(StringUtils.leftPad(String.valueOf(id), 4, "0"));
        sb.append(uuidRandom());
        return sb.toString();
    }

    @Override
    public String makeMenuCode() {
        long id = redisService.increment(CodeRedisHelper.menu_code);
        StringBuilder sb = new StringBuilder();
        sb.append("M");
        sb.append(sdf.format(new Date()));
        sb.append(StringUtils.leftPad(String.valueOf(id), 4, "0"));
        sb.append(uuidRandom());
        return sb.toString();
    }

    @Override
    public String makeUserCode() {
        long id = redisService.increment(CodeRedisHelper.house_user_code);
        StringBuilder sb = new StringBuilder();
        sb.append("US");
        sb.append(sdf.format(new Date()));
        sb.append(StringUtils.leftPad(String.valueOf(id), 4, "0"));
        sb.append(uuidRandom());
        return sb.toString();
    }

    @Override
    public String makeUserRoleCode(){
        long id = redisService.increment(CodeRedisHelper.house_user_code);
        StringBuilder sb = new StringBuilder();
        sb.append("UR");
        sb.append(sdf.format(new Date()));
        sb.append(StringUtils.leftPad(String.valueOf(id), 4, "0"));
        sb.append(uuidRandom());
        return sb.toString();
    }

    @Override
    public String makeSerialNumber() {
        long id = redisService.increment(CodeRedisHelper.fin_serial_code);
        StringBuilder sb = new StringBuilder();
        sb.append(sdf.format(new Date()));
        sb.append(StringUtils.leftPad(String.valueOf(id), 8, "0"));
        sb.append(uuidRandom());
        return sb.toString();
    }


    @Override
    public String makeSmsPoolCode() {
        long id = redisService.increment(CodeRedisHelper.sms_pool_code);
        StringBuilder sb = new StringBuilder();
        sb.append("SP");
        sb.append(sdf.format(new Date()));
        sb.append(StringUtils.leftPad(String.valueOf(id), 4, "0"));
        sb.append(uuidRandom());
        return sb.toString();
    }

    @Override
    public String makeCommonCode(){
        long id = redisService.increment(CodeRedisHelper.common_code);
        StringBuilder sb = new StringBuilder();
        sb.append(sdf.format(new Date()));
        sb.append(StringUtils.leftPad(String.valueOf(id), 4, "0"));
        sb.append(uuidRandom());
        return sb.toString();
    }

    @Override
    public String makeGoodsTypeCode() {
        return makeCode("GT");
    }

    @Override
    public String makeGoodsCateCode(){
        return makeCode("GC");
    }

    @Override
    public String makeGoodsGiftCode() {
        return makeCode("GG");
    }

    @Override
    public String makePackgeId(){
        return makeCode("P", 7);
    }

    @Override
    public String makePackageHistoryId() {
        return makeCode("PH", 7);
    }

    @Override
    public String makeMemberGiftCode(){
        return makeCode("MG");
    }

    @Override
    public String makeMemberMessageCode(){
        return makeCode("MG");
    }

    @Override
    public String makeHouseparkCode() {
        return makeCode("H", 10);
    }
    @Override
    public String makeBuildingCode() {
        return makeCode("B", 10);
    }
    @Override
    public String makeUnitCode() {
        return makeCode("U", 10);
    }
    @Override
    public String makeFloorCode() {
        return makeCode("F", 10);
    }
    @Override
    public String makeDoorCode() {
        return makeCode("D", 10);
    }

    @Override
    public String makeCompanyCode() {
        return makeCode("PP", 10);
    }

    @Override
    public String makeOnlineStoreCode() {
        return makeCode("OS", 10);
    }

    @Override
    public String makeArticleMenuCode() {
        return makeCode("AM", 10);
    }
    @Override
    public String makeArticleContentCode() {
        return makeCode("AC", 10);
    }

    //视频编号生成
    @Override
    public String makeVideoCode() {
        return makeCode("VO",10);
    }

    private String makeCode(String preCode){
//        long id = redisService.increment(CodeRedisHelper.common_code);
//        StringBuilder sb = new StringBuilder();
//        sb.append(preCode);
//        sb.append(sdf.format(new Date()));
//        sb.append(StringUtils.leftPad(String.valueOf(id), 4, "0"));
//        sb.append(uuidRandom());
//        return sb.toString();
        return this.makeCode(preCode, DateFormatUtil.getCurrentDateTime(), 4);
    }

    private String makeCode(String preCode, Integer size){
        return makeCode(preCode, DateFormatUtil.getCurrentDateTime(), size);
    }

    private String makeCode(String preCode, Date date){
//        long id = redisService.increment(CodeRedisHelper.common_code);
//        StringBuilder sb = new StringBuilder();
//        sb.append(preCode);
//        sb.append(sdf.format(date));
//        sb.append(StringUtils.leftPad(String.valueOf(id), 4, "0"));
//        sb.append(uuidRandom());
//        return sb.toString();
        return makeCode(preCode, date, 4);
    }

    private String makeCode(String preCode, Date date, int size){
        long id = redisService.increment(CodeRedisHelper.common_code);
        StringBuilder sb = new StringBuilder();
        sb.append(preCode);
        sb.append(sdf.format(date));
        sb.append(StringUtils.leftPad(String.valueOf(id), size, "0"));
        sb.append(uuidRandom());
        return sb.toString();
    }

    private String makeCode(){
//        long id = redisService.increment(CodeRedisHelper.common_code);
//        StringBuilder sb = new StringBuilder();
////        sb.append("BID");
//        sb.append(sdf.format(new Date()));
//        sb.append(StringUtils.leftPad(String.valueOf(id), 6, "0"));
//        sb.append(uuidRandom());
//        return sb.toString();

        return makeCode("", DateFormatUtil.getCurrentDateTime(), 6);

    }

    public static String uuidRandom(){
        String tmp = UUID.randomUUID().toString();
        tmp = tmp.substring(0,4);
        return tmp;
    }
}
