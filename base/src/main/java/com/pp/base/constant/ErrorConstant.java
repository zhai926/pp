package com.pp.base.constant;

/**
 */
public interface ErrorConstant {

    interface Common {
        String PARAM_IS_EMPTY = "参数为空";
        String INVALID_PARAM = "无效的参数";
        String CAN_NOT_FIND_PARAM_TO_CONTIUNE = "找不到参数继续运行";
        String MOBILE_EXIST = "手机号已存在";
        String ACCOUNT_EXIST = "账号已存在";
        String CARD_EXIST = "证件号已存在";
        String NOT_FOND = "没有找到对应值";
        String FIND_MORE_RECORD = "找到多个对应值";
    }

    interface Option {
        String DELETE_OPTION_FAIL = "删除配置失败";
        String UPDATE_OPTION_FAIL = "更新配置失败";
    }

    interface Meta {
        String ADD_META_FAIL = "添加项目信息失败";
        String UPDATE_META_FAIL = "更新项目信息失败";
        String DELETE_META_FAIL = "删除项目信息失败";
        String NOT_ONE_RESULT = "获取的项目的数量不止一个";
        String META_IS_EXIST = "该项目已经存在";
    }

    interface Auth {
        String USERNAME_PASSWORD_IS_EMPTY = "用户名和密码不可为空";
        String USERNAME_PASSWORD_ERROR = "用户名不存在或密码错误";
        String NOT_LOGIN = "用户未登录";
    }

    interface Plate{
        String PLATE_IS_NULL = "找不到环保袋";
        String PLATE_CODE_IS_EMPTY = "环保袋编码为空";
        String PLATE_HAS_BIND = "环保袋已被绑定";
    }

    interface Puppet{
        String PUPPET_IS_NULL = "找不到用户";
        String PUPPET_ID_IS_EMPTY = "用户编码不存在";
    }

    interface AppointmentOrder{
        String BAG_NOT_FOND_APPOINTMENT_ORDER = "没有找到该环保袋对应的未盘点预约订单，环保袋编码： %s";
        String NOT_FOND = "预约订单找不到对应值";
        String FIND_MORE_RECORD = "预约订单找到多个对应值";
        String FIND_MORE_RECORD_BY_CODE = "预约订单找到多个对应值，订单编码 %s";
    }

    interface Order{
        String FIND_MORE_RECORD = "订单找到对个对应值";
        String BAG_MEMBER_NO_PRE_ORDER = "没有找到对应已分配预约订单，环保袋编码：%s，用户编码：%s。";
        String BAG_MEMBER_MORE_PRE_ORDER = "找到多个对应已分配预约订单，环保袋编码：%s，用户编码：%s。";
        String BAG_HAS_ORDER = "该环保袋已被盘点，环保袋编码：%s";
        String NEED_ORDER_CONFIRM_PASS = "请输入订单确认密码";
        String ORDER_CONFIRM_PASS_ERROR = "订单确认密码不正确";
    }

    interface Member{
        String NOT_FIND = "没有该会员信息";
    }


}
