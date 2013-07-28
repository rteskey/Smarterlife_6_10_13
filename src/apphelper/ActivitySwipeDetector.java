package apphelper;



import java.util.Calendar;

import com.smarterlife.calendar.Cal_monthActivity;
import com.smarterlife.calendar.CustomEventTaskDragShadowBuilder;
import com.smarterlife.calendar.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.ViewFlipper;
import apphelper.SmarterlifeHelper;

public class ActivitySwipeDetector implements View.OnTouchListener {

static final String logTag = "ActivitySwipeDetector";
private Activity activity;
static final int MIN_DISTANCE = 5;
private float downX, downY, upX, upY;
long time1, time2;
private Point eventPoint;
private Display ScreenDisplay;
SmarterlifeHelper helper;
public ActivitySwipeDetector(Activity activity){
    this.activity = activity;
    helper = new SmarterlifeHelper(activity);
}

public void onRightToLeftSwipe(){
    Log.i(logTag, "RightToLeftSwipe!");
    helper.Toastmsg("RightToLeftSwipe!",activity);
   // activity.doSomething();
}

public void onLeftToRightSwipe(){
    Log.i(logTag, "LeftToRightSwipe!");
    helper.Toastmsg("LeftToRightSwipe!",activity);
   // activity.doSomething();
}

public void onTopToBottomSwipe(){
    Log.i(logTag, "onTopToBottomSwipe!");
    //activity.doSomething();
}

public void onBottomToTopSwipe(){
    Log.i(logTag, "onBottomToTopSwipe!");
   // activity.doSomething();
}

public boolean onTouch(View v, MotionEvent event) {
	 int viewIndex  = ((ViewFlipper) v).getDisplayedChild();
	 eventPoint = new Point();
	 ScreenDisplay =activity.getWindowManager().getDefaultDisplay();
		ScreenDisplay.getSize(eventPoint);
	switch(event.getAction()){
        case MotionEvent.ACTION_DOWN: {
            downX = event.getX();
            downY = event.getY();
            time1 = Calendar.getInstance().getTimeInMillis();
            return true;
        }
        case MotionEvent.ACTION_MOVE: {
        	  time2 = Calendar.getInstance().getTimeInMillis();
        	  
        	  if((time2 - time1) >500)
        	  {
        		  CustomEventTaskDragShadowBuilder shadowBuilder =  new CustomEventTaskDragShadowBuilder(v);
      	        
      	        v.startDrag(null, shadowBuilder, v, 0);
      	        //view.setVisibility(View.INVISIBLE);
      	        ScaleAnimation animation = new ScaleAnimation(0,0.5f,0,0.5f);
      	       v.startAnimation(animation);
      	        //v.setOnTouchListener(null);
      	       
        		  
        	  }
        	
        return true;
        }
       
        case MotionEvent.ACTION_UP: {
            upX = event.getX();
            upY = event.getY();

            float deltaX = downX - upX;
            float deltaY = downY - upY;

            // swipe horizontal?
            if(Math.abs(deltaX) > MIN_DISTANCE){
                // left or right
                if(deltaX < 0) 
                            {                    	                             	             
                	             if(viewIndex ==1)
                	             {
                	            	 ((ViewFlipper) v).setOutAnimation(activity, R.anim.out_to_right);
                	            	 ((ViewFlipper) v) .setDisplayedChild(0);
                	            	 showEventPopup(activity, eventPoint);
                	            	 
                	             }
                	             if(viewIndex ==2)
                	             {
                	            	 ((ViewFlipper) v).setInAnimation(activity,  R.anim.in_from_left);
                	            	 ((ViewFlipper) v) .setDisplayedChild(1);
                	            	 
                	             }
                	             
			                	 //((ViewFlipper) v).setOutAnimation(activity, R.anim.out_to_left);
			                	 //((ViewFlipper) v).setInAnimation(activity,  R.anim.in_from_right);
                				 this.onLeftToRightSwipe();   
                				return true; 
                            
                            }
                if(deltaX > 0) 
                			{ 
				                	 if(viewIndex ==1)
				    	             {     ((ViewFlipper) v).setInAnimation(activity,  R.anim.out_to_left);
				    	            	 ((ViewFlipper) v) .setDisplayedChild(2);
				    	            	 
				    	             }
				    	             if(viewIndex ==0)
				    	             { 
				    	            	 
				    	                 ((ViewFlipper) v).setOutAnimation(activity, R.anim.out_to_right);
				    	               //  ((ViewFlipper) v).setOutAnimation(activity, R.anim.in_from_left);
				    	            	 ((ViewFlipper) v).setDisplayedChild(1);
				    	            	 
				    	             }
                	
                							
                	        
                	     //    ((ViewFlipper) v).setOutAnimation(activity, R.anim.in_from_left);
                	       //   ((ViewFlipper) v).setInAnimation(activity,  R.anim.out_to_right);
                				this.onRightToLeftSwipe();         return true; 
                			}
            }
            else {
                    Log.i(logTag, "Swipe was only " + Math.abs(deltaX) + " long, need at least " + MIN_DISTANCE);
                    return false; // We don't consume the event
            }

            // swipe vertical?
            if(Math.abs(deltaY) > MIN_DISTANCE){
                // top or down
                if(deltaY < 0) { this.onTopToBottomSwipe(); return true; }
                if(deltaY > 0) { this.onBottomToTopSwipe(); return true; }
            }
            else {
                    Log.i(logTag, "Swipe was only " + Math.abs(deltaX) + " long, need at least " + MIN_DISTANCE);
                    return false; // We don't consume the event
            }

            return true;
        }
    }
    return false;
}



private void showEventPopup(final Activity context, Point p) {
    int popupWidth = ScreenDisplay.getWidth();
    int popupHeight = ScreenDisplay.getHeight();
  p.x = 10;
  p.y = 10;
    // Inflate the popup_layout.xml
    ScrollView viewGroup = (ScrollView) context.findViewById(R.id.lyt_event_leftswipe);
    LayoutInflater layoutInflater = (LayoutInflater) context
      .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    View layout = layoutInflater.inflate(R.layout.lyt_event_popup_leftswipe, viewGroup);
  
    // Creating the PopupWindow
    PopupWindow EventLeftSwipePopup = new PopupWindow(context);
    EventLeftSwipePopup.setContentView(layout);
    EventLeftSwipePopup.setWidth(popupWidth);
    EventLeftSwipePopup.setHeight(popupHeight);
 //  helper.Toastmsg(viewGroup.getLayoutParams().height+"  h", context);
    EventLeftSwipePopup.setFocusable(true);
  
    // Some offset to align the popup a bit to the right, and a bit down, relative to button's position.
    int OFFSET_X = 0;
    int OFFSET_Y = SmarterlifeHelper.getPixels(28,activity.getResources());
  
    // Clear the default translucent background
    EventLeftSwipePopup.setBackgroundDrawable(new BitmapDrawable());
  
    // Displaying the popup at the specified location, + offsets.
    EventLeftSwipePopup.showAtLocation(layout, Gravity.NO_GRAVITY, p.x + OFFSET_X, p.y + OFFSET_Y);
  
    // Getting a reference to Close button, and close the popup when clicked.


 
 
    	
  
   
    
 }
 













}