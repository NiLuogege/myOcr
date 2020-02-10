package com.example.ruifight_3.saolouruifight.util;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.RelativeSizeSpan;

/**
 * Created by RuiFight-3 on 2018/7/2.
 */

public class ConvertUtils {
    /**
     * dp值转换成px值
     *
     * @param dpValue dp值
     * @return px值
     */
    public static int dp2px(Context context, final float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * px值转换成dp值
     *
     * @param pxValue px值
     * @return dp值
     */
    public static int px2dp(Context context, final float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * sp值转换成px值
     *
     * @param spValue sp值
     * @return px值
     */
    public static int sp2px(Context context, final float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * px值转换成sp值
     *
     * @param pxValue px值
     * @return sp值
     */
    public static int px2sp(Context context, final float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 改变字符串中的数字字体大小
     *
     * @param str
     * @return
     */
    public static SpannableStringBuilder setNumColor(String str) {
        SpannableStringBuilder style = new SpannableStringBuilder(str);
        for (int i = 0; i < str.length(); i++) {
            char a = str.charAt(i);
            if (a >= '0' && a <= '9') {
                //style.setSpan(new ForegroundColorSpan(Color.RED), i, i + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                style.setSpan(new RelativeSizeSpan(1.3f), i, i + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        return style;
    }
    /**
     * 判断字符串是否包含字符
     */
    public static boolean isText(String str,String is){
        boolean issa;
        boolean status = str.contains(is);
        //包含
        if (status) {
            issa = status;
            return issa;
            //不包含
        } else {
            issa = status;
            return issa;
        }
    }
}
