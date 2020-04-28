package com.wt.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	//获取本周一日期
	public static Date getThisWeekMonday(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int dayWeek=cal.get(Calendar.DAY_OF_WEEK);
		if(1==dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		int day=cal.get(Calendar.DAY_OF_WEEK);
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek()-day);
		return cal.getTime();
	}
	//获取下周一日期
	public static Date getNextWeekDate(Date date,int day) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getThisWeekMonday(date));
		cal.add(Calendar.DATE, 7+(day-1));
		return cal.getTime();
	}
	//获取本月第一天
	public static Date getFirstMonthDay(){
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, 0);
		c.set(Calendar.DAY_OF_MONTH,1);
		return c.getTime();
	}
	//获取本月最后一天
	public static Date getLastMonthDay(){
		Date firstMonthDay = getFirstMonthDay();
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		return  c.getTime();
	}
}
