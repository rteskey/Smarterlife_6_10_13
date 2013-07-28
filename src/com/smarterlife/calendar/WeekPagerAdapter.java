package com.smarterlife.calendar;



//import com.smarterlife.calendar.Cal_dayActivity.WeekFrag;

import android.app.Activity;
import android.content.Context;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
public class WeekPagerAdapter extends android.support.v4.app.FragmentPagerAdapter{
	 
	private int PAGE_COUNT = 2000;
	public Context context;
	private	int ScreenWidth, ScreenHeights;
	private Activity eActivity;
	private int Day;
    /** Constructor of the class 
     * @param day 
     * @param absolute */
    
  public WeekPagerAdapter(FragmentManager fm,Context c, int screenWidth, int screenHeights, Activity activity, int day ) {
        super(fm);
        context = c;
        ScreenWidth =screenWidth;
        ScreenHeights = screenHeights;
        eActivity = activity;
        Day = day;
    }
 
    /** This method will be invoked when a page is requested to create */
    @Override
    public Fragment getItem(int arg0) {
 
    
		//WeekFrag myWeekFragment1 = new WeekFrag(context,ScreenWidth,  ScreenHeights, eActivity, arg0, Day);
		
    	
    	
    	WeekFrag myWeekFragment = new WeekFrag();
		Bundle data = new Bundle();
        data.putInt("current_page", arg0+1);
        data.putInt("arg0", arg0);
        data.putInt("Day", Day);
        
        
        myWeekFragment.setArguments(data);
     // PAGE_COUNT++;
        
      
        return myWeekFragment;
    }
 
    /** Returns the number of pages */
    @Override
    public int getCount() {
//    	 
    	return  PAGE_COUNT ;
    }
}