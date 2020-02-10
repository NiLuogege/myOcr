package com.example.ruifight_3.saolouruifight.ui;

import android.app.Dialog;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ruifight_3.saolouruifight.MyApi;
import com.example.ruifight_3.saolouruifight.R;
import com.example.ruifight_3.saolouruifight.baseui.BaseActivity;
import com.example.ruifight_3.saolouruifight.db.datamessage.LouMessage;
import com.example.ruifight_3.saolouruifight.db.datamessage.RecordMeanage;
import com.example.ruifight_3.saolouruifight.db.util.FileUtils;
import com.example.ruifight_3.saolouruifight.db.util.bean.RecordBean;
import com.example.ruifight_3.saolouruifight.threadpool.ThreadExecutorService;
import com.example.ruifight_3.saolouruifight.ui.bean.upDataBean;
import com.example.ruifight_3.saolouruifight.util.ConvertUtils;
import com.example.ruifight_3.saolouruifight.util.DateUtil;
import com.example.ruifight_3.saolouruifight.util.DiaLogUtil;
import com.example.ruifight_3.saolouruifight.util.JsonUtils;
import com.example.ruifight_3.saolouruifight.util.NetWorkUtil;
import com.example.ruifight_3.saolouruifight.util.SPUtils;
import com.example.ruifight_3.saolouruifight.util.ToastUtil;
import com.example.ruifight_3.saolouruifight.widget.CommomDialog;
import com.example.zhouwei.library.CustomPopWindow;
import com.github.library.BaseRecyclerAdapter;
import com.github.library.BaseViewHolder;
import com.github.library.listener.OnRecyclerItemLongClickListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Response;

