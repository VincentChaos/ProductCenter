package com.example.administrator.productcenter.View;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.View;

import com.example.administrator.productcenter.Activity.DialogActivity;

public class RobotImageView extends AppCompatImageView{

    private Activity activity;
    private int res, width, height;

    public RobotImageView(Context context) {
        super(context);
    }

    public RobotImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RobotImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void initImageResource(Activity activity,int res,int maxWidth,int maxHeight){
        this.activity = activity;
        this.res = res;
        this.width = maxWidth;
        this.height = maxHeight;
        super.setImageResource(res);
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                doClick();
            }
        });
    }

    public void doClick() {
            DialogActivity dialogActivity = new DialogActivity();
            Intent intent = new Intent(activity,dialogActivity.getClass());
            intent.putExtra("res",res);
            intent.putExtra("width", width);
            intent.putExtra("height", height);
            activity.startActivity(intent);
    }
}
