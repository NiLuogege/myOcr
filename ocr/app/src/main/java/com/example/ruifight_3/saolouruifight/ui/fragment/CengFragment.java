package com.example.ruifight_3.saolouruifight.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.ruifight_3.saolouruifight.MyApi;
import com.example.ruifight_3.saolouruifight.R;
import com.example.ruifight_3.saolouruifight.baseui.LanBaseFragment;
import com.example.ruifight_3.saolouruifight.db.datamessage.LouMessage;
import com.example.ruifight_3.saolouruifight.db.util.DanYuanUtil;
import com.example.ruifight_3.saolouruifight.db.util.FileUtils;
import com.example.ruifight_3.saolouruifight.db.util.bean.view_ceng;
import com.example.ruifight_3.saolouruifight.db.util.bean.view_danYuan;
import com.example.ruifight_3.saolouruifight.ui.AddUserActivity;
import com.example.ruifight_3.saolouruifight.ui.adapter.HouseMessageAdapter;
import com.example.ruifight_3.saolouruifight.ui.adapter.SqlHoseContAdapter;
import com.example.ruifight_3.saolouruifight.ui.bean.DanYuanBean;
import com.example.ruifight_3.saolouruifight.util.JsonUtils;
import com.example.ruifight_3.saolouruifight.util.NetWorkUtil;
import com.example.ruifight_3.saolouruifight.util.ToastUtil;
import com.example.ruifight_3.saolouruifight.widget.DragFloatActionButton;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RuiFight-3 on 2018/7/5.
 */

public class CengFragment extends LanBaseFragment {
    private RecyclerView recyclerView;
    //单元号
    private int data;
    //建筑物编码
    private String floorCode;
    //街路巷编码
    private String jiedaoCode;
    private String addressname;
    private DanYuanUtil.view_jzw view_jzw;
    private HouseMessageAdapter houseMessageAdapter;
    private SqlHoseContAdapter adapter;
    private DragFloatActionButton drag_button;
    private RelativeLayout ceng_relativelayout;
    private String jzw_bm;//建筑物编码
    public static String BieMing = "";
    public static String CengNum = "";
    private LouMessage louMessage;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 1:
                    DanYuanBean danYuanBean = (DanYuanBean) msg.obj;
                    if (danYuanBean.getData().getDyDetail() != null && danYuanBean.getData().getDyDetail().getBieMing() != null) {
                        BieMing = danYuanBean.getData().getDyDetail().getBieMing() + "";//单元别名
                    } else {
                        BieMing = "";
                    }
                    if (danYuanBean.getData().getDyDetail() != null && danYuanBean.getData().getDyDetail().getCengList() != null) {
                        CengNum = danYuanBean.getData().getDyDetail().getCengList().size() + "";
                    } else {
                        CengNum = "";
                    }
                    if (danYuanBean.getData().getDyDetail() != null) {
                        if (danYuanBean.getData().getDyDetail().getCengList() != null && danYuanBean.getData().getDyDetail().getCengList().size() > 0) {
                            ceng_relativelayout.setVisibility(View.GONE);
                            List<DanYuanBean.DataBean.DyDetailBean.CengListBean> listBeans = danYuanBean.getData().getDyDetail().getCengList();

                            if (houseMessageAdapter != null) {
                                houseMessageAdapter.setData(listBeans, jzw_bm, data);
                                recyclerView.setAdapter(houseMessageAdapter);
                                houseMessageAdapter.setOnXXClickListener(new HouseMessageAdapter.XXListener() {
                                    @Override
                                    public void onXXClick() {
                                        getData();
                                    }
                                });
                            } else {
                                houseMessageAdapter = new HouseMessageAdapter(getMyActivity(), listBeans, floorCode, jiedaoCode, addressname, jzw_bm, data);
                                recyclerView.setAdapter(houseMessageAdapter);
                                houseMessageAdapter.setOnXXClickListener(new HouseMessageAdapter.XXListener() {
                                    @Override
                                    public void onXXClick() {
                                        getData();
                                    }
                                });
                            }
                        }
                    } else {
                        ceng_relativelayout.setVisibility(View.GONE);
                    }

                    break;

