package com.example.administrator.productcenter.Presenter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextClock;

import com.example.administrator.productcenter.Activity.ProductActivity;
import com.example.administrator.productcenter.Adapter.ProductAdapter;
import com.example.administrator.productcenter.Fragment.BusFragment;
import com.example.administrator.productcenter.Fragment.DrFragment;
import com.example.administrator.productcenter.Fragment.TeachFragment;
import com.example.administrator.productcenter.Utils.ViewPagerScroller;
import com.example.administrator.productcenter.View.VerticalViewPager;

import java.util.ArrayList;
import java.util.List;

import static com.example.administrator.productcenter.Model.Config.FLIP_DISTANCE;

public class ProductPresenter {
    private Context context;
    private ProductActivity activity;

    private List<Fragment> fragmentList ;
    private TeachFragment replaceFirstFragment;
    private BusFragment busFragment;
    private DrFragment drFragment;
    private TeachFragment teachFragment;
    private BusFragment replaceLastFragment;
    private ProductAdapter productAdapter;
    private VerticalViewPager viewPager;

    float x1 = 0 ,x2 = 0 ,y1 = 0 ,y2 = 0;       //x1，y1手指放下坐标   x2，y2手指离开坐标
    int currentPosition;        //当前位置

    public ProductPresenter(Context context, ProductActivity activity){
        this.context = context;
        this.activity = activity;
    }

    public void setTextClock(TextClock textClock){
        textClock.setFormat12Hour("yyyy-MM-dd HH:mm:ss");   //TextClock设置格式
    }

    public void initViewPager(final VerticalViewPager verticalViewPager){
        this.viewPager = verticalViewPager;

        fragmentList = new ArrayList<>();
        busFragment = new BusFragment();
        drFragment = new DrFragment();
        teachFragment = new TeachFragment();
        replaceFirstFragment = new TeachFragment();
        replaceLastFragment = new BusFragment();

        fragmentList.add(replaceFirstFragment);
        fragmentList.add(busFragment);
        fragmentList.add(drFragment);
        fragmentList.add(teachFragment);
        fragmentList.add(replaceLastFragment);

        productAdapter = new ProductAdapter(activity.getSupportFragmentManager(),fragmentList);

        //设置滑动速度scroller
        ViewPagerScroller scroller = new ViewPagerScroller(context);
        scroller.setScrollDuration(500);
        scroller.initViewPagerScroll(viewPager);
        viewPager.setAdapter(productAdapter);

        //设置预加载页面
        viewPager.setOffscreenPageLimit(3);
        viewPager.setCurrentItem(1);
        viewPager.addOnPageChangeListener(new PageChangeListener());

    }

    private class PageChangeListener implements ViewPager.OnPageChangeListener {
        //页面变换监听器
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            //当滑动至替换页面时，修改当前页面
            if(i == 0){
                currentPosition = fragmentList.size()-2;
            }else if(i == fragmentList.size()-1){
                currentPosition = 1;
            }else {
                currentPosition = i;
            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {
            if (i == ViewPager.SCROLL_STATE_IDLE) {
                viewPager.setCurrentItem(currentPosition, false);
            }
        }
    }

    public void slideUp(){
        //上滑
        if(viewPager.getCurrentItem() != fragmentList.size()) {
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
        }
    }

    public void slideDown(){
        //下滑
        if(viewPager.getCurrentItem() != 0) {
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }

    public void slideRight(){
        //右滑
        switch (viewPager.getCurrentItem()){
            case 1:
                busFragment.slideRight();
                replaceLastFragment.slideRight();
                break;
            case 2:
                drFragment.slideRight();
                break;
            case 3:
                teachFragment.slideRight();
                replaceFirstFragment.slideRight();
        }
    }

    public void slideLeft(){
        //左滑
        switch (viewPager.getCurrentItem()){
            case 1:
                busFragment.slideLeft();
                replaceLastFragment.slideLeft();
                break;
            case 2:
                drFragment.slideLeft();
                break;
            case 3:
                teachFragment.slideLeft();
                replaceFirstFragment.slideLeft();
                break;
        }
    }

    public void doReturn(){}

    public void doTouch(MotionEvent event){
        //执行触摸事件
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                y1 = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:
                //当手指离开的时候
                x2 = event.getX();
                y2 = event.getY();
                if(Math.abs(x1 - x2) > Math.abs(y1 - y2)){
                    if(x1 - x2 > FLIP_DISTANCE) {
                        slideRight();
                    } else if(x2 - x1 > FLIP_DISTANCE) {
                        slideLeft();
                    }
                }else {
                    if (y1 - y2 > FLIP_DISTANCE) {
                        slideUp();
                    } else if (y2 - y1 > FLIP_DISTANCE) {
                        slideDown();
                    }
                }
                break;
        }
    }
}
