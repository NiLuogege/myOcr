package com.example.ruifight_3.saolouruifight.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.ruifight_3.saolouruifight.MyApi;
import com.example.ruifight_3.saolouruifight.R;
import com.example.ruifight_3.saolouruifight.ui.AddHouseMessageActivity;
import com.example.ruifight_3.saolouruifight.ui.bean.DanYuanBean;
import com.example.ruifight_3.saolouruifight.util.ToastUtil;
import com.example.ruifight_3.saolouruifight.widget.CustomRecyclerView;
import com.example.ruifight_3.saolouruifight.widget.MyGridLayoutManager;
import com.example.zhouwei.library.CustomPopWindow;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;


/**
 * Created by RuiFight-3 on 2018/4/19.
 * 单元adapter
 */

public class HouseMessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<DanYuanBean.DataBean.DyDetailBean.CengListBean> list;

    private String addressname; //头部地址
    private String floorCode;//编号
    private String jiedaoCode;//街路巷编码
    private String jzw_bm;
    private int data;//单元号
    private CustomPopWindow mCustomPopWindow;
    private XXListener mXXListener;

    /**
     * item点击事件接口，
     */
    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickListener mOnitemClickListener;

    public OnItemClickListener getmOnitemClickListener() {
        return mOnitemClickListener;
    }

    public void setmOnitemClickListener(OnItemClickListener mOnitemClickListener) {
        this.mOnitemClickListener = mOnitemClickListener;
    }

    public void setOnXXClickListener(XXListener XXListener) {
        this.mXXListener = XXListener;
    }

    public HouseMessageAdapter(Context mContext, List<DanYuanBean.DataBean.DyDetailBean.CengListBean> list, String floorCode, String jiedaoCode, String addressname, String jzw_bm, int data) {
        this.mContext = mContext;
        this.list = list;
        this.floorCode = floorCode;
        this.jiedaoCode = jiedaoCode;
        this.addressname = addressname;
        this.jzw_bm = jzw_bm;
        this.data = data;
        try {
            mLayoutInflater = LayoutInflater.from(mContext);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override

    public MyHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mLayoutInflater.inflate(R.layout.item_house_message_adapter, parent, false);
        MyHolders holder = new MyHolders(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyHolders viewholder = (MyHolders) holder;

        viewholder.lou_count_tv.setText(list.get(position).getCengShu() + " 层");

        viewholder.lou_count_tv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (!"0".equals(MyApi.ISHOMELOGIN)) {
                    View contentView = LayoutInflater.from(mContext).inflate(R.layout.item_popwindow_adapter, null);
                    //处理popWindow 显示内容
                    handleLogic(contentView, position);
                    //创建并显示popWindow
                    mCustomPopWindow = new CustomPopWindow.PopupWindowBuilder(mContext)
                            .setView(contentView)
                            .setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED)
                            .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
                            .create()
                            .showAtLocation(contentView, Gravity.CENTER, 0, 0);
                }
                return true;
            }
        });

        MyGridLayoutManager gridLayoutManager = new MyGridLayoutManager(mContext, 4);
        gridLayoutManager.setScrollEnabled(false);
        viewholder.recyclerView.setNestedScrollingEnabled(false);
        viewholder.recyclerView.setLayoutManager(gridLayoutManager);

//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
//        viewholder.recyclerView.setLayoutManager(linearLayoutManager);

        final List<DanYuanBean.DataBean.DyDetailBean.CengListBean.FjListBean> lists = list.get(position).getFjList();

        HouseCountAdapter houseCountAdapter = new HouseCountAdapter(mContext, lists);
        viewholder.recyclerView.setAdapter(houseCountAdapter);

        houseCountAdapter.setmOnitemClickListener(new HouseCountAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                //ToastUtil.showInfo(mContext,"点了"+lists.get(position).getHubm());
                Intent intent = new Intent(mContext, AddHouseMessageActivity.class);
                //建筑物编码
                intent.putExtra("floorCode", floorCode);
                //街路巷编码
                intent.putExtra("jiedaoCode", jiedaoCode);
                //房间id
                intent.putExtra("Huid", lists.get(position).getHuid());
                //头部地址
                intent.putExtra("addressname", addressname + "< " + lists.get(position).getHubm());
                mContext.startActivity(intent);

            }

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onItemLongClick(View view, int positionu) {

                if (!"0".equals(MyApi.ISHOMELOGIN)) {
                    View contentView = LayoutInflater.from(mContext).inflate(R.layout.item_popwindow_count, null);
                    //处理popWindow 显示内容
                    if (position < list.size()) {
                        if (positionu < lists.size()) {
                            handleLogict(contentView, list.get(position).getCengShu(), lists.get(positionu).getHubm(), lists.get(positionu).getHuid(), lists, positionu);
                        }
                    }
                    //创建并显示popWindow
                    mCustomPopWindow = new CustomPopWindow.PopupWindowBuilder(mContext)
                            .setView(contentView)
                            .setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED)
                            .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
                            .create()
                            .showAtLocation(contentView, Gravity.CENTER, 0, 0);
                }
            }
        });
