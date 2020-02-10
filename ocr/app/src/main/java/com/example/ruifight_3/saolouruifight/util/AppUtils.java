package com.example.ruifight_3.saolouruifight.util;

import android.app.ActivityManager;
import android.content.Context;

import java.util.List;

/**
 * Created by RuiFight-3 on 2019/6/21.
 * Creator: 张震 (Android  zhangzhen)
 * Describe: 常用工具类
 */
public class AppUtils {
    /**
     * 方法描述：判断某一Service是否正在运行
     *
     * @param context 上下文
     * @return true 表示正在运行，false 表示没有运行
     */
    public static boolean isServiceRunning(Context context) {

        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> runningServiceInfos = am.getRunningServices(Integer.MAX_VALUE);
        if (runningServiceInfos.size() <= 0) {
            return false;
        }
        for (ActivityManager.RunningServiceInfo serviceInfo : runningServiceInfos) {
            if (serviceInfo.service.getClassName().equals("com.example.ruifight_3.saolouruifight.service.DownloaderService")) {
                return true;
            }
        }
        return false;
    }
}
