package com.smarterlife.calendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.smarterlife.calendar.R.drawable;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import apphelper.SmarterlifeHelper;
 
public class WeekFrag extends Fragment {
	 SmarterlifeHelper helper ;
	 int mCurrentPage;
	 int currentWeek;
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
	 private static  Context c;
	 private static RelativeLayout lyt_status;
	 private static  Day_tag day_tag;
	 private   LinearLayout lyt_undo_save;

    public WeekFrag (){}
		
    	
	
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper = new SmarterlifeHelper(getActivity());
    	c= getActivity().getApplicationContext();
       
        Bundle data = getArguments();
 
        
        mCurrentPage = data.getInt("current_page", 0);
        currentWeek =  data.getInt("arg0", 0);
        Day =  data.getInt("Day", 0);
       
        
        DisplayMetrics metrics = this.getResources().getDisplayMetrics();
        ScreenWidth  = metrics.widthPixels;
        ScreenHeights =metrics.heightPixels;
        
        if(ScreenHeights <ScreenWidth )
       	 ScreenWidth =ScreenHeights;
    }
	
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        
        
    	 View v = inflater.inflate(R.layout.month_frag_layout, container,false);
    	RL = (LinearLayout) v.findViewById(R.id.monthcalendarLayout);
       
       
        PopulateCalendar(v);
       
      
   
        DayWidth = (ScreenWidth/8) ;
        
     
        
    	LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
    			android.view.ViewGroup.LayoutParams.WRAP_CONTENT    , android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
    	layoutParams.weight = 1;
    	
    	
        
        return   v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
       c=activity.getApplicationContext();
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
        		   day_tag = new Day_tag();
        		 
        		 RelativeLayout RL_day = new RelativeLayout(v.getContext());
        	     RL_day.setOnDragListener( EventTaskDrag);
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
        		
        		day_tag.EventCount=12;
            	day_tag.Day= gCal.get(Calendar.DAY_OF_MONTH);
            	day_tag.Month =  gCal.get(Calendar.MONTH);
            	day_tag.Year = gCal.get(Calendar.YEAR);
            	tvDate.setTag(day_tag);
        		tvDate.setId(1001);
            	
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
        		tvEvent.setId(1000);
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
        	 
        	 

    	
    }
	



    private String[] wDays = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"   };
    private  String[] monthslist = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    private int[] wDate={0,0,0,0,0,0,0,0};
	


    private   OnDragListener EventTaskDrag = new OnDragListener(){

    	@Override
        public boolean onDrag(View v, DragEvent event) {
             
    		Activity activity = getActivity();
        	 int  vWidth = v.getWidth();
        	 int vHeights = v.getHeight();
        	// Drawable enterShape = getResources().getDrawable(R.drawable.shape_droptarget);
    		//    Drawable normalShape = getResources().getDrawable(R.drawable.shape);
    		  
//    		
//        	 RelativeLayout viewGroup = (RelativeLayout)activity. findViewById(R.id.lyt_monthcalendar_status);
//     	    
//     	    LayoutInflater layoutInflater = (LayoutInflater) activity
//     	      .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//     	    View layout = layoutInflater.inflate(R.layout.lyt_calendardrag_status, viewGroup);
        	 
        	 
        	 lyt_status =   (RelativeLayout)activity.findViewById(R.id.lyt_monthcalendar_status);
    		    
    		      lyt_undo_save = (LinearLayout)activity.findViewById(R.id.lyt_monthcalendar_undo_save);
    		    
    		    ImageButton img_undo = (ImageButton)lyt_undo_save.findViewById(R.id.btn_undo);
    		     
    		   img_undo.setOnClickListener(Event_Task_Darg_Undo);
    		   
    		    
    		    lyt_status.bringToFront();
    		   lyt_status.setVisibility(View.VISIBLE);
    	          TextView txtdragstatus = (TextView)  lyt_status.findViewById(R.id.txt_monthcalendar_status);
    	          
    	          RelativeLayout container = (RelativeLayout) v;
    	         
    	        
    	           
    		        TextView txtEvent = (TextView)container.getChildAt(2);
    		        TextView btnDay = (TextView)container.getChildAt(1);
    		     	        
    		      
        	
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
        	  container.setBackgroundColor(Color.RED);
        	  
             //Toastmsg("Text  "+btnDay.getText());
            break;
          case DragEvent.ACTION_DRAG_EXITED:
        	 
        	 v.setBackgroundColor(0XEEEEEEEE);
        	 txtdragstatus.setText("");
             lyt_status.setVisibility(View.GONE);
        	  
            break;
          case DragEvent.ACTION_DROP:
              
        	//  lyt_status.setVisibility(View.GONE);
        	//  txtdragstatus.getLayoutParams().height= 45;
        	    lyt_status.setVisibility(View.VISIBLE);
        	    lyt_undo_save.setVisibility(View.VISIBLE);
        	  
            // Dropped, reassign View to ViewGroup
            View view = (View) event.getLocalState();
            ViewGroup owner = (ViewGroup) v.getParent();
           
            RelativeLayout lytEvent = (RelativeLayout)view;
            CalendarClass event_mov = (CalendarClass) lytEvent.getTag();
            TextView txtEvent_mov = (TextView)lytEvent.getChildAt(0);
            view.setVisibility(View.GONE);
            txtdragstatus.setText("Move " +event_mov.EventName + "  to  " +  Cal_Calendar.getCurrentDate(day_tag.Day,day_tag.Month,day_tag.Year) +  " events: "+String.valueOf(day_tag.EventCount));
            
            
            //  owner.addView(b);
            owner.removeView(view);
            
            txtEvent.setText(String.valueOf( Integer.parseInt(txtEvent.getText().toString()) + 1));
            //container.addView(view);
            view.setVisibility(View.VISIBLE);
           
            container.setBackgroundResource(R.drawable.shape_droptarget);
        
            
            break;
          case DragEvent.ACTION_DRAG_ENDED:
        	  container.setBackgroundColor(0XEEEEEEEE);
        	
        	  
          default:
            break;
          }
          return true;
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
    