//        viewholder.lou_count_tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //cengAdd(list.get(position).getFloor_code(),list.get(position).getUnit(),list.get(position).getLayer(),position);
//            }
//        });

        setUpItemEvent(viewholder);

    }

    /**
     * 点击事件回调接口
     *
     * @param holder
     */
    protected void setUpItemEvent(final MyHolders holder) {
        if (mOnitemClickListener != null) {
            holder.lou_count_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //获取最新的item位置
                    int layoutPosition = holder.getLayoutPosition();
                    mOnitemClickListener.onItemClick(holder.lou_count_tv, layoutPosition);
                }
            });

            holder.lou_count_tv.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    //获取最新的item位置
                    int layoutPosition = holder.getLayoutPosition();
                    mOnitemClickListener.onItemLongClick(holder.lou_count_tv, layoutPosition);
                    return false;
                }
            });
        }
    }

    public void handleLogict(View contentView, int ceng, final String getHubm, final String huid, final List<DanYuanBean.DataBean.DyDetailBean.CengListBean.FjListBean> lists, final int position) {

        TextView danyuan_tv_count = contentView.findViewById(R.id.danyuan_tv_count);
        TextView guanbi_tv_count = contentView.findViewById(R.id.guanbi_tv_count);
        final EditText et_danyuan_ceng_count = contentView.findViewById(R.id.et_danyuan_ceng_count);
        Button ceng_button_count = contentView.findViewById(R.id.ceng_button_count);
        final Button delete_button_count = contentView.findViewById(R.id.delete_button_count);
        et_danyuan_ceng_count.setText(getHubm + "");

        delete_button_count.setText("点击删除" + getHubm + "房间");
        danyuan_tv_count.setText(data + "单元" + ceng + "层" + getHubm + "");


        //修改房间号
        ceng_button_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCustomPopWindow.dissmiss();
                updateHomeCode(huid, et_danyuan_ceng_count.getText().toString().trim(), lists, position);

            }
        });

        //删除房间
        delete_button_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCustomPopWindow.dissmiss();
                deleteHu(huid, lists, position);
            }
        });

        guanbi_tv_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCustomPopWindow.dissmiss();
            }
        });
    }


    public void handleLogic(View contentView, final int position) {

        TextView button = contentView.findViewById(R.id.guanbi_tv_adapter);
        Button ceng_button_number = contentView.findViewById(R.id.ceng_button_number);
        Button hu_commit_bu = contentView.findViewById(R.id.hu_commit_bu);
        Button delete_button_number = contentView.findViewById(R.id.delete_button_number);
        TextView danyuan_tv_a = contentView.findViewById(R.id.danyuan_tv_a);
        final EditText et_danyuan_ceng_adapter = contentView.findViewById(R.id.et_danyuan_ceng_adapter);

        if (position < list.size()) {
            et_danyuan_ceng_adapter.setText(String.valueOf(list.get(position).getCengShu()));//d
            danyuan_tv_a.setText(String.valueOf(data) + "单元" + String.valueOf(list.get(position).getCengShu()) + "层");
        }
        //修改层号提交
        ceng_button_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCustomPopWindow.dissmiss();
                if (position < list.size()) {
                    updateCengNumber(jzw_bm, data, list.get(position).getCengShu() + "", et_danyuan_ceng_adapter.getText().toString().trim(), position);
                }
            }
        });
        //添加户数
        hu_commit_bu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCustomPopWindow.dissmiss();
                if (position < list.size()) {
                    updateHu(jzw_bm, data, list.get(position).getCengShu() + "", position);
                }
            }
        });
        //删除层
        delete_button_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCustomPopWindow.dissmiss();
                if (position < list.size()) {
                    deleteCeng(jzw_bm, data, list.get(position).getCengShu(), position);
                }
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCustomPopWindow.dissmiss();
            }
        });
    }

    public void setData(List<DanYuanBean.DataBean.DyDetailBean.CengListBean> list2, String jzw_bm, int data) {
        this.list = list2;
        this.jzw_bm = jzw_bm;
        this.data = data;
        notifyDataSetChanged();
    }

    public void addData(int pos) {
        //list.add(pos, "insert one");
        // notifyDataSetChanged();这里不用这个
        notifyItemInserted(pos);
    }

    public void deleteData(int pos) {
        //  mDatas.remove(pos);

        notifyItemRemoved(pos);
    }

    @Override
    public int getItemCount() {
        return list.size();

    }

    /**
     * 删除房间
     */
    public void deleteHu(String huid, final List<DanYuanBean.DataBean.DyDetailBean.CengListBean.FjListBean> lis, final int position) {
        OkHttpUtils
                .post()
                .url(MyApi.URL + MyApi.DELETEFANGWU + huid)
                .tag(mContext)
                .addParams("", "")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        ToastUtil.showInfo(mContext, "无法连接服务器");
                    }

                    @Override
                    public void onResponse(String response) {
                        if (response.equals("ok")) {
                            if (position < lis.size()) {
                                lis.remove(position);
                            }
                            notifyDataSetChanged();
                            ToastUtil.showInfo(mContext, "删除成功");
                        } else {
                            ToastUtil.showInfo(mContext, "删除失败");
                        }
                    }
                });


    }

    /**
     * 修改房间号
     *
     * @param huid
     * @param newHubm
     */

    public void updateHomeCode(String huid, final String newHubm, final List<DanYuanBean.DataBean.DyDetailBean.CengListBean.FjListBean> lists, final int position) {
        OkHttpUtils
                .post()
                .url(MyApi.URL + MyApi.UPDATEHUID + huid)
                .tag(mContext)
                .addParams("newHubm", newHubm)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        ToastUtil.showInfo(mContext, "无法连接服务器");
                    }

                    @Override
                    public void onResponse(String response) {
                        if (response.equals("ok")) {
                            if (position < lists.size()) {
                                lists.get(position).setHubm(newHubm);
                            }
                            notifyDataSetChanged();
//                            mXXListener.onXXClick();
                            ToastUtil.showInfo(mContext, "修改成功");
                        } else {
                            ToastUtil.showInfo(mContext, "修改失败");
                        }
                    }
                });

    }

    /**
     * 删除层
     */
    public void deleteCeng(String jzw_bm, int dyh, int cs, final int position) {
        OkHttpUtils
                .post()
                .url(MyApi.URL + MyApi.DELETECENG + jzw_bm)
                .tag(mContext)
                .addParams("dys", dyh + "")
                .addParams("cs", cs + "")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        ToastUtil.showInfo(mContext, "无法连接服务器");
                    }

                    @Override
                    public void onResponse(String response) {
                        if (response.equals("ok")) {
                            list.remove(position);
                            notifyDataSetChanged();
//                            mXXListener.onXXClick();
                            ToastUtil.showInfo(mContext, "删除成功");
                        } else {
                            ToastUtil.showInfo(mContext, "删除失败");
                        }
                    }
                });
    }

    /**
     * 修改层数
     */
    public void updateCengNumber(String jzw_bm, int dyh, String cs, final String newCs, final int position) {
        OkHttpUtils
                .post()
                .url(MyApi.URL + MyApi.UPDATECENG + jzw_bm)
                .tag(mContext)
                .addParams("dyh", dyh + "")
                .addParams("cs", cs)
                .addParams("newCs", newCs)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        ToastUtil.showInfo(mContext, "无法连接服务器");
                    }

                    @Override
                    public void onResponse(String response) {
                        if (response.equals("ok")) {
                            list.get(position).setCengShu(Integer.parseInt(newCs));
                            notifyDataSetChanged();
//                            mXXListener.onXXClick();
                            ToastUtil.showInfo(mContext, "修改成功");
                        } else {
                            ToastUtil.showInfo(mContext, "修改失败");
                        }
                    }
                });
    }

    /**
     * 添加户数
     */
    public void updateHu(String jzw_bm, int dyh, String cs, final int position) {
        OkHttpUtils
                .post()
                .url(MyApi.URL + MyApi.UPDATEHU + jzw_bm)
                .tag(mContext)
                .addParams("dyh", dyh + "")
                .addParams("cs", cs)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        ToastUtil.showInfo(mContext, "无法连接服务器");
                    }

                    @Override
                    public void onResponse(String response) {
                        if (response.equals("ok")) {
                            ToastUtil.showInfo(mContext, "添加成功");
                            mXXListener.onXXClick();
                        } else {
                            ToastUtil.showInfo(mContext, "添加失败");
                        }
                    }
                });
    }

    public interface XXListener {
        public void onXXClick();
    }
}


class MyHolders extends RecyclerView.ViewHolder {
    TextView lou_count_tv;
    CustomRecyclerView recyclerView;

    public MyHolders(View itemView) {
        super(itemView);

        recyclerView = itemView.findViewById(R.id.house_recycle);
        lou_count_tv = itemView.findViewById(R.id.lou_count_tv);
    }
}