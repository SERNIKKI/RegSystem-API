package com.bs.regsystemapi.utils;

public class ReserveSurplusUtils {
    public static String getDataColum(String week, String time) {
        String colum = "";
        if(week.equals("1") || week.equals("周一") || week.equals("一") || week.equals("星期一") || week.equalsIgnoreCase("Monday")) {
            colum = "mon";
        } else if(week.equals("2") || week.equals("周二") || week.equals("二") || week.equals("星期二") || week.equalsIgnoreCase("Tuesday")) {
            colum = "tue";
        } else if(week.equals("3") || week.equals("周三") || week.equals("三") || week.equals("星期三") || week.equalsIgnoreCase("Wednesday")) {
            colum = "wed";
        } else if(week.equals("4") || week.equals("周四") || week.equals("四") || week.equals("星期四") || week.equalsIgnoreCase("Thursday")) {
            colum = "thur";
        } else if(week.equals("5") || week.equals("周五") || week.equals("五") || week.equals("星期五") || week.equalsIgnoreCase("Friday")) {
            colum = "fri";
        } else if(week.equals("6") || week.equals("周六") || week.equals("六") || week.equals("星期六") || week.equalsIgnoreCase("Saturday")) {
            colum = "sat";
        } else if(week.equals("7") || week.equals("周日") || week.equals("七") || week.equals("星期日") || week.equalsIgnoreCase("Sunday")) {
            colum = "sun";
        }
        if(time.equals("上午") || time.equalsIgnoreCase("morning")) {
            colum += "_morning";
        } else if(time.equals("下午") || time.equalsIgnoreCase("afternoon")) {
            colum += "_afternoon";
        } else if(time.equals("晚上") || time.equalsIgnoreCase("night")) {
            colum += "_night";
        }
        return colum;
    }
    public static String getDataColum(String week) {
        String colum = "";
        if(week.equals("1") || week.equals("周一") || week.equals("一") || week.equals("星期一") || week.equalsIgnoreCase("Monday")) {
            colum = "mon";
        } else if(week.equals("2") || week.equals("周二") || week.equals("二") || week.equals("星期二") || week.equalsIgnoreCase("Tuesday")) {
            colum = "tue";
        } else if(week.equals("3") || week.equals("周三") || week.equals("三") || week.equals("星期三") || week.equalsIgnoreCase("Wednesday")) {
            colum = "wed";
        } else if(week.equals("4") || week.equals("周四") || week.equals("四") || week.equals("星期四") || week.equalsIgnoreCase("Thursday")) {
            colum = "thur";
        } else if(week.equals("5") || week.equals("周五") || week.equals("五") || week.equals("星期五") || week.equalsIgnoreCase("Friday")) {
            colum = "fri";
        } else if(week.equals("6") || week.equals("周六") || week.equals("六") || week.equals("星期六") || week.equalsIgnoreCase("Saturday")) {
            colum = "sat";
        } else if(week.equals("7") || week.equals("周日") || week.equals("七") || week.equals("星期日") || week.equalsIgnoreCase("Sunday")) {
            colum = "sun";
        }
        return colum;
    }
}
