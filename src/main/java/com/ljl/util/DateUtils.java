package com.ljl.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author lvjunlong
 * @date 2019/8/21 上午9:29
 */
@SuppressWarnings("unused")
public class DateUtils {

    private static final String TIME_PATTON_DEFAULT = "yyyy-MM-dd HH:mm:ss";
    private static final String DATE_PATTON_DEFAULT = "yyyy-MM-dd";
    private static final String DATE_PATTON_DEFAULT2 = "yyyy.MM.dd";
    private static final String DATA_PATTON_YYYYMMDD = "yyyyMMdd";
    private static final String DATA_PATTON_YYYYMM = "yyyyMM";
    public static final String DATA_PATTON_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    private static final String TIME_PATTON_HHMMSS = "HH:mm:ss";
    private static final String TIME_PATTON_MMDDHHMMSS = "MM-dd HH:mm:ss";
    private static final String TIME_PATTON_MMDD = "MM月dd";
    private static final String TIME_PATTON_DEFAULT_T = "yyyy/MM/dd HH:mm:ss";

    private static final int ONE_SECOND = 1000;
    private static final int ONE_MINUTE = 60 * ONE_SECOND;
    private static final int ONE_HOUR = 60 * ONE_MINUTE;
    private static final long ONE_DAY = 24 * ONE_HOUR;

    private static final long  ONE_HOUR_LONG = 60 * ONE_MINUTE;


    /**
     * 获取当前系统时间
     *
     * @return  unix时间戳
     */
    public static int getCurrentSecond() {
        return Long.valueOf(System.currentTimeMillis() / 1000).intValue();
    }

    /**
     * 将linux时间转化为date时间
     *
     * @param unixSecond    unix时间戳
     * @return Date
     */
    public static Date formatUnixToDate(Integer unixSecond) {
        Date date = new Date();
        if (unixSecond != null) {
            date.setTime(unixSecond.longValue() * 1000L);
        }
        return date;
    }

