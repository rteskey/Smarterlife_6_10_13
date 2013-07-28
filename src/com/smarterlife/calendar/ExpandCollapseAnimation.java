package com.smarterlife.calendar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
public class ExpandCollapseAnimation extends Animation {
    private View mAnimatedView;
    private int mEndHeight;
    private int mType;
    boolean done = false;

    /**
     * Initializes expand collapse animation, has two types, collapse (1) and expand (0).
     * @param view The view to animate
     * @param duration
     * @param type The type of animation: 0 will expand from gone and 0 size to visible and layout size defined in xml. 
     * 1 will collapse view and set to gone
     */
    public ExpandCollapseAnimation(View view, int duration, int type,  int height) {
        setDuration(duration);
        mAnimatedView = view;
        mEndHeight =    mAnimatedView.getLayoutParams().height;
        mType = type;
        if(mType == 0) {
            mAnimatedView.getLayoutParams().height = 0;
            mAnimatedView.setVisibility(View.VISIBLE);
         
        }
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        
        
       
    //    mAnimatedView.getLayoutParams().height =  (int) (  mEndHeight  * interpolatedTime);
      //  mAnimatedView.requestLayout();
      
        
        if (interpolatedTime < 1.0f) {
            if(mType == 0) {
                mAnimatedView.getLayoutParams().height = (int) (mEndHeight * interpolatedTime);
            } else {
                mAnimatedView.getLayoutParams().height = mEndHeight - (int) (mEndHeight * interpolatedTime);
            }
            mAnimatedView.requestLayout();
        } else {
            if(mType == 0) {
                mAnimatedView.getLayoutParams().height = mEndHeight;
                mAnimatedView.requestLayout();
            } else {
                mAnimatedView.getLayoutParams().height = 0;
                mAnimatedView.setVisibility(View.GONE);
                mAnimatedView.requestLayout();
                mAnimatedView.getLayoutParams().height = mEndHeight;
                mAnimatedView.invalidate();
                View parent  =(View)mAnimatedView.getParent();
                parent.invalidate();
            }
        }
    }
    
    
    @Override
    public void initialize(int width, int height, int parentWidth,
            int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
    }

    @Override
    public boolean willChangeBounds() {
        return true;
    }
    
}