package com.example.ruifight_3.saolouruifight.service;

import android.annotation.TargetApi;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.ruifight_3.saolouruifight.MyApi;
import com.example.ruifight_3.saolouruifight.R;
import com.example.ruifight_3.saolouruifight.ui.XiaZaiActivity;
import com.example.ruifight_3.saolouruifight.util.DateUtil;
import com.example.ruifight_3.saolouruifight.util.SPUtils;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloader;


/**
 * Created by RuiFight-3 on 2019/4/17.
 * Creator: 张震 (Android  zhangzhen)
 * Describe:
 */
public class DownloaderService extends Service {

    private int downLoadId = 1;//分配的下载进程编号
    Intent intent = new Intent(XiaZaiActivity.CLOCK_ACTION);
    MediaPlayer mMediaPlayer;

    //必须实现的方法
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    //Service被创建时调用
    @Override
    public void onCreate() {
        super.onCreate();
    }

    //Service被启动时调用
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //初始化
        FileDownloader.setup(this);
        String JSON = (String) SPUtils.get(this, "JSESSIONID", "");
        FileDownloader.getImpl().create(MyApi.URL + MyApi.DOWNLOAD).addHeader("cookie", "JSESSIONID=" + JSON)
                .setPath(MyApi.FileLoad + MyApi.DBNAME)
                //强制重新下载，将会忽略检测文件是否健在
                .setForceReDownload(true)
                //设置整个下载过程中FileDownloadListener#progress最大回调次数
                .setCallbackProgressTimes(700)
                //设置下载中刷新下载速度的最小间隔
                .setMinIntervalUpdateSpeed(400)
                .setTag(downLoadId)
                .setListener(new fileDownloadListener())
                .start();
        return super.onStartCommand(intent, flags, startId);
    }

    //Service被销毁时调用
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    public class fileDownloadListener extends FileDownloadListener {
        //等待
        @Override
        protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {
//            up_text.setVisibility(View.VISIBLE);
//            ProgressBar.setProgress(ProgressBar.getProgress());
//            tishi_image.setText(task.getFilename() + getString(R.string.ShaoHou));
//            xiazai_mb_tv.setVisibility(View.VISIBLE);
//            tishi_tv.setVisibility(View.VISIBLE);
//            tishi_tv.setText(getString(R.string.dengdai));
            intent.putExtra("state", 1);
            intent.putExtra("take", task.getFilename());
            sendBroadcast(intent);
            SPUtils.put(DownloaderService.this, SPUtils.get(DownloaderService.this, "welcomeUserName", "") + "_okip", true);
        }

        //下载进度回调
        @Override
        protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
//            tishi_tv.setText(getString(R.string.xaizaiz));
//            tishi_image.setText(task.getFilename() + getString(R.string.XiaZai));
//            Log.e("soFarBytes", soFarBytes + "" + "totalBytes" + totalBytes);
//            ProgressBar.setProgress(convertFileSize(soFarBytes));
//            up_text.setText(setNumColor(String.format("%dKB/s", task.getSpeed())));
//            xiazai_mb_tv.setText(getString(R.string.filesize) + String.format(" %dM", fileSize(soFarBytes)));
            intent.putExtra("state", 2);
            intent.putExtra("take", task.getFilename());
            intent.putExtra("soFarBytes", soFarBytes);
            intent.putExtra("totalBytes", totalBytes);
            intent.putExtra("getSpeed", task.getSpeed());
            sendBroadcast(intent);
        }

        //完成下载
        @TargetApi(Build.VERSION_CODES.N)
        @Override
        protected void completed(BaseDownloadTask task) {
//            imageView.setEnabled(false);
//            up_text.setVisibility(View.GONE);
//            ProgressBar.setMax(task.getSmallFileTotalBytes());
//            ProgressBar.setProgress(task.getSmallFileSoFarBytes());
//            tishi_image.setText(task.getFilename() + getString(R.string.WanCheng));
//            tishi_tv.setVisibility(View.GONE);
            try {
                //清空filedownloader数据库中的所有数据
                FileDownloader.getImpl().clearAllTaskData();
                //判断是否下载完成
                SPUtils.remove(DownloaderService.this, SPUtils.get(DownloaderService.this, "welcomeUserName", "") + "_okip");
                //存入最近下载日期
                SPUtils.put(DownloaderService.this, SPUtils.get(DownloaderService.this, "welcomeUserName", "") + "_Messdata", DateUtil.getCurrentTimeString(DateUtil.FORMAT_DATE_TIME));
                SPUtils.put(DownloaderService.this, SPUtils.get(DownloaderService.this, "welcomeUserName", "") + "_dowtime", DateUtil.getDate());
                mMediaPlayer = MediaPlayer.create(DownloaderService.this, R.raw.xiazai);
                mMediaPlayer.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
            intent.putExtra("state", 3);
            intent.putExtra("getSmallFileTotalBytes", task.getSmallFileTotalBytes());
            intent.putExtra("getSmallFileSoFarBytes", task.getSmallFileSoFarBytes());
            intent.putExtra("getFilename", task.getFilename());
            sendBroadcast(intent);
        }

        //暂停
        @Override
        protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {

        }

        //下载出错
        @Override
        protected void error(BaseDownloadTask task, Throwable e) {
//            Toast.makeText(XiaZaiActivity.this, getString(R.string.eChang), Toast.LENGTH_SHORT).show();
//            up_text.setVisibility(View.GONE);
//            tishi_image.setText(task.getFilename() + getString(R.string.xiaZaiEs));
//            tishi_tv.setVisibility(View.GONE);
            Handler handlerThree = new Handler(Looper.getMainLooper());
            handlerThree.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), getString(R.string.eChang), Toast.LENGTH_SHORT).show();
                }
            });
            SPUtils.remove(DownloaderService.this, SPUtils.get(DownloaderService.this, "welcomeUserName", "") + "_okip");
            intent.putExtra("state", 4);
            intent.putExtra("error", task.getFilename());
            sendBroadcast(intent);
        }

        //已存在相同下载
        @Override
        protected void warn(BaseDownloadTask task) {
//            imageView.setEnabled(false);
//            ToastUtil.showInfo(XiaZaiActivity.this, "下载连接中，请不要重复点击");
            intent.putExtra("state", 5);
            sendBroadcast(intent);
        }
    }
}
