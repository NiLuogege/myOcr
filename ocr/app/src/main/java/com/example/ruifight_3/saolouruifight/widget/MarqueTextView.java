package com.example.ruifight_3.saolouruifight.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by RuiFight-3 on 2018/4/18.
 */

@SuppressLint("AppCompatCustomView")
public class MarqueTextView extends TextView {
    public MarqueTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public MarqueTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MarqueTextView(Context context) {
        super(context);
    }
    @Override

    public boolean isFocused() {
        //就是把这里返回true即滚动
        return false;
    }
}
