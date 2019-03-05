package com.pp.base.constant;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 */
@Component
public class WebConst {


    public static String ENCRYPT_REQUEST_AES_KEY = "1234567890000000";
    public static String ENCRYPT_REQUEST_AES_IV = "1234567890000000";
    public static String ENCRYPT_SIGN_KEY = "ABCDEFGHILGKLMKDFD";


    public static String SUPER_COMPANY_ID = "PP20180000001";


    /**
     * 一些网站配置
     */
    public static Map<String, String> initConfig = new HashMap<>();

    /**
     * session的key
     */
    public static String LOGIN_SESSION_KEY = "login_user";
    public static String LOGIN_SESSION_KEY_MEMER = "login_member";

    public static final String USER_IN_COOKIE = "S_L_ID";




    /**
     * aes加密加盐
     */
    public static String AES_SALT = "0123456789abcdef";

    /**
     * 最大获取条数
     */
    public static final int MAX_POSTS = 9999;

    /**
     * 最大页码
     */
    public static final int MAX_PAGE = 100;

    /**
     * 点击次数超过多少更新到数据库
     */
    public static final int HIT_EXCEED = 10;

    /**
     * 上传文件最大1M
     */
    public static Integer MAX_FILE_SIZE = 1048576;
}
