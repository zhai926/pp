package com.pp.base.utils;

/**
 * redis key
 */
public class CodeRedisHelper {

    public static String seq_emp_code = "pp:sequence:seq_emp_code";
    public static String order_code = "pp:sequence:order_code";
    public static String image_code = "pp:sequence:image_code";
    public static String dept_code = "pp:sequence:dept_code";
    public static String post_code = "pp:sequence:post_code";
    public static String role_code = "pp:sequence:role_code";
    public static String special_auth_code = "pp:sequence:special_auth_code";
    public static String menu_code = "pp:sequence:menu_code";
    public static String sms_pool_code = "pp:sequence:sms_pool_code";
    public static String house_user_code = "pp:sequence:user_code";
    public static String fin_serial_code = "pp:sequence:fin_serial_code";
    public static String common_code = "pp:sequence:common_code";


    /**
     * 需要连接人员empId
     * 人员特殊权限 pp:special_auth:empId
     */
    public static String special_auth = "pp:special_auth:";

    /**
     * 连接userId
     */
    public static String login_info = "pp:login:";

    /**
     * 连接empId
     */
    public static String emp_info = "pp:emp:";

    /**
     * 连接用户empId
     */
    public static String role_info = "pp:role:";

}
