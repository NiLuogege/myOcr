package com.example.ruifight_3.saolouruifight.ui;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chanven.lib.cptr.recyclerview.RecyclerAdapterWithHF;
import com.example.ruifight_3.saolouruifight.MyApi;
import com.example.ruifight_3.saolouruifight.R;
import com.example.ruifight_3.saolouruifight.baseui.BaseActivity;
import com.example.ruifight_3.saolouruifight.db.datamessage.DanYuanMessage;
import com.example.ruifight_3.saolouruifight.db.datamessage.LouMessage;
import com.example.ruifight_3.saolouruifight.db.util.DanYuanUtil;
import com.example.ruifight_3.saolouruifight.db.util.FileUtils;
import com.example.ruifight_3.saolouruifight.db.util.bean.jzw_bean;
import com.example.ruifight_3.saolouruifight.db.util.bean.t_jzw_fangjian;
import com.example.ruifight_3.saolouruifight.ui.bean.HouseBean;
import com.example.ruifight_3.saolouruifight.ui.fragment.CengFragment;
import com.example.ruifight_3.saolouruifight.util.ConvertUtils;
import com.example.ruifight_3.saolouruifight.util.DiaLogUtil;
import com.example.ruifight_3.saolouruifight.util.JsonUtils;
import com.example.ruifight_3.saolouruifight.util.NetWorkUtil;
import com.example.ruifight_3.saolouruifight.util.SPUtils;
import com.example.ruifight_3.saolouruifight.util.ToastUtil;
import com.example.ruifight_3.saolouruifight.widget.CommomDialog;
import com.example.zhouwei.library.CustomPopWindow;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * Created by RuiFight-3 on 2018/4/18.
 * 房屋信息
 */

public class HouseManageActivity extends BaseActivity implements View.OnClickListener {

