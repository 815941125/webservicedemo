package com.lf.demo.constants;

/**
 * @author linfei
 * @version 1.0
 * @date 2018/10/15
 * @desc 语言枚举
 * @see
 * @since 1.0
 */
public enum LangEnum {

    LANG_EN_US(1, "en_US", "English"),
    LANG_ZH_CN(3, "zh_CN", "简体中文");

    /**
     * 语言唯一值
     * 与HIS语言值一致。
     */
    private Integer lang;
    /**
     * 客户端语言
     */
    private String language;
    /**
     * 描述
     */
    private String desc;

    private LangEnum(Integer lang, String language, String desc) {
        this.lang = lang;
        this.language = language;
        this.desc = desc;
    }

    public static Boolean exist(Integer lang) {
        for (LangEnum langEnum : LangEnum.values()) {
            if (langEnum.getLang().equals(lang)) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    public static Boolean exist(String language) {
        for (LangEnum langEnum : LangEnum.values()) {
            if (langEnum.getLanguage().equals(language)) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    public static LangEnum getLangEnum(Integer lang) {
        for (LangEnum langEnum : LangEnum.values()) {
            if (langEnum.getLang().equals(lang)) {
                return langEnum;
            }
        }
        return null;
    }

    public static LangEnum getLangEnum(String language) {
        for (LangEnum langEnum : LangEnum.values()) {
            if (langEnum.getLanguage().equals(language)) {
                return langEnum;
            }
        }
        return null;
    }

    public Integer getLang() {
        return lang;
    }

    public String getLanguage() {
        return language;
    }

    public String getDesc() {
        return desc;
    }

}