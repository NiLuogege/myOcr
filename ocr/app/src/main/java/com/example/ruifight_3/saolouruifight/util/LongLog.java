package com.example.ruifight_3.saolouruifight.util;

import android.util.Log;

/**
 * Created by RuiFight-3 on 2018/5/16.
 */

public class LongLog {

    //可以全局控制是否打印log日志
    private static boolean isPrintLog = true;
    private static int LOG_MAXLENGTH = 2000;

    public static void e(String tagName, String msg) {
        if (isPrintLog) {
            int strLength = msg.length();
            int start = 0;
            int end = LOG_MAXLENGTH;
            for (int i = 0; i < 100; i++) {
                if (strLength > end) {
                    Log.e(tagName + i, msg.substring(start, end));
                    start = end;
                    end = end + LOG_MAXLENGTH;
                } else {
                    Log.e(tagName + i, msg.substring(start, strLength));
                    break;
                }
            }
        }
    }
}