    //添加Header和Footer的封装类
    private RecyclerAdapterWithHF mAdapter;
    private String floorCode;//编号
    private String address;//单元
    private String jiedaoCode;//街路巷编码
    private StringBuffer sb;
    @BindView(R.id.title_textview)
    TextView title_textview;
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.no_textview)
    TextView no_textview;
    //数据源
    private String[] titles;
    private int[] state;
    private DanYuanUtil.view_jzw view_jzw;
    @BindView(R.id.login_nex_no_rl)
    RelativeLayout login_nex_no_rl;
    @BindView(R.id.base_toobar_right_button)
    Button base_toobar_right_button;

    private CustomPopWindow mCustomPopWindow;
    private HouseBean houseBean;
    private LouMessage louMessage;
    private DanYuanMessage danYuanMessage;
    private DanYuanUtil DanYuanUtil;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 1:
                    houseBean = (HouseBean) msg.obj;
                    if (houseBean.getData() != null) {
                        int gong = houseBean.getData().getRenshu_hjrk() + houseBean.getData().getRenshu_ldrk() + houseBean.getData().getRenshu_rhfl();
                        title_textview.setText(ConvertUtils.setNumColor("房屋详情：" + "共有房屋" + houseBean.getData().getFangwu_total() + "" + "户，" + "采集房主信息" + houseBean.getData().getFangwu_caiji() + "" + "户," + "出租房屋" + houseBean.getData().getFangwu_chuzu() + "" + "户，" + "共登记" + gong + "" + "人，"
                                + "户籍人口" + houseBean.getData().getRenshu_hjrk() + "" + "人, " + "流动人口" + houseBean.getData().getRenshu_ldrk() + "" + "人," + "人户分离人口" + houseBean.getData().getRenshu_rhfl() + "人。"));

                        if (houseBean.getData().getDyTotal() + "" != null && houseBean.getData().getDyTotal() > 0) {
                            viewpager.setVisibility(View.VISIBLE);
                            no_textview.setVisibility(View.GONE);
                            titles = new String[houseBean.getData().getDyTotal()];
                            state = new int[houseBean.getData().getDyTotal()];

                            if (houseBean.getData().getDyList() != null) {
                                for (int i = 0; i < houseBean.getData().getDyList().size(); i++) {
                                    titles[i] = houseBean.getData().getDyList().get(i).getDybm().trim();
                                    //根据下标 查单元下信息
                                    state[i] = houseBean.getData().getDyList().get(i).getDys();
                                }
                                initViewPager();
                            }
                        } else {
                            viewpager.setVisibility(View.GONE);
                            no_textview.setVisibility(View.VISIBLE);
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
        return R.layout.activity_house_manager;
    }

    @Override
    protected void initView() {
        setSteepStatusBar(true);
        setTooBarBreakImage(R.mipmap.breakimagee);
        if (!"0".equals(MyApi.ISHOMELOGIN)) {
            setTooBarRightIma(true);
            base_toobar_right_button.setOnClickListener(this);
        }
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        floorCode = intent.getStringExtra("floorCode");
        String addressName = intent.getStringExtra("addressName");
        address = intent.getStringExtra("address");
        jiedaoCode = intent.getStringExtra("jiedaoCode");
        StringBuffer stringBuffer = new StringBuffer(addressName + "<");
        sb = stringBuffer.append(address);
        setTooBarTitleText("街路巷列表" + ">" + sb);

        isNowe();
//        //下拉刷新
//        ptrClassicFrameLayout.setPtrHandler(new PtrDefaultHandler() {
//            @Override
//            public void onRefreshBegin(PtrFrameLayout frame) {
//                getHoseMess(floorCode);
//                mAdapter.notifyDataSetChanged();
//                ptrClassicFrameLayout.refreshComplete();
//
//            }
//        });
//
//        //上拉加载
//        ptrClassicFrameLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
//            @Override
//            public void loadMore() {
//               // house_recycview.add
//                mAdapter.notifyDataSetChanged();
//                ptrClassicFrameLayout.loadMoreComplete(true);
//            }
//        });
    }

    public void isNowe() {
        if (!TextUtils.isEmpty(floorCode)) {
            if (NetWorkUtil.isConn(HouseManageActivity.this) == false) {
                //没有网 走数据库查询
                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        if (FileUtils.fileIsExists(MyApi.FileLoad + MyApi.DBNAME) == true) {
                            if (louMessage == null) {
                                louMessage = new LouMessage(HouseManageActivity.this);
                            }
                            if (louMessage.tabbleIsExist("t_jzw_jlx") == true) {
                                getSql(floorCode);
                            }
                        }
                    }
                });
            } else {
                //有网
                DiaLogUtil.showDiaLog(this, "加载数据中");
                getHoseMess(floorCode);
            }
        }
    }

    /**
     * 获取数据库数据
     *
     * @param floorCode
     */
    public void getSql(String floorCode) {
        if (DanYuanUtil == null) {
            DanYuanUtil = new DanYuanUtil();
        }
        List<t_jzw_fangjian> list = new ArrayList<>();
        view_jzw = new DanYuanUtil.view_jzw();
        List<DanYuanUtil.view_jzw> view_jzwList = new ArrayList<>();

        if (danYuanMessage == null) {
            danYuanMessage = new DanYuanMessage(HouseManageActivity.this);
        }
        jzw_bean jzw_bean = new jzw_bean();
        list = danYuanMessage.selectCeng(floorCode);
        jzw_bean = danYuanMessage.selectJzw(floorCode);
        if (DanYuanUtil.gzchjg(jzw_bean, list) != null) {

            view_jzw = DanYuanUtil.gzchjg(jzw_bean, list);
            view_jzwList.add(view_jzw);

            if (view_jzw != null) {
                int gong = view_jzw.getRenshu_hjrk() + view_jzw.getRenshu_ldrk() + view_jzw.getRenshu_rhfl();
                title_textview.setText(ConvertUtils.setNumColor("房屋详情：" + "共有房屋" + view_jzw.getFangwu_total() + "" + "户，" + "采集房主信息" + view_jzw.getFangwu_caiji() + "" + "户," + "出租房屋" + view_jzw.getFangwu_chuzu() + "" + "户，" + "共登记" + gong + "" + "人，"
                        + "户籍人口" + view_jzw.getRenshu_hjrk() + "" + "人, " + "流动人口" + view_jzw.getRenshu_ldrk() + "" + "人，" + "人户分离人口" + view_jzw.getRenshu_rhfl() + "人。"));

                if (view_jzw.getDs_dy_list() != null && view_jzw.getDs_dy_list().size() > 0) {

                    viewpager.setVisibility(View.VISIBLE);
                    no_textview.setVisibility(View.GONE);
                    titles = new String[view_jzw.getDs_dy_list().size()];
                    state = new int[view_jzw.getDs_dy_list().size()];

                    for (int i = 0; i < view_jzw.getDs_dy_list().size(); i++) {
                        titles[i] = view_jzw.getDs_dy_list().get(i).getBieMing() == null ? i + 1 + "单元" : view_jzw.getDs_dy_list().get(i).getBieMing();
                        //根据下标 查单元下信息
                        state[i] = i + 1;
                    }

                    initViewPager();
                } else {
                    viewpager.setVisibility(View.GONE);
                    no_textview.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void initViewPager() {

        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());

        viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));

        viewpager.setAdapter(myPagerAdapter);

        tablayout.setupWithViewPager(viewpager);
        //设置可以滑动
        tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        for (int i = 0; i < tablayout.getTabCount(); i++) {
            TabLayout.Tab tab = tablayout.getTabAt(i);//获得每一个tab
            if (tab != null) {
                Class c = tab.getClass();
                try {
                    Field field = c.getDeclaredField("mView");
                    if (field != null) {
                        field.setAccessible(true);
                        View view = (View) field.get(tab);
                        if (view != null) {
                            view.setTag(i);
                            view.setOnLongClickListener(new View.OnLongClickListener() {
                                @Override
                                public boolean onLongClick(View v) {
                                    int position = (int) v.getTag() + 1;//单元号
                                    int position1 = (int) v.getTag();//下标
                                    //backgroundAlpha(0.5f);
                                    if (NetWorkUtil.isConn(HouseManageActivity.this) == false) {
                                        ToastUtil.showInfo(HouseManageActivity.this, getString(R.string.lixian));
                                    } else {
                                        if (!"0".equals(MyApi.ISHOMELOGIN)) {
                                            ShouPopWindow(houseBean, v, position, position1);
                                        }
                                    }
                                    return true;
                                }
                            });
                        }
                    }
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public Fragment getItem(int position) {
            CengFragment cengFragment = new CengFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("view_jzw", view_jzw);
            bundle.putInt("state", state[position]);
            bundle.putString("floorCode", floorCode);
            bundle.putString("jiedaoCode", jiedaoCode);
            bundle.putString("addressname", String.valueOf(sb));
            bundle.putString("jzw_bm", houseBean != null ? houseBean.getData().getJzw().getJzw_bm() : "");
            cengFragment.setArguments(bundle);
            return cengFragment;
        }

        @Override
        public int getCount() {
            return titles.length;
        }
    }

    /**
     * 获取号楼及门牌列表
     */
    public void getHoseMess(String floorCode) {
        OkHttpUtils
                .post()
                .url(MyApi.URL + MyApi.UNFINISH + floorCode)
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
                        DiaLogUtil.dismissDiaLog();
                        try {
                            HouseBean houseBean = (HouseBean) JsonUtils.stringToObject(response, HouseBean.class);
                            Message msg = new Message();
                            msg.what = 1;
                            msg.obj = houseBean;
                            handler.sendMessage(msg);
                        } catch (Exception e) {
                            e.printStackTrace();
                            setLogin();
                        }
                    }
                });
    }

    /**
     * 删除单元
     *
     * @param
     */
    public void deleteDy(String jzw_bm, int dyh) {
        OkHttpUtils
                .post()
                .url(MyApi.URL + MyApi.DELETEDY + jzw_bm)
                .tag(this)
                .addParams("dyh", dyh + "")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        ToastUtil.showInfo(HouseManageActivity.this, getString(R.string.wufaData));
                    }

                    @Override
                    public void onResponse(String response) {
                        if (response.equals("ok")) {
                            ToastUtil.showInfo(HouseManageActivity.this, getString(R.string.ok));
                            //刷新
                            isNowe();
                        } else {
                            ToastUtil.showInfo(HouseManageActivity.this, getString(R.string.error));
                        }
                    }
                });
    }

    /**
     * 添加单元层数  默认添加1个单元
     *
     * @param
     */
    public void updateCeng(String jzw_bm, int dyh) {
        OkHttpUtils
                .post()
                .url(MyApi.URL + MyApi.UPDATECENGNUM + jzw_bm)
                .tag(this)
                .addParams("dys", dyh + "")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        ToastUtil.showInfo(HouseManageActivity.this, getString(R.string.wufaData));
                    }

                    @Override
                    public void onResponse(String response) {
                        if (response.equals("ok")) {
                            ToastUtil.showInfo(HouseManageActivity.this, "添加成功");
                            //刷新
                            isNowe();
                        } else {
                            ToastUtil.showInfo(HouseManageActivity.this, "添加失败");
                        }
                    }
                });
    }

    /**
     * 新增单元
     *
     * @param
     */
    public void addDy(String jzw_bm) {
        DiaLogUtil.showDiaLog(this, "正在添加");
        OkHttpUtils
                .post()
                .url(MyApi.URL + MyApi.ADDDY + jzw_bm)
                .tag(this)
                .addParams("", "")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        DiaLogUtil.dismissDiaLog();
                        ToastUtil.showInfo(HouseManageActivity.this, getString(R.string.wufaData));
                    }

                    @Override
                    public void onResponse(String response) {
                        DiaLogUtil.dismissDiaLog();
                        if (response.equals("ok")) {
                            ToastUtil.showInfo(HouseManageActivity.this, "添加成功");
                            //刷新
                            isNowe();
                        } else {
                            ToastUtil.showInfo(HouseManageActivity.this, "添加失败");
                        }
                    }
                });
    }

    /**
     * 修改单元别名
     *
     * @param
     */
    public void updateBieName(String jzw_bm, int dyh, String newDybm, final int position) {
        OkHttpUtils
                .post()
                .url(MyApi.URL + MyApi.UPDATEBIENAME + jzw_bm)
                .tag(this)
                .addParams("dyh", dyh + "")
                .addParams("newDybm ", newDybm)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        ToastUtil.showInfo(HouseManageActivity.this, getString(R.string.wufaData));
                    }

                    @Override
                    public void onResponse(String response) {
                        if (response.equals("ok")) {
                            ToastUtil.showInfo(HouseManageActivity.this, "修改成功");
                            isNowe();
                        } else {
                            ToastUtil.showInfo(HouseManageActivity.this, "修改失败");
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //新增单元
            case R.id.base_toobar_right_button:
                if (NetWorkUtil.isConn(HouseManageActivity.this) == false) {
                    ToastUtil.showInfo(HouseManageActivity.this, getString(R.string.lixian));
                } else {
                    if (houseBean != null) {
                        new CommomDialog(HouseManageActivity.this, R.style.dialog, "点击添加 默认添加 1单元 1层 1户", new CommomDialog.OnCloseListener() {
                            @Override
                            public void onClick(Dialog dialog, boolean confirm) {
                                if (confirm) {
                                    addDy(houseBean.getData().getJzw().getJzw_bm());
                                    dialog.dismiss();
                                } else {
                                    dialog.dismiss();
                                }
                            }
                        }).setTitle("添加单元").setPositiveButton("点击添加").setNegativeButton("取消").show();
                    }
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 返回刷新  判断是否刷新
        boolean isResune = (boolean) SPUtils.get(HouseManageActivity.this, "isResune", false);

        if (isResune == true) {
            SPUtils.put(HouseManageActivity.this, "isResune", false);
            if (!TextUtils.isEmpty(floorCode)) {
                if (NetWorkUtil.isConn(HouseManageActivity.this) == false) {
                    //没有网 走数据库查询
                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            if (FileUtils.fileIsExists(MyApi.FileLoad + MyApi.DBNAME) == true) {
                                if (louMessage == null) {
                                    louMessage = new LouMessage(HouseManageActivity.this);
                                }
                                if (louMessage.tabbleIsExist("t_jzw_jlx") == true) {
                                    getSql(floorCode);
                                }
                            }
                        }
                    });
                } else {
                    getHoseMess(floorCode);
                }
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
                            louMessage = new LouMessage(HouseManageActivity.this);
                        }
                        if (louMessage.tabbleIsExist("t_jzw_jlx") == true) {
                            getSql(floorCode);
                        } else {
                            ToastUtil.showInfo(HouseManageActivity.this, "请返回首页下载离线数据");
                        }
                    } else {
                        ToastUtil.showInfo(HouseManageActivity.this, "请返回首页下载离线数据");
                    }
                }
            });

        } else if (netMobile == 0) {
            //ToastUtil.showInfo(this, "4G");
            login_nex_no_rl.setVisibility(View.GONE);
        }
    }

    //解决getActivity()为空的问题
    @SuppressLint("MissingSuperCall")
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //super.onSaveInstanceState(outState);   //注释掉该方法， 即不保存状态
    }

    public void ShouPopWindow(HouseBean houseBean, View view, int danName, int position) {
        View contentView = LayoutInflater.from(this).inflate(R.layout.item_popwindow_house, null);
        //处理popWindow 显示内容
        handleLogic(houseBean, contentView, danName, position);
        //创建并显示popWindow
        mCustomPopWindow = new CustomPopWindow.PopupWindowBuilder(this)
                .setView(contentView)
                .setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED)
                .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
                .create()
                .showAtLocation(contentView, Gravity.CENTER, 0, 0);

    }

    public void handleLogic(final HouseBean houseBean, View contentView, final int danName, final int position) {
        TextView textView = contentView.findViewById(R.id.danyuan_tv);//单元号
        final EditText editText = contentView.findViewById(R.id.et_danyuan2);//单元别名
        TextView guanbi_tv = contentView.findViewById(R.id.guanbi_tv);//关闭按钮
        Button deletebutton = contentView.findViewById(R.id.delete_button);//删除单元button
        Button ceng_button = contentView.findViewById(R.id.ceng_button);//修改层确定按钮
        Button bie_button = contentView.findViewById(R.id.bie_button);//修改别名确定按钮

        editText.setText(CengFragment.BieMing.equals("") ? "无别名" : CengFragment.BieMing);
        textView.setText(danName + "" + "单元");
        //修改别名
        bie_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (houseBean != null) {
                    mCustomPopWindow.dissmiss();
                    updateBieName(houseBean.getData().getJzw().getJzw_bm(), danName, editText.getText().toString().trim(), position);
                }
            }
        });

        //修改层确定按钮
        ceng_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (houseBean != null) {
                    mCustomPopWindow.dissmiss();
                    updateCeng(houseBean.getData().getJzw().getJzw_bm(), danName);
                }
            }
        });

        //删除单元
        deletebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (houseBean != null) {
                    mCustomPopWindow.dissmiss();
                    deleteDy(houseBean.getData().getJzw().getJzw_bm(), danName);
                }
            }
        });

        guanbi_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCustomPopWindow.dissmiss();
            }
        });
    }
}
