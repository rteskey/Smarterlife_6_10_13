package com.smarterlife.calendar;




import java.util.Arrays;
import java.util.Calendar;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.view.ViewPager.PageTransformer;
import android.util.TypedValue;
import android.view.Display;
import android.view.DragEvent;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.LayoutInflater.Filter;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewAnimator;
import android.widget.ViewFlipper;
import apphelper.ActivitySwipeDetector;
import apphelper.EventPagerListener;
import apphelper.SmarterlifeHelper;

public class Cal_monthActivity extends FragmentActivity  implements OnClickListener, OnTouchListener,OnItemSelectedListener,OnDismissListener,
		OnLongClickListener {

	private static ImageButton ic_Calenendar;
	private static ImageButton ic_Search;
	private static ImageButton ic_Settings;
	private static ImageButton ic_Menu;
	private static TextView sp_Month;
	private static TextView sp_Year;
	private static int screenWidth, screenHeight;
	private String[] monthsList = { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October",	"November", "December" };
	private  Integer[] yearsList = new Integer[20];
	private  static   ViewPager  vp_MonthCalendar;
	private static FragmentManager fg_MonthCalendar;
	private static ViewAnimator mContainer;
	private  static Button handleShowHide;
	private static int  vp_height;
	private static LinearLayout lyt_vp_monthcalendar ; 
	private static Button btnAddEvent;
	private static Button btnEvents;
	private static Button btnAddTask;
	private static Button btnTasks;
	private static Button btnToday;
	private static LinearLayout lyt_events;
	private static LinearLayout lyt_tasks;
	private static LinearLayout lyt_events_lists;
	private static LinearLayout lyt_tasks_lists;
	private static ImageView img_event;
	private static ImageView img_task;
	//private static LinearLayout lyt_handle;
	private static LayoutInflater inflater;
	private static View event_item_view_vp;
	private static ImageView event_item_img;
	private static TextView event_item_time;
	private static TextView event_item_title;
	
	private static View task_item_view;
	private static ImageView task_item_img;
	private static TextView task_item_time;
	private static TextView task_item_title;
	private static long vpTimeInMills;
	private static LinearLayout  lyt_monthspinner;
	private static LinearLayout  lyt_yearspinner;
	private static Point monthPoint;
	private static Point yearPoint;
	private PopupWindow MonthPopup;
	private PopupWindow YearPopup;
	private Display ScreenDisplay;
	private Event eventDelete ; 
	SmarterlifeHelper helper ;
	 
	    View.OnTouchListener gestureListener;
	    ActivitySwipeDetector activitySwipeDetector = new ActivitySwipeDetector(this);
 	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cal_month);
        
		
		
		initializeActivity();
		populateControls();
		populateYearList();
		ShowHideTabView(btnEvents);
		
		populateEvents();
		populateTasks();
		
		helper = new SmarterlifeHelper(this);
		
		
		

	}

	
	//@SuppressLint("NewApi")
	private void initializeActivity() {
		
		ic_Calenendar = (ImageButton) findViewById(R.id.img_calendar_icon);
		ic_Search = (ImageButton) findViewById(R.id.img_search_icon);
		ic_Settings = (ImageButton) findViewById(R.id.img_settings_icon);
		ic_Menu = (ImageButton) findViewById(R.id.img_menu_icon);
		sp_Month = (TextView) findViewById(R.id.spn_month);
		sp_Year = (TextView) findViewById(R.id.spn_year);
		vp_MonthCalendar = (ViewPager) findViewById(R.id.vp_monthcalendar);
		
		fg_MonthCalendar = getSupportFragmentManager();
		handleShowHide = (Button) findViewById(R.id.btn_handle);
		btnAddEvent =(Button)findViewById(R.id.btn_eventsadd);
		btnEvents =(Button)findViewById(R.id.btn_events);
		btnAddTask =(Button)findViewById(R.id.btn_tasksadd);
		btnTasks =(Button)findViewById(R.id.btn_tasks);
		btnToday=(Button)findViewById(R.id.btn_today);
		lyt_events = (LinearLayout) findViewById(R.id.lyt_events);
		lyt_tasks = (LinearLayout) findViewById(R.id.lyt_tasks);
		lyt_events_lists =(LinearLayout) findViewById(R.id.lyt_events_lists); 
		lyt_tasks_lists =(LinearLayout) findViewById(R.id.lyt_tasks_lists);
		img_event = (ImageView)findViewById(R.id.img_event);
		img_task = (ImageView)findViewById(R.id.img_task);
		lyt_monthspinner= (LinearLayout)findViewById(R.id.lyt_monthspinner);
		lyt_yearspinner= (LinearLayout)findViewById(R.id.lyt_yearspinner);
		 ScreenDisplay = getWindowManager().getDefaultDisplay();
		 SmarterlifeHelper.ScreenDisplay= ScreenDisplay;
		 monthPoint = new Point();
		ScreenDisplay.getSize(monthPoint);
		 yearPoint = new Point();
		ScreenDisplay.getSize(yearPoint);
		
		vp_MonthCalendar.getLayoutParams().height=425;
		//lyt_handle = (LinearLayout) findViewById(R.id.lyt_handle);
		inflater=(LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		handleShowHide.setOnTouchListener(this);
		//lyt_handle.setOnTouchListener(this);
		
		lyt_vp_monthcalendar  = (LinearLayout)findViewById(R.id.lyt_vp_monthcalendar);
		btnEvents.setOnClickListener(this);
		btnTasks.setOnClickListener(this);
		btnToday.setOnClickListener(MonthControlClickListener);
		//lyt_events_lists.setOnDragListener(new MyDragListener());
		vp_MonthCalendar.setOnPageChangeListener(CalendarViewPagerListener);
		lyt_monthspinner.setOnClickListener(MonthControlClickListener);
		lyt_yearspinner.setOnClickListener(MonthControlClickListener);
		
	}

	private void populateControls() {
		//sp_Month.setAdapter(new ArrayAdapter<CharSequence>(this , R.layout.sp_item_text, monthsList));
		//sp_Year.setAdapter(new ArrayAdapter<Integer>(this , R.layout.sp_item_text, yearsList));
		//sp_Month.setOnItemSelectedListener(this);
		
		Calendar nCal = Calendar.getInstance();
		nCal.set(2012, 0,1 );
		vpTimeInMills = nCal.getTimeInMillis() - Calendar.getInstance().getTimeInMillis();
		MonthPagerAdapter vpMonthAdapter = new MonthPagerAdapter(fg_MonthCalendar, getApplicationContext(), Calendar.getInstance().get(Calendar.MONTH) ,vpTimeInMills);
		vp_MonthCalendar.setAdapter(vpMonthAdapter);
	    vp_MonthCalendar.setCurrentItem(100);
		vp_height = vp_MonthCalendar.getLayoutParams().height;
		
		vp_MonthCalendar.setPageTransformer(false, new PageTransformer()
        {
            @SuppressLint("NewApi")
            public void transformPage(View v, float pos)
            {
                final float invt = Math.abs(Math.abs(pos) - 1);
                v.setAlpha(invt);
                
            }
        });
		
	}
	

	private void populateEvents()
	{
		for(int i = 1 ; i <8; i ++)
		{
            Event event = new Event();
            event.setEventid(i);
            event.setEventName("Event" + i);
            
			event_item_view_vp = inflater.inflate(R.layout.lyt_event_item_vp,null);
		
			  event_item_view_vp.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,SmarterlifeHelper.getPixels(80, this.getResources())));
		    final ViewPager vp_event_item =(ViewPager) event_item_view_vp.findViewById(R.id.vp_event);
		    vp_event_item.setId(i);
		   
		    vp_event_item.setOnTouchListener(new OnTouchListener(){

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					switch (event.getAction()) {
				    case MotionEvent.ACTION_MOVE: 
				    	
				    	vp_event_item.requestDisallowInterceptTouchEvent(true);
				        break;
				    case MotionEvent.ACTION_UP:
				    case MotionEvent.ACTION_CANCEL:
				    	vp_event_item.requestDisallowInterceptTouchEvent(false);
				        break;
				    }
					return false;
				}});
		    
		    
		    EventPagerAdapter eventPagerAdapter  = new EventPagerAdapter(event, getSupportFragmentManager(),getApplicationContext());
			vp_event_item.setAdapter(eventPagerAdapter); // EventPagerAdapter( getSupportFragmentManager(),getApplicationContext())
		
			//vp_event_item.getLayoutParams().height=60;
			vp_event_item.setTag(event);
		 	vp_event_item.setCurrentItem(1);
		 	//vp_event_item.setOnLongClickListener(EventTaskLongClickListener);
		 	//vp_event_item.setOnTouchListener(EventTaskTouchListener);
		 	vp_event_item.setOnPageChangeListener(new EventPagerListener(this,  vp_event_item));
		 	vp_event_item.setPageMargin(50);
		 	int margin = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10*2,     getResources().getDisplayMetrics());
		 	vp_event_item.setPageMargin(margin);
		 	vp_event_item.setPageTransformer(false, new PageTransformer()
            {
                @SuppressLint("NewApi")
                public void transformPage(View v, float pos)
                {
                    final float invt = Math.abs(Math.abs(pos) - 1);
                    v.setAlpha(invt);
                }
            });
		 	
		 	
		//vfp_event_item.setDisplayedChild(1);
	//	LinearLayout lyt_event_item = (LinearLayout)event_item_view.findViewById(R.id.lyt_event_item);
		//	vp_event_item.setOnDragListener(new MyDragListener());
