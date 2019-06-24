package com.lf.demo.constants;

/**
 * PACKAGE      :  com.jiahui.eappt.constants
 * CREATE DATE  :  2018/4/2
 * AUTHOR       :  linfei
 * 文件描述      :  基础常量参数
 */
public class BaseConstants {

    /**
     * 项目环境
     */
    public final static String PROJECT_PROFILE_DEV = "dev";
    public final static String PROJECT_PROFILE_UAT = "uat";
    public final static String PROJECT_PROFILE_PROD = "prod";

    public final static String DEFAULT_PAGE_SIZE = "20";
    public final static Long SMS_DAY_MAX = 10L;

    /**
     * 企业用户
     */
    public final static String TOKEN_USER = "user";
    /**
     * 管理员
     */
    public final static String TOKEN_ADMIN = "admin";

    /**
     * token HTTP  head key
     */
    public final static String HEAD_KEY_TOKEN = "_st";
    /**
     * HTTP  head key lang
     */
    public final static String HTTP_HEAD_KEY_LANG = "language";


}