package com.smarterlife.calendar;

import java.util.Calendar;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import apphelper.SmarterlifeHelper;

public class EventPagerAdapter_d extends android.support.v4.app.FragmentPagerAdapter{
	 
	
	public Context context;
	public Event event;
	
    /** Constructor of the class 
     * @param event 
     * @param c */
    
  public EventPagerAdapter_d(Event event, FragmentManager fm, Context c ) {
        super(fm);
        context = c;
        this.event = event;
      
       // ScreenHeights = screenHeights;
    }
 
    /** This method will be invoked when a page is requested to create */
    @Override
    public Fragment getItem(int position) {
    
    	Bundle bundle=new Bundle();
    	bundle.putSerializable("event", event);
    
    	
    	Fragment  frag = new Fragment();
    	
    	
    	//EventFrag eventFrag = new EventFrag();
    	
    	
    //	eventFrag.setArguments(bundle);
    	
    	
    	
    //	frag = eventFrag;
//    	if(position ==0 )
//    	{ 
   		EventFrag_L_d eventFrag_L = new EventFrag_L_d();
    		eventFrag_L.setArguments(bundle);
    		frag= eventFrag_L;
//    	}
//    	if(position ==2 )
//    	{	
//    		EventFrag_R eventFrag_R = new EventFrag_R();
//    		eventFrag_R.setArguments(bundle);
//    		frag =eventFrag_R;
//    		
//    	
//    	}
    	
		
       
       
    	
       
    	
       
        return frag;
    }
 
    /** Returns the number of pages */
    @Override
    public int getCount() {
//    	 
    	return 3;
    }
}