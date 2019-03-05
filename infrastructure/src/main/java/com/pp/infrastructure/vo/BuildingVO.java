package com.pp.infrastructure.vo;

import lombok.Data;

@Data
public class BuildingVO {

    private String code;
    /**
     * 小区编码
     */
    private String upCode;

    private String upName;

    private String buildingNums;

    private String unitNum;

    private String floorNum;

    private String doorNum;

    /**
     * 保存房号
     */
    private String addressCode;
    private String houseCode;
    private String buildingCode;
    private String unitCode;
    private String name;


}
