package com.smarterlife.calendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.ActionBar.LayoutParams;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.smarterlife.calendar.R.drawable;

public class WeekFrag_day extends Fragment  {
	
	

//	String[] items = { "this", "is", "a", "really", "really2", "really3",
//			"really4", "really5", "silly", "list" };
   int mCurrentPage;
   int currentWeek;
 //  private   Context c =null;
   private static int ScreenWidth=0;
private static int ScreenHeights=0;
private int DayWidth;
   private Boolean fullWeek;
   
   private WeekAdapter mAdapter;
	 private ArrayList<String> listCountry;
	 private ArrayList<Integer> listFlag;
	 private static Activity eActivity;
	 private GridView gridView;
	 private  LinearLayout RL;
     private static int Day = 0 ;
  
   
//	public WeekFrag(Context context, int scrrenWidth, int screenHeights,  Activity activity , int cpage, int day) {
//		// TODO Auto-generated constructor stub
//    	c= context;
//    	ScreenWidth =  scrrenWidth;
//    	ScreenHeights= screenHeights;
//    	eActivity = activity;
//    	currentWeek = cpage;
//    	Day = day; 
//    	
//    	
//    	
//	}
//	
	public WeekFrag_day()
	{
		
		
	}
   
   
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
        /** Getting the arguments to the Bundle object */
        Bundle data = getArguments();
 
        /** Getting integer data of the key current_page from the bundle */
        mCurrentPage = data.getInt("current_page", 0);
        currentWeek =  data.getInt("arg0", 0);
        Day =  data.getInt("Day", 0);
        // currentWeek =  data.getInt("current_page", 0);
        
        DisplayMetrics metrics = this.getResources().getDisplayMetrics();
        ScreenWidth  = metrics.widthPixels;
        ScreenHeights =metrics.heightPixels;
        
        if(ScreenHeights <ScreenWidth )
       	 ScreenWidth =ScreenHeights;
        
        
 
    }
	
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //View v = inflater.inflate(R.layout.week_frag_layout, container,false);
        
    	 View v = inflater.inflate(R.layout.month_frag_layout, container,false);
    	RL = (LinearLayout) v.findViewById(R.id.monthcalendarLayout);
        //prepareList();
       
        //gridView.setAdapter( new WeekAdapter(v1.getContext(),listCountry, listFlag));
        //
       // mAdapter = new WeekAdapter(v.getContext(),listCountry, listFlag);
       // gridView.setAdapter(mAdapter);
        //Context context = getApplicationContext();
       
        PopulateCalendar(v);
       
      
        
   //     GridView  wkGrid = (GridView ) v.findViewById(R.id.gridView101);
     //   wkGrid.setAdapter(new PhotoImageAdapter(v.getContext(),currentWeek, eActivity)); // uses the view to get the context instead of getActivity().
