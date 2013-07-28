package com.smarterlife.calendar;

import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;




public class CustomEventTaskDragShadowBuilder extends View.DragShadowBuilder {
	
	private View view;
    public CustomEventTaskDragShadowBuilder(View v) {
       super(v);
       this.view  = v;

    }
@Override
public void onDrawShadow(Canvas canvas) {
        super.onDrawShadow(canvas);
        
        int strokeWidth = 4;

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setAlpha(45);
       
        paint.setMaskFilter(new BlurMaskFilter(15, BlurMaskFilter.Blur.INNER));
         
        if(view instanceof Button )
        {  
        	 paint.setColor(Color.RED);
        	canvas.drawRect(10, 10, 10,10, paint);
        	
        }
        else   
        {
        	 paint.setColor(Color.GREEN);
        	canvas.drawCircle(20, 20, 50, paint);
        }
         
    }
    @Override
    public void onProvideShadowMetrics(Point shadowSize, Point touchPoint) {
        shadowSize.set(330,75);
        touchPoint.set(15,15);
    }
}