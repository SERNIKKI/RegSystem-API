package com.bs.regsystemapi.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.SimpleFormatter;

/**
 * @Author qpj
 * @Date: 2022/02/22/ 10:15
 * @Description 时间工具类
 */
public class TimeUtils {
    public static int calLastedTime(Date startDate) {
        long a = new Date().getTime();
        long b = startDate.getTime();
        int c = (int)((a - b) / 1000);
        return c;
    }

    /**
     * 获取当前月份
     * @return
     */
    public static int getMonth() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取当前年份
     * @return
     */
    public static int getYear() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.YEAR);
    }

    /**
     * 获取当前年份周数
     * @return
     */
    public static int getWeekOfYear() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 返回指定格式的日期
     */
    public static String formatDate(Date date, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }
}
