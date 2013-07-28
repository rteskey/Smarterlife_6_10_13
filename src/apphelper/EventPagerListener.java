package apphelper;

import com.smarterlife.calendar.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.provider.ContactsContract.CommonDataKinds.Event;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.Toast;


public class EventPagerListener  implements OnPageChangeListener, OnDismissListener {
     
	private static Activity activity;
	private static com.smarterlife.calendar.Event event;
	private static Display ScreenDisplay;
	private static ViewPager vp;
	public  EventPagerListener( final Activity activity,   ViewPager vp) // com.smarterlife.calendar.Event event
	{   
		this.vp = vp;
		this.activity = activity;
		this.event = (com.smarterlife.calendar.Event)vp.getTag();
		ScreenDisplay = activity.getWindowManager().getDefaultDisplay();
		this.vp.setOnFocusChangeListener(new OnFocusChangeListener(){

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				Toast.makeText(activity, "v", Toast.LENGTH_SHORT).show();
				
				
			}} );
		
	}
	
	
	


	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onPageSelected(int position) {
	
		
	   
		
		if(position == 0 )
		{
		 // showEventPopup();    
		
		}
		if(position == 2 )
		{
	
		// showEventDeletePopup();
		}
		
	}

	
	private void showEventPopup() {
		 Point p = new Point();
		 ScreenDisplay.getSize(p);
		 
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

	 
	   Button btnclose = (Button) layout.findViewById(R.id.btn_close);
	   btnclose.setOnClickListener(new OnClickListener(){

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			EventLeftSwipePopup.dismiss();
		}});  	
	  
	   
	    
	 }

	public   void showEventDeletePopup() {
		 Point p = new Point();
		 ScreenDisplay.getSize(p);
		 
		 int popupWidth = p.x;
	    int popupHeight = p.y/2;
	   
	    
	 //   p.x = 10;
//	  p.y = 10;
	    // Inflate the popup_layout.xml
	    ScrollView viewGroup = (ScrollView)activity. findViewById(R.id.lyt_event_delete);
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
	    int OFFSET_Y =  p.y/2;  //SmarterlifeHelper.getPixels(28,activity.getResources());
	  
	    // Clear the default translucent background
	    EventRightSwipePopup.setBackgroundDrawable(new BitmapDrawable());
	  
	    // Displaying the popup at the specified location, + offsets.
	    EventRightSwipePopup.showAtLocation(layout, Gravity.NO_GRAVITY, p.x + OFFSET_X, p.y + OFFSET_Y);
	  
	    // Getting a reference to Close button, and close the popup when clicked.
	    EventRightSwipePopup.setOnDismissListener(this);

	   TextView txtDeleteMsg = (TextView) layout.findViewById(R.id.txt_event_delete);
	   txtDeleteMsg.setText("Delete Event?" + event.getEventName());
	    
	    
	   Button btnclose = (Button) layout.findViewById(R.id.btn_event_delete_close);
	   btnclose.setOnClickListener(new OnClickListener(){

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			EventRightSwipePopup.dismiss();
		}});  	
	  
	   
	    
	 }

	@Override
	public void onDismiss() {
		
		activity.finish();
		activity.startActivity(activity.getIntent());
	}





	

}
