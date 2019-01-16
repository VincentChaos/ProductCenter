package com.example.administrator.productcenter.Presenter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import com.example.administrator.productcenter.Adapter.TypeAdapter;
import com.example.administrator.productcenter.R;
import com.example.administrator.productcenter.Utils.ViewPagerScroller;
import com.example.administrator.productcenter.View.HorizontalViewPager;
import com.example.administrator.productcenter.View.RobotImageView;

import java.util.ArrayList;

public class TeachPresenter {
    private Context context;
    private Activity activity;
    private HorizontalViewPager viewPager;

    private int viewNum = 0;
    private int currentPosition;

    public TeachPresenter(Context context,Activity activity){
        this.context = context;
        this.activity = activity;
    }

    public void initViewPager(HorizontalViewPager horizontalViewPager) {
        this.viewPager = horizontalViewPager;

        View view1 = LayoutInflater.from(context).inflate(R.layout.layout_robot,null);
        RobotImageView iv1 = view1.findViewById(R.id.image_robot);
        iv1.initImageResource(activity,R.drawable.teach_pink,600,900);

        View view2 = LayoutInflater.from(context).inflate(R.layout.layout_robot,null);
        RobotImageView iv2 = view2.findViewById(R.id.image_robot);
        iv2.initImageResource(activity,R.drawable.teach_blue,600,900);

        View view3 = LayoutInflater.from(context).inflate(R.layout.layout_robot,null);
        RobotImageView iv3 = view3.findViewById(R.id.image_robot);
        iv3.initImageResource(activity,R.drawable.teach_red,600,900);

        View view4 = LayoutInflater.from(context).inflate(R.layout.layout_robot,null);
        RobotImageView iv4 = view4.findViewById(R.id.image_robot);
        iv4.initImageResource(activity,R.drawable.teach_cyan,600,900);

        View replaceFirst = LayoutInflater.from(context).inflate(R.layout.layout_robot,null);
        RobotImageView replace1 = replaceFirst.findViewById(R.id.image_robot);
        replace1.setImageResource(R.drawable.teach_cyan);

        View replaceLast = LayoutInflater.from(context).inflate(R.layout.layout_robot,null);
        RobotImageView replace2 = replaceLast.findViewById(R.id.image_robot);
        replace2.setImageResource(R.drawable.teach_pink);

        ArrayList<View> views = new ArrayList<>();
        views.add(replaceFirst);
        views.add(view1);
        views.add(view2);
        views.add(view3);
        views.add(view4);
        views.add(replaceLast);
        TypeAdapter adapter = new TypeAdapter(views);
        viewNum = views.size();

        //绑定适配器
        viewPager.setAdapter(adapter);
        ViewPagerScroller scroller = new ViewPagerScroller(context);
        scroller.setScrollDuration(800);
        scroller.initViewPagerScroll(viewPager);
        viewPager.setOffscreenPageLimit(viewNum-2);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if(i == 0){
                    currentPosition = viewNum-2;
                }else if(i == viewNum-1){
                    currentPosition = 1;
                }else {
                    currentPosition = i;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {
                if(i == ViewPager.SCROLL_STATE_IDLE){
                    viewPager.setCurrentItem(currentPosition,false);
                }
            }
        });
        viewPager.setCurrentItem(1);
    }

    public void slideLeft(){
        if(viewPager != null && viewPager.getCurrentItem() !=0)
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
    }

    public void slideRight(){
        if(viewPager != null && viewPager.getCurrentItem() != viewNum)
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
    }
}
