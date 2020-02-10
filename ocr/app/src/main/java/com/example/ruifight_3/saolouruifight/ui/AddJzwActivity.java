package com.example.ruifight_3.saolouruifight.ui;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.example.ruifight_3.saolouruifight.MyApi;
import com.example.ruifight_3.saolouruifight.R;
import com.example.ruifight_3.saolouruifight.baseui.BaseActivity;
import com.example.ruifight_3.saolouruifight.util.AnimationUtil;
import com.example.ruifight_3.saolouruifight.util.DiaLogUtil;
import com.example.ruifight_3.saolouruifight.util.SPUtils;
import com.example.ruifight_3.saolouruifight.util.ToastUtil;
import com.example.ruifight_3.saolouruifight.util.uiutil.AndroidBug5497Workaround;
import com.example.ruifight_3.saolouruifight.widget.ClearEditText;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;

/**
 * Created by RuiFight-3 on 2019/6/24.
 * Creator: 张震 (Android  zhangzhen)
 * Describe:添加建筑物
 */
public class AddJzwActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.addjzw_name_el)
    ClearEditText addjzw_name_el;
    @BindView(R.id.addjzw_danyun_el)
    ClearEditText addjzw_danyun_el;
    @BindView(R.id.addjzw_ceng_el)
    ClearEditText addjzw_ceng_el;
    @BindView(R.id.addjzw_fw_el)
    ClearEditText addjzw_fw_el;
    @BindView(R.id.commit_im_addjzw)
    Button commit_im_addjzw;

    private String jzw_jlxdm;

    @Override
    protected int setLayout() {
        return R.layout.activity_addjzw;
    }

    @Override
    protected void initView() {
        setSteepStatusBar(true);
        setTooBarBreakImage(R.mipmap.breakimagee);
        setTooBarTitleText("添加建筑物");
        AndroidBug5497Workaround.assistActivity(this);
        commit_im_addjzw.setOnClickListener(this);
        AnimationUtil.initDouDong();
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        jzw_jlxdm = intent.getStringExtra("jzw_jlxdm");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.commit_im_addjzw:
                String jzw_dizhi = addjzw_name_el.getText().toString().trim();
                String ds_dys = addjzw_danyun_el.getText().toString().trim();
                String ds_cs = addjzw_ceng_el.getText().toString().trim();
                String ds_hs = addjzw_fw_el.getText().toString().trim();
                if (jzw_dizhi.equals("")) {
                    AnimationUtil.setDouDong(addjzw_name_el);
                    ToastUtil.showInfo(AddJzwActivity.this, getString(R.string.jzwname));
                } else if (ds_dys.equals("")) {
                    AnimationUtil.setDouDong(addjzw_danyun_el);
                    ToastUtil.showInfo(AddJzwActivity.this, getString(R.string.dynumber));
                } else if (ds_cs.equals("")) {
                    AnimationUtil.setDouDong(addjzw_ceng_el);
                    ToastUtil.showInfo(AddJzwActivity.this, getString(R.string.cengnumber));
                } else if (ds_hs.equals("")) {
                    AnimationUtil.setDouDong(addjzw_fw_el);
                    ToastUtil.showInfo(AddJzwActivity.this, getString(R.string.fwnumber));
                } else {
                    DiaLogUtil.showDiaLog(this, "正在添加");
                    addJzw(jzw_dizhi, jzw_jlxdm, ds_dys, ds_cs, ds_hs);
                }

                break;
            default:
                break;
        }
    }


    /**
     * 网络添加
     */
    public void addJzw(String jzw_dizhi, String jzw_jlxdm, String ds_dys, String ds_cs, String ds_hs) {
        OkHttpUtils
                .post()
                .url(MyApi.URL + MyApi.ADDJZW)
                .tag(this)
                .addHeader("JSESSIONID", (String) SPUtils.get(AddJzwActivity.this, "JSESSIONID", ""))
                .addParams("jzw_dizhi", jzw_dizhi)
                .addParams("jzw_jlxdm", jzw_jlxdm)
                .addParams("ds_dys", ds_dys)
                .addParams("ds_cs", ds_cs)
                .addParams("ds_hs", ds_hs)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        DiaLogUtil.dismissDiaLog();
                        ToastUtil.showInfo(AddJzwActivity.this, getString(R.string.wufaData));
                    }

                    @Override
                    public void onResponse(String response) {
                        DiaLogUtil.dismissDiaLog();
                        if (response.equals("ok")) {
                            ToastUtil.showInfo(AddJzwActivity.this, "添加成功");
                            finish();
                        } else {
                            ToastUtil.showInfo(AddJzwActivity.this, "添加失败");
                        }
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AnimationUtil.setDongNull();
    }
}
