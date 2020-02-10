package com.example.ruifight_3.saolouruifight.ui;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ruifight_3.saolouruifight.MyApi;
import com.example.ruifight_3.saolouruifight.R;
import com.example.ruifight_3.saolouruifight.baseui.BaseActivity;
import com.example.ruifight_3.saolouruifight.db.datamessage.LouMessage;
import com.example.ruifight_3.saolouruifight.db.datamessage.RecordMeanage;
import com.example.ruifight_3.saolouruifight.db.datamessage.ZhuHuMeanage;
import com.example.ruifight_3.saolouruifight.db.util.FileUtils;
import com.example.ruifight_3.saolouruifight.db.util.bean.RecordBean;
import com.example.ruifight_3.saolouruifight.db.util.bean.t_jzw_fangjian;
import com.example.ruifight_3.saolouruifight.myinterface.PermissionCallBack;
import com.example.ruifight_3.saolouruifight.ui.adapter.ImagePickerAdapter;
import com.example.ruifight_3.saolouruifight.ui.bean.AddHouseBean;
import com.example.ruifight_3.saolouruifight.ui.bean.AddUserBean;
import com.example.ruifight_3.saolouruifight.ui.bean.GetUserBean;
import com.example.ruifight_3.saolouruifight.ui.bean.IdCardBean;
import com.example.ruifight_3.saolouruifight.ui.bean.Idcard;
import com.example.ruifight_3.saolouruifight.ui.bean.MainBean;
import com.example.ruifight_3.saolouruifight.ui.idcard.CameraActivity;
import com.example.ruifight_3.saolouruifight.util.Base64Utils;
import com.example.ruifight_3.saolouruifight.util.ConvertUtils;
import com.example.ruifight_3.saolouruifight.util.DateUtil;
import com.example.ruifight_3.saolouruifight.util.DiaLogUtil;
import com.example.ruifight_3.saolouruifight.util.ImageUtil;
import com.example.ruifight_3.saolouruifight.util.JsonUtils;
import com.example.ruifight_3.saolouruifight.util.NetWorkUtil;
import com.example.ruifight_3.saolouruifight.util.SPUtils;
import com.example.ruifight_3.saolouruifight.util.ToastUtil;
import com.example.ruifight_3.saolouruifight.util.Validator;
import com.example.ruifight_3.saolouruifight.util.uiutil.AndroidBug5497Workaround;
import com.example.ruifight_3.saolouruifight.widget.ClearEditText;
import com.example.ruifight_3.saolouruifight.widget.SelectDialog;
import com.example.ruifight_3.saolouruifight.widget.XRadioGroup;
import com.github.library.BaseRecyclerAdapter;
import com.github.library.BaseViewHolder;
import com.github.library.listener.OnRecyclerItemClickListener;
import com.linchaolong.android.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.ui.ImagePreviewDelActivity;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.squareup.okhttp.Request;
import com.yunmai.cc.idcard.vo.IdCardInfo;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.angmarch.views.NiceSpinner;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;


/**
 * Created by RuiFight-3 on 2018/5/11.
 */

public class AddUserActivity extends BaseActivity implements View.OnClickListener, ImagePickerAdapter.OnRecyclerViewItemClickListener {

    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_SELECTS = 107;
    @BindView(R.id.add_image)
    ImageView idCard_im;
    @BindView(R.id.touxiang_im)
    ImageView touxiang_im;

    @BindView(R.id.ed_name)//姓名
            ClearEditText ed_name;

    @BindView(R.id.radiogroup1) //性别RadioGroup
            RadioGroup radiogroup1;

    @BindView(R.id.item0_radiobutton_nan)//男
            RadioButton item0_radiobutton_nan;

    @BindView(R.id.item0_radiobutton_nv)// 女
            RadioButton item0_radiobutton_nv;

    @BindView(R.id.nice_spinner_minzu)//民族 选择
            NiceSpinner nice_spinner_minzu;

    @BindView(R.id.idcard_ed)//身份证号
            ClearEditText idcard_ed;

    @BindView(R.id.address_ct)//住址
            ClearEditText address_ct;

    @BindView(R.id.phone_cet)//手机号
            ClearEditText phone_cet;

    @BindView(R.id.fuwu_changsuo)//现服务场所
            ClearEditText fuwu_changsuo;


    //==============================================人员性质
    private String RENYUANXINGZHI = "流动人口";
    @BindView(R.id.xingzhi_gr)
    XRadioGroup xingzhi_gr;
    @BindView(R.id.liudong_ren) //流动人口
            RadioButton liudong_ren;
    @BindView(R.id.huji_ren) //户籍人口
            RadioButton huji_ren;
    @BindView(R.id.fenli_ren) //人口分离
            RadioButton fenli_ren;

    //=============================文化程度
    private String WENHUA_CHENGDU = "初中";
    @BindView(R.id.wenhua_radio)//文化RadioGroup
            XRadioGroup wenhua_radio;
    @BindView(R.id.shushi_radiobutton) //硕士
            RadioButton shushi_radiobutton;
    @BindView(R.id.daxue_radiobutton) //大学
            RadioButton daxue_radiobutton;
    @BindView(R.id.dazhuan_radiobutton) //大专
            RadioButton dazhuan_radiobutton;
    @BindView(R.id.jixiao_radiobutton) //技校
            RadioButton jixiao_radiobutton;
    @BindView(R.id.gaozhong_radiobutton) //高中
            RadioButton gaozhong_radiobutton;
    @BindView(R.id.chuzhong_radiobutton)//初中
            RadioButton chuzhong_radiobutton;
    @BindView(R.id.xiaooxue_radiobutton)//小学
            RadioButton xiaooxue_radiobutton;
    @BindView(R.id.wenmang_radiobutton) //文盲或半文盲
            RadioButton wenmang_radiobutton;
    @BindView(R.id.zhongzhuan_ra)
    RadioButton zhongzhuan_ra; //中专
    //=============================婚姻状况
    private String HUNYIN_ZHUANGKUANG = "未婚";
    @BindView(R.id.hunyin_ra)
    XRadioGroup hunyin_ra;
    @BindView(R.id.weihun_radiobutton)//未婚
            RadioButton weihun_radiobutton;
    @BindView(R.id.sango_radiobutton)//丧偶
            RadioButton sango_radiobutton;
    @BindView(R.id.lihun_radiobutton)//离婚
            RadioButton lihun_radiobutton;
    @BindView(R.id.qita_radiobutton) //其他
            RadioButton qita_radiobutton;
    @BindView(R.id.peio_radiobutton) //有配偶
            RadioButton peio_radiobutton;

    //=============================户籍类型
    private String HUJI_LEIXING = "农业";
    @BindView(R.id.hunjie_ra)
    XRadioGroup hunjie_ra;
    @BindView(R.id.nongye_radiobutton)//农业
            RadioButton nongye_radiobutton;
    @BindView(R.id.feinongye_radiobutton)//非农业
            RadioButton feinongye_radiobutton;
    @BindView(R.id.wuhukou_radiobutton)//无户口
            RadioButton wuhukou_radiobutton;


    //=============================来京原因
    private String LAIJING_YUANYIN = "务工";
    @BindView(R.id.laijing_ra)
    XRadioGroup laijing_ra;
    @BindView(R.id.wugong_radiobutton)//务工
            RadioButton wugong_radiobutton;
    @BindView(R.id.wunong_radiobutton)//务农
            RadioButton wunong_radiobutton;
    @BindView(R.id.jingshang_radiobutton)//经商
            RadioButton jingshang_radiobutton;
    @BindView(R.id.xuexipei_radiobutton)//学习培训
            RadioButton xuexipei_radiobutton;
    @BindView(R.id.suiqian_radiobutton)//随迁
            RadioButton suiqian_radiobutton;
    @BindView(R.id.qitaa_radiobutton)//其他
            RadioButton qitaa_radiobutton;
    @BindView(R.id.hunjia_radiobutton)//婚嫁
            RadioButton hunjia_radiobutton;
    @BindView(R.id.zhibing_radiobutton)//治病疗养
            RadioButton zhibing_radiobutton;


//    @BindView(R.id.nice_spinner_wenhua)//文化程度选择
//            NiceSpinner nice_spinner_wenhua;
//
//    @BindView(R.id.nice_spinner_hunyin)//婚姻选择
//            NiceSpinner nice_spinner_hunyin;
//
//    @BindView(R.id.nice_spinner_huji) //户籍选择
//            NiceSpinner nice_spinner_huji;
//
//    @BindView(R.id.nice_spinner_yuanyin) //来京原因选择
//            NiceSpinner nice_spinner_yuanyin;

    @BindView(R.id.xian_data_ince)//来现地址日期
            NiceSpinner xian_data_ince;

    @BindView(R.id.nice_spinner_riqi) //来京日期选择
            NiceSpinner nice_spinner_riqi;

    @BindView(R.id.beizhu_cet)//备注
            ClearEditText beizhu_cet;

    @BindView(R.id.commit_im)//提交
            Button commit_im;
    @BindView(R.id.add_xianchang_tv)
    TextView add_xianchang_tv;
    @BindView(R.id.add_user_recyclerView)
    RecyclerView add_user_recyclerView;//相册选择
    @BindView(R.id.image_add_re)
    RecyclerView image_add_re;//展示图片隐藏
    @BindView(R.id.re8)
    RelativeLayout re8;//文字
    //来京日期
    @BindView(R.id.laijing_data_line)
    LinearLayout laijing_data_line;
    //来京原因
    @BindView(R.id.li0)
    RelativeLayout li0;
    //人员性质
    @BindView(R.id.lid)
    RelativeLayout lid;

    private ImagePickerAdapter adapter;
    private ArrayList<ImageItem> selImageList; //当前选择的所有图片
    private int maxImgCounts = 4;               //允许选择图片最大数
    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_PREVIEW = 101;
    private StringBuffer buf;
    private StringBuffer Nobuf;
    private PopupWindow popupWindow;
    private ImagePicker imagePicker;


    private String picture = "";
    private String picImage = "";
    private String type;
    private String userId = "";
    private String sax_string = null;

    private String XINGZHISTRING = null;
    List<String> mimzu = new LinkedList<>(Arrays.asList("请选择", "汉族", "壮族", "满族", "回族",
            "苗族", "维吾尔族", "蒙古族", "土家族", "彝族", "藏族", "布依族", "侗族", "瑶族", "朝鲜族", "白族", "哈尼族", "哈萨克族", "黎族",
            "傣族", "畲族", "傈僳族", "仡佬族", "东乡族", "高山族", "拉祜族", "水族", "佤族", "纳西族", "羌族", "土族", "仫佬族", "锡伯族",
            "柯尔克孜族", "达斡尔族", "景颇族", "毛南族", "撒拉族", "塔吉克族", "阿昌族", "普米族", "鄂温克族", "怒族", "京族", "基诺族", "德昂族", "保安族", "俄罗斯族",
            "裕固族", "乌兹别克族", "门巴族", "鄂伦春族", "独龙族", "塔塔尔族", "赫哲族", "珞巴族", "布朗族"));

//    List<String> wenhua = new LinkedList<>(Arrays.asList("请选择", "研究生", "大学", "大专", "中专",
//            "技校", "高中", "初中", "小学", "文盲或半文盲"));
//
//    List<String> hunyin = new LinkedList<>(Arrays.asList("请选择", "未婚", "有配偶", "丧偶", "离婚",
//            "其他"));
//
//    List<String> huji = new LinkedList<>(Arrays.asList("请选择", "非农业", "农业", "无户口"));
//
//    List<String> yuanyin = new LinkedList<>(Arrays.asList("请选择", "务工", "务农", "经商", "学习培训",
//           "治病疗养", "婚嫁", "随迁", "其他"));

