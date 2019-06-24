package com.lf.demo.util;

import org.joda.time.DateTime;
import org.joda.time.Days;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * PACKAGE      :  com.jiahui.eappt.util
 * CREATE DATE  :  2018/4/3
 * AUTHOR       :  linfei
 * 文件描述      :
 */
public class DateUtil {

    public static long SECOND_LONG = 1000l;
    public static long MINUTE_LONG = 60 * SECOND_LONG;
    public static long HOUR_LONG = 60 * MINUTE_LONG;
    public static long DAY_LONG = 24 * HOUR_LONG;
    public static long MONTH_LONG = 30 * DAY_LONG;
    public static long YEAR_LONG = 365 * DAY_LONG;

    private static final long ONE_MINUTE = 60;
    private static final long ONE_HOUR = 3600;
    private static final long ONE_DAY = 86400;

    private static String[] WEEKS_CN = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
    private static String[] WEEKS_US = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
    private static String[] WEEKS_TW = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};

    private static String[] MONTH_CH = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
    private static String[] MONTH_EN = {"January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"};

    private static String[] DAY_CN = {"今天", "明天", "后天"};
    private static String[] DAY_TW = {"今天", "明天", "後天"};

    public static final String DATE_FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND = "yyyy/MM/dd HH:mm:ss";
    public static final String DATE_FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND2 = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND3 = "yyyy年MM月dd日 HH:mm";
    public static final String DATE_FORMAT_MONTH_DAY = "MM月dd日";
    public static final String DATE_FORMAT_MONTH_DAY2 = "MM:dd";
    public static final String DATE_FORMAT_HOUR_MINUTE = "HH:mm";
    public static final String DATE_FORMAT_YEAR_MONTH_DAY = "yyyyMMdd";
    public static final String DATE_FORMAT_YEAR_MONTH_DAY2 = "yyyy-MM-dd";
    public static final String DATE_FORMAT_YEAR_MONTH_DAY3 = "yyyy/MM/dd";
    public static final String DATE_FORMAT_YEAR_MONTH_DAY4 = "yyyy.MM.dd";
    public static final String DATE_FORMAT_YEAR_MONTH_DAY_STRING = "yy年MM月dd日";
    public static final String DATE_FORMAT_YEAR_MONTH_DAY_STRING2 = "yyyy年MM月dd日";
    public static final String DATE_FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE = "yyyy-MM-dd HH:mm";
    public static final String DATE_FORMAT_YEAR_MONTH_DAY_HOUR_SECOND = "yyyy-MM-dd HH:ss";

    public static final Integer DIFF_DAY_YEAR = 365;

    /**
     * 时间转换成一小时前/一天前/年-月-日
     */
    public static String timeToStr(Date date) {
        StringBuffer timeStr = new StringBuffer();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        long time = date.getTime() / 1000;
        long now = new Date().getTime() / 1000;
        long ago = now - time;

        if (ago <= ONE_MINUTE) {
            timeStr.append("刚刚");
        } else if (ago <= ONE_HOUR) {
            timeStr.append(ago / ONE_MINUTE).append("分钟前");
        } else if (ago <= ONE_DAY) {
            timeStr.append(ago / ONE_HOUR).append("小时前");
        } else {
            timeStr.append(DateUtil.DateToString(date));
        }
        return timeStr.toString();
    }


    /**
     * 得到某一天的开始时间，精确到毫秒
     *
     * @param date 日期
     * @return 某一天的0时0分0秒0毫秒的那个Date
     */
    public static Date beginOfDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        date = c.getTime();
        return date;
    }

    /**
     * 得到某一天的最后时间，精确到毫秒
     *
     * @param date 日期
     * @return 某一天的下一天的0时0分0秒0毫秒的那个Date减去1毫秒所得到的Date
     */
    public static Date endOfDay(Date date) {
        date = beginOfDay(date);
        return endOfDayByBeginOfDate(date);
    }

    /**
     * 根据某一天的开始时间，得到某一天的最后时间，精确到毫秒
     *
     * @param date 日期
     * @return 某一天的下一天的0时0分0秒0毫秒的那个Date减去1毫秒所得到的Date
     */
    public static Date endOfDayByBeginOfDate(Date date) {
        date = nextDay(date);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MILLISECOND, -1);
        date = c.getTime();
        return date;
    }

    /**
     * 得到指定日期的下一天
     *
     * @param date 日期
     * @return 传入日期的下一天
     */
    public static Date nextDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 1);
        date = c.getTime();
        return date;
    }


    /**
     * 获取指定时间
     *
     * @param date    指定日期
     * @param diffDay 天数
     * @return 自指定日期后的若干天的日期
     */
    public static Date getDay(Date date, Integer diffDay) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, diffDay);
        return cal.getTime();
    }


    /**
     * 得到本周周一
     *
     * @return Date
     */
    public static Date getMondayOfWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (dayOfWeek == 0) {
            dayOfWeek = 7;
        }
        c.add(Calendar.DATE, -dayOfWeek + 1);
        return c.getTime();
    }


    /**
     * 得到本周周日
     *
     * @return Date
     */
    public static Date getSundayOfWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (dayOfWeek == 0) {
            dayOfWeek = 7;
        }
        c.add(Calendar.DATE, -dayOfWeek + 7);
        return c.getTime();
    }

    /**
     * Date 转String
     *
     * @return Date
     */
    public static String DateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_YEAR_MONTH_DAY2);
        String str = sdf.format(date);
        return str;
    }

    /**
     * Date 转String
     *
     * @return Date
     */
    public static String DateToString2(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_YEAR_MONTH_DAY);
        String str = sdf.format(date);
        return str;
    }

    /**
     * 输出字符串类型的格式化日期
     *
     * @param dt      Date
     * @param pattern 格式(DATE_FORMAT_YEAR_MONTH_DAY2/DATE_FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND2)
     * @return sDate
     */
    public static String getFormatDate(Date dt, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(dt);
    }

    /**
     * 时间格式字符串类型的格式化日期
     *
     * @param dt      Date
     * @param pattern 格式(DATE_FORMAT_YEAR_MONTH_DAY2/DATE_FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND2)
     * @return sDate
     */
    public static String getFormatDate(String dt, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        Date date = getFormatDate(dt);
        return formatter.format(date);
    }

    /**
     * 字符串型日期/时间转DATE
     */
    public static Date getFormatDate(String str) {
        if (str == null || "".equals(str)) {
            return null;
        }
        if (str.length() <= 10) {
            return getDateByString(str, DATE_FORMAT_YEAR_MONTH_DAY2);
        } else {
            return getDateByString(str, DATE_FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND2);
        }
    }

    /**
     * 字符串型日期/时间转DATE
     *
     * @param str     日期时间的字符串
     * @param pattern 格式(DATE_FORMAT_YEAR_MONTH_DAY2/DATE_FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND2)
     */
    public static Date getDateByString(String str, String pattern) {
        Date date = null;
        try {
            SimpleDateFormat df3 = new SimpleDateFormat();
            df3.applyPattern(pattern);
            date = df3.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    /**
     * 获取时间的月日
     */
    public static String getDateMMDD(Date date) {
        StringBuffer time = new StringBuffer();
        String mmdd = getFormatDate(date, "MM-dd");
        time.append(mmdd.replaceAll("-", "月")).append("日");
        return time.toString();
    }

    /**
     * 比较时间差
     *
     * @param type SECOND_LONG,MINUTE_LONG,HOUR_LONG,DAY_LONG,MONTH_LONG,YEAR_LONG
     */
    public static Integer diffDate(Date date1, Date date2, long type) {
        if (date1 == null || date2 == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.clear(Calendar.MINUTE);
        calendar.clear(Calendar.SECOND);
        calendar.clear(Calendar.MILLISECOND);
        return new Long((date2.getTime() - calendar.getTimeInMillis()) / type).intValue();
    }

    /**
     * 获取当前时间前天时间
     */
    public static String getDaysDate(Integer days) {
        GregorianCalendar worldTour = new GregorianCalendar();
        worldTour.add(GregorianCalendar.DATE, -days); // 当前日期加100天
        Date d = worldTour.getTime();
        DateFormat df = DateFormat.getDateInstance();
        String date = df.format(d);
        return date;
    }

    /**
     * 某一个月第一天和最后一天
     */
    public static Map<String, String> getFirstdayLastdayMonth(Date date) {
        SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT_YEAR_MONTH_DAY2);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 0);//-1:上个月， 0：当前月
        Date theDate = calendar.getTime();
        //第一天
        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        gcLast.setTime(theDate);
        gcLast.set(Calendar.DAY_OF_MONTH, 1);
        String day_first = df.format(gcLast.getTime());
        StringBuffer str = new StringBuffer().append(day_first).append(" 00:00:00");
        day_first = str.toString();
        //后一天
        calendar.add(Calendar.MONTH, 1);    //加一个月
        calendar.set(Calendar.DATE, 1);        //设置为该月第一天
        calendar.add(Calendar.DATE, -1);    //再减一天即为上个月最后一天
        String day_last = df.format(calendar.getTime());
        StringBuffer endStr = new StringBuffer().append(day_last).append(" 23:59:59");
        day_last = endStr.toString();

        Map<String, String> map = new HashMap<String, String>();
        map.put("first", day_first);
        map.put("last", day_last);
        return map;
    }

    /**
     * 当月第一天
     */
    public static String getFirstDay() {
        SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT_YEAR_MONTH_DAY2);
        Calendar calendar = Calendar.getInstance();
        Date theDate = calendar.getTime();
        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        gcLast.setTime(theDate);

        gcLast.set(Calendar.DAY_OF_MONTH, 1);
        String day_first = df.format(gcLast.getTime());
        StringBuffer str = new StringBuffer().append(day_first).append(" 00:00:00");
        return str.toString();
    }

    /**
     * 当月最后一天
     */
    public static String getLastDay() {
        SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT_YEAR_MONTH_DAY2);
        Calendar calendar = Calendar.getInstance();
        Date theDate = calendar.getTime();
        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        gcLast.setTime(theDate);

        calendar.add(Calendar.MONTH, 1);    //加一个月
        calendar.set(Calendar.DATE, 1);        //设置为该月第一天
        calendar.add(Calendar.DATE, -1);    //再减一天即为上个月最后一天
        String day_last = df.format(calendar.getTime());
        StringBuffer str = new StringBuffer().append(day_last).append(" 23:59:59");
        return str.toString();
    }

    /**
     * 获取明年的年份
     */
    public static int getNextYear() {
        Calendar calendar = Calendar.getInstance();
        Date date = new Date(System.currentTimeMillis());
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, 1);
        date = calendar.getTime();
        String yyyy = getFormatDate(date, "yyyy");
        return Integer.valueOf(yyyy);
    }

    /**
     * 获取当前系统的UNIX时间戳
     */
    public static String getUnixTime() throws ParseException {
        Timestamp appointTime = Timestamp.valueOf(
                new SimpleDateFormat(DATE_FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND2).format(new Date()));
        SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND2);
        Date date = df.parse(String.valueOf(appointTime));
        long s = date.getTime();
        return String.valueOf(s).substring(0, 10);
    }

    /**
     * 格式化月日
     * flag true-MM:dd/false-MM月dd日
     */
    public static String dateFormatByMMdd(Date date, boolean flag) {
        String pattern = null;
        if (flag) {
            pattern = DATE_FORMAT_MONTH_DAY2;
        } else {
            pattern = DATE_FORMAT_MONTH_DAY;
        }
        SimpleDateFormat dateFm = new SimpleDateFormat(pattern);
        return dateFm.format(date);
    }

    public static String getWeek(Date dt) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return WEEKS_CN[w];
    }

    public static String getWeekEn(Date dt) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return WEEKS_US[w];
    }

    /**
     * 获取中文月份
     * @return
     */
    public static String getMonthCh(Date dt) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int m = cal.get(Calendar.MONTH) + 1;
        return String.valueOf(m);
    }

    /**
     * 获取英文月份
     * @return
     */
    public static String getMonthEn(Date dt) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int m = cal.get(Calendar.MONTH) + 1;
        return MONTH_EN[m];
    }

    public static String getDayEn(Date dt) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int m = cal.get(Calendar.DAY_OF_MONTH);
        return m + "th";
    }

    public static String getDayCh(Date dt) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int m = cal.get(Calendar.DAY_OF_MONTH);
        return String.valueOf(m);
    }

    /**
     * 计算年纪
     *
     * @param birthDay
     * @return
     * @throws Exception
     */
    public static int getAge(Date birthDay) {
        Calendar cal = Calendar.getInstance();
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthDay);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        int age = yearNow - yearBirth;
        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) {
                    age--;
                }
            } else {
                age--;
            }
        }
        return age;
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param fDate        时间A
     * @param oDate        时间B
     * @param includeToday 是否包括今天
     * @return 时间A与时间B的差值(前者 - 后者)。当有任何一个时间为空时，返回null
     */
    public static Integer betweenDay(Date fDate, Date oDate, boolean includeToday) {
        if (fDate == null || oDate == null) {
            return null;
        }
        DateTime begin = new DateTime(fDate);
        DateTime end = new DateTime(oDate);
        int days = Days.daysBetween(end.toLocalDate(), begin.toLocalDate()).getDays();
        return includeToday ? days + 1 : days;
    }

}