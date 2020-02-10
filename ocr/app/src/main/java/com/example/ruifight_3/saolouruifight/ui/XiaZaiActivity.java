package com.example.ruifight_3.saolouruifight.ui;

import android.Manifest;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.example.ruifight_3.saolouruifight.MyApi;
import com.example.ruifight_3.saolouruifight.R;
import com.example.ruifight_3.saolouruifight.baseui.BaseActivity;
import com.example.ruifight_3.saolouruifight.db.datamessage.LouMessage;
import com.example.ruifight_3.saolouruifight.db.util.FileUtils;
import com.example.ruifight_3.saolouruifight.myinterface.PermissionCallBack;
import com.example.ruifight_3.saolouruifight.service.DownloaderService;
import com.example.ruifight_3.saolouruifight.threadpool.ThreadExecutorService;
import com.example.ruifight_3.saolouruifight.util.ConvertUtils;
import com.example.ruifight_3.saolouruifight.util.FileCacheUtils;
import com.example.ruifight_3.saolouruifight.util.NetWorkUtil;
import com.example.ruifight_3.saolouruifight.util.SPUtils;
import com.example.ruifight_3.saolouruifight.util.ToastUtil;
import com.example.ruifight_3.saolouruifight.widget.CommomDialog;

import butterknife.BindView;


/**
 * Created by RuiFight-3 on 2018/7/2.
 */

