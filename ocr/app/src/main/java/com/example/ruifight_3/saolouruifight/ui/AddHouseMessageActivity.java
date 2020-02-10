package com.example.ruifight_3.saolouruifight.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.ruifight_3.saolouruifight.MyApi;
import com.example.ruifight_3.saolouruifight.R;
import com.example.ruifight_3.saolouruifight.baseui.BaseActivity;
import com.example.ruifight_3.saolouruifight.db.datamessage.DanYuanMessage;
import com.example.ruifight_3.saolouruifight.db.datamessage.LouMessage;
import com.example.ruifight_3.saolouruifight.db.util.FileUtils;
import com.example.ruifight_3.saolouruifight.ui.adapter.LouParticAdapter;
import com.example.ruifight_3.saolouruifight.ui.adapter.SqlLouParticAdapter;
import com.example.ruifight_3.saolouruifight.ui.bean.AddHouseBean;
import com.example.ruifight_3.saolouruifight.ui.bean.SqlFangzhuBean;
import com.example.ruifight_3.saolouruifight.util.DiaLogUtil;
import com.example.ruifight_3.saolouruifight.util.JsonUtils;
import com.example.ruifight_3.saolouruifight.util.NetWorkUtil;
import com.example.ruifight_3.saolouruifight.util.ToastUtil;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by RuiFight-3 on 2018/5/9.
 * 添加房主信息
 */

public class AddHouseMessageActivity extends BaseActivity {

    @BindView(R.id.add_recycleview)
    RecyclerView recyclerView;
    private String floorCode; //建筑物
    private String jiedaoCode; //街道
    private String Huid;//房间
    private String addressname;
    @BindView(R.id.login_nex_no_rl)
    RelativeLayout login_nex_no_rl;
    private LouParticAdapter adapter;
    private SqlLouParticAdapter sqlLouParticAdapter;
    private DanYuanMessage danYuanMessage;
    private LouMessage louMessage;
    private int isshow = 0;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 1:
                    AddHouseBean addHouseBean = (AddHouseBean) msg.obj;
                    if (adapter == null) {
                        adapter = new LouParticAdapter(AddHouseMessageActivity.this, floorCode, jiedaoCode, Huid, addressname, addHouseBean);
                        recyclerView.setLayoutManager(new LinearLayoutManager(AddHouseMessageActivity.this));
                        recyclerView.setAdapter(adapter);
                    } else {
                        adapter.setData(addHouseBean);
                    }

                    break;

                default:
                    break;
            }
        }
    };

    @Override
    protected int setLayout() {
        return R.layout.activity_add_housemessage;
    }

    @Override
    protected void initView() {
        setSteepStatusBar(true);
        setTooBarBreakImage(R.mipmap.breakimagee);

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        floorCode = intent.getStringExtra("floorCode");
        jiedaoCode = intent.getStringExtra("jiedaoCode");
        Huid = intent.getStringExtra("Huid");
        addressname = intent.getStringExtra("addressname");
        setTooBarTitleText("街路巷列表" + ">" + addressname);

    }

    /**
     * 没网操作
     *
     * @param
     */

    public void getSql(String huid) {
        SqlFangzhuBean sqlFangzhuBean = new SqlFangzhuBean();
        if (danYuanMessage == null) {
            danYuanMessage = new DanYuanMessage(AddHouseMessageActivity.this);
        }
        //房主信息
        sqlFangzhuBean = danYuanMessage.selectHid(huid);
        //人员信息
        List<AddHouseBean.DataBean.RyListBean> list = new ArrayList<>();
        list = danYuanMessage.selectRenYuan(huid);

        if (sqlLouParticAdapter == null) {
            sqlLouParticAdapter = new SqlLouParticAdapter(AddHouseMessageActivity.this, floorCode, jiedaoCode, Huid, addressname, sqlFangzhuBean, list);
            recyclerView.setLayoutManager(new LinearLayoutManager(AddHouseMessageActivity.this));
            recyclerView.setAdapter(sqlLouParticAdapter);
        } else {
            sqlLouParticAdapter.setData(sqlFangzhuBean, list);
        }
    }


    public void getFangWuMess(String floorCode, String jiedaoCode, String Huid) {
        OkHttpUtils
                .post()
                .url(MyApi.URL + MyApi.GETFANGWU_MESSAGE + Huid + "/" + jiedaoCode + "/" + floorCode)
                .tag(this)
                .addParams("", "")
                .build()
                .execute(new StringCallback() {

                    @Override
                    public void onError(Request request, Exception e) {
                        DiaLogUtil.dismissDiaLog();
                    }

                    @Override
                    public void onResponse(String response) {
//                        Log.e(TAG, "loginMessageSSSSSS" + response);
                        //接数据   2018 7 月6 号 放假前
                        DiaLogUtil.dismissDiaLog();
                        try {
                            AddHouseBean addHouseBean = (AddHouseBean) JsonUtils.stringToObject(response, AddHouseBean.class);
                            Message msg = new Message();
                            msg.what = 1;
                            msg.obj = addHouseBean;
                            handler.sendMessage(msg);
                        } catch (Exception e) {
                            e.printStackTrace();
                            setLogin();
                        }
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!TextUtils.isEmpty(floorCode)) {
            if (NetWorkUtil.isConn(AddHouseMessageActivity.this) == false) {
                //没有网 走数据库查询

                if (FileUtils.fileIsExists(MyApi.FileLoad + MyApi.DBNAME) == true) {
                    if (louMessage == null) {
                        louMessage = new LouMessage(AddHouseMessageActivity.this);
                    }
                    if (louMessage.tabbleIsExist("t_jzw_jlx") == true) {
                        getSql(Huid);
                    }
                }
            } else {
                //有网
                //房屋信息
                if (isshow == 0) {
                    DiaLogUtil.showDiaLog(this, "加载数据中");
                }
                getFangWuMess(floorCode, jiedaoCode, Huid);
                isshow = 1;
            }
        }
    }

    @Override
    public void onNetChange(int netMobile) {
        if (netMobile == 1) {
            //ToastUtil.showInfo(this, "有网络wifi");
            login_nex_no_rl.setVisibility(View.GONE);

        } else if (netMobile == -1) {
            //ToastUtil.showInfo(this, "没有网络");
            login_nex_no_rl.setVisibility(View.VISIBLE);
            handler.post(new Runnable() {
                @Override
                public void run() {

                    if (FileUtils.fileIsExists(MyApi.FileLoad + MyApi.DBNAME) == true) {
                        if (louMessage == null) {
                            louMessage = new LouMessage(AddHouseMessageActivity.this);
                        }
                        if (louMessage.tabbleIsExist("t_jzw_jlx") == true) {
                            getSql(Huid);
                        } else {
                            ToastUtil.showInfo(AddHouseMessageActivity.this, "请返回首页下载离线数据");
                        }
                    } else {
                        ToastUtil.showInfo(AddHouseMessageActivity.this, "请返回首页下载离线数据");
                    }
                }
            });
        } else if (netMobile == 0) {
            //ToastUtil.showInfo(this, "4G");
            login_nex_no_rl.setVisibility(View.GONE);
        }
    }
}
