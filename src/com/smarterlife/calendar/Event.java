package com.smarterlife.calendar;




import java.io.Serializable;
import java.util.Date;


//import com.google.gson.annotations.Expose;
//import com.google.gson.annotations.SerializedName;

public class  Event  implements Serializable { 
	
//	@Expose
	private  int Eventid ;
	public  void setEventid(int eventid) {
	Eventid = eventid;
	}
	public  int getEventid() {
	return 	Eventid;
	}
	
//	@Expose
	private  String Eventname ;
	public  void setEventName(String eventname) {
	Eventname = eventname;
	}
	public  String getEventName() {
	return Eventname;
	}
	
//	@Expose
	private  Date Startdatetime ;
	public  void  setStartDateTime( Date startdatetime) {
	Startdatetime = startdatetime;
	}
	public  Date getStartDateTime() {
	return Startdatetime;
	}
	
//	@Expose
	private  Date Endtdatetime ;
	public  void setEndDateTime( Date endtdatetime) {
	Endtdatetime = endtdatetime;
	}
	public  Date getEndDateTime()  {
	return Endtdatetime;
	}
	
//	@Expose
	private  String Eventlocation ;
	public  void setEventLocation(String eventlocation) {
	Eventlocation = eventlocation;
	}
	public  String getEventLocation() {
	return Eventlocation;
	}
	
//	@Expose
	private String[] eventreminders ;
	public void setEventReminders(String[]  eventreminders) {
	this.eventreminders = eventreminders;
	}
	public String[] getEventReminders() {
	return eventreminders;
	}
	
	
	
	
	//@Expose
	private String[] eventtasks ;
	public void setEventTasks(String[]  eventtasks) {
	this.eventtasks = eventtasks;
	}
	public String[] getEventTasks() {
	return eventtasks;
	}
	
	//@Expose
	private String[] eventattachments ;
	public void setEventAttachments (String[]  eventattachments) {
	this.eventattachments = eventattachments;
	}
	public String[] getEventAttachments() {
	return eventattachments;
	}
	
	//@Expose
	private int eventpriority ;
	public void setEventPriority ( int eventpriority) {
	this.eventattachments = eventattachments;
	}
	public int getEventPriority() {
	return eventpriority;
	}
	
	//@Expose
	private int eventprivacy ;
	public void setEventPrivacy ( int eventprivacy) {
	this.eventprivacy = eventprivacy;
	}
	public int getEventPrivacy() {
	return eventprivacy;
	}
	
	//@Expose
	private String[] eventattendees ;
	public void setEventAttendees (String[]  eventattendees) {
	this.eventattendees = eventattendees;
	}
	public String[] getEventAttendees() {
	return eventattendees;
	}
	
	//@Expose
	private String eventnotes ;
	public void setEventNotes (String  eventnotes) {
	this.eventnotes = eventnotes;
	}
	public String getEventNotes() {
	return eventnotes;
	}
	
	public enum eventrepeat { everyday,everyweek, everymonth}
	
	
	
	
	

}