public class XiaZaiActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.tishi_tv)
    TextView tishi_tv;
    @BindView(R.id.image)
    ImageView imageView;
    @BindView(R.id.number_progress_bar)
    NumberProgressBar ProgressBar;
    @BindView(R.id.up_text)
    TextView up_text;
    @BindView(R.id.tishi_image)
    TextView tishi_image;
    @BindView(R.id.xiazai_mb_tv)
    TextView xiazai_mb_tv;
    @BindView(R.id.neicun_tv)
    TextView neicun_tv;
    @BindView(R.id.progress_neicun)
    ProgressBar progress_neicun;
    @BindView(R.id.time_activity_xaizai)
    TextView time_activity_xaizai;
    //广播
    public static final String CLOCK_ACTION = "com.jereh.Clock_Action";
    private LouMessage louMessage;

    @Override
    protected int setLayout() {
        return R.layout.activity_xiazai;
    }

    @Override
    protected void initView() {
        setSteepStatusBar(true);
        setTooBarBreakImage(R.mipmap.breakimagee);
        setTooBarTitleText("离线文件下载");

        imageView.setOnClickListener(this);
        imageView.setEnabled(true);
        ProgressBar.setMax(100);
        ProgressBar.setProgress(0);
        tishi_image.setText(getString(R.string.xiazaiActivity));
        regReceiver();//注册广播

    }


    @Override
    protected void initData() {
        //获取内存信息
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                boolean is = (boolean) SPUtils.get(XiaZaiActivity.this, SPUtils.get(XiaZaiActivity.this, "welcomeUserName", "") + "_okip", false);
                if (is) {
                    up_text.setVisibility(View.VISIBLE);
                    ProgressBar.setProgress(ProgressBar.getProgress());
                    tishi_image.setText(MyApi.DBNAME + getString(R.string.ShaoHou));
                    xiazai_mb_tv.setVisibility(View.VISIBLE);
                    tishi_tv.setVisibility(View.VISIBLE);
                    tishi_tv.setText(getString(R.string.dengdai));
                }
                progress_neicun.setMax((int) Double.parseDouble(FileCacheUtils.getTotalMem(2)));
                progress_neicun.setProgress((int) Double.parseDouble(FileCacheUtils.getFreeMem(2)));
                neicun_tv.setText("总内存" + FileCacheUtils.getTotalMem(1) + "/剩余容量" + FileCacheUtils.getFreeMem(1));
                /**
                 * sp 取值
                 */
                String downloadDataTime = (String) SPUtils.get(XiaZaiActivity.this, SPUtils.get(XiaZaiActivity.this, "welcomeUserName", "") + "_dowtime", "null");
                if (!("null").equals(downloadDataTime)) {
                    time_activity_xaizai.setVisibility(View.VISIBLE);
                    time_activity_xaizai.setText(ConvertUtils.setNumColor(getString(R.string.dowtime) + downloadDataTime));
                } else {
                    time_activity_xaizai.setVisibility(View.GONE);
                }
            }
        };
        ThreadExecutorService.newCachedThreadPool(1).execute(runnable);
    }

    public void regReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(CLOCK_ACTION);
        super.registerReceiver(clockReceiver, intentFilter);
    }

    /**
     * 广播接受者，接受来自ClockService（计时服务）的广播，ClockService每隔一秒
     * 钟发一次广播
     */
    private BroadcastReceiver clockReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int state = intent.getIntExtra("state", 0);
            switch (state) {
                //等待
                case 1:
                    final String take = intent.getStringExtra("take");
                    XiaZaiActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            up_text.setVisibility(View.VISIBLE);
                            ProgressBar.setProgress(ProgressBar.getProgress());
                            tishi_image.setText(take + getString(R.string.ShaoHou));
                            xiazai_mb_tv.setVisibility(View.VISIBLE);
                            tishi_tv.setVisibility(View.VISIBLE);
                            tishi_tv.setText(getString(R.string.dengdai));
                        }
                    });

                    break;
                //下载进度回调
                case 2:
                    final String take1 = intent.getStringExtra("take");
                    final int soFarBytes = intent.getIntExtra("soFarBytes", 0);
                    final int totalBytes = intent.getIntExtra("totalBytes", 0);
                    final int getSpeed = intent.getIntExtra("getSpeed", 0);
                    XiaZaiActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tishi_tv.setText(getString(R.string.xaizaiz));
                            tishi_image.setText(take1 + getString(R.string.XiaZai));
                            ProgressBar.setProgress(convertFileSize(soFarBytes));
                            up_text.setText(setNumColor(String.format("%dKB/s", getSpeed)));
                            xiazai_mb_tv.setText(getString(R.string.filesize) + FileCacheUtils.getFormatSize(soFarBytes));
                            //String.format(" %dM", fileSize(soFarBytes))
                        }
                    });
                    break;
                //完成下载
                case 3:
                    final int getSmallFileTotalBytes = intent.getIntExtra("getSmallFileTotalBytes", 0);
                    final int getSmallFileSoFarBytes = intent.getIntExtra("getSmallFileSoFarBytes", 0);
                    final String getFilename = intent.getStringExtra("getFilename");
                    XiaZaiActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            imageView.setEnabled(false);
                            up_text.setVisibility(View.GONE);
                            ProgressBar.setMax(getSmallFileTotalBytes);
                            ProgressBar.setProgress(getSmallFileSoFarBytes);
                            tishi_image.setText(getFilename + getString(R.string.WanCheng));
                            tishi_tv.setVisibility(View.INVISIBLE);
                        }
                    });
                    stopService(new Intent(XiaZaiActivity.this, DownloaderService.class));
                    break;
                //下载出错
                case 4:
                    final String error = intent.getStringExtra("error");
                    XiaZaiActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(XiaZaiActivity.this, getString(R.string.eChang), Toast.LENGTH_SHORT).show();
                            up_text.setVisibility(View.GONE);
                            tishi_image.setText(error + getString(R.string.xiaZaiEs));
                            tishi_tv.setVisibility(View.INVISIBLE);
                        }
                    });
                    break;
                //已存在相同下载
                case 5:
                    XiaZaiActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            imageView.setEnabled(false);
                            ToastUtil.showInfo(XiaZaiActivity.this, "下载连接中，请不要重复点击");
                        }
                    });
                    break;

                default:
                    break;
            }
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image:
                checkPermission();
                break;

            default:
                break;
        }
    }

    public void uoFile() {
        if (NetWorkUtil.WifiConnected(XiaZaiActivity.this)) {
            startService(new Intent(this, DownloaderService.class));
        } else {
            new CommomDialog(XiaZaiActivity.this, R.style.dialog, "当前不是WiFi环境 确定要下载吗？", new CommomDialog.OnCloseListener() {
                @Override
                public void onClick(Dialog dialog, boolean confirm) {
                    if (confirm) {
                        dialog.dismiss();
                        startService(new Intent(XiaZaiActivity.this, DownloaderService.class));
                    }
                }
            }).setTitle("提示").setPositiveButton("确定下载").setNegativeButton("取消").show();
        }
    }

    private void checkPermission() {

        //登录检查权限
        checkPermissions(XiaZaiActivity.this, new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, new PermissionCallBack() {
            @Override
            public void granted() {
                if (louMessage == null) {
                    louMessage = new LouMessage(XiaZaiActivity.this);
                }
                // Toast.makeText(this, "授权成功！", Toast.LENGTH_SHORT).show();

                if (FileUtils.fileIsExists(MyApi.FileLoad + MyApi.DBNAME) == false) {

                    if (NetWorkUtil.isConn(XiaZaiActivity.this) == false) {
                        //没有网
                        ToastUtil.showInfo(XiaZaiActivity.this, "失败 请连接网络下载");
                    } else {
                        //有网
                        uoFile();
                    }

                } else if (louMessage.tabbleIsExist("t_jzw_jlx") == false) {

                    if (NetWorkUtil.isConn(XiaZaiActivity.this) == false) {
                        //没有网
                        ToastUtil.showInfo(XiaZaiActivity.this, "失败 请连接网络下载");
                    } else {
                        //有网
                        uoFile();
                    }
                } else {
                    new CommomDialog(XiaZaiActivity.this, R.style.dialog, "离线文件已存在，确定要更新覆盖吗？如果有离线未上传采集，也会覆盖掉，还要执行吗？", new CommomDialog.OnCloseListener() {
                        @Override
                        public void onClick(Dialog dialog, boolean confirm) {
                            if (confirm) {
                                if (NetWorkUtil.isConn(XiaZaiActivity.this) == false) {
                                    //没有网
                                    dialog.dismiss();
                                    ToastUtil.showInfo(XiaZaiActivity.this, "失败 请连接网络下载");
                                } else {
                                    //有网
                                    dialog.dismiss();
                                    uoFile();
                                }
                            }
                        }
                    }).setTitle("重要提示").show();
                }
            }

            @Override
            public void denied() {
                ToastUtil.showInfo(XiaZaiActivity.this, "请开通sd卡读写权限，否则无法正常使用！");
            }
        });

    }

    /**
     * 改变字符串中的数字字体大小
     *
     * @param str
     * @return
     */
    public static SpannableStringBuilder setNumColor(String str) {
        SpannableStringBuilder style = new SpannableStringBuilder(str);
        for (int i = 0; i < str.length(); i++) {
            char a = str.charAt(i);
            if (a >= '0' && a <= '9') {
                //style.setSpan(new ForegroundColorSpan(Color.RED), i, i + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                style.setSpan(new RelativeSizeSpan(1.3f), i, i + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        return style;
    }

//    public int fileSize(int size) {
//        size = size / 1024 / 1024;
//        return size;
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (clockReceiver != null) {
            unregisterReceiver(clockReceiver);
        }
    }

    public int convertFileSize(int size) {
        size = size / 1024 / 100;
        return size;
    }
}