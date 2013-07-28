package com.smarterlife.calendar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;




import android.R.drawable;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.SearchManager.OnDismissListener;
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
 
public class EventFrag_R extends Fragment implements  android.widget.PopupWindow.OnDismissListener{
	//SmarterlifeHelper helper = new SmarterlifeHelper();
	
    private static Context c;
    private static int layoutIndex;
    private static Display ScreenDisplay;
    private static Activity activity;
    private static Event event;
	private static View v;
    public EventFrag_R (){
    	
		// TODO Auto-generated constructor stub
    	
	}
  
   
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        /** Getting the arguments to the Bundle object */
        Bundle data = getArguments();
      //  layoutIndex = data.getInt("layoutIndex");
        
      //  this.layoutIndex = layoutIndex;
        /** Getting integer data of the key current_page from the bundle */
      
        activity = this.getActivity();
   
       
      
    }
	
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         v = inflater.inflate(R.layout.lyt_event_item_r, container,false); //
         
     //   Point eventPoint = new Point();
      //  ScreenDisplay =  SmarterlifeHelper.ScreenDisplay;
    //   ScreenDisplay.getSize(eventPoint);
        event =(Event)getArguments().getSerializable("event");
		
        

        PopulateView(v);
        
   
       
    
        
//      

        
        return   v;
    }

    
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        // Make sure that we are currently visible
        if (this.isVisible()) {
            // If we are becoming invisible, then...
            if (isVisibleToUser) {
            	// Point eventPoint = new Point();
                // ScreenDisplay =  SmarterlifeHelper.ScreenDisplay;
               // ScreenDisplay.getSize(eventPoint);
                event =(Event)getArguments().getSerializable("event");
            	
                SmarterlifeHelper sh = new SmarterlifeHelper(activity);
                sh.showEventDeletePopup(activity, event);
            }
        }
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
       c=activity.getApplicationContext();
       
      
      
       
      // showEventDeletePopup();
     
    }
    
	 // @SuppressLint("NewApi")
	
	private void PopulateView(View v) {
	
	TextView txtEventTitle = (TextView)v.findViewById(R.id.txt_event_r);
	
	txtEventTitle.setText(event.getEventName());
	
        
        
	}
	

	
	
	
	
//	public    void showEventDeletePopup() {
//		 Point p = new Point();
//	    ScreenDisplay.getSize(p);
//		 
//		 int popupWidth = p.x;
//	    int popupHeight =  p.y ;
//	   
//	    
//	 //   p.x = 10;
////	  p.y = 10;
//	    // Inflate the popup_layout.xml
//	    ScrollView viewGroup = (ScrollView)activity.findViewById(R.id.lyt_event_delete);
//	    LayoutInflater layoutInflater = (LayoutInflater) activity
//	      .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//	    View layout = layoutInflater.inflate(R.layout.lyt_event_popup_delete, viewGroup);
//	  
//	    // Creating the PopupWindow
//	    final PopupWindow EventRightSwipePopup = new PopupWindow(activity);
//	    EventRightSwipePopup.setContentView(layout);
//	    EventRightSwipePopup.setWidth(popupWidth);
//	    EventRightSwipePopup.setHeight(popupHeight);
//	 //  helper.Toastmsg(viewGroup.getLayoutParams().height+"  h", context);
//	    EventRightSwipePopup.setFocusable(true);
//	  
//	    // Some offset to align the popup a bit to the right, and a bit down, relative to button's position.
//	    int OFFSET_X = 100;
//	    int OFFSET_Y =  p.y -(p.y/4);  //SmarterlifeHelper.getPixels(28,activity.getResources());
//	  
//	    // Clear the default translucent background
//	    EventRightSwipePopup.setBackgroundDrawable(new BitmapDrawable());
//	    
//	    // Displaying the popup at the specified location, + offsets.
//	    EventRightSwipePopup.showAtLocation(layout, Gravity.CENTER, p.x + OFFSET_X, p.y + OFFSET_Y);
//	  
//	    // Getting a reference to Close button, and close the popup when clicked.
//	    EventRightSwipePopup.setOnDismissListener( EventFrag_R.this);
//
//	   TextView txtDeleteMsg = (TextView) layout.findViewById(R.id.txt_event_delete);
//	  
//	    txtDeleteMsg.setText("Delete Event?" + event.getEventName());
//	    
//	    
//	   Button btnclose = (Button) layout.findViewById(R.id.btn_event_delete_close);
//	   btnclose.setOnClickListener(new OnClickListener(){
//
//		@Override
//		public void onClick(View v) {
//			// TODO Auto-generated method stub
//			EventRightSwipePopup.dismiss();
//			 showProgressBarPopup();
//		}});  	
//	  
//	   
//	    
//	 }

	 @Override
		public void onDismiss() {
			
			getActivity().finish();
			getActivity().startActivity(getActivity().getIntent());
			
		}

//	 public    void showProgressBarPopup() {
//		 
//		 
//		 Point p = new Point();
//		    ScreenDisplay.getSize(p);
//			 
//			 int popupWidth = p.x;
//		    int popupHeight =  p.y ;
//		   
//		    
//		 //   p.x = 10;
////		  p.y = 10;
//		    // Inflate the popup_layout.xml
//		    ScrollView viewGroup = (ScrollView)activity.findViewById(R.id.lyt_progress);
//		    LayoutInflater layoutInflater = (LayoutInflater) activity
//		      .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//		    View layout = layoutInflater.inflate(R.layout.lyt_progress, viewGroup);
//		  
//		    // Creating the PopupWindow
//		    final PopupWindow EventRightSwipePopup = new PopupWindow(activity);
//		    EventRightSwipePopup.setContentView(layout);
//		    EventRightSwipePopup.setWidth(popupWidth);
//		    EventRightSwipePopup.setHeight(popupHeight);
//		 //  helper.Toastmsg(viewGroup.getLayoutParams().height+"  h", context);
//		    EventRightSwipePopup.setFocusable(true);
//		  
//		    // Some offset to align the popup a bit to the right, and a bit down, relative to button's position.
//		    int OFFSET_X = 100;
//		    int OFFSET_Y =  p.y -(p.y/4);  //SmarterlifeHelper.getPixels(28,activity.getResources());
//		  
//		    // Clear the default translucent background
//		    EventRightSwipePopup.setBackgroundDrawable(new BitmapDrawable());
//		    
//		    // Displaying the popup at the specified location, + offsets.
//		    EventRightSwipePopup.showAtLocation(layout, Gravity.CENTER, p.x + OFFSET_X, p.y + OFFSET_Y);
//		  
//		    // Getting a reference to Close button, and close the popup when clicked.
//		    EventRightSwipePopup.setOnDismissListener( EventFrag_R.this);
//
//		  
//		  
//		   
//		    
//		
//		  
//		   
//		    
//		 }
//	 


	 
	
	

	
	
	
	
	
}
    
