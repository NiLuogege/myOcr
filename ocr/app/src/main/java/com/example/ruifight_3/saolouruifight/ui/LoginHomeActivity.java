package com.example.ruifight_3.saolouruifight.ui;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.ruifight_3.saolouruifight.MyApi;
import com.example.ruifight_3.saolouruifight.R;
import com.example.ruifight_3.saolouruifight.baseui.BaseActivity;
import com.example.ruifight_3.saolouruifight.myinterface.PermissionCallBack;
import com.example.ruifight_3.saolouruifight.ui.bean.LoginBean;
import com.example.ruifight_3.saolouruifight.ui.fragment.NullFragment;
import com.example.ruifight_3.saolouruifight.util.AnimationUtil;
import com.example.ruifight_3.saolouruifight.util.DiaLogUtil;
import com.example.ruifight_3.saolouruifight.util.JsonUtils;
import com.example.ruifight_3.saolouruifight.util.SPUtils;
import com.example.ruifight_3.saolouruifight.util.ToastUtil;
import com.example.ruifight_3.saolouruifight.widget.ClearEditText;
import com.example.ruifight_3.saolouruifight.widget.ViewPagerTriangleIndicator;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by RuiFight-3 on 2018/4/16.
 * <p>
 * login 登录页
 */

public class LoginHomeActivity extends BaseActivity implements View.OnClickListener {

    private ClearEditText login_name;
    private ClearEditText login_password;
    private Button btn_Loginbutton;
    @BindView(R.id.login_nex_no_rl)
    RelativeLayout login_nex_no_rl;
    @BindView(R.id.password_checkbox)
    CheckBox password_checkbox;
    private boolean isyoUT = false;
    @BindView(R.id.vpti_main_tab)
    ViewPagerTriangleIndicator viewPagerTriangleIndicator;
    @BindView(R.id.login_viewpager)
    ViewPager login_viewpager;
    @BindView(R.id.liner)
    LinearLayout liner;
    private List<String> tabnum = new ArrayList<>();
    private List<Fragment> fragments = new ArrayList<>();
    private NullFragment simpleFragmet;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 1:
                    LoginBean loginBean = (LoginBean) msg.obj;
                    if (!loginBean.isStatus()) {
                        AnimationUtil.setDouDong(liner);
                        ToastUtil.showInfo(LoginHomeActivity.this, getString(R.string.tishi));
                    } else {
                        if (ViewPagerTriangleIndicator.mPosition == 0) {
                            if (!"0".equals(loginBean.getMsg().getLevel())) {
                                MyApi.ISHOMELOGIN = loginBean.getMsg().getLevel();
                                setData(loginBean);
                            } else {
                                AnimationUtil.setDouDong(liner);
                                ToastUtil.showInfo(LoginHomeActivity.this, getString(R.string.tishi));
                            }
                        } else {
                            if ("0".equals(loginBean.getMsg().getLevel())) {
                                MyApi.ISHOMELOGIN = loginBean.getMsg().getLevel();
                                setData(loginBean);
                            } else {
                                AnimationUtil.setDouDong(liner);
                                ToastUtil.showInfo(LoginHomeActivity.this, getString(R.string.tishi));
                            }
                        }
                    }

                    break;

