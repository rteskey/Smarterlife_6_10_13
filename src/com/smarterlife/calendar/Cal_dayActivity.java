package com.smarterlife.calendar;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;

import pulltorefresh.PullToRefreshBase;
import pulltorefresh.PullToRefreshBase.OnRefreshListener;
import pulltorefresh.PullToRefreshScrollView;

//import com.pulltorefresh.samples.PullToRefreshScrollViewActivity.GetDataTask;
import com.smarterlife.calendar.R.drawable;



import android.os.AsyncTask;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.view.ViewPager.PageTransformer;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.SlidingDrawer;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SlidingDrawer.OnDrawerCloseListener;
import android.widget.SlidingDrawer.OnDrawerOpenListener;
import apphelper.EventPagerListener;
import apphelper.SmarterlifeHelper;

public class Cal_dayActivity extends  FragmentActivity implements OnClickListener  {
	private static RelativeLayout lyt_status;
	private static  Day_tag day_tag;
	FrameLayout abs;
	ListView listView;
	Button btnClose;
	ScrollView scrollView;
	TableLayout tableLayoutCal;
	//SlidingDrawer EventLyt;
	int key=0;
	  private float mPosX;
	    private float mPosY;
	    
	    private float mLastTouchX;
	    private float mLastTouchY;
	    ScaleAnimation scale;
	    AnimationSet spriteAnimation;
	    float centerX;
	    float centerY;
	    float offsetX;
	    float offsetY;
	    DatePicker datepicker;
	  //  ViewFlinger viewFlinger;
	    View cur_view,next_view,prev_view;
	    static int screenwidth;
		int screenheight;
	    float ScreenDensity;
	     Event popup;
	    private static  FrameLayout lyt_day_glance;
	     private int eventHrNow = -1;
	     private static int paddingEvent = 50;
	    private static int[] eventStartAry = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	    private final static int START_DRAGGING = 0;
		private final static int STOP_DRAGGING = 1;
		private int status;
		private boolean EditDragable = false; 
		private ImageView image;
		ToggleButton Edittogglebtn;
		 private ArrayList<String> listCountry;
		 private ArrayList<Integer> listFlag;
		 SimpleCursorAdapter mAdapter;
		 
			private TextView text;
			private AutoCompleteTextView  text1;
			private AutoCompleteTextView text2;
			private AutoCompleteTextView text3;
			public static Context  ctx;
			private boolean wheelScrolled = false;
			private LayoutInflater inflater;
 			
			private View dialoglayout;
//			private WheelView wheelMonth;
//			private WheelView wheelDays;
//			private WheelView wheelYears; 
			private int totalDaysInMonth;
			private int timeLinePadding;
			private static   LinearLayout lyt_undo_save;
			 static SmarterlifeHelper helper ;
			private static Activity activity;
		 private  static    float dragYLoc=0;
		 private static boolean eventDroped = false;
		 private int _xDelta;
		 private int _yDelta;
		 private static FrameLayout.LayoutParams fLayoutParams = null;
		 private static  float eventMovingHeight = 0 ;
		 private static  float eventMovingWidth = 0 ;
		 private static  int   eventPaddingSize=50;
		 private static   ArrayList<CalendarClass> calendarResult= null;
		private static  TextView txtstatus= null;
		private static Resources resources; 
		private static PullToRefreshScrollView lyt_scroll_timeline; 
		ScrollView mScrollView;
		LinearLayout lyt_linear_timeline , lyt_events_lists;
		private static View event_item_view_vp;
		private static  View event_item_sub_task_view;
		Button eventsLists; 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cal_day);
        resources= getResources();
        ctx = Cal_dayActivity.this;
        activity = (Activity) ctx;
        txtstatus = (TextView)findViewById(R.id.txtstatus);
     	lyt_events_lists =(LinearLayout) findViewById(R.id.lyt_events_lists); 
     	eventsLists = (Button)findViewById(R.id.btn_events);
     	eventsLists.setOnClickListener(this);
     	
        lyt_linear_timeline = (LinearLayout)findViewById(R.id.lyt_linear_timeline);
        lyt_scroll_timeline = (PullToRefreshScrollView)findViewById(R.id.lyt_scroll_timeline);
        lyt_scroll_timeline.setBackgroundResource(drawable.bk_calendar_btn_toprow);
        lyt_scroll_timeline.setOnRefreshListener(new OnRefreshListener<ScrollView>() {
      
			@Override
			public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
				new GetDataTask().execute();
			}
		});

		mScrollView = lyt_scroll_timeline.getRefreshableView();
        
        
        GetEvents();
        
       
        SeekBar sb = (SeekBar)findViewById(R.id.sb_dayview);
        sb.setMax(200);
        sb.setProgress(25);
        sb.incrementProgressBy(25);
        
        
       // lyt_day_glance.refreshDrawableState();
        //lyt_day_glance.invalidate();
       
        
        sb.setOnSeekBarChangeListener(new  OnSeekBarChangeListener(){

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				int stepSize = 5;
				  progress = ((int)Math.round(progress/stepSize))*stepSize;
				
			      if(progress <20)
			    	  progress =25;
				  seekBar.setProgress(progress);
			        
				if(eventPaddingSize !=progress)
				{
					eventPaddingSize =progress;
					lyt_day_glance.removeAllViews();
					lyt_day_glance.refreshDrawableState();
					GetCalendarDownload.BuildEvents(calendarResult, eventPaddingSize);
					lyt_day_glance.invalidate();
				   
				
				}	
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}});
    	     
    	
    	Intent intent = getIntent();
       Calendar  icalendar=    Calendar.getInstance();
       Calendar  Currentcalendar=    Calendar.getInstance();
       Currentcalendar.add(Calendar.DAY_OF_YEAR, Calendar.SUNDAY - Currentcalendar.get(Calendar.DAY_OF_WEEK));
//       String monthStr = null;
//       
       int day=0, year = 0, month = 0;
       double weeks_Diff=0;
