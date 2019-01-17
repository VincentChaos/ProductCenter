package com.example.administrator.productcenter.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.administrator.productcenter.R;
import com.example.administrator.productcenter.Utils.ScreenUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DialogActivity extends AppCompatActivity {

    @BindView(R.id.image_view)
    ImageView iv ;
    private int res, width, height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        init();

    }

    @Override
    protected void onResume() {
        super.onResume();
        ScreenUtils.hideBottomUIMenu(this);
    }

    private void init(){
        ButterKnife.bind(this);

        //获取传入的intent
        Intent i = getIntent();
        res = i.getIntExtra("res",0);
        width = i.getIntExtra("width",0);
        height = i.getIntExtra("height",0);
        iv.setImageResource(res);

        ViewGroup.LayoutParams para;
        para = iv.getLayoutParams();
        para.width = width;
        para.height = height;

        iv.setLayoutParams(para);

        //设置宽高
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = getResources().getDisplayMetrics();     //获取屏幕宽高

        //设置dialog位置大小
        lp.x = 2;
        lp.y = 20;
        lp.width = (int) (d.widthPixels*0.719);
        lp.height = (int)(d.heightPixels*0.930);
        dialogWindow.setAttributes(lp);
    }
}
