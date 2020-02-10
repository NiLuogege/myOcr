package com.example.ruifight_3.saolouruifight.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import com.example.ruifight_3.saolouruifight.baseui.BaseActivity;
import com.example.ruifight_3.saolouruifight.util.NetUtil;

/**
 * Created by RuiFight-3 on 2018/6/28.
 *
 * 张振 网络监听接收器
 */

public class NetWorkStateReceiver extends BroadcastReceiver {

    public NetEvevt evevt = BaseActivity.evevt;

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        // 如果相等的话就说明网络状态发生了变化
        if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            int netWorkState = NetUtil.getNetWorkState(context);
            // 接口回调传过去状态的类型
            if (evevt!=null){
                evevt.onNetChange(netWorkState);
            }
        }
    }

    // 自定义接口
    public interface NetEvevt {
        public void onNetChange(int netMobile);
    }
}