//       
//       if( intent!=null && intent.hasExtra("month")&&  intent.hasExtra("day") &&  intent.hasExtra("year"))
//        {
//    	  
//    	   
//    	   
//    	   monthStr = intent.getExtras().getString("month"); 
//            day = intent.getExtras().getInt("day");
//            year = intent.getExtras().getInt("year");
//            for(int i = 0; i < monthslist.length; i++)
//                if(monthslist[i].contains(monthStr.toString().trim()))
//                	month = i;
//            
//            weeks_Diff= getCalendarDays(year, month,day);
//           
//        
//          Toast.makeText(getApplicationContext(), "this-->Year: "+ year+ " Month: " + monthStr + "  day:" + day  + "  CompareDays: " + weeks_Diff  + " getCalendarDays= " + getCalendarDays(year, month,day), Toast.LENGTH_LONG).show();
//        }
       
       
     

       
       
       
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
         screenwidth = size.x;
         screenheight = size.y;
         DisplayMetrics dm = new DisplayMetrics();
         getWindowManager().getDefaultDisplay().getMetrics(dm);
         
         LinearLayout.LayoutParams ls = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, screenheight );
 		ls.setMargins(0, 0, 0, 30);
 		lyt_scroll_timeline.setLayoutParams(ls);
 		lyt_linear_timeline.invalidate();
       //  screenwidth = dm.widthPixels;
        
   //      LinearLayout footerLayout = (LinearLayout) findViewById(R.id.footer);
  //       footerLayout.bringToFront();
         
        
         
        /** Getting a reference to the ViewPager defined the layout file */
       
        LinearLayout monthyearLayout = (LinearLayout)findViewById(R.id.myLayout);
        
        final TextView tvmonth = (TextView)findViewById(R.id.tvmonth);
        final TextView tvyear = (TextView)findViewById(R.id.tvyear);
        
        monthyearLayout.setOnClickListener(new OnClickListener(){
        	
        	public void onClick(View v)
        	{
        		Intent intent = new Intent(getApplicationContext(),
						 Cal_monthActivity.class);
				 intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
				 intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			        intent.putExtra("month" , tvmonth.getText().toString().trim());  // gCal.get(Calendar.MONTH)
			        intent.putExtra("year", Integer.parseInt( tvyear.getText().toString().trim()));
			    // Toast.makeText(mContext.getApplicationContext(), "Month="+txtDay.getText()+" year="+ Integer.parseInt(txtDates.getText().toString()), Toast.LENGTH_SHORT).show();
			     startActivity(intent);
			       finish();
        	}
        	
        	
        } );
         
       
        
         tvmonth.setGravity(Gravity.CENTER_HORIZONTAL);
        // tvmonth.setGravity(Gravity.CENTER_VERTICAL);
         tvyear.setGravity(Gravity.CENTER_HORIZONTAL);
        // tvyear.setGravity(Gravity.CENTER_VERTICAL);
       final  ViewPager pager = (ViewPager) findViewById(R.id.weekpager);
 
        pager.bringToFront();
        
       
        pager.setOnPageChangeListener(new OnPageChangeListener(){

		

			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
				//Toast.makeText(getApplicationContext()," pager.onPageScrollStateChanged " +  arg0,  Toast.LENGTH_SHORT).show();
				
			}

			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}

			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				
			 Calendar g1Cal = Calendar.getInstance();
			 int currentDay =0;
    		 currentDay = g1Cal.get(Calendar.DAY_OF_WEEK);
			 g1Cal.add(Calendar.DAY_OF_YEAR, Calendar.SUNDAY - currentDay);	
			 
			 int cWeek;
				cWeek = (arg0) -1000;
			    
			        	cWeek = (cWeek)*7;
			  g1Cal .add(Calendar.DAY_OF_YEAR, cWeek);
			   
	    		 tvmonth.setText(  monthslist[g1Cal.get(Calendar.MONTH)]);
	    		 tvyear.setText( String.valueOf( g1Cal.get(Calendar.YEAR)) );
		
			}} );
        
 
     
     if(pager != null)    
     {
        /** Instantiating FragmentPagerAdapter */
      FragmentManager fm = getSupportFragmentManager();
        WeekPagerAdapter pagerAdapter = new WeekPagerAdapter(fm, getApplicationContext(),screenwidth,screenheight, Cal_dayActivity.this, day );
     
        /** Setting the pagerAdapter to the pager object */
        if(pagerAdapter != null)
        pager.setAdapter(pagerAdapter);
        

        pager.setCurrentItem(1000 + (int)weeks_Diff);
     }
    

//   //  Button todaybtn = (Button) findViewById(R.id.todaybtn);
//     todaybtn.setOnClickListener(new OnClickListener(){
//
//		public void onClick(View v) {
//			// TODO Auto-generated method stub
//			 pager.setCurrentItem(1000);
//			
//		}
//    	 
//    	 
//     });
     
     
//        tableLayoutCal = (TableLayout) findViewById(R.id.tableLayoutCal);
       
//        Edittogglebtn = (ToggleButton) findViewById(R.id.EditTogglebtn);
//        Edittogglebtn.bringToFront();
//        Edittogglebtn.invalidate();
//        Edittogglebtn.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//		public void onCheckedChanged(CompoundButton buttonView,
//					boolean isChecked) {
//				if(isChecked == true)
//    			{
//    				Toast.makeText(getBaseContext(), "ON State",
//    					Toast.LENGTH_SHORT).show();
//    				EditDragable = true;
//    			}
//    			
//    			else
//    			{
//    				Toast.makeText(getBaseContext(), "OFF State",
//    					Toast.LENGTH_SHORT).show();
//    				EditDragable = false;
//    			}
//				// TODO Auto-generated method stub
//				
//			}
//    	});
//        
       
       
         
   //     EventLyt = (SlidingDrawer)findViewById(R.id.event_lyt);
      //  popup = (Event) findViewById(R.id.popup_window);
        
        // for flinger
     lyt_day_glance = (FrameLayout) findViewById(R.id.lyt_day_glance);
		
        
        
        
        
    //=============== for the pop up

     //   EventLyt.bringToFront();

    	
    	// display screen 
        //=====================================================
        
        