//     
//        ArrayAdapter<String> aa = new ArrayAdapter<String>(
//        		v.getContext(),
//				android.R.layout.simple_list_item_1, 
//				items );
        // WeekAdapter.setWeek(c);
      // wkGrid.setAdapter(aa);
       // wkGrid.setOrientation(LinearLayout.HORIZONTAL);
        DayWidth = (ScreenWidth/8) ;
        
      //  Button Monthbtn = new Button(c);
      //  Monthbtn.setText("M");
    	//wkGrid.addView(Monthbtn,DayWidth,LayoutParams.WRAP_CONTENT);
        
    	LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
    			android.view.ViewGroup.LayoutParams.WRAP_CONTENT    , android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
    	layoutParams.weight = 1;
    	//wkGrid.setwwidth= ScreenWidth - DayWidth ;
    	
    	//layoutParams.setMargins(-5,-5,-5, -5);
    	//wkGrid.setLayoutParams(layoutParams);
    	
        
        return   v;
    }

    private void PopulateCalendar(View v) {
    
    	Calendar  calendar=    Calendar.getInstance();
    	//calendar.add(Calendar.DAY_OF_YEAR, cWeek);
    	Calendar currentDayCal =   Calendar.getInstance();
    	final Calendar gCal=  Calendar.getInstance();
    	 Calendar mCal=  Calendar.getInstance();
		
    		int currentDay =0;
    		 currentDay = gCal.get(Calendar.DAY_OF_WEEK);
    	
    	
		int cWeek;
		cWeek = (currentWeek) -1000;
	    
	        	cWeek = (cWeek)*7;
	    //gCal.setTimeInMillis(gCal.getTimeInMillis()+ cWeek*86400000);    	
		
		gCal.add(Calendar.DAY_OF_YEAR, cWeek);
		
	    final int selecteddate = gCal.get(Calendar.DAY_OF_MONTH);
	    
	    
		
	
	
	        
	        
		
		
		
		
		//this gets the day of week range 1-7, Sunday - Saturday
        Date currentDate = gCal.getTime();
        gCal.setTime(currentDate);
	
        int month = gCal.get(Calendar.MONTH);
		
        //backtracks to the beginning of current week (Sunday)
        gCal.add(Calendar.DAY_OF_YEAR, Calendar.SUNDAY - currentDay);
      
      //  Toast.makeText(v.getContext(),"  Cweek: "+ cWeek, Toast.LENGTH_LONG).show();
       
       
      RL.setOrientation(LinearLayout.HORIZONTAL);
      
       wDate[0] = gCal.get(Calendar.MONTH);
        	 for(int i = 0 ; i <7 ;i++)
             { 
        		 RelativeLayout RL_day = new RelativeLayout(v.getContext());
        		// RL_day.setOnDragListener( EventTaskDrag);
        		 RL_day.setBackgroundResource(drawable.bk_day_btn_toprow);
        		//RL_day.setOrientation(LinearLayout.VERTICAL);
        		TextView tvDay = new TextView(v.getContext());
        		tvDay.setText(wDays[i]);
        		tvDay.setTextColor(0xffeeeeee);
        		tvDay.setTag(gCal.get(Calendar.YEAR));
        		
        		 RelativeLayout.LayoutParams day_params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        		 day_params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        		 day_params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        		 tvDay.setGravity(Gravity.CENTER_HORIZONTAL);
         		 tvDay.setBackgroundColor(0xff99c3e7);
         		 tvDay.setTextSize(14);
         		 tvDay.setTypeface(null,Typeface.BOLD);
         		tvDay.setId(100+i);
         		 
         		 RL_day.addView(tvDay, day_params);
        		final TextView tvDate = new TextView(v.getContext());
        		tvDate.setText(String.valueOf( gCal.get(Calendar.DAY_OF_MONTH)));
        		tvDate.setTextSize(16);
        		tvDate.setGravity(Gravity.BOTTOM| Gravity.CENTER_HORIZONTAL);
        		//tvDate.setGravity(Gravity.CENTER_HORIZONTAL);
        		tvDate.setTextColor(0xff444444);
        		
        		if(cWeek ==0 && currentDayCal.get(Calendar.DAY_OF_MONTH)== gCal.get(Calendar.DAY_OF_MONTH))  // && Day !=0
        			{ 
        			   tvDate.setBackgroundColor(0xff00468c);
        			   tvDate.setTextColor(0xffeeeeee);
        			}
        		if( Day== gCal.get(Calendar.DAY_OF_MONTH))
        			 RL_day.setBackgroundColor(Color.GREEN);
        		
        		RelativeLayout.LayoutParams date_params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);  // LayoutParams.WRAP_CONTENT
        		 date_params.setMargins(1, 1, 1, 0);
        		 date_params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        		 date_params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        		 
        		 date_params.addRule(RelativeLayout.BELOW, tvDay.getId() );
        		RL_day.addView(tvDate,date_params);
        		RL_day.bringToFront();
        		
        		TextView tvEvent = new TextView(v.getContext());
//       	EventData eventDat = new EventData(v.getContext(), eActivity,tvEvent );
//        		eventDat.GetEventsCount( gCal.getTimeInMillis());
        		tvEvent.setText("20");
        		tvEvent.setTextColor(0xffeeeeee);
        		tvEvent.setBackgroundResource(drawable.events_green);
        		tvEvent.setGravity(Gravity.RIGHT);
        		 RelativeLayout.LayoutParams event_params = new RelativeLayout.LayoutParams( LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
                 //event_params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                 event_params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                 event_params .addRule(RelativeLayout.BELOW, tvDay.getId() );
                 event_params.setMargins(1, 1, 1, 0);
                
                 RL_day.addView(tvEvent,event_params);
        		 tvEvent.bringToFront();
        		 
        		
        		 
          	   
          LinearLayout.LayoutParams Lparams =	 new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT);
          Lparams.weight = 1;
          
          //Lparams.setMargins(5, 5, 5, 5);	   
          RL_day.setLayoutParams(Lparams);
          	
          	   RL.addView(RL_day);
          
         // Button tbtn = new Button(ctx);
       //   tbtn.setText(" "+ i);
          
          	  //   RL.addView(tbtn);
          	   
          	 gCal.add(Calendar.DAY_OF_YEAR, 1);
          	
           
          	RL_day.setOnClickListener(new OnClickListener(){

				public void onClick(View v) {
				  
					Intent intent = new Intent(getActivity().getApplicationContext(),
							 Cal_monthActivity.class);
					 intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
					 intent.putExtra("day", Integer.parseInt( (String) tvDate.getText()));   
//					 intent.putExtra("month",tvDate.getTag(R.string.selectedmonth).toString() );
//				        intent.putExtra("year", (Integer)tvDate.getTag(R.string.selectedyear));   
				    
				        //Toast.makeText(v.getContext(),tvDate.getText()+" "+tvDate.getTag(R.string.selectedmonth)+ " "+ tvDate.getTag(R.string.selectedyear)  , Toast.LENGTH_SHORT).show(); 
				        
					 getActivity().startActivity(intent);
					 //     ((Activity)c).finish();
				        eActivity.finish();
				        
				}
          		
          	} );
          	
        
          
         }
        	 
        	 
//        	 for(int i=0; i<((ViewGroup)RL).getChildCount(); ++i) {
//        		    View nextChild = ((ViewGroup)RL).getChildAt(0);
//        		}
//        	 View nextChild = ((ViewGroup)RL).getChildAt(0);
//        	 
        	 
        	// Toast.makeText(v.getContext(), nextChild.getTag().toString() +"  wDate[0-6]  "+wDate[0] +"  "+ wDate[1] + " "+wDate[2]+ " "+wDate[3]+" "+ wDate[4]+ " "+ wDate[5]+" "+wDate[6] , Toast.LENGTH_SHORT).show();
//        	 final TextView tvmonthyear = (TextView)eActivity.findViewById(R.id.tvmonthyear);
//    		 tvmonthyear.setText( "\n  "  + nextChild.getTag().toString() + " " );
    	
    	
    }
    private String[] wDays = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"   };
    private  String[] monthslist = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    private int[] wDate={0,0,0,0,0,0,0,0};
    
    
    
    


}
