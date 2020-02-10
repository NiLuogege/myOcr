package com.example.ruifight_3.saolouruifight.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.ruifight_3.saolouruifight.MyApi;
import com.example.ruifight_3.saolouruifight.R;
import com.example.ruifight_3.saolouruifight.baseui.BaseActivity;
import com.example.ruifight_3.saolouruifight.ui.bean.TaskBean;
import com.example.ruifight_3.saolouruifight.util.GlideImageLoader;
import com.example.ruifight_3.saolouruifight.util.JsonUtils;

import com.example.ruifight_3.saolouruifight.util.ToastUtil;

import com.squareup.okhttp.Request;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
 * Created by RuiFight-3 on 2018/5/15.
 */

public class TaskCompleteActivity extends BaseActivity {

    private int followHouseId;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.libear)
    LinearLayout layout;
    @BindView(R.id.task_tv_shuoming)
    EditText task_tv_shuoming;
    @BindView(R.id.task_tv_miaoshu)
    EditText task_tv_miaoshu;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 1:
                    TaskBean taskBean = (TaskBean) msg.obj;

                    if (taskBean.getData().getContent() != null) {
                        task_tv_shuoming.setText(taskBean.getData().getContent());
                    }
                    if (taskBean.getData().getMarkCause() != null) {
                        task_tv_miaoshu.setText(taskBean.getData().getMarkCause());
                    }
                    if (taskBean.getData().getImg() != null) {
                        String str = taskBean.getData().getImg();
                        List<String> result = Arrays.asList(str.split(","));
                        initViewPager(result);
                    }
                    task_tv_shuoming.setEnabled(false);
                    task_tv_miaoshu.setEnabled(false);

                    break;

                default:
                    break;
            }
        }
    };

    @Override
    protected int setLayout() {
        return R.layout.activity_task_complete;
    }

    @Override
    protected void initView() {
        setSteepStatusBar(true);
        setTooBarBreakImage(R.mipmap.breakimagee);
        setTooBarTitleText("关注详情描述");


    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        followHouseId = intent.getIntExtra("followHouseId", 0);
        getTaskMess(followHouseId);
    }

    public void getTaskMess(int followHouseId) {
        OkHttpUtils
                .post()
                .url(MyApi.URL + MyApi.CHAKAN + followHouseId)
                .tag(this)
                .addParams("", "")
                .build()
                .execute(new StringCallback() {

                    @Override
                    public void onError(Request request, Exception e) {
                        ToastUtil.showInfo(TaskCompleteActivity.this,  getString(R.string.wufaData));
                    }

                    @Override
                    public void onResponse(String response) {
                        try {
                            TaskBean taskBean = (TaskBean) JsonUtils.stringToObject(response, TaskBean.class);
                            Message msg = new Message();
                            msg.what = 1;
                            msg.obj = taskBean;
                            handler.sendMessage(msg);
                        } catch (Exception e) {
                            e.printStackTrace();
                            setLogin();
                        }
                    }
                });

    }

    @SuppressLint("ClickableViewAccessibility")
    public void initViewPager(final List<String> list1) {

        List<String> list2 = new ArrayList<>();

        for (int i = 0; i < list1.size(); i++) {
            list2.add(MyApi.URL + MyApi.IMAGEURL + list1.get(i));
        }

        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(list2);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(1500);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();

    }
}