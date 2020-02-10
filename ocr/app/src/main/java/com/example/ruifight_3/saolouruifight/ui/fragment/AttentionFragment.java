package com.example.ruifight_3.saolouruifight.ui.fragment;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ruifight_3.saolouruifight.MyApi;
import com.example.ruifight_3.saolouruifight.R;
import com.example.ruifight_3.saolouruifight.baseui.LanBaseFragment;
import com.example.ruifight_3.saolouruifight.ui.ChuLiAttentionActivity;
import com.example.ruifight_3.saolouruifight.ui.LoginHomeActivity;
import com.example.ruifight_3.saolouruifight.ui.TaskCompleteActivity;
import com.example.ruifight_3.saolouruifight.ui.bean.GuanZhuBean;
import com.example.ruifight_3.saolouruifight.util.JsonUtils;
import com.example.ruifight_3.saolouruifight.util.NetWorkUtil;
import com.example.ruifight_3.saolouruifight.util.ToastUtil;
import com.github.library.BaseRecyclerAdapter;
import com.github.library.BaseViewHolder;
import com.github.library.listener.OnRecyclerItemClickListener;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RuiFight-3 on 2018/5/12.
 */

public class AttentionFragment extends LanBaseFragment {
    // 标志位，标志已经初始化完成。
    private boolean isPrepared;
    private RecyclerView recyclerView;
    private int data;
    private RelativeLayout relativeLayout;
    private ImageView no_image;
    private TextView no_textview;
    private BaseRecyclerAdapter adapter;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 1:
                    GuanZhuBean guanZhuBean = (GuanZhuBean) msg.obj;

                    final List<GuanZhuBean.DataBean> list = new ArrayList<>();