                default:
                    break;
            }
        }
    };


    @Override
    public void onLazyLoad() {
        //填充各控件的数据
        initData();
    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_ceng, container, false);
        //初始化view的各控件
        ceng_relativelayout = view.findViewById(R.id.ceng_relativelayout);
        recyclerView = view.findViewById(R.id.ceng_recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getMyActivity()));
        drag_button = view.findViewById(R.id.drag_button);
        return view;
    }

    @Override
    public void initEvent() {
        drag_button.hide();
    }

    public void initData() {
        Bundle bundle = getArguments();
        data = bundle.getInt("state");
        floorCode = bundle.getString("floorCode");
        jiedaoCode = bundle.getString("jiedaoCode");
        addressname = bundle.getString("addressname");
        view_jzw = (DanYuanUtil.view_jzw) bundle.getSerializable("view_jzw");
        jzw_bm = bundle.getString("jzw_bm");
        getData();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            //返回当前recyclerview的可见的item数目，也就是datas.length
            //dx是水平滚动的距离，dy是垂直滚动距离，向上滚动的时候为正，向下滚动的时候为负
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                if (layoutManager instanceof LinearLayoutManager) {
                    LinearLayoutManager linearManager = (LinearLayoutManager) layoutManager;
                    int firstVisibleItemPosition = linearManager.findFirstVisibleItemPosition();
                    if (firstVisibleItemPosition > 2) {
                        drag_button.show();
                    } else {
                        drag_button.hide();
                    }
                }
            }
        });

        drag_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.smoothScrollToPosition(0);
            }
        });
    }

    /**
     * 获取数据
     */
    public void getData() {
        if (!TextUtils.isEmpty(floorCode)) {
            if (NetWorkUtil.isConn(getMyActivity()) == false) {
                //没有网 走数据库查询
                try {
                    if (FileUtils.fileIsExists(MyApi.FileLoad + MyApi.DBNAME) == true) {
                        if (louMessage == null) {
                            louMessage = new LouMessage(getMyActivity());
                        }
                        if (louMessage.tabbleIsExist("t_jzw_jlx") == true) {
                            getSql(floorCode);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                //有网
                getGuanMess(data, floorCode);
            }
        }
    }

    public void getSql(String floorCode) {

        if (view_jzw != null) {
            List<view_danYuan> danYuans = new ArrayList<>();
            danYuans = view_jzw.getDs_dy_list();
            if (danYuans.get(data - 1).getBieMing() != null) {
                BieMing = danYuans.get(data - 1).getBieMing();//单元别名
            } else {
                BieMing = "";
            }
            if (danYuans.get(data - 1).getCengList() != null) {
                CengNum = danYuans.get(data - 1).getCengList().size() + "";
            } else {
                CengNum = "";
            }
            if (danYuans != null && danYuans.size() > 0) {
                if (danYuans.get(data - 1).getCengList() != null && danYuans.get(data - 1).getCengList().size() > 0) {
                    List<view_ceng> cengs = danYuans.get(data - 1).getCengList();

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            ceng_relativelayout.setVisibility(View.GONE);
                        }
                    });
                    if (adapter != null) {
                        adapter.setData(cengs);
                        recyclerView.setAdapter(adapter);
//                        if (easylayout_ceng.isLoading() == false) {
//                            easylayout_ceng.refreshComplete();
//                        }
                    } else {
                        adapter = new SqlHoseContAdapter(getMyActivity(), cengs, floorCode, jiedaoCode, addressname);
                        recyclerView.setAdapter(adapter);
//                        if (easylayout_ceng.isLoading() == false) {
//                            easylayout_ceng.refreshComplete();
//                        }
                    }
                }
            } else {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        ceng_relativelayout.setVisibility(View.GONE);
                    }
                });
            }
        }
    }

    public void getGuanMess(int data, String floorCode) {
        OkHttpUtils
                .post()
                .url(MyApi.URL + MyApi.UNFINISH + floorCode + "/" + data)
                .tag(getMyActivity())
                .addParams("", "")
                .build()
                .execute(new StringCallback() {

                    @Override
                    public void onError(Request request, Exception e) {
//                        if (easylayout_ceng.isLoading() == false) {
//                            easylayout_ceng.refreshComplete();
//                        }
                    }

                    @Override
                    public void onResponse(String response) {
                        //Log.e(TAG, "cengfragment=-=-=-" + response);

                        try {
                            DanYuanBean danYuanBean = (DanYuanBean) JsonUtils.stringToObject(response, DanYuanBean.class);
                            Message msg = new Message();
                            msg.what = 1;
                            msg.obj = danYuanBean;
                            handler.sendMessage(msg);
                        } catch (Exception e) {
                            e.printStackTrace();
                            ToastUtil.showInfo(getMyActivity(), getString(R.string.nowufu));
                        }
//                        if (easylayout_ceng.isLoading() == false) {
//                            easylayout_ceng.refreshComplete();
//                        }
                    }
                });
    }
}