//        final Animation animation = new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
//        animation.setDuration(300); // duration - half a second
//        animation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
//        animation.setRepeatCount(1); // Repeat animation infinitely
//        //animation.setRepeatMode(Animation.REVERSE); // R
//        
//        
//        
//      
//       
//      //  listView = (ListView) findViewById(R.id.listView1);
//        btnClose = (Button) findViewById(R.id.btnClose);
//        
//        btnClose.setOnClickListener(new OnClickListener(){
//
//			public void onClick(View v) {
//				key=0;
//				//popup.setVisibility(View.GONE);
//				// TODO Auto-generated method stub
//				Toast.makeText(getApplicationContext(),  "Event Saved" , Toast.LENGTH_SHORT).show();
//			}
//        	
//		});
//
//		for (int n = 0, s = tableLayoutCal.getChildCount(); n < s; ++n) {
//			final TableRow tableRow = (TableRow) tableLayoutCal.getChildAt(n);
//			tableRow.setOnClickListener(new OnClickListener() {
//				public void onClick(View v) {
//					v.startAnimation(animation);
//					//setCurrentDateOnView();
////					EventLyt.setPadding(screenwidth / 2, 0, 0, 0);
////					Toast.makeText(getApplicationContext(),
////							String.valueOf(tableRow.getId()),
////							Toast.LENGTH_SHORT).show();
//
//				}
//			});
//		}
//
//    
//
	}

    
    private void populateEvents(ArrayList<CalendarClass> calendarResult)
	{
    	 
    
    
    	lyt_status =   (RelativeLayout)activity.findViewById(R.id.lyt_monthcalendar_status);
    	LinearLayout status_parent = (LinearLayout) activity.findViewById(R.id.lyt_event_status_drag);
    	
    	RelativeLayout.LayoutParams lyt_status_params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
    	
    	// lyt_status_params.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
    	
    	
    // status_parent.setLayoutParams(lyt_status_params);
    	//lyt_status.setLayoutParams(lyt_ststus_params);
    	final TextView txtdragstatus = (TextView)  lyt_status.findViewById(R.id.txt_monthcalendar_status);
    	 txtdragstatus.setTextColor(Color.WHITE);
    	 txtdragstatus.setTextSize(TypedValue.COMPLEX_UNIT_SP,35);
    	 txtdragstatus.setBackgroundColor(Color.RED);
    	inflater=(LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    	int i = 9000;
    	for(CalendarClass e: calendarResult)
		{
    		try {
            
    		
    		final Event event = new Event();
            
            event.setEventid(e.Eventid);
           event.setEventName(String.valueOf(e.EventName));
       
//            Activity activity = (Activity) ctx;
//            inflater=  (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		   event_item_view_vp = inflater.inflate(R.layout.lyt_event_item_vp,null);
		   final ViewPager vp_event_item =(ViewPager) event_item_view_vp.findViewById(R.id.vp_event);
		  
		   vp_event_item.setOnTouchListener(new OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent motionevent) {
				 View view_in_vp=null;
				 view_in_vp =vp_event_item.getFocusedChild();
				
				 
				switch (motionevent.getAction()) {
			    case MotionEvent.ACTION_MOVE: 
			    	
			    	lyt_status.setVisibility(View.VISIBLE); 
			    	  
			    	
			    	//eventsLists.setText(vp_event_item.getScrollX()+ " ");
			    	//eventsLists.scrollBy(vp_event_item.getScrollX(), vp_event_item.getScrollY());
			    	
		               
			    	if(vp_event_item.getScrollX()>=200)
			    		txtdragstatus.setText("Change date..");
			    	
			    	if(vp_event_item.getScrollX()>=400)
			    		txtdragstatus.setText("Delete..");
			    	
			    	
			    	if(vp_event_item.getScrollX()< -200)
			    		txtdragstatus.setText("Edit..");
			    	
			    	if(vp_event_item.getScrollX()< -400)
			    		txtdragstatus.setText("Completed..");
			    	
			    	vp_event_item.requestDisallowInterceptTouchEvent(true);
			        break;
			    case MotionEvent.ACTION_UP:
			    case MotionEvent.ACTION_CANCEL:
			    	lyt_status.setVisibility(View.GONE);
			    	vp_event_item.requestDisallowInterceptTouchEvent(false);
			        break;
			    }
				return false;
			}});
		   
		  
		   
		   event_item_view_vp.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,SmarterlifeHelper.getPixels(80, ctx.getResources())));
		    
		    vp_event_item.setId(i);
		    i++;
		    EventPagerAdapter eventPagerAdapter  = new EventPagerAdapter(event, getSupportFragmentManager(),getApplicationContext());
		    
		   vp_event_item.setAdapter(eventPagerAdapter); // EventPagerAdapter( getSupportFragmentManager(),getApplicationContext())
		
			//vp_event_item.getLayoutParams().height=60;
			//vp_event_item.setTag(event);
		 	vp_event_item.setCurrentItem(1);
		 	//vp_event_item.setOnLongClickListener(EventTaskLongClickListener);
		 	//vp_event_item.setOnTouchListener(EventTaskTouchListener);
		 	//vp_event_item.setOnPageChangeListener(new EventPagerListener(this,  vp_event_item));
		 	//vp_event_item.setPageMargin(50);
		 	int margin = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10*2,     getResources().getDisplayMetrics());
		 	//vp_event_item.setPageMargin(margin);
		 	//vp_event_item.setPageTransformer(false, new PageTransformer()