                    if (guanZhuBean.getData() != null && guanZhuBean.getData().size() > 0) {

                        if (data == 1) {
                            for (int i = 0; i < guanZhuBean.getData().size(); i++) {
                                if (guanZhuBean.getData().get(i).getState() == 1) {
                                    list.add(new GuanZhuBean.DataBean(guanZhuBean.getData().get(i).getId(), guanZhuBean.getData().get(i).getHuId(), guanZhuBean.getData().get(i).getJzwDm(), guanZhuBean.getData().get(i).getCreateDate(),
                                            guanZhuBean.getData().get(i).getFoundUserId(), guanZhuBean.getData().get(i).getFoundUserDepartmentCode(), guanZhuBean.getData().get(i).getFoundUserName(), guanZhuBean.getData().get(i).getJzwDizhi()
                                            , guanZhuBean.getData().get(i).getGrade(), guanZhuBean.getData().get(i).getExecUserId(), guanZhuBean.getData().get(i).getExecUserDepartmentCode(), guanZhuBean.getData().get(i).getExecUserName(), guanZhuBean.getData().get(i).getContent(), guanZhuBean.getData().get(i).getImg()
                                            , guanZhuBean.getData().get(i).getOverDate(), guanZhuBean.getData().get(i).getState(), guanZhuBean.getData().get(i).getMarkCause()));
                                }
                            }
                            if (list.size() == 0) {
                                recyclerView.setVisibility(View.GONE);
                                relativeLayout.setVisibility(View.VISIBLE);
                                no_textview.setText("暂无任务");
                                no_image.setBackground(ContextCompat.getDrawable(getMyActivity(),R.drawable.wurenwu));
                            } else {
                                recyclerView.setVisibility(View.VISIBLE);
                                relativeLayout.setVisibility(View.GONE);
                            }

                        } else {
                            for (int i = 0; i < guanZhuBean.getData().size(); i++) {
                                if (guanZhuBean.getData().get(i).getState() == 2) {
                                    list.add(new GuanZhuBean.DataBean(guanZhuBean.getData().get(i).getId(), guanZhuBean.getData().get(i).getHuId(), guanZhuBean.getData().get(i).getJzwDm(), guanZhuBean.getData().get(i).getCreateDate(),
                                            guanZhuBean.getData().get(i).getFoundUserId(), guanZhuBean.getData().get(i).getFoundUserDepartmentCode(), guanZhuBean.getData().get(i).getFoundUserName(), guanZhuBean.getData().get(i).getJzwDizhi()
                                            , guanZhuBean.getData().get(i).getGrade(), guanZhuBean.getData().get(i).getExecUserId(), guanZhuBean.getData().get(i).getExecUserDepartmentCode(), guanZhuBean.getData().get(i).getExecUserName(), guanZhuBean.getData().get(i).getContent(), guanZhuBean.getData().get(i).getImg()
                                            , guanZhuBean.getData().get(i).getOverDate(), guanZhuBean.getData().get(i).getState(), guanZhuBean.getData().get(i).getMarkCause()));

                                }
                            }
                            if (list.size() == 0) {
                                recyclerView.setVisibility(View.GONE);
                                relativeLayout.setVisibility(View.VISIBLE);
                                no_textview.setText("暂无完成任务");
                                no_image.setBackground(ContextCompat.getDrawable(getMyActivity(),R.drawable.wurenwu));
                            } else {
                                recyclerView.setVisibility(View.VISIBLE);
                                relativeLayout.setVisibility(View.GONE);
                            }
                        }
                        try {
                            if (adapter == null) {
                                adapter = new BaseRecyclerAdapter<GuanZhuBean.DataBean>(getMyActivity(), list, R.layout.item_attention) {
                                    @Override
                                    protected void convert(BaseViewHolder helper, GuanZhuBean.DataBean item) {
                                        if (item.getState() == 1) {
                                            helper.setBackgroundRes(R.id.main_recycle_item_im, R.mipmap.renwua);
                                        } else {
                                            helper.setBackgroundRes(R.id.main_recycle_item_im, R.mipmap.jieshou);
                                        }
                                        helper.setText(R.id.main_recycle_item_tv, item.getJzwDizhi());
                                        if (item.getMarkCause() != null) {
                                            helper.setText(R.id.yuanyin_tv, item.getMarkCause());
                                        }
                                    }
                                };
                                recyclerView.setAdapter(adapter);
                            } else {
                                adapter.setData(list);
                            }
                            adapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    if (data == 1 && list.get(position).getState() == 1) {
                                        //处理任务
                                        Intent intent = new Intent(getMyActivity(), ChuLiAttentionActivity.class);
                                        //关注原因
                                        intent.putExtra("housemark", list.get(position).getMarkCause());
                                        //关注id
                                        intent.putExtra("followHouseId", list.get(position).getId() + "");
                                        startActivity(intent);
                                    } else {
                                        //查看任务详情
                                        Intent intent2 = new Intent(getMyActivity(), TaskCompleteActivity.class);
                                        intent2.putExtra("followHouseId", list.get(position).getId());
                                        startActivity(intent2);

                                    }
                                }
                            });

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        recyclerView.setVisibility(View.GONE);
                        relativeLayout.setVisibility(View.VISIBLE);
                        no_textview.setText("暂无任务");
                        no_image.setBackground(ContextCompat.getDrawable(getMyActivity(),R.drawable.wurenwu));
                    }
                    break;
                default:
                    break;
            }
        }
    };


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onLazyLoad() {
        //填充各控件的数据
        initData();
    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_attention, container, false);
        //XXX初始化view的各控件
        recyclerView = view.findViewById(R.id.att_recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getMyActivity()));
        relativeLayout = view.findViewById(R.id.wurenwu_relativekayout);
        no_image = view.findViewById(R.id.no_image);
        no_textview = view.findViewById(R.id.no_textview);
        return view;
    }

    @Override
    public void initEvent() {

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void initData() {
        Bundle bundle = getArguments();
        data = bundle.getInt("state");
        Log.e("sss", "sss" + data);
        try {
            if (NetWorkUtil.isConn(getMyActivity()) == false) {
                //没有网
                recyclerView.setVisibility(View.GONE);
                relativeLayout.setVisibility(View.VISIBLE);
                no_textview.setText("无网络连接");
                no_image.setBackground(ContextCompat.getDrawable(getMyActivity(),R.drawable.nowangluo));
            } else {
                //获取数据
                recyclerView.setVisibility(View.VISIBLE);
                relativeLayout.setVisibility(View.GONE);
                getGuanMess(data);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void getGuanMess(int state) {

        OkHttpUtils
                .post()
                .url(MyApi.URL + MyApi.GUANZHURENWU)
                .tag(getMyActivity())
                .addParams("", "")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        ToastUtil.showInfo(getMyActivity(), e.getMessage() + "onError");
                    }

                    @Override
                    public void onResponse(String response) {
                        //Log.e(TAG, "guanzhu+++  " + response);
                        try {
                            GuanZhuBean guanZhuBean = (GuanZhuBean) JsonUtils.stringToObject(response, GuanZhuBean.class);
                            Message msg = new Message();
                            msg.what = 1;
                            msg.obj = guanZhuBean;
                            handler.sendMessage(msg);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Intent intent = new Intent(getMyActivity(), LoginHomeActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);//这样Task中只剩LoginHomeActivity
                            startActivity(intent);
                            getMyActivity().overridePendingTransition(R.anim.stop, R.anim.start);
                        }
                    }
                });

    }

    @Override
    public void onResume() {
        super.onResume();
        if (NetWorkUtil.isConn(getMyActivity()) == true) {
            getGuanMess(data);
        }
    }
}
