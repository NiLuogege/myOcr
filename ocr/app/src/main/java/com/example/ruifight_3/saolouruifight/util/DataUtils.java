package com.example.ruifight_3.saolouruifight.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by RuiFight-3 on 2018/7/10.
 */

public class DataUtils {

    private static String mYear; // 当前年
    private static String mMonth; // 月
    private static String mDay;

    /**
     * 获取年
     */
    public static String getDateYer() {
        String ti = null;
        //获取当前时间
        Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
        int year = c.get(Calendar.YEAR);
        ti = year + "";
        if (ti == null) {
            return "none";
        } else {
            return ti;
        }
    }

    /**
     * 获取当前年月日
     *
     * @return
     */
    public static String StringData() {

        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        mYear = String.valueOf(c.get(Calendar.YEAR));// 获取当前年份
        mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
        mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));// 获取当前月份的日期号码
        if (Integer.parseInt(mDay) > MaxDayFromDay_OF_MONTH(Integer.parseInt(mYear), (Integer.parseInt(mMonth)))) {
            mDay = String.valueOf(MaxDayFromDay_OF_MONTH(Integer.parseInt(mYear), (Integer.parseInt(mMonth))));
        }
        return mYear + "-" + (mMonth.length() == 1 ? "0" + mMonth : mMonth) + "-" + (mDay.length() == 1 ? "0" + mDay : mDay);
    }


    /**
     * 根据当前日期获得是星期几
     *
     * @return
     */
    public static String getWeek(String time) {
        String Week = "";


        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {


            c.setTime(format.parse(time));


        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            Week += "周天";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 2) {
            Week += "周一";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 3) {
            Week += "周二";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 4) {
            Week += "周三";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 5) {
            Week += "周四";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 6) {
            Week += "周五";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 7) {
            Week += "周六";
        }
        return Week;
    }


    /**
     * 得到当年当月的最大日期
     **/
    public static int MaxDayFromDay_OF_MONTH(int year, int month) {
        Calendar time = Calendar.getInstance();
        time.clear();
        time.set(Calendar.YEAR, year);
        time.set(Calendar.MONTH, month - 1);//注意,Calendar对象默认一月为0
        int day = time.getActualMaximum(Calendar.DAY_OF_MONTH);//本月份的天数
        return day;
    }


//    /**
//     * 计算两个日期相差多少天
//     *
//     * @param smdate
//     * @param bdate
//     * @return
//     */
//    public static long getGapCoun(String smdate, String bdate) throws ParseException {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(sdf.parse(smdate));
//        long time1 = cal.getTimeInMillis();
//        cal.setTime(sdf.parse(bdate));
//        long time2 = cal.getTimeInMillis();
//        long between_days = (time2 - time1) / (1000 * 3600 * 24);
//        return Integer.parseInt(String.valueOf(between_days));
//    }
}
