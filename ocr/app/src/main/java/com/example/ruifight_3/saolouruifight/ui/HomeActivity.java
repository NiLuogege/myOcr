package com.example.ruifight_3.saolouruifight.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.ruifight_3.saolouruifight.MyApi;
import com.example.ruifight_3.saolouruifight.R;
import com.example.ruifight_3.saolouruifight.baseui.BaseActivity;
import com.example.ruifight_3.saolouruifight.db.datamessage.LouMessage;
import com.example.ruifight_3.saolouruifight.db.util.FileUtils;
import com.example.ruifight_3.saolouruifight.ui.bean.HomeBean;
import com.example.ruifight_3.saolouruifight.util.DiaLogUtil;
import com.example.ruifight_3.saolouruifight.util.JsonUtils;
import com.example.ruifight_3.saolouruifight.util.NetWorkUtil;
import com.example.ruifight_3.saolouruifight.util.SPUtils;
import com.example.ruifight_3.saolouruifight.util.ToastUtil;
import com.example.ruifight_3.saolouruifight.widget.MarqueTextView;
import com.github.library.BaseRecyclerAdapter;
import com.github.library.BaseViewHolder;
import com.github.library.listener.OnRecyclerItemClickListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author RuiFight-3
 * @date 2018/4/18
 * 楼信息
 */

