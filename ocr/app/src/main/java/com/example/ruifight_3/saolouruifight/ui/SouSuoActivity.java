package com.example.ruifight_3.saolouruifight.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import com.example.ruifight_3.saolouruifight.MyApi;
import com.example.ruifight_3.saolouruifight.R;
import com.example.ruifight_3.saolouruifight.baseui.BaseActivity;
import com.example.ruifight_3.saolouruifight.ui.bean.SouSuoBean;
import com.example.ruifight_3.saolouruifight.util.JsonUtils;
import com.example.ruifight_3.saolouruifight.util.SPUtils;
import com.example.ruifight_3.saolouruifight.util.ToastUtil;
import com.example.ruifight_3.saolouruifight.widget.ClearEditText;
import com.example.ruifight_3.saolouruifight.widget.MarqueTextView;
import com.github.library.BaseRecyclerAdapter;
import com.github.library.BaseViewHolder;
import com.github.library.listener.OnRecyclerItemClickListener;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.BindView;

/**
 * Created by RuiFight-3 on 2018/5/18.
 */

public class SouSuoActivity extends BaseActivity {

    @BindView(R.id.ClearEditText)
    ClearEditText ClearEditText;
    @BindView(R.id.sousuo_recycleview)
    RecyclerView sousuo_recycleview;
    @SuppressLint("HandlerLeak")
    BaseRecyclerAdapter baseRecyclerAdapter;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 1:

                    SouSuoBean souSuoBean = (SouSuoBean) msg.obj;
                    if (souSuoBean.getFloorList() != null && souSuoBean.getFloorList().size() > 0) {
                        final List<SouSuoBean.FloorListBean> list = souSuoBean.getFloorList();
                        baseRecyclerAdapter = new BaseRecyclerAdapter<SouSuoBean.FloorListBean>(SouSuoActivity.this, list, R.layout.item_home_recycleview) {
                            @Override
                            protected void convert(BaseViewHolder helper, SouSuoBean.FloorListBean item) {
                                MarqueTextView marqueTextView = helper.getConvertView().findViewById(R.id.main_recycle_item_tv);
                                marqueTextView.setText(item.getAddressName());
                                marqueTextView.setSelected(true);
                            }
                        };
                        sousuo_recycleview.setAdapter(baseRecyclerAdapter);

                        baseRecyclerAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {

                                Intent intent = new Intent(SouSuoActivity.this, MainActivity.class);
                                intent.putExtra("address", list.get(position).getAddressName());
                                intent.putExtra("id", list.get(position).getFloorCode());
                                startActivity(intent);
                                finish();
                            }
                        });
                    }

                    break;

                default:
                    break;
            }
        }
    };


    @Override
    protected int setLayout() {
        return R.layout.activity_sousuo;
    }

    @Override
    protected void initView() {
        setInvisible(1);
        setSteepStatusBar(true);
        //setTooBarLeftImage(R.drawable.brakim);
        setToolBarTitle("搜索");

        sousuo_recycleview.setLayoutManager(new LinearLayoutManager(SouSuoActivity.this));

    }

    @Override
    protected void initData() {

        ClearEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(s)) {
                    Log.e("ss", "没变");
                } else {
                    Log.e("ss", "变了");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                //ToastUtil.showInfo(SouSuoActivity.this,ClearEditText.getText().toString().trim());
                // ClearEditText.requestFocus();//获取焦点
                ClearEditText.setFocusable(true);
                ClearEditText.setFocusableInTouchMode(true);
                souSuo(ClearEditText.getText().toString().trim());
            }
        });
    }

    public void souSuo(String selectFloorName) {

        OkHttpUtils
                .post()
                .url(MyApi.URL + MyApi.GETADDRESS)
                .tag(this)
                .addHeader("JSESSIONID", (String) SPUtils.get(SouSuoActivity.this, "JSESSIONID", ""))
                .addParams("selectFloorName", selectFloorName)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        ToastUtil.showInfo(SouSuoActivity.this, e.getMessage() + "onError");
                    }

                    @Override
                    public void onResponse(String response) {

                        try {
                            SouSuoBean souSuoBean = (SouSuoBean) JsonUtils.stringToObject(response, SouSuoBean.class);
                            Message msg = new Message();
                            msg.what = 1;
                            msg.obj = souSuoBean;
                            handler.sendMessage(msg);
                        } catch (Exception e) {
                            e.printStackTrace();
                            ToastUtil.showInfo(SouSuoActivity.this, getString(R.string.nowufu));
                        }
                    }
                });
    }
}