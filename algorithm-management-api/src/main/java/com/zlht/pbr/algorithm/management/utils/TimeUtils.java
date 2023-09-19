package com.zlht.pbr.algorithm.management.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zijian Wang
 */
public class TimeUtils {

    public static Map<String, Date> getCurrentWeekRange(Date date) {
        Map<String, Date> map = new HashMap<>(2);
        LocalDate today = LocalDate.now();
        LocalDate monday = today.with(DayOfWeek.MONDAY);
        LocalDate sunday = today.with(DayOfWeek.SUNDAY);

        map.put("startOfWeek", convertToDate(monday));
        map.put("endOfWeek", convertToDate(sunday));
        return map;
    }

    public static Map<String, Date> getCurrentMonthRange(Date date) {
        Map<String, Date> map = new HashMap<>(2);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date startOfMonth = calendar.getTime();
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DATE, -1);
        Date endOfMonth = calendar.getTime();

        map.put("startOfMonth", startOfMonth);
        map.put("endOfMonth", endOfMonth);
        return map;
    }
    public static Map<String, Date> getPreviousMonthRange(Date date) {
        Map<String, Date> map = new HashMap<>(2);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date startOfPreviousMonth = calendar.getTime();
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DATE, -1);
        Date endOfPreviousMonth = calendar.getTime();

        map.put("startOfMonth", startOfPreviousMonth);
        map.put("endOfMonth", endOfPreviousMonth);
        return map;
    }

    public static Date convertToDate(LocalDate localDate) {
        ZoneId zoneId = ZoneId.systemDefault();
        return Date.from(localDate.atStartOfDay(zoneId).toInstant());
    }
}
