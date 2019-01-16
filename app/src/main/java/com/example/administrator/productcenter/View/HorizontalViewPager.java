package com.example.administrator.productcenter.View;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import static com.example.administrator.productcenter.Model.Config.FLIP_DISTANCE;

public class HorizontalViewPager extends ViewPager {

    private boolean hasMove = false;
    private float downXDispatch,downYDispatch;

    public HorizontalViewPager(@NonNull Context context) {
        super(context);
    }

    public HorizontalViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //是否拦截点击事件
        int actionMasked = ev.getActionMasked();
        switch (actionMasked) {
            case MotionEvent.ACTION_DOWN:
                hasMove = false;
                downXDispatch = ev.getX();
                downYDispatch = ev.getY();
                return false;//首先子类优先处理 点击事件，
            case MotionEvent.ACTION_MOVE:
                float distanceX = ev.getX() - downXDispatch;//表示触摸到哪里，但是 你滑动具体是多少是得转换的
                float distanceY = ev.getY() - downYDispatch;
                if (!hasMove && Math.abs(distanceX) <= FLIP_DISTANCE && Math.abs(distanceY) <= FLIP_DISTANCE) {
                    //点击事件
                    return false;

                } else {
                    return true;
                }

            case MotionEvent.ACTION_UP:
                if (hasMove) {
                    return false;
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }

}
