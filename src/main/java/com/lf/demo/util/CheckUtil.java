package com.lf.demo.util;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * PACKAGE      :  com.jiahui.eappt.util
 * CREATE DATE  :  2018/4/2
 * AUTHOR       :  linfei
 * 文件描述      :  检查工具类
 */
public class CheckUtil {

    /**
     * 判断字符串长度(字符串全为空格的为false)
     */
    public static Boolean checkStrLength(String str, Integer min, Integer max) {
        return (!isEmpty(str) && str.length() >= min && str.length() <= max);
    }

    /**
     * 判断字符串是不是数字
     */
    public static Boolean checkNumber(String str) {
        return !isEmpty(str) && str.matches("^\\d+$");
    }

    /**
     * 判断数组是否全部为空
     */
    public static Boolean isAllEmpty(Object[] o) {
        if (o == null) {
            return true;
        }

        for (Object tmp : o) {
            if (!isEmpty(tmp)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断字符串是不是null或无字符（trim后）
     */
    public static Boolean isEmpty(String o) {
        return (o == null || o.equals("") || o.trim().length() == 0);
    }

    /**
     * 判断整形是否为null或0
     */
    public static Boolean isEmpty(Integer value) {
        return (value == null || value == 0);
    }

    /**
     * 判断整形是否为null或0
     */
    public static Boolean isEmpty(Long value) {
        return (value == null || value == 0);
    }

    /**
     * 判断List是否为空
     */
    public static Boolean isEmpty(List<?> list) {
        return (list == null || list.size() == 0);
    }

    /**
     * 判断Map是否为空
     */
    public static Boolean isEmpty(Map<?, ?> map) {
        return (map == null || map.size() == 0);
    }

    /**
     * 判断Set是否为空
     */
    public static Boolean isEmpty(Set<?> set) {
        return (set == null || set.size() == 0);
    }

    /**
     * 判断数组是否为空
     */
    public static Boolean isEmpty(Object[] o) {
        return (o == null || o.length == 0);
    }

    /**
     * 判断Object是否为空
     */
    public static Boolean isEmpty(Object o) {
        if (o == null) {
            return true;
        }
        boolean isEmpty = false;
        if (o instanceof String) {
            isEmpty = isEmpty((String) o);
        } else if (o instanceof Integer) {
            isEmpty = isEmpty((Integer) o);
        } else if (o instanceof List<?>) {
            isEmpty = isEmpty((Integer) o);
        } else if (o instanceof Map<?, ?>) {
            isEmpty = isEmpty((Integer) o);
        } else if (o instanceof Set<?>) {
            isEmpty = isEmpty((Integer) o);
        }
        return isEmpty;
    }

}