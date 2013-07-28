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
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
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
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableLayout.LayoutParams;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import apphelper.SmarterlifeHelper;
 
public class EventFrag extends Fragment{
	//SmarterlifeHelper helper = new SmarterlifeHelper();
	
    private static Activity activity;
    private  int layoutIndex;
  //  private Display ScreenDisplay;
	
    
  
   
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        /** Getting the arguments to the Bundle object */
        Bundle data = new Bundle();
     
        layoutIndex = data.getInt("layoutIndex");
        this.layoutIndex =  SmarterlifeHelper.eventPageIdex;
       
        activity = this.getActivity();
   
       
      
    }
	
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = null ; 
       // Point eventPoint = new Point();
       // ScreenDisplay = SmarterlifeHelper.getScreenSize(); //SmarterlifeHelper.ScreenDisplay;
       // ScreenDisplay.getSize(eventPoint);
       

        	v=	inflater.inflate(R.layout.lyt_event_item, container,false);
            
        	
            TextView txtEventTitle = (TextView)v.findViewById(R.id.txt_event_item_time);
           
        v.setOnLongClickListener(EventTaskLongClickListener);
      
        Event event =(Event)getArguments().getSerializable("event");
       
        txtEventTitle.setText(event.getEventName()+ "  "+event.getEventid());
        txtEventTitle.setTag(event);
        
       // PopulateCalendar(v);
        
       
        return   v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
      // c=activity.getApplicationContext();
    }
    
	 // @SuppressLint("NewApi")
	
	private void PopulateCalendar(View v) {
	
		
	
        
        
	}
	
	private OnLongClickListener EventTaskLongClickListener = new OnLongClickListener() {

		@Override
		public boolean onLongClick(View v) {
			
			
			v.setOnTouchListener(EventTaskTouchListener);
			
			return false;
		}
	
	};
	
	

	private OnTouchListener EventTaskTouchListener = new OnTouchListener() {

		@Override
		public boolean onTouch(View view, MotionEvent motionEvent) {
			  float x = motionEvent.getX();
			  float y = motionEvent.getY();
			  view.setBackgroundColor(Color.GRAY);  
			  
			 long time = Calendar.getInstance().getTimeInMillis();
			  
			 // if (motionEvent.getAction() == MotionEvent.ACTION_DOWN)
				  
			  
		//	if (motionEvent.getAction() == MotionEvent.ACTION_MOVE ) // && Calendar.getInstance().getTimeInMillis()-time >2000
		//	{   //.ACTION_DOWN
                 
                  
	    	 
//				  motionEvent.setLocation(x+100f,1f);	 
//	    	  motionEvent.offsetLocation(motionEvent.getX()+150, motionEvent.getY()+150);
//	    	  motionEvent.setLocation(1000,1000);
	    	 //ClipData data = ClipData.newPlainText("", "");
	    	 //DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
	         //view.setBackgroundResource(R.drawable.shape_droptarget);
	       CustomEventTaskDragShadowBuilder shadowBuilder =  new CustomEventTaskDragShadowBuilder(view);
	        
	        view.startDrag(null, shadowBuilder, view, 0);
	        //view.setVisibility(View.INVISIBLE);
	        ScaleAnimation animation = new ScaleAnimation(0,0.5f,0,0.5f);
	       view.startAnimation(animation);
	        view.setOnTouchListener(null);
	        return true;
	   //   } else {
	    	  
	    //	  ((View) view.getParent()).setOnTouchListener(activitySwipeDetector);
	      //  return false;
	       
		//}
			
		}
		
	};



	
	
	

	
	
	
	
	
}
    
