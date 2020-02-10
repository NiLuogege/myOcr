package com.example.ruifight_3.saolouruifight.ui;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.ruifight_3.saolouruifight.MyApi;
import com.example.ruifight_3.saolouruifight.R;
import com.example.ruifight_3.saolouruifight.baseui.BaseActivity;
import com.example.ruifight_3.saolouruifight.ui.bean.PerSouSuoBean;
import com.example.ruifight_3.saolouruifight.util.JsonUtils;
import com.example.ruifight_3.saolouruifight.util.SPUtils;
import com.example.ruifight_3.saolouruifight.util.ToastUtil;
import com.github.library.BaseRecyclerAdapter;
import com.github.library.BaseViewHolder;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;

/**
 * Created by RuiFight-3 on 2019/4/28.
 * Creator: 张震 (Android  zhangzhen)
 * Describe:
 */

public class PersonnelQueryActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.login_nex_no_rl)
    RelativeLayout login_nex_no_rl;
    @BindView(R.id.main_sousuo_per)
    EditText main_sousuo_per;
    @BindView(R.id.recycleview_per)
    RecyclerView recycleview_per;
    private BaseRecyclerAdapter baseRecyclerAdapter;
    @BindView(R.id.sousuo_button_per)
    Button sousuo_button_per;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    final PerSouSuoBean perSouSuoBean = (PerSouSuoBean) msg.obj;
                    if (perSouSuoBean.isStatus()) {
                        if (perSouSuoBean.getData() != null && perSouSuoBean.getData().size() > 0) {
                            if (baseRecyclerAdapter != null) {
                                baseRecyclerAdapter.setData(perSouSuoBean.getData());
                            } else {
                                baseRecyclerAdapter = new BaseRecyclerAdapter<PerSouSuoBean.DataBean>(PersonnelQueryActivity.this, perSouSuoBean.getData(), R.layout.item_person) {
                                    @Override
                                    protected void convert(BaseViewHolder helper, PerSouSuoBean.DataBean item) {
                                        ImageView imageView = helper.getConvertView().findViewById(R.id.per_item_tou);
                                        helper.setText(R.id.per_name_tv, "姓名: " + item.getXm());
                                        helper.setText(R.id.per_age_tv, "性别: " + item.getXb());
                                        helper.setText(R.id.per_phone_tv, "手机号: " + item.getLxdh());
                                        helper.setText(R.id.per_idcard_tv, "身份证号: " + item.getSfz());
                                        helper.setText(R.id.per_renkou_tv, "地址: " + item.getDizhi_xz());
                                        Glide.with(PersonnelQueryActivity.this).load(MyApi.URL + MyApi.IMAGEURL + item.getPicture())
                                                .diskCacheStrategy(DiskCacheStrategy.NONE)//禁用磁盘缓存
                                                .skipMemoryCache(true)//跳过内存缓存
                                                .dontAnimate()
                                                .into(imageView);
                                    }
                                };
                                recycleview_per.setAdapter(baseRecyclerAdapter);
                            }
                        } else {
                            ToastUtil.shouToast(PersonnelQueryActivity.this, "查询无此人");
                        }
                    }
                    break;
                default:
                    break;
            }

        }
    };


    @Override
    protected int setLayout() {
        return R.layout.activity_personnel_query;
    }

    @Override
    protected void initView() {
        setSteepStatusBar(true);
        setTooBarBreakImage(R.mipmap.breakimagee);
        setTooBarTitleText("人员查询");
        sousuo_button_per.setOnClickListener(this);
        recycleview_per.setLayoutManager(new LinearLayoutManager(PersonnelQueryActivity.this));

    }

    @Override
    protected void initData() {
        perSuoSuo();
    }

    public void perSuoSuo() {
        main_sousuo_per.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (!TextUtils.isEmpty(main_sousuo_per.getText().toString().trim())) {
                        getMessageData(main_sousuo_per.getText().toString().trim());
                    } else {
                        ToastUtil.showInfo(PersonnelQueryActivity.this, "输入搜索内容");
                    }
                }
                return false;
            }
        });
    }

    /**
     * 搜索
     */
    public void getMessageData(String srt) {
        OkHttpUtils
                .post()
                .url(MyApi.URL + MyApi.SOUSUO)
                .tag(this)
                .addHeader("JSESSIONID", (String) SPUtils.get(PersonnelQueryActivity.this, "JSESSIONID", ""))
                .addParams("queryStr", srt)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                        ToastUtil.showInfo(PersonnelQueryActivity.this, getString(R.string.wufaData));
                    }

                    @Override
                    public void onResponse(String response) {
                        try {
                            PerSouSuoBean perSouSuoBean = (PerSouSuoBean) JsonUtils.stringToObject(response, PerSouSuoBean.class);
                            Message msg = new Message();
                            msg.what = 1;
                            msg.obj = perSouSuoBean;
                            handler.sendMessage(msg);
                        } catch (Exception e) {
                            e.printStackTrace();
                            setLogin();
                        }
                    }
                });
    }

    @Override
    public void onNetChange(int netMobile) {
        if (netMobile == 1) {
            //ToastUtil.showInfo(this, "有网络wifi");
            login_nex_no_rl.setVisibility(View.GONE);
        } else if (netMobile == -1) {
            //ToastUtil.showInfo(this, "没有网络");
            login_nex_no_rl.setVisibility(View.VISIBLE);
        } else if (netMobile == 0) {
            //ToastUtil.showInfo(this, "4G");
            login_nex_no_rl.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sousuo_button_per:
                main_sousuo_per.clearFocus();//失去焦点
                if (!TextUtils.isEmpty(main_sousuo_per.getText().toString().trim())) {
                    getMessageData(main_sousuo_per.getText().toString().trim());
                } else {
                    ToastUtil.showInfo(PersonnelQueryActivity.this, "输入搜索内容");
                }
                break;
            default:
                break;
        }
    }
}
