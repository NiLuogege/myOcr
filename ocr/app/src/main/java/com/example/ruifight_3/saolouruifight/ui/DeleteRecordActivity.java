package com.example.ruifight_3.saolouruifight.ui;

import com.example.ruifight_3.saolouruifight.MyApi;
import com.example.ruifight_3.saolouruifight.R;
import com.example.ruifight_3.saolouruifight.baseui.BaseActivity;
import com.example.ruifight_3.saolouruifight.db.datamessage.LouMessage;
import com.example.ruifight_3.saolouruifight.db.util.FileUtils;
import com.example.ruifight_3.saolouruifight.threadpool.ThreadExecutorService;
import com.example.ruifight_3.saolouruifight.ui.bean.DeleteJBean;
import com.example.ruifight_3.saolouruifight.util.DateUtil;
import com.example.ruifight_3.saolouruifight.util.DiaLogUtil;
import com.example.ruifight_3.saolouruifight.util.JsonUtils;
import com.example.ruifight_3.saolouruifight.util.NetWorkUtil;
import com.example.ruifight_3.saolouruifight.util.ToastUtil;
import com.github.library.BaseRecyclerAdapter;
import com.github.library.BaseViewHolder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;

/**
 * Created by RuiFight-3 on 2019/7/25.
 * Creator: 张震 (Android  zhangzhen)
 * Describe:删除人员记录详情
 */
public class DeleteRecordActivity extends BaseActivity {

    @BindView(R.id.delete_smart)
    SmartRefreshLayout smartRefreshLayout;

    @BindView(R.id.delete_recy_bringinto)
    RecyclerView recyclerView;

    @BindView(R.id.nodata_text)
    TextView nodata_text;
    private LouMessage louMessage;
    //当前页
    private int currentPage = 1;
    //条数
    private int pageSize = 15;
    private BaseRecyclerAdapter mAdapter;

