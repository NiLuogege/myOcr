package com.example.ruifight_3.saolouruifight.myinterface;

/**
 * Created by RuiFight-3 on 2019/6/12.
 * Creator: 张震 (Android  zhangzhen)
 * Describe:权限回调接口
 */
public interface PermissionCallBack {
    /**
     * 同意授权
     */
    void granted();

    /**
     * 取消授权
     */
    void denied();
}
