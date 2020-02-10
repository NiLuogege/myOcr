package com.example.ruifight_3.saolouruifight.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by RuiFight-3 on 2019/4/1.
 * Creator: 张震 (Android  zhangzhen)
 * Describe:
 */
public class MyRecycleview extends RecyclerView {
    public MyRecycleview(Context context) {
        super(context);
    }

    public MyRecycleview(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRecycleview(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean canScrollHorizontally(int direction) {
        return false;
    }
}
