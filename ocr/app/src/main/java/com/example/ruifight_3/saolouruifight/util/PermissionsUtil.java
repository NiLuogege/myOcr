package com.example.ruifight_3.saolouruifight.util;

/**
 * Created by RuiFight-3 on 2019/6/12.
 * Creator: 张震 (Android  zhangzhen)
 * Describe:
 */
public class PermissionsUtil {

    public static StringBuilder reString(String[] strings) {
        StringBuilder permissionName = new StringBuilder();
        for (String s : strings) {
            if (s.equals("android.permission.WRITE_EXTERNAL_STORAGE")) {
                permissionName = permissionName.append("读写手机文件" + "\r\n");
            }
            if (s.equals("android.permission.ACCESS_COARSE_LOCATION")) {
                permissionName = permissionName.append("获取位置权限" + "\r\n");
            }
            if (s.equals("android.permission.READ_PHONE_STATE")) {
                permissionName = permissionName.append("获取手机信息" + "\r\n");
            }
            if (s.equals("android.permission.CAMERA")) {
                permissionName = permissionName.append("相机权限" + "\r\n");
            }

        }
        return permissionName;
    }
}
