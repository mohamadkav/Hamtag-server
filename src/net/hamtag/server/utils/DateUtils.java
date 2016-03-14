package net.hamtag.server.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	public static Date getStartOfDay(Date day) {
		Calendar cal = Calendar.getInstance();
		if (day == null)
			day = new Date();
		cal.setTime(day);
		cal.set(Calendar.HOUR_OF_DAY, cal.getMinimum(Calendar.HOUR_OF_DAY));
		cal.set(Calendar.MINUTE, cal.getMinimum(Calendar.MINUTE));
		cal.set(Calendar.SECOND, cal.getMinimum(Calendar.SECOND));
		cal.set(Calendar.MILLISECOND, cal.getMinimum(Calendar.MILLISECOND));
		return cal.getTime();
	}
	public static Date getEndOfDay(Date day) {
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(day);
	    calendar.set(Calendar.HOUR_OF_DAY, 23);
	    calendar.set(Calendar.MINUTE, 59);
	    calendar.set(Calendar.SECOND, 59);
	    calendar.set(Calendar.MILLISECOND, 999);
	    return calendar.getTime();
	}
	public static boolean validateLongStringDate(String date){
		if(date==null)
			return false;
		Long longDate;
		try{
			longDate=Long.parseLong(date);
		}catch(NumberFormatException e){
			return false;
		}
		Calendar calendar=Calendar.getInstance();
		calendar.add(Calendar.YEAR, -1);
		if(longDate<calendar.getTime().getTime())
			return false;
		calendar=Calendar.getInstance();
		calendar.add(Calendar.MONTH, 1);
		if(longDate>calendar.getTime().getTime())
			return false;
		return true;
	}
}
