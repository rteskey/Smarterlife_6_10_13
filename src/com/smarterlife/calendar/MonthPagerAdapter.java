package com.smarterlife.calendar;

import java.util.Calendar;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

public class MonthPagerAdapter extends android.support.v4.app.FragmentPagerAdapter{
	 
	private int PAGE_COUNT = 200;
	public Context context;
	private	int ScreenWidth, ScreenHeights;
	private int month, year;
	private  Calendar Cal;
	private static long  CalInMils;
    /** Constructor of the class 
     * @param c */
    
  public MonthPagerAdapter(FragmentManager fm, Context c, int Month, long calinmils ) {
        super(fm);
        context = c;
        month = Month;
        Cal = Calendar.getInstance();
        CalInMils = calinmils;
        Cal.setTimeInMillis(calinmils);
       // ScreenHeights = screenHeights;
    }
 
    /** This method will be invoked when a page is requested to create */
    @Override
    public Fragment getItem(int arg0) {
    	Cal.add(Calendar.MONTH,arg0 );
    
		MonthFrag myMonthFragment = new MonthFrag();
        Bundle data = new Bundle();
        data.putInt("arg0", arg0);
        data.putInt("month", arg0);
        data.putLong("CalendarInMill",CalInMils );
        
        myMonthFragment.setArguments(data);
       PAGE_COUNT++;
       
      
       
        return myMonthFragment;
    }
 
    /** Returns the number of pages */
    @Override
    public int getCount() {
//    	 
    	return PAGE_COUNT;
    }
}