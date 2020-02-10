package com.example.ruifight_3.saolouruifight.baseui;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhy.http.okhttp.OkHttpUtils;

/**
 * Created by RuiFight-3 on 2018/5/12.
 * setUserVisibleHint方法在切换界面时会多次调用，而我们只希望他被调用一次，既第一次进入页面时被调用。
 */

public abstract class LanBaseFragment extends Fragment {

    private boolean isFirstLoad = false;
    private Activity mActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = initView(inflater, container);//让子类实现初始化视图
        initEvent();//初始化事件
        isFirstLoad = true;//视图创建完成，将变量置为true

        if (getUserVisibleHint()) {//如果Fragment可见进行数据加载
            onLazyLoad();
            isFirstLoad = false;
        }
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //取消以Activity.this作为tag的请求
        try {
            OkHttpUtils.getInstance().cancelTag(this);
            //视图销毁将变量置为false
            isFirstLoad = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isFirstLoad && isVisibleToUser) {//视图变为可见并且是第一次加载
            onLazyLoad();
            isFirstLoad = false;
        }

    }

    //数据加载接口，留给子类实现
    public abstract void onLazyLoad();

    //初始化视图接口，子类必须实现
    public abstract View initView(LayoutInflater inflater, @Nullable ViewGroup container);

    //初始化事件接口，留给子类实现
    public abstract void initEvent();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onAttachToContext(context);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            onAttachToContext(activity);
        }
    }

    protected void onAttachToContext(Context context) {
        //do something
        mActivity = (Activity) context;
    }

    //得到可靠地Activity
    public Activity getMyActivity() {
        return mActivity;
    }
}
