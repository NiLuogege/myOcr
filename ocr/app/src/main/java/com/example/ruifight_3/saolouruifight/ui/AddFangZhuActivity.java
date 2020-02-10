package com.example.ruifight_3.saolouruifight.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.SwitchCompat;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.example.ruifight_3.saolouruifight.MyApi;
import com.example.ruifight_3.saolouruifight.R;
import com.example.ruifight_3.saolouruifight.baseui.BaseActivity;
import com.example.ruifight_3.saolouruifight.db.datamessage.DanYuanMessage;
import com.example.ruifight_3.saolouruifight.db.datamessage.LouMessage;
import com.example.ruifight_3.saolouruifight.db.datamessage.RecordMeanage;
import com.example.ruifight_3.saolouruifight.db.util.FileUtils;
import com.example.ruifight_3.saolouruifight.db.util.bean.RecordBean;
import com.example.ruifight_3.saolouruifight.ui.bean.AddBean;
import com.example.ruifight_3.saolouruifight.ui.bean.SqlFangzhuBean;
import com.example.ruifight_3.saolouruifight.ui.bean.UpdateBean;
import com.example.ruifight_3.saolouruifight.util.DiaLogUtil;
import com.example.ruifight_3.saolouruifight.util.JsonUtils;
import com.example.ruifight_3.saolouruifight.util.NetWorkUtil;
import com.example.ruifight_3.saolouruifight.util.SPUtils;
import com.example.ruifight_3.saolouruifight.util.ToastUtil;
import com.example.ruifight_3.saolouruifight.util.Validator;
import com.example.ruifight_3.saolouruifight.util.uiutil.AndroidBug5497Workaround;
import com.example.ruifight_3.saolouruifight.widget.ClearEditText;
import com.example.ruifight_3.saolouruifight.widget.RightMarkView;
import com.lljjcoder.citypickerview.widget.CityPicker;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.angmarch.views.NiceSpinner;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by RuiFight-3 on 2018/5/11.
 */

