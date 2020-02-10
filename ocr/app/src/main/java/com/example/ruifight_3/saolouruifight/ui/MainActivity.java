package com.example.ruifight_3.saolouruifight.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ruifight_3.saolouruifight.MyApi;
import com.example.ruifight_3.saolouruifight.R;
import com.example.ruifight_3.saolouruifight.baseui.BaseActivity;
import com.example.ruifight_3.saolouruifight.db.datamessage.DanYuanMessage;
import com.example.ruifight_3.saolouruifight.db.datamessage.LouMessage;
import com.example.ruifight_3.saolouruifight.db.util.FileUtils;
import com.example.ruifight_3.saolouruifight.ui.bean.MainBean;
import com.example.ruifight_3.saolouruifight.util.DiaLogUtil;
import com.example.ruifight_3.saolouruifight.util.JsonUtils;
import com.example.ruifight_3.saolouruifight.util.NetWorkUtil;
import com.example.ruifight_3.saolouruifight.util.SPUtils;
import com.example.ruifight_3.saolouruifight.util.ToastUtil;
import com.example.ruifight_3.saolouruifight.widget.DragFloatActionButton;
import com.example.ruifight_3.saolouruifight.widget.MarqueTextView;
import com.example.zhouwei.library.CustomPopWindow;
import com.github.library.BaseRecyclerAdapter;
import com.github.library.BaseViewHolder;
import com.github.library.listener.OnRecyclerItemClickListener;
import com.github.library.listener.OnRecyclerItemLongClickListener;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 楼层信息
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private BaseRecyclerAdapter mAdapter;
    //小区名字
    private String address;
    private DanYuanMessage danYuanMessage;
    private List<MainBean.DataBean.JzwListBean> unitsListBeans;
    private String floorCode;
    @BindView(R.id.login_nex_no_rl)
    RelativeLayout login_nex_no_rl;
    @BindView(R.id.main_smart)
    SmartRefreshLayout main_smart;
    //搜索框
    @BindView(R.id.main_sousuo_cet)
    EditText clearEditText;
    @BindView(R.id.drag_buttons)
    DragFloatActionButton drag_button;
    @BindView(R.id.sousuo_button)
    Button sousuo_button;
    @BindView(R.id.base_toobar_right_button)
    Button base_toobar_right_button;
    //当前页
    private int currentPage = 1;
    //条数
    private int pageSize = 28;
    private List<MainBean.DataBean.JzwListBean> listBe;
    private LouMessage louMessage;
    private CustomPopWindow mCustomPopWindow;
    private String isNullJZW = "";
    private String rkOrderBy = "";
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 1:
                    MainBean mainBean = (MainBean) msg.obj;

                    if (mainBean.getData().getJzwList() != null && mainBean.getData().getJzwList().size() > 0) {
                        //完成加载成功
                        main_smart.finishLoadMore(true);
                        final List<MainBean.DataBean.JzwListBean> listBeans = mainBean.getData().getJzwList();
                        if (mAdapter != null) {
                            mAdapter.addData(listBeans);
                            listBe = mAdapter.getData();
                        } else {
                            mAdapter = new BaseRecyclerAdapter<MainBean.DataBean.JzwListBean>(MainActivity.this, listBeans, R.layout.item_main_recycleview) {
                                @Override
                                protected void convert(BaseViewHolder helper, MainBean.DataBean.JzwListBean item) {
                                    MarqueTextView marqueTextView = helper.getConvertView().findViewById(R.id.main_recycle_item_tv);
                                    marqueTextView.setText(address + " " + item.getJzw_dizhi());
                                    helper.setText(R.id.renkou_tv, "流: " + item.getLdrk_count() + "");

                                }
                            };
                            listBe = mAdapter.getData();
                            recyclerView.setAdapter(mAdapter);
                        }
                        mAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                //ToastUtil.showInfo(MainActivity.this, "dainle" + position);
                                Intent intent = new Intent(MainActivity.this, HouseManageActivity.class);
                                //楼编号
                                intent.putExtra("floorCode", listBe.get(position).getJzw_bm());
                                //楼
                                intent.putExtra("addressName", address);
                                //单元名
                                intent.putExtra("address", listBe.get(position).getJzw_dizhi());
                                //街路巷编码
                                intent.putExtra("jiedaoCode", floorCode);
                                startActivityForResult(intent, 200);
                            }
                        });

                        mAdapter.setOnRecyclerItemLongClickListener(new OnRecyclerItemLongClickListener() {
                            @Override
                            public boolean onItemLongClick(View view, int position) {
                                //如果不是房主登录
                                if (!"0".equals(MyApi.ISHOMELOGIN)) {
                                    //如果有网络
                                    if (NetWorkUtil.isConn(MainActivity.this) == false) {
                                        ToastUtil.showInfo(MainActivity.this, getString(R.string.lixian));
                                    } else {
                                        if (position < listBe.size()) {
                                            showUpdate(listBe.get(position).getJzw_dizhi(), position, listBe.get(position).getJzw_bm());
                                        }
                                    }
                                }
                                return true;
                            }
                        });

                    } else {
                        //标记没有更多数据
                        main_smart.finishLoadMoreWithNoMoreData();
                    }
                    break;
                case 9:
                    MainBean mainBean5 = (MainBean) msg.obj;

                    if (mainBean5.getData().getJzwList() != null && mainBean5.getData().getJzwList().size() > 0) {

                        final List<MainBean.DataBean.JzwListBean> listBeans = mainBean5.getData().getJzwList();
                        if (mAdapter != null) {
                            mAdapter.setData(listBeans);
                            listBe = mAdapter.getData();
                        } else {
                            mAdapter = new BaseRecyclerAdapter<MainBean.DataBean.JzwListBean>(MainActivity.this, listBeans, R.layout.item_main_recycleview) {
                                @Override
                                protected void convert(BaseViewHolder helper, MainBean.DataBean.JzwListBean item) {
                                    MarqueTextView marqueTextView = helper.getConvertView().findViewById(R.id.main_recycle_item_tv);
                                    marqueTextView.setText(address + " " + item.getJzw_dizhi());
                                    helper.setText(R.id.renkou_tv, "流: " + item.getLdrk_count() + "");

                                }
                            };
                            listBe = mAdapter.getData();
                            recyclerView.setAdapter(mAdapter);
                        }
                        mAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                //ToastUtil.showInfo(MainActivity.this, "dainle" + position);
                                Intent intent = new Intent(MainActivity.this, HouseManageActivity.class);
                                //楼编号
                                intent.putExtra("floorCode", listBe.get(position).getJzw_bm());
                                //楼
                                intent.putExtra("addressName", address);
                                //单元名
                                intent.putExtra("address", listBe.get(position).getJzw_dizhi());
                                //街路巷编码
                                intent.putExtra("jiedaoCode", floorCode);
                                startActivityForResult(intent, 200);
                            }
                        });
                    }

                    break;

                case 2:
                    //在线搜索
                    MainBean mainBean1 = (MainBean) msg.obj;
                    if (mainBean1.isStatus()) {
                        if (mainBean1.getData().getJzwList() != null && mainBean1.getData().getJzwList().size() > 0) {
                            //完成加载成功
                            main_smart.finishLoadMore(true);
                            final List<MainBean.DataBean.JzwListBean> listBeans = mainBean1.getData().getJzwList();
                            if (mAdapter != null) {
                                mAdapter.setData(listBeans);
                                listBe = mAdapter.getData();
                            }
                        } else {
                            //标记没有更多数据
                            main_smart.finishLoadMoreWithNoMoreData();
                            ToastUtil.showInfo(MainActivity.this, "没查到该信息");
                        }
                    }
                    break;


                case 3:
                    //监听输入框查询
                    String sDa = (String) msg.obj;
                    if (danYuanMessage == null) {
                        danYuanMessage = new DanYuanMessage(MainActivity.this);
                    }
                    if (unitsListBeans == null) {
                        unitsListBeans = new ArrayList<>();
                    } else {
                        unitsListBeans.clear();
                    }
                    unitsListBeans = danYuanMessage.dimSuosuo(floorCode, sDa);
                    if (unitsListBeans.size() > 0) {
                        if (mAdapter != null) {
                            mAdapter.setData(unitsListBeans);
                        }
                    } else {
                        ToastUtil.showInfo(MainActivity.this, "没查到该信息");
                    }
                    break;

                case 4:
                    MainBean mainBean2 = (MainBean) msg.obj;
                    if (mainBean2.getData().getJzwList() != null && mainBean2.getData().getJzwList().size() > 0) {
                        //完成加载成功
                        main_smart.finishLoadMore(true);
                        final List<MainBean.DataBean.JzwListBean> listBeans = mainBean2.getData().getJzwList();
                        if (mAdapter != null) {
                            mAdapter.setData(listBeans);
                            listBe = mAdapter.getData();
                        }
                    } else {
                        //标记没有更多数据
                        main_smart.finishLoadMoreWithNoMoreData();
                    }

                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        setSteepStatusBar(true);
        setTooBarBreakImage(R.mipmap.breakimagee);
        if (!"0".equals(MyApi.ISHOMELOGIN)) {
            //设置右键添加建筑物按钮
            setTooBarRightIma(true);
            base_toobar_right_button.setOnClickListener(this);
        }

        drag_button.hide();
        recyclerView = findViewById(R.id.recy_bringinto);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        sousuo_button.setOnClickListener(this);

        //recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        //设置 Footer 为 球脉冲 样式
        main_smart.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale));
        //不启用下拉刷新
        main_smart.setEnableRefresh(true);
        //是否下拉Header的时候向下平移列表或者内容
        main_smart.setEnableHeaderTranslationContent(true);
        //设置Header
        main_smart.setRefreshHeader(new ClassicsHeader(MainActivity.this));
        main_smart.setEnableScrollContentWhenLoaded(true);
        main_smart.setEnableLoadMoreWhenContentNotFull(false);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        floorCode = intent.getStringExtra("id");
        address = intent.getStringExtra("address");
        setTooBarTitleText("街路巷列表" + ">" + address);

        if (!TextUtils.isEmpty(floorCode)) {
            if (NetWorkUtil.isConn(MainActivity.this) == false) {
                //没有网 走数据库查询
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (FileUtils.fileIsExists(MyApi.FileLoad + MyApi.DBNAME) == true) {
                            if (louMessage == null) {
                                louMessage = new LouMessage(MainActivity.this);
                            }
                            if (louMessage.tabbleIsExist("t_jzw_jlx") == true) {
                                getSql(floorCode, 3);
                            }
                        }
                    }
                });
            } else {
                //有网
                DiaLogUtil.showDiaLog(this, "加载数据中");
                getAddress(floorCode, currentPage, pageSize, "", 2);
            }
        }

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
                    if (firstVisibleItemPosition > 6) {
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
        //加载
        onLoad();
        //搜索方法
        seSouSuo();
    }

    public void onLoad() {
        //下拉刷新
        main_smart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                //恢复没有更多数据的原始状态 1.0.5
                main_smart.resetNoMoreData();
                if (!TextUtils.isEmpty(floorCode)) {
                    if (NetWorkUtil.isConn(MainActivity.this) == false) {
                        //没有网 走数据库查询
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                if (FileUtils.fileIsExists(MyApi.FileLoad + MyApi.DBNAME) == true) {
                                    if (louMessage == null) {
                                        louMessage = new LouMessage(MainActivity.this);
                                    }
                                    if (louMessage.tabbleIsExist("t_jzw_jlx") == true) {
                                        getSql(floorCode, 3);
                                    }
                                }
                            }
                        });
                    } else {
                        currentPage = 1;
                        isNullJZW = "";
                        rkOrderBy = "";
                        getAddress(floorCode, currentPage, pageSize, "", 9);
                    }
                }
            }
        });
        //上拉加载
        main_smart.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                currentPage += 1;
                if (NetWorkUtil.isConn(MainActivity.this) == false) {
                    //没有网 走数据库查询
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (FileUtils.fileIsExists(MyApi.FileLoad + MyApi.DBNAME) == true) {
                                if (louMessage == null) {
                                    louMessage = new LouMessage(MainActivity.this);
                                }
                                if (louMessage.tabbleIsExist("t_jzw_jlx") == true) {
                                    //标记没有更多数据
                                    main_smart.finishLoadMoreWithNoMoreData();
                                }
                            }
                        }
                    });
                } else {
                    getAddress(floorCode, currentPage, pageSize, "", 1);
                }
            }
        });
    }

    //搜索
    @SuppressLint("ClickableViewAccessibility")
    public void seSouSuo() {
        clearEditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        //软件盘搜索
        clearEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    //TODO
                    if (!TextUtils.isEmpty(clearEditText.getText().toString().trim())) {
                        if (NetWorkUtil.isConn(MainActivity.this) == false) {
                            //没有网 走数据搜索
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    if (FileUtils.fileIsExists(MyApi.FileLoad + MyApi.DBNAME) == true) {
                                        if (louMessage == null) {
                                            louMessage = new LouMessage(MainActivity.this);
                                        }
                                        if (louMessage.tabbleIsExist("t_jzw_jlx") == true) {

                                            String s = clearEditText.getText().toString().trim();
                                            Message msg = new Message();
                                            msg.what = 3;
                                            msg.obj = s;
                                            handler.sendMessage(msg);
                                        }
                                    }
                                }
                            });
                        } else {
                            //网络 搜索
                            currentPage = 1;
                            isNullJZW = "";
                            rkOrderBy = "";
                            getAddress(floorCode, currentPage, pageSize, clearEditText.getText().toString().trim(), 3);
                        }
                    } else {
                        ToastUtil.showInfo(MainActivity.this, "输入搜索内容");
                    }

                    return false;
                }
                return false;
            }
        });

        clearEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub
                //这个应该是在改变的时候会做的动作

            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub
                //这是文本框改变之前会执行的动作
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
                /**这是文本框改变之后 会执行的动作
                 * 因为我们要做的就是，在文本框改变的同时，我们的listview的数据也进行相应的变动，并且如一的显示在界面上。
                 * 所以这里我们就需要加上数据的修改的动作了。
                 */
                if (s.length() == 0) {
                    //搜索框没有字事显示数据
                    if (!TextUtils.isEmpty(floorCode)) {
                        if (NetWorkUtil.isConn(MainActivity.this) == false) {
                            //没有网 走数据库查询
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    if (FileUtils.fileIsExists(MyApi.FileLoad + MyApi.DBNAME) == true) {
                                        if (louMessage == null) {
                                            louMessage = new LouMessage(MainActivity.this);
                                        }
                                        if (louMessage.tabbleIsExist("t_jzw_jlx") == true) {
                                            clearEditText.clearFocus();//失去焦点
                                            getSql(floorCode, 3);

                                        }
                                    }
                                }
                            });
                        } else {
                            //有网
                            clearEditText.clearFocus();//失去焦点
                            currentPage = 1;
                            isNullJZW = "";
                            rkOrderBy = "";
                            getAddress(floorCode, currentPage, pageSize, "", 5);
                        }
                    }
                }
            }
        });
    }

    /**
     * 数据库查询
     *
     * @param floorCode
     */
    public void getSql(final String floorCode, int type) {
        if (danYuanMessage == null) {
            danYuanMessage = new DanYuanMessage(MainActivity.this);
        }
        if (unitsListBeans == null) {
            unitsListBeans = new ArrayList<>();
        } else {
            unitsListBeans.clear();
        }
        unitsListBeans = danYuanMessage.selectDan(floorCode, type);

        if (unitsListBeans != null && unitsListBeans.size() > 0) {

            //结束刷新（成功）
            main_smart.finishRefresh(true);

            if (mAdapter != null) {
                mAdapter.setData(unitsListBeans);
                unitsListBeans = mAdapter.getData();
            } else {
                mAdapter = new BaseRecyclerAdapter<MainBean.DataBean.JzwListBean>(MainActivity.this, unitsListBeans, R.layout.item_main_recycleview) {
                    @Override
                    protected void convert(BaseViewHolder helper, MainBean.DataBean.JzwListBean item) {
                        MarqueTextView marqueTextView = helper.getConvertView().findViewById(R.id.main_recycle_item_tv);
                        marqueTextView.setText(address + " " + item.getJzw_dizhi());
                        helper.setText(R.id.renkou_tv, "流: " + item.getLdrk_count() + "");
                    }
                };
                mAdapter.openLoadAnimation();
                recyclerView.setAdapter(mAdapter);
            }

            mAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    //ToastUtil.showInfo(MainActivity.this, "dainle" + position);
                    Intent intent = new Intent(MainActivity.this, HouseManageActivity.class);
                    //楼编号
                    intent.putExtra("floorCode", unitsListBeans.get(position).getJzw_bm());
                    //楼
                    intent.putExtra("addressName", address);
                    //单元名
                    intent.putExtra("address", unitsListBeans.get(position).getJzw_dizhi());
                    //街路巷编码
                    intent.putExtra("jiedaoCode", floorCode);
                    startActivityForResult(intent, 200);
                }
            });
        } else {
            //结束刷新（失败）
            main_smart.finishRefresh(false);
        }
    }

    /**
     * 网络查询  默认 和搜索有个接口  默认 paramStr 为空
     *
     * @param floorCode
     */

    public void getAddress(String floorCode, int currentPage, int pageSize, String paramStr, final int type) {
        OkHttpUtils
                .post()
                .url(MyApi.URL + MyApi.ADDRESSMESSAGE + floorCode)
                .tag(this)
                .addHeader("JSESSIONID", (String) SPUtils.get(MainActivity.this, "JSESSIONID", ""))
                //T只显示为空建筑物
                .addParams("isNullJZW", isNullJZW)
                //排序方式
                .addParams("rkOrderBy", rkOrderBy)
                .addParams("currentPage", currentPage + "")
                .addParams("pageSize", pageSize + "")
                .addParams("paramStr", paramStr)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        ToastUtil.showInfo(MainActivity.this, getString(R.string.wufaData));
                        if (type != 1) {
                            DiaLogUtil.dismissDiaLog();
                        } else {
                            //加载失败
                            main_smart.finishLoadMore(false);
                        }
                        if (type == 9) {
                            //刷新失败
                            main_smart.finishRefresh(false);
                        }
                    }

                    @Override
                    public void onResponse(String response) {

                        if (type != 1) {
                            DiaLogUtil.dismissDiaLog();
                        }
                        if (type == 9) {
                            //刷新成功
                            main_smart.finishRefresh(true);
                        }
                        //搜索
                        if (type == 3) {
                            try {
                                MainBean mainBean = (MainBean) JsonUtils.stringToObject(response, MainBean.class);
                                Message msg = new Message();
                                msg.what = 2;
                                msg.obj = mainBean;
                                handler.sendMessage(msg);
                            } catch (Exception e) {
                                e.printStackTrace();
                                setLogin();
                            }
                        } else if (type == 5) {
                            try {
                                MainBean mainBean = (MainBean) JsonUtils.stringToObject(response, MainBean.class);
                                Message msg = new Message();
                                msg.what = 4;
                                msg.obj = mainBean;
                                handler.sendMessage(msg);
                            } catch (Exception e) {
                                e.printStackTrace();
                                setLogin();
                            }
                        } else if (type == 9) {
                            try {
                                MainBean mainBean = (MainBean) JsonUtils.stringToObject(response, MainBean.class);
                                Message msg = new Message();
                                msg.what = 9;
                                msg.obj = mainBean;
                                handler.sendMessage(msg);
                            } catch (Exception e) {
                                e.printStackTrace();
                                setLogin();
                            }
                        } else {
                            try {
                                MainBean mainBean = (MainBean) JsonUtils.stringToObject(response, MainBean.class);
                                Message msg = new Message();
                                msg.what = 1;
                                msg.obj = mainBean;
                                handler.sendMessage(msg);
                            } catch (Exception e) {
                                e.printStackTrace();
                                setLogin();
                            }
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sousuo_button:
                clearEditText.clearFocus();//失去焦点
                if (!TextUtils.isEmpty(clearEditText.getText().toString().trim())) {
                    if (NetWorkUtil.isConn(MainActivity.this) == false) {
                        //没有网 走数据搜索
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                if (FileUtils.fileIsExists(MyApi.FileLoad + MyApi.DBNAME) == true) {
                                    if (louMessage == null) {
                                        louMessage = new LouMessage(MainActivity.this);
                                    }
                                    if (louMessage.tabbleIsExist("t_jzw_jlx") == true) {

                                        String s = clearEditText.getText().toString().trim();
                                        Message msg = new Message();
                                        msg.what = 3;
                                        msg.obj = s;
                                        handler.sendMessage(msg);
                                    }
                                }
                            }
                        });
                    } else {
                        //网络 搜索
                        currentPage = 1;
                        getAddress(floorCode, currentPage, pageSize, clearEditText.getText().toString().trim(), 3);
                    }
                } else {
                    ToastUtil.showInfo(MainActivity.this, "输入搜索内容");
                }
                break;
            case R.id.base_toobar_right_button:
                ShouPopWindows(v);
                break;
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
                            louMessage = new LouMessage(MainActivity.this);
                        }
                        if (louMessage.tabbleIsExist("t_jzw_jlx") == true) {
                            getSql(floorCode, 3);
                        } else {
                            ToastUtil.showInfo(MainActivity.this, "请返回首页下载离线数据");
                        }
                    } else {
                        ToastUtil.showInfo(MainActivity.this, "请返回首页下载离线数据");
                    }
                }
            });
        } else if (netMobile == 0) {
            //ToastUtil.showInfo(this, "4G");
            login_nex_no_rl.setVisibility(View.GONE);
        }
    }

    /**
     * 修改建筑物名称
     */
    public void showUpdate(String jzw_dizhi, int position, String jzw_bm) {
        View contentView = LayoutInflater.from(this).inflate(R.layout.layout_main_pop, null);
        //处理popWindow 显示内容
        handleLogics(contentView, jzw_dizhi, position, jzw_bm);
        //创建并显示popWindow
        mCustomPopWindow = new CustomPopWindow.PopupWindowBuilder(this)
                .setView(contentView)
                .setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED)
                .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
                .create()
                .showAtLocation(contentView, Gravity.CENTER, 0, 0);

    }

    /**
     * 设置布局
     *
     * @param view
     */
    public void handleLogics(View view, String jzw_dizhi, final int position, final String jzw_bm) {
        TextView guanbi_tv_main = view.findViewById(R.id.guanbi_tv_main);
        TextView danyuan_tv_main = view.findViewById(R.id.danyuan_tv_main);
        Button ceng_button_main = view.findViewById(R.id.ceng_button_main);
        final EditText et_danyuan2_main = view.findViewById(R.id.et_danyuan2_main);
        danyuan_tv_main.setText(jzw_dizhi);
        //确定按钮
        ceng_button_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_danyuan2_main.getText().toString().trim().equals("")) {
                    ToastUtil.showInfo(MainActivity.this, getString(R.string.jzwname));
                } else {
                    mCustomPopWindow.dissmiss();
                    updateJzw(position, et_danyuan2_main.getText().toString().trim(), jzw_bm);
                }
            }
        });

        guanbi_tv_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCustomPopWindow.dissmiss();
            }
        });
    }

    /**
     * 网络修改建筑物名称
     */
    public void updateJzw(final int position, final String jzw_dizhi, String jzw_bm) {
        OkHttpUtils
                .post()
                .url(MyApi.URL + MyApi.UPDATEJZW + jzw_bm)
                .tag(this)
                .addHeader("JSESSIONID", (String) SPUtils.get(MainActivity.this, "JSESSIONID", ""))
                .addParams("jzw_dizhi", jzw_dizhi)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        DiaLogUtil.dismissDiaLog();
                        ToastUtil.showInfo(MainActivity.this, getString(R.string.wufaData));
                    }

                    @Override
                    public void onResponse(String response) {
                        DiaLogUtil.dismissDiaLog();
                        if (response.equals("ok")) {
                            if (response.equals("ok")) {
                                if (listBe != null) {
                                    if (position < listBe.size()) {
                                        listBe.get(position).setJzw_dizhi(jzw_dizhi);
                                        mAdapter.notifyDataSetChanged();
                                    }
                                }
                                ToastUtil.showInfo(MainActivity.this, "修改成功");
                            } else {
                                ToastUtil.showInfo(MainActivity.this, "修改失败");
                            }
                        }
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void ShouPopWindows(View view) {
        View contentView = LayoutInflater.from(this).inflate(R.layout.item_layout_main_add, null);
        //处理popWindow 显示内容
        handleLogics(contentView);
        //创建并显示popWindow
        mCustomPopWindow = new CustomPopWindow.PopupWindowBuilder(this)
                .setView(contentView)
                .setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED)
                .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
                .create()
                .showAsDropDown(view, 0, 30);//显示PopupWindow
    }

    public void handleLogics(View contentView) {
        //添加建筑物
        TextView textView = contentView.findViewById(R.id.add_jzw_tv);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCustomPopWindow.dissmiss();
                Intent intent = new Intent(MainActivity.this, AddJzwActivity.class);
                intent.putExtra("jzw_jlxdm", floorCode);
                startActivity(intent);
                overridePendingTransition(R.anim.stop, R.anim.start);
            }
        });
        //升序建筑物
        TextView textView1 = contentView.findViewById(R.id.desc_tv);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCustomPopWindow.dissmiss();
                if (NetWorkUtil.isConn(MainActivity.this)) {
                    rkOrderBy = "ASC";
                    getAddress(floorCode, currentPage, pageSize, "", 9);
                } else {

                    if (FileUtils.fileIsExists(MyApi.FileLoad + MyApi.DBNAME)) {
                        if (louMessage == null) {
                            louMessage = new LouMessage(MainActivity.this);
                        }
                        if (louMessage.tabbleIsExist("t_jzw_jlx")) {
                            getSql(floorCode, 1);
                        }
                    }
                }
            }
        });
        //降序建筑物
        TextView textView2 = contentView.findViewById(R.id.dsc_tv);
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCustomPopWindow.dissmiss();
                if (NetWorkUtil.isConn(MainActivity.this)) {
                    rkOrderBy = "DESC";
                    getAddress(floorCode, currentPage, pageSize, "", 9);
                } else {

                    if (FileUtils.fileIsExists(MyApi.FileLoad + MyApi.DBNAME)) {
                        if (louMessage == null) {
                            louMessage = new LouMessage(MainActivity.this);
                        }
                        if (louMessage.tabbleIsExist("t_jzw_jlx")) {
                            getSql(floorCode, 2);
                        }
                    }
                }
            }
        });
        //筛选建筑物
        TextView textView3 = contentView.findViewById(R.id.screen_tv);
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCustomPopWindow.dissmiss();
                if (NetWorkUtil.isConn(MainActivity.this)) {
                    isNullJZW = "T";
                    getAddress(floorCode, currentPage, pageSize, "", 9);
                } else {
                    if (FileUtils.fileIsExists(MyApi.FileLoad + MyApi.DBNAME)) {
                        if (louMessage == null) {
                            louMessage = new LouMessage(MainActivity.this);
                        }
                        if (louMessage.tabbleIsExist("t_jzw_jlx")) {
                            getSql(floorCode, 4);
                        }
                    }
                }
            }
        });
    }
}
