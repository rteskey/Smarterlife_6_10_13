package com.smarterlife.calendar;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class  Cal_Calendar {
	private static SimpleDateFormat currentDateFormat;
	private static String Months[] = new String[] { "January", "February", "March",
			"April", "May", "June", "July", "August", "September", "October",
			"November", "December" };
	public static  Calendar getCurrentCalendar()
	{
		return Calendar.getInstance();
	}
	
	public static String getCurrentDate_MonthDay()
	{
		currentDateFormat = new SimpleDateFormat("MMMMMMMMM, dd");
		return currentDateFormat.format(Calendar.getInstance().getTimeInMillis());
	}
	public static int getCurrentDate_DayInYear()
	{
	  return Calendar.getInstance().get(Calendar.DAY_OF_YEAR);
	}
	
	public static int getCurrentDate_WeekInYear()
	{
		return Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
	}
	public static int getCurrentDate_MonthInYear()
	{
		return Calendar.getInstance().get(Calendar.MONTH)+1;  // months are based on zero index.. 
	}
	public static int getCurrentDate_Year()
	{
		return Calendar.getInstance().get(Calendar.YEAR);  // months are based on zero index.. 
	}
     
	public static String getCurrentDate(int day, int month, int year)
	{    Calendar cal = Calendar.getInstance();
	      cal.set(year, month, day);
		
		currentDateFormat = new SimpleDateFormat("MMM dd, yyyy");
		return currentDateFormat.format(cal.getTimeInMillis());
	}
}