public class AddFangZhuActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.lou_name_ed)
    ClearEditText lou_name_ed;
    @BindView(R.id.lou_cardnumber_ed)
    ClearEditText lou_cardnumber_ed;
    @BindView(R.id.lou_phone_ed)
    ClearEditText lou_phone_ed;
    @BindView(R.id.beizhu_ce)
    ClearEditText beizhu_ce;
    @BindView(R.id.nice_spinner)
    NiceSpinner niceSpinner;
    @BindView(R.id.address_nicSpinner)
    NiceSpinner address_nicSpinner;
    @BindView(R.id.commit_im)
    Button commit_im;
    @BindView(R.id.add_gr)
    RadioGroup add_gr;
    @BindView(R.id.login_nex_no_rl)
    RelativeLayout login_nex_no_rl;
    @BindView(R.id.switch_setactivitys_islogin)
    SwitchCompat switch_setactivitys_islogin;
    // 1为 开启  0 为关闭
    private int isLogin = 0;

    List<String> spinnerData = new LinkedList<>(Arrays.asList("请选择", "自住房屋", "出租房屋", "出借房屋", "集体宿舍",
            "闲置房屋", "其他"));
    List<String> address = new LinkedList<>(Arrays.asList("请选择"));
    private String type;
    private RightMarkView rightMarkView;
    private PopupWindow window;
    private String fz_id;
    private String addressname;
    //private String FileLoad = "/data/data/com.example.ruifight_3.saolouruifight/databases/";
    private CityPicker mCP;
    //户主范围 1 户 2 层 3 单元 默认户
    private int hzfw = 1;
    private RecordMeanage recordMeanage;
    private LouMessage louMessage;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                //添加
                case 1:
                    AddBean addBean = (AddBean) msg.obj;

                    if (addBean.isStatus() == false) {
                        ToastUtil.showInfo(AddFangZhuActivity.this, addBean.getMsg() + "");
                    } else {
                        SPUtils.put(AddFangZhuActivity.this, "isResune", true);
                        ToastUtil.showInfo(AddFangZhuActivity.this, "操作成功");
                        finish();
                    }

                    break;
                //获取
                case 2:

                    UpdateBean updateBean = (UpdateBean) msg.obj;

                    if (updateBean != null && updateBean.getData().getFj() != null) {

                        lou_name_ed.setText(updateBean.getData().getFj().getFz_xm());
                        lou_cardnumber_ed.setText(updateBean.getData().getFj().getFz_lxdh() + "");
                        lou_phone_ed.setText(updateBean.getData().getFj().getFz_sfz() != null ? updateBean.getData().getFj().getFz_sfz() : "");
                        niceSpinner.setText(updateBean.getData().getFj().getFz_fwjzlx());
                        if (updateBean.getData().getFj().getFz_hjdz() != null) {
                            address_nicSpinner.setText(updateBean.getData().getFj().getFz_hjdz());
                        }
                        if (updateBean.getData().getFj().getFz_bz() != null) {
                            beizhu_ce.setText(updateBean.getData().getFj().getFz_bz());
                        }
                        if (updateBean.getData().getFj().getIs_login() != null) {
                            switch (updateBean.getData().getFj().getIs_login()) {
                                case "0":
                                    switch_setactivitys_islogin.setChecked(false);
                                    isLogin = 0;
                                    break;
                                case "1":
                                    switch_setactivitys_islogin.setChecked(true);
                                    isLogin = 1;
                                    break;

                                default:
                                    break;
                            }
                        }
                    }
                    break;

                case 3:
                    SqlFangzhuBean sqlFangzhuBean = (SqlFangzhuBean) msg.obj;

                    if (sqlFangzhuBean != null) {
                        lou_name_ed.setText(sqlFangzhuBean.getFz_xm());
                        lou_cardnumber_ed.setText(sqlFangzhuBean.getFz_lxdh() + "");
                        lou_phone_ed.setText(sqlFangzhuBean.getFz_sfs() != null ? sqlFangzhuBean.getFz_sfs() : "");
                        niceSpinner.setText(sqlFangzhuBean.getFz_fwjzlx());
                        if (sqlFangzhuBean.getFz_hjdz() != null) {
                            address_nicSpinner.setText(sqlFangzhuBean.getFz_hjdz());
                        }
                        if (sqlFangzhuBean.getFz_bz() != null) {
                            beizhu_ce.setText(sqlFangzhuBean.getFz_bz());
                        }
                    }

                    break;
                case 4:
                    //没网修改
                    //String name,String idcard,String phone,String type,String bz
                    // Log.e("hu_id","huid"+fz_id);

                    String name = msg.getData().getString("name");
                    String idcard = msg.getData().getString("idcard");
                    String phone = msg.getData().getString("phone");
                    String type = msg.getData().getString("type");
                    String address = msg.getData().getString("address");
                    String bz = msg.getData().getString("bz");

                    if (recordMeanage == null) {
                        recordMeanage = new RecordMeanage(AddFangZhuActivity.this);
                    }
                    RecordBean recordBean = new RecordBean();
                    recordBean.setType(MyApi.UPDATE);
                    recordBean.setStatement("update t_jzw_fangjian set fz_xm=" + "'" + name + "'," + "fz_lxdh=" + "'" + phone + "'," + "fz_sfz=" + "'" + idcard + "'," + "fz_fwjzlx=" + "'" + type + "'," + "fz_bz=" + "'" + bz + "'," + "fz_hjdz=" + "'" + address + "' " + "where hu_id=" + "'" + fz_id + "'");
                    recordBean.setOperateDate("");
                    recordBean.setOperateUser((String) SPUtils.get(AddFangZhuActivity.this, "loginName", ""));
                    recordBean.setTableName("t_jzw_fangjian");
                    recordMeanage.addRecord(recordBean);
                    SPUtils.put(AddFangZhuActivity.this, "isResune", true);
                    SPUtils.put(AddFangZhuActivity.this, "updateIs", "yes");

                    ToastUtil.showInfo(AddFangZhuActivity.this, "操作成功");
                    finish();
                    break;
                case 5:
                    //没网添加房主
                    String name1 = msg.getData().getString("name");
                    String idcard2 = msg.getData().getString("idcard");
                    String phone3 = msg.getData().getString("phone");
                    String type4 = msg.getData().getString("type");
                    String address6 = msg.getData().getString("address");
                    String bz5 = msg.getData().getString("bz");

                    if (recordMeanage == null) {
                        recordMeanage = new RecordMeanage(AddFangZhuActivity.this);
                    }
                    RecordBean recordBea = new RecordBean();
                    recordBea.setType(MyApi.UPDATE);
                    recordBea.setStatement("update t_jzw_fangjian set fz_xm=" + "'" + name1 + "'," + "fz_lxdh=" + "'" + phone3 + "'," + "fz_sfz=" + "'" + idcard2 + "'," + "fz_fwjzlx=" + "'" + type4 + "'," + "fz_bz=" + "'" + bz5 + "'," + "fz_hjdz=" + "'" + address6 + "' " + "where hu_id=" + "'" + fz_id + "'");
                    recordBea.setOperateDate("");
                    recordBea.setOperateUser((String) SPUtils.get(AddFangZhuActivity.this, "loginName", ""));
                    recordBea.setTableName("t_jzw_fangjian");
                    recordMeanage.addRecord(recordBea);
                    //判断是否刷新
                    SPUtils.put(AddFangZhuActivity.this, "isResune", true);
                    SPUtils.put(AddFangZhuActivity.this, "updateIs", "yes");
                    ToastUtil.showInfo(AddFangZhuActivity.this, "操作成功");
                    finish();

                    break;

                default:
                    break;
            }
        }
    };

    @Override
    protected int setLayout() {
        return R.layout.activity_add_fangzhu;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void initView() {
        setSteepStatusBar(true);
        setTooBarBreakImage(R.mipmap.breakimagee);
        AndroidBug5497Workaround.assistActivity(this);
        //房屋类型
        niceSpinner.attachDataSource(spinnerData);
        niceSpinner.setBackgroundResource(R.drawable.nicespinner_bg);
        niceSpinner.setTextColor(R.color.colors_font6);
        //户籍地址
        address_nicSpinner.attachDataSource(address);
        address_nicSpinner.setBackgroundResource(R.drawable.nicespinner_bg);
        address_nicSpinner.setTextColor(R.color.colors_font6);
        commit_im.setOnClickListener(this);
        address_nicSpinner.setOnClickListener(this);
        initRadio();
    }

    @Override
    protected void initData() {

        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        if (type.equals("update")) {  //修改
            fz_id = intent.getStringExtra("fz_id");
            addressname = intent.getStringExtra("addressname");
            setTooBarTitleText("街路巷列表" + ">" + addressname);

            if (!TextUtils.isEmpty(fz_id)) {
                if (NetWorkUtil.isConn(AddFangZhuActivity.this) == false) {

                    if (FileUtils.fileIsExists(MyApi.FileLoad + MyApi.DBNAME) == true) {
                        if (louMessage == null) {
                            louMessage = new LouMessage(AddFangZhuActivity.this);
                        }
                        if (louMessage.tabbleIsExist("t_jzw_jlx") == true) {
                            //没有网 走数据库查询
                            getSql(fz_id);
                        }
                    }

                } else {
                    //房主信息
                    DiaLogUtil.showDiaLog(this, "加载数据中");
                    getHuzhuMess(fz_id);
                }
            }
        } else if (type.equals("add")) {  //添加
            fz_id = intent.getStringExtra("fz_id");
            addressname = intent.getStringExtra("addressname");
            setTooBarTitleText("街路巷列表" + ">" + addressname);
        }
        switch_setactivitys_islogin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    isLogin = 1;
                } else {
                    isLogin = 0;
                }
            }
        });
    }

    public void initRadio() {
        add_gr.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.add_hu_ra:
                        hzfw = 1;
                        break;
                    case R.id.add_ceng_ra:
                        hzfw = 2;
                        break;
                    case R.id.add_danyuan_ra:
                        hzfw = 3;
                        break;

                    default:

                        break;
                }
            }
        });
    }

    /**
     * 查房主信息
     *
     * @param fz_id
     */

    public void getSql(String fz_id) {
        DanYuanMessage danYuanMessage = new DanYuanMessage(AddFangZhuActivity.this);
        SqlFangzhuBean sqlFangzhuBean = new SqlFangzhuBean();
        //房主信息
        sqlFangzhuBean = danYuanMessage.selectHid(fz_id);

        Message msg = new Message();
        msg.what = 3;
        msg.obj = sqlFangzhuBean;
        handler.sendMessage(msg);

    }

    public void mYunCityPicher() {
        String re = address_nicSpinner.getText().toString().trim();
        List<String> result = Arrays.asList(re.split(" "));
        mCP = new CityPicker.Builder(AddFangZhuActivity.this)
                .textSize(18)
                //地址选择
                .title("地址选择")
                .backgroundPop(0xa0000000)
                //文字的颜色
                .titleBackgroundColor("#FFFFFF")
                .titleTextColor("#696969")
                .backgroundPop(0xa0000000)
                .confirTextColor("#7782e3")
                .cancelTextColor("#696969")
                .province(result.size() > 0 ? result.get(0) : "北京市")
                .city(result.size() > 1 ? result.get(1) : "北京市")
                .district(result.size() > 2 ? result.get(2) : "xx区")
                //滑轮文字的颜色
                .textColor(Color.parseColor("#000000"))
                //省滑轮是否循环显示
                .provinceCyclic(true)
                //市滑轮是否循环显示
                .cityCyclic(false)
                //地区（县）滑轮是否循环显示
                .districtCyclic(false)
                //滑轮显示的item个数
                .visibleItemsCount(7)
                //滑轮item间距
                .itemPadding(10)
                .onlyShowProvinceAndCity(false)
                .build();

        //监听
        mCP.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
            @Override
            public void onSelected(String... citySelected) {
                //省
                String province = citySelected[0];
                //市
                String city = citySelected[1];
                //区。县。（两级联动，必须返回空）
                String district = citySelected[2];
                //邮证编码
                String code = citySelected[3];
                address_nicSpinner.setText(province + " " + city + " " + district + " ");
                address_nicSpinner.dismissDropDown();
            }

            @Override
            public void onCancel() {
                address_nicSpinner.dismissDropDown();
            }
        });
    }

    private void initListener() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //户籍地址联动
            case R.id.address_nicSpinner:
                mYunCityPicher();
                mCP.show();

                break;
            case R.id.commit_im:
                String name = lou_name_ed.getText().toString();//姓名
                String phone = lou_phone_ed.getText().toString();//身份证号
                String idcard = lou_cardnumber_ed.getText().toString();//手机号
                String types = niceSpinner.getText().toString().trim();//类型
                String address = address_nicSpinner.getText().toString().trim();//户籍地址
                String beizhu = beizhu_ce.getText().toString();//备注


                if (!TextUtils.isEmpty(phone)) {
                    if (Validator.isLegalId(phone) == false) {
                        ToastUtil.showInfo(AddFangZhuActivity.this, "输入合法身份证号");
                        return;
                    }
                }

                if (Validator.isLegalName(name) == false) {
                    ToastUtil.showInfo(AddFangZhuActivity.this, "输入合法姓名");
                } else if ("".equals(idcard)) {
                    ToastUtil.showInfo(AddFangZhuActivity.this, "输入联系方式");
                } else if (types.equals("请选择")) {
                    ToastUtil.showInfo(AddFangZhuActivity.this, "请完善信息");
                } else if (TextUtils.isEmpty(types)) {
                    ToastUtil.showInfo(AddFangZhuActivity.this, "请完善信息");
                } else if (address.equals("请选择")) {
                    ToastUtil.showInfo(AddFangZhuActivity.this, "选择户籍地址");
                } else {
                    if (type.equals("add")) {
                        if (!TextUtils.isEmpty(fz_id)) {
                            if (NetWorkUtil.isConn(AddFangZhuActivity.this) == false) {

                                if (FileUtils.fileIsExists(MyApi.FileLoad + MyApi.DBNAME) == true) {
                                    if (louMessage == null) {
                                        louMessage = new LouMessage(AddFangZhuActivity.this);
                                    }
                                    if (louMessage.tabbleIsExist("t_jzw_jlx") == true) {
                                        //没有网 走数据库添加
                                        if (hzfw == 1) {
                                            if ("0".equals(MyApi.ISHOMELOGIN)) {
                                                ToastUtil.showInfo(AddFangZhuActivity.this, "房主无权限添加");
                                            } else {
                                                sqAddData(name, phone, idcard, types, address, beizhu);
                                            }
                                        } else {
                                            ToastUtil.showInfo(AddFangZhuActivity.this, "离线房主添加范围只支持本户");
                                        }
                                    }
                                }

                            } else {

                                if ("0".equals(MyApi.ISHOMELOGIN)) {
                                    ToastUtil.showInfo(AddFangZhuActivity.this, "房主无权限添加");
                                } else {
                                    DiaLogUtil.showDiaLog(AddFangZhuActivity.this, "正在添加");
                                    addMessage(name, phone, idcard, types, address, beizhu);
                                }
                            }
                        }

                    } else if (type.equals("update")) {
                        if (!TextUtils.isEmpty(fz_id)) {
                            if (NetWorkUtil.isConn(AddFangZhuActivity.this) == false) {

                                if (FileUtils.fileIsExists(MyApi.FileLoad + MyApi.DBNAME) == true) {
                                    if (louMessage == null) {
                                        louMessage = new LouMessage(AddFangZhuActivity.this);
                                    }
                                    if (louMessage.tabbleIsExist("t_jzw_jlx") == true) {
                                        //没有网 走数据库修改
                                        if (hzfw == 1) {

                                            if ("0".equals(MyApi.ISHOMELOGIN)) {
                                                ToastUtil.showInfo(AddFangZhuActivity.this, "房主无权限修改");
                                            } else {
                                                sqlUpdate(name, phone, idcard, types, address, beizhu);
                                            }
                                        } else {
                                            ToastUtil.showInfo(AddFangZhuActivity.this, "离线房主修改范围只支持本户");
                                        }
                                    }
                                }
                            } else {
                                if ("0".equals(MyApi.ISHOMELOGIN)) {
                                    ToastUtil.showInfo(AddFangZhuActivity.this, "房主无权限修改");
                                } else {
                                    DiaLogUtil.showDiaLog(AddFangZhuActivity.this, "正在修改");
                                    //修改还是走添加接口
                                    addMessage(name, phone, idcard, types, address, beizhu);
                                }
                            }
                        }
                    }
                }
                break;
            default:
                break;
        }
    }

    /**
     * 没网添加房主
     *
     * @param name
     * @param idcard
     * @param phone
     * @param type
     * @param bz
     */
    public void sqAddData(String name, String idcard, String phone, String type, String address, String bz) {

        DanYuanMessage danYuanMessage = new DanYuanMessage(AddFangZhuActivity.this);
        SqlFangzhuBean sqlFangzhuBean = new SqlFangzhuBean();
        sqlFangzhuBean.setFz_xm(name);
        sqlFangzhuBean.setFz_sfs(idcard);
        sqlFangzhuBean.setFz_lxdh(phone);
        sqlFangzhuBean.setFz_fwjzlx(type);
        sqlFangzhuBean.setFz_bz(bz);
        sqlFangzhuBean.setFz_hjdz(address);

        danYuanMessage.updateFangzhu(sqlFangzhuBean, fz_id);


        Message msg = new Message();
        msg.what = 5;
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        bundle.putString("idcard", idcard);
        bundle.putString("phone", phone);
        bundle.putString("type", type);
        bundle.putString("address", address);
        bundle.putString("bz", bz);
        msg.setData(bundle);
        handler.sendMessage(msg);

    }

    /**
     * 本地修改房主信息  小数据不需要异步
     *
     * @param name
     * @param idcard
     * @param phone
     * @param type
     * @param bz
     */
    public void sqlUpdate(String name, String idcard, String phone, String type, String address, String bz) {

        DanYuanMessage danYuanMessage = new DanYuanMessage(AddFangZhuActivity.this);
        SqlFangzhuBean sqlFangzhuBean = new SqlFangzhuBean();
        sqlFangzhuBean.setFz_xm(name);
        sqlFangzhuBean.setFz_sfs(idcard);
        sqlFangzhuBean.setFz_lxdh(phone);
        sqlFangzhuBean.setFz_fwjzlx(type);
        sqlFangzhuBean.setFz_hjdz(address);
        sqlFangzhuBean.setFz_bz(bz);
        danYuanMessage.updateFangzhu(sqlFangzhuBean, fz_id);
        Message msg = new Message();

        msg.what = 4;
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        bundle.putString("idcard", idcard);
        bundle.putString("phone", phone);
        bundle.putString("type", type);
        bundle.putString("address", address);
        bundle.putString("bz", bz);
        msg.setData(bundle);
        handler.sendMessage(msg);
    }


    /**
     * 添加房主信息
     *
     * @param name
     * @param phone
     * @param idcard
     */
    public void addMessage(final String name, final String phone, final String idcard, final String types, final String address, final String beizhu) {
        OkHttpUtils
                .post()
                .url(MyApi.URL + MyApi.ADDFANGZHU)
                .tag(this)
                .addHeader("JSESSIONID", (String) SPUtils.get(AddFangZhuActivity.this, "JSESSIONID", ""))
                //是否允许户主登录
                .addParams("isLogin", String.valueOf(isLogin))
                .addParams("hzfw", String.valueOf(hzfw))
                .addParams("hu_id", fz_id)
                //姓名
                .addParams("fz_xm", name)
                //电话
                .addParams("fz_lxdh", idcard)
                //证件号
                .addParams("fz_sfz", phone != null ? phone : "")
                //房屋类型
                .addParams("fz_fwjzlx", types)
                //户籍地址
                .addParams("fz_hjdz", address)
                //备注
                .addParams("fz_bz", beizhu != null ? beizhu : "")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        DiaLogUtil.dismissDiaLog();
                        ToastUtil.showInfo(AddFangZhuActivity.this, "无法连接服务器");
                    }

                    @Override
                    public void onResponse(String response) {
                        DiaLogUtil.dismissDiaLog();
                        try {
                            AddBean addBean = (AddBean) JsonUtils.stringToObject(response, AddBean.class);
                            if (addBean.isStatus() == true) {
                                //暂时只有选择添加本户的时候 在操作本地数据库
                                if (hzfw == 1) {
                                    if (FileUtils.fileIsExists(MyApi.FileLoad + MyApi.DBNAME) == true) {
                                        if (louMessage == null) {
                                            louMessage = new LouMessage(AddFangZhuActivity.this);
                                        }
                                        if (louMessage.tabbleIsExist("t_jzw_jlx") == true) {
                                            DanYuanMessage danYuanMessage = new DanYuanMessage(AddFangZhuActivity.this);
                                            SqlFangzhuBean sqlFangzhuBean = new SqlFangzhuBean();
                                            sqlFangzhuBean.setFz_xm(name);
                                            sqlFangzhuBean.setFz_sfs(phone);
                                            sqlFangzhuBean.setFz_lxdh(idcard);
                                            sqlFangzhuBean.setFz_fwjzlx(types);
                                            sqlFangzhuBean.setFz_hjdz(address);
                                            sqlFangzhuBean.setFz_bz(beizhu);
                                            danYuanMessage.updateFangzhu(sqlFangzhuBean, fz_id);

                                        }
                                    }
                                }
                            }
                            Message msg = new Message();
                            msg.what = 1;
                            msg.obj = addBean;
                            handler.sendMessage(msg);
                        } catch (Exception e) {
                            e.printStackTrace();
                            setLogin();
                        }
                    }
                });
    }

    /**
     * 获取房主信息
     *
     * @param houseHolderId
     */
    public void getHuzhuMess(String houseHolderId) {
        OkHttpUtils
                .post()
                .url(MyApi.URL + MyApi.UPDATEFANGZHU)
                .tag(this)
                .addParams("hu_id", houseHolderId)
                .build()
                .execute(new StringCallback() {

                    @Override
                    public void onError(Request request, Exception e) {
                        DiaLogUtil.dismissDiaLog();
                        ToastUtil.showInfo(AddFangZhuActivity.this, getString(R.string.wufaData));
                    }

                    @Override
                    public void onResponse(String response) {
                        DiaLogUtil.dismissDiaLog();
                        try {
                            UpdateBean updateBean = (UpdateBean) JsonUtils.stringToObject(response, UpdateBean.class);
                            Message msg = new Message();
                            msg.what = 2;
                            msg.obj = updateBean;
                            handler.sendMessage(msg);
                        } catch (Exception e) {
                            e.printStackTrace();
                            setLogin();
                        }
                    }
                });
    }


    public void showPopWindow() {
        darkenBackground(0.6f);
        View popupView = AddFangZhuActivity.this.getLayoutInflater().inflate(R.layout.popupwindow, null);

        rightMarkView = popupView.findViewById(R.id.activity_right_mark_rmv);
        // 设置开始和结束两种颜色
        rightMarkView.setColor(Color.parseColor("#FF4081"), Color.YELLOW);
        // 设置画笔粗细
        rightMarkView.setStrokeWidth(5f);
        window = new PopupWindow(popupView, 500, 500);
//        // TODO: 2016/5/17 设置动画
        window.setAnimationStyle(R.style.popwin_anim_style);
//        window.setAnimationStyle(R.style.popup_window_anim);
        // TODO: 2016/5/17 设置背景颜色
        window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#F8F8F8")));
        // TODO: 2016/5/17 设置可以获取焦点
        window.setFocusable(true);
        // TODO: 2016/5/17 设置可以触摸弹出框以外的区域
        window.setOutsideTouchable(true);
        // TODO：更新popupwindow的状态
        window.update();
        // TODO: 2016/5/17 以下拉的方式显示，并且可以设置显示的位置
        // window.showAsDropDown(popupView, 0, 20);
        window.showAtLocation(popupView, Gravity.CENTER, 0, 0);

        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                darkenBackground(1f);
            }
        });
    }

    /**
     * 改变背景颜色
     */
    private void darkenBackground(Float bgcolor) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgcolor;

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);

    }

    @Override
    public void onNetChange(int netMobile) {
        if (netMobile == 1) {
            //ToastUtil.showInfo(this, "有网络wifi");
            login_nex_no_rl.setVisibility(View.GONE);

        } else if (netMobile == -1) {
            //ToastUtil.showInfo(this, "没有网络");
            login_nex_no_rl.setVisibility(View.VISIBLE);
            //getSql(fz_id);
        } else if (netMobile == 0) {
            //ToastUtil.showInfo(this, "4G");
            login_nex_no_rl.setVisibility(View.GONE);
        }
    }
}
