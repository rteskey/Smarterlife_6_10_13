package com.smarterlife.calendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;







import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;
import android.widget.CompoundButton.OnCheckedChangeListener;
import androidwheel.ArrayWheelAdapter;
import androidwheel.OnWheelChangedListener;
import androidwheel.OnWheelScrollListener;
import androidwheel.WheelView;
import apphelper.SmarterlifeHelper;

public class Cal_eventActivity extends Activity {
	private TextView dateStartDisplay, dateEndDisplay, timeStartDisplay, timeEndDisplay, reminderDisplay ,reminderMonthDisplay, reminderYearDisplay , txttimewheeltitle ;
	private EditText lbltitle;
	private Button date_ok, time_ok, savebtn, cancelbtn , customreminderbtn,  customreminderbtn_previous, customreminderbtn_next;  
	private View dialoglayout;
	private WheelView wheelHours;
	private WheelView wheelMinutes;
	private WheelView wheelAmPm;
	private AlertDialog alertTime;
	private AddEvent addEvent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cal_event);
		initializeActivityControls();
		populateActivityControls();
	
	}
	private void initializeActivityControls()
	{
		timeStartDisplay = (TextView) this.findViewById(R.id.timeStartDisplay);
		timeEndDisplay=  (TextView) this.findViewById(R.id.timeEndDisplay);
        dateStartDisplay = (TextView) this.findViewById(R.id.dateStartDisplay);
		dateEndDisplay = (TextView) this.findViewById(R.id.dateEndDisplay);
	    
		lbltitle = (EditText)this.findViewById(R.id.txtTitle);
		
	   
		
	}
	
	private void populateActivityControls()
	{
	
		addEvent = new AddEvent(this, Cal_eventActivity.this  );
		addEvent.AddNewEvent();
	    timeStartDisplay .setText(SmarterlifeHelper.getCurrentTimeString(0));
	    timeEndDisplay .setText(SmarterlifeHelper.getCurrentTimeString(1));
		dateStartDisplay.setText(SmarterlifeHelper.getCurrentDateString());
		dateEndDisplay.setText(SmarterlifeHelper.getCurrentDateString());
		
	    
	    // set control events 
	    
	   // lbltitle.addTextChangedListener(textWatcher);
	//	timeStartDisplay.setOnClickListener(TimeDisplayListener);
		//timeEndDisplay.setOnClickListener(TimeDisplayListener);
	    
		
	}
	
@Override
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
	addEvent.onActivityResult(requestCode, resultCode, data);
}

}