//            {
//                @SuppressLint("NewApi")
//                public void transformPage(View v, float pos)
//                {
//                    final float invt = Math.abs(Math.abs(pos) - 1);
//                    v.setAlpha(invt);
//                }
//            });
		 	
		 	
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
		 	 // inflate sub task for event.. 
			  for(int j =0 ; j < 2; j++)
			  {
			   event_item_sub_task_view = inflater.inflate(R.layout.lyt_event_subtask_item,null);
			   LinearLayout  sub_task_event_item =(LinearLayout) event_item_view_vp.findViewById(R.id.lyt_linear_event_subtask_item);
			   lyt_events_lists.addView( event_item_sub_task_view);
			  }
	   
	 
	 
	  
	  //lyt_day_glance.addView(event_item_view_vp);
		
		
    		}
    		catch(Exception ex) {
    		    // probably don't bother doing clean up
    		} finally {
    		    // carry on as if nothing went wrong
    		}
    		
		}
	}
    
    
    
    
    
    
    
    
	public void GetEvents() {
		
		 
		 calendarResult = new ArrayList<CalendarClass>();
		
		     GetCalendarDownload task = new GetCalendarDownload();
		    //http://www.aimsus.com/CalendarService.svc/json/GetEvents?id=1
		     task.execute(new String[] { "http://036d4b1.netsolhost.com/smartlife/calendarservice.svc/json/getevents?id=1" });
		 
		    //Toast.makeText(getApplicationContext(),
		    	//	calendarResult.size() ,
					//Toast.LENGTH_SHORT).show();
		    for(CalendarClass event : calendarResult )
		    {
		    	
		    	Calendar calendarStart = Calendar.getInstance();
		    	calendarStart.setTime(event.EventStartDate);
		    	Calendar calendarEnd = Calendar.getInstance();
		    	calendarEnd.setTime(event.EventEndDate);
		    	
		     	int totalWidthAval = 400;
		    	int totalEventperStartDate = eventStartAry[calendarStart.get(Calendar.HOUR_OF_DAY)]; 
		    	int eventWidth;
		    	int eventHeight;
		    	
		    	if(totalEventperStartDate>1)
		    	 eventWidth =totalWidthAval/ totalEventperStartDate;
		    	else eventWidth = totalWidthAval;
		    	eventHeight = (int)((calendarEnd.getTimeInMillis() -calendarStart.getTimeInMillis())/1000)/60; 
		    	//if(eventHrNow == calendarStart.get(Calendar.HOUR_OF_DAY))
		    	//{
		    	//	paddingEvent = paddingEvent + eventWidth;
		    	//}
		    	FrameLayout.LayoutParams fLayoutParams = new FrameLayout.LayoutParams(
		    			eventWidth, eventHeight);
		    	Button b = new Button(this);
				b.setText(event.EventName);
				b.setLayoutParams(fLayoutParams);
				b.setId(event.Eventid);
				b.setX(calendarStart.get(Calendar.HOUR_OF_DAY)*2);
				b.setY(paddingEvent);
				lyt_day_glance.addView(b);
				
		    	
		    }
		    
		
//		for (int i = 1330; i < 1300; i++) {
//			i = i + 40;
//			FrameLayout.LayoutParams fLayoutParams = new FrameLayout.LayoutParams(
//					200, 200);
//			Button b = new Button(this);
//			b.setText("Button added dynamically!");
//			b.setLayoutParams(fLayoutParams);
//			b.setId(i);
//			b.setX(i);
//			b.setY(i);
//			root.addView(b);
//			int color;
//			Random rnd = new Random();
//			color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256),
//					rnd.nextInt(256));
//			b.setBackgroundColor(color);
//			b.setOnClickListener(OnPopupOpen);
//		}

	}


	
	  private static class GetCalendarDownload extends AsyncTask<String, Void, String> {
		    @Override
		    protected String doInBackground(String... urls) {
		    	
		       	    	
		      String response = "";
		      for (String url : urls) {
		        DefaultHttpClient client = new DefaultHttpClient();
		        HttpGet httpGet = new HttpGet(url);
		        try {
		          HttpResponse execute = client.execute(httpGet);
		          InputStream content = execute.getEntity().getContent();

		          BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
		          String s = "";
		          while ((s = buffer.readLine()) != null) {
		            response += s;
		          }

		        } catch (Exception e) {
		          e.printStackTrace();
		        }
		      }
		      return response;
		    }

		 
			@Override
		    protected void onPostExecute(String result) {
		      //textView.setText(result);
		    	 calendarResult = new ArrayList<CalendarClass>();
		    	String  eventDateStart = "";
		    	String  eventDateEnd = "";
		    	
		    	
		    	Date date = null; 
		    	SimpleDateFormat fmt = null;
		    	
		    	 Calendar calendar = Calendar.getInstance(); // creates a new calendar instance
			try {
				// this will break the JSON messages into an array
				JSONArray aryJSONStrings = new JSONArray(result);
				for (int i = 0; i < aryJSONStrings.length(); i++) {
					CalendarClass calendarData = new CalendarClass();
					eventDateStart = (aryJSONStrings.getJSONObject(i)
							.getString("EventStartDate")).replace("\\", "")
							.trim();
					eventDateEnd = (aryJSONStrings.getJSONObject(i)
							.getString("EventEndDate")).replace("\\", "")
							.trim();

					fmt = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");
					try {
						date = fmt.parse(eventDateStart);
					} catch (ParseException e) {
						Toast.makeText(ctx, e.toString() +"  "+eventDateStart,
								Toast.LENGTH_LONG).show();
					}
					if(date != null )
					{
					//Toast.makeText(getApplicationContext(), eventDateStart, Toast.LENGTH_LONG).show();
					calendar.setTime(date);
					int eventStarthr = calendar.get(Calendar.HOUR_OF_DAY);
					eventStartAry[eventStarthr] = eventStartAry[eventStarthr] + 1;
					calendarData.EventPerhr = eventStartAry[eventStarthr];
					calendarData.CalendarId = aryJSONStrings.getJSONObject(i)
							.getInt("CalendarID");
					calendarData.EventData = aryJSONStrings.getJSONObject(i)
							.getString("EventData");
					calendarData.EventName = aryJSONStrings.getJSONObject(i)
							.getString("EventName");
					calendarData.setEventStartDate(eventDateStart);
					calendarData.setEventEndDate(eventDateEnd);
					calendarResult.add(calendarData);
					
					calendarData.Eventid = aryJSONStrings.getJSONObject(i)
							.getInt("EventId");
					
					}
				}

			} catch (JSONException e) {
				Log.e("json", e.toString());
				Toast.makeText(ctx, e.toString() + " "+ eventDateStart,
						Toast.LENGTH_LONG).show();
			}
		    	
		    
		    	
		BuildEvents(calendarResult, eventPaddingSize);
		               //Toast.makeText(getApplicationContext(),calendarResult.size()+ "-----"+ "-------"+ result, RESULT_OK).show();
		    }

            public static  void  BuildEvents(ArrayList<CalendarClass> calendarResult, int eventPaddingSize)
            {  // TableRow imgtest = (TableRow)findViewById(R.id.tRow12am);
            	//Toast.makeText(getApplicationContext(), imgtest.getX() + " X " + imgtest.getY(), RESULT_OK).show();
            	paddingEvent= 50;
            	Context context = ctx;
            	final Context context1 = ctx;
            	 //Toast.makeText(getApplicationContext(),calendarResult.size()+ "-----"+ "-------", RESULT_OK).show();
           
            	
            	long LongestEventHeight = 0;
            	int LongestEventId=0;
            	
            	
            	long earliestEvent =calendarResult.get(0).EventStartDate.getTime();
            	
            	long latestEvent = calendarResult.get(0).EventEndDate.getTime();
            	
            	 
            	CalendarClass LongestEvent = new CalendarClass();
            	ArrayList<CalendarClass> OrderedEvent = new ArrayList<CalendarClass>();
            	final ArrayList<CalendarClass> DrawnEvents = new ArrayList<CalendarClass>();
            	final ArrayList<CalendarClass> Dragedevents = new ArrayList<CalendarClass>();
            	DrawnEvents.removeAll(DrawnEvents);
            	CalendarClass DrawnEvent = null;
            	 final CalendarClass Dragedevent;
            	 
            	DrawnEvents.clear();
            	//OrderedEvent.clear();
            	for(CalendarClass event1 : calendarResult )
    		    {   event1.ConcurrrentEventIds.clear();
            		Calendar calendarStart = Calendar.getInstance();
    		    	calendarStart.setTime(event1.EventStartDate);
    		    	Calendar calendarEnd = Calendar.getInstance();
    		    	calendarEnd.setTime(event1.EventEndDate);
    		    	long longestEventCheck= (calendarEnd.get(Calendar.HOUR_OF_DAY)*60+ calendarEnd.get(Calendar.MINUTE)) - (calendarStart.get(Calendar.HOUR_OF_DAY)*60+ calendarStart.get(Calendar.MINUTE));
    		    	event1.EventHeight = longestEventCheck;
    		    	CalendarClass e0 = event1;
    		    	for(CalendarClass e : calendarResult)
    		    	{ 
    		    		if( ( e0!= e))
    		    		if(  ( e.EventStartDate.getTime() >= e0.EventStartDate.getTime() &&  e.EventStartDate.getTime() <= e0.EventEndDate.getTime()   ) ||
    		    			 ( e.EventEndDate.getTime()<= e0.EventEndDate.getTime()      &&  e.EventEndDate.getTime()   >= e0.EventStartDate.getTime() ) ||
    		    			 ( e.EventStartDate.getTime() >=e0.EventStartDate.getTime()	 &&  e.EventStartDate.getTime() <= e0.EventEndDate.getTime()  && e.EventEndDate.getTime()>= e0.EventStartDate.getTime() && e.EventEndDate.getTime() <=e0.EventEndDate.getTime() )||
    		    			 ( e.EventStartDate.getTime() <= e0.EventStartDate.getTime()  &&  e.EventEndDate.getTime()   >= e0.EventEndDate.getTime()   )																								
    		    			  	
    		    		  )
    		    		{
    		    			// if one of the condition .. then concurrent--events 
    		    		int i =0;	
    		    			i++;
    		    			
    		    			e0.ConcurrrentEventIds.add(e.Eventid);	
    		    			
    		    		}
    		    
    		    		
    		    	}
    		    	event1 = e0;
    		    	
    		    	if( latestEvent < event1.EventEndDate.getTime())
    		    	{
    		    		 latestEvent = event1.EventEndDate.getTime();
    		    	}
    		    	
    		    	if(earliestEvent >event1.EventStartDate.getTime())
    		    	{
    		    		earliestEvent = event1.EventStartDate.getTime();	
    		    	}
    		    	
    		    	if(LongestEventHeight < longestEventCheck )
    		    	{   
    		    		//OrderedEvent.add(event1);
    		    	    //calendarResult.remove(event1);
    		    	    LongestEvent = event1;
    		    	    LongestEventHeight= longestEventCheck;
    		    		LongestEventId = event1.Eventid;
    		    	}
    		    }
            
            	Calendar startCal = Calendar.getInstance(); startCal.setTime(new Date(earliestEvent));
           	 
            	Calendar endCal = Calendar.getInstance(); endCal.setTime(new Date(latestEvent));
            	int margin =0;
            	
            	Activity activity = (Activity) ctx;
        		 // Inflate the layout.xml
            	int totalTime =  (endCal.get(Calendar.HOUR_OF_DAY)-startCal.get(Calendar.HOUR_OF_DAY) )+ 1;
            	
            	 Resources r = ctx.getResources() ;
            	LinearLayout.LayoutParams frameParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,totalTime*eventPaddingSize );
            	
            	frameParams.setMargins(0, SmarterlifeHelper.getPixels(20, r), 0,  SmarterlifeHelper.getPixels(30, r));
            	lyt_day_glance.setLayoutParams(frameParams);
            	
            	
            	
            	 txtstatus.setText(" Earliest event: " +startCal.getTime() + " Latest Event: "+ endCal.getTime() + " diff "+ (endCal.get(Calendar.HOUR_OF_DAY)-startCal.get(Calendar.HOUR_OF_DAY))   );
            	int startTimeLine =startCal.get(Calendar.HOUR_OF_DAY);
            	int endTimeLine =  endCal.get(Calendar.HOUR_OF_DAY);
        	    for(int i = startTimeLine ; i <= endTimeLine;i ++ )
        	    {
	            	RelativeLayout viewGroup = (RelativeLayout)activity. findViewById(R.id.lyt_rel_timeline);
	        	    
	        	    LayoutInflater layoutInflater = (LayoutInflater) activity
	        	      .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        	    View layout = layoutInflater.inflate(R.layout.lyt_timeline, viewGroup);
	        	    
	        	    RelativeLayout LytTimeLine = (RelativeLayout)layout.findViewById(R.id.lyt_rel_timeline);
	        	    LytTimeLine.setOnDragListener(EventTaskDrag_day);
	        	    TextView txtTimeLine = (TextView)layout.findViewById(R.id.txt_day_time);
	        	    txtTimeLine.setText(startCal.get(Calendar.HOUR_OF_DAY) + " "+( startCal.get(Calendar.AM_PM)==0?"am":"pm" )  );
	        	    TimeLine time = new TimeLine();
	        	    time.Hours = startCal.get(Calendar.HOUR_OF_DAY);
	        	    time.Minutes = startCal.get(Calendar.MINUTE);
	        	    time.AmPm =  startCal.get(Calendar.AM_PM)==0?"am":"pm";
	        	    time.MarginTop = margin;
                    txtTimeLine.setTag(time);
	        	    layout.setY(margin);
	        	    layout.setId(margin); 
	        	   
	        	   lyt_day_glance.addView(layout);
	        	    margin = margin+eventPaddingSize;
	        	    startCal.add(Calendar.HOUR, 1);
        	    }
            	
            	
           	
//            	// Now we have Longest Event... time and Id
//            	// Loop through the Events to get all concurrent events starting or ending.. 
            	int index = 0;
            	
            	ArrayList<CalendarClass> builtEvents = new ArrayList<CalendarClass>();
            	
            	
            	for(CalendarClass event : calendarResult )
    		    {
            		
            		
            		
            	
            	
            		
            		Calendar calendarStart = Calendar.getInstance();
    		    	calendarStart.setTime(event.EventStartDate);
    		    	Calendar calendarEnd = Calendar.getInstance();
    		    	calendarEnd.setTime(event.EventEndDate);
    		    	
    		    	Calendar calendarStartLongest = Calendar.getInstance();
    		    	calendarStartLongest.setTime(LongestEvent.EventStartDate);
    		    	Calendar calendarEndLongest = Calendar.getInstance();
    		    	calendarEndLongest.setTime(LongestEvent.EventEndDate);
    		    	
    		    	
            	
    		      }
                  
            	
            	
            	
            	// Now Lets build Concurrent Events..
            	
			if (calendarResult.size() > 0) {
				// build concurrent event
				     


				for (CalendarClass event : calendarResult) {   //OrderedEvent

					Calendar calendarStart = Calendar.getInstance();
					calendarStart.setTime(event.EventStartDate);
					Calendar calendarEnd = Calendar.getInstance();
					calendarEnd.setTime(event.EventEndDate);


					float eventWidth = 0;
					float eventHeight;
					int rowHeights;

					rowHeights = (int) TypedValue.applyDimension(
							TypedValue.COMPLEX_UNIT_DIP, 120 + 16 + 1 + 16 + 1,
							resources.getDisplayMetrics()); // 120 dp row
																	// heights
					//if (totalEventperStartDate > 1) 
					
						eventWidth =     (screenwidth-  SmarterlifeHelper.getPixels(50,ctx.getResources()) )/(event.ConcurrrentEventIds.size() +1) ;          //totalWidthAval / OrderedEvent.size(); // ? should fix 

					  // now let's get the x location of the event...
						float EventsBuiltSize = 0 ; 
						for(CalendarClass be :builtEvents)
							 for(int cId : event.ConcurrrentEventIds)
								 if(cId == be.Eventid)
									 EventsBuiltSize= EventsBuiltSize +be.EventWidth ;
						
					
						
						
						event.EventWidth = eventWidth;
						builtEvents.add(event);
				
					eventHeight = (calendarEnd.get(Calendar.HOUR_OF_DAY) - calendarStart
							.get(Calendar.HOUR_OF_DAY)) 
							+ (float) (calendarEnd.get(Calendar.MINUTE) - calendarStart.get(Calendar.MINUTE))/60 ;
					
			
					
					eventHeight =  SmarterlifeHelper.getPixels(eventHeight*eventPaddingSize, r);
					event.EventHeight = eventHeight;

					int tm = calendarStart.get(Calendar.MINUTE)/60;
					
					float eventPaddingTop = ((calendarStart.get(Calendar.HOUR_OF_DAY) + (float)calendarStart.get(Calendar.MINUTE)/60)- startTimeLine )*eventPaddingSize;
					eventPaddingTop = SmarterlifeHelper.getPixels(eventPaddingTop, r);
					
					final FrameLayout.LayoutParams fLayoutParams = new FrameLayout.LayoutParams(
							(int)eventWidth, (int)eventHeight );

					
					final TextView b = new TextView(context);
					final RelativeLayout el = new RelativeLayout(context);
					final TextView txtDel = new TextView(context);
					final TextView txtTasks = new TextView(context);
					final Button txtResize = new Button(context);
					
					
					txtDel.setText("X");
					txtDel.setTextColor(Color.RED);
					txtDel.setTextSize(16);
					RelativeLayout.LayoutParams btnDelParams  = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
					btnDelParams.setMargins(1, 1, 5, 1);
					btnDelParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, 1);
					btnDelParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, 1);
					txtDel.setLayoutParams(btnDelParams);
					
					txtTasks.setText("Tasks: 3");
					RelativeLayout.LayoutParams txtTasksParams  = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
					txtTasksParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, 1);
					txtTasksParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, 1);
					txtTasksParams.setMargins(3, 1, 1, 3);
					txtTasks.setLayoutParams(txtTasksParams);
					
					
					txtResize.setBackgroundResource(drawable.bk_event_day_drag_selector);
					
					txtResize.setTextSize(20);
					RelativeLayout.LayoutParams txtResizeParams  = new RelativeLayout.LayoutParams(30, 20);
					//txtResize.setBackgroundColor(Color.RED);
					txtResizeParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, 1);
					txtResizeParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, 1);
					txtResizeParams.setMargins(1, 1, 0, 0);
					txtResize.setLayoutParams(txtResizeParams);
					txtResize.setOnLongClickListener(EventTaskResizeLongClickListener);
					
					
					el.setBackgroundResource(drawable.bk_event_day_selector);
					el.setTag(event);
					//el.setBackgroundColor(0xcc5b9ed9);
					b.setText(event.EventName);
					//fLayoutParams.setMargins(1, 1, 1, 1);
					el.setLayoutParams(fLayoutParams);
					el.setId(event.Eventid);
                    
                    b.setPadding(10, 10, 10, 10);
                    
					el.setY(eventPaddingTop); // 16 from textview
					//b.setX(paddingEvent);
					//if(Xpadding==0) Xpadding = 120;
					el.setX( (EventsBuiltSize + SmarterlifeHelper.getPixels(50,ctx.getResources()) ));   //Xpadding
