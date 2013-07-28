package apphelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.smarterlife.calendar.Event;
import com.smarterlife.calendar.EventFrag_R;
import com.smarterlife.calendar.R;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;

public class SmarterlifeHelper  implements  android.widget.PopupWindow.OnDismissListener {

	
	
	
	public SmarterlifeHelper(Activity activity) {
		// TODO Auto-generated constructor stub
		 this.activity = activity;
	}

	public static int getPixels(int dipValue,  Resources r)
	{
       
        int px = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, 
        r.getDisplayMetrics());
        return px;
      }
	
	
	public static float getPixels(float dipValue,  Resources r)
	{
       
        float px = (float)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, 
        r.getDisplayMetrics());
        return px;
      }
	
	
	
	public static  void Toastmsg(String msg, Context c)
	{
		
		Toast.makeText(c.getApplicationContext(), msg, Toast.LENGTH_LONG).show();
		
	}
	
	public static int eventPageIdex=0;
	
	public static Display ScreenDisplay;
	public static  Activity calActivity;
	public static  Activity activity;
 	
	public static Display getScreenSize()
	{  Point p = new Point();
	    ScreenDisplay.getSize(p);
	    ScreenDisplay.getSize(p);
		return ScreenDisplay;
		
	}
	
	public static void showEventStatusPopup( Activity activity) {
		
		//Activity activity = (Activity) context;
		Point p = new Point();
		 ScreenDisplay.getSize(p);
		 
		 int popupWidth = p.x;
	    int popupHeight = p.y;
	   
	    
	 //   p.x = 10;
//	  p.y = 10;
	    // Inflate the popup_layout.xml
	    ScrollView viewGroup = (ScrollView)activity.findViewById(R.id.lyt_event_status_drag);   // id of the view 
	    LayoutInflater layoutInflater = (LayoutInflater) activity
	      .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    View layout = layoutInflater.inflate(R.layout.lyt_calendardrag_status, viewGroup);  // name of xml file 
	  
	    // Creating the PopupWindow
	    final PopupWindow EventLeftSwipePopup = new PopupWindow(activity);
	    EventLeftSwipePopup.setContentView(layout);
	    EventLeftSwipePopup.setWidth(popupWidth);
	    EventLeftSwipePopup.setHeight(popupHeight);
	 //  helper.Toastmsg(viewGroup.getLayoutParams().height+"  h", context);
	    EventLeftSwipePopup.setFocusable(true);
	  
	    // Some offset to align the popup a bit to the right, and a bit down, relative to button's position.
	    int OFFSET_X = 0;
	    int OFFSET_Y =  0;  //SmarterlifeHelper.getPixels(28,activity.getResources());
	  
	    // Clear the default translucent background
	    EventLeftSwipePopup.setBackgroundDrawable(new BitmapDrawable());
	  
	    // Displaying the popup at the specified location, + offsets.
	    EventLeftSwipePopup.showAtLocation(layout, Gravity.NO_GRAVITY, p.x + OFFSET_X, p.y + OFFSET_Y);
	  
	    // Getting a reference to Close button, and close the popup when clicked.
	 //   EventLeftSwipePopup.setOnDismissListener();

	 
	 //  Button btnclose = (Button) layout.findViewById(R.id.btn_close);
//	  // btnclose.setOnClickListener(new OnClickListener(){
//
//		@Override
//		public void onClick(View v) {
//			// TODO Auto-generated method stub
//			EventLeftSwipePopup.dismiss();
//		}});  	
//	  
//	   
//	    
 }
	
	public static void Toastmsg(Context context , String msg)
	{
		
		Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_LONG).show();
		
	}
	
	public  void showEventPopup( final Activity activity, Event event) {
		 Point p = new Point();
		 ScreenDisplay = activity.getWindowManager().getDefaultDisplay();
		 
		 ScreenDisplay.getSize(p);
		 this.activity = activity;
		 int popupWidth = p.x;
	    int popupHeight = p.y;
	   
	    
	 //   p.x = 10;
//	  p.y = 10;
	    // Inflate the popup_layout.xml
	    ScrollView viewGroup = (ScrollView)activity. findViewById(R.id.lyt_event_leftswipe);
	    LayoutInflater layoutInflater = (LayoutInflater) activity
	      .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    View layout = layoutInflater.inflate(R.layout.lyt_event_popup_leftswipe, viewGroup);
	  
	    // Creating the PopupWindow
	    final PopupWindow EventLeftSwipePopup = new PopupWindow(activity);
	    EventLeftSwipePopup.setContentView(layout);
	    EventLeftSwipePopup.setWidth(popupWidth);
	    EventLeftSwipePopup.setHeight(popupHeight);
	 //  helper.Toastmsg(viewGroup.getLayoutParams().height+"  h", context);
	    EventLeftSwipePopup.setFocusable(true);
	  
	    // Some offset to align the popup a bit to the right, and a bit down, relative to button's position.
	    int OFFSET_X = 0;
	    int OFFSET_Y =  0;  //SmarterlifeHelper.getPixels(28,activity.getResources());
	  
	    // Clear the default translucent background
	    EventLeftSwipePopup.setBackgroundDrawable(new BitmapDrawable());
	  
	    // Displaying the popup at the specified location, + offsets.
	    EventLeftSwipePopup.showAtLocation(layout, Gravity.NO_GRAVITY, p.x + OFFSET_X, p.y + OFFSET_Y);
	  
	    // Getting a reference to Close button, and close the popup when clicked.
	   
	    EventLeftSwipePopup.setOnDismissListener(this);
     
	    TextView  txtEventTitle =  (TextView)layout.findViewById(R.id.txt_event_leftswipe_title);
		txtEventTitle.setText(event.getEventName());
	 
	   Button btnclose = (Button) layout.findViewById(R.id.btn_close);
	   btnclose.setOnClickListener(new OnClickListener(){

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			EventLeftSwipePopup.dismiss();
			showProgressBarPopup();
		}});  	
	  
	   
	    
	 }
	
	
	
	public    void showEventDeletePopup( final Activity activity, Event event) {
		 Point p = new Point();
		 ScreenDisplay = activity.getWindowManager().getDefaultDisplay();
	    ScreenDisplay.getSize(p);
		 
		 int popupWidth = p.x;
	    int popupHeight =  p.y ;
	   
	    
	 //   p.x = 10;
//	  p.y = 10;
	    // Inflate the popup_layout.xml
	    ScrollView viewGroup = (ScrollView)activity.findViewById(R.id.lyt_event_delete);
	    LayoutInflater layoutInflater = (LayoutInflater) activity
	      .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    View layout = layoutInflater.inflate(R.layout.lyt_event_popup_delete, viewGroup);
	  
	    // Creating the PopupWindow
	    final PopupWindow EventRightSwipePopup = new PopupWindow(activity);
	    EventRightSwipePopup.setContentView(layout);
	    EventRightSwipePopup.setWidth(popupWidth);
	    EventRightSwipePopup.setHeight(popupHeight);
	 //  helper.Toastmsg(viewGroup.getLayoutParams().height+"  h", context);
	    EventRightSwipePopup.setFocusable(true);
	  
	    // Some offset to align the popup a bit to the right, and a bit down, relative to button's position.
	    int OFFSET_X = 100;
	    int OFFSET_Y =  p.y -(p.y/4);  //SmarterlifeHelper.getPixels(28,activity.getResources());
	  
	    // Clear the default translucent background
	    EventRightSwipePopup.setBackgroundDrawable(new BitmapDrawable());
	    
	    // Displaying the popup at the specified location, + offsets.
	    EventRightSwipePopup.showAtLocation(layout, Gravity.CENTER, p.x + OFFSET_X, p.y + OFFSET_Y);
	  
	    // Getting a reference to Close button, and close the popup when clicked.
	    EventRightSwipePopup.setOnDismissListener( this);

	   TextView txtDeleteMsg = (TextView) layout.findViewById(R.id.txt_event_delete);
	  
	    txtDeleteMsg.setText("Delete Event?" + event.getEventName());
	    
	    
	   Button btnclose = (Button) layout.findViewById(R.id.btn_event_delete_close);
	   btnclose.setOnClickListener(new OnClickListener(){

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			EventRightSwipePopup.dismiss();
			 showProgressBarPopup();
		}});  	
	  
	   
	    
	 }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
 public    void showProgressBarPopup(   ) {
		 
		 
		 Point p = new Point();
		    ScreenDisplay.getSize(p);
			 
			 int popupWidth = p.x;
		    int popupHeight =  p.y ;
		   
		    
		 //   p.x = 10;
//		  p.y = 10;
		    // Inflate the popup_layout.xml
		    ScrollView viewGroup = (ScrollView)activity.findViewById(R.id.lyt_progress);
		    LayoutInflater layoutInflater = (LayoutInflater) activity
		      .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		    View layout = layoutInflater.inflate(R.layout.lyt_progress, viewGroup);
		  
		    // Creating the PopupWindow
		    final PopupWindow EventRightSwipePopup = new PopupWindow(activity);
		    EventRightSwipePopup.setContentView(layout);
		    EventRightSwipePopup.setWidth(popupWidth);
		    EventRightSwipePopup.setHeight(popupHeight);
		 //  helper.Toastmsg(viewGroup.getLayoutParams().height+"  h", context);
		    EventRightSwipePopup.setFocusable(true);
		  
		    // Some offset to align the popup a bit to the right, and a bit down, relative to button's position.
		    int OFFSET_X = 100;
		    int OFFSET_Y =  p.y -(p.y/4);  //SmarterlifeHelper.getPixels(28,activity.getResources());
		  
		    // Clear the default translucent background
		    EventRightSwipePopup.setBackgroundDrawable(new BitmapDrawable());
		    
		    // Displaying the popup at the specified location, + offsets.
		    EventRightSwipePopup.showAtLocation(layout, Gravity.CENTER, p.x + OFFSET_X, p.y + OFFSET_Y);
		  
		    // Getting a reference to Close button, and close the popup when clicked.
		    EventRightSwipePopup.setOnDismissListener( this);

		  
		  
		   
		    
		
		  
		   
		    
		 }
 
 
 private static int currentYear = 0;
 private static SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy");
 public static String getCurrentDateString() {
		Calendar currentCalendar = Calendar.getInstance();
		currentYear = currentCalendar.get(Calendar.YEAR);
		return dateFormat.format(currentCalendar.getTimeInMillis());
	}
 private static SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm aa"); 
 public static String getCurrentTimeString(int s) {
		//s=0 for star time s=1 for end time
		Calendar currentCalendar = Calendar.getInstance();
		int hours = currentCalendar.get( Calendar.HOUR_OF_DAY );
		int minutes = currentCalendar.get( Calendar.MINUTE );
		
			if (minutes < 30) {
				currentCalendar.setTimeInMillis(currentCalendar
						.getTimeInMillis() + (30 - minutes) * 60 * 1000);
				// currentCalendar.setTimeInMillis(currentCalendar.getTimeInMillis()
				// + 30*60*100);
			}
			if (minutes > 30) {
				currentCalendar.setTimeInMillis(currentCalendar
						.getTimeInMillis() + (60 - minutes) * 60 * 1000);
				// currentCalendar.setTimeInMillis(currentCalendar.getTimeInMillis()
				// + 30*60*100);
			}
			if (s == 1)
				currentCalendar.setTimeInMillis(currentCalendar.getTimeInMillis() + 30*60*1000);
				
			
		     return timeFormat.format(currentCalendar.getTimeInMillis());
	}

 
@Override
public void onDismiss() {
	
	
			
	activity.finish();
	activity.startActivity(activity.getIntent());
			
		}
	// TODO Auto-generated method stub
	

	
	
	
}
