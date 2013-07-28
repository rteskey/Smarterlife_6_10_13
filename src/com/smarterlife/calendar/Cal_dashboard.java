package com.smarterlife.calendar;
import com.smarterlife.calendar.ResponsiveScrollView.OnScrollStoppedListener;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.Toast;
public class Cal_dashboard  extends Activity implements OnClickListener{
	 
	private static Button btnAddEvent;
	private static Button btnEvents;
	private static Button btnCalendar;
	private static Button btnSettings;
	private static Button btnHelp;
	private static Button btnDate;
	private static Button btnDay;
	private static Button btnWeek;
	private static Button btnMonth;
	private static Button btnYear;
	private static ResponsiveScrollView  responsiveScrollView;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calendardashboard);
		initializeActivity();
		setTextControls();
		
		
	}
	private void initializeActivity()
	{
		btnDay = (Button)findViewById(R.id.btnday);
		btnAddEvent =(Button)findViewById(R.id.btn_eventsadd);
		btnEvents =(Button)findViewById(R.id.btn_events);
		btnCalendar=(Button)findViewById(R.id.btn_calendar);
		btnSettings =(Button)findViewById(R.id.btn_settings);
		btnHelp = (Button)findViewById(R.id.btn_help);
		btnDate =(Button)findViewById(R.id.btndate);
		btnWeek=(Button)findViewById(R.id.btnweek);
		btnMonth=(Button)findViewById(R.id.btnmonth);
		btnYear=(Button)findViewById(R.id.btn_calendaryear);
		responsiveScrollView = (ResponsiveScrollView)findViewById(R.id.lyt_calendar_scroll);
		// set on Click 
		btnDate.setOnClickListener(this);
		btnCalendar.setOnClickListener(this);
		btnYear.setOnClickListener(this);
		btnDay.setOnClickListener(this);
		btnAddEvent.setOnClickListener(this);
		responsiveScrollView.setOnTouchListener(new OnTouchListener() {

	        @Override
			public boolean onTouch(View v, MotionEvent event) {

	            if (event.getAction() == MotionEvent.ACTION_UP) {

	            	responsiveScrollView.startScrollerTask();
	            }

	            return false;
	        }
	});
		responsiveScrollView.setOnScrollStoppedListener(new OnScrollStoppedListener() {

	        @Override
			public void onScrollStopped() {

	           // Log.i(TAG, "stopped");

	        	Toast.makeText(getApplicationContext(), "Stopped", Toast.LENGTH_LONG).show();
	        }
	});
		
		
		
		
		
		
	}
		
	private void setTextControls()
	{		
		// fill in current date /day /month /week 
		btnDate.setText( Cal_Calendar.getCurrentDate_MonthDay());
	    btnDay.setText( String.valueOf(Cal_Calendar.getCurrentDate_DayInYear()));   
	    btnWeek.setText( String.valueOf(Cal_Calendar.getCurrentDate_WeekInYear()));
	    btnMonth.setText( String.valueOf(Cal_Calendar.getCurrentDate_MonthInYear()));
	    btnYear.setText( String.valueOf(Cal_Calendar.getCurrentDate_Year()));
	}
		
	@Override
	public void onClick(View v) {
		if(v.getTag().equals("btndate"))
		{
			Toast.makeText(getApplicationContext(), " Test",Toast.LENGTH_SHORT).show();
		}
		
		if(v.getTag().equals("btn_calendar"))
		{
		Intent monthIntent = new Intent(Cal_dashboard.this, Cal_monthActivity.class);
		Cal_dashboard.this.startActivity(monthIntent);
		}
		if(v.getTag().equals("btnday"))
		{
		Intent dayIntent = new Intent(Cal_dashboard.this, Cal_dayActivity.class);
		Cal_dashboard.this.startActivity(dayIntent);
		}
		if(v.getTag().equals("btn_eventsadd"))
		{
		Intent eventIntent = new Intent(Cal_dashboard.this, Cal_eventActivity.class);
		Cal_dashboard.this.startActivity(eventIntent);
		}
		
		
	}
	
}