    /**
     * 将unix时间戳转为yyy/MM/dd格式
     * @param unixSecond   unix时间戳
     * @return    String
     */
    public static String formatUnixToString(Integer unixSecond) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        if (unixSecond != null) {
            date.setTime(unixSecond.longValue() * 1000L);
        }
        return sdf.format(date);
    }
    /**
     * Date日期格式化
     *
     * @param date     日期
     * @param format   格式
     *            yyyyMMddHHmmss 或 yyyy-MM-dd HH:mm:ss
     * @return String
     */
    public static String formatDateToString(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     *
     * 【将字符串转为unix时间戳 按照指定格式】
     *
     * @author lvjunlong 2019年8月22日
     * @param userTime 时间字符串
     * @param format 指定格式
     * @return 时间戳
     */
    public static int getTimeFormat(String userTime, String format) {
        int reTime = 0;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date d;
        try {
            d = sdf.parse(userTime);
            long l = d.getTime();
            String str = String.valueOf(l);
            reTime = Integer.parseInt(str.substring(0, 10));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return reTime;
    }

    /**
     * 获取当前日期前一天时间
     * @return  String
     */
    public static String getStringBeforeDay(){
        //当前时间
        Date dNow = new Date();
        Date dBefore;
        //得到日历
        Calendar calendar = Calendar.getInstance();
        //把当前时间赋给日历
        calendar.setTime(dNow);
        //设置为前一天
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        //得到前一天的时间
        dBefore = calendar.getTime();
        //设置时间格式
        SimpleDateFormat sdf=new SimpleDateFormat(DATA_PATTON_YYYYMMDD);
        return sdf.format(dBefore);
    }

    /**
     * 获取当前日期后一天时间
     * @return String
     */
    public static String getStringNextDay(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATA_PATTON_YYYYMMDD);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date date = calendar.getTime();
        return sdf.format(date);
    }


    /**
     * 根据日期字符串返回日期类型数据
     *
     * @param strDate   日期字符串
     * @return          Date
     */
    public static Date strToDate(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_PATTON_DEFAULT);
        ParsePosition pos = new ParsePosition(0);
        return formatter.parse(strDate, pos);
    }
    /**
     * 根据日期字符串返回日期类型数据
     *
     * @param strDate   日期字符串
     * @param format    格式
     * @return          Date
     */
    public static Date strToDateCom(String strDate,String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        ParsePosition pos = new ParsePosition(0);
        return formatter.parse(strDate, pos);
    }

    /**
     * 根据时间和格式返回时间字符串
     *
     * @return DATE<br>
     */
    public static String formatDate(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    /**
     *显示当前时间的小时 int 2012101212
     *
     * @return DATE<br>
     */
    public static Integer getHourFromDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

        return Integer.valueOf(dateFormat.format(new Date()).substring(0, 10));
    }

    /**
     * 根据格式返回当前时间字符串
     *
     * @param format
     * @return
     */
    public static String getCurrentDateByFormat(String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(new Date());
    }

    /**
     * 返回当前时间Date对象
     *
     * @return
     */
    public static Date getCurrentDate() {
        Calendar cal = Calendar.getInstance();
        Date currDate = cal.getTime();

        return currDate;
    }

    /**
     * 根据格式'yyyy-MM-dd'返回当前时间字符串
     *
     * @return
     */
    public static String getCurrentDateStr() {
        Calendar cal = Calendar.getInstance();
        Date currDate = cal.getTime();

        return format(currDate);
    }

    /**
     * 根据格式返回当前时间字符串
     *
     * @param strFormat
     * @return
     */
    public static String getCurrentDateStr(String strFormat) {
        Calendar cal = Calendar.getInstance();
        Date currDate = cal.getTime();

        return format(currDate, strFormat);
    }

    /**
     * 将时间字符串转换为"yyyy-MM-dd"类型的Date返回
     *
     * @param dateValue 时间字符串
     * @return 时间
     */
    private static Date parseDate(String dateValue) {
        return parseDate(DATE_PATTON_DEFAULT, dateValue);
    }

    /**
     * 根据时间格式转换成date返回
     * @param format 时间格式
     * @param dateValue 时间
     * @return 时间
     */
    private static Date parseDateForFormat(String format, String dateValue){
        return parseDate(format, dateValue);
    }

    /**
     * 将时间字符串的Object对象转换为"yyyy-MM-dd"类型的Date返回
     *
     * @param object 时间字符串的对象
     * @return 时间
     */
    public static Date parseDate(Object object) {
        return parseDate(DATE_PATTON_DEFAULT, (String) object);
    }

    /**
     * 将时间字符串对象转换为"yyyy-MM-dd HH:mm:ss"类型的Date返回
     *
     * @param dateValue 时间字符串
     * @return date
     */
    public static Date parseDateTime(String dateValue) {
        return parseDate(TIME_PATTON_DEFAULT, dateValue);
    }

    /**
     * 将时间字符串对象转换为指定格式类型的Date返回 默认："yyyy-MM-dd HH:mm:ss"
     *
     * @param strFormat 时间格式
     * @param dateValue 时间字符串
     * @return date
     */
    private static Date parseDate(String strFormat, String dateValue) {
        if (dateValue == null) {
            return null;
        }

        if (strFormat == null) {
            strFormat = TIME_PATTON_DEFAULT;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat(strFormat);
        Date newDate;

        try {
            newDate = dateFormat.parse(dateValue);
        } catch (ParseException pe) {
            newDate = null;
        }

        return newDate;
    }

    /**
     * 将时间转换格式为"yyyy-MM-dd"的字符串
     *
     * @param aTs_Datetime date
     * @return 时间字符串
     */
    public static String format(Date aTs_Datetime) {
        return format(aTs_Datetime, DATE_PATTON_DEFAULT);
    }
    /**
     * 将时间转换格式为"yyyy.MM.dd"的字符串
     *
     * @param aTs_Datetime date
     * @return 时间字符串
     */
    public static String format2(Date aTs_Datetime) {
        return format(aTs_Datetime, DATE_PATTON_DEFAULT2);
    }
    /**
     * 将时间转换格式为"MM-dd HH:mm:ss"的字符串
     *
     * @param aTs_Datetime 原始时间
     * @return MM-dd HH:mm:ss
     */
    public static String format3(Date aTs_Datetime) {
        return format(aTs_Datetime, TIME_PATTON_MMDDHHMMSS);
    }
    /**
     * 将时间转换格式为"MM月dd"的字符串
     *
     * @param aTs_Datetime 原始时间
     * @return MM-DD
     */
    public static String format4(Date aTs_Datetime) {
        return format(aTs_Datetime, TIME_PATTON_MMDD);
    }
    /**
     * 将时间转换格式为"yyyy-MM-dd HH:mm:ss"的字符串
     *
     * @param aTs_Datetime 原始时间
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String formatTime(Date aTs_Datetime) {
        return format(aTs_Datetime, TIME_PATTON_DEFAULT);
    }

    /**
     * 将时间转换格式为"yyyyMM"的字符串
     * @author lvjunlong
     * @data 2019年8月22日
     * @param aTs_Datetime 原始时间
     * @return
     */
    public static String formatTime5(Date aTs_Datetime) {
        return format(aTs_Datetime, DATA_PATTON_YYYYMM);
    }
    /**
     * 将给定的时间转化为给定的字符串格式 一般不带"HH:mm:ss"
     *
     * @param aTs_Datetime 给定的时间
     * @param as_Pattern 指定的字符串格式
     * @return 目标时间字符串
     */
    public static String format(Date aTs_Datetime, String as_Pattern) {
        if (aTs_Datetime == null || as_Pattern == null) {
            return null;
        }

        SimpleDateFormat dateFromat = new SimpleDateFormat();
        dateFromat.applyPattern(as_Pattern);

        return dateFromat.format(aTs_Datetime);
    }

    /**
     * 将给定的时间转化为给定的字符串格式 一般带"HH:mm:ss"
     *
     * @param aTs_Datetime 给定的时间
     * @param as_Format 指定的字符串格式
     * @return 目标时间字符串
     */
    public static String formatTime(Date aTs_Datetime, String as_Format) {
        if (aTs_Datetime == null || as_Format == null)
            return null;

        SimpleDateFormat dateFromat = new SimpleDateFormat();
        dateFromat.applyPattern(as_Format);

        return dateFromat.format(aTs_Datetime);
    }

    public static String getFormatTime(Date dateTime) {
        return formatTime(dateTime, TIME_PATTON_HHMMSS);
    }

    /**
     * Timestamp:它允许 JDBC API 将该类标识为 SQL TIMESTAMP 值
     *
     * @param aTs_Datetime
     * @param as_Pattern
     * @return
     */
    public static String format(Timestamp aTs_Datetime, String as_Pattern) {
        if (aTs_Datetime == null || as_Pattern == null)
            return null;

        SimpleDateFormat dateFromat = new SimpleDateFormat();
        dateFromat.applyPattern(as_Pattern);

        return dateFromat.format(aTs_Datetime);
    }


    /**
     * 获取两个日期，间隔天数
     * @param nowTime 日期1
     * @param lastTime 日期2
     * @return 时间间隔
     */
    public static Long getApartDays(Date nowTime,Date lastTime){
        if (nowTime.getTime() > lastTime.getTime()) {
            return 0L;
        }
        return (lastTime.getTime() - nowTime.getTime()) / (24*60*60*1000);
    }



    /**
     * 计算连个时间间隔的天数，包括当前天数
     *
     * @param bDate 时间1
     * @param eDate 时间2
     * @return 时间间隔
     */
    public static double daysOfTwoDate(Date bDate, Date eDate) {

        GregorianCalendar bg = new GregorianCalendar();
        GregorianCalendar eg = new GregorianCalendar();

        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(Calendar.YEAR, 1900);//
        calendar1.set(Calendar.MONTH, 0);
        calendar1.set(Calendar.DATE, 1);

        Date tempDate = DateUtils.formatDateNoHour(calendar1.getTime());

        if (bDate == null || eDate == null || bDate.before(eDate)
                || bDate.equals(tempDate)) {
            return 0;
        }
        bg.setTime(bDate);
        eg.setTime(eDate);

        double elapsed = 0;
        GregorianCalendar bgc, egc;

        if (eg.after(bg)) {
            egc = (GregorianCalendar) eg.clone();
            bgc = (GregorianCalendar) bg.clone();
        } else {
            egc = (GregorianCalendar) bg.clone();
            bgc = (GregorianCalendar) eg.clone();
        }

        bgc.clear(Calendar.MILLISECOND);
        bgc.clear(Calendar.SECOND);
        bgc.clear(Calendar.MINUTE);
        bgc.clear(Calendar.HOUR_OF_DAY);
        bgc.clear(Calendar.AM_PM);
        bgc.clear(Calendar.HOUR);

        egc.clear(Calendar.MILLISECOND);
        egc.clear(Calendar.SECOND);
        egc.clear(Calendar.MINUTE);
        egc.clear(Calendar.HOUR_OF_DAY);
        bgc.clear(Calendar.AM_PM);
        bgc.clear(Calendar.HOUR);

        while (bgc.before(egc)) {
            bgc.add(Calendar.DATE, 1);
            elapsed++;
        }
        return elapsed;

    }

    /**
     * 返回不带HH:mm:ss的时间
     *
     * @param date
     * @return
     */
    public static Date formatDateNoHour(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.clear(Calendar.MILLISECOND);
        calendar.clear(Calendar.SECOND);
        calendar.clear(Calendar.MINUTE);
        calendar.clear(Calendar.HOUR_OF_DAY);
        calendar.clear(Calendar.AM_PM);
        calendar.clear(Calendar.HOUR);
        return calendar.getTime();

    }

    /**
     * 创建不带HH:mm:ss的当前时间
     *
     * @return
     */
    public static Calendar getCurrentDateNoHour() {
        Calendar calendar = Calendar.getInstance();
        calendar.clear(Calendar.MILLISECOND);
        calendar.clear(Calendar.SECOND);
        calendar.clear(Calendar.MINUTE);
        calendar.clear(Calendar.HOUR_OF_DAY);
        calendar.clear(Calendar.AM_PM);
        calendar.clear(Calendar.HOUR);
        return calendar;
    }

    /**
     * 添加月份
     *
     * @param bDate
     * @param months
     * @return
     */
    public static Date getDateAfterMonths(Date bDate, int months) {
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(bDate);
        rightNow.add(Calendar.MONTH, months);
        return rightNow.getTime();
    }

    /**
     * 获取时间
     *
     * @param year 年
     * @param month 月
     * @param day 日
     * @return 时间
     */
    public static Date getDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);//
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DATE, day);

        return calendar.getTime();
    }

    /**
     * 根据格式输出时间字符串
     *
     * @param date 日
     * @param style 指定格式
     * @return 时间字符串
     */
    public static String formatDateByStyle(Date date, String style) {
        if (date == null) {

            return "1900-01-01";
        }
        SimpleDateFormat fmt = new SimpleDateFormat(style);
        return fmt.format(date);

    }

    /**
     * 获取当前月份的第一天
     *
     * @param date 日
     * @return 当前月份的第一天
     */
    public static Date getMinDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int minDate = cal.getActualMinimum(Calendar.DATE);
        cal.set(Calendar.DATE, minDate);
        return formatDateNoHour(cal.getTime());

    }

    /**
     * 获取当前月份的最后一天
     *
     * @param date 日
     * @return 当前月份的最后一天
     */
    public static Date getMaxDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int maxDate = cal.getActualMaximum(Calendar.DATE);
        cal.set(Calendar.DATE, maxDate);

        return formatDateNoHour(cal.getTime());
    }

    public static Date getLastDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH,
                cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    public static Date getFirstDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    /**
     * 得到 当前时间的 13位毫秒数
     * @return 时间戳
     */
    public static Long getTImes(){
        Date d = new Date();
        return d.getTime();
    }

    /**
     * 获取当前14位时间
     * @return 当前14位时间
     */
    public static String getDateTime(){
        SimpleDateFormat fmt = new SimpleDateFormat(TIME_PATTON_DEFAULT);
        Date date = new Date();
        return fmt.format(date);
    }

    /**
     * 获取当前月份的后一个月份
     * @param specifiedDay 当前月份
     * @return 当前月份的后一个月
     */
    public static String getSpecifiedMonthAfter(String specifiedDay){
        Calendar c = Calendar.getInstance();
        Date date=null;
        try {
            date = new SimpleDateFormat("yyyyMM").parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int month=c.get(Calendar.MONTH);
        c.set(Calendar.MONTH,month+1);
        String dayBefore=new SimpleDateFormat("yyyyMM").format(c.getTime());
        return dayBefore;
    }

    /**
     * 获取当前月份的前一个月
     * @author lvjunlong
     * @data 2019年8月22日
     * @param specifiedDay 当前月份
     * @return 当前月份的前一个月
     */
    public static String getSpecifiedMonthBefore(String specifiedDay){
        Calendar c = Calendar.getInstance();
        Date date=null;
        try {
            date = new SimpleDateFormat("yyyyMM").parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int month=c.get(Calendar.MONTH);
        c.set(Calendar.MONTH,month-1);
        String dayBefore=new SimpleDateFormat("yyyyMM").format(c.getTime());
        return dayBefore;
    }

    /**
     * 获取N个小时之后的时间
     */
    public static Date getNextTime(Date date,float n){
        long curren = date.getTime();
        curren += n* 60 * 60 * 1000;
        Date da = new Date(curren);
        return da;
    }

    /**
     * 获取某个时间n分钟后的时间
     * @author lvjunlong
     * @date 2018年8月22日
     * @param date 时间
     * @param n 分钟
     * @return 某个时间n分钟后的时间
     */
    public static Date getAfterMinTime(Date date,int n){
        long curren = date.getTime();
        curren += n * 60 * 1000;
        Date da = new Date(curren);
        return da;
    }
    /**
     * 获取两个时间之间的时间差
     * @param nowTime 开始时间
     * @param lastTime 结束时间
     * @return 返回格式：00天00时00分00秒
     */
    public static String getTimeCha(Date nowTime,Date lastTime){
        if (nowTime.getTime() <= lastTime.getTime()) {
            long diff = lastTime.getTime() - nowTime.getTime();
            long day=diff/(24*60*60*1000);
            long hour=(diff/(60*60*1000)-day*24);
            long min=((diff/(60*1000))-day*24*60-hour*60);
            long s=(diff/1000-day*24*60*60-hour*60*60-min*60);
            StringBuffer strbuff = new StringBuffer();
            if (day >0 && day<10) {
                strbuff.append("0"+day+"天");
            }else if(day >=10){
                strbuff.append(day+"天");
            }
            if (hour<10) {
                strbuff.append("0"+hour+"时");
            }else {
                strbuff.append(hour+"时");
            }
            if (min<10) {
                strbuff.append("0"+min+"分");
            }else {
                strbuff.append(min+"分");
            }
            if (s<10) {
                strbuff.append("0"+s+"秒");
            }else {
                strbuff.append(s+"秒");
            }
            return strbuff.toString();
        }else{
            return "0";
        }
    }
    /**
     * 时间段比较
     * @param beginTime 开始时间
     * @param endTime 结束时间
     * @return -1-未开始；0-进行中；1-已结束
     */
    public static int checkTime(Date beginTime,Date endTime){
        int flag=0;
        Date date = parseDate(format(new Date()));
        if (endTime.getTime() < beginTime.getTime()) {
            Date d = endTime;
            endTime = beginTime;
            beginTime = d;
        }
        if (date.getTime() < beginTime.getTime()) {//未开启
            flag = -1;
        }else if (endTime.getTime() < date.getTime()) {//已结束
            flag = 1;
        }else if (beginTime.getTime() <= date.getTime() && date.getTime() <= endTime.getTime()) {
            flag = 0;
        }
        return flag;
    }
    /**
     * 获取目标时间的之前/之后几小时的时间
     * @param date 目标时间
     * @param hours 小时
     * @return 目标时间的之前/之后几小时的时间
     */
    public static Date getNextHourseTime(Date date,int hours){
        Calendar  dar=Calendar.getInstance();
        dar.setTime(date);
        dar.add(Calendar.HOUR_OF_DAY, hours);
        return dar.getTime();
    }
    /**
     * 获取当前时间与某个时间的间隔
     * @author xiaoli<xiaoli@zjyushi.com>
     * @date 2015年9月10日
     * @param time 指定时间
     * @return 时间间隔
     */
    public Long getTimeInterval (String time) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //给定的时间
            Date d=format.parse(time);
            //当前时间处理
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);

            //给定时间处理
            Calendar setCal = Calendar.getInstance();
            setCal.setTime(d);
            setCal.set(Calendar.HOUR_OF_DAY, 0);
            setCal.set(Calendar.MINUTE, 0);
            setCal.set(Calendar.SECOND, 0);
            setCal.set(Calendar.MILLISECOND, 0);
            System.out.println(cal.getTimeInMillis());
            System.out.println(setCal.getTimeInMillis());
            long dayDiff =(cal.getTimeInMillis()-setCal.getTimeInMillis())/(1000*60*60*24);
            return dayDiff ;
        } catch (Exception e) {
            return null ;
        }

    }

    /**
     * 获取同一天时间内的分钟数
     * @author xiaoli<xiaoli@zjyushi.com>
     * @date 2015年9月10日
     * @param nowTime 当前时间
     * @param lastTime 结束时间
     * @return 分钟数
     */
    public static String getTimeChaHour(Date nowTime,Date lastTime){
        String str = "" ;
        if (nowTime.getTime() <= lastTime.getTime()) {
            long diff = lastTime.getTime() - nowTime.getTime();
            long day=diff/(24*60*60*1000);
            long hour=(diff/(60*60*1000)-day*24);
            long min=((diff/(60*1000))-day*24*60-hour*60);
            long s=(diff/1000-day*24*60*60-hour*60*60-min*60);
            if (day==1) {
                str = "昨天" ;
            }else if (day == 2) {
                str = "前天" ;
            }else if (day > 2) {
                str =day+ "天前" ;
            }else if (day == 0 && hour > 0) {
                str =hour+ "小时前" ;
            }else if (day == 0 && hour == 0 && min>0) {
                str =min+ "分钟前" ;
            }else{
                str =s+ "秒前" ;
            }
        }
        return str ;
    }

    /**
     * 获取当前日期前一天
     * @return 当前时间的前一天
     */
    public static String getBeforeDateFormat(String format){
        Date dNow = new Date();  					//当前时间
        Date dBefore = new Date();
        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(dNow);						//把当前时间赋给日历
        calendar.add(Calendar.DAY_OF_MONTH, -1);  	//设置为前一天
        dBefore = calendar.getTime();   			//得到前一天的时间
        SimpleDateFormat sdf=new SimpleDateFormat(format); //设置时间格式
        String beforeDate = sdf.format(dBefore);    //格式化前一天
        return beforeDate;
    }

    /**
     * 获取当前时间的前n天
     * @author lvjunlong
     * @data 2018年8月22日
     * @param n 天
     * @return 时间
     */
    public static String getBeforeDateFormat(int n){
        Date dNow = new Date();  					//当前时间
        Date dBefore = new Date();
        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(dNow);						//把当前时间赋给日历
        calendar.add(Calendar.DAY_OF_MONTH, -n);  	//设置为前n天
        dBefore = calendar.getTime();   			//得到前一天的时间
        SimpleDateFormat sdf=new SimpleDateFormat(DATA_PATTON_YYYYMMDD); //设置时间格式
        String beforeDate = sdf.format(dBefore);    //格式化前一天
        return beforeDate;
    }

    /**
     * 获取当前时间n天后
     * @author lvjunlong
     * @data 2018年\8月22日
     * @param n 天
     * @return n天后
     */
    public static String getAfterDateFormat(int n){
        Date dNow = new Date();  					//当前时间
        Date dBefore = new Date();
        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(dNow);						//把当前时间赋给日历
        calendar.add(Calendar.DAY_OF_MONTH, n);  	//设置为后一天
        dBefore = calendar.getTime();   			//得到前一天的时间
        SimpleDateFormat sdf=new SimpleDateFormat(DATA_PATTON_YYYYMMDD); //设置时间格式
        String beforeDate = sdf.format(dBefore);    //格式化前一天
        return beforeDate;
    }

    /**
     * 获取某天date的n天后
     * @author lvjunlong
     * @data 2019年8月22日
     * @param date 天
     * @param n 天
     * @return 某天的n天后
     */
    public static String getAfterDateFormat(String date ,int n){
        Date dNow = parseDateForFormat(DATA_PATTON_YYYYMMDD, date);				//当前时间
        Date dBefore = parseDateForFormat(DATA_PATTON_YYYYMMDD, date);
        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(dNow);						//把当前时间赋给日历
        calendar.add(Calendar.DAY_OF_MONTH, n);  	//设置为后一天
        dBefore = calendar.getTime();   			//得到前一天的时间
        SimpleDateFormat sdf=new SimpleDateFormat(DATA_PATTON_YYYYMMDD); //设置时间格式
        String beforeDate = sdf.format(dBefore);    //格式化前一天
        return beforeDate;
    }

    /**
     * 获取之后的n天
     *
     * @param date 天
     * @param days 之后的n天
     * @return 之后的n天
     */
    public static Date getDateAfterDay(Date date, int days) {
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        rightNow.add(Calendar.DATE, days);
        rightNow.clear(Calendar.MILLISECOND);
        rightNow.clear(Calendar.SECOND);
        rightNow.clear(Calendar.MINUTE);
        rightNow.clear(Calendar.HOUR_OF_DAY);
        rightNow.clear(Calendar.AM_PM);
        rightNow.clear(Calendar.HOUR);
        return rightNow.getTime();

    }

    /**
     *  获取当前时间的年月日
     * @return 当前时间的年月日
     */
    public static String getNowDate () {
        Date date = getStartDate() ;
        return formatDate(date, DATA_PATTON_YYYYMMDD);
    }

    /**
     * 获取当日 00:00:00
     * @return 获取当日
     */
    private static Date getStartDate() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    /**
     * 获取当日 24:00:00
     * @return
     */
    public static Date getEndDate() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 24);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    /**
     *
     * @param str
     * @return
     */
    public static Integer getUnixTimestampByString(String str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = format.parse(str);
            long dt = date.getTime() / 1000;
            return Integer.parseInt(Long.toString(dt));
        } catch (Exception ignored) {
            return null;
        }
    }
}
