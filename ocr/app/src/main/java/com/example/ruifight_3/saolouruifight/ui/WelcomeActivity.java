package com.example.ruifight_3.saolouruifight.ui;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ruifight_3.saolouruifight.MyApi;
import com.example.ruifight_3.saolouruifight.R;
import com.example.ruifight_3.saolouruifight.baseui.BaseActivity;
import com.example.ruifight_3.saolouruifight.db.DBOpenHelper;
import com.example.ruifight_3.saolouruifight.db.datamessage.LouMessage;
import com.example.ruifight_3.saolouruifight.db.datamessage.RecordMeanage;
import com.example.ruifight_3.saolouruifight.db.util.FileUtils;
import com.example.ruifight_3.saolouruifight.db.util.bean.RecordBean;
import com.example.ruifight_3.saolouruifight.service.DownloaderService;
import com.example.ruifight_3.saolouruifight.threadpool.ThreadExecutorService;
import com.example.ruifight_3.saolouruifight.ui.bean.HomeDataBean;
import com.example.ruifight_3.saolouruifight.ui.bean.ModelHomeEntrance;
import com.example.ruifight_3.saolouruifight.util.AppUtils;
import com.example.ruifight_3.saolouruifight.util.ConvertUtils;
import com.example.ruifight_3.saolouruifight.util.DataUtils;
import com.example.ruifight_3.saolouruifight.util.DateUtil;
import com.example.ruifight_3.saolouruifight.util.DiaLogUtil;
import com.example.ruifight_3.saolouruifight.util.FileCacheUtils;
import com.example.ruifight_3.saolouruifight.util.JsonUtils;
import com.example.ruifight_3.saolouruifight.util.SPUtils;
import com.example.ruifight_3.saolouruifight.util.ScreenUtils;
import com.example.ruifight_3.saolouruifight.util.ToastUtil;
import com.example.ruifight_3.saolouruifight.util.VersionUtil;
import com.example.ruifight_3.saolouruifight.util.uiutil.BaiduUtil;
import com.example.ruifight_3.saolouruifight.widget.CommomDialog;
import com.example.ruifight_3.saolouruifight.widget.DownloadCircleDialog;
import com.example.ruifight_3.saolouruifight.widget.PageMenuLayout;
import com.example.ruifight_3.saolouruifight.widget.PrinterTextView;
import com.example.ruifight_3.saolouruifight.widget.SuitLines;
import com.example.ruifight_3.saolouruifight.widget.UIndicator;
import com.example.ruifight_3.saolouruifight.widget.Unit;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.squareup.okhttp.Request;
import com.stx.xhb.pagemenulibrary.holder.AbstractHolder;
import com.stx.xhb.pagemenulibrary.holder.PageMenuViewHolderCreator;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * Created by RuiFight-3 on 2018/7/3.
 */

public class WelcomeActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.baidu_address)
    TextView baidu_address;
    @BindView(R.id.baidu_state)
    TextView baidu_state;
    @BindView(R.id.baidu_du)
    TextView baidu_du;
    @BindView(R.id.line_chart)
    SuitLines suitLines;
    @BindView(R.id.yer_tv_we)
    TextView yer_tv;//年
    //记录用户首次点击返回键的时间
    private long firstTime = 0;

    //    @BindView(R.id.title_textview)
