package com.example.ruifight_3.saolouruifight;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;

import com.example.ruifight_3.saolouruifight.util.GlideImage;
import com.example.ruifight_3.saolouruifight.util.ScreenUtils;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.view.CropImageView;
import com.nostra13.universalimageloader.cache.disc.impl.LimitedAgeDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.FIFOLimitedMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.tencent.bugly.crashreport.CrashReport;


/**
 * Created by RuiFight-3 on 2018/4/18.
 */

public class MyApplication extends Application {
    public static Context context;
    private int maxImgCount = 6;               //允许选择图片最大数

    @Override
    public void onCreate() {
        super.onCreate();
        // ButterKnife.bind();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }
        //腾讯日志上传 Bugly
        CrashReport.initCrashReport(getApplicationContext(), "874829023d", false);

        context = getApplicationContext();
        //初始化工具类
        ScreenUtils.init(this);
        //初始化图片加载类
        initImageLoader(this);
        //初始化图片选择框架
        initImagePicker();

    }

    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImage());   //设置图片加载器
        imagePicker.setShowCamera(true);                      //显示拍照按钮
        imagePicker.setCrop(false);                           //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true);                   //是否按矩形区域保存
        imagePicker.setSelectLimit(maxImgCount);              //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);                         //保存文件的高度。单位像素
    }


    public static Context getContext() {
        return context;
    }

    public static void initImageLoader(Context context) {
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context)
                .threadPoolSize(3)//线程池内加载的数量
                .threadPriority(Thread.NORM_PRIORITY - 2)//降低线程的优先级保证主UI线程不受太大影响
                .tasksProcessingOrder(QueueProcessingType.FIFO) // ic_default
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new FIFOLimitedMemoryCache(10 * 1024 * 1024))//建议内存设在5-10M,可以有比较好的表现
                .memoryCacheSize(10 * 1024 * 1024)
                .memoryCacheSizePercentage(13)
                .diskCache(new LimitedAgeDiskCache(StorageUtils.getCacheDirectory(context), System.currentTimeMillis()))
                .diskCacheSize(50 * 1024 * 1024)
                .diskCacheFileCount(100)
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO);
                 ImageLoader.getInstance().init(config.build());
    }
}
