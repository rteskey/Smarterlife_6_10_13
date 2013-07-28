package com.smarterlife.calendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class CalendarClass {
	
	int CalendarId;
	int Eventid;
	String EventData;
    String EventName;
    java.util.Date EventStartDate;
    java.util.Date EventEndDate;
    int EventPerhr;
    float EventHeight;
    float EventWidth;
    int Xlayout;
    float Ylayout;
    ArrayList<Integer> ConcurrrentEventIds = new  ArrayList<Integer>();
	public void setEventStartDate(String strDate)
	{		//date = EventStartDate;
	 SimpleDateFormat fmt = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");
	 try {
		 EventStartDate = fmt.parse(strDate);  }
	 catch (ParseException e){ }
	 }
	
	public void setEventEndDate(String strDate)
	{		//date = EventStartDate;
		 SimpleDateFormat fmt = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");
		 try {
			 EventEndDate = fmt.parse(strDate);  }
		 catch (ParseException e){ }
	}
	


}
