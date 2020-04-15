package com.lckj.lckjlib.map;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

public class MapFrameLayout extends FrameLayout {
 
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
 
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            getParent().requestDisallowInterceptTouchEvent(true);//请求父控件不拦截触摸事件
        } else if (ev.getAction() == MotionEvent.ACTION_UP) {
            getParent().requestDisallowInterceptTouchEvent(false);
        }
 
        return super.dispatchTouchEvent(ev);
    }
 
    public MapFrameLayout(Context context) {
        super(context);
    }
 
    public MapFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
 
    public MapFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}