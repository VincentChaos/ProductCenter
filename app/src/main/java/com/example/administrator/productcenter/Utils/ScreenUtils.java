package com.example.administrator.productcenter.Utils;

import android.app.Activity;
import android.view.View;

public class ScreenUtils {
    private static final int SYSTEM_UI_FLAG_IMMERSIVE_GESTURE_ISOLATED = 0x00004000;

    /**
     * 隐藏虚拟按键，并且全屏
     */
    public static void hideBottomUIMenu(Activity activity) {
        // 隐藏虚拟按键，并且全屏
        // for new api versions.
        View decorView = activity.getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | SYSTEM_UI_FLAG_IMMERSIVE_GESTURE_ISOLATED;
        decorView.setSystemUiVisibility(uiOptions);
    }

    /**
     * 隐藏虚拟按键，并且全屏
     */
    public static void showBottomUIMenu(Activity activity) {
        // 显示虚拟键
        View decorView = activity.getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_VISIBLE;
        decorView.setSystemUiVisibility(uiOptions);

    }
}
