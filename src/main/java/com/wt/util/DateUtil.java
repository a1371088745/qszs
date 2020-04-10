package com.wt.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
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
	public static Date getNextWeekDate(Date date,int day) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getThisWeekMonday(date));
		cal.add(Calendar.DATE, 7+(day-1));
		return cal.getTime();
	}
}
