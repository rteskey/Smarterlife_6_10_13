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
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TableLayout.LayoutParams;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import apphelper.SmarterlifeHelper;
 
public class EventFrag_L extends Fragment  implements OnDismissListener{
	//SmarterlifeHelper helper = new SmarterlifeHelper( );
	
	 private static Activity activity;
	 private static Display ScreenDisplay;
     private static Event event; 
	
    public EventFrag_L (){
    	
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
        View v = inflater.inflate(R.layout.lyt_event_item_l, container,false);
       // Point eventPoint = new Point();
     //   ScreenDisplay =  SmarterlifeHelper.ScreenDisplay;
     //  ScreenDisplay.getSize(eventPoint);
        event =(Event)getArguments().getSerializable("event");
		

       PopulateView(v);
        
       
     
    
        
//      

        
        return   v;
    }

//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        
//       
//     //  c=activity.getApplicationContext();
//    }
    
	 // @SuppressLint("NewApi")
	
	private void PopulateView(View v) {
	
		TextView  txtEventTitle =  (TextView)v.findViewById(R.id.txt_event_l);
		txtEventTitle.setText(event.getEventName());
		
		
		
        
        
	}
	

	





	   
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        // Make sure that we are currently visible
        if (this.isVisible()) {
            // If we are becoming invisible, then...
            if (isVisibleToUser) {
            	// Point eventPoint = new Point();
              //   ScreenDisplay =  SmarterlifeHelper.ScreenDisplay;
             //   ScreenDisplay.getSize(eventPoint);
                event =(Event)getArguments().getSerializable("event");
            	
                SmarterlifeHelper sh = new SmarterlifeHelper(activity);
                sh.showEventPopup(activity, event);
                
            	
            	
            	
            }
        }
    }

	
	 @Override
		public void onDismiss() {
			
			getActivity().finish();
			getActivity().startActivity(getActivity().getIntent());
			
		}
	
	
	
}
    
