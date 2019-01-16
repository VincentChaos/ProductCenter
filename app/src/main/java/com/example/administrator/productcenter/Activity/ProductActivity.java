package com.example.administrator.productcenter.Activity;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextClock;

import com.example.administrator.productcenter.Presenter.ProductPresenter;
import com.example.administrator.productcenter.R;
import com.example.administrator.productcenter.Utils.ScreenUtils;
import com.example.administrator.productcenter.View.VerticalViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductActivity extends FragmentActivity{
    //上下滑动切换产品，左右滑动切换型号

    @BindView(R.id.button_left)
    ImageView btnLeft;
    @BindView(R.id.button_right)
    ImageView btnRight;
    @BindView(R.id.button_up)
    ImageView btnUp;
    @BindView(R.id.button_down)
    ImageView btnDown;
    @BindView(R.id.button_return)
    ImageView btnReturn;
    @BindView(R.id.viewpager)
    VerticalViewPager viewPager;
    @BindView(R.id.text_clock)
    TextClock textClock;

    private ProductPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        init();

    }

    @Override
    protected void onResume() {
        super.onResume();
        ScreenUtils.hideBottomUIMenu(this);
    }

    private void init(){
        ButterKnife.bind(this);

        presenter = new ProductPresenter(this,this);

        presenter.setTextClock(textClock);

        presenter.initViewPager(viewPager);

        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.slideUp();
            }
        });
        btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.slideDown();
            }
        });
        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.slideRight();
            }
        });
        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.slideLeft();
            }
        });
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.doReturn();
            }
        });

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        presenter.doTouch(event);
        return super.onTouchEvent(event);
    }


}