    private List<DeleteJBean.DataBean> listBe;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 1:
                    DeleteJBean deleteJBean = (DeleteJBean) msg.obj;
                    if (deleteJBean.getData() != null && deleteJBean.getData().size() > 0) {
                        //完成加载成功
                        smartRefreshLayout.finishLoadMore(true);
                        final List<DeleteJBean.DataBean> listBeans = deleteJBean.getData();
                        if (mAdapter != null) {
                            mAdapter.addData(listBeans);
                            listBe = mAdapter.getData();
                        } else {
                            mAdapter = new BaseRecyclerAdapter<DeleteJBean.DataBean>(DeleteRecordActivity.this, listBeans, R.layout.item_deleterecore_layout) {
                                @Override
                                protected void convert(BaseViewHolder helper, DeleteJBean.DataBean item) {
                                    helper.setText(R.id.position_tv, String.valueOf(helper.getAdapterPosition() + 1));
                                    //删除时间
                                    try {
                                        helper.setText(R.id.riqi_tv, "删除时间: " + DateUtil.getDateToString1(item.getScsj(), DateUtil.DELETEDATA));
                                        //添加时间
                                        helper.setText(R.id.add_tv, "添加时间: " + DateUtil.getDateToString1(item.getCjsj(), DateUtil.DELETEDATA));

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    helper.setText(R.id.name_tv, "姓名: " + item.getXm());
                                    helper.setText(R.id.xb_tv, "性别: " + item.getXb());
                                    helper.setText(R.id.address_tv, "地址: " + item.getDizhi_xz());
                                }
                            };
                            listBe = mAdapter.getData();
                            recyclerView.setAdapter(mAdapter);
                        }

                    } else {
                        //标记没有更多数据
                        smartRefreshLayout.finishLoadMoreWithNoMoreData();
                    }
                    break;
                case 9:
                    DeleteJBean deleteJBean1 = (DeleteJBean) msg.obj;
                    if (deleteJBean1.getData() != null && deleteJBean1.getData().size() > 0) {
                        final List<DeleteJBean.DataBean> listBeans = deleteJBean1.getData();
                        if (mAdapter != null) {
                            mAdapter.setData(listBeans);
                            listBe = mAdapter.getData();
                        } else {
                            mAdapter = new BaseRecyclerAdapter<DeleteJBean.DataBean>(DeleteRecordActivity.this, listBeans, R.layout.item_deleterecore_layout) {
                                @Override
                                protected void convert(BaseViewHolder helper, DeleteJBean.DataBean item) {
                                    helper.setText(R.id.position_tv, String.valueOf(helper.getAdapterPosition() + 1));
                                    //删除时间
                                    try {
                                        helper.setText(R.id.riqi_tv, "删除时间: " + DateUtil.getDateToString1(item.getScsj(), DateUtil.DELETEDATA));
                                        //添加时间
                                        helper.setText(R.id.add_tv, "添加时间: " + DateUtil.getDateToString1(item.getCjsj(), DateUtil.DELETEDATA));

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    helper.setText(R.id.name_tv, "姓名: " + item.getXm());
                                    helper.setText(R.id.xb_tv, "性别: " + item.getXb());
                                    helper.setText(R.id.address_tv, "地址: " + item.getDizhi_xz());

                                }
                            };
                            listBe = mAdapter.getData();
                            recyclerView.setAdapter(mAdapter);
                        }
                    }

                    break;
                case 5:

                    DeleteJBean deleteJBean2 = (DeleteJBean) msg.obj;
                    if (deleteJBean2.getData() != null && deleteJBean2.getData().size() > 0) {

                        final List<DeleteJBean.DataBean> listBeans = deleteJBean2.getData();
                        if (mAdapter != null) {
                            mAdapter.setData(listBeans);
                            listBe = mAdapter.getData();
                        } else {
                            mAdapter = new BaseRecyclerAdapter<DeleteJBean.DataBean>(DeleteRecordActivity.this, listBeans, R.layout.item_deleterecore_layout) {
                                @Override
                                protected void convert(BaseViewHolder helper, DeleteJBean.DataBean item) {
                                    helper.setText(R.id.position_tv, String.valueOf(helper.getAdapterPosition() + 1));
                                    //删除时间
                                    try {
                                        helper.setText(R.id.riqi_tv, "删除时间: " + DateUtil.getDateToString1(item.getScsj(), DateUtil.DELETEDATA));
                                        //添加时间
                                        helper.setText(R.id.add_tv, "添加时间: " + DateUtil.getDateToString1(item.getCjsj(), DateUtil.DELETEDATA));

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    helper.setText(R.id.name_tv, "姓名: " + item.getXm());
                                    helper.setText(R.id.xb_tv, "性别: " + item.getXb());
                                    helper.setText(R.id.address_tv, "地址: " + item.getDizhi_xz());

                                }
                            };
                            listBe = mAdapter.getData();
                            recyclerView.setAdapter(mAdapter);
                        }
                    } else {
                        recyclerView.setVisibility(View.GONE);
                        nodata_text.setVisibility(View.VISIBLE);
                        nodata_text.setText("无删除数据");
                    }

                    break;

                default:
                    break;
            }
        }
    };


    @Override
    protected int setLayout() {
        return R.layout.activity_delete_record;
    }

