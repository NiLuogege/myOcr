package com.example.ruifight_3.saolouruifight.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.ruifight_3.saolouruifight.R;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by RuiFight-3 on 2018/5/16.
 */

public class GlideImageLoader  extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context)
                    .load(path)
                    .diskCacheStrategy( DiskCacheStrategy.NONE )//禁用磁盘缓存
                    .skipMemoryCache( true )//跳过内存缓存
                    .placeholder(R.drawable.wutupian)
                    .into(imageView);
  }
}
