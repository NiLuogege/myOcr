package com.example.ruifight_3.saolouruifight.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.SwitchCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ruifight_3.saolouruifight.MyApi;
import com.example.ruifight_3.saolouruifight.R;
import com.example.ruifight_3.saolouruifight.baseui.BaseActivity;
import com.example.ruifight_3.saolouruifight.db.datamessage.LouMessage;
import com.example.ruifight_3.saolouruifight.db.datamessage.RecordMeanage;
import com.example.ruifight_3.saolouruifight.db.util.FileUtils;
import com.example.ruifight_3.saolouruifight.db.util.bean.RecordBean;
import com.example.ruifight_3.saolouruifight.myinterface.PopClickEvent;
import com.example.ruifight_3.saolouruifight.threadpool.ThreadExecutorService;
import com.example.ruifight_3.saolouruifight.util.DateUtil;
import com.example.ruifight_3.saolouruifight.util.FileCacheUtils;
import com.example.ruifight_3.saolouruifight.util.GlideCacheUtil;
import com.example.ruifight_3.saolouruifight.util.SPUtils;
import com.example.ruifight_3.saolouruifight.util.ToastUtil;
import com.example.ruifight_3.saolouruifight.util.VersionUtil;
import com.example.ruifight_3.saolouruifight.util.uiutil.PopOptionUtil;
import com.example.ruifight_3.saolouruifight.widget.CommomDialog;
import com.example.ruifight_3.saolouruifight.widget.DownloadCircleDialog;
import com.example.zhouwei.library.CustomPopWindow;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloader;
import com.nostra13.universalimageloader.utils.L;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * Created by RuiFight-3 on 2018/9/30.
 */

public class SetActivity extends BaseActivity implements View.OnClickListener {
    //清除离线文件
    @BindView(R.id.recome_huancuisize)
    RelativeLayout recome_huancuisize;
    @BindView(R.id.huancui_sizez_tv)
    TextView huancui_sizez_tv;
    //清除缓存
    @BindView(R.id.recome_huancui)
    RelativeLayout recome_huancui;
    @BindView(R.id.huancui_sizez)
    TextView huancui_sizez;
    @BindView(R.id.updae_item)
    RelativeLayout updae_item;
    @BindView(R.id.updae_text)
    TextView updae_text;
    @BindView(R.id.switch_setactivity)
    SwitchCompat switchCompat;
    @BindView(R.id.switch_setactivitys)
    SwitchCompat switch_setactivitys;
    @BindView(R.id.switch_setactivitys_tianqi)
    SwitchCompat switch_setactivitys_tianqi;//天气开关
    @BindView(R.id.update_re) //检测新版本
            RelativeLayout update_re;
    @BindView(R.id.versionname_tv)
    TextView versionname_tv;
    @BindView(R.id.hongdian_im_set)
    ImageView hongdian_im_set;
    @BindView(R.id.user_tvs)
    TextView user_tvs;
    //设置户籍地址过滤词
    @BindView(R.id.address_guolv)
    RelativeLayout address_guolv;
    @BindView(R.id.dangqian_tv_set)
    TextView dangqian_tv_set;
    private CustomPopWindow mCustomPopWindow;
    private File outCachePath;
    private PopOptionUtil mPop;
    private int downLoadId = 2;//分配的下载进程编号
    private LouMessage louMessage;
    private RecordMeanage recordMeanage;
    private List<RecordBean> list;

    @Override
    protected int setLayout() {
        return R.layout.activity_set;
    }