//		event_item_img = (ImageView)event_item_view.findViewById(R.id.img_event_item_color);
	//	event_item_time = (TextView)event_item_view.findViewById(R.id.txt_event_item_time); 
	//	event_item_title = (TextView)event_item_view.findViewById(R.id.txt_drag_status);
		// setting values
	//	event_item_time.setText(Calendar.getInstance().get(Calendar.HOUR) + ":"+ Calendar.getInstance().get(Calendar.MINUTE)+i + " pm ");
	//	event_item_title.setText("Event "+ i  );
	
		//lyt_event_item.setOnLongClickListener(EventTaskLongClickListener);
		
	  //vp_event_item.setOnTouchListener(activitySwipeDetector);
	  
		
	
		//lyt_events_lists.addView(lyt_drag);
		lyt_events_lists.addView(event_item_view_vp);
		}
	}
	
	private void populateTasks()
	{
		for(int i = 0 ; i <10; i ++)
		{
			task_item_view = inflater.inflate(R.layout.lyt_task_item,null);
			task_item_img = (ImageView)task_item_view.findViewById(R.id.img_task_item_color);
			task_item_time = (TextView)task_item_view.findViewById(R.id.txt_task_item_time); 
			task_item_title = (TextView)task_item_view.findViewById(R.id.txt_task_item_title);
			// setting values
			task_item_time.setText(Calendar.getInstance().get(Calendar.HOUR) + ":"+ Calendar.getInstance().get(Calendar.MINUTE)+i + " pm ");
			task_item_title.setText("task " +String.valueOf(i));
			task_item_view.setOnLongClickListener(EventTaskLongClickListener);
			lyt_tasks_lists.addView(task_item_view);
		}
	}
	
	private OnLongClickListener EventTaskLongClickListener = new OnLongClickListener() {

		@Override
		public boolean onLongClick(View v) {
			Toastmsg("touched");
			
			v.setOnTouchListener(EventTaskTouchListener);
			
			return false;
		}
	
	};
	
	
	private OnTouchListener EventTaskTouchListener = new OnTouchListener() {

		@Override
		public boolean onTouch(View view, MotionEvent motionEvent) {
			  float x = motionEvent.getX();
			  float y = motionEvent.getY();
			  
			 long time = Calendar.getInstance().getTimeInMillis();
			  
			
	       CustomEventTaskDragShadowBuilder shadowBuilder =  new CustomEventTaskDragShadowBuilder(view);
	        
	        view.startDrag(null, shadowBuilder, view, 0);
	        //view.setVisibility(View.INVISIBLE);
	        ScaleAnimation animation = new ScaleAnimation(0,0.5f,0,0.5f);
	       view.startAnimation(animation);
	        view.setOnTouchListener(null);
	        return true;
	  
			
		}
		
	};
	
	
	private Runnable TouchRunnable =   new Runnable() {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			
		}
	    };
	
	
	
	

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);

		// Trigger the initial hide() shortly after the activity has been
		// created, to briefly hint to the user that UI controls
		// are available.

	}
	


	@Override
	public void onClick(View v) {
		
		
		
		ShowHideTabView(v);
           
          

	}

	
	
	private void ShowHideTabView(View v)
	{
		 if(v.getTag().equals("events"))
		 {
			 lyt_events.setBackgroundColor(Color.argb(255, 238, 238, 238));
			 btnEvents.setTextColor(Color.argb(255,17 , 17, 17));
			 btnAddEvent.setTextColor(Color.argb(255,17 , 17, 17));
			 img_event.setBackgroundColor(Color.argb(255,17 , 17, 17));
			 
			 lyt_tasks.setBackgroundColor(Color.argb(255, 217, 164, 4));
			 btnTasks.setTextColor(Color.argb(255, 238, 238, 238));
			 btnAddTask.setTextColor(Color.argb(255, 238, 238, 238));
			 img_task.setBackgroundColor(Color.argb(255, 238, 238, 238));
			 
			 lyt_events_lists.setVisibility(View.VISIBLE);
			 lyt_tasks_lists.setVisibility(View.GONE);
			 
		 }
	
		 if(v.getTag().equals("tasks"))
		 {
			 lyt_tasks.setBackgroundColor(Color.argb(255, 238, 238, 238)); 
			 btnTasks.setTextColor(Color.argb(255,17 , 17, 17));
			 btnAddTask.setTextColor(Color.argb(255,17 , 17, 17));
			 img_task.setBackgroundColor(Color.argb(255,17 , 17, 17));
			 
			 
			 lyt_events.setBackgroundColor(Color.argb(255, 217, 164, 4));
			 btnEvents.setTextColor(Color.argb(255, 238, 238, 238)); 
			 btnAddEvent.setTextColor(Color.argb(255, 238, 238, 238)); 
			 img_event.setBackgroundColor(Color.argb(255, 238, 238, 238));
			
			
			 
			 lyt_events_lists.setVisibility(View.GONE);
			 lyt_tasks_lists.setVisibility(View.VISIBLE);
			 
		 }
		
	}
	
	
	
	private void CalendarMonthAnimation(View v) {

		ExpandCollapseAnimation animation = null;
		if (v.getTag().equals("-")) {
			animation = new ExpandCollapseAnimation(lyt_vp_monthcalendar, 10,
					1, 420);

			v.setTag("+");

		} else {
			animation = new ExpandCollapseAnimation(lyt_vp_monthcalendar, 10,
					0, 420);

			v.setTag("-");
		}

		vp_MonthCalendar.startAnimation(animation);

		if (v.getTag().equals("-"))
			handleShowHide
					.setBackgroundResource(R.drawable.top_switcher_expanded_background);
		else
			handleShowHide
					.setBackgroundResource(R.drawable.top_switcher_collapsed_background);

	}
	
	@Override
	public boolean onLongClick(View v) {
		// TODO Auto-generated method stub
		return false;
	}
    @TargetApi(14)
	public void getScreenDem() {
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		
		display.getSize(size);
		display.getSize(size);
		screenWidth = size.x;
		screenHeight = size.y;
	}

    private void populateYearList() {
		Calendar calendar = Calendar.getInstance();
		int startYear = calendar.get(Calendar.YEAR) - 2;
		for (int i = 0; i < yearsList.length; i++)
			yearsList[i] =i + startYear;
		
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		 ExpandCollapseAnimation animation = null;
		if(event.getAction() == MotionEvent.ACTION_UP)
		{
			
		
			CalendarMonthAnimation( v);
	           
	           return true;
	           
		}
	return false;
	}
	
	private OnClickListener MonthControlClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			if(v.getTag().equals("btn_today"))
			{  vpTimeInMills =0L;
				MonthPagerAdapter vpMonthAdapter = new MonthPagerAdapter(fg_MonthCalendar, getApplicationContext(), Calendar.getInstance().get(Calendar.MONTH) ,vpTimeInMills);
				vp_MonthCalendar.setAdapter(vpMonthAdapter);
			    vp_MonthCalendar.setCurrentItem(100);
			   return;
			}
			if(v.getTag().equals("sp_month"))
			{
				// MonthsPopUP();
				//Open popup window
			       if (monthPoint != null)
			      showMonthPopup(Cal_monthActivity.this, monthPoint);
			      // showYearPopup(Cal_monthActivity.this, monthPoint);
			    //   
				return;
			}
			
			if(v.getTag().equals("sp_year"))
			{
				// MonthsPopUP();
				//Open popup window
			       if (yearPoint != null)
			     //  showMonthPopup(Cal_monthActivity.this, monthPoint);
			       showYearPopup(Cal_monthActivity.this, yearPoint);
			    //  
			       return;
				
			} 
			if(v.getTag().equals("btn_year"))
			{
				YearPopup.dismiss();
				return;
			}
			
			else  // if (Integer.valueOf(v.getTag().toString()) >=0 )
			{
				Calendar nCal = Calendar.getInstance();
				//nCal.set(Calendar.MONTH, Integer.valueOf(v.getTag().toString()));
			//	nCal.set(Calendar.YEAR, 2013);  // this should pull from year drop down
		       sp_Month.setText(monthsList[Integer.valueOf(v.getTag().toString())] );
		       YearPopup.dismiss();
		       MonthPopup.dismiss();
				
		        nCal.set(2013, Integer.valueOf(v.getTag().toString()),1 );
				vpTimeInMills = nCal.getTimeInMillis() - Calendar.getInstance().getTimeInMillis();
				MonthPagerAdapter vpMonthAdapter = new MonthPagerAdapter(fg_MonthCalendar, getApplicationContext(), Calendar.getInstance().get(Calendar.MONTH) ,vpTimeInMills);
				vp_MonthCalendar.setAdapter(vpMonthAdapter);
				vp_MonthCalendar.invalidate();
				vp_MonthCalendar.requestLayout();
				vp_MonthCalendar.setCurrentItem(10);
		     
				
				
				
			}
			
			
		}
		
	};
    
	

	
	
	
	private OnPageChangeListener CalendarViewPagerListener = new OnPageChangeListener()
	{

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onPageSelected(int arg0) {
			// TODO Auto-generated method stub
			
			Calendar cal = Calendar.getInstance();
		        cal =  getCalfromMils(vpTimeInMills);
		        cal.add(Calendar.MONTH, arg0-100);
			      int mIndex=  cal.get(Calendar.MONTH);
			      sp_Month.setText( monthsList[ mIndex]); 
			      int yIndex =Arrays.asList(yearsList).indexOf(cal.get(Calendar.YEAR));
			   sp_Year.setText(String.valueOf(cal.get(Calendar.YEAR)));
			      
			     
			
			
		}
		
	};
	
	private static Calendar getCalfromMils(long mils)
	{
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH,Calendar.getInstance().getActualMinimum(Calendar.DAY_OF_MONTH));
		cal.add(Calendar.DAY_OF_YEAR,   (int) (mils / (1000*60*60*24)) );
		
		return cal;
		
	}
	
	public void Toastmsg(String msg)
	{
		
		Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
		
	}
	@Override
	public void onItemSelected(AdapterView<?> adapterView, View view, int i,
	            long l) {

	      
          
	    }


	 @Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	 
	 
	
	 
	 
	 public void onWindowFocusChanged(boolean hasFocus) {
		
		 int[] locationMonth = new int[2];
		 int[] locationYear = new int[2];
		 sp_Month.getLocationOnScreen(locationMonth);
		 sp_Year.getLocationOnScreen(locationYear);
		
		 monthPoint.x = locationMonth[0];
		 monthPoint.y = locationMonth[1];
		 yearPoint.x = locationYear[0];
		 yearPoint.y = locationYear[1];
		 
		 }
	
	      
	// The method that displays the popup.
	 private void showMonthPopup(final Activity context, Point p) {
	    int popupWidth = 400;
	    int popupHeight = 270;
	  
	    // Inflate the popup_layout.xml
	    LinearLayout viewGroup = (LinearLayout) context.findViewById(R.id.lyt_event_leftswipe);
	    LayoutInflater layoutInflater = (LayoutInflater) context
	      .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    View layout = layoutInflater.inflate(R.layout.lyt_monthslist, viewGroup);
	  
	    // Creating the PopupWindow
	    MonthPopup = new PopupWindow(context);
	    MonthPopup.setContentView(layout);
	    MonthPopup.setWidth(popupWidth);
	    MonthPopup.setHeight(SmarterlifeHelper.getPixels(180,getResources()));
	 //  helper.Toastmsg(viewGroup.getLayoutParams().height+"  h", context);
	    MonthPopup.setFocusable(true);
	  
	    // Some offset to align the popup a bit to the right, and a bit down, relative to button's position.
	    int OFFSET_X = 0;
	    int OFFSET_Y = SmarterlifeHelper.getPixels(28,getResources());
	  
	    // Clear the default translucent background
	    MonthPopup.setBackgroundDrawable(new BitmapDrawable());
	  
	    // Displaying the popup at the specified location, + offsets.
	    MonthPopup.showAtLocation(layout, Gravity.NO_GRAVITY, p.x + OFFSET_X, p.y + OFFSET_Y);
	  
	    // Getting a reference to Close button, and close the popup when clicked.
	
	 
	   layout.findViewById(R.id.btn_jan).setOnClickListener( MonthControlClickListener);
	   layout.findViewById(R.id.btn_feb).setOnClickListener( MonthControlClickListener);
	   layout.findViewById(R.id.btn_mar).setOnClickListener( MonthControlClickListener);
	   layout.findViewById(R.id.btn_apr).setOnClickListener( MonthControlClickListener);
	   layout.findViewById(R.id.btn_may).setOnClickListener( MonthControlClickListener);
	   layout.findViewById(R.id.btn_jun).setOnClickListener( MonthControlClickListener);
	   layout.findViewById(R.id.btn_jul).setOnClickListener( MonthControlClickListener);
	   layout.findViewById(R.id.btn_aug).setOnClickListener( MonthControlClickListener);
	   layout.findViewById(R.id.btn_sep).setOnClickListener( MonthControlClickListener);
	   layout.findViewById(R.id.btn_oct).setOnClickListener( MonthControlClickListener);
	   layout.findViewById(R.id.btn_nov).setOnClickListener( MonthControlClickListener);
       layout.findViewById(R.id.btn_dec).setOnClickListener( MonthControlClickListener);
	    
	 

	 }
	 
	 
	 private void showYearPopup(final Activity context, Point p) {
		    int popupWidth = 400;
		    int popupHeight = 370;
		  
		    // Inflate the popup_layout.xml
		    ScrollView viewGroup = (ScrollView) context.findViewById(R.id.lyt_scroll_years);
		    LayoutInflater layoutInflater = (LayoutInflater) context
		      .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		    View layout = layoutInflater.inflate(R.layout.lyt_yearslist, viewGroup);
		  
		    // Creating the PopupWindow
		    YearPopup = new PopupWindow(context);
		    YearPopup.setContentView(layout);
		    YearPopup.setWidth(popupWidth);
		    YearPopup.setHeight(SmarterlifeHelper.getPixels(180,getResources()));
		 //  helper.Toastmsg(viewGroup.getLayoutParams().height+"  h", context);
		    YearPopup.setFocusable(true);
		  
		    // Some offset to align the popup a bit to the right, and a bit down, relative to button's position.
		    int OFFSET_X = 0;
		    int OFFSET_Y = SmarterlifeHelper.getPixels(28,getResources());
		  
		    // Clear the default translucent background
		    YearPopup.setBackgroundDrawable(new BitmapDrawable());
		  
		    // Displaying the popup at the specified location, + offsets.
		    YearPopup.showAtLocation(layout, Gravity.NO_GRAVITY, p.x + OFFSET_X, p.y + OFFSET_Y);
		  
		    // Getting a reference to Close button, and close the popup when clicked.
		
		
		 
		    TableLayout table = new TableLayout(this); 
		    table.setStretchAllColumns(true);
		    int startYear = yearsList[0];
		    
		    for (int i = 0; i <5; i++)
		    {  
		    	TableRow tr = new TableRow(this);
		    
		    	for(int j = 0 ; j <4 ; j ++ )
		    	{
		    		
		    	Button btn_year = new Button(this, null,R.style.calendarmonth_toprow);  //calendarmonth_toprow
		    	TableRow.LayoutParams paramsYear = new TableRow.LayoutParams( TableRow.LayoutParams.MATCH_PARENT,  TableRow.LayoutParams.MATCH_PARENT);
		    	 paramsYear.setMargins(3, 3, 3, 3);
		    	 btn_year.setPadding(5,10, 5, 10);
		         btn_year.setGravity(Gravity.CENTER);
		    	 btn_year.setBackgroundResource(R.drawable.bk_calendar_btn_toprow);
		    	btn_year.setText(String.valueOf(startYear));
		    	btn_year.setTag("btn_year");
		    	btn_year.setOnClickListener( MonthControlClickListener);
		    	
//		    	btn_year.setOnClickListener(new OnClickListener() {
//		    		  
//		    	      @Override
//		    	      public void onClick(View v) {
//		    	      YearPopup.dismiss();
//		    	      }
//		    	    });
		    	tr.addView(btn_year,paramsYear);
		    	startYear ++;
		    	}
		    	table.addView(tr);
		    	
		  }
		    ((ViewGroup) layout.findViewById(R.id.lyt_years_popup)).addView(table);
		    
		 }
		 
	 
	 

		
		
		

		

		@Override
		public void onDismiss() {
			
			finish();
			startActivity(getIntent());
		}
	 
	
}