//			        b.setBackgroundResource(R.drawable.bluebtn);
			        b.setTypeface(Typeface.DEFAULT_BOLD);
			        b.setTextSize(TypedValue.COMPLEX_UNIT_SP,15);
			     //   b.setOnClickListener(OnPopupOpen);
					b.setGravity(Gravity.TOP);
					
					el.addView(b);
					el.addView(txtDel);
					el.addView(txtTasks);
					el.addView(txtResize);
					lyt_day_glance.addView(el);
					
					
					
					el.setOnLongClickListener(EventTaskLongClickListener);
					
				//	event.Xlayout =  Xpadding;
					
					
					el.setDrawingCacheEnabled(true);
					
					   final int YY;
					
					
					
 
				}
			

			}
				
				
            }
           
          
	       
		  }

	
    

	 private  String[] monthslist = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"}; 
		 //{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	 

	 public void onResume() {
		  super.onResume();
		  
		
		    

		  
	 }
	 
	 
	 private static final ScheduledExecutorService Refreshactivityworker = 
			  Executors.newSingleThreadScheduledExecutor();
	
	 public void onRestart() {
		
		 super.onRestart();// TODO Auto-generated method stub
			
			  
			 
			Runnable task = new Runnable() {
			    public void run() {
			    
			    	Intent intent = new Intent();  //your class
				        intent.setClassName( getApplicationContext(), this.getClass().getName());
				      
				    
					    
			 	    startActivity(intent);
			 	    finish();
			    }
			  };
			  
			 
			  
			  Refreshactivityworker.schedule(task, 3, TimeUnit.SECONDS);
		} 
	 
	 
	 
	 
	 
	 
	 
	 
    





