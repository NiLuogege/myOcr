package com.example.ruifight_3.saolouruifight.ui;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ruifight_3.saolouruifight.MyApi;
import com.example.ruifight_3.saolouruifight.R;
import com.example.ruifight_3.saolouruifight.baseui.BaseActivity;
import com.example.ruifight_3.saolouruifight.db.datamessage.HomeMessage;
import com.example.ruifight_3.saolouruifight.db.datamessage.LouMessage;
import com.example.ruifight_3.saolouruifight.db.util.FileUtils;
import com.example.ruifight_3.saolouruifight.ui.bean.StatisticsBean;
import com.example.ruifight_3.saolouruifight.util.ConvertUtils;
import com.example.ruifight_3.saolouruifight.util.DiaLogUtil;
import com.example.ruifight_3.saolouruifight.util.JsonUtils;
import com.example.ruifight_3.saolouruifight.util.NetWorkUtil;
import com.example.ruifight_3.saolouruifight.util.SPUtils;
import com.example.ruifight_3.saolouruifight.util.ToastUtil;
import com.razerdp.widget.animatedpieview.AnimatedPieView;
import com.razerdp.widget.animatedpieview.AnimatedPieViewConfig;
import com.razerdp.widget.animatedpieview.data.SimplePieInfo;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.text.NumberFormat;

import butterknife.BindView;

/**
 * Created by RuiFight-3 on 2019/4/28.
 * Creator: 张震 (Android  zhangzhen)
 * Describe:
 */
public class DataStatisticsActivity extends BaseActivity {

    @BindView(R.id.login_nex_no_rl)
    RelativeLayout login_nex_no_rl;
    @BindView(R.id.title_textview)
    TextView title_textview;
    @BindView(R.id.anima_picview)
    AnimatedPieView anima_picview;
    @BindView(R.id.personne_tv)
    TextView personne_tv;
    private LouMessage louMessage;
    private HomeMessage homeMessage;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    StatisticsBean statisticsBean = (StatisticsBean) msg.obj;
                    if (statisticsBean.isStatus()) {
                        personne_tv.setVisibility(View.VISIBLE);
                        title_textview.setText(ConvertUtils.setNumColor("截止目前: 建筑物共" + statisticsBean.getData().getJzwTotal() + "" + "栋,"
                                + "房屋" + statisticsBean.getData().getFangwu_caiji() + "" + "户。" + "已采集房主信息" + statisticsBean.getData().getFzTotal() + "" + "户，" + "其中：出租房屋" + statisticsBean.getData().getFangwu_chuzu() + "" + "户。" + "共登记" + statisticsBean.getData().getUserTotal() + "" + "人," + "其中：" + "户籍人口" + statisticsBean.getData().getRenshu_hjrk() + "" + "人."
                                + "流动人口" + statisticsBean.getData().getRenshu_ldrk() + "" + "人，" + "人户分离人口" + statisticsBean.getData().getRenshu_rhfl() + "" + "人。"));

                        if (anima_picview != null) {
                            AnimatedPieViewConfig config = new AnimatedPieViewConfig();
                            config.startAngle(-90)// 起始角度偏移
                                    .canTouch(true)
                                    .autoSize(true)// 自动测量甜甜圈半径
                                    .strokeMode(false)// 是否只画圆弧【甜甜圈哈哈】，否则画扇形（默认true）
                                    .drawText(true)
                                    .textSize(25f)
                                    .pieRadius(200)// 甜甜圈半径
                                    .floatExpandAngle(15f)// 点击后圆弧/扇形扩展的角度
                                    .guideLineWidth(4)// 设置描述文字的指示线宽度
                                    .addData(new SimplePieInfo(Float.parseFloat(numFormat(statisticsBean.getData().getUserTotal(), statisticsBean.getData().getRenshu_ldrk())), ContextCompat.getColor(DataStatisticsActivity.this, R.color.colors_fontGreen2), "流动人口" + numFormat(statisticsBean.getData().getUserTotal(), statisticsBean.getData().getRenshu_ldrk()) + "%"))//数据（实现IPieInfo接口的bean）
                                    .addData(new SimplePieInfo(Float.parseFloat(numFormat(statisticsBean.getData().getUserTotal(), statisticsBean.getData().getRenshu_hjrk())), ContextCompat.getColor(DataStatisticsActivity.this, R.color.colors_fontDarkBlue), "户籍人口" + numFormat(statisticsBean.getData().getUserTotal(), statisticsBean.getData().getRenshu_hjrk()) + "%"))//数据（实现IPieInfo接口的bean）
                                    .addData(new SimplePieInfo(Float.parseFloat(numFormat(statisticsBean.getData().getUserTotal(), statisticsBean.getData().getRenshu_rhfl())), ContextCompat.getColor(DataStatisticsActivity.this, R.color.colors_fontOrange2), "人户分离" + numFormat(statisticsBean.getData().getUserTotal(), statisticsBean.getData().getRenshu_rhfl()) + "%"))
                                    .duration(1000);// 持续时间
                            anima_picview.applyConfig(config);
                            anima_picview.start();

                        }
                    }
                    break;

