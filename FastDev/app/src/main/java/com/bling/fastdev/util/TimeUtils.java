package com.bling.fastdev.util;

/**
 * Created by Administrator on 2015/12/7 0007.
 *
 */

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * TimeUtils
 */
public class TimeUtils {

    private static Calendar calendar = Calendar.getInstance();

    public static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("MM-dd HH:mm");
    public static final SimpleDateFormat DATE_FORMAT_DATE = new SimpleDateFormat("yyyy-MM-dd");

    private TimeUtils() {
        throw new AssertionError();
    }

    /**
     * long time to string
     *
     * @param timeInMillis
     * @param dateFormat
     * @return
     */
    public static String getTime(long timeInMillis, SimpleDateFormat dateFormat) {
        return dateFormat.format(new Date(timeInMillis));
    }

    /**
     * 时间戳转换为日期
     *
     * @param seconds
     * @param format
     * @return
     */
    public static String timeStamp2Date(String seconds, String format) {
        if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
            return "";
        }
        if (format == null || format.isEmpty()) {
            format = "MM-dd HH:mm";
        }

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));      //设置时区为东八区，如果不设置则依据手机的默认时区
        return sdf.format(new Date(Long.valueOf(seconds + "000")));
    }

    /**
     * 获取n天前的日期
     * @return 日期
     */
    public static String getStateTime(int n) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -n);
        Date monday = c.getTime();
        return  DATE_FORMAT_DATE.format(monday);
    }

    /**
     * long time to string, format is {@link #DEFAULT_DATE_FORMAT}
     *
     * @param timeInMillis
     * @return
     */
    public static String getTime(long timeInMillis) {
        return getTime(timeInMillis, DEFAULT_DATE_FORMAT);
    }

    /**
     * get current time in milliseconds
     *
     * @return
     */
    public static long getCurrentTimeInLong() {
        return System.currentTimeMillis();
    }

    /**
     * get current time in milliseconds, format is {@link #DEFAULT_DATE_FORMAT}
     *
     * @return
     */
    public static String getCurrentTimeInString() {
        return getTime(getCurrentTimeInLong());
    }

    /**
     * get current time in milliseconds
     *
     * @return
     */
    public static String getCurrentTimeInString(SimpleDateFormat dateFormat) {
        return getTime(getCurrentTimeInLong(), dateFormat);
    }

    //把日期转为字符串
    public static String ConverToString(Date date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        return df.format(date);
    }

    //把字符串转为日期
    public static Date ConverToDate(String strDate) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.parse(strDate);
    }

    /**
     * 获取当前月
     *
     */
    public static int getCurMonth() {
        Calendar calendar= Calendar.getInstance();
        return calendar.get(calendar.MONTH) + 1;
    }

    public static int getCurDay() {
        Calendar calendar= Calendar.getInstance();
        return calendar.get(calendar.DAY_OF_MONTH) ;
    }
    /**
     * 格式化月份
     *
     */
    public static String getMonth() {
        int month = calendar.get(Calendar.MONTH);
        Dlog.e("******** month = " + month);
        if (month < 9) {
            return "0" + (month + 1);
        }
        return "" + (month + 1);
    }

    /**
     * 获取当前年
     *
     */
    public static int getCurYear() {
        Calendar calendar= Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取当前日期是星期几<br>
     *
     * @param dt
     * @return 当前日期是星期几      0星期日 1星期一 2星期2
     */
    public static int  getWeekOfDate(Date dt) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return w;
    }


    /**
     * 掉此方法输入所要转换的时间输入例如（"2014年06月14日16时09分00秒"）返回时间戳
     *
     * @param time
     * @param format 格式化后的日期格式
     * @return
     */
    public static String FormatData(String time, String format) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date;
        String times = null;
        try {
            date = sdr.parse(time);
            long l = date.getTime();
            String stf = String.valueOf(l);
            times = stf.substring(0, 10);
            times=timeStamp2Date(times,format);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return times;
    }
    /**
     * 掉此方法输入所要转换的时间输入例如（"2014年06月14日16时09分00秒"）返回时间戳
     *
     * @param time

     * @return
     */
    public static long getSecondFromData(String time) {

        return   getSecondFromData(time,"yyyy-MM-dd HH:mm:ss");

    }
    /**
     * 掉此方法输入所要转换的时间输入例如（"2014年06月14日16时09分00秒"）返回时间戳
     *
     * @param time

     * @return
     */
    public static long getSecondFromData(String time,String format) {
        SimpleDateFormat sdr = new SimpleDateFormat(format,
                Locale.CHINA);
        Date date;
        String times = null;
        long l=0;
        try {
            date = sdr.parse(time);
            l = date.getTime();
            l=l/1000;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return l;
    }
}
