package com.example.ruifight_3.saolouruifight.baseui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.ruifight_3.saolouruifight.R;
import com.example.ruifight_3.saolouruifight.baseentity.ActivityCollector;
import com.example.ruifight_3.saolouruifight.myinterface.PermissionCallBack;
import com.example.ruifight_3.saolouruifight.receiver.NetWorkStateReceiver;
import com.example.ruifight_3.saolouruifight.receiver.NetWorkStateReceiver.NetEvevt;
import com.example.ruifight_3.saolouruifight.ui.LoginHomeActivity;
import com.example.ruifight_3.saolouruifight.ui.SetActivity;
import com.example.ruifight_3.saolouruifight.util.NetUtil;
import com.example.ruifight_3.saolouruifight.util.PermissionsUtil;
import com.example.ruifight_3.saolouruifight.widget.CommomDialog;
import com.example.ruifight_3.saolouruifight.widget.MarqueTextView;
import com.example.zhouwei.library.CustomPopWindow;
import com.zhy.http.okhttp.OkHttpUtils;

import butterknife.ButterKnife;


/**
 * Created by RuiFight-3 on 2018/4/16.
 */

public abstract class BaseActivity extends AppCompatActivity implements NetEvevt {

    public static NetEvevt evevt;
    /**
     * 网络类型
     */
    private int netMobile;

