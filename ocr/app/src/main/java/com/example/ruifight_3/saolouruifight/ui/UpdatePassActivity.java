package com.example.ruifight_3.saolouruifight.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.example.ruifight_3.saolouruifight.MyApi;
import com.example.ruifight_3.saolouruifight.R;
import com.example.ruifight_3.saolouruifight.baseui.BaseActivity;
import com.example.ruifight_3.saolouruifight.ui.bean.UpdatePassBean;
import com.example.ruifight_3.saolouruifight.util.AnimationUtil;
import com.example.ruifight_3.saolouruifight.util.DiaLogUtil;
import com.example.ruifight_3.saolouruifight.util.JsonUtils;
import com.example.ruifight_3.saolouruifight.util.SPUtils;
import com.example.ruifight_3.saolouruifight.util.ToastUtil;
import com.example.ruifight_3.saolouruifight.util.uiutil.AndroidBug5497Workaround;
import com.example.ruifight_3.saolouruifight.widget.ClearEditText;
import com.example.ruifight_3.saolouruifight.widget.Code;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;


/**
 * Created by RuiFight-3 on 2018/4/18.
 * 修改密码
 */

public class UpdatePassActivity extends BaseActivity implements View.OnClickListener {

    private ClearEditText update_password, updates_password, yuan_password;
    private Button complete_bu;
    @BindView(R.id.yanzhengma_ed)
    ClearEditText yanzhengma_ed;
    @BindView(R.id.yanzheng_im)
    ImageView yanzheng_im;
    //产生的验证码
    private String realCode;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 1:
                    UpdatePassBean updatePassBean = (UpdatePassBean) msg.obj;

                    if (updatePassBean.isStatus() == false) {
                        ToastUtil.showInfo(UpdatePassActivity.this, updatePassBean.getMsg() + "");
                    } else {
                        ToastUtil.showInfo(UpdatePassActivity.this, "修改密码成功");
                        finish();
                    }
                    break;

                default:
                    break;
            }
        }
    };


    @Override
    protected int setLayout() {
        return R.layout.activity_update_password;
    }

    @Override
    protected void initView() {
        setSteepStatusBar(true);
        setTooBarBreakImage(R.mipmap.breakimagee);
        setTooBarTitleText("修改密码");
        AndroidBug5497Workaround.assistActivity(this);
//        update_name=findViewById(R.id.update_name);
//        update_name.setEnabled(false);
        update_password = findViewById(R.id.update_password);
        updates_password = findViewById(R.id.updates_password);
        complete_bu = findViewById(R.id.update_button);
        complete_bu.setOnClickListener(this);

        yanzheng_im.setOnClickListener(this);
//        name_tv=findViewById(R.id.name);
        yuan_password = findViewById(R.id.yuan_password);

        AnimationUtil.initDouDong();
        update_password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
                } else {
//  activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                }

            }
        });
    }

    @Override
    protected void initData() {

        Intent intent = getIntent();
        String title = intent.getStringExtra("tile");

        //将验证码用图片的形式显示出来
        yanzheng_im.setImageBitmap(Code.getInstance().createBitmap());
        realCode = Code.getInstance().getCode().toLowerCase();


//        if (title.equals("LoginHomeActivity")){
//            name_tv.setText("首次登录请修改密码");
//        }else {
//            name_tv.setText("修改密码");
//        }
//        if (!TextUtils.isEmpty(SPUtils.get(this, "loginName", "").toString())){
//            update_name.setText(SPUtils.get(this, "loginName", "").toString());
//        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.update_button:

                //String name=update_name.getText().toString();
                //原密码
                String yuanpass = yuan_password.getText().toString().trim();
                //1
                String pass = update_password.getText().toString().trim();
                //2
                String passwor = updates_password.getText().toString().trim();
                //验证码
                String cade = yanzhengma_ed.getText().toString().toLowerCase();

                if (!TextUtils.isEmpty(pass) && !TextUtils.isEmpty(yuanpass)) {

                    if (!TextUtils.isEmpty(passwor)) {

                        if (pass.equals(passwor)) {
                            if (cade.equals(realCode)) {
                                DiaLogUtil.showDiaLog(UpdatePassActivity.this, "正在修改");
                                updatePassWord(yuanpass, passwor);
                            } else {
                                AnimationUtil.setDouDong(yanzhengma_ed);
                                ToastUtil.showInfo(this, "验证码错误");
                            }
                        } else {
                            AnimationUtil.setDouDong(updates_password);
                            AnimationUtil.setDouDong(update_password);
                            ToastUtil.showInfo(this, "两次密码不一致");
                        }
                    } else {
                        AnimationUtil.setDouDong(updates_password);
                        ToastUtil.showInfo(this, "请输入密码");
                    }
                } else {
                    AnimationUtil.setDouDong(update_password);
                    ToastUtil.showInfo(this, "请输入密码");
                }

                break;
            case R.id.yanzheng_im:
                yanzheng_im.setImageBitmap(Code.getInstance().createBitmap());
                realCode = Code.getInstance().getCode().toLowerCase();
                break;
            default:
                break;
        }
    }

    public void updatePassWord(String yuanpass, String passwor) {

        OkHttpUtils
                .post()
                .url(MyApi.URL + MyApi.UPDATEPASSWORD)
                .tag(this)
                .addHeader("JSESSIONID", (String) SPUtils.get(UpdatePassActivity.this, "JSESSIONID", ""))
                .addParams("confirmPasswordMsg1", passwor)
                .addParams("originalPwd1", yuanpass)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        DiaLogUtil.dismissDiaLog();
                        ToastUtil.showInfo(UpdatePassActivity.this, getString(R.string.eChang));
                    }

                    @Override
                    public void onResponse(String response) {
                        DiaLogUtil.dismissDiaLog();
                        try {
                            UpdatePassBean updatePassBean = (UpdatePassBean) JsonUtils.stringToObject(response, UpdatePassBean.class);
                            Message msg = new Message();
                            msg.what = 1;
                            msg.obj = updatePassBean;
                            handler.sendMessage(msg);
                        } catch (Exception e) {
                            e.printStackTrace();
                            setLogin();
                        }
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AnimationUtil.setDongNull();
    }
}
