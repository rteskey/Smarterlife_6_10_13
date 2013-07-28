package com.smarterlife.calendar;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;



import android.R.string;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.TableLayout.LayoutParams;
import android.widget.TextView;

public class PhotoImageAdapter extends BaseAdapter {
    private static Context mContext;
    public int cWeek =0;
    public static int today = 0 ;
    public  boolean dblMonth = false;
    private Activity eActivity;
    public static  int GlobalcurrentDay ,GlobalcurrentMonth , GlobalcurrentYear ;
    public PhotoImageAdapter(Context c, int currentWeek, Activity activity) {
        mContext = c;
        eActivity = activity;
        cWeek = (currentWeek-1 ) - 1000;
      // currentDay =  (currentWeek -1);
        //if(cWeek!=0)
        	cWeek = cWeek*7;
        
//        	 wDays[0] = "Month"; 
//   		  wDate[0] =-1;  
        	
    }

    public int getCount() {
        return wDays.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView( final int position, View convertView, ViewGroup parent) {
        
    	 //SelectedDay sd_Class = ((SelectedDay)mContext.getApplicationContext());
//    	  SelectedDay sd_Class = new SelectedDay();
    	  int globalint=0;
    	Intent ii = getIntent();
    	Bundle extras;
    	if(ii!=null){
        extras = ii.getExtras();//.getExtras();
        globalint = extras.getInt("selected_Day");
       
    	}
    	
    	  
    	
    	Calendar  calendar=    Calendar.getInstance();
    	//calendar.add(Calendar.DAY_OF_YEAR, cWeek);
    	Calendar currentDayCal =   Calendar.getInstance();
    	 Calendar gCal=  Calendar.getInstance();
    	
		//Toast.makeText(mContext.getApplicationContext(),"Day_of_Week: "+ calendar.get(Calendar.DAY_OF_MONTH) + "  Cweek: "+ cWeek, Toast.LENGTH_LONG).show();
    		int currentDay =0;
    		 currentDay = gCal.get(Calendar.DAY_OF_WEEK);
    	 if(GlobalcurrentYear!=0  )
			{
    		 
    		
    		 
    		 //gCal.set(GlobalcurrentYear, GlobalcurrentMonth, GlobalcurrentDay ) ;
    		
    		 //if(GlobalcurrentMonth ==0)
    		 //wDays[0] =monthslist[11];
    		  wDays[0] = "Month"; //monthslist[GlobalcurrentMonth];
    		  wDate[0] =-1;  //GlobalcurrentYear;
    		 // currentDay = GlobalcurrentDay;
    		
    		
			}
		//else
			//gCal.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), (calendar.get(Calendar.DAY_OF_WEEK)));//(2013, Calendar.JANUARY, 1);
    	
    	
		gCal.add(Calendar.DAY_OF_YEAR, cWeek);
		 wDays[0]  =monthslist[gCal.get(Calendar.MONTH)];
  	   wDate[0]  =gCal.get(Calendar.YEAR);
  	   
  	 
		//this gets the day of week range 1-7, Sunday - Saturday
        Date currentDate = gCal.getTime();
        gCal.setTime(currentDate);
	
		
		
        //backtracks to the beginning of current week (Sunday)
        gCal.add(Calendar.DAY_OF_YEAR, Calendar.SUNDAY - currentDay);
        
          
       	
        final  SimpleDateFormat month_date = new SimpleDateFormat("MMM");
        final SimpleDateFormat year_date = new SimpleDateFormat("yyyy");
        
     //   final TextView tvmonthyear = (TextView)eActivity.findViewById(R.id.tvmonthyear);
        
//        if(wDays[0]=="Month")
//        	 wDays[0]=  monthslist[gCal.get(Calendar.MONTH)];	
        //wDays[0] = month_date.format(gCal.getTime());
        String weekEndingMth = "";
        
//        if(wDate[0]==-1)
//        try { 
//        wDate[0] =  Integer.parseInt(year_date.format(gCal.getTime())) ;
//        }
//        catch(NumberFormatException nfe) {
//        	   System.out.println("Could not parse " + nfe);
//        	} 
        int gridSizeX = 8, gridSizeY = 1;
//        wDays[0]= "Month";
//        wDate[0]=-1;
      //  tvmonthyear.setText( monthslist[gCal.get(Calendar.MONTH)]+"\n"  + gCal.get(Calendar.YEAR) + "\n"+gCal.get(Calendar.MONTH));
        Toast.makeText(mContext.getApplicationContext(),"tvmonthyear:"+gCal.get(Calendar.MONTH) , Toast.LENGTH_SHORT ).show();
        for (int i = 0; i < gridSizeY; i++)
        {  
        	
            for (int j = 0; j < gridSizeX; j++)
            {
                //fill in your cell with this value
                //
               if(j==0)
               {
            	  
            	   wDays[j]  =monthslist[gCal.get(Calendar.MONTH)];
            	   wDate[j]  =gCal.get(Calendar.YEAR);
            	  
            	   mwDate[j]= gCal.get(Calendar.MONTH); 
                   mwyDate[j]= gCal.get(Calendar.YEAR); 
                   
                   
                  
               }
               else
               {
            	wDate[j]= gCal.get(Calendar.DAY_OF_MONTH);
                mwDate[j]= gCal.get(Calendar.MONTH); 
                mwyDate[j]= gCal.get(Calendar.YEAR); 
                
//                if(wDays[0] =="Month" )
//                {
//                	
//                  wDays[0]= monthslist[gCal.get(Calendar.MONTH)];
//                }
//                if(wDate[0]==-1)
//                   wDate[0]= gCal.get(Calendar.YEAR);
//               
             

                //add one to the day and keep going
                gCal.add(Calendar.DAY_OF_YEAR, 1);
                weekEndingMth = month_date.format(gCal.getTime());
               }
            }
        
        }
       
   
       if(monthslist[mwDate[1]] != wDays[0])
       {
    	   wDays[0]=monthslist[mwDate[1]];
       }
        
        
        View customView;
        
       
        
       // mContext.startActivity(intent);
    	
    	ImageView imageView = new ImageView(mContext);
       // TextView txDay = new TextView(mContext);
        //Button eventbtn ;
        
        LayoutParams lparams = new LayoutParams(
        		   800, android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
        //lparams.gravity = 1;
        //lparams.setMargins(-10, 0, 0, 0);
        RelativeLayout ll ;
       
        if (convertView == null) {
        	
        	
        	 
        	LayoutInflater li = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           
        	customView = new View(mContext);
        
        	
        	customView.setLayoutParams(lparams);
            customView = li.inflate(R.layout.customweekview,parent, false); 
        	final TextView txtDay = (TextView) customView.findViewById(R.id.txtDays);
        	txtDay.setText(wDays[position].toString());
        	
        	final TextView txtDates = (TextView) customView.findViewById(R.id.txtDates);
        	 int date = wDate[position];
        	
        	 txtDates.setText( date +"");
        	TextView txtEvents = (TextView) customView.findViewById(R.id.txtEvents);
        	txtEvents.setText("10");
        	txtEvents.bringToFront();
        	Calendar.getInstance();
        	customView.setId(mwDate[position]);
        	customView.setTag(mwyDate[position]);
        	  
        	
        	
			if(date ==calendar.get(Calendar.DAY_OF_MONTH) && cWeek == 0 ) //
        	{
//        		txtDates.setBackgroundColor(R.drawable.blackbtn);
        		txtDates.setTextSize(25);
        		
//        		txtDay.setBackgroundColor(R.drawable.blackbtn);
        		txtDay.bringToFront();
        		txtEvents.bringToFront();
        		txtEvents.setTextSize(10);
        		
        	}
			
			
			
			if(date >31)
			{
				txtDates.setTextSize(15);
				txtDates.setBackgroundColor(Color.WHITE);
				txtDates.setTextColor(Color.BLACK);
				txtDates.setText(mwyDate[position]+"");
				txtDay.setBackgroundColor(Color.WHITE);
				txtDay.setTextColor(Color.BLACK);
				//txtDay.setText(wDays[0]);
				txtEvents.setVisibility(txtEvents.INVISIBLE);
				customView.setBackgroundColor(Color.WHITE);
				customView.setOnClickListener(new OnClickListener(){

					public void onClick(View v) {
						// TODO Auto-generated method stub
						 Intent intent = new Intent(mContext.getApplicationContext(),
								 Cal_monthActivity.class);
						 intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
						 intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					        intent.putExtra("month" , txtDay.getText().toString().trim());  // gCal.get(Calendar.MONTH)
					        intent.putExtra("year", Integer.parseInt( txtDates.getText().toString().trim()));
					    // Toast.makeText(mContext.getApplicationContext(), "Month="+txtDay.getText()+" year="+ Integer.parseInt(txtDates.getText().toString()), Toast.LENGTH_SHORT).show();
					        mContext.startActivity(intent);
					        ((Activity)mContext).finish();
					}});
			}
			else
			{
				customView.setOnClickListener(new OnClickListener(){

					public void onClick(View v) {
						// TODO Auto-generated method stub
						 Intent intent = new Intent(mContext.getApplicationContext(),
								 Cal_monthActivity.class);
						// intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
						 intent.putExtra("day", wDate[position]);   
						 intent.putExtra("month",monthslist[v.getId()] ); // wDays[0] // gCal.get(Calendar.MONTH)
					        intent.putExtra("year", (Integer)v.getTag());   // wDate[0]
					       
					       int day = wDate[position];
					        String month =  monthslist[v.getId()];
					       int  year  =(Integer)(v.getTag());
					        
					        SharedPreferences daysettings =mContext.getSharedPreferences("day", 0);
					        SharedPreferences monthsettings =mContext.getSharedPreferences("month", 0);
					        SharedPreferences yearsettings =mContext.getSharedPreferences("year", 0);
					        SharedPreferences.Editor dayeditor = daysettings.edit();
					        SharedPreferences.Editor montheditor = monthsettings.edit();
					        SharedPreferences.Editor yeareditor = yearsettings.edit();
					        dayeditor.putInt("day", 1);
					        montheditor.putString("month", month);
					        yeareditor.putInt("year", year);
					        
					       
					        dayeditor.commit();
					        montheditor.commit();
					        yeareditor.commit();
					        
					      mContext.startActivity(intent);
					      ((Activity)mContext).finish();
					}});
				
			}
			 
			//Toast.makeText(mContext.getApplicationContext(),"currentDay" +gCal.get(Calendar.DAY_OF_MONTH)  + " Position: "+ position + "wDate= "+ wDate[position], Toast.LENGTH_SHORT).show();
			if(wDate[position] == GlobalcurrentDay)
        	{ 
				
				txtDates.setTextSize(15);
				txtDates.setBackgroundColor(Color.GREEN);
				txtDates.setTextColor(Color.WHITE);
				txtDay.setBackgroundColor(Color.GREEN);
				txtDay.setTextColor(Color.WHITE);
				//txtEvents.setVisibility(txtEvents.INVISIBLE);
				customView.setBackgroundColor(Color.GREEN);
				GlobalcurrentDay = 0;
        	}
			
			
   
        } else {
        	customView = convertView;
           
        }
    
        // GlobalcurrentDay= 0;
        GlobalcurrentMonth= 0; GlobalcurrentYear= 0 ;
        
      
        return   customView ; // ll;
    }

    private Intent getIntent() {
		// TODO Auto-generated method stub
		return null;
	}

	// references to our images
//    private Integer[] mThumbIds = {
//            R.drawable.pruplebtn, R.drawable.sample_3,
//            R.drawable.sample_4, R.drawable.sample_5,
//            R.drawable.sample_6, R.drawable.sample_7,
//            R.drawable.sample_0, R.drawable.sample_0
//    };
    
    private String[] wDaysFull = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"  };
    
    private String[] wDays = {"Month","Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"   };
    private int[] wDate={-1,0,0,0,0,0,0,0,0};
    private int[] mwDate={-1,0,0,0,0,0,0,0,0};
    private int[] mwyDate={-1,0,0,0,0,0,0,0,0};
	public static void setArguments_day(int day, int month, int year) {
		// TODO Auto-generated method stub
		GlobalcurrentDay = day;
		GlobalcurrentMonth= month;
		GlobalcurrentYear= year;
		
		today = day;
		//	Toast.makeText(mContext.getApplicationContext(),"GlobalcurrentDay"+ day + " "+ month+ " " + year, Toast.LENGTH_SHORT).show();
	}
	 private  String[] monthslist = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
}