private static OnLongClickListener EventTaskLongClickListener = new OnLongClickListener() {

	@Override
	public boolean onLongClick(View v) {
		
		
		v.setOnTouchListener(EventTaskTouchListener);
		
		return false;
	}

};


private static OnLongClickListener EventTaskResizeLongClickListener = new OnLongClickListener() {

	@Override
	public boolean onLongClick(View v) {
		
		
		v.setOnTouchListener(EventTaskResizeTouchListener);
		
		return false;
	}

};

private static OnTouchListener EventTaskResizeTouchListener = new OnTouchListener() {

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


private static OnTouchListener EventTaskTouchListener = new OnTouchListener() {

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



private static   OnDragListener EventTaskDrag_day = new OnDragListener(){

	@Override
    public boolean onDrag(View v, DragEvent event) {
         
		//Activity activity = (Activity) getApplicationContext();
    	 int  vWidth = v.getWidth();
    	 int vHeights = v.getHeight();
    	// Drawable enterShape = getResources().getDrawable(R.drawable.shape_droptarget);
		//    Drawable normalShape = getResources().getDrawable(R.drawable.shape);
		  float vTop = v.getY();
//		
//    	 RelativeLayout viewGroup = (RelativeLayout)activity. findViewById(R.id.lyt_monthcalendar_status);
// 	    
// 	    LayoutInflater layoutInflater = (LayoutInflater) activity
// 	      .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
// 	    View layout = layoutInflater.inflate(R.layout.lyt_calendardrag_status, viewGroup);
    	 
		   View view = (View) event.getLocalState();
		  
    	 
    	 lyt_status =   (RelativeLayout)activity.findViewById(R.id.lyt_monthcalendar_status);//
		    
		      lyt_undo_save = (LinearLayout)activity.findViewById(R.id.lyt_monthcalendar_undo_save);
		    
		    ImageButton img_undo = (ImageButton)lyt_undo_save.findViewById(R.id.btn_undo);
		     
		   img_undo.setOnClickListener(Event_Task_Darg_Undo);
		   
		    
		    lyt_status.bringToFront();
		   lyt_status.setVisibility(View.VISIBLE);
	          TextView txtdragstatus = (TextView)  lyt_status.findViewById(R.id.txt_monthcalendar_status);
	          
	          RelativeLayout container = (RelativeLayout) v;
	         
	        
	           
		        TextView txtTimeLineTime = (TextView)container.getChildAt(0);
		        
		        String timeMoving = txtTimeLineTime.getText().toString();
		      
		        TimeLine time = (TimeLine)txtTimeLineTime.getTag();
		        
		        int TimeLineHr = time.Hours;
		        
		        String AmPm = time.AmPm;
		        
		        dragYLoc = event.getY();
		        
		        
		        float dragXLoc = event.getX();
		        
		        
		        int TimeLineMinute = ((int)(( dragYLoc*(60))/eventPaddingSize));
		        if(!eventDroped)
		        txtdragstatus.setText( TimeLineHr + ":"+ TimeLineMinute + " "+ AmPm   );
		        RelativeLayout viewParentLyt = null;
		        if(view instanceof Button && !eventDroped)
		        {
		        	 ViewGroup viewParent = (ViewGroup) view.getParent();
		        	 viewParentLyt = (RelativeLayout)view.getParent();
		        	 
		        	 CalendarClass eventSize = (CalendarClass) viewParent.getTag();	
		        	 Calendar calendarStart = Calendar.getInstance();
			         calendarStart.setTime(eventSize.EventStartDate);
			         
			         Calendar calendarEnd = Calendar.getInstance();
						//calendarEnd.setTime(eventSize.EventEndDate);
			         calendarEnd.set(Calendar.HOUR_OF_DAY, TimeLineHr);
			         calendarEnd.set(Calendar.MINUTE, TimeLineMinute);
			         
			         txtdragstatus.setText(    calendarStart.get(Calendar.HOUR_OF_DAY) + ":"+calendarStart.get(Calendar.MINUTE) + " - "+ TimeLineHr + ":"+ TimeLineMinute + " "+ AmPm); 
			     	
			         
			     	eventMovingHeight = (calendarEnd.get(Calendar.HOUR_OF_DAY) - calendarStart
							.get(Calendar.HOUR_OF_DAY)) 
							+ (float) (calendarEnd.get(Calendar.MINUTE) - calendarStart.get(Calendar.MINUTE))/60 ;
			     	eventMovingHeight =  SmarterlifeHelper.getPixels(eventMovingHeight*eventPaddingSize, ctx.getResources());
			     	
			     	Toast.makeText(ctx, eventMovingWidth + " ", Toast.LENGTH_SHORT).show();
			     	
			    	 fLayoutParams = new FrameLayout.LayoutParams(
			     			viewParentLyt.getWidth(), (int)eventMovingHeight  );
			    	viewParentLyt.setLayoutParams(fLayoutParams);
			        
			         
		        }
		        
		        
    	
    	int action = event.getAction();
      switch (event.getAction()) {
      case DragEvent.ACTION_DRAG_STARTED:
    	
        break;
      case DragEvent.ACTION_DRAG_ENTERED:
    	
    	  eventDroped = false;
    	  
    	  //  day_tag  = (Day_tag)btnDay.getTag();	
    	 
    	 
    	// txtdragstatus.setText(timeMoving   );
    	  //txtdragstatus.setText(String.valueOf(day_tag.Day)+ "/"+String.valueOf(day_tag.Month) +"/" +String.valueOf(day_tag.Year) + " Events: "+String.valueOf(day_tag.EventCount) );
    	  lyt_undo_save.setVisibility(View.GONE);
    	 
    	  lyt_status.setVisibility(View.VISIBLE); 
    	  container.setBackgroundColor(Color.RED);
    	  
         //Toastmsg("Text  "+btnDay.getText());
        break;
      case DragEvent.ACTION_DRAG_EXITED:
    	  dragYLoc =0;
    	 v.setBackgroundColor(0XEEEEEEEE);
    	 txtdragstatus.setText("");
         lyt_status.setVisibility(View.GONE);
    	  
        break;
      case DragEvent.ACTION_DROP:
    	  eventDroped = true;
    	//  lyt_status.setVisibility(View.GONE);
    	//  txtdragstatus.getLayoutParams().height= 45;
    	    lyt_status.setVisibility(View.VISIBLE);
    	    lyt_undo_save.setVisibility(View.VISIBLE);
    	  
        // Dropped, reassign View to ViewGroup
         view = (View) event.getLocalState();
        ViewGroup owner = (ViewGroup) v.getParent();
       
        if(view instanceof RelativeLayout)
        {
        
        RelativeLayout lytEvent = (RelativeLayout)view;
        CalendarClass event_mov = (CalendarClass) lytEvent.getTag();
        TextView txtEvent_mov = (TextView)lytEvent.getChildAt(0);
        //view.setVisibility(View.GONE);
        txtdragstatus.setText("Move " +event_mov.EventName + "  to  " +  TimeLineHr + ":"+ TimeLineMinute + " "+ AmPm  );
        
        view.setY(time.MarginTop + dragYLoc );
         view.setX(  dragXLoc);
        //  owner.addView(b);
      //  owner.removeView(view);
        
       // txtEvent.setText(String.valueOf( Integer.parseInt(txtEvent.getText().toString()) + 1));
        //container.addView(view);
        view.setVisibility(View.VISIBLE);
       
        container.setBackgroundResource(R.drawable.shape_droptarget);
        }
        
        else 
        	
        {
        	
       	 fLayoutParams = new FrameLayout.LayoutParams(
	     			viewParentLyt.getWidth(), (int)eventMovingHeight  );
	    	viewParentLyt.setLayoutParams(fLayoutParams);
        	
        //	viewParentLyt.setLayoutParams(fLayoutParams);
//        	  ViewGroup viewParent = (ViewGroup) view.getParent();
        	 // CalendarClass eventSize = (CalendarClass) viewParent.getTag();	
        	//  Calendar calendarStart = Calendar.getInstance();
				//calendarStart.setTime(eventSize.EventStartDate);
				
        	  
        	//Toast.makeText(ctx, calendarStart.getTime().toString(), Toast.LENGTH_SHORT).show();
        	
        }
        
        dragYLoc =0;
        
        break;
      case DragEvent.ACTION_DRAG_ENDED:
    	  container.setBackgroundColor(0XEEEEEEEE);
    	
    	  
      default:
        break;
      }
      return true;
    }
	
	
};

private static View.OnClickListener Event_Task_Darg_Undo = new View.OnClickListener() {
 	
	@Override
	public void onClick(View v) {
		helper.Toastmsg("Undo..",activity);
		 lyt_status.setVisibility(View.GONE);
		 lyt_undo_save.setVisibility(View.GONE);
		
	}
	};


	private class GetDataTask extends AsyncTask<Void, Void, String[]> {

		@Override
		protected String[] doInBackground(Void... params) {
			// Simulates a background job.
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
			}
			return null;
		}

		@Override
		protected void onPostExecute(String[] result) {
			// Do some stuff here
			 GetEvents();
			// Call onRefreshComplete when the list has been refreshed.
			lyt_scroll_timeline.onRefreshComplete();
         
			super.onPostExecute(result);
		}
	}


	@Override
	public void onClick(View v) {
		 populateEvents( calendarResult);
		
	}

}
