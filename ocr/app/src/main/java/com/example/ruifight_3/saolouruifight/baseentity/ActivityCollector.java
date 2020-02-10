package com.example.ruifight_3.saolouruifight.baseentity;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RuiFight-3 on 2018/4/16.
 */

public class ActivityCollector {
    private static List<Activity> activityList = new ArrayList<>();

    public static void addActivity(Activity activity) {
        if (activityList != null) {
            activityList.add(activity);
        }
    }

    public static void removeActivity(Activity activity) {
        if (activityList != null) {
            activityList.remove(activity);
        }
    }

    // 获取栈顶 Activity
    public static Activity getTopActivity() {
        if (activityList.isEmpty()) {
            return null;
        }
        return activityList.get(activityList.size() - 1);
    }
}
