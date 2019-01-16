package com.example.administrator.productcenter.View;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import static com.example.administrator.productcenter.Model.Config.FLIP_DISTANCE;

public class VerticalViewPager extends ViewPager {
    //竖直方向viewpager
    private boolean hasMove = false;
    private float downXDispatch,downYDispatch;

    public VerticalViewPager(Context context) {
        super(context);
        init();
    }

    public VerticalViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        // The majority of the magic happens here
        setPageTransformer(true, new VerticalPageTransformer());
        // The easiest way to get rid of the overscroll drawing that happens on the btn_left and btn_right
        setOverScrollMode(OVER_SCROLL_NEVER);


    }

    private class VerticalPageTransformer implements ViewPager.PageTransformer {

        @Override
        public void transformPage(View view, float position) {

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the btn_left.
                view.setAlpha(0);

            } else if (position <= 1) { // [-1,1]
                view.setAlpha(1);

                // Counteract the default slide transition
                view.setTranslationX(view.getWidth() * -position);

                //set Y position to swipe in from top
                float yPosition = position * view.getHeight();
                view.setTranslationY(yPosition);

            } else { // (1,+Infinity]
                // This page is way off-screen to the btn_right.
                view.setAlpha(0);
            }
        }
    }

    /**
     * Swaps the X and Y coordinates of your touch event.
     */
    private MotionEvent swapXY(MotionEvent ev) {
        float width = getWidth();
        float height = getHeight();

        float newX = (ev.getY() / height) * width;
        float newY = (ev.getX() / width) * height;

        ev.setLocation(newX, newY);

        return ev;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev){
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
                if (hasMove == false && Math.abs(distanceX) <= FLIP_DISTANCE && Math.abs(distanceY) <= FLIP_DISTANCE) {
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
        swapXY(ev); // return touch coordinates to original reference frame for any child views
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }
}

