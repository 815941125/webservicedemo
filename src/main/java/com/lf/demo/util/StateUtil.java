package com.lf.demo.util;

import java.util.HashMap;
import java.util.Map;

/**
 * PACKAGE      :  com.jiahui.eappt.util
 * CREATE DATE  :  2018/4/2
 * AUTHOR       :  xlinfei
 * 文件描述      :  状态
 */
public class StateUtil {

    public static Map<Integer, String> message = new HashMap<Integer, String>();

    public static final int FAILURE = -1;
    public static final int SUCCESS = 0;
    public static final int SYSTEM_EXCEPTION = 1;
    public static final int USER_NOT_LOGIN = 2;//用户未登录
    public static final int ADMIN_NOT_LOGIN = 3;//管理员未登录
    public static final int PARAMS_ERROR = 4;//参数不正确
    public static final int REQUEST_NOT_FOUND = 5;//请求未找到
    public static final int TOKEN_OVER_TIME = 6;//Token超时
    public static final int ACCESS_LIMIT_ERROR = 7;//访问限制

    public static final int PARAMS_NOT_NULL = 100;//参数不能为空

    //User |10000-10999|
    public static final int USER_NOT_FOUND = 10000;//用户不存在
    public static final int FIRL_NOT_LOGIN = 10001;//文件不存在
    public static final int FIRL_NAME_NOT_LOGIN = 10002;//文件名错误
    public static final int USER_POST_ID_ERROR = 10003;//用户证件ID错误
    public static final int SMS_SEND_COUNT_TOO_MUCH = 10004;//短信发送次数过多
    public static final int SMS_CODE_OVER_TIME = 10005;//短信验证码超时
    public static final int SMS_CODE_ERROR = 10006;//短信验证码错误
    public static final int USER_POST_ID_EXIST = 10007;//身份证号码已经存在
    //Admin |11000-11999|
    public static final int ADMIN_NOT_FOUND = 11000;//管理员不存在
    public static final int ADMIN_PASSWROD_ERROR = 11001;//管理员密码错误

    //Scheduling |12000-12999|
    public static final int SCHEDULING_NOT_FOUND = 12000;//排班不存在
    public static final int SCHEDULING_APPT_YES = 12001;//排班已被约满
    public static final int SCHEDULING_TIME_OVERDUE = 12003;//不能取消过期的排班

    //AppOrder |13000-13999|
    public static final int APPT_ORDER_NOT_FOUND = 13000;//预约单不存在
    public static final int APPT_ORDER_CANCEL_YES = 13001;//预约单已取消
    public static final int APPT_ORDER_EFFECTIVE_NOT_EXIST = 13002;//不存在有效预约单
    public static final int APPT_ORDER_EFFECTIVE_EXIST = 13003;//已存在有效预约单
    public static final int APPT_ORDER_EXPORT_ERR = 13004;//导出失败
    public static final int APPT_ORDER_HOURS_ERR = 13005;//不能取消48小时之内的预约
    public static final int APPT_ORDER_FAILURE = 13006;//预约失败
    public static final int USER_LOGGIN_FAILURE = 13007;//用户登录失败
    public static final int USER_LOGGIN_FAMILYNAME = 13008;//请输入你的姓氏
    public static final int USER_LOGGIN_GIVENAME = 13009;//请输入你的名称
    public static final int USER_LOGGIN_BIRTHDAY = 13010;//请输入你的生日
    public static final int ILLEGAL_MOBILE_PHONE_NUMBER = 13011;//非法手机号



    static {
        message.put(FAILURE, "failure");
        message.put(SUCCESS, "success");
        message.put(SYSTEM_EXCEPTION, "系统异常");
        message.put(USER_NOT_LOGIN, "用户未登录");
        message.put(ADMIN_NOT_LOGIN, "管理员未登录");
        message.put(PARAMS_ERROR, "参数不正确");
        message.put(REQUEST_NOT_FOUND, "请求未找到");
        message.put(TOKEN_OVER_TIME, "Token超时");
        message.put(ACCESS_LIMIT_ERROR, "访问限制");

        message.put(PARAMS_NOT_NULL, "参数不能为空");

        message.put(USER_NOT_FOUND, "用户不存在");
        message.put(FIRL_NOT_LOGIN, "文件不存在");
        message.put(FIRL_NAME_NOT_LOGIN, "文件名错误");
        message.put(USER_POST_ID_ERROR, "用户证件ID错误");
        message.put(SMS_SEND_COUNT_TOO_MUCH, "短信发送次数过多");
        message.put(SMS_CODE_OVER_TIME, "短信验证码超时");
        message.put(SMS_CODE_ERROR, "短信验证码错误");
        message.put(USER_POST_ID_EXIST, "身份证号码已经存在");

        message.put(ADMIN_NOT_FOUND, "管理员不存在");
        message.put(ADMIN_PASSWROD_ERROR, "管理员密码错误");

        message.put(SCHEDULING_NOT_FOUND, "排班不存在");
        message.put(SCHEDULING_APPT_YES, "排班已被约满");
        message.put(SCHEDULING_TIME_OVERDUE, "不能取消过期的排班");

        message.put(APPT_ORDER_NOT_FOUND, "预约单不存在");
        message.put(APPT_ORDER_CANCEL_YES, "预约单已取消");
        message.put(APPT_ORDER_EFFECTIVE_NOT_EXIST, "不存在有效预约单");
        message.put(APPT_ORDER_EFFECTIVE_EXIST, "已存在有效预约单");
        message.put(APPT_ORDER_EXPORT_ERR, "导出失败");
        message.put(APPT_ORDER_HOURS_ERR, "不能取消48小时之内的预约");
        message.put(APPT_ORDER_FAILURE, "预约失败");
        message.put(USER_LOGGIN_FAILURE,"用户登录失败");

        message.put(USER_LOGGIN_FAMILYNAME, "请输入你的姓氏");
        message.put(USER_LOGGIN_GIVENAME, "请输入你的名称");
        message.put(USER_LOGGIN_BIRTHDAY,"请输入你的生日");
        message.put(ILLEGAL_MOBILE_PHONE_NUMBER,"非法手机号");
    }

}