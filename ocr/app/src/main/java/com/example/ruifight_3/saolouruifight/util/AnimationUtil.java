package com.example.ruifight_3.saolouruifight.util;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;

/**
 * Created by RuiFight-3 on 2019/6/13.
 * Creator: 张震 (Android  zhangzhen)
 * Describe:
 */
public class AnimationUtil {
    //左右抖动动画
    private static TranslateAnimation animation;

    /**
     * 初始化左右抖动动画
     */
    public static void initDouDong() {
        animation = new TranslateAnimation(0, -5, 0, 0);
        animation.setInterpolator(new OvershootInterpolator());
        animation.setDuration(100);
        animation.setRepeatCount(3);
        animation.setRepeatMode(Animation.REVERSE);
    }

    /**
     * 设置抖动动画
     */
    public static void setDouDong(View view) {
        if (animation != null) {
            view.startAnimation(animation);
        }
    }

    /**
     * 制空抖动动画
     */
    public static void setDongNull() {
        if (animation != null) {
            animation = null;
        }
    }

    /**
     * 初始化隐藏和显示动画
     */
    public static void initHidAnim() {
        //暂时先不往工具类里写 都在页面上
    }
}