    List<String> riqi = new LinkedList<>(Arrays.asList("请选择"));

    List<String> xainData = new LinkedList<>(Arrays.asList("请选择"));
    private List<MainBean.DataBean.JzwListBean> unitsListBeans;
    private BaseRecyclerAdapter baseRecyclerAdapter;
    private String fz_id;//房间id
    private String floorCode;//楼编码
    private String addressname;//title 地址
    private String addressUp; //人员详细地址
    @BindView(R.id.login_nex_no_rl)
    RelativeLayout login_nex_no_rl;
    private int maxImgCount = 1;
    /**
     * 显示隐藏动画
     */
    private TranslateAnimation mShowAction;
    private TranslateAnimation mHiddenAction;
    private LouMessage louMessage;
    private ZhuHuMeanage zhuHuMeanage;
    private RecordMeanage recordMeanag;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 1:
                    //获取到身份证信息 百度
                    IdCardBean idCardBean = (IdCardBean) msg.obj;

                    if (idCardBean.isStatus() == true) {
                        try {
                            touxiang_im.setImageBitmap(Base64Utils.base64ToBitmap(idCardBean.getData().getBase64()));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        picture = idCardBean.getData().getUrl();
                        picImage = idCardBean.getData().getBase64();
                        ed_name.setText(idCardBean.getData().getName() != null ? idCardBean.getData().getName() : "识别失败");
                        nice_spinner_minzu.setText(idCardBean.getData().getNation() != null ? idCardBean.getData().getNation() + "族" : "");
                        idcard_ed.setText(idCardBean.getData().getIdCard() != null ? idCardBean.getData().getIdCard() : "");
                        address_ct.setText(idCardBean.getData().getAddr() != null ? idCardBean.getData().getAddr() : "");

                        switch (idCardBean.getData().getSex() != null ? idCardBean.getData().getSex() : "") {
                            case "男":
                                item0_radiobutton_nan.setChecked(true);
                                break;
                            case "女":
                                item0_radiobutton_nv.setChecked(true);
                                break;
                            default:
                                break;
                        }

                    } else {
                        ToastUtil.showInfo(AddUserActivity.this, idCardBean.getMsg() + "");
                    }

                    break;

                case 2:
                    //修改回显
                    GetUserBean getUserBean = (GetUserBean) msg.obj;

                    if (getUserBean.getData() != null) {
                        ed_name.setText(getUserBean.getData().getXm());
                        nice_spinner_minzu.setText(getUserBean.getData().getMinzu());
                        idcard_ed.setText(getUserBean.getData().getSfz());
                        address_ct.setText(getUserBean.getData().getHj_hjxz() + "");
                        phone_cet.setText(getUserBean.getData().getLxdh());
                        fuwu_changsuo.setText(getUserBean.getData().getLdrk_fwcs() != null ? getUserBean.getData().getLdrk_fwcs() + "" : "");
                        if (getUserBean.getData().getNew_picture() != null) {
                            String str = String.valueOf(getUserBean.getData().getNew_picture());
                            final List<String> result = Arrays.asList(str.split(","));
                            baseRecyclerAdapter = new BaseRecyclerAdapter<String>(AddUserActivity.this, result, R.layout.item_adduser_phone) {
                                @Override
                                protected void convert(BaseViewHolder helper, String s) {
                                    ImageView imageView = helper.getConvertView().findViewById(R.id.iv_img_add);
                                    String url = MyApi.URL + MyApi.IMAGEURL + s;
                                    Glide.with(AddUserActivity.this).load(url)
                                            .crossFade() //设置显示动画，
                                            .into(imageView);

                                }
                            };
                            image_add_re.setAdapter(baseRecyclerAdapter);
                            baseRecyclerAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    if (NetWorkUtil.isConn(AddUserActivity.this) == true) {
                                        if (Build.VERSION.SDK_INT < 21) {
                                            Toast.makeText(AddUserActivity.this, "21+ only, keep out", Toast.LENGTH_SHORT).show();
                                        } else {
                                            if (result.get(position) != null && !result.equals("")) {
                                                //图片url
                                                MyApi.DAIMAGE = result.get(position);
                                                Intent intent = new Intent(AddUserActivity.this, BigPictureActivity.class);
                                                ActivityOptionsCompat options = ActivityOptionsCompat.
                                                        makeSceneTransitionAnimation((Activity) AddUserActivity.this, view, "test");
                                                //2 有网
                                                intent.putExtra("is", "2");
                                                startActivity(intent, options.toBundle());
                                            }
                                        }
                                    }
                                }
                            });
                        } else {
                            add_xianchang_tv.setText(getString(R.string.xianchangNo));
                        }
                        if (buf == null) {
                            buf = new StringBuffer();
                        }
                        buf.append(getUserBean.getData().getNew_picture());
                        if (getUserBean.getData().getRylx() != null) {
                            XINGZHISTRING = getUserBean.getData().getRylx() + "";
                        }
                        switch (getUserBean.getData().getRylx() != null ? getUserBean.getData().getRylx() + "" : "") {
                            case "流动人口":
                                liudong_ren.setChecked(true);
                                break;
                            case "户籍人口":
                                huji_ren.setChecked(true);
                                break;
                            case "人户分离":
                                fenli_ren.setChecked(true);
                                break;
                            default:
                                break;
                        }


                        switch (getUserBean.getData().getLdrk_whcd() != null ? getUserBean.getData().getLdrk_whcd() : "") {
                            case "硕士":
                                shushi_radiobutton.setChecked(true);
                                break;
                            case "大学":
                                daxue_radiobutton.setChecked(true);
                                break;
                            case "大专":
                                dazhuan_radiobutton.setChecked(true);
                                break;
                            case "技校":
                                jixiao_radiobutton.setChecked(true);
                                break;
                            case "高中":
                                gaozhong_radiobutton.setChecked(true);
                                break;
                            case "初中":
                                chuzhong_radiobutton.setChecked(true);
                                break;
                            case "小学":
                                xiaooxue_radiobutton.setChecked(true);
                                break;
                            case "文盲或半文盲":
                                wenmang_radiobutton.setChecked(true);
                                break;
                            case "中专":
                                zhongzhuan_ra.setChecked(true);
                                break;
                            default:
                                break;
                        }
                        switch (getUserBean.getData().getLdrk_hunyin() != null ? getUserBean.getData().getLdrk_hunyin() : "") {
                            case "未婚":
                                weihun_radiobutton.setChecked(true);
                                break;

                            case "丧偶":
                                sango_radiobutton.setChecked(true);
                                break;
                            case "离婚":
                                lihun_radiobutton.setChecked(true);
                                break;
                            case "其他":
                                qita_radiobutton.setChecked(true);
                                break;
                            case "有配偶":
                                peio_radiobutton.setChecked(true);
                                break;

                            default:
                                break;
                        }

                        switch (getUserBean.getData().getLdrk_zzcs() != null ? getUserBean.getData().getLdrk_zzcs() : "") {
                            case "农业":
                                nongye_radiobutton.setChecked(true);
                                break;

                            case "非农业":
                                feinongye_radiobutton.setChecked(true);
                                break;
                            case "无户口":
                                wuhukou_radiobutton.setChecked(true);
                                break;
                            default:
                                break;
                        }

                        switch (getUserBean.getData().getLdrk_ljqcyzk() != null ? getUserBean.getData().getLdrk_ljqcyzk() : "") {
                            case "务工":
                                wugong_radiobutton.setChecked(true);
                                break;
                            case "务农":
                                wunong_radiobutton.setChecked(true);
                                break;
                            case "经商":
                                jingshang_radiobutton.setChecked(true);
                                break;
                            case "学习培训":
                                xuexipei_radiobutton.setChecked(true);
                                break;
                            case "随迁":
                                suiqian_radiobutton.setChecked(true);
                                break;
                            case "其他":
                                qitaa_radiobutton.setChecked(true);
                                break;
                            case "婚嫁":
                                hunjia_radiobutton.setChecked(true);
                                break;
                            case "治病疗养":
                                zhibing_radiobutton.setChecked(true);
                                break;
                            default:
                                break;
                        }

                        if (getUserBean.getData().getPicture() != null) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                                if (AddUserActivity.this instanceof Activity && ((Activity) AddUserActivity.this).isDestroyed()) {
                                    return;
                                } else {
                                    String url = String.valueOf(MyApi.URL + MyApi.IMAGEURL + getUserBean.getData().getPicture());
                                    Glide.with(AddUserActivity.this).load(url)
                                            .placeholder(R.drawable.touxiangid)
                                            .error(R.drawable.touxiangid)
                                            .crossFade() //设置显示动画，
                                            .into(touxiang_im);
                                    picture = getUserBean.getData().getPicture() + "";
                                }
                            }
                            /**
                             * 修改回显应该返回base64   picImage=idCardBean.getData().getBase64();
                             */

                        } else {
                            ImageUtil.displayImage(AddUserActivity.this, R.drawable.touxiangid, touxiang_im);
                        }
                        if ((int) getUserBean.getData().getLdrk_ljrq() != 0) {
                            try {
                                nice_spinner_riqi.setText(DateUtil.longToString(getUserBean.getData().getLdrk_ljrq(), "yyyy-MM-dd"));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        if (getUserBean.getData().getLdrk_lxjzdrq() != null) {

                            long deadline = Long.parseLong(getUserBean.getData().getLdrk_lxjzdrq());
                            try {
                                xian_data_ince.setText(DateUtil.longToString(deadline, "yyyy-MM-dd"));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        if (getUserBean.getData().getBeizhu() != null) {
                            beizhu_cet.setText(getUserBean.getData().getBeizhu());
                        }
                        switch (getUserBean.getData().getXb()) {
                            case "男":
                                item0_radiobutton_nan.setChecked(true);
                                break;
                            case "女":
                                item0_radiobutton_nv.setChecked(true);
                                break;

                            default:
                                break;
                        }

                        //设置不可编辑
                        ed_name.setFocusable(false);
                        item0_radiobutton_nan.setEnabled(false);
                        item0_radiobutton_nv.setEnabled(false);
                        nice_spinner_minzu.setEnabled(false);
                        idcard_ed.setFocusable(false);
                        address_ct.setFocusable(false);

                    }

                    break;
                case 4:
                    /**
                     * 离线添加人员
                     */
                    int renshu_ldrk1 = 0;
                    int renshu_rhfl1 = 0;
                    int renshu_hjrk1 = 0;
                    String name = msg.getData().getString("name");
                    String sax_string = msg.getData().getString("sax_string");
                    String minzu = msg.getData().getString("minzu");
                    String idcard = msg.getData().getString("idcard");
                    String address = msg.getData().getString("address");
                    String phone = msg.getData().getString("phone");
                    String wenhua = msg.getData().getString("wenhua");
                    String hunyin = msg.getData().getString("hunyin");
                    String huji = msg.getData().getString("huji");
                    String yuanyin = msg.getData().getString("yuanyin");
                    String beizhu = msg.getData().getString("beizhu");
                    String picture1 = msg.getData().getString("picture");
                    String riqi2 = msg.getData().getString("riqi");
                    String xainriqi6 = msg.getData().getString("xainriqi");
                    String new_picture7 = msg.getData().getString("new_picture");
                    String xingzhi = msg.getData().getString("rylx");
                    String ldrk_fwcs = msg.getData().getString("ldrk_fwcs");
                    if (zhuHuMeanage == null) {
                        zhuHuMeanage = new ZhuHuMeanage(AddUserActivity.this);
                    }
                    t_jzw_fangjian t_jzw_fangjian = new t_jzw_fangjian();
                    t_jzw_fangjian = zhuHuMeanage.selectFangjian(fz_id);

                    if (t_jzw_fangjian.getRenshu_ldrk() != null) {
                        renshu_ldrk1 = t_jzw_fangjian.getRenshu_ldrk();
                    } else if (t_jzw_fangjian.getRenshu_rhfl() != null) {
                        renshu_rhfl1 = t_jzw_fangjian.getRenshu_rhfl();
                    } else if (t_jzw_fangjian.getRenshu_hjrk() != null) {
                        renshu_hjrk1 = t_jzw_fangjian.getRenshu_hjrk();
                    }

                    if (xingzhi.equals("流动人口")) {
                        if (unitsListBeans == null) {
                            unitsListBeans = new ArrayList<>();
                        } else {
                            unitsListBeans.clear();
                        }
                        unitsListBeans = zhuHuMeanage.selectLiu(floorCode);
                        if (0 < unitsListBeans.size()) {
                            int ldrk_count = unitsListBeans.get(0).getLdrk_count() + 1;
                            zhuHuMeanage.updateLiu(ldrk_count, floorCode);
                        }
                        zhuHuMeanage.updateFngjian(renshu_ldrk1 + 1, fz_id);
                    } else if (xingzhi.equals("人户分离")) {
                        zhuHuMeanage.updateFngjianrhfl(renshu_rhfl1 + 1, fz_id);
                    } else if (xingzhi.equals("户籍人口")) {
                        zhuHuMeanage.updateFngjianhjrk1(renshu_hjrk1 + 1, fz_id);
                    }

                    if (recordMeanag == null) {
                        //插入记录表
                        recordMeanag = new RecordMeanage(AddUserActivity.this);
                    }
                    RecordBean recordBea = new RecordBean();
                    recordBea.setType(MyApi.INSERT);
                    recordBea.setStatement("insert into t_ryxx " + "(" + "xm," + "xb," + "minzu," + "sfz," + "hj_hjxz," +
                            "lxdh," + "jz_fwid," + "jz_jzwbm," + "ldrk_whcd," + "rylx," + "ldrk_hunyin," + "ldrk_zzcs," + "ldrk_ljqcyzk," + "ldrk_ljrq," + "cjsj," + "is_del," + "beizhu," + "ldrk_lxjzdrq," + "dizhi_xz," + "picture," + "new_picture," + "ldrk_fwcs" + ") " + "VALUES " + "(" + "'" + name + "'," + "'" + sax_string + "'," +
                            "'" + minzu + "'," + "'" + idcard + "'," + "'" + address + "'," + "'" + phone + "'," + "'" + fz_id + "'," + "'" + floorCode + "'," + "'" + wenhua + "'," + "'" + xingzhi + "'," + "'" + hunyin + "'," + "'" + huji + "'," + "'" + yuanyin + "'," + "'" + riqi2 + "'," + "'" + DateUtil.getCurrentTimeString(DateUtil.FORMAT_DATE_TIME) + "'," + "'" + "否" + "'," + "'" + beizhu + "'," + "'" + xainriqi6 + "'," + "'" + addressUp + "'," + "'" + picture1 + "'," + "' " + new_picture7 + "', " + "'" + ldrk_fwcs + "'" + ")");
                    recordBea.setOperateDate("");
                    recordBea.setOperateUser((String) SPUtils.get(AddUserActivity.this, "loginName", ""));
                    recordBea.setTableName("t_ryxx");
                    recordMeanag.addRecord(recordBea);

                    //修改人数插入记录表
                    RecordBean recordBea1 = new RecordBean();
                    recordBea1.setType(MyApi.UPDATE);
                    if (xingzhi.equals("流动人口")) {
                        int s = renshu_ldrk1 + 1;
                        recordBea1.setStatement("update t_jzw_fangjian set renshu_ldrk=" + "'" + s + "' " + "where " + "hu_id=" + "'" + fz_id + "'");
                    } else if (xingzhi.equals("人户分离")) {
                        int b = renshu_rhfl1 + 1;
                        recordBea1.setStatement("update t_jzw_fangjian set renshu_rhfl=" + "'" + b + "' " + "where " + "hu_id=" + "'" + fz_id + "'");

                    } else if (xingzhi.equals("户籍人口")) {
                        int f = renshu_hjrk1 + 1;
                        recordBea1.setStatement("update t_jzw_fangjian set renshu_hjrk=" + "'" + f + "' " + "where " + "hu_id=" + "'" + fz_id + "'");
                    }
                    recordBea1.setOperateDate("");
                    recordBea1.setOperateUser((String) SPUtils.get(AddUserActivity.this, "loginName", ""));
                    recordBea1.setTableName("t_jzw_fangjian");
                    recordMeanag.addRecord(recordBea1);

                    SPUtils.put(AddUserActivity.this, "isResune", true);
                    SPUtils.put(AddUserActivity.this, "updateIs", "yes");
                    ToastUtil.showInfo(AddUserActivity.this, "操作成功");
                    finish();
                    break;

                case 6:
                    //没网修改住户 信息回显
                    GetUserBean.DataBean dataBean = (GetUserBean.DataBean) msg.obj;

                    if (dataBean != null) {
                        ed_name.setText(dataBean.getXm());
                        nice_spinner_minzu.setText(dataBean.getMinzu());
                        idcard_ed.setText(dataBean.getSfz());
                        address_ct.setText(dataBean.getHj_hjxz() + "");
                        phone_cet.setText(dataBean.getLxdh());
                        fuwu_changsuo.setText(dataBean.getLdrk_fwcs() != null ? dataBean.getLdrk_fwcs() + "" : "");
                        if (dataBean.getNew_picture() != null) {
                            String str = dataBean.getNew_picture() + "";
                            final List<String> result = Arrays.asList(str.split(","));
                            baseRecyclerAdapter = new BaseRecyclerAdapter<String>(AddUserActivity.this, result, R.layout.item_adduser_phone) {
                                @Override
                                protected void convert(BaseViewHolder helper, String s) {
                                    try {
                                        helper.setImageBitmap(R.id.iv_img_add, Base64Utils.base64ToBitmap(s));
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            };
                            image_add_re.setAdapter(baseRecyclerAdapter);
                            baseRecyclerAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    if (NetWorkUtil.isConn(AddUserActivity.this) == false) {
                                        if (Build.VERSION.SDK_INT < 21) {
                                            Toast.makeText(AddUserActivity.this, "21+ only, keep out", Toast.LENGTH_SHORT).show();
                                        } else {
                                            if (result.get(position) != null && !result.equals("")) {
                                                //base64
                                                MyApi.DAIMAGE = result.get(position);
                                                Intent intent = new Intent(AddUserActivity.this, BigPictureActivity.class);
                                                ActivityOptionsCompat options = ActivityOptionsCompat.
                                                        makeSceneTransitionAnimation((Activity) AddUserActivity.this, view, "test");
                                                //1 没网
                                                intent.putExtra("is", "1");
                                                startActivity(intent, options.toBundle());
                                            }
                                        }
                                    }
                                }
                            });
                        } else {
                            add_xianchang_tv.setText(getString(R.string.xianchangNo));
                        }
                        if (Nobuf == null) {
                            Nobuf = new StringBuffer();
                        }
                        Nobuf.append(dataBean.getNew_picture());
                        if (dataBean.getRylx() != null) {
                            XINGZHISTRING = dataBean.getRylx() + "";
                        }
                        switch (dataBean.getRylx() != null ? dataBean.getRylx() + "" : "") {
                            case "流动人口":
                                liudong_ren.setChecked(true);
                                break;
                            case "户籍人口":
                                huji_ren.setChecked(true);
                                break;
                            case "人户分离":
                                fenli_ren.setChecked(true);
                                break;
                            default:
                                break;
                        }

                        switch (dataBean.getLdrk_whcd() != null ? dataBean.getLdrk_whcd() : "") {
                            case "硕士":
                                shushi_radiobutton.setChecked(true);
                                break;
                            case "大学":
                                daxue_radiobutton.setChecked(true);
                                break;
                            case "大专":
                                dazhuan_radiobutton.setChecked(true);
                                break;
                            case "技校":
                                jixiao_radiobutton.setChecked(true);
                                break;
                            case "高中":
                                gaozhong_radiobutton.setChecked(true);
                                break;
                            case "初中":
                                chuzhong_radiobutton.setChecked(true);
                                break;
                            case "小学":
                                xiaooxue_radiobutton.setChecked(true);
                                break;
                            case "文盲或半文盲":
                                wenmang_radiobutton.setChecked(true);
                                break;
                            case "中专":
                                zhongzhuan_ra.setChecked(true);
                                break;
                            default:
                                break;
                        }

                        switch (dataBean.getLdrk_hunyin() != null ? dataBean.getLdrk_hunyin() : "") {
                            case "未婚":
                                weihun_radiobutton.setChecked(true);
                                break;

                            case "丧偶":
                                sango_radiobutton.setChecked(true);
                                break;
                            case "离婚":
                                lihun_radiobutton.setChecked(true);
                                break;
                            case "其他":
                                qita_radiobutton.setChecked(true);
                                break;
                            case "有配偶":
                                peio_radiobutton.setChecked(true);
                                break;

                            default:
                                break;
                        }

                        switch (dataBean.getLdrk_zzcs() != null ? dataBean.getLdrk_zzcs() : "") {
                            case "农业":
                                nongye_radiobutton.setChecked(true);
                                break;

                            case "非农业":
                                feinongye_radiobutton.setChecked(true);
                                break;
                            case "无户口":
                                wuhukou_radiobutton.setChecked(true);
                                break;
                            default:
                                break;
                        }

                        switch (dataBean.getLdrk_ljqcyzk() != null ? dataBean.getLdrk_ljqcyzk() : "") {
                            case "务工":
                                wugong_radiobutton.setChecked(true);
                                break;
                            case "务农":
                                wunong_radiobutton.setChecked(true);
                                break;
                            case "经商":
                                jingshang_radiobutton.setChecked(true);
                                break;
                            case "学习培训":
                                xuexipei_radiobutton.setChecked(true);
                                break;
                            case "随迁":
                                suiqian_radiobutton.setChecked(true);
                                break;
                            case "其他":
                                qitaa_radiobutton.setChecked(true);
                                break;
                            case "婚嫁":
                                hunjia_radiobutton.setChecked(true);
                                break;
                            case "治病疗养":
                                zhibing_radiobutton.setChecked(true);
                                break;
                            default:
                                break;
                        }
                        if (dataBean.getPicture() != null && !dataBean.getPicture().equals("")) {
                            if (NetWorkUtil.isConn(AddUserActivity.this) == false) {
                                try {
                                    touxiang_im.setImageBitmap(Base64Utils.base64ToBitmap(dataBean.getPicture() + ""));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                picture = dataBean.getPicture() + "";
                            } else {
                                ImageLoader.getInstance().displayImage(MyApi.URL + MyApi.IMAGEURL + dataBean.getPicture() + "", touxiang_im);
                            }
                        } else {
                            ImageUtil.displayImage(AddUserActivity.this, R.drawable.touxiangid, touxiang_im);
                        }
                        try {
                            nice_spinner_riqi.setText(DateUtil.getDateToString(dataBean.getLdrk_ljrq(), "yyyy-MM-dd"));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        if (dataBean.getLdrk_lxjzdrq() != null) {
                            xian_data_ince.setText(dataBean.getLdrk_lxjzdrq());
                        }

                        if (dataBean.getBeizhu() != null) {
                            beizhu_cet.setText(dataBean.getBeizhu());
                        }
                        switch (dataBean.getXb() != null ? dataBean.getXb() : "") {
                            case "男":
                                item0_radiobutton_nan.setChecked(true);
                                break;
                            case "女":
                                item0_radiobutton_nv.setChecked(true);
                                break;

                            default:
                                break;
                        }

                        //设置不可编辑
                        ed_name.setFocusable(false);
                        item0_radiobutton_nan.setEnabled(false);
                        item0_radiobutton_nv.setEnabled(false);
                        nice_spinner_minzu.setEnabled(false);
                        idcard_ed.setFocusable(false);
                        address_ct.setFocusable(false);
                    }

                    break;

                case 7:
                    /**
                     * 没网修改
                     */
                    String name1 = msg.getData().getString("name");
                    String sax_string2 = msg.getData().getString("sax_string");
                    String minzu3 = msg.getData().getString("minzu");
                    String idcard4 = msg.getData().getString("idcard");
                    String address5 = msg.getData().getString("address");
                    String phone6 = msg.getData().getString("phone");
                    String wenhua7 = msg.getData().getString("wenhua");
                    String hunyin8 = msg.getData().getString("hunyin");
                    String huji9 = msg.getData().getString("huji");
                    String yuanyin1 = msg.getData().getString("yuanyin");
                    String beizhu2 = msg.getData().getString("beizhu");
                    String picture3 = msg.getData().getString("picture");
                    String riqi6 = msg.getData().getString("riqi");
                    String xainriqi8 = msg.getData().getString("xainriqi");
                    String new_picture = msg.getData().getString("new_picture");
                    String xingzhi1 = msg.getData().getString("rylx");
                    String fuwucs1 = msg.getData().getString("fuwucs");

                    //  修改房屋人员信息 插入记录表
                    if (recordMeanag == null) {
                        recordMeanag = new RecordMeanage(AddUserActivity.this);
                    }
                    RecordBean recordBea2 = new RecordBean();
                    recordBea2.setType(MyApi.UPDATE);
                    recordBea2.setStatement("update t_ryxx set xm=" + "'" + name1 + "'," + "xb=" + "'" + sax_string2 + "'," + "minzu=" + "'" + minzu3 + "'," + "sfz=" + "'" + idcard4 + "'," + "hj_hjxz=" + "'" + address5 + "',"
                            + "lxdh=" + "'" + phone6 + "'," + "ldrk_whcd=" + "'" + wenhua7 + "'," + "ldrk_hunyin=" + "'" + hunyin8 + "'," + "ldrk_zzcs=" + "'" + huji9 + "'," + "ldrk_ljqcyzk=" + "'" + yuanyin1 + "'," + "is_del=" + "'" + "否" + "'," + "beizhu=" + "'" + beizhu2 + "'," + "ldrk_ljrq=" + "'" + riqi6 + "'," + "ldrk_lxjzdrq=" + "'" + xainriqi8 + "'," + "dizhi_xz=" + "'" + addressUp + "'," + "picture=" + "'" + picture3 + "'," + "new_picture=" + "' " + new_picture + "'," + "rylx=" + "' " + xingzhi1 + "', " + "ldrk_fwcs=" + "' " + fuwucs1 + "'" + "where sfz=" + "'" + idcard4 + "' and " + "jz_fwid=" + "'" + fz_id + "'");
                    recordBea2.setOperateDate("");
                    recordBea2.setOperateUser((String) SPUtils.get(AddUserActivity.this, "loginName", ""));
                    recordBea2.setTableName("t_ryxx");
                    recordMeanag.addRecord(recordBea2);
                    SPUtils.put(AddUserActivity.this, "isResune", true);
                    SPUtils.put(AddUserActivity.this, "updateIs", "yes");
                    ToastUtil.showInfo(AddUserActivity.this, "操作成功");
                    finish();
                    break;

                default:
                    break;
            }
        }
    };


    @Override
    protected int setLayout() {
        return R.layout.activity_add_user;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void initView() {
        setSteepStatusBar(true);
        setTooBarBreakImage(R.mipmap.breakimagee);
        AndroidBug5497Workaround.assistActivity(this);
        //民族
        nice_spinner_minzu.attachDataSource(mimzu);
        nice_spinner_minzu.setBackgroundResource(R.drawable.nicespinner_bg);
        nice_spinner_minzu.setTextColor(R.color.colors_font6);

//        //文化程度
//        nice_spinner_wenhua.attachDataSource(wenhua);
//        nice_spinner_wenhua.setBackgroundResource(R.drawable.nicespinner_bg);
//        nice_spinner_wenhua.setTextColor(R.color.colors_font6);
//
//        //婚姻状况
//        nice_spinner_hunyin.attachDataSource(hunyin);
//        nice_spinner_hunyin.setBackgroundResource(R.drawable.nicespinner_bg);
//        nice_spinner_hunyin.setTextColor(R.color.colors_font6);
//
//        //户籍类型
//        nice_spinner_huji.attachDataSource(huji);
//        nice_spinner_huji.setBackgroundResource(R.drawable.nicespinner_bg);
//        nice_spinner_huji.setTextColor(R.color.colors_font6);
//
//        //来京原因
//        nice_spinner_yuanyin.attachDataSource(yuanyin);
//        nice_spinner_yuanyin.setBackgroundResource(R.drawable.nicespinner_bg);
//        nice_spinner_yuanyin.setTextColor(R.color.colors_font6);

        //来京日期 nice_spinner_riqi
        nice_spinner_riqi.attachDataSource(riqi);
        nice_spinner_riqi.setBackgroundResource(R.drawable.nicespinner_bg);
        nice_spinner_riqi.setTextColor(R.color.colors_font6);

        //来现地址日期
        xian_data_ince.attachDataSource(xainData);
        xian_data_ince.setBackgroundResource(R.drawable.nicespinner_bg);
        xian_data_ince.setTextColor(R.color.colors_font6);

        idCard_im.setOnClickListener(this);
        commit_im.setOnClickListener(this);
        nice_spinner_riqi.setOnClickListener(this);
        xian_data_ince.setOnClickListener(this);

        image_add_re.setLayoutManager(new GridLayoutManager(AddUserActivity.this, 4));
        image_add_re.setNestedScrollingEnabled(false);
        initAnim();
        setOnlin();
    }

    /**
     * 判断是否是北京  户籍
     */
    public void setOnlin() {
        address_ct.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                //包含北京市
                if (ConvertUtils.isText(s.toString().trim(), "北京市")) {
                    String address = (String) SPUtils.get(AddUserActivity.this, SPUtils.get(AddUserActivity.this, "welcomeUserName", "") + "_addressguolv", "true");

                    if (!"true".equals(address)) {
                        if (ConvertUtils.isText(s.toString().trim(), address)) {
                            //默认户籍人口
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    huji_ren.setChecked(true);
                                    RENYUANXINGZHI = huji_ren.getText().toString().trim();
                                }
                            });
                        } else {
                            //不包含 人户分离
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    fenli_ren.setChecked(true);
                                    RENYUANXINGZHI = fenli_ren.getText().toString().trim();
                                }
                            });
                        }
                    } else {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                //默认户籍人口
                                huji_ren.setChecked(true);
                                RENYUANXINGZHI = huji_ren.getText().toString().trim();
                            }
                        });
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //显示人口性质
                            lid.startAnimation(mShowAction);
                            lid.setVisibility(View.VISIBLE);
                            xingzhi_gr.startAnimation(mShowAction);
                            xingzhi_gr.setVisibility(View.VISIBLE);

                            //来京日期隐藏
                            laijing_data_line.startAnimation(mHiddenAction);
                            laijing_data_line.setVisibility(View.GONE);
                            //来京原因隐藏
                            li0.startAnimation(mHiddenAction);
                            li0.setVisibility(View.GONE);
                            laijing_ra.startAnimation(mHiddenAction);
                            laijing_ra.setVisibility(View.GONE);
                            //流动人口隐藏
                            liudong_ren.startAnimation(mHiddenAction);
                            liudong_ren.setVisibility(View.GONE);

                            if (XINGZHISTRING != null) {
                                switch (XINGZHISTRING) {
                                    case "户籍人口":
                                        //默认户籍人口
                                        huji_ren.setChecked(true);
                                        break;
                                    case "人户分离":
                                        fenli_ren.setChecked(true);
                                        break;

                                    default:
                                        break;
                                }
                            }
                        }
                    });
                } else {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //不包含
                            //隐藏人员性质
                            lid.startAnimation(mHiddenAction);
                            lid.setVisibility(View.GONE);
                            xingzhi_gr.startAnimation(mHiddenAction);
                            xingzhi_gr.setVisibility(View.GONE);
                            liudong_ren.setChecked(true);
                            RENYUANXINGZHI = liudong_ren.getText().toString().trim();
                            //来京日期显示
                            laijing_data_line.startAnimation(mShowAction);
                            laijing_data_line.setVisibility(View.VISIBLE);
                            //来京原因显示
                            li0.startAnimation(mShowAction);
                            li0.setVisibility(View.VISIBLE);
                            laijing_ra.startAnimation(mShowAction);
                            laijing_ra.setVisibility(View.VISIBLE);

                        }
                    });
                }
            }
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void initData() {
        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        // 修改
        if (type.equals("update")) {
            userId = intent.getStringExtra("UserId");
            fz_id = intent.getStringExtra("fz_id");
            floorCode = intent.getStringExtra("floorCode");
            addressname = intent.getStringExtra("addressname");
            addressUp = intent.getStringExtra("addressUp");
            setTooBarTitleText("街路巷列表" + ">" + addressname);
            image_add_re.setVisibility(View.VISIBLE);
            re8.setVisibility(View.VISIBLE);
            if (NetWorkUtil.isConn(AddUserActivity.this) == false) {

                if (FileUtils.fileIsExists(MyApi.FileLoad + MyApi.DBNAME) == true) {
                    if (louMessage == null) {
                        louMessage = new LouMessage(AddUserActivity.this);
                    }
                    if (louMessage.tabbleIsExist("t_jzw_jlx") == true) {
                        //没网修改本地数据库回显
                        sqSelect(userId);
                    }
                }

            } else {
                DiaLogUtil.showDiaLog(this, "加载数据中");
                getUser(userId);
            }

            //添加
        } else if (type.equals("adduser")) {
            fz_id = intent.getStringExtra("fz_id");
            floorCode = intent.getStringExtra("floorCode");
            addressname = intent.getStringExtra("addressname");
            addressUp = intent.getStringExtra("addressUp");
            setTooBarTitleText("街路巷列表" + ">" + addressname);
            image_add_re.setVisibility(View.GONE);
            re8.setVisibility(View.GONE);
        }


        selImageList = new ArrayList<>();
        adapter = new ImagePickerAdapter(this, selImageList, maxImgCounts);
        adapter.setOnItemClickListener(this);
        add_user_recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        add_user_recyclerView.setHasFixedSize(true);
        add_user_recyclerView.setAdapter(adapter);

        imagePicker = new ImagePicker();
        // 设置标题
        imagePicker.setTitle("选择身份证照片");
        // 设置是否裁剪图片
        imagePicker.setCropImage(false);
        handler.post(new Runnable() {
            @Override
            public void run() {
                xian_data_ince.setText(DateUtil.getStringDateShort());
                nice_spinner_riqi.setText(DateUtil.getStringDateShort());
            }
        });

        initRadiGroup();
    }

    /**
     * 初始化动画
     */
    public void initAnim() {
        mShowAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                -1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        mShowAction.setDuration(500);

        mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                -1.0f);
        mHiddenAction.setDuration(500);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_image:
                checkPermission();
                break;

            case R.id.commit_im:
                //姓名
                String name = ed_name.getText().toString();
                //民族
                String minzu = nice_spinner_minzu.getText().toString().trim();
                //身份证号
                String idcard = idcard_ed.getText().toString().trim();
                //住址
                String address = address_ct.getText().toString().trim();
                //联系电话
                String phone = phone_cet.getText().toString().trim();
                //服务地址
                String fuwu_address = fuwu_changsuo.getText().toString().trim();
                //文化程度
                String wenhua = WENHUA_CHENGDU;
                //婚姻状况
                String hunyin = HUNYIN_ZHUANGKUANG;
                //户籍类型
                String huji = HUJI_LEIXING;
                //人员性质
                String xingzhi = RENYUANXINGZHI;
                //来京原因
                String yuanyin = LAIJING_YUANYIN;
                //来现地址日期
                String xainriqi = xian_data_ince.getText().toString().trim();
                //来京日期
                String riqi = nice_spinner_riqi.getText().toString().trim();
                //备注
                String beizhu = beizhu_cet.getText().toString().trim();
                if (name.equals("")) {
                    ToastUtil.showInfo(AddUserActivity.this, "输入姓名");
                } else if (sax_string == null) {
                    ToastUtil.showInfo(AddUserActivity.this, "选择性别");
                } else if (minzu.equals("请选择")) {
                    ToastUtil.showInfo(AddUserActivity.this, "选择民族");
                } else if (Validator.isLegalId(idcard) == false) {
                    ToastUtil.showInfo(AddUserActivity.this, "输入合法身份证号");
                } else if (address.equals("")) {
                    ToastUtil.showInfo(AddUserActivity.this, "输入地址");
                } else if (Validator.isTelPhoneNumber(phone) == false) {
                    ToastUtil.showInfo(AddUserActivity.this, "输入合法手机号");
                } else if (wenhua.equals("请选择")) {
                    ToastUtil.showInfo(AddUserActivity.this, "选择文化程度");
                } else if (hunyin.equals("请选择")) {
                    ToastUtil.showInfo(AddUserActivity.this, "选择婚姻状况");
                } else if (huji.equals("请选择")) {
                    ToastUtil.showInfo(AddUserActivity.this, "选择户籍类型");
                } else if (xingzhi.equals("请选择")) {
                    ToastUtil.showInfo(AddUserActivity.this, "选择人员性质");
                } else if (yuanyin.equals("请选择")) {
                    ToastUtil.showInfo(AddUserActivity.this, "选择来京原因");
                } else if (xainriqi.equals("请选择")) {
                    ToastUtil.showInfo(AddUserActivity.this, "选择来现地址日期");
                } else {
                    if (type.equals("update")) {
                        if (NetWorkUtil.isConn(AddUserActivity.this) == false) {

                            if (FileUtils.fileIsExists(MyApi.FileLoad + MyApi.DBNAME) == true) {
                                if (louMessage == null) {
                                    louMessage = new LouMessage(AddUserActivity.this);
                                }
                                if (louMessage.tabbleIsExist("t_jzw_jlx") == true) {
                                    //没网走数据库修改
                                    sqUpdate(userId, idcard, fz_id, name, sax_string, minzu, idcard, address, phone, wenhua, hunyin, huji, yuanyin, riqi, xainriqi, beizhu, picture, Nobuf, xingzhi, fuwu_address);
                                }
                            }
                        } else {
                            DiaLogUtil.showDiaLog(AddUserActivity.this, "正在修改");
                            if (riqi.equals("请选择")) {
                                riqi = DateUtil.getDates();
                            }
                            try {
                                addZuHu(name, sax_string, minzu, idcard, address, phone, wenhua, hunyin, huji, yuanyin, riqi, xainriqi, beizhu, picture, buf, Nobuf, xingzhi, fuwu_address);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    } else if (type.equals("adduser")) {
                        if (NetWorkUtil.isConn(AddUserActivity.this) == false) {

                            if (FileUtils.fileIsExists(MyApi.FileLoad + MyApi.DBNAME) == true) {
                                if (louMessage == null) {
                                    louMessage = new LouMessage(AddUserActivity.this);
                                }
                                if (louMessage.tabbleIsExist("t_jzw_jlx") == true) {
                                    //没有网 走数据库添加
                                    sqlAddUser(fz_id, floorCode, name, sax_string, minzu, idcard, address, phone, wenhua, hunyin, huji, yuanyin, riqi, xainriqi, beizhu, picture, Nobuf, xingzhi, fuwu_address);
                                }
                            }

                        } else {
                            //有网
                            DiaLogUtil.showDiaLog(AddUserActivity.this, "正在添加");
                            if (riqi.equals("请选择")) {
                                riqi = DateUtil.getDates();
                            }
                            try {
                                addZuHu(name, sax_string, minzu, idcard, address, phone, wenhua, hunyin, huji, yuanyin, riqi, xainriqi, beizhu, picture, buf, Nobuf, xingzhi, fuwu_address);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                break;
            case R.id.nice_spinner_riqi:
                // 来京日期选择
                final Calendar c = Calendar.getInstance();
                DatePickerDialog dialog = new DatePickerDialog(AddUserActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        c.set(year, monthOfYear, dayOfMonth);
                        nice_spinner_riqi.setText(DateFormat.format("yyy-MM-dd", c));
                        nice_spinner_riqi.dismissDropDown();
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));

                dialog.setButton(DatePickerDialog.BUTTON_NEGATIVE, "取消", new DatePickerDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        nice_spinner_riqi.dismissDropDown();
                    }
                });


                dialog.show();

                break;
            case R.id.xian_data_ince:
                //来现地址日期
                final Calendar cb = Calendar.getInstance();
                DatePickerDialog dialogs = new DatePickerDialog(AddUserActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        cb.set(year, monthOfYear, dayOfMonth);
                        xian_data_ince.setText(DateFormat.format("yyy-MM-dd", cb));
                        xian_data_ince.dismissDropDown();
                    }
                }, cb.get(Calendar.YEAR), cb.get(Calendar.MONTH), cb.get(Calendar.DAY_OF_MONTH));

                dialogs.setButton(DatePickerDialog.BUTTON_NEGATIVE, "取消", new DatePickerDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        xian_data_ince.dismissDropDown();
                    }
                });

                dialogs.show();

                break;
            default:
                break;
        }
    }

    ArrayList<ImageItem> images = null;

    /**
     * 身份读取结果
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 200) {
            if (data != null) {
                IdCardInfo idCardInfo = (IdCardInfo) data.getSerializableExtra("idcardinfo");
                if (idCardInfo != null) {
                    try {
                        String res = new String(idCardInfo.getCharInfo(), "gbk");
                        Idcard idcard = (Idcard) JsonUtils.stringToObject(res.trim(), Idcard.class);

                        ed_name.setText(idcard.getName().getValue() != null ? idcard.getName().getValue() : "识别失败");
                        nice_spinner_minzu.setText(idcard.getFolk().getValue() != null ? idcard.getFolk().getValue() + "族" : "");
                        idcard_ed.setText(idcard.getNum().getValue() != null ? idcard.getNum().getValue() : "");
                        address_ct.setText(idcard.getAddr().getValue() != null ? idcard.getAddr().getValue() : "");

                        switch (idcard.getSex().getValue() != null ? idcard.getSex().getValue() : "") {
                            case "男":
                                item0_radiobutton_nan.setChecked(true);
                                break;
                            case "女":
                                item0_radiobutton_nv.setChecked(true);
                                break;
                            default:
                                break;
                        }

                        //touxiang_im.setImageURI(Uri.fromFile(new File(idCardInfo.getHeadPath())));
                        Bitmap bitmap1 = ImageUtil.getBitmapFormUri(AddUserActivity.this, Uri.fromFile(new File(idCardInfo.getHeadPath())));
                        touxiang_im.setImageBitmap(bitmap1);
                        picture = Base64Utils.bitmapToBase64(bitmap1);
                        picImage = Base64Utils.bitmapToBase64(bitmap1);
                        Bitmap bitmap2 = ImageUtil.getBitmapFormUri(AddUserActivity.this, Uri.fromFile(new File(idCardInfo.getImgPath())));
                        idCard_im.setImageBitmap(bitmap2);
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                } else {

//                    Log.e("res", "" + "idCardInfo is null");
                }
            } else {
//                Log.e("res", "" + "data is null");
            }
        } else {
//            Log.e("res", "resultCode" + resultCode);
        }
        if (resultCode == com.lzy.imagepicker.ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == REQUEST_CODE_SELECT) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(com.lzy.imagepicker.ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null) {
                    try {
                        // 这里我们即将处理图片的方向
                        int degrees = getOrientation(this, Uri.fromFile(new File(images.get(0).path)));
                        // 旋转你的图片  加压缩
                        Bitmap bitmap = ImageUtil.getBitmapFormUri(AddUserActivity.this, Uri.fromFile(new File(images.get(0).path)));

                        Bitmap bitmap1 = rotateImage(bitmap, degrees);
                        idCard_im.setImageBitmap(bitmap1);
                        getIdcard(Base64Utils.bitmapToBase64s(bitmap1));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            } else if (data != null && requestCode == REQUEST_CODE_SELECTS) {
                beizhu_cet.requestFocus();
                images = (ArrayList<ImageItem>) data.getSerializableExtra(com.lzy.imagepicker.ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null) {
                    selImageList.addAll(images);
                    adapter.setImages(selImageList);
                    buf = new StringBuffer();
                    Nobuf = new StringBuffer();
                    for (int i = 0; i < images.size(); i++) {
                        try {
                            Bitmap bitmap1 = ImageUtil.getBitmapFormUri(AddUserActivity.this, Uri.fromFile(new File(images.get(i).path)));
                            // Bitmap bitmap1 = BitmapFactory.decodeFile(images.get(i).path);//filePath
                            buf.append(Base64Utils.bitmapToBase64(bitmap1)).append(",");
                            Nobuf.append(Base64Utils.bitmapToBase64(bitmap1)).append(",");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } else if (resultCode == com.lzy.imagepicker.ImagePicker.RESULT_CODE_BACK) {
            //预览图片返回
            if (data != null && requestCode == REQUEST_CODE_PREVIEW) {
                beizhu_cet.requestFocus();
                images = (ArrayList<ImageItem>) data.getSerializableExtra(com.lzy.imagepicker.ImagePicker.EXTRA_IMAGE_ITEMS);
                if (images != null) {
                    selImageList.clear();
                    selImageList.addAll(images);
                    adapter.setImages(selImageList);
                    buf = null;
                    Nobuf = null;
                    buf = new StringBuffer();
                    Nobuf = new StringBuffer();
                    for (int i = 0; i < images.size(); i++) {
                        try {
                            Bitmap bitmap2 = ImageUtil.getBitmapFormUri(AddUserActivity.this, Uri.fromFile(new File(images.get(i).path)));
                            //Bitmap bitmap2 = BitmapFactory.decodeFile(images.get(i).path);//filePath
                            buf.append(Base64Utils.bitmapToBase64(bitmap2)).append(",");
                            Nobuf.append(Base64Utils.bitmapToBase64(bitmap2)).append(",");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public static int getOrientation(Context context, Uri photoUri) {
        int orientation = 0;
        Cursor cursor = context.getContentResolver().query(photoUri,
                new String[]{MediaStore.Images.ImageColumns.ORIENTATION}, null, null, null);
        if (cursor != null) {
            if (cursor.getCount() != 1) {
                return -1;
            }
            cursor.moveToFirst();
            orientation = cursor.getInt(0);
            cursor.close();
        }
        return orientation;
    }

    public static Bitmap rotateImage(Bitmap bmp, int degrees) {
        if (degrees != 0) {
            Matrix matrix = new Matrix();
            matrix.postRotate(degrees);
            return Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, true);
        }
        return bmp;
    }

    /**
     * 修改住户信息
     *
     * @param userId 用户id
     * @param idcard 身份证
     * @param fz_id  hui_d
     */
    public void sqUpdate(String userId, String idcar, String fz_id, String name, String sax_string, String minzu, String idcard, String address, String phone, String wenhua, String hunyin, String huji, String yuanyin, String riqi, String xainriqi, String beizhu, String picture, StringBuffer Nobuf, String xingzhi, String fuwu_address) {
        if (zhuHuMeanage == null) {
            zhuHuMeanage = new ZhuHuMeanage(AddUserActivity.this);
        }
        GetUserBean.DataBean dataBean = new GetUserBean.DataBean();
        dataBean.setXm(name);
        dataBean.setXb(sax_string);
        dataBean.setMinzu(minzu);
        dataBean.setSfz(idcard);
        dataBean.setHj_hjxz(address);
        dataBean.setLxdh(phone);
        dataBean.setLdrk_whcd(wenhua);
        dataBean.setLdrk_hunyin(hunyin);
        dataBean.setLdrk_zzcs(huji);
        dataBean.setLdrk_ljqcyzk(yuanyin);
        dataBean.setLdrk_fwcs(fuwu_address);
        if (riqi.equals("请选择")) {
            riqi = DateUtil.getDates();
        }
        try {
            dataBean.setLdrk_ljrq(DateUtil.getTimeStamp(riqi, "yyyy-MM-dd"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        dataBean.setLdrk_lxjzdrq(xainriqi);
        dataBean.setBeizhu(beizhu);
        dataBean.setPicture(picture);
        dataBean.setIs_del("否");
        dataBean.setRylx(xingzhi);
        dataBean.setNew_picture(Nobuf);
        zhuHuMeanage.updateZhuHu(userId, idcar, fz_id, dataBean);

        Message msg = new Message();
        msg.what = 7;
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        bundle.putString("sax_string", sax_string);
        bundle.putString("minzu", minzu);
        bundle.putString("idcard", idcard);
        bundle.putString("address", address);
        bundle.putString("phone", phone);
        bundle.putString("wenhua", wenhua);
        bundle.putString("hunyin", hunyin);
        bundle.putString("huji", huji);
        bundle.putString("yuanyin", yuanyin);
        bundle.putString("beizhu", beizhu);
        bundle.putString("picture", picture);
        bundle.putString("riqi", riqi);
        bundle.putString("xainriqi", xainriqi);
        bundle.putString("new_picture", Nobuf + "");
        bundle.putString("rylx", xingzhi);
        bundle.putString("fuwucs", fuwu_address);
        msg.setData(bundle);
        handler.sendMessage(msg);


    }


    /**
     * 查询住户信息 用于修改回显
     *
     * @param useid
     */

    public void sqSelect(String useid) {
        GetUserBean.DataBean dataBean = new GetUserBean.DataBean();
        if (zhuHuMeanage == null) {
            zhuHuMeanage = new ZhuHuMeanage(AddUserActivity.this);
        }
        dataBean = zhuHuMeanage.getUser(useid);

        Message msg = new Message();
        msg.what = 6;
        msg.obj = dataBean;
        handler.sendMessage(msg);

    }

    /**
     * 本地数据库添房间人员
     */
    public void sqlAddUser(String fz_id, String floorCode, String name, String sax_string, String minzu, String idcard, String address, String phone, String wenhua, String hunyin, String huji, String yuanyin, String riqi, String xainriqi, String beizhu, String picture, StringBuffer nobuf, String xingzhi, String fuwucs_address) {
        try {
            AddHouseBean.DataBean.RyListBean ryListBean = new AddHouseBean.DataBean.RyListBean();
            if (zhuHuMeanage == null) {
                zhuHuMeanage = new ZhuHuMeanage(AddUserActivity.this);
            }
            ryListBean.setXm(name);
            ryListBean.setXb(sax_string);
            ryListBean.setMinzu(minzu);
            ryListBean.setSfz(idcard);
            ryListBean.setHj_hjxz(address);
            ryListBean.setLxdh(phone);
            ryListBean.setLdrk_whcd(wenhua);
            ryListBean.setLdrk_hunyin(hunyin);
            ryListBean.setLdrk_zzcs(huji);
            ryListBean.setLdrk_ljqcyzk(yuanyin);
            ryListBean.setLdrk_fwcs(fuwucs_address);
            if (riqi.equals("请选择")) {
                riqi = DateUtil.getDates();
            }
            ryListBean.setLdrk_ljrq(DateUtil.getTimeStamp(riqi, "yyyy-MM-dd"));
            ryListBean.setLdrk_lxjzdrq(xainriqi);
            ryListBean.setBeizhu(beizhu);
            ryListBean.setPicture(picImage);
            ryListBean.setNew_picture(nobuf);
            ryListBean.setIs_del("否");
            ryListBean.setRylx(xingzhi);
            zhuHuMeanage.addZhuHu(fz_id, floorCode, ryListBean);


            Message msg = new Message();
            msg.what = 4;
            Bundle bundle = new Bundle();
            bundle.putString("name", name);
            bundle.putString("sax_string", sax_string);
            bundle.putString("minzu", minzu);
            bundle.putString("idcard", idcard);
            bundle.putString("address", address);
            bundle.putString("phone", phone);
            bundle.putString("wenhua", wenhua);
            bundle.putString("hunyin", hunyin);
            bundle.putString("huji", huji);
            bundle.putString("yuanyin", yuanyin);
            bundle.putString("beizhu", beizhu);
            bundle.putString("picture", picture);
            bundle.putString("riqi", riqi);
            bundle.putString("xainriqi", xainriqi);
            bundle.putString("new_picture", nobuf + "");
            bundle.putString("rylx", xingzhi);
            bundle.putString("ldrk_fwcs", fuwucs_address);
            msg.setData(bundle);
            handler.sendMessage(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 身份证识别
     *
     * @param photoBase64
     */

    public void getIdcard(String photoBase64) {
        DiaLogUtil.showDiaLog(AddUserActivity.this, "正在识别");
        OkHttpUtils
                .post()
                .url(MyApi.URL + MyApi.GEIIDCARD)
                .tag(this)
                .addHeader("JSESSIONID", (String) SPUtils.get(AddUserActivity.this, "JSESSIONID", ""))
                .addParams("photoBase64", photoBase64)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        DiaLogUtil.dismissDiaLog();
                        ToastUtil.showInfo(AddUserActivity.this, "无法获取信息 请注意拍照姿势");
                    }

                    @Override
                    public void onResponse(String response) {
                        // Log.e(TAG, "loginMessage------xiexi" + response);
                        DiaLogUtil.dismissDiaLog();
//                        LongLog.e("sssssssssss", response);
                        try {
                            IdCardBean idCardBean = (IdCardBean) JsonUtils.stringToObject(response, IdCardBean.class);
                            Message msg = new Message();
                            msg.what = 1;
                            msg.obj = idCardBean;
                            handler.sendMessage(msg);
                        } catch (Exception e) {
                            e.printStackTrace();
                            ToastUtil.showInfo(AddUserActivity.this, getString(R.string.nowufu));
                        }
                    }
                });
    }

    /**
     * 在线添加修改
     *
     * @param name
     * @param sax_string
     * @param minzu
     * @param idcard
     * @param address
     * @param phone
     * @param wenhua
     * @param hunyin
     * @param huji
     * @param yuanyin
     * @param riqi
     * @param beizhu
     * @param picture
     */
    public void addZuHu(final String name, final String sax_string, final String minzu, final String idcard, final String address, final String phone, final String wenhua, final String hunyin, final String huji, final String yuanyin, final String riqi, final String xainriqi, final String beizhu, final String picture, final StringBuffer buf, final StringBuffer Nobuf, final String xingzhi, final String fuwu_address) throws ParseException {
//        Log.e(TAG, "idcard" + idcard);
        OkHttpUtils
                .post()
                .url(MyApi.URL + MyApi.ADDZHUHU)
                .tag(this)
                .addHeader("JSESSIONID", (String) SPUtils.get(AddUserActivity.this, "JSESSIONID", ""))
                .addParams("jz_fwid", fz_id)
                .addParams("jz_jzwbm", floorCode)
                .addParams("id", userId)//  user id   空的是添加
                .addParams("xm", name)
                .addParams("xb", sax_string)
                .addParams("minzu", minzu)
                .addParams("sfz", idcard != null ? idcard : "")
                .addParams("hj_hjxz", address)
                .addParams("lxdh", phone)
                .addParams("ldrk_whcd", wenhua)
                .addParams("ldrk_fwcs", fuwu_address)
                .addParams("rylx", xingzhi)
                .addParams("ls_del", "否")
                .addParams("ldrk_hunyin", hunyin)
                .addParams("ldrk_zzcs", huji)
                .addParams("ldrk_ljqcyzk", yuanyin)
                .addParams("ldrk_ljrq", riqi != null ? riqi : "")
                .addParams("ldrk_lxjzdrq", xainriqi != null ? xainriqi : "")
                .addParams("beizhu", beizhu != null ? beizhu : "")
                .addParams("dizhi_xz", addressUp != null ? addressUp : "")
                .addParams("picture", picture != null ? picture : "")
                .addParams("new_picture", buf + "")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        DiaLogUtil.dismissDiaLog();
                        ToastUtil.showInfo(AddUserActivity.this, getString(R.string.wufaData));
                    }

                    @Override
                    public void onResponse(String response) {
//                        Log.e(TAG, "loginMessage------add" + response);
                        DiaLogUtil.dismissDiaLog();
                        try {
                            AddUserBean addUserBean = (AddUserBean) JsonUtils.stringToObject(response, AddUserBean.class);
                            if (addUserBean.isStatus() == false) {
                                ToastUtil.showInfo(AddUserActivity.this, addUserBean.getMsg());
                            } else {
                                SPUtils.put(AddUserActivity.this, "isResune", true);
                                if (type.equals("update")) {
                                    if (louMessage == null) {
                                        louMessage = new LouMessage(AddUserActivity.this);
                                    }
                                    if (FileUtils.fileIsExists(MyApi.FileLoad + MyApi.DBNAME) == true) {
                                        if (louMessage.tabbleIsExist("t_jzw_jlx") == true) {
                                            if (zhuHuMeanage == null) {
                                                zhuHuMeanage = new ZhuHuMeanage(AddUserActivity.this);
                                            }
                                            GetUserBean.DataBean dataBean = new GetUserBean.DataBean();
                                            dataBean.setXm(name);
                                            dataBean.setXb(sax_string);
                                            dataBean.setMinzu(minzu);
                                            dataBean.setSfz(idcard);
                                            dataBean.setHj_hjxz(address);
                                            dataBean.setLxdh(phone);
                                            dataBean.setLdrk_whcd(wenhua);
                                            dataBean.setLdrk_hunyin(hunyin);
                                            dataBean.setLdrk_zzcs(huji);
                                            dataBean.setLdrk_ljqcyzk(yuanyin);
                                            dataBean.setRylx(xingzhi);
                                            dataBean.setLdrk_fwcs(fuwu_address);
                                            dataBean.setNew_picture(Nobuf + "");
                                            try {
                                                dataBean.setLdrk_ljrq(DateUtil.getTimeStamp(riqi, "yyyy-MM-dd"));
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                            dataBean.setLdrk_lxjzdrq(xainriqi);
                                            dataBean.setBeizhu(beizhu);
                                            if (!picImage.equals("")) {
                                                dataBean.setPicture(picImage);
                                            }
                                            dataBean.setIs_del("否");
                                            zhuHuMeanage.updateZhuHu(userId, idcard, fz_id, dataBean);

                                            ToastUtil.showInfo(AddUserActivity.this, "操作成功");
                                            finish();
                                        } else {
                                            ToastUtil.showInfo(AddUserActivity.this, "操作成功");
                                            finish();
                                        }
                                    } else {
                                        ToastUtil.showInfo(AddUserActivity.this, "操作成功");
                                        finish();
                                    }

                                } else {
                                    //添加
                                    if (louMessage == null) {
                                        louMessage = new LouMessage(AddUserActivity.this);
                                    }
                                    if (FileUtils.fileIsExists(MyApi.FileLoad + MyApi.DBNAME) == true) {
                                        if (louMessage.tabbleIsExist("t_jzw_jlx") == true) {

                                            int renshu_ldrk1 = 0;
                                            int renshu_rhfl1 = 0;
                                            int renshu_hjrk1 = 0;
                                            AddHouseBean.DataBean.RyListBean ryListBean = new AddHouseBean.DataBean.RyListBean();
                                            if (zhuHuMeanage == null) {
                                                zhuHuMeanage = new ZhuHuMeanage(AddUserActivity.this);
                                            }
                                            ryListBean.setXm(name);
                                            ryListBean.setXb(sax_string);
                                            ryListBean.setMinzu(minzu);
                                            ryListBean.setSfz(idcard);
                                            ryListBean.setHj_hjxz(address);
                                            ryListBean.setLxdh(phone);
                                            ryListBean.setLdrk_whcd(wenhua);
                                            ryListBean.setRylx(xingzhi);
                                            ryListBean.setLdrk_hunyin(hunyin);
                                            ryListBean.setLdrk_zzcs(huji);
                                            ryListBean.setLdrk_ljqcyzk(yuanyin);
                                            ryListBean.setNew_picture(Nobuf + "");
                                            ryListBean.setLdrk_fwcs(fuwu_address);
                                            try {
                                                ryListBean.setLdrk_ljrq(DateUtil.getTimeStamp(riqi, "yyyy-MM-dd"));
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                            ryListBean.setLdrk_lxjzdrq(xainriqi);
                                            ryListBean.setBeizhu(beizhu);
                                            if (!picImage.equals("")) {
                                                ryListBean.setPicture(picImage);
                                            }
                                            ryListBean.setIs_del("否");
                                            zhuHuMeanage.addZhuHu(fz_id, floorCode, ryListBean);

                                            t_jzw_fangjian t_jzw_fangjian = new t_jzw_fangjian();
                                            t_jzw_fangjian = zhuHuMeanage.selectFangjian(fz_id);

                                            if (t_jzw_fangjian.getRenshu_ldrk() != null) {
                                                renshu_ldrk1 = t_jzw_fangjian.getRenshu_ldrk();
                                            } else if (t_jzw_fangjian.getRenshu_rhfl() != null) {
                                                renshu_rhfl1 = t_jzw_fangjian.getRenshu_rhfl();
                                            } else if (t_jzw_fangjian.getRenshu_hjrk() != null) {
                                                renshu_hjrk1 = t_jzw_fangjian.getRenshu_hjrk();
                                            }

                                            if (xingzhi.equals("流动人口")) {
                                                if (unitsListBeans == null) {
                                                    unitsListBeans = new ArrayList<>();
                                                } else {
                                                    unitsListBeans.clear();
                                                }
                                                unitsListBeans = zhuHuMeanage.selectLiu(floorCode);
                                                if (0 < unitsListBeans.size()) {
                                                    int ldrk_count = unitsListBeans.get(0).getLdrk_count() + 1;
                                                    zhuHuMeanage.updateLiu(ldrk_count, floorCode);
                                                }
                                                zhuHuMeanage.updateFngjian(renshu_ldrk1 + 1, fz_id);
                                            } else if (xingzhi.equals("人户分离")) {
                                                zhuHuMeanage.updateFngjianrhfl(renshu_rhfl1 + 1, fz_id);
                                            } else if (xingzhi.equals("户籍人口")) {
                                                zhuHuMeanage.updateFngjianhjrk1(renshu_hjrk1 + 1, fz_id);
                                            }

                                            ToastUtil.showInfo(AddUserActivity.this, "操作成功");
                                            finish();
                                        } else {
                                            ToastUtil.showInfo(AddUserActivity.this, "操作成功");
                                            finish();
                                        }
                                    } else {
                                        ToastUtil.showInfo(AddUserActivity.this, "操作成功");
                                        finish();
                                    }
                                }
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                            setLogin();
                        }
                    }
                });
    }

    /**
     * 获取租户信息
     *
     * @param UserId
     */
    public void getUser(String UserId) {
        OkHttpUtils
                .post()
                .url(MyApi.URL + MyApi.UPDATEZHUHU + UserId)
                .tag(this)
                .addParams("", "")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        DiaLogUtil.dismissDiaLog();
                        ToastUtil.showInfo(AddUserActivity.this, getString(R.string.wufaData));
                    }

                    @Override
                    public void onResponse(String response) {
//                        Log.e(TAG, "loginMessage------UPDATE" + response);
                        DiaLogUtil.dismissDiaLog();
                        try {
                            GetUserBean getUserBean = (GetUserBean) JsonUtils.stringToObject(response, GetUserBean.class);
                            Message msg = new Message();
                            msg.what = 2;
                            msg.obj = getUserBean;
                            handler.sendMessage(msg);
                        } catch (Exception e) {
                            e.printStackTrace();
                            setLogin();
                        }
                    }
                });
    }


    private void initPopWIndow() {
        darkenBackground(0.6f);
        View view = LayoutInflater.from(AddUserActivity.this).inflate(R.layout.popwindow_select_photo, null);

        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        TextView selectPhoto = (TextView) view.findViewById(R.id.selectPhoto);
        selectPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        TextView takePhoto = (TextView) view.findViewById(R.id.takePhoto);
        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        TextView cancel = (TextView) view.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                darkenBackground(1f);
            }
        });

        popupWindow.setContentView(view);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setAnimationStyle(R.style.popwin_anim_style);
        ColorDrawable colorDrawable = new ColorDrawable(Color.BLUE);
        popupWindow.setBackgroundDrawable(colorDrawable);
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
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

    /**
     * 选择照片识别  2018  11  15 更新直接调取 不用选择  需要选择的话 把下边注释去掉
     */
    public void opnePhoto() {

        //打开选择,本次允许选择的数量
        com.lzy.imagepicker.ImagePicker.getInstance().setSelectLimit(maxImgCount);
        Intent intent = new Intent(AddUserActivity.this, ImageGridActivity.class);
        intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
        startActivityForResult(intent, REQUEST_CODE_SELECT);


//        List<String> names = new ArrayList<>();
//        names.add("拍照");
//        names.add("相册");
//        showDialog(new SelectDialog.SelectDialogListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                switch (position) {
//                    case 0: // 直接调起相机
//
//                        //打开选择,本次允许选择的数量
//                        com.lzy.imagepicker.ImagePicker.getInstance().setSelectLimit(maxImgCount);
//                        Intent intent = new Intent(AddUserActivity.this, ImageGridActivity.class);
//                        intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
//                        startActivityForResult(intent, REQUEST_CODE_SELECT);
//
//
//                        break;
//                    case 1:
//                        //打开选择,本次允许选择的数量
//                        com.lzy.imagepicker.ImagePicker.getInstance().setSelectLimit(maxImgCount);
//                        Intent intent1 = new Intent(AddUserActivity.this, ImageGridActivity.class);
//                        /* 如果需要进入选择的时候显示已经选中的图片，
//                         * 详情请查看ImagePickerActivity
//                         * */
////                                intent1.putExtra(ImageGridActivity.EXTRAS_IMAGES,images);
//                        startActivityForResult(intent1, REQUEST_CODE_SELECT);
//                        break;
//                    default:
//                        break;
//                }
//
//            }
//        }, names);


//
//
//        imagePicker.startChooser(AddUserActivity.this, new ImagePicker.Callback() {
//            // 选择图片回调
//            @Override
//            public void onPickImage(Uri imageUri) {
//                try {
//                    Bitmap bitmap = ImageUtil.getBitmapFormUri(AddUserActivity.this, imageUri);
//                    idCard_im.setImageBitmap(bitmap);
//                    getIdcard(Base64Utils.bitmapToBase64s(bitmap));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            // 自定义裁剪配置
//            @Override
//            public void cropConfig(CropImage.ActivityBuilder builder) {
////                builder
////                        // 是否启动多点触摸
////                        .setMultiTouchEnabled(true)
////                        // 设置网格显示模式
////                        .setGuidelines(CropImageView.Guidelines.OFF)
////                        // 圆形/矩形
////                        .setCropShape(CropImageView.CropShape
////                                .RECTANGLE)
////                        // 调整裁剪后的图片最终大小
////                        .setRequestedSize(960, 960)
////                        // 宽高比
////                        .setAspectRatio(9, 9);
//            }
//
//            // 用户拒绝授权回调
//            @Override
//            public void onPermissionDenied(int requestCode,
//                                           String[] permissions,
//                                           int[] grantResults) {
//            }
//        });
    }

    private SelectDialog showDialog(SelectDialog.SelectDialogListener listener, List<String> names) {
        SelectDialog dialog = new SelectDialog(this, R.style
                .transparentFrameWindowStyle,
                listener, names);
        if (!this.isFinishing()) {
            dialog.show();
        }
        return dialog;
    }

    @Override
    public void onNetChange(int netMobile) {
        if (netMobile == 1) {
            //ToastUtil.showInfo(this, "有网络wifi");
            login_nex_no_rl.setVisibility(View.GONE);

            boolean IsswitchZiDong = (boolean) SPUtils.get(AddUserActivity.this, "IsswitchZiDong", false);
            if (IsswitchZiDong == true) {
                SPUtils.put(AddUserActivity.this, "IsswitchString", true);
            }

        } else if (netMobile == -1) {
            //ToastUtil.showInfo(this, "没有网络");
            login_nex_no_rl.setVisibility(View.VISIBLE);

            boolean IsswitchZiDong = (boolean) SPUtils.get(AddUserActivity.this, "IsswitchZiDong", false);
            if (IsswitchZiDong == true) {
                SPUtils.put(AddUserActivity.this, "IsswitchString", false);
            }

        } else if (netMobile == 0) {
            //ToastUtil.showInfo(this, "4G");
            login_nex_no_rl.setVisibility(View.GONE);
            boolean IsswitchZiDong = (boolean) SPUtils.get(AddUserActivity.this, "IsswitchZiDong", false);
            if (IsswitchZiDong == true) {
                SPUtils.put(AddUserActivity.this, "IsswitchString", true);
            }
        }
    }

    public static String newHeadPath() {
        File file = new File(Environment.getExternalStorageDirectory().getPath() + "/idheadimg/");
        if (!file.exists()) {
            file.mkdirs();
        }
        return Environment.getExternalStorageDirectory().getPath() + "/idheadimg/" + getTime("yyMMddHHmmssSSS") + ".jpg";
    }

    private static String getTime(String format) {
        return new SimpleDateFormat(format).format(new Date());
    }

    /**
     * 权限申请
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void checkPermission() {
        //检查权限（NEED_PERMISSION）是否被授权 PackageManager.PERMISSION_GRANTED表示同意授权
        //登录检查权限
        checkPermissions(AddUserActivity.this, new String[]{
                Manifest.permission.CAMERA}, new PermissionCallBack() {
            @Override
            public void granted() {
                boolean IsswitchString = (boolean) SPUtils.get(AddUserActivity.this, "IsswitchString", false);
                if (IsswitchString == false) {
                    String SDKtime = (String) SPUtils.get(AddUserActivity.this, "SDKtime", "");

                    if (DateUtil.isDate2Bigger(DateUtil.getStringDateShort(), SDKtime) == false) {
                        ToastUtil.showInfo(AddUserActivity.this, "离线识别引擎已过期，请购买新授权");
                    } else {
                        Intent intent = new Intent(AddUserActivity.this, CameraActivity.class);
                        startActivityForResult(intent, 110);
                        overridePendingTransition(R.anim.stop, R.anim.start);
                    }

                } else {
                    if (NetWorkUtil.isConn(AddUserActivity.this) == false) {
                        ToastUtil.showInfo(AddUserActivity.this, "在线ocr识别需要连接网络");
                    } else {
                        opnePhoto();
                    }
                }
            }

            @Override
            public void denied() {
                ToastUtil.showInfo(AddUserActivity.this, "请开通相机权限，否则无法正常使用！");
            }
        });
    }

    /**
     * 初始化radiobutton
     */
    public void initRadiGroup() {
        //性别
        radiogroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.item0_radiobutton_nan:
                        sax_string = "男";
                        break;
                    case R.id.item0_radiobutton_nv:
                        sax_string = "女";
                        break;
                    default:
                        break;
                }
            }
        });
        //文化程度
        wenhua_radio.setOnCheckedChangeListener(new XRadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(XRadioGroup xRadioGroup, int i) {
                switch (i) {
                    case R.id.shushi_radiobutton:
                        WENHUA_CHENGDU = shushi_radiobutton.getText().toString().trim();
                        break;
                    case R.id.daxue_radiobutton:
                        WENHUA_CHENGDU = daxue_radiobutton.getText().toString().trim();
                        break;
                    case R.id.dazhuan_radiobutton:
                        WENHUA_CHENGDU = dazhuan_radiobutton.getText().toString().trim();
                        break;
                    case R.id.jixiao_radiobutton:
                        WENHUA_CHENGDU = jixiao_radiobutton.getText().toString().trim();
                        break;
                    case R.id.gaozhong_radiobutton:
                        WENHUA_CHENGDU = gaozhong_radiobutton.getText().toString().trim();
                        break;
                    case R.id.chuzhong_radiobutton:
                        WENHUA_CHENGDU = chuzhong_radiobutton.getText().toString().trim();
                        break;
                    case R.id.xiaooxue_radiobutton:
                        WENHUA_CHENGDU = xiaooxue_radiobutton.getText().toString().trim();
                        break;
                    case R.id.wenmang_radiobutton:
                        WENHUA_CHENGDU = wenmang_radiobutton.getText().toString().trim();
                        break;
                    case R.id.zhongzhuan_ra:
                        WENHUA_CHENGDU = zhongzhuan_ra.getText().toString().trim();
                    default:
                        break;
                }
            }
        });

        //婚姻状况
        hunyin_ra.setOnCheckedChangeListener(new XRadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(XRadioGroup xRadioGroup, int i) {
                switch (i) {
                    case R.id.weihun_radiobutton:
                        HUNYIN_ZHUANGKUANG = weihun_radiobutton.getText().toString().trim();
                        break;
                    case R.id.sango_radiobutton:
                        HUNYIN_ZHUANGKUANG = sango_radiobutton.getText().toString().trim();
                        break;
                    case R.id.lihun_radiobutton:
                        HUNYIN_ZHUANGKUANG = lihun_radiobutton.getText().toString().trim();
                        break;
                    case R.id.qita_radiobutton:
                        HUNYIN_ZHUANGKUANG = qita_radiobutton.getText().toString().trim();
                        break;
                    case R.id.peio_radiobutton:
                        HUNYIN_ZHUANGKUANG = peio_radiobutton.getText().toString().trim();
                        break;
                    default:
                        break;
                }
            }
        });
        //户籍类型
        hunjie_ra.setOnCheckedChangeListener(new XRadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(XRadioGroup xRadioGroup, int i) {
                switch (i) {
                    case R.id.nongye_radiobutton:
                        HUJI_LEIXING = nongye_radiobutton.getText().toString().trim();
                        break;
                    case R.id.feinongye_radiobutton:
                        HUJI_LEIXING = feinongye_radiobutton.getText().toString().trim();
                        break;
                    case R.id.wuhukou_radiobutton:
                        HUJI_LEIXING = wuhukou_radiobutton.getText().toString().trim();
                        break;
                    default:
                        break;
                }
            }
        });

        //人员性质
        xingzhi_gr.setOnCheckedChangeListener(new XRadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(XRadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.liudong_ren:
                        RENYUANXINGZHI = liudong_ren.getText().toString().trim();
                        break;
                    case R.id.huji_ren:
                        RENYUANXINGZHI = huji_ren.getText().toString().trim();
                        break;
                    case R.id.fenli_ren:
                        RENYUANXINGZHI = fenli_ren.getText().toString().trim();
                        break;
                    default:
                        break;
                }
            }
        });

        //来京原因
        laijing_ra.setOnCheckedChangeListener(new XRadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(XRadioGroup xRadioGroup, int i) {
                switch (i) {
                    case R.id.wugong_radiobutton:
                        LAIJING_YUANYIN = wugong_radiobutton.getText().toString().trim();
                        break;
                    case R.id.wunong_radiobutton:
                        LAIJING_YUANYIN = wunong_radiobutton.getText().toString().trim();
                        break;
                    case R.id.jingshang_radiobutton:
                        LAIJING_YUANYIN = jingshang_radiobutton.getText().toString().trim();

                        break;
                    case R.id.xuexipei_radiobutton:
                        LAIJING_YUANYIN = xuexipei_radiobutton.getText().toString().trim();
                        break;
                    case R.id.suiqian_radiobutton:
                        LAIJING_YUANYIN = suiqian_radiobutton.getText().toString().trim();
                        break;

                    case R.id.qitaa_radiobutton:
                        LAIJING_YUANYIN = qitaa_radiobutton.getText().toString().trim();
                        break;
                    case R.id.hunjia_radiobutton:
                        LAIJING_YUANYIN = hunjia_radiobutton.getText().toString().trim();
                        break;
                    case R.id.zhibing_radiobutton:
                        LAIJING_YUANYIN = zhibing_radiobutton.getText().toString().trim();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public void onItemClick(View view, int position) {
        switch (position) {
            case IMAGE_ITEM_ADD:
                List<String> names = new ArrayList<>();
                names.add("拍照");
                names.add("相册");
                showDialog(new SelectDialog.SelectDialogListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        switch (position) {
                            case 0: // 直接调起相机
                                /**
                                 * 0.4.7 目前直接调起相机不支持裁剪，如果开启裁剪后不会返回图片，请注意，后续版本会解决
                                 *
                                 * 但是当前直接依赖的版本已经解决，考虑到版本改动很少，所以这次没有上传到远程仓库
                                 *
                                 * 如果实在有所需要，请直接下载源码引用。
                                 */
                                //打开选择,本次允许选择的数量
                                com.lzy.imagepicker.ImagePicker.getInstance().setSelectLimit(maxImgCounts - selImageList.size());
                                Intent intent = new Intent(AddUserActivity.this, ImageGridActivity.class);
                                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                                startActivityForResult(intent, REQUEST_CODE_SELECTS);
                                break;
                            case 1:
                                //打开选择,本次允许选择的数量
                                com.lzy.imagepicker.ImagePicker.getInstance().setSelectLimit(maxImgCounts - selImageList.size());
                                Intent intent1 = new Intent(AddUserActivity.this, ImageGridActivity.class);
                                /* 如果需要进入选择的时候显示已经选中的图片，
                                 * 详情请查看ImagePickerActivity
                                 * */
//                                intent1.putExtra(ImageGridActivity.EXTRAS_IMAGES,images);
                                startActivityForResult(intent1, REQUEST_CODE_SELECTS);
                                break;
                            default:
                                break;
                        }

                    }
                }, names);


                break;
            default:
                //打开预览
                Intent intentPreview = new Intent(this, ImagePreviewDelActivity.class);
                intentPreview.putExtra(com.lzy.imagepicker.ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) adapter.getImages());
                intentPreview.putExtra(com.lzy.imagepicker.ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
                intentPreview.putExtra(com.lzy.imagepicker.ImagePicker.EXTRA_FROM_ITEMS, true);
                startActivityForResult(intentPreview, REQUEST_CODE_PREVIEW);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mShowAction != null) {
            mShowAction = null;
        }
        if (mHiddenAction != null) {
            mHiddenAction = null;
        }
    }
}