    @Override
    protected void initView() {
        setSteepStatusBar(true);
        setTooBarBreakImage(R.mipmap.breakimagee);
        setTooBarTitleText("删除记录");
        smartRefreshLayout.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale));
        //不启用下拉刷新
        smartRefreshLayout.setEnableRefresh(true);
        //是否下拉Header的时候向下平移列表或者内容
        smartRefreshLayout.setEnableHeaderTranslationContent(true);
        //设置Header
        smartRefreshLayout.setRefreshHeader(new ClassicsHeader(DeleteRecordActivity.this));
        smartRefreshLayout.setEnableScrollContentWhenLoaded(true);
        smartRefreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(DeleteRecordActivity.this));
    }

    @Override
    protected void initData() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (NetWorkUtil.isConn(DeleteRecordActivity.this) == false) {
                    if (FileUtils.fileIsExists(MyApi.FileLoad + MyApi.DBNAME)) {
                        if (louMessage == null) {
                            louMessage = new LouMessage(DeleteRecordActivity.this);
                        }
                        if (louMessage.tabbleIsExist("t_jzw_jlx")) {
//                            // getSql(floorCode, 3);
                        }
                    }
                } else {
                    // 有网
                    DiaLogUtil.showDiaLog(DeleteRecordActivity.this, "加载数据中");
                    getDelete(currentPage, pageSize, 5);
                }
            }
        };
        ThreadExecutorService.newCachedThreadPool(1).execute(runnable);
        //加载
        onLoad();
    }


    public void onLoad() {
        //下拉刷新
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                //恢复没有更多数据的原始状态 1.0.5
                smartRefreshLayout.resetNoMoreData();
                if (NetWorkUtil.isConn(DeleteRecordActivity.this) == false) {
                    //没有网 走数据库查询
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (FileUtils.fileIsExists(MyApi.FileLoad + MyApi.DBNAME)) {
                                if (louMessage == null) {
                                    louMessage = new LouMessage(DeleteRecordActivity.this);
                                }
                                if (louMessage.tabbleIsExist("t_jzw_jlx")) {
//                                    getSql(floorCode, 3);
                                }
                            }
                        }
                    });
                } else {
                    currentPage = 1;
                    getDelete(currentPage, pageSize, 9);
                }
            }
        });
        //上拉加载
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                currentPage += 1;
                if (NetWorkUtil.isConn(DeleteRecordActivity.this) == false) {
                    //没有网 走数据库查询
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (FileUtils.fileIsExists(MyApi.FileLoad + MyApi.DBNAME)) {
                                if (louMessage == null) {
                                    louMessage = new LouMessage(DeleteRecordActivity.this);
                                }
                                if (louMessage.tabbleIsExist("t_jzw_jlx")) {
                                    //标记没有更多数据
                                    smartRefreshLayout.finishLoadMoreWithNoMoreData();
                                }
                            }
                        }
                    });
                } else {
                    getDelete(currentPage, pageSize, 1);
                }
            }
        });
    }

    /**
     * 获取记录
     */
    public void getDelete(int currentPage, int pageSize, final int type) {
        OkHttpUtils
                .post()
                .url(MyApi.URL + MyApi.DELETEJILU)
                .tag(this)
                .addParams("currentPage", currentPage + "")
                .addParams("pageSize", pageSize + "")
                .build()
                .execute(new StringCallback() {

                    @Override
                    public void onError(Request request, Exception e) {
                        DiaLogUtil.dismissDiaLog();
                        ToastUtil.showInfo(DeleteRecordActivity.this, getString(R.string.wufaData));
                        if (type == 9) {
                            //刷新失败
                            smartRefreshLayout.finishRefresh(false);
                        }
                    }

                    @Override
                    public void onResponse(String response) {
                        DiaLogUtil.dismissDiaLog();
                        if (type == 9) {
                            //刷新成功
                            smartRefreshLayout.finishRefresh(true);
                        }
                        if (type == 1) {
                            try {
                                DeleteJBean deleteJBean = (DeleteJBean) JsonUtils.stringToObject(response, DeleteJBean.class);
                                Message msg = new Message();
                                msg.what = 1;
                                msg.obj = deleteJBean;
                                handler.sendMessage(msg);

                            } catch (Exception e) {
                                e.printStackTrace();
                                setLogin();
                            }
                        } else if (type == 9) {
                            try {
                                DeleteJBean deleteJBean = (DeleteJBean) JsonUtils.stringToObject(response, DeleteJBean.class);
                                Message msg = new Message();
                                msg.what = 9;
                                msg.obj = deleteJBean;
                                handler.sendMessage(msg);

                            } catch (Exception e) {
                                e.printStackTrace();
                                setLogin();
                            }
                        } else if (type == 5) {
                            try {
                                DeleteJBean deleteJBean = (DeleteJBean) JsonUtils.stringToObject(response, DeleteJBean.class);
                                Message msg = new Message();
                                msg.what = 5;
                                msg.obj = deleteJBean;
                                handler.sendMessage(msg);

                            } catch (Exception e) {
                                e.printStackTrace();
                                setLogin();
                            }
                        }
                    }
                });
    }
}