                default:
                    break;
            }
        }
    };

    @Override
    protected int setLayout() {
        return R.layout.activity_phone_login;
    }

    @Override
    protected void initView() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        login_name = findViewById(R.id.login_name);
        login_password = findViewById(R.id.login_password);
        btn_Loginbutton = findViewById(R.id.btn_Loginbutton);
        btn_Loginbutton.setOnClickListener(this);
        AnimationUtil.initDouDong();

        login_name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
                } else {
                }

            }
        });
        password_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    isyoUT = isChecked;
                    SPUtils.put(LoginHomeActivity.this, "isChecked_check", isChecked);
                } else {
                    isyoUT = isChecked;
                    SPUtils.put(LoginHomeActivity.this, "isChecked_check", isChecked);
                }
            }
        });
    }

    @Override
    protected void initData() {

        //登录检查权限
        checkPermissions(LoginHomeActivity.this, new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.READ_PHONE_STATE}, new PermissionCallBack() {
            @Override
            public void granted() {
            }

            @Override
            public void denied() {
                ToastUtil.showInfo(LoginHomeActivity.this, "授权失败");
            }
        });


        if (tabnum != null) {
            tabnum.add("流管员/账号");
            tabnum.add("房主/账号");
        }
        //创建Fragment
        if (tabnum != null) {
            if (fragments != null) {
                for (int i = 0; i < tabnum.size(); i++) {
                    simpleFragmet = new NullFragment();
                    fragments.add(simpleFragmet);
                }
            }
        }

        //设置指示器标题
        viewPagerTriangleIndicator.setPageTitle(tabnum, login_name, login_password, password_checkbox);
        viewPagerTriangleIndicator.setViewPagerWithIndicator(login_viewpager);
        //设置适配器
        login_viewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_Loginbutton:
                String name = login_name.getText().toString();
                String pass = login_password.getText().toString();

                if (name.equals("")) {
                    AnimationUtil.setDouDong(liner);
                    ToastUtil.showInfo(this, getString(R.string.login_name_tv));

                } else if (pass.equals("")) {
                    AnimationUtil.setDouDong(liner);
                    ToastUtil.showInfo(this, getString(R.string.login_pass_tv));

                } else {
                    DiaLogUtil.showDiaLog(this, getString(R.string.login_tv));
                    //登录
                    getLoginMess(name, pass);
                }
                break;

            default:
                break;
        }
    }

    /**
     * 登录
     *
     * @param name
     * @param pass
     */
    public void getLoginMess(final String name, String pass) {
        OkHttpUtils
                .post()
                .url(MyApi.URL + MyApi.LOGIN)
                .tag(this)
                .addParams("name", name)
                .addParams("pass", pass)
                .build()
                .execute(new StringCallback() {

                    @Override
                    public void onError(Request request, Exception e) {
                        DiaLogUtil.dismissDiaLog();
                        ToastUtil.showInfo(LoginHomeActivity.this, getString(R.string.eChang));
                    }

                    @Override
                    public void onResponse(String response) {
                        DiaLogUtil.dismissDiaLog();
                        try {
                            LoginBean loginBean = (LoginBean) JsonUtils.stringToObject(response, LoginBean.class);
                            Message msg = new Message();
                            msg.what = 1;
                            msg.obj = loginBean;
                            handler.sendMessage(msg);
                            if (ViewPagerTriangleIndicator.mPosition == 0) {
                                SPUtils.put(LoginHomeActivity.this, "loginName", name);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            ToastUtil.showInfo(LoginHomeActivity.this, getString(R.string.loginE));
                        }
                    }
                });
    }

    public void setData(LoginBean loginBean) {
        if (isyoUT) {
            SPUtils.put(LoginHomeActivity.this, login_name.getText().toString().trim() + "-LoginPassword", login_password.getText().toString());
        }
        SPUtils.put(LoginHomeActivity.this, "JSESSIONID", loginBean.getData());
        //离线识别sdk 过期时间
        SPUtils.put(LoginHomeActivity.this, "SDKtime", loginBean.getMsg().getAppTimeout() != null ? loginBean.getMsg().getAppTimeout() : "null");
        //登录用户名用于赋值数据库名字
        SPUtils.put(LoginHomeActivity.this, "userName", login_name.getText().toString().trim() + ".db");
        //登录用户名用于欢迎页消息 不用用户消息不一样
        SPUtils.put(LoginHomeActivity.this, "welcomeUserName", login_name.getText().toString().trim());
        //数据库文件名
        MyApi.DBNAME = (String) SPUtils.get(LoginHomeActivity.this, "userName", "");
        //apk版本号
        SPUtils.put(LoginHomeActivity.this, "appVersion", loginBean.getMsg().getAppVersion());
        //更新内容
        SPUtils.put(LoginHomeActivity.this, "versionComment", loginBean.getMsg().getVersionComment() != null ? loginBean.getMsg().getVersionComment() : "点击确定更新");
        Intent intent = new Intent(LoginHomeActivity.this, WelcomeActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.stop, R.anim.start);
    }

    @Override
    public void onNetChange(int netMobile) {
        if (netMobile == 1) {
            login_nex_no_rl.setVisibility(View.GONE);
        } else if (netMobile == -1) {
            login_nex_no_rl.setVisibility(View.VISIBLE);
        } else if (netMobile == 0) {
            login_nex_no_rl.setVisibility(View.GONE);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (viewPagerTriangleIndicator != null) {
            viewPagerTriangleIndicator.onDelete();
            viewPagerTriangleIndicator = null;
        }
        if (simpleFragmet != null) {
            simpleFragmet = null;
        }
        AnimationUtil.setDongNull();
    }
}