    /**
     * 是否沉浸状态栏
     **/
    private boolean isSetStatusBar;
    private NetWorkStateReceiver netWorkStateReceiver;
    private CustomPopWindow mCustomPopWindow;
    private static final int REQUEST_EXTERNAL_STORAGE = 1300;
    private PermissionCallBack mRequestPermissionCallBack;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayout());
        //设置Android软键盘的默认不弹出
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        ButterKnife.bind(this);
        evevt = BaseActivity.this;

        initView();
        initData();
        if (isSetStatusBar) {
            steepStatusBar();
        }
        ActivityCollector.addActivity(this);

        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        netWorkStateReceiver = new NetWorkStateReceiver();
        //注册广播接收
        registerReceiver(netWorkStateReceiver, filter);

        inspectNet();

    }

    // 设置布局
    protected abstract int setLayout();

    // 初始化组件
    protected abstract void initView();

    // 初始化数据
    protected abstract void initData();


    /**
     * 初始化时判断有没有网络
     */

    public boolean inspectNet() {
        this.netMobile = NetUtil.getNetWorkState(BaseActivity.this);
        return isNetConnect();
        // if (netMobile == 1) {
        // System.out.println("inspectNet：连接wifi");
        // } else if (netMobile == 0) {
        // System.out.println("inspectNet:连接移动数据");
        // } else if (netMobile == -1) {
        // System.out.println("inspectNet:当前没有网络");
        //
        // }
    }

    /**
     * * 网络变化之后的类型
     *
     * @param netMobile
     */
    @Override
    public void onNetChange(int netMobile) {
        // TODO Auto-generated method stub
        this.netMobile = netMobile;
        isNetConnect();

    }

    /**
     * 判断有无网络 。
     *
     * @return true 有网, false 没有网络.
     */
    public boolean isNetConnect() {
        if (netMobile == 1) {
            return true;
        } else if (netMobile == 0) {
            return true;
        } else if (netMobile == -1) {
            return false;

        }
        return false;
    }

    /**
     * [沉浸状态栏]
     */
    private void steepStatusBar() {
        if (Build.VERSION.SDK_INT >=
                Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            // 透明导航栏
//            getWindow().addFlags(
//                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    /**
     * [是否设置沉浸状态栏]
     *
     * @param isSetStatusBar
     */
    public void setSteepStatusBar(boolean isSetStatusBar) {
        this.isSetStatusBar = isSetStatusBar;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            if (netWorkStateReceiver != null) {
                unregisterReceiver(netWorkStateReceiver);
                netWorkStateReceiver = null;
            }
            //可以取消同一个tag的网络请求
            OkHttpUtils.getInstance().cancelTag(this);
            ActivityCollector.removeActivity(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置左返回按键
     *
     * @param image
     */
    public void setTooBarBreakImage(int image) {
        ImageView imageView = findViewById(R.id.base_toobar_lift_break_im);
        imageView.setImageResource(image);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * 设置右按键
     *
     * @param is
     */
    public void setTooBarRightIma(boolean is) {
        if (is) {
            Button button = findViewById(R.id.base_toobar_right_button);
            button.setVisibility(View.VISIBLE);
        }
    }


    public void setTooBarTitleText(final String title) {
        MarqueTextView textView = findViewById(R.id.base_layout_toobar_title_tv);
        textView.setText(title);
//        textView.setSelected(true);
        textView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ShouPopWindow(v, title);
                return true;
            }
        });
    }

    public void ShouPopWindow(View view, String title) {
        View contentView = LayoutInflater.from(this).inflate(R.layout.item_text_layout, null);
        //处理popWindow 显示内容
        handleLogic(contentView, title);
        //创建并显示popWindow
        mCustomPopWindow = new CustomPopWindow.PopupWindowBuilder(this)
                .setView(contentView)
                .setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED)
                .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
                .create()
                .showAsDropDown(view, 0, 10);//显示PopupWindow
    }

    public void handleLogic(View contentView, String title) {
        TextView textView = contentView.findViewById(R.id.loyout_tv);
        textView.setText(title);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCustomPopWindow.dissmiss();
            }
        });
    }


    public void setToolBarTitle(String title) {
        MarqueTextView textView = findViewById(R.id.base_toobar_content_tv);
        textView.setText(title);
//        textView.setSelected(true);
    }

    //weActivity设置天气title 隐藏
    public void setWeTile(String title) {
        TextView textView = findViewById(R.id.title_tv);
        textView.setText(title);
    }

    //weActivity 欢迎页 设置按钮
    public void setTooBarRightImage(int image) {
        ImageView imageView = findViewById(R.id.base_toobar_right_im);
        imageView.setImageResource(image);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BaseActivity.this, SetActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.stop, R.anim.start);
            }
        });
    }

    public void setInvisible(int s) {
        View view = findViewById(R.id.view);
        if (s == 1) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }

    /**
     * 发起权限请求
     *
     * @param context
     * @param permissions
     * @param callback
     */
    public void checkPermissions(final Context context, final String[] permissions,
                                 PermissionCallBack callback) {


        this.mRequestPermissionCallBack = callback;
        //如果所有权限都已授权，则直接返回授权成功,只要有一项未授权，则发起权限请求
        boolean isAllGranted = true;
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_DENIED) {
                isAllGranted = false;
                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, permission)) {
                    //TODO Auto-generated method stub
                    ActivityCompat.requestPermissions(((Activity) context), permissions, REQUEST_EXTERNAL_STORAGE);
                } else {
                    ActivityCompat.requestPermissions(((Activity) context), permissions, REQUEST_EXTERNAL_STORAGE);
                }
                break;
            }
        }
        if (isAllGranted) {
            mRequestPermissionCallBack.granted();
            return;
        }

    }

    /**
     * 权限请求结果回调
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean hasAllGranted = true;

        switch (requestCode) {
            case REQUEST_EXTERNAL_STORAGE: {
                for (int i = 0; i < grantResults.length; ++i) {
                    if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        hasAllGranted = false;
                        //在用户已经拒绝授权的情况下，如果shouldShowRequestPermissionRationale返回false则
                        // 可以推断出用户选择了“不在提示”选项，在这种情况下需要引导用户至设置页手动授权
                        if (!ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[i])) {

                            new CommomDialog(BaseActivity.this, R.style.dialog, "您拒绝过此权限:" + "\n" + PermissionsUtil.reString(permissions) +
                                    "需要到设置页面手动授权", new CommomDialog.OnCloseListener() {
                                @Override
                                public void onClick(Dialog dialog, boolean confirm) {
                                    if (confirm) {
                                        dialog.dismiss();
                                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                        Uri uri = Uri.fromParts("package", getApplicationContext().getPackageName(), null);
                                        intent.setData(uri);
                                        startActivity(intent);
                                    } else {
                                        dialog.dismiss();
                                        mRequestPermissionCallBack.denied();
                                    }
                                }
                            }).setTitle("提示").setPositiveButton("去授权").setNegativeButton("取消").show();

                        } else {
                            //用户拒绝权限请求，但未选中“不再提示”选项
                            mRequestPermissionCallBack.denied();
                        }
                        break;
                    }
                }
                if (hasAllGranted) {
                    mRequestPermissionCallBack.granted();
                }
            }
            default:
                break;
        }
    }


    /**
     * 弹出软键盘的时候  为了让用户按任意处关闭软键盘
     *
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideKeyboard(v, ev)) {
                InputMethodManager imm =
                        (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS
                );
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 根据 EditText 所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘
     */
    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            return !(event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom);
        }
        return false;
    }

    /**
     * 崩溃跳转登录页
     */
    public void setLogin() {
        Intent intent = new Intent(BaseActivity.this, LoginHomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);//这样Task中只剩LoginHomeActivity
        startActivity(intent);
        overridePendingTransition(R.anim.stop, R.anim.start);
    }

    /**
     * Android字体不跟随系统变化
     *
     * @return
     */
    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        if (res.getConfiguration().fontScale != 1) {
            Configuration newConfig = new Configuration();
            newConfig.setToDefaults();
            res.updateConfiguration(newConfig, res.getDisplayMetrics());
        }
        return res;
    }
}
