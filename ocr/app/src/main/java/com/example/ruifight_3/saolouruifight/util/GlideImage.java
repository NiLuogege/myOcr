package com.example.ruifight_3.saolouruifight.util;

import android.app.Activity;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.ruifight_3.saolouruifight.R;
import com.lzy.imagepicker.loader.ImageLoader;

import java.io.File;

/**
 * Created by RuiFight-3 on 2018/7/24.
 */

public class GlideImage  implements ImageLoader {

    @Override
    public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {

        Glide.with(activity)                             //配置上下文
                .load(Uri.fromFile(new File(path)))      //设置图片路径(fix #8,文件名包含%符号 无法识别和显示)
                .error(R.drawable.ic_default_image)           //设置错误图片
                .placeholder(R.drawable.ic_default_image)     //设置占位图片
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)//缓存全尺寸
                .into(imageView);
    }

    @Override
    public void displayImagePreview(Activity activity, String path, ImageView imageView, int width, int height) {
        Glide.with(activity)                             //配置上下文
                .load(Uri.fromFile(new File(path)))      //设置图片路径(fix #8,文件名包含%符号 无法识别和显示)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)//缓存全尺寸
                .into(imageView);
    }

    @Override
    public void clearMemoryCache() {
    }
}
