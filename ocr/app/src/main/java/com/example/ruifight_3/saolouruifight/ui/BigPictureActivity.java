package com.example.ruifight_3.saolouruifight.ui;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.ruifight_3.saolouruifight.MyApi;
import com.example.ruifight_3.saolouruifight.R;
import com.example.ruifight_3.saolouruifight.baseui.BaseActivity;
import com.example.ruifight_3.saolouruifight.util.Base64Utils;
import com.example.ruifight_3.saolouruifight.util.ToastUtil;
import com.example.ruifight_3.saolouruifight.util.bigutil.PhotoView;

import butterknife.BindView;


/**
 * @author RuiFight-3
 */

public class BigPictureActivity extends BaseActivity {
    @BindView(R.id.iv_photo)
    PhotoView iv_photo;

    @Override
    protected int setLayout() {
        return R.layout.activity_big_picture;
    }

    @Override
    protected void initView() {
        setSteepStatusBar(true);

    }

    @Override
    protected void initData() {
        String type = getIntent().getStringExtra("is");
        //没网
        if (type.equals("1")) {
            if (!Base64Utils.isHttpUrl(MyApi.DAIMAGE)) {
                try {
                    iv_photo.setImageBitmap(Base64Utils.base64ToBitmap(MyApi.DAIMAGE));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                ToastUtil.showInfo(BigPictureActivity.this, "图片格式错误无法查看");
                finish();
            }
            //有网
        } else {
            if (Base64Utils.isHttpUrl(MyApi.DAIMAGE)) {
                String url = MyApi.URL + MyApi.IMAGEURL + MyApi.DAIMAGE;
                Glide.with(BigPictureActivity.this).load(url)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)//禁用磁盘缓存
                        .skipMemoryCache(true)//跳过内存缓存
                        .dontAnimate()
                        .into(iv_photo);
            } else {
                ToastUtil.showInfo(BigPictureActivity.this, "图片格式错误无法查看");
                finish();
            }
        }
    }
}