public class UploadActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.upload_recycleview)
    RecyclerView upload_recycleview;
    @BindView(R.id.commit_imt)
    Button commit_imt;
    @BindView(R.id.nodata_text)
    TextView nodata_text;
    private Handler handler = new Handler();
    @BindView(R.id.login_nex_no_rl)
    RelativeLayout login_nex_no_rl;
    @BindView(R.id.time_activity_uplo)
    TextView time_activity_uplo;
    private RecordMeanage recordMeanage;
    private BaseRecyclerAdapter adapter;
    private CustomPopWindow mCustomPopWindow;
    private LouMessage louMessage;

    @Override
    protected int setLayout() {
        return R.layout.activity_upload;
    }

    @Override
    protected void initView() {
        setSteepStatusBar(true);
        setTooBarBreakImage(R.mipmap.breakimagee);
        setTooBarTitleText("离线采集上传");
        commit_imt.setOnClickListener(this);
        upload_recycleview.setLayoutManager(new LinearLayoutManager(UploadActivity.this));

    }

    @Override
    protected void initData() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                /**
                 * sp 取值
                 */
                String downloadDataTime = (String) SPUtils.get(UploadActivity.this, SPUtils.get(UploadActivity.this, "welcomeUserName", "") + "_UploaDdata", "null");
                if (!("null").equals(downloadDataTime)) {
                    time_activity_uplo.setVisibility(View.VISIBLE);
                    time_activity_uplo.setText(ConvertUtils.setNumColor(getString(R.string.uplo) + downloadDataTime));
                } else {
                    time_activity_uplo.setVisibility(View.GONE);
                }

                try {
                    if (louMessage == null) {
                        louMessage = new LouMessage(UploadActivity.this);

                    }
                    if (FileUtils.fileIsExists(MyApi.FileLoad + MyApi.DBNAME) == true) {

                        if (louMessage.tabbleIsExist("t_jzw_jlx") == true) {
                            if (recordMeanage == null) {
                                recordMeanage = new RecordMeanage(UploadActivity.this);
                            }
                            //recordMeanage.deleTabData();
                            //查询记录表
                            List<RecordBean> list = new ArrayList<>();
                            list = recordMeanage.selectRecord();

                            if (list != null && list.size() > 0) {
                                //查看
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        nodata_text.setVisibility(View.GONE);
                                        commit_imt.setVisibility(View.VISIBLE);
                                    }
                                });
                                upData();
                                initRecycleView(list);
                            } else {
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        nodata_text.setVisibility(View.VISIBLE);
                                        commit_imt.setVisibility(View.GONE);
                                        nodata_text.setText("暂无数据需要上传");
                                    }
                                });
                            }

                        } else {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    nodata_text.setVisibility(View.VISIBLE);
                                    commit_imt.setVisibility(View.GONE);
                                    nodata_text.setText("请先下载离线数据");
                                }
                            });
                        }
                    } else {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                nodata_text.setVisibility(View.VISIBLE);
                                commit_imt.setVisibility(View.GONE);
                                nodata_text.setText("请先下载离线数据");
                            }
                        });
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        ThreadExecutorService.newCachedThreadPool(1).execute(runnable);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.commit_imt:
                if (louMessage == null) {
                    louMessage = new LouMessage(UploadActivity.this);
                }

                if (FileUtils.fileIsExists(MyApi.FileLoad + MyApi.DBNAME) == true) {

                    if (louMessage.tabbleIsExist("t_jzw_jlx") == true) {
                        if (recordMeanage == null) {
                            recordMeanage = new RecordMeanage(UploadActivity.this);
                        }
                        //recordMeanage.deleTabData();
                        //查询记录表
                        List<RecordBean> list = new ArrayList<>();
                        list = recordMeanage.selectRecord();


                        if (NetWorkUtil.isConn(UploadActivity.this) == false) {
                            ToastUtil.showInfo(UploadActivity.this, "失败 请连接网络");
                        } else {
                            if (list != null && list.size() > 0) {
                                //上传
                                updaDate(list);
                            } else {
                                ToastUtil.showInfo(UploadActivity.this, "暂无数据需要上传");
                            }
                        }
                    } else {
                        ToastUtil.showInfo(UploadActivity.this, "请先下载离线数据");
                    }
                } else {
                    ToastUtil.showInfo(UploadActivity.this, "请先下载离线数据");
                }
                break;
            default:
                break;
        }
    }

    /**
     * 上传josn数据
     *
     * @param list
     */
    public void updaDate(List<RecordBean> list) {
        DiaLogUtil.showDiaLog(UploadActivity.this, "正在上传");
        OkHttpClient client = new OkHttpClient();
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        JSONObject tmpObj = null;
        try {
            int count = list.size();
            for (int i = 0; i < count; i++) {
                tmpObj = new JSONObject();
                tmpObj.put("type", list.get(i).getType());
                tmpObj.put("statement", list.get(i).getStatement());
                tmpObj.put("operateDate", list.get(i).getOperateDate());
                tmpObj.put("operateUser", list.get(i).getOperateUser());
                tmpObj.put("tableName", list.get(i).getTableName());
                jsonArray.put(tmpObj);
                tmpObj = null;
            }
            //  jsonObject.put("jsonData" ,personInfos);   // 获得JSONObject的String
        } catch (Exception e) {
            e.printStackTrace();
        }
        String personInfos = jsonArray.toString(); // 将JSONArray转换得到String
        okhttp3.RequestBody body = okhttp3.RequestBody.create(MyApi.CONTENT_TYPE, personInfos);
        final okhttp3.Request request = new okhttp3.Request.Builder()
                .url(MyApi.URL + MyApi.UPDA)
                .post(body)
                .build();
        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showInfo(UploadActivity.this, "连接超时 请重试");
                        DiaLogUtil.dismissDiaLog();
                    }
                });
            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // Log.e("response", "response=====" + response);
                try {
                    final upDataBean updateBean = (upDataBean) JsonUtils.stringToObject(response.body().string(), upDataBean.class);

                    if (updateBean.isStatus() == false) {

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                DiaLogUtil.dismissDiaLog();
                                ToastUtil.showInfo(UploadActivity.this, updateBean.getData() != null ? updateBean.getData() + "" : "");
                            }
                        });
                        if (recordMeanage == null) {
                            recordMeanage = new RecordMeanage(UploadActivity.this);
                        }
                        if (!"删除失败".contains(updateBean.getMsg() + "")) {
                            recordMeanage.deleteType("delete");
                        }
                        if (!"修改失败".contains(updateBean.getMsg() + "")) {
                            recordMeanage.deleteType("update");
                        }
                        if (!"添加失败".contains(updateBean.getMsg() + "")) {
                            recordMeanage.deleteType("insert");
                        }
                        selectData(recordMeanage);

                    } else {
                        //存入最近上传日期
                        SPUtils.put(UploadActivity.this, SPUtils.get(UploadActivity.this, "welcomeUserName", "") + "_UploaDdata", DateUtil.getDate());
                        SPUtils.remove(UploadActivity.this, "updateIs");
                        //成功后 开始清表  暂时清记录表
                        if (recordMeanage == null) {
                            recordMeanage = new RecordMeanage(UploadActivity.this);
                        }
                        recordMeanage.deleTabData();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                DiaLogUtil.dismissDiaLog();
                                upload_recycleview.setVisibility(View.GONE);
                                commit_imt.setVisibility(View.GONE);
                                nodata_text.setVisibility(View.VISIBLE);
                                nodata_text.setText("上传成功");
                                ToastUtil.showInfo(UploadActivity.this, "上传成功");
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    setLogin();
                }
            }
        });
    }

    public void initRecycleView(final List<RecordBean> list) {
        if (adapter != null) {
            adapter.setData(list);
        } else {
            adapter = new BaseRecyclerAdapter<RecordBean>(UploadActivity.this, list, R.layout.itme_upload) {
                @Override
                protected void convert(BaseViewHolder helper, RecordBean item) {
                    final int layoutPosition = helper.getLayoutPosition();
                    helper.setText(R.id.name_tv, "录入人: " + item.getOperateUser());
                    helper.setText(R.id.zhuangtai_tv, "录入状态: " + "可上传");
                    switch (item.getType()) {
                        case "insert":
                            helper.setText(R.id.update_type, "录入类型: 添加操作");
                            break;
                        case "delete":
                            helper.setText(R.id.update_type, "录入类型: 删除操作");
                            break;
                        case "update":
                            helper.setText(R.id.update_type, "录入类型: 修改操作");
                            break;
                        default:
                            break;
                    }

                    helper.setOnClickListener(R.id.button_delete, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            new CommomDialog(mContext, R.style.dialog, "您确定要删除这条数据吗", new CommomDialog.OnCloseListener() {
                                @Override
                                public void onClick(Dialog dialog, boolean confirm) {
                                    if (confirm) {
                                        if (recordMeanage == null) {
                                            recordMeanage = new RecordMeanage(UploadActivity.this);
                                            recordMeanage.deleteItemData(list.get(layoutPosition).getId() + "");
                                            if (layoutPosition < list.size()) {
                                                list.remove(layoutPosition);
                                            }
                                            adapter.notifyDataSetChanged();
                                            upData();
                                        } else {
                                            recordMeanage.deleteItemData(list.get(layoutPosition).getId() + "");
                                            if (layoutPosition < list.size()) {
                                                list.remove(layoutPosition);
                                            }
                                            if (adapter != null) {
                                                adapter.notifyDataSetChanged();
                                                upData();
                                            }
                                        }
                                        dialog.dismiss();
                                    }
                                }
                            }).setTitle("提示").show();
                        }
                    });
                }
            };
            upload_recycleview.setAdapter(adapter);

            adapter.setOnRecyclerItemLongClickListener(new OnRecyclerItemLongClickListener() {
                @Override
                public boolean onItemLongClick(View view, int position) {
                    ShouPopWindow(list.get(position).getStatement());
                    return true;
                }
            });
        }
    }

    @Override
    public void onNetChange(int netMobile) {
        if (netMobile == 1) {
            //ToastUtil.showInfo(this, "有网络wifi");
            login_nex_no_rl.setVisibility(View.GONE);
        } else if (netMobile == -1) {
            //ToastUtil.showInfo(this, "没有网络");
            login_nex_no_rl.setVisibility(View.VISIBLE);
        } else if (netMobile == 0) {
            //ToastUtil.showInfo(this, "4G");
            login_nex_no_rl.setVisibility(View.GONE);
        }
    }

    /**
     * 判断有网  如果有操作记录提示用户上传
     */
    public void upData() {

        if (FileUtils.fileIsExists(MyApi.FileLoad + MyApi.DBNAME) == true) {
            if (louMessage == null) {
                louMessage = new LouMessage(UploadActivity.this);
            }
            if (louMessage.tabbleIsExist("t_jzw_jlx") == true) {
                if (recordMeanage == null) {
                    recordMeanage = new RecordMeanage(UploadActivity.this);
                }
                //查询记录表
                List<RecordBean> list = new ArrayList<>();
                list = recordMeanage.selectRecord();
                if (list != null && list.size() > 0) {
                    //查询记录表条数
                    final Long count = recordMeanage.recordData();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            commit_imt.setText(ConvertUtils.setNumColor("上传采集数据 " + count + ""));
                        }
                    });
                } else {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            commit_imt.setText("无数据需要上传");
                        }
                    });
                }
            }
        }
    }

    public void ShouPopWindow(String content) {
        View contentView = LayoutInflater.from(this).inflate(R.layout.itme_uplad_layout, null);
        //处理popWindow 显示内容
        handleLogics(contentView, content);
        //创建并显示popWindow
        mCustomPopWindow = new CustomPopWindow.PopupWindowBuilder(this)
                .setView(contentView)
                .setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED)
                .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
                .create()
                .showAtLocation(contentView, Gravity.CENTER, 0, 0);

    }

    public void handleLogics(View view, String content) {
        TextView textView = view.findViewById(R.id.guanbi_tv_update);
        TextView textView1 = view.findViewById(R.id.upload_tv);
        textView1.setText(content != null ? content : "无法找到信息");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCustomPopWindow.dissmiss();
            }
        });
    }

    public void selectData(RecordMeanage recordMeanage) {
        //查询记录表
        List<RecordBean> list = new ArrayList<>();
        list = recordMeanage.selectRecord();
        if (list != null && list.size() > 0) {
            upData();
            initRecycleView(list);
        }
    }
}