//    TextView title_textview;
    @BindView(R.id.welcome_smart)
    SmartRefreshLayout welcome_smart;
    @BindView(R.id.login_nex_no_rl)
    RelativeLayout login_nex_no_rl;
    @BindView(R.id.textview3)
    PrinterTextView printerTextView;
    //private String FileLoad = "/data/data/com.example.ruifight_3.saolouruifight/databases/";
    //设置高亮view 信息采集 //上传
    @BindView(R.id.hongdian_im)
    ImageView hongdian_im;
    //通知
    @BindView(R.id.tongzhi_tv_one)
    TextView tongzhi_tv_one;
    @BindView(R.id.tongzhi_tv_two)
    TextView tongzhi_tv_two;

    private List<Unit> lines = new ArrayList<>();
    private int downLoadId = 2;//分配的下载进程编号
    private LouMessage louMessage;
    private List<RecordBean> list;
    private RecordMeanage recordMeanage;

    private List<ModelHomeEntrance> homeEntrances;
    @BindView(R.id.indicator4)
    UIndicator entranceIndicatorView;
    @BindView(R.id.pagemenu)
    PageMenuLayout<ModelHomeEntrance> mPageMenuLayout;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 1:
                    HomeDataBean homeDataBean = (HomeDataBean) msg.obj;
                    if (homeDataBean.isStatus()) {
                        if (lines != null) {
                            lines.clear();
                        } else {
                            lines = new ArrayList<>();
                        }
                        if (homeDataBean.getData().getDates() != null && homeDataBean.getData().getDates().size() > 0 && homeDataBean.getData().getDates().size() == homeDataBean.getData().getRynums().size()) {
                            for (int i = 0; i < homeDataBean.getData().getDates().size(); i++) {
                                //获得第一个点的位置
                                int index = homeDataBean.getData().getDates().get(i).indexOf(".");
                                String result = homeDataBean.getData().getDates().get(i).substring(index + 1);
                                lines.add(new Unit(Float.parseFloat(homeDataBean.getData().getRynums().get(i) + ""), result));
                            }
                            initLineChartView(lines);
                        }
                    }
                    if ("0".equals(MyApi.ISHOMELOGIN)) {
                        printerTextView.setPrintText("录入人员统计");
                    } else {
                        printerTextView.setPrintText("每日工作量统计");
                    }
                    printerTextView.startPrint();

                    break;

                default:
                    break;
            }
        }
    };

    @Override
    protected int setLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initView() {
        setSteepStatusBar(true);
        setTooBarRightImage(R.drawable.shehzi);
        //天气开关
        boolean tianqiIs = (boolean) SPUtils.get(WelcomeActivity.this, "tianqiIs", false);
        if (tianqiIs == false) {
            BaiduUtil baiduUtil = new BaiduUtil();
            baiduUtil.initBaiDu(WelcomeActivity.this, baidu_address, baidu_state, baidu_du);
        } else {
            if ("0".equals(MyApi.ISHOMELOGIN)) {
                setWeTile(getString(R.string.welcomefangzhu));
            } else {
                setWeTile(getString(R.string.welcome));
            }
        }
        //初始化折线图
        suitLines.setDefaultOneLineColor(ContextCompat.getColor(WelcomeActivity.this, R.color.colors_bot));
        suitLines.setXySize(10);
        suitLines.setXyColor(ContextCompat.getColor(WelcomeActivity.this, R.color.colors_font6));
        suitLines.setLineType(SuitLines.SOLID);
        suitLines.setEdgeEffectColor(ContextCompat.getColor(WelcomeActivity.this, R.color.colors_bot));
        suitLines.setHintColor(ContextCompat.getColor(WelcomeActivity.this, R.color.colors_bot));
        suitLines.setShowYGrid(true);
        //线的宽度
        suitLines.setLineSize(6);

        //是否启用上拉加载功能
        welcome_smart.setEnableLoadMore(false);
        //是否启用下拉刷新功能
        welcome_smart.setEnableRefresh(true);
        //是否下拉Header的时候向下平移列表或者内容
        welcome_smart.setEnableHeaderTranslationContent(true);
        //设置Header
        welcome_smart.setRefreshHeader(new ClassicsHeader(WelcomeActivity.this));

        initMunData();
        initMenuLayout();
    }

    @Override
    protected void initData() {
        //服务器版本号
        int vert = (int) SPUtils.get(WelcomeActivity.this, "appVersion", 0);
        if (vert > VersionUtil.getVersionCode(WelcomeActivity.this)) {
            hongdian_im.setVisibility(View.VISIBLE);
            new CommomDialog(WelcomeActivity.this, R.style.dialog, (String) SPUtils.get(WelcomeActivity.this, "versionComment", ""), new CommomDialog.OnCloseListener() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onClick(Dialog dialog, boolean confirm) {
                    if (confirm) {
                        DownLoadApk();
                        dialog.dismiss();
                    } else {
                        dialog.dismiss();
                    }
                }
            }).setTitle("发现新版本").setPositiveButton("点击升级").setNegativeButton("取消").show();

        } else {
            hongdian_im.setVisibility(View.GONE);
        }

        WelcomeActivity.this.runOnUiThread(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void run() {
                //设置折线图上边年份
                yer_tv.setText(DataUtils.getDateYer() + "年");
            }
        });
        //获取折线图数据
        getMessage(1);
        welcome_smart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                //获取数据
                getMessage(2);
            }
        });
    }

    public void initMunData() {
        if ("0".equals(MyApi.ISHOMELOGIN)) {
            if (homeEntrances == null) {
                homeEntrances = new ArrayList<>();
            }
            homeEntrances.add(new ModelHomeEntrance("信息采集", R.drawable.message));
            homeEntrances.add(new ModelHomeEntrance("人员查询", R.drawable.query));
            homeEntrances.add(new ModelHomeEntrance("修改密码", R.drawable.updatepass));
            homeEntrances.add(new ModelHomeEntrance("退出登录", R.drawable.breaklogins));

        } else {
            if (homeEntrances == null) {
                homeEntrances = new ArrayList<>();
            }
            homeEntrances.add(new ModelHomeEntrance("信息采集", R.drawable.message));
            homeEntrances.add(new ModelHomeEntrance("关注任务", R.drawable.guanzhu));
            homeEntrances.add(new ModelHomeEntrance("离线下载", R.drawable.lixian));
            homeEntrances.add(new ModelHomeEntrance("采集上传", R.drawable.sahngchuan));
            homeEntrances.add(new ModelHomeEntrance("辖区统计", R.drawable.datatongji));
            homeEntrances.add(new ModelHomeEntrance("人员查询", R.drawable.query));
            homeEntrances.add(new ModelHomeEntrance("修改密码", R.drawable.updatepass));
            homeEntrances.add(new ModelHomeEntrance("退出登录", R.drawable.breaklogins));
            homeEntrances.add(new ModelHomeEntrance("删除记录", R.drawable.deletejilu));
        }
    }

    public void initMenuLayout() {
        mPageMenuLayout.setPageDatas(homeEntrances, new PageMenuViewHolderCreator() {
            @Override
            public AbstractHolder createHolder(View itemView) {
                return new AbstractHolder<ModelHomeEntrance>(itemView) {
                    private TextView entranceNameTextView;
                    private ImageView entranceIconImageView;

                    @Override
                    protected void initView(View itemView) {
                        entranceIconImageView = itemView.findViewById(R.id.entrance_image);
                        entranceNameTextView = itemView.findViewById(R.id.entrance_name);
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (int) ((float) ScreenUtils.getScreenWidth() / 4.0f));
                        itemView.setLayoutParams(layoutParams);
                    }

                    @Override
                    public void bindView(RecyclerView.ViewHolder holder, final ModelHomeEntrance data, final int pos) {
                        entranceNameTextView.setText(data.getName());
                        entranceIconImageView.setImageResource(data.getImage());
                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                switch (data.getName()) {
                                    case "信息采集":
                                        Intent intent1 = new Intent(WelcomeActivity.this, HomeActivity.class);
                                        startActivity(intent1);
                                        break;
                                    case "关注任务":
                                        Intent intent4 = new Intent(WelcomeActivity.this, AttentionActivity.class);
                                        startActivity(intent4);
                                        overridePendingTransition(R.anim.stop, R.anim.start);
                                        break;
                                    case "离线下载":
                                        Intent intent3 = new Intent(WelcomeActivity.this, XiaZaiActivity.class);
                                        startActivity(intent3);
                                        overridePendingTransition(R.anim.stop, R.anim.start);
                                        break;
                                    case "采集上传":
                                        Intent intent8 = new Intent(WelcomeActivity.this, UploadActivity.class);
                                        startActivity(intent8);
                                        overridePendingTransition(R.anim.stop, R.anim.start);
                                        break;
                                    case "辖区统计":
                                        Intent intent2 = new Intent(WelcomeActivity.this, DataStatisticsActivity.class);
                                        startActivity(intent2);
                                        overridePendingTransition(R.anim.stop, R.anim.start);
                                        break;
                                    case "人员查询":
                                        Intent intent5 = new Intent(WelcomeActivity.this, PersonnelQueryActivity.class);
                                        startActivity(intent5);
                                        overridePendingTransition(R.anim.stop, R.anim.start);
                                        break;
                                    case "修改密码":
                                        Intent intent = new Intent(WelcomeActivity.this, UpdatePassActivity.class);
                                        startActivity(intent);
                                        overridePendingTransition(R.anim.stop, R.anim.start);
                                        break;
                                    case "退出登录":
                                        new CommomDialog(WelcomeActivity.this, R.style.dialog, "您确定要退出登录吗？", new CommomDialog.OnCloseListener() {
                                            @Override
                                            public void onClick(Dialog dialog, boolean confirm) {
                                                if (confirm) {
                                                    dialog.dismiss();
                                                    DBOpenHelper.getInstance(WelcomeActivity.this, true);
                                                    //判断下载服务是否关闭
                                                    if (AppUtils.isServiceRunning(WelcomeActivity.this)) {
                                                        stopService(new Intent(WelcomeActivity.this, DownloaderService.class));
                                                    }
                                                    Intent intent2 = new Intent(WelcomeActivity.this, LoginHomeActivity.class);
                                                    startActivity(intent2);
                                                    finish();
                                                    overridePendingTransition(R.anim.stop, R.anim.start);
                                                }
                                            }
                                        }).setTitle("提示").show();
                                        break;
                                    case "删除记录":
                                        Intent intent6 = new Intent(WelcomeActivity.this, DeleteRecordActivity.class);
                                        startActivity(intent6);
                                        overridePendingTransition(R.anim.stop, R.anim.start);
                                        break;
                                    default:
                                        break;
                                }
                            }
                        });
                    }
                };
            }

            @Override
            public int getLayoutId() {
                return R.layout.item_home_entrance;
            }
        });
        if (mPageMenuLayout.getViewPager() != null) {
            entranceIndicatorView.attachToViewPager(mPageMenuLayout.getViewPager());
        }
    }

    /**
     * @param keyCode
     * @param event
     * @return
     */

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                long secondTime = System.currentTimeMillis();
                if (secondTime - firstTime > 2000) {
                    Toast.makeText(WelcomeActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                    firstTime = secondTime;
                    return true;
                } else {
                    finish();
                }
                break;
            default:
                break;
        }
        return super.onKeyUp(keyCode, event);
    }

    /**
     * 获取首页数据
     */
    public void getMessage(final int type) {
        if (type == 1) {
            DiaLogUtil.showDiaLog(this, "加载中");
        }
        OkHttpUtils
                .post()
                .url(MyApi.URL + MyApi.HOMEDATA)
                .tag(this)
                .addHeader("JSESSIONID", (String) SPUtils.get(WelcomeActivity.this, "JSESSIONID", ""))
                .addParams("", "")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        if (type == 1) {
                            DiaLogUtil.dismissDiaLog();
                        } else {
                            //刷新失败
                            welcome_smart.finishRefresh(false);
                        }
                        ToastUtil.showInfo(WelcomeActivity.this, getString(R.string.wufaData));
                    }

                    @Override
                    public void onResponse(String response) {
                        // Log.e(TAG, "homemessage" + response);
                        if (type == 1) {
                            DiaLogUtil.dismissDiaLog();
                        } else {
                            //刷新成功
                            welcome_smart.finishRefresh(true);
                        }
                        try {
                            HomeDataBean homeDataBean = (HomeDataBean) JsonUtils.stringToObject(response, HomeDataBean.class);
                            Message msg = new Message();
                            msg.what = 1;
                            msg.obj = homeDataBean;
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
    }

    @Override
    public void onNetChange(int netMobile) {
        if (netMobile == 1) {
            //ToastUtil.showInfo(this, "有网络wifi");
            login_nex_no_rl.setVisibility(View.GONE);
        } else if (netMobile == -1) {
            //ToastUtil.showInfo(this, "没有网络");
            login_nex_no_rl.setVisibility(View.VISIBLE);  //ruifightSaoLou.db
            if (FileUtils.fileIsExists(MyApi.FileLoad + MyApi.DBNAME) == false) {

                new CommomDialog(WelcomeActivity.this, R.style.dialog, "已断开网络连接，您还未下载离线文件，确认去下载吗？", new CommomDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm) {
                        if (confirm) {
                            Intent intent3 = new Intent(WelcomeActivity.this, XiaZaiActivity.class);
                            startActivity(intent3);
                            dialog.dismiss();
                        }
                    }
                }).setTitle("重要提示").show();

            } else {
                if (louMessage == null) {
                    louMessage = new LouMessage(WelcomeActivity.this);
                }
                if (louMessage.tabbleIsExist("t_jzw_jlx") == false) {

                    new CommomDialog(WelcomeActivity.this, R.style.dialog, "已断开网络连接，您还未下载离线文件，确认去下载吗？", new CommomDialog.OnCloseListener() {
                        @Override
                        public void onClick(Dialog dialog, boolean confirm) {
                            if (confirm) {
                                Intent intent3 = new Intent(WelcomeActivity.this, XiaZaiActivity.class);
                                startActivity(intent3);
                                dialog.dismiss();
                            }
                        }
                    }).setTitle("重要提示").show();


                } else {
                    ToastUtil.showInfoLong(this, "注意 已自动切换到离线模式,采集完请连接网络及时上传");
                }
            }
        } else if (netMobile == 0) {
            //ToastUtil.showInfo(this, "4G");
            login_nex_no_rl.setVisibility(View.GONE);
        }
    }

    /**
     * 回来判断数据
     */
    @TargetApi(Build.VERSION_CODES.N)
    @Override
    protected void onResume() {
        super.onResume();
        try {
            getNotion();
            upData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取通知信息
     */
    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getNotion() {
        final String xiazaiactivity_datatime = (String) SPUtils.get(WelcomeActivity.this, SPUtils.get(WelcomeActivity.this, "welcomeUserName", "") + "_Messdata", "");
        if (!"".equals(xiazaiactivity_datatime)) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    try {
                        tongzhi_tv_one.setText(getString(R.string.grount) + DateUtil.getDescriptionTimeFromDateString(xiazaiactivity_datatime) + "#更新过离线数据");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } else {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    tongzhi_tv_one.setText("· " + DateUtil.getStringDateShort());
                }
            });
        }
    }

    /**
     * 判断有网  如果有操作记录提示用户上传
     */
    public void upData() {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final String deletetime = (String) SPUtils.get(WelcomeActivity.this, SPUtils.get(WelcomeActivity.this, "welcomeUserName", "") + "_deleteData", "");
                if (FileUtils.fileIsExists(MyApi.FileLoad + MyApi.DBNAME)) {
                    if (louMessage == null) {
                        louMessage = new LouMessage(WelcomeActivity.this);
                    }
                    if (louMessage.tabbleIsExist("t_jzw_jlx")) {
                        if (recordMeanage == null) {
                            recordMeanage = new RecordMeanage(WelcomeActivity.this);
                        }
                        //查询记录表
                        if (list == null) {
                            list = new ArrayList<>();
                        }
                        list = recordMeanage.selectRecord();

                        if (list != null && list.size() > 0) {
                            //查询记录表条数
                            final Long count = recordMeanage.recordData();
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    tongzhi_tv_two.setText(ConvertUtils.setNumColor("· 您有未上传采集数据  " + count + "  条" + ", 请及时上传" + ""));
                                    //ToastUtil.shouToast(WelcomeActivity.this, ConvertUtils.setNumColor("您有未上传采集数据  " + count + "  条" + ", 请及时上传" + ""));
                                }
                            });
                        } else {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    if ("0".equals(MyApi.ISHOMELOGIN)) {
                                        tongzhi_tv_two.setText(ConvertUtils.setNumColor("· 欢迎房主"));
                                    } else {
                                        tongzhi_tv_two.setText(ConvertUtils.setNumColor("· 欢迎流管员"));
                                    }
                                }
                            });
                        }
                    } else {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                if (!"".equals(deletetime)) {
                                    tongzhi_tv_two.setText("· 您已清除离线文件时间:" + deletetime);
                                } else {
                                    if ("0".equals(MyApi.ISHOMELOGIN)) {
                                        tongzhi_tv_two.setText(ConvertUtils.setNumColor("· 欢迎房主"));
                                    } else {
                                        tongzhi_tv_two.setText(ConvertUtils.setNumColor("· 您还未下载离线文件"));
                                    }
                                }
                            }
                        });
                    }
                } else {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (!"".equals(deletetime)) {
                                tongzhi_tv_two.setText("· 您已清除离线文件时间:" + deletetime);
                            } else {
                                if ("0".equals(MyApi.ISHOMELOGIN)) {
                                    tongzhi_tv_two.setText(ConvertUtils.setNumColor("· 欢迎房主"));
                                } else {
                                    tongzhi_tv_two.setText(ConvertUtils.setNumColor("· 您还未下载离线文件"));
                                }
                            }
                        }
                    });
                }
            }
        };
        ThreadExecutorService.newCachedThreadPool(1).execute(runnable);
    }


    /**
     * 下载 APK
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void DownLoadApk() {
        final DownloadCircleDialog downloadCircleDialog = new DownloadCircleDialog(WelcomeActivity.this);
        downloadCircleDialog.show();
        FileDownloader.setup(this);
        FileDownloader.getImpl().create(MyApi.APKUPDATE + "?" + DateUtil.getTimeStamp(DateUtil.getStringDateShortMine(), "yyyy年MM月dd日 HH:mm:ss"))
                .setPath(MyApi.APLPATH)
                //强制重新下载，将会忽略检测文件是否健在
                .setForceReDownload(true)
                //设置整个下载过程中FileDownloadListener#progress最大回调次数
                .setCallbackProgressTimes(700)
                //设置下载中刷新下载速度的最小间隔
                .setMinIntervalUpdateSpeed(400)
                .setTag(downLoadId)
                .setListener(new FileDownloadListener() {
                    //等待
                    @Override
                    protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                        downloadCircleDialog.setMsg("连接中..");
                    }

                    //下载进度回调
                    @Override
                    protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                        downloadCircleDialog.setProgress((soFarBytes * 100) / totalBytes);
                        downloadCircleDialog.setMsg(String.format("%dKB/s  ", task.getSpeed()) + FileCacheUtils.getFormatSize(soFarBytes) + " 下载中..");
                    }

                    //完成下载
                    @Override
                    protected void completed(BaseDownloadTask task) {
                        downloadCircleDialog.setMsg("下载完成");
                        downloadCircleDialog.dismiss();
                        //清空filedownloader数据库中的所有数据
                        FileDownloader.getImpl().clearAllTaskData();
                        installApkO();
                    }

                    //暂停
                    @Override
                    protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {

                    }

                    //下载出错
                    @Override
                    protected void error(BaseDownloadTask task, Throwable e) {
                        downloadCircleDialog.setMsg("下载出错");
                        downloadCircleDialog.dismiss();
                        ToastUtil.showInfo(WelcomeActivity.this, "下载出错");
                    }

                    //已存在相同下载
                    @Override
                    protected void warn(BaseDownloadTask task) {

                    }
                })
                .start();
    }

    //下载完成
    public void installApkO() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //是否有安装位置来源的权限
            boolean haveInstallPermission = getPackageManager().canRequestPackageInstalls();
            if (haveInstallPermission) {
//                L.i("8.0手机已经拥有安装未知来源应用的权限，直接安装！");
                installApk(WelcomeActivity.this, MyApi.APLPATH);
            } else {
                new CommomDialog(WelcomeActivity.this, R.style.dialog, "安装应用需要打开安装未知来源应用权限，请去设置中开启权限\"", new CommomDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm) {
                        if (confirm) {
                            Uri packageUri = Uri.parse("package:" + VersionUtil.getAppProcessName(WelcomeActivity.this));
                            Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, packageUri);
                            startActivityForResult(intent, 5);
                            dialog.dismiss();
                        }
                    }
                }).setTitle("提示").show();
            }
        } else {
            installApk(WelcomeActivity.this, MyApi.APLPATH);
        }
    }

    /**
     * 7.0安装权限
     *
     * @param context
     * @param downloadApk
     */
    public void installApk(Context context, String downloadApk) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        File file = new File(downloadApk);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Uri apkUri = FileProvider.getUriForFile(context, VersionUtil.getAppProcessName(WelcomeActivity.this) + ".fileProvider", file);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        } else {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Uri uri = Uri.fromFile(file);
            intent.setDataAndType(uri, "application/vnd.android.package-archive");
        }
        context.startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 5) {
            //是否有安装位置来源的权限
            boolean haveInstallPermission = getPackageManager().canRequestPackageInstalls();
            if (haveInstallPermission) {
                installApk(WelcomeActivity.this, MyApi.APLPATH);
            } else {
                ToastUtil.showInfo(WelcomeActivity.this, "未成功授权");
            }
        }
    }

    public void initLineChartView(List<Unit> lines) {
        if (suitLines != null && lines.size() > 0) {
            suitLines.feedWithAnim(lines);
        }
    }
}