    @Override
    protected void initView() {
        setSteepStatusBar(true);
        setTooBarBreakImage(R.mipmap.breakimagee);
        setTooBarTitleText("设置");
        mPop = new PopOptionUtil(this);
        recome_huancuisize.setOnClickListener(this);
        recome_huancui.setOnClickListener(this);
        updae_item.setOnClickListener(this);
        address_guolv.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        boolean IsswitchString = (boolean) SPUtils.get(SetActivity.this, "IsswitchString", false);//关闭离线ocr
        boolean IsswitchZiDong = (boolean) SPUtils.get(SetActivity.this, "IsswitchZiDong", false);//开启自动转换
        boolean tianqiIs = (boolean) SPUtils.get(SetActivity.this, "tianqiIs", false);//天气开关
        final int vert = (int) SPUtils.get(SetActivity.this, "appVersion", 0); //版本号
        if (IsswitchString == true) {
            switchCompat.setChecked(true);
        } else {
            switchCompat.setChecked(false);
        }

        if (IsswitchZiDong == true) {
            switch_setactivitys.setChecked(true);
        } else {
            switch_setactivitys.setChecked(false);
        }
        if (tianqiIs == true) {
            switch_setactivitys_tianqi.setChecked(true);
        } else {
            switch_setactivitys_tianqi.setChecked(false);
        }
        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    SPUtils.put(SetActivity.this, "IsswitchString", isChecked);
                } else {
                    SPUtils.put(SetActivity.this, "IsswitchString", isChecked);
                }
            }
        });

        switch_setactivitys.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    SPUtils.put(SetActivity.this, "IsswitchZiDong", isChecked);
                } else {
                    SPUtils.put(SetActivity.this, "IsswitchZiDong", isChecked);
                }
            }
        });
        switch_setactivitys_tianqi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    SPUtils.put(SetActivity.this, "tianqiIs", isChecked);
                } else {
                    SPUtils.put(SetActivity.this, "tianqiIs", isChecked);
                }
            }
        });

        if (vert > VersionUtil.getVersionCode(SetActivity.this)) {
            hongdian_im_set.setVisibility(View.VISIBLE);
        } else {
            hongdian_im_set.setVisibility(View.GONE);
        }

        updae_item.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mPop.show(v);
                return false;
            }
        });

        mPop.setOnPopClickEvent(new PopClickEvent() {
            @Override
            public void onPreClick() {
                mPop.disMiss();
                //上传
                if (upData() == null) {
                    ToastUtil.showInfo(SetActivity.this, "无离线记录");
                } else {
                    Intent intent8 = new Intent(SetActivity.this, UploadActivity.class);
                    startActivity(intent8);
                    finish();
                }
            }

            @Override
            public void onNextClick() {
                mPop.disMiss();
                //删除
                if (upData() == null) {
                    ToastUtil.showInfo(SetActivity.this, "清除失败，无离线记录");
                } else {
                    //成功后 开始清表  暂时清记录表
                    if (recordMeanage == null) {
                        recordMeanage = new RecordMeanage(SetActivity.this);
                    }
                    recordMeanage.deleTabData();
                    updae_text.setText(upData() == null ? "无记录" : upData() + "" + " 条记录");
                    ToastUtil.showInfo(SetActivity.this, "清除成功");
                }
            }
        });

        update_re.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                if (vert > VersionUtil.getVersionCode(SetActivity.this)) {
                    DownLoadApk();
                } else {
                    ToastUtil.showInfo(SetActivity.this, getString(R.string.update_set) + " " + VersionUtil.getVersionCode(SetActivity.this) + " " + VersionUtil.getVersionName(SetActivity.this));
                }
            }
        });

        /**
         * 外部Cache路径：/mnt/sdcard/android/data/com.xxx.xxx/cache 一般存储缓存数据（注：通过getExternalCacheDir()获取）
         *  外部File路径：/mnt/sdcard/android/data/com.xxx.xxx/files 存储长时间存在的数据
         （注：通过getExternalFilesDir(String type)获取， type为特定类型，可以是以下任何一种
         Environment.DIRECTORY_MUSIC,
         Environment.DIRECTORY_PODCASTS,
         */
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (!MyApi.DBNAME.equals("")) {
                    outCachePath = SetActivity.this.getDatabasePath(MyApi.FileLoad);
                    //一般存放临时缓存数据
                    //outFilePath = SetActivity.this.getExternalCacheDir();
                    try {
                        huancui_sizez_tv.setText(FileCacheUtils.getCacheSize(outCachePath));//数据库文件
                        huancui_sizez.setText(GlideCacheUtil.getInstance().getCacheSize(SetActivity.this));//缓存
                        updae_text.setText(upData() == null ? "无记录" : upData() + "" + " 条记录");
                        versionname_tv.setText(VersionUtil.getVersionCode(SetActivity.this) + " " + VersionUtil.getVersionName(SetActivity.this));
                        user_tvs.setText(SPUtils.get(SetActivity.this, "welcomeUserName", "") != null ? (String) SPUtils.get(SetActivity.this, "welcomeUserName", "") : "无法获取");
                        String add = (String) SPUtils.get(SetActivity.this, SPUtils.get(SetActivity.this, "welcomeUserName", "") + "_addressguolv", "");
                        if (add.equals("")) {
                            dangqian_tv_set.setText("未设置");
                        } else {
                            dangqian_tv_set.setText((String) SPUtils.get(SetActivity.this, SPUtils.get(SetActivity.this, "welcomeUserName", "") + "_addressguolv", ""));
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        };
        ThreadExecutorService.newCachedThreadPool(1).execute(runnable);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //清除离线文件
            case R.id.recome_huancuisize:
                if (!MyApi.DBNAME.equals("")) {
                    //删除数据库文件
                    new CommomDialog(SetActivity.this, R.style.dialog, upData() == null ? "确定要删除离线文件吗？" : "有未上传数据 " + upData() + " 条, 确定要删除吗？", new CommomDialog.OnCloseListener() {
                        @Override
                        public void onClick(Dialog dialog, boolean confirm) {
                            if (confirm) {
                                dialog.dismiss();
                                if (!"0".equals(MyApi.ISHOMELOGIN)) {
                                    if (FileUtils.fileIsExists(MyApi.FileLoad + MyApi.DBNAME)) {
                                        FileCacheUtils.cleanDatabaseByName(SetActivity.this, MyApi.DBNAME);
                                        SPUtils.put(SetActivity.this, SPUtils.get(SetActivity.this, "welcomeUserName", "") + "_deleteData", DateUtil.getDate());
                                        try {
                                            outCachePath = SetActivity.this.getDatabasePath(MyApi.FileLoad);
                                            huancui_sizez_tv.setText(FileCacheUtils.getCacheSize(outCachePath));
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    } else {
                                        ToastUtil.showInfo(SetActivity.this, "没有下载离线文件，无需清除");
                                    }
                                } else {
                                    ToastUtil.showInfo(SetActivity.this, "房主无权限删除");
                                }
                            }
                        }
                    }).setTitle("提示").setPositiveButton("删除").setNegativeButton("取消").show();
                }
                break;
            //清除缓存
            case R.id.recome_huancui:
                //清除临时缓存
                FileCacheUtils.cleanExternalCache(this);
                //outFilePath = SetActivity.this.getExternalCacheDir();
                if (!huancui_sizez.getText().toString().trim().equals("0.00B")) {
                    GlideCacheUtil.getInstance().clearImageAllCache(SetActivity.this);
                    try {
                        huancui_sizez.setText("0.00B");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                break;
            case R.id.updae_item:
                ToastUtil.showInfo(this, this.upData() == null ? getString(R.string.noupdate) : this.getString(R.string.update));
                break;
            case R.id.address_guolv:
                ShouPopWindow();
                break;
            default:
                break;
        }
    }

    public void ShouPopWindow() {
        View contentView = LayoutInflater.from(this).inflate(R.layout.item_set_pop_guolv, null);
        //处理popWindow 显示内容
        handleLogics(contentView);
        //创建并显示popWindow
        mCustomPopWindow = new CustomPopWindow.PopupWindowBuilder(this)
                .setView(contentView)
                .setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED)
                .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
                .create()
                .showAtLocation(contentView, Gravity.CENTER, 0, 0);

    }

    public void handleLogics(View view) {
        TextView textView = view.findViewById(R.id.guanbi_tv_count_set);
        final EditText editText = view.findViewById(R.id.et_danyuan_ceng_count_set);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCustomPopWindow.dissmiss();
            }
        });

        Button button = view.findViewById(R.id.delete_button_count_set);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("".equals(editText.getText().toString().trim())) {
                    ToastUtil.showInfo(SetActivity.this, "过滤地址不能为空");
                } else {
                    mCustomPopWindow.dissmiss();
                    SPUtils.put(SetActivity.this, SPUtils.get(SetActivity.this, "welcomeUserName", "") + "_addressguolv", editText.getText().toString().trim());

                    dangqian_tv_set.setText((String) SPUtils.get(SetActivity.this, SPUtils.get(SetActivity.this, "welcomeUserName", "") + "_addressguolv", ""));
                }
            }
        });
    }


    /**
     * 如果有操作记录提示用户上传
     */
    public Long upData() {
        Long coun = null;
        if (FileUtils.fileIsExists(MyApi.FileLoad + MyApi.DBNAME)) {
            if (louMessage == null) {
                louMessage = new LouMessage(SetActivity.this);
            }
            if (louMessage.tabbleIsExist("t_jzw_jlx")) {
                if (recordMeanage == null) {
                    recordMeanage = new RecordMeanage(SetActivity.this);
                }
                //查询记录表
                if (list == null) {
                    list = new ArrayList<>();
                }
                list = recordMeanage.selectRecord();
                if (list != null && list.size() > 0) {
                    //查询记录表条数
                    Long count = recordMeanage.recordData();
                    coun = count;
                }
            }
        }
        return coun;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 下载 APK
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void DownLoadApk() {

        final DownloadCircleDialog downloadCircleDialog = new DownloadCircleDialog(SetActivity.this);
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
                        downloadCircleDialog.setMsg(String.format("%dKB/s  ", task.getSpeed()) + FileCacheUtils.getFormatSize(soFarBytes) + "下载中..");

                        //FileCacheUtils.getFormatSize()    String.format(" %dM", fileSize(soFarBytes))
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
                        ToastUtil.showInfo(SetActivity.this, "下载出错");
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
                L.i("8.0手机已经拥有安装未知来源应用的权限，直接安装！");
                installApk(SetActivity.this, MyApi.APLPATH);
            } else {
                new CommomDialog(SetActivity.this, R.style.dialog, "安装应用需要打开安装未知来源应用权限，请去设置中开启权限\"", new CommomDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm) {
                        if (confirm) {
                            Uri packageUri = Uri.parse("package:" + VersionUtil.getAppProcessName(SetActivity.this));
                            Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, packageUri);
                            startActivityForResult(intent, 5);
                            dialog.dismiss();
                        }
                    }
                }).setTitle("提示").show();
            }
        } else {
            installApk(SetActivity.this, MyApi.APLPATH);
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
            Uri apkUri = FileProvider.getUriForFile(context, VersionUtil.getAppProcessName(SetActivity.this) + ".fileProvider", file);
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
                installApk(SetActivity.this, MyApi.APLPATH);
            } else {
                ToastUtil.showInfo(SetActivity.this, "未成功授权");
            }
        }
    }
}
