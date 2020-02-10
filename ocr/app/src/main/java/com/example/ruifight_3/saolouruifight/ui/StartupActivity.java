package com.example.ruifight_3.saolouruifight.ui;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import com.example.ruifight_3.saolouruifight.R;
import com.example.ruifight_3.saolouruifight.baseui.BaseActivity;

/**
 * Created by RuiFight-3 on 2019/7/26.
 * Creator: 张震 (Android  zhangzhen)
 * Describe:启动activity
 */
public class StartupActivity extends BaseActivity {
    private Handler handler;
    private Runnable runnable;
    public static final int STARTUP_DELAY = 300;
    public static final int ANIM_ITEM_DURATION = 1000;
    public static final int ITEM_DELAY = 300;

    private boolean animationStarted = false;

    @Override
    protected int setLayout() {
        return R.layout.activity_startup;
    }

    @Override
    protected void initView() {
        setSteepStatusBar(true);
        //延迟5S后发送handler信息
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(StartupActivity.this, LoginHomeActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.stop, R.anim.start);
            }
        }, 2000);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {

        if (!hasFocus || animationStarted) {
            return;
        }
        animate();

        super.onWindowFocusChanged(hasFocus);
    }

    /**
     * 参考 https://github.com/saulmm/onboarding-examples-android
     */
    private void animate() {
        ImageView logoImageView = (ImageView) findViewById(R.id.img_logo);
        ViewGroup container = (ViewGroup) findViewById(R.id.container);

        ViewCompat.animate(logoImageView)
                .translationY(-250)
                .setStartDelay(STARTUP_DELAY)
                .setDuration(ANIM_ITEM_DURATION).setInterpolator(
                new DecelerateInterpolator(1.2f)).start();


        for (int i = 0; i < container.getChildCount(); i++) {
            View v = container.getChildAt(i);
            ViewPropertyAnimatorCompat viewAnimator;

            if (!(v instanceof Button)) {
                viewAnimator = ViewCompat.animate(v)
                        .translationY(-60).alpha(1)
                        .setStartDelay((ITEM_DELAY * i) + 500)
                        .setDuration(1000);
                viewAnimator.setInterpolator(new DecelerateInterpolator()).start();
            }
        }
    }


    @Override
    protected void initData() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (runnable != null) {
            handler.removeCallbacks(runnable);
        }
    }

    /**
     * 屏蔽返回键
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //不执行父类点击事件
            return true;
        }
        //继续执行父类其他点击事件
        return super.onKeyDown(keyCode, event);
    }
}