                case 2:
                    String building = msg.getData().getString("building");
                    String houses = msg.getData().getString("houses");
                    title_textview.setText(ConvertUtils.setNumColor("建筑物共" + building + "" + "栋," + "房屋" + houses + "" + "户。"));
                    break;
                default:
                    break;
            }

        }
    };

    @Override
    protected int setLayout() {
        return R.layout.activity_data_statistics;
    }

    @Override
    protected void initView() {
        setSteepStatusBar(true);
        setTooBarBreakImage(R.mipmap.breakimagee);
        setTooBarTitleText("辖区统计");

    }

    @Override
    protected void initData() {
        getDatas();
    }

    public void getDatas() {
        if (NetWorkUtil.isConn(DataStatisticsActivity.this) == false) {
            //没有网 走数据库查询
            if (FileUtils.fileIsExists(MyApi.FileLoad + MyApi.DBNAME) == true) {
                if (louMessage == null) {
                    louMessage = new LouMessage(DataStatisticsActivity.this);
                }
                if (louMessage.tabbleIsExist("t_jzw_jlx") == true) {
                    getSql();
                } else {
                    ToastUtil.showInfo(DataStatisticsActivity.this, "请先下载离线数据");
                }
            } else {
                ToastUtil.showInfo(DataStatisticsActivity.this, "请先下载离线数据");
            }
        } else {
            getMessageData();
        }
    }


    /**
     * 获取统计数据
     */
    public void getMessageData() {
        DiaLogUtil.showDiaLog(DataStatisticsActivity.this, "加载中");
        OkHttpUtils
                .post()
                .url(MyApi.URL + MyApi.XIAQU)
                .tag(this)
                .addHeader("JSESSIONID", (String) SPUtils.get(DataStatisticsActivity.this, "JSESSIONID", ""))
                .addParams("", "")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        DiaLogUtil.dismissDiaLog();
                        ToastUtil.showInfo(DataStatisticsActivity.this, getString(R.string.wufaData));
                    }

                    @Override
                    public void onResponse(String response) {
                        DiaLogUtil.dismissDiaLog();
                        try {
                            StatisticsBean statisticsBean = (StatisticsBean) JsonUtils.stringToObject(response, StatisticsBean.class);
                            Message msg = new Message();
                            msg.what = 1;
                            msg.obj = statisticsBean;
                            handler.sendMessage(msg);
                        } catch (Exception e) {
                            e.printStackTrace();
                            setLogin();
                        }
                    }
                });

    }

    /**
     * 获取数据库总数
     */
    public void getSql() {
        if (homeMessage == null) {
            homeMessage = new HomeMessage(DataStatisticsActivity.this);
        }
        Long building = homeMessage.homeData();// 共多少栋建筑物
        Long houses = homeMessage.homeFangWuData();//多少房屋
        Message msg = new Message();
        msg.what = 2;
        Bundle bundle = new Bundle();
        bundle.putString("building", building + "");
        bundle.putString("houses", houses + "");
        msg.setData(bundle);
        handler.sendMessage(msg);
    }

    @Override
    public void onNetChange(int netMobile) {
        if (netMobile == 1) {
            //ToastUtil.showInfo(this, "有网络wifi");
            login_nex_no_rl.setVisibility(View.GONE);
        } else if (netMobile == -1) {
            //ToastUtil.showInfo(this, "没有网络");
            login_nex_no_rl.setVisibility(View.VISIBLE);
            getDatas();
        } else if (netMobile == 0) {
            //ToastUtil.showInfo(this, "4G");
            login_nex_no_rl.setVisibility(View.GONE);
        }
    }

    public String numFormat(int numerator, int denominator) {
        float numeratorf = (float) numerator;//转换成浮点型
        float denominatorf = (float) denominator;
        NumberFormat nt = NumberFormat.getPercentInstance();//获取百分数实例
        nt.setMinimumFractionDigits(2);
        return nt.format(denominatorf / numeratorf).replace("%", "").trim();//得到结果
    }
}
