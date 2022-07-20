package com.bs.regsystemapi.utils;

import java.time.LocalDate;

/**
 * 生成no
 */
public class NoGeneratorUtil {
    public static String getNo(){
        long uid = System.nanoTime();
        return getYearAndMonth() + uid;
    }
    public static String getNo(String prefix){
        long uid = System.nanoTime();
        return prefix+getYearAndMonth() + uid;
    }
    private static String getYearAndMonth() {
        LocalDate localDate = LocalDate.now();
        if (localDate.getMonthValue() < 10) {
            return localDate.getYear() + "0" + localDate.getMonthValue();
        }
        return localDate.getYear() + "" + localDate.getMonthValue();
    }

}
