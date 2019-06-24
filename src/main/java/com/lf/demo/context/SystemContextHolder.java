package com.lf.demo.context;

/**
 * PACKAGE      :  com.jiahui.eappt.context
 * CREATE DATE  :  2018/4/2
 * AUTHOR       :  linfei
 * 文件描述      :  系统上下文处理 & 支持
 */
public class SystemContextHolder {

    /**
     * 语言线程变量
     */
    private static ThreadLocal<String> languageContextThread = new ThreadLocal();
    /**
     * 用户线程变量
     */
    private static ThreadLocal<UserSystemContext> userSystemContextThread = new ThreadLocal();
    /**
     * 管理员线程变量
     */
    private static ThreadLocal<AdminSystemContext> adminSystemContextThread = new ThreadLocal();

    /**
     * 设置语言线程变量
     */
    public static void put(String language) {
        languageContextThread.set(language);
    }

    /**
     * 设置用户线程变量
     */
    public static void put(UserSystemContext userContext) {
        userSystemContextThread.set(userContext);
    }

    /**
     * 设置管理员线程变量
     */
    public static void put(AdminSystemContext adminContext) {
        adminSystemContextThread.set(adminContext);
    }

    /**
     * 语言线程变量
     */
    public static String getLanguageContext() {
        return languageContextThread.get();
    }

    /**
     * 用户线程变量
     */
    public static UserSystemContext getUserContext() {
        return userSystemContextThread.get();
    }

    /**
     * 管理员线程变量
     */
    public static AdminSystemContext getAdminContext() {
        return adminSystemContextThread.get();
    }

    /**
     * 清空语言线程变量
     */
    public static void removeLanguageContext() {
        userSystemContextThread.remove();
    }

    /**
     * 清空用户线程变量
     */
    public static void removeUserContext() {
        userSystemContextThread.remove();
    }

    /**
     * 清空管理员线程变量
     */
    public static void removeAdminContext() {
        adminSystemContextThread.remove();
    }


    /**
     * remove ThreadLocal
     */
    public static void remove() {
        try {
            removeLanguageContext();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            removeUserContext();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            removeAdminContext();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}