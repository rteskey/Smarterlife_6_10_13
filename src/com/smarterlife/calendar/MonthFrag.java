package com.smarterlife.calendar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;




import android.R.drawable;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableLayout.LayoutParams;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import apphelper.SmarterlifeHelper;
 
public class MonthFrag extends Fragment implements OnItemSelectedListener{
	SmarterlifeHelper helper ;
	
	int MonthDisplay;
   int mCurrentPage;
   int currentMonth;
   private static  Context c;
   private int ScreenWidth,  ScreenHeights, DayWidth;
   private Boolean fullWeek;
   
  private  View CalendarView; 
  
	 private ArrayList<String> listCountry;
	 private ArrayList<Integer> listFlag;
	 
	 private GridView gridView;
      int Month, Year,cMonth; 
      boolean monthSelected = false;
      private   Spinner title_month;
      private Spinner title_year;
      String spinnerMonth;
	private int spinnerYear;
	private int  screenwidth, screenheight; 
    private static  Day_tag day_tag;
    private RelativeLayout lyt_status;
    private  LinearLayout lyt_undo_save;
    private ImageButton img_undo ;
    private static  long  CalendarInMill;
 
    private   static Calendar  Cal = Calendar.getInstance();
//   public MonthFrag(Context context) {
////		// TODO Auto-generated constructor stub
////    	c= context;
////    	//Month = month;
////    	//Year = year;
//	}

    public MonthFrag (){
		// TODO Auto-generated constructor stub
    	
	}
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper = new SmarterlifeHelper(getActivity());
    	
        /** Getting the arguments to the Bundle object */
        Bundle data = getArguments();
 
        /** Getting integer data of the key current_page from the bundle */
       // mCurrentPage = data.getInt("current_page_month", 0);
        Month =  data.getInt("month", 0);
        Month = Month -100;
        
      CalendarInMill =  data.getLong("CalendarInMill", 0);
       
        int arg0 = data.getInt("arg0", 0);
        // MonthDisplay=  data.getInt("month_now", 0);
       
        Cal.setTimeInMillis(CalendarInMill);
      
        cMonth = (currentMonth ) ;
       
//        
         DisplayMetrics metrics = this.getResources().getDisplayMetrics();
         screenwidth  = metrics.widthPixels;
         screenheight =metrics.heightPixels;
         
         if(screenheight <screenwidth )
        	 screenwidth =screenheight;
    }
	
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.month_frag_layout, container,false);
        CalendarView = v;
        
		Integer[] yearlist = { 2011, 2012, 2013, 2014, 2015, 2016, 2017, 2018,
				2019, 2020, 2021, 2022, 2023, 2024, 2025, 2026, 2027, 2028,
				2029, 2030 };

        PopulateCalendar(v);
        
   
       
       Bundle data = getArguments();
         MonthDisplay=  data.getInt("month_now", 0);
        
//      

        
        return   v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
       c=activity.getApplicationContext();
    }
    
	 // @SuppressLint("NewApi")
	
	private void PopulateCalendar(View v) {
	
		
		Calendar  calendar=  Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR,   (int) (CalendarInMill / (1000*60*60*24)) );
	//	calendar =Cal;
		calendar.add(Calendar.MONTH, Month);
		//calendar.set(Calendar.MONTH, cMonth);
		Calendar currentCalendar = Calendar.getInstance();

		Calendar.getInstance();
		GregorianCalendar gCal =  new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1 )  ;// (calendar.get(Calendar.DAY_OF_MONTH))+(cWeek)    //(2013, Calendar.JANUARY, 1);
		
		//Calendar gCal = Cal;
		
		//this gets the day of week range 1-7, Sunday - Saturday
        Date currentDate = gCal.getTime();
      //  gCal.setTime(currentDate);
		int currentDay = gCal.get(Calendar.DAY_OF_WEEK);
        //backtracks to the beginning of current week (Sunday)
		final int backtrackdays =gCal.get(Calendar.SUNDAY) - currentDay;
        gCal.add(Calendar.DAY_OF_YEAR, Calendar.SUNDAY - currentDay);
		
		//Toast.makeText(c.getApplicationContext(),"backtrackdays: "+ backtrackdays  + "currentDay:  "+ currentDay, Toast.LENGTH_SHORT).show();
        SimpleDateFormat month_date = new SimpleDateFormat("MMMMMMMMMM");
        SimpleDateFormat year_date = new SimpleDateFormat("yyyy");
       // wDays[0] = month_date.format(gCal.getTime());
        String weekEndingMth = "";
        try {
       // wDate[0] =  Integer.parseInt(year_date.format(gCal.getTime())) ;
        }
        catch(NumberFormatException nfe) {
        	   System.out.println("Could not parse " + nfe);
        	} 
        String month=month_date.format(calendar.getTime())+"  " + calendar.get(Calendar.MONTH) +" "+ calendar.get(Calendar.YEAR);
        int yearindex =  calendar.get(Calendar.YEAR);
        for(int i = 0 ; i< yearlist.length ;i++)
        if(yearindex ==yearlist[i] )
        	yearindex=i;   
        
        
        
     
        LinearLayout RL =   (LinearLayout)v.findViewById(R.id.monthcalendarLayout);
        LinearLayout.LayoutParams rlParams =new LinearLayout.LayoutParams((screenwidth - helper.getPixels(6,getResources())), android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
      //  RL.setGravity(Gravity.CENTER_HORIZONTAL);
        // rl.alignWithParent= true;
       
        
        TableLayout table = new TableLayout(c);  
        table.setStretchAllColumns(true);  
        table.setShrinkAllColumns(true);  
        
        TableRow rowTitle = new TableRow(c);  
       // rowTitle.setGravity(Gravity.CENTER_HORIZONTAL);  
        TableRow rowDayLabels = new TableRow(c); 
     // title column/row  
        TableRow.LayoutParams paramsMonth = new TableRow.LayoutParams();  
        paramsMonth.span = 3;
        TableRow.LayoutParams paramsYear = new TableRow.LayoutParams();  
        paramsYear.span = 2;
         title_month = new Spinner(c);
   
        
         title_month.setAdapter(new ArrayAdapter<String>(c,
        		android.R.layout.simple_list_item_checked, monthslist));
        //title_month.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);  
       // title_month.setGravity(Gravity.LEFT);  
        boolean monthSelected = false;
     
        	title_month.setSelection(calendar.get(Calendar.MONTH));
        
        	//title_month.setOnItemSelectedListener(this);
            spinnerMonth =title_month.getSelectedItem().toString();
        	
     
          // Toast.makeText(c.getApplicationContext(),title_month.getChildCount()+"v.toString()", Toast.LENGTH_SHORT).show();	
        title_month.setBackgroundResource(drawable.btn_dialog);
      //  title_month.setOnTouchListener(Spinner_OnTouch);
        title_month.setOnKeyListener(Spinner_OnKey);
        
        View viewItem = new View(c);
          for(int i = 0 ; i < title_month.getChildCount(); i++)
        	  {
        	    viewItem =  title_month.getChildAt(i);
        	   	
        	    viewItem.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						 Toast.makeText(c.getApplicationContext(),"v.toString()", Toast.LENGTH_SHORT).show();
					}
				});
        
        	  }
     
          
             TableLayout.LayoutParams tableRowParams=
        		  new TableLayout.LayoutParams
        		  (android.view.ViewGroup.LayoutParams.MATCH_PARENT,android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
          
             tableRowParams.setMargins(0, 0, 0, 0);
          

          TableRow.LayoutParams dayTitleParams = new TableRow.LayoutParams((screenwidth - helper.getPixels(4,getResources()))/7, android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
         // dayTitleParams.gravity = Gravity.CENTER;
          dayTitleParams.setMargins(0, 2, 0, 2);
        table.addView(rowTitle);
        
        // Days Row 
        TableRow daysNameRow = new TableRow(c);
    	for(int i = 0; i< dayslist.length; i++)
		{// add Days Name Row..
			TextView daysTxt = new TextView(c);
			daysTxt.setBackgroundColor(0X99c3e7e7);
			
			daysTxt.setTypeface(Typeface.DEFAULT_BOLD);
		//	daysTxt.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
			daysTxt.setTextColor(0Xeeeeeeee);
			daysTxt.setTextSize(TypedValue.COMPLEX_UNIT_SP , 18);
			daysTxt.setText(dayslist[i]);
			daysTxt.setGravity(Gravity.CENTER_HORIZONTAL);
		//	daysTxt.setGravity(Gravity.CENTER);
			daysNameRow.addView(daysTxt,dayTitleParams);
			
	
		}
    	table.addView(daysNameRow);
        
    	int monthBegin= 0;
    	final int monthEnd=gCal.getActualMaximum(Calendar.DAY_OF_MONTH);
    
    	   boolean before = true ;
    	  
    	   TableRow.LayoutParams RL_dayParams =new TableRow.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, android.view.ViewGroup.LayoutParams.MATCH_PARENT);
    	 RL_dayParams.setMargins(0, 2, 0, 2);
    	   
    	   RelativeLayout.LayoutParams dayParams = new RelativeLayout.LayoutParams((screenwidth - helper.getPixels(6,getResources()))/7, 60);
    	 
    	   
    	 
    	   
        int gridSizeX = 7, gridSizeY = 6;
        for (int i = 0; i < gridSizeY; i++)
        {    TableRow rowDays = new TableRow(c);  
        	// TableRow.LayoutParams TblRowParams = new TableRow.LayoutParams();
        final int ii = i;	
        for (int j = 0; j < gridSizeX; j++)
            {  
        	
              day_tag = new Day_tag();
        	 
             final int jj = j;
            		 
               RelativeLayout RL_day = new RelativeLayout(c);
               
             //  RL_day.setLayoutParams(RL_dayParams);
            	final Button daybtn = new Button(c);
            	//daybtn.setGravity(Gravity.CENTER);
            	daybtn.setPadding(0, 15, 0, 0);
            	daybtn.setTextColor(0x77111111);
            	Button daybtn_event = new Button(c);
            	daybtn_event.setText(String.valueOf( gCal.get(Calendar.MONTH)));
            	daybtn_event.setTextSize(15);
            	day_tag.EventCount=12;
            	day_tag.Day= gCal.get(Calendar.DAY_OF_MONTH);
            	day_tag.Month =  gCal.get(Calendar.MONTH);
            	day_tag.Year = gCal.get(Calendar.YEAR);
            	
            	daybtn.setText(String.valueOf( gCal.get(Calendar.DAY_OF_MONTH)));
              daybtn.setId( gCal.get(Calendar.MONTH));
              daybtn.setTextSize(TypedValue.COMPLEX_UNIT_SP , 25);
              daybtn.setTag(day_tag);   //gCal.get(Calendar.YEAR)
             // Toast.makeText(c.getApplicationContext(), "M "+ gCal.get(Calendar.MONTH) + " Y "+ gCal.get(Calendar.YEAR) ,Toast.LENGTH_SHORT ).show();
              daybtn.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					Intent intent = new Intent().setClass(c.getApplicationContext(), Cal_monthActivity.class);
					//intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
					intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					//intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
					// intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					int selectedmonth=0;
					int selectedyear = 0;
					selectedmonth = v.getId();
					  selectedyear =(Integer) v.getTag(); //(Integer)title_year.getSelectedItem(); 
//					  if( Integer.parseInt(daybtn.getText().toString().trim()) >1 && jj <= Math.abs( backtrackdays) )
//					  {
//						  
//						  if(title_month.getSelectedItemPosition()== 0)   
//							    selectedmonth = 11;
//						  else selectedmonth = v.getId(); //title_month.getSelectedItemPosition()-1;
//						  selectedyear = (Integer)title_year.getSelectedItem()-1;
//						  
//					  }
//					  else 
//					  {
//						  selectedmonth = title_month.getSelectedItemPosition();
//						  selectedyear = (Integer)title_year.getSelectedItem();
//					  }
//					  
//					 
//					  if(   ((ii*7)+ jj)   >(monthEnd +Math.abs( backtrackdays) -1)    )
//					  {
//						  if(title_month.getSelectedItemPosition()== 11)   
//							    selectedmonth = 0;
//						  else 
//							  selectedmonth = title_month.getSelectedItemPosition();
//						  selectedyear = (Integer)title_year.getSelectedItem();
//					  }
					  intent.putExtra("month",monthslist[selectedmonth] );  // gCal.get(Calendar.MONTH)
				      intent.putExtra("year", selectedyear);
				      intent.putExtra("day", Integer.parseInt(daybtn.getText().toString().trim()) );
				      c.getApplicationContext().startActivity(intent);
					
					
				}
            	  
            	  
              });
             
            RL_day.setOnDragListener( EventTaskDrag);
                
                
              if(currentCalendar.get(Calendar.DAY_OF_MONTH) == gCal.get(Calendar.DAY_OF_MONTH) && cMonth ==1 )
              daybtn.setBackgroundResource(drawable.ic_lock_silent_mode); // .bk_day_btn);
             
              daybtn.setId(101);
              // daybtn.setBackgroundColor(Color.RED);
             // if(currentCalendar.get(Calendar.DAY_OF_MONTH) > gCal.get(Calendar.DAY_OF_MONTH) && cMonth ==0 )
                 daybtn.setBackgroundResource(R.drawable.bk_day_btn);
             // daybtn.setLayoutParams(rl);
              
               RelativeLayout.LayoutParams event_params = new RelativeLayout.LayoutParams(50, 50);
               event_params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
               event_params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
              // event_params.addRule(RelativeLayout.ABOVE);
               //event_params.addRule(Gravity.TOP);
               //event_params.addRule(Gravity.RIGHT);
               daybtn_event.setTextSize(11);
               daybtn_event.setTextColor(Color.BLACK);
               daybtn_event.setBackgroundResource(R.drawable.bk_event);
            
               daybtn_event.setId(100);
               //event_params.setMargins(0, 0, 0, 75);
               daybtn_event.setPadding(30, 0, 0, 30);
               daybtn_event.setTextColor(Color.WHITE);
               daybtn_event.setGravity(Gravity.RIGHT);
               daybtn_event.setGravity(Gravity.TOP);
               RL_day.addView(daybtn,dayParams) ;  // dayParams
                RL_day.addView(daybtn_event,event_params ) ;
                RL_day.setBackgroundColor(0XEEEEEEEE);
                RL_day.setLayoutParams(RL_dayParams);
              rowDays.addView(RL_day);
              rowDays.setBackgroundColor(Color.LTGRAY);
              rowDays.setLayoutParams(tableRowParams);
             // daybtn_event.bringToFront();
              daybtn.bringToFront();
              //rowDays.addView(daybtn_event,event_params );
              
             
             
              
                //fill in your cell with this value
                //System.out.print("Day of month:"+gCal.get(Calendar.DAY_OF_MONTH) + " position: "+position );
               // wDate.add(j,  gCal.get(Calendar.DAY_OF_MONTH));  // wDate[j]
                
                //System.out.print(" ");

                //add one to the day and keep going
                gCal.add(Calendar.DAY_OF_YEAR, 1);
                weekEndingMth = month_date.format(gCal.getTime());
            }
        	table.addView(rowDays);
        	
          // wDate[i]= gCal.get(Calendar.DAY_OF_MONTH);
            System.out.println();
        }
        RL.addView(table,rlParams);
        
        
	}
	


	private View.OnTouchListener Spinner_OnTouch = new View.OnTouchListener() {
 	    @Override
		public boolean onTouch(View v, MotionEvent event) {
 	        if (event.getAction() == MotionEvent.ACTION_UP) {
 	            //doWhatYouWantHere();
 	        	v.setEnabled(true);
 	        	Toast.makeText(c.getApplicationContext(), "Test Touch", Toast.LENGTH_SHORT).show();
 	        }
 	        return true;
 	    

		
		}
 	};
 	private static View.OnKeyListener Spinner_OnKey = new View.OnKeyListener() {
 	    @Override
		public boolean onKey(View v, int keyCode, KeyEvent event) {
 	        if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER) {
 	           // doWhatYouWantHere();
 	        	Toast.makeText(c.getApplicationContext(), "Test onKey", Toast.LENGTH_SHORT).show();
 	            return true;
 	        } else {
 	            return false;
 	        }
 	    }
 	};
	
	
	 private void monthSelected() {
		// TODO Auto-generated method stub
		
	}
	private String[] dayslist = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"   };
	
	 private  String[] monthslist = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    
	private Integer[] yearlist = { 2011, 2012, 2013, 2014, 2015, 2016, 2017,
			2018, 2019, 2020, 2021, 2022, 2023, 2024, 2025, 2026, 2027, 2028,
			2029, 2030 };

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		//if(title_month.getCount()==12 || spinnerMonth != title_month.getSelectedItem().toString() ||  spinnerYear != title_year.getSelectedItemId()  )
			//Toast.makeText(c.getApplicationContext(), "text"+ title_month.getSelectedItem().toString(), Toast.LENGTH_LONG).show();
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	

	
	
	private  OnDragListener EventTaskDrag = new OnDragListener(){

		@Override
	    public boolean onDrag(View v, DragEvent event) {
	         
	    	 int  vWidth = v.getWidth();
	    	 int vHeights = v.getHeight();
	    	 Drawable enterShape = getResources().getDrawable(R.drawable.shape_droptarget);
			    Drawable normalShape = getResources().getDrawable(R.drawable.shape);
			  
			  lyt_status =   (RelativeLayout)getActivity().findViewById(R.id.lyt_monthcalendar_status);
			    
			     lyt_undo_save = (LinearLayout)getActivity().findViewById(R.id.lyt_monthcalendar_undo_save);
			    
			     img_undo = (ImageButton)lyt_undo_save.findViewById(R.id.btn_undo);
			     
			    img_undo.setOnClickListener(Event_Task_Darg_Undo);
			   
			    
			    lyt_status.bringToFront();
			   lyt_status.setVisibility(View.VISIBLE);
		          TextView txtdragstatus = (TextView)  lyt_status.findViewById(R.id.txt_monthcalendar_status);
		          
		          RelativeLayout container = (RelativeLayout) v;
			        Button btnEvent = (Button)container.findViewById(100);
			        Button btnDay = (Button)container.findViewById(101);
			     	        
			      
	    	
	    	int action = event.getAction();
	      switch (event.getAction()) {
	      case DragEvent.ACTION_DRAG_STARTED:
	   
	        break;
	      case DragEvent.ACTION_DRAG_ENTERED:
	    	  day_tag  = (Day_tag)btnDay.getTag();	
	    	 txtdragstatus.setText(Cal_Calendar.getCurrentDate(day_tag.Day,day_tag.Month,day_tag.Year) +  " events: "+String.valueOf(day_tag.EventCount));
	    	  //txtdragstatus.setText(String.valueOf(day_tag.Day)+ "/"+String.valueOf(day_tag.Month) +"/" +String.valueOf(day_tag.Year) + " Events: "+String.valueOf(day_tag.EventCount) );
	    	  lyt_undo_save.setVisibility(View.GONE);
	    	 
	    	  lyt_status.setVisibility(View.VISIBLE);
	    	
	    	  
	         //Toastmsg("Text  "+btnDay.getText());
	        break;
	      case DragEvent.ACTION_DRAG_EXITED:
	    	 
	    	 v.setBackgroundColor(0XEEEEEEEE);
	    	 txtdragstatus.setText("");
	         lyt_status.setVisibility(View.GONE);
	    	  
	        break;
	      case DragEvent.ACTION_DROP:
	    	  txtdragstatus.setText(Cal_Calendar.getCurrentDate(day_tag.Day,day_tag.Month,day_tag.Year) +  " events: "+String.valueOf(day_tag.EventCount));
	    	//  lyt_status.setVisibility(View.GONE);
	    	//  txtdragstatus.getLayoutParams().height= 45;
	    	    lyt_status.setVisibility(View.VISIBLE);
	    	    lyt_undo_save.setVisibility(View.VISIBLE);
	    	  
	        // Dropped, reassign View to ViewGroup
	        View view = (View) event.getLocalState();
	        ViewGroup owner = (ViewGroup) v.getParent();
	      
	        
	       // owner.addView(b);
	       // owner.removeView(view);
	      
	        btnEvent.setText(String.valueOf( Integer.parseInt(btnEvent.getText().toString()) + 1));
	        //container.addView(view);
	        view.setVisibility(View.VISIBLE);
	       
	        v.setBackgroundResource(R.drawable.shape_droptarget);
	    
	        
	        break;
	      case DragEvent.ACTION_DRAG_ENDED:
	       v.setBackgroundColor(0XEEEEEEEE);
	    	
	    	  
	      default:
	        break;
	      }
	      return true;
	    }
		
		
	};
	
	
	private View.OnClickListener Event_Task_Darg_Save = new View.OnClickListener() {
 	
		@Override
		public void onClick(View v) {
			helper.Toastmsg("Saved..", c);
			 lyt_status.setVisibility(View.GONE);
			 lyt_undo_save.setVisibility(View.GONE);
		}
 	};

	private View.OnClickListener Event_Task_Darg_Undo = new View.OnClickListener() {
	 	
		@Override
		public void onClick(View v) {
			helper.Toastmsg("Undo..",c);
			 lyt_status.setVisibility(View.GONE);
			 lyt_undo_save.setVisibility(View.GONE);
			
		}
 	};
 	


	
}
    
