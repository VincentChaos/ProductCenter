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
import butterknife.OnClick;

public class ProductActivity extends FragmentActivity{
    //上下滑动切换产品，左右滑动切换型号

    @BindView(R.id.button_left)
    ImageView buttonLeft;
    @BindView(R.id.button_right)
    ImageView buttonRight;
    @BindView(R.id.button_up)
    ImageView buttonUp;
    @BindView(R.id.button_down)
    ImageView buttonDown;
    @BindView(R.id.button_return)
    ImageView buttonReturn;
    @BindView(R.id.viewpager)
    VerticalViewPager viewPager;
    @BindView(R.id.text_clock)
    TextClock textClock;

    private ProductPresenter productPresenter;

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

        productPresenter = new ProductPresenter(this,this);

        productPresenter.setTextClock(textClock);

        productPresenter.initViewPager(viewPager);

        buttonUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productPresenter.slideUp();
            }
        });
        buttonDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productPresenter.slideDown();
            }
        });
        buttonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productPresenter.slideRight();
            }
        });
        buttonRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productPresenter.slideLeft();
            }
        });
        buttonReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productPresenter.doReturn();
            }
        });

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        productPresenter.doTouch(event);
        return super.onTouchEvent(event);
    }


}
