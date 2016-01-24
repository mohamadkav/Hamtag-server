package net.hamtag.server.utils;

import java.util.Calendar;

public class DateValidator {
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