public class HomeActivity extends BaseActivity implements View.OnClickListener {
    private RecyclerView home_recyc;
    private BaseRecyclerAdapter baseRecyclerAdapter;
    private LouMessage louMessage;
    private List<HomeBean.DataBean.JlxListBean> loginBeans;
    @BindView(R.id.home_smart)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.login_nex_no_rl)
    RelativeLayout login_nex_no_rl;
    private List<HomeBean.DataBean.JlxListBean> lists;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 1:
                    HomeBean homeBean = (HomeBean) msg.obj;
                    if (homeBean.getData().getJlxList() != null && homeBean.getData().getJlxList().size() > 0) {
                        lists = homeBean.getData().getJlxList();

                        if (baseRecyclerAdapter == null) {
                            baseRecyclerAdapter = new BaseRecyclerAdapter<HomeBean.DataBean.JlxListBean>(HomeActivity.this, lists, R.layout.item_home_recycleview) {
                                @Override
                                protected void convert(BaseViewHolder helper, HomeBean.DataBean.JlxListBean item) {
                                    MarqueTextView marqueTextView = helper.getConvertView().findViewById(R.id.main_recycle_item_tv);
                                    marqueTextView.setText(item.getJlxmc());
                                    marqueTextView.setSelected(true);
                                }
                            };
                            home_recyc.setAdapter(baseRecyclerAdapter);
                        } else {
                            baseRecyclerAdapter.setData(lists);
                            lists = baseRecyclerAdapter.getData();
                        }

                        baseRecyclerAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {

                                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                                intent.putExtra("address", lists.get(position).getJlxmc());
                                intent.putExtra("id", lists.get(position).getJlxdm());
                                startActivity(intent);
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
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {
        setSteepStatusBar(true);
        setTooBarBreakImage(R.mipmap.breakimagee);
        setTooBarTitleText("街路巷列表");
        home_recyc = findViewById(R.id.home_recycleview);
        home_recyc.setLayoutManager(new LinearLayoutManager(HomeActivity.this));
        //是否启用上拉加载功能
        smartRefreshLayout.setEnableLoadMore(false);
        //是否启用下拉刷新功能
        smartRefreshLayout.setEnableRefresh(true);
        //是否下拉Header的时候向下平移列表或者内容
        smartRefreshLayout.setEnableHeaderTranslationContent(true);
        //设置Header
        smartRefreshLayout.setRefreshHeader(new ClassicsHeader(HomeActivity.this));
    }

    @Override
    protected void initData() {

        if (NetWorkUtil.isConn(HomeActivity.this) == false) {
            //没有网 走数据库查询
            handler.post(new Runnable() {
                @Override
                public void run() {
                    //是否有数据文件  只需要再第一层判断
                    if (FileUtils.fileIsExists(MyApi.FileLoad + MyApi.DBNAME) == true) {
                        if (louMessage == null) {
                            louMessage = new LouMessage(HomeActivity.this);
                        }
                        if (louMessage.tabbleIsExist("t_jzw_jlx") == true) {
                            getSql();
                        }
                    }
                }
            });
        } else {
            //有网
            getAddress(1);
        }
        //下拉刷新
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                if (NetWorkUtil.isConn(HomeActivity.this) == false) {
                    //没有网 走数据库查询
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //是否有数据文件  只需要再第一层判断
                            if (FileUtils.fileIsExists(MyApi.FileLoad + MyApi.DBNAME) == true) {
                                if (louMessage == null) {
                                    louMessage = new LouMessage(HomeActivity.this);
                                }
                                if (louMessage.tabbleIsExist("t_jzw_jlx") == true) {
                                    getSql();
                                }
                            }
                        }
                    });
                } else {
                    //有网
                    getAddress(2);
                }

            }
        });
    }

    public void getSql() {
        if (louMessage == null) {
            louMessage = new LouMessage(HomeActivity.this);
        }
        if (loginBeans == null) {
            loginBeans = new ArrayList<>();
        }
        loginBeans = louMessage.selectLou();

        if (loginBeans != null && loginBeans.size() > 0) {

            //结束刷新（成功）
            smartRefreshLayout.finishRefresh(true);

            if (baseRecyclerAdapter == null) {
                baseRecyclerAdapter = new BaseRecyclerAdapter<HomeBean.DataBean.JlxListBean>(HomeActivity.this, loginBeans, R.layout.item_home_recycleview) {
                    @Override
                    protected void convert(BaseViewHolder helper, HomeBean.DataBean.JlxListBean item) {
                        MarqueTextView marqueTextView = new MarqueTextView(HomeActivity.this);
                        marqueTextView = helper.getConvertView().findViewById(R.id.main_recycle_item_tv);
                        marqueTextView.setText(item.getJlxmc());
                        marqueTextView.setSelected(true);
                    }
                };
                home_recyc.setAdapter(baseRecyclerAdapter);
            } else {
                baseRecyclerAdapter.setData(loginBeans);
                loginBeans = baseRecyclerAdapter.getData();
            }

            baseRecyclerAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {

                    Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                    intent.putExtra("address", loginBeans.get(position).getJlxmc());
                    intent.putExtra("id", loginBeans.get(position).getJlxdm());
                    startActivity(intent);
                }
            });
        } else {
            smartRefreshLayout.finishRefresh(false);
        }
    }

    /**
     * 获取楼信息
     *
     * @param
     */
    public void getAddress(final int type) {
        if (type == 1) {
            DiaLogUtil.showDiaLog(this, "加载数据中");
        }
        OkHttpUtils
                .get()
                .url(MyApi.URL + MyApi.GETADDRESS)
                .tag(this)
                .addHeader("JSESSIONID", (String) SPUtils.get(HomeActivity.this, "JSESSIONID", ""))
                .addParams("", " ")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        if (type == 1) {
                            DiaLogUtil.dismissDiaLog();
                        } else {
                            //刷新失败
                            smartRefreshLayout.finishRefresh(false);
                        }
                        ToastUtil.showInfo(HomeActivity.this, getString(R.string.wufaData));
                    }

                    @Override
                    public void onResponse(String response) {
//                        Log.e(TAG, "homeActivity+" + response);
                        if (type == 1) {
                            DiaLogUtil.dismissDiaLog();
                        } else {
                            //刷新成功
                            smartRefreshLayout.finishRefresh(true);
                        }
                        try {
                            HomeBean homeBeanlist = (HomeBean) JsonUtils.stringToObject(response, HomeBean.class);
                            Message msg = new Message();
                            msg.what = 1;
                            msg.obj = homeBeanlist;
                            handler.sendMessage(msg);

                        } catch (Exception e) {
                            e.printStackTrace();
                            setLogin();
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
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
                            louMessage = new LouMessage(HomeActivity.this);
                        }
                        if (louMessage.tabbleIsExist("t_jzw_jlx") == true) {
                            getSql();
                        } else {
                            ToastUtil.showInfo(HomeActivity.this, "请返回首页下载离线数据");
                        }
                    } else {
                        ToastUtil.showInfo(HomeActivity.this, "请返回首页下载离线数据");
                    }
                }
            });
        } else if (netMobile == 0) {
            //ToastUtil.showInfo(this, "4G");
            login_nex_no_rl.setVisibility(View.GONE);
        }
    }
}
