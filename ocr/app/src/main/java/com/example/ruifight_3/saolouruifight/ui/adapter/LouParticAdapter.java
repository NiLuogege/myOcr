package com.example.ruifight_3.saolouruifight.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ruifight_3.saolouruifight.R;
import com.example.ruifight_3.saolouruifight.ui.AddFangZhuActivity;
import com.example.ruifight_3.saolouruifight.ui.AddUserActivity;
import com.example.ruifight_3.saolouruifight.ui.bean.AddHouseBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by RuiFight-3 on 2018/4/23.
 */

public class LouParticAdapter extends RecyclerView.Adapter {

    private int ITEM_TYPE_IMAGE = 1;
    private int ITEM_TYPE_TEXT = 2;

    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private String floorCode;//建筑物
    private String jiedaoCode;//街道
    private AddHouseBean addHouseBean;
    private String fz_id;
    private String addressname;//title 名字
    private String addressUp;
    //判断房主是添加 还是编辑
    private String houseType = null;
    private ZuHuAdapter zuHuAdapter;

    public LouParticAdapter(Context context, String floorCode, String jiedaoCode, String fz_id, String addressname, AddHouseBean addHouseBean) {
        this.mContext = context;
        this.floorCode = floorCode;
        this.jiedaoCode = jiedaoCode;
        this.addHouseBean = addHouseBean;
        this.fz_id = fz_id;
        this.addressname = addressname;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void setData(AddHouseBean addHouseBean) {
        this.addHouseBean = addHouseBean;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //还有 itme_lou_particulars_item0
        if (viewType == ITEM_TYPE_IMAGE) {
            return new item1Holder(mLayoutInflater.inflate(R.layout.itme_lou_particulars_item1, parent, false));
        } else {
            return new ImageViewHolder(mLayoutInflater.inflate(R.layout.item_lot_particulars_item2, parent, false));
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        /**
         * 房主
         */
        if (holder instanceof item1Holder) {
            if (addHouseBean.getData().getFangjian() != null && addHouseBean.getData().getFangjian().getFz_xm() != null) {
                ((item1Holder) holder).fz_name.setText(addHouseBean.getData().getFangjian().getFz_xm());
            }
            if (addHouseBean.getData().getFangjian() != null && addHouseBean.getData().getFangjian().getFz_fwjzlx() != null) {
                ((item1Holder) holder).fz_type.setText(addHouseBean.getData().getFangjian().getFz_fwjzlx());
                houseType = "YES";
            } else {
                ((item1Holder) holder).fz_type.setText("请录入房主信息");
                ((item1Holder) holder).fangzhu_commit_button.setBackground(ContextCompat.getDrawable(mContext, R.mipmap.tianjia));
                houseType = "NO";
            }

            ((item1Holder) holder).fangzhu_commit_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (houseType.equals("YES")) {
                        //编辑
                        Intent intent = new Intent(mContext, AddFangZhuActivity.class);
                        intent.putExtra("type", "update");
                        intent.putExtra("fz_id", fz_id);
                        intent.putExtra("addressname", addressname);
                        mContext.startActivity(intent);
                    } else {
                        //添加
                        Intent intent = new Intent(mContext, AddFangZhuActivity.class);
                        intent.putExtra("type", "add");
                        intent.putExtra("fz_id", fz_id);
                        intent.putExtra("addressname", addressname);
                        mContext.startActivity(intent);
                    }
                }
            });
        }
        /**
         * 住户
         */
        if (holder instanceof ImageViewHolder) {

            ((ImageViewHolder) holder).recyclerView1.setLayoutManager(new LinearLayoutManager(mContext));
            //获取用户地址信息
            addressUp = addHouseBean.getData().getFangjian().getJzw_dizhi() + " " + addHouseBean.getData().getFangjian().getDys() + " 单元" + " " + addHouseBean.getData().getFangjian().getCs() + " 层" + " " + addHouseBean.getData().getFangjian().getHumc();
            if (addHouseBean.getData().getRyList() != null && addHouseBean.getData().getRyList().size() > 0) {

                ((ImageViewHolder) holder).recyclerView1.setVisibility(View.VISIBLE);
                ((ImageViewHolder) holder).no_data_relative.setVisibility(View.GONE);

                List<AddHouseBean.DataBean.RyListBean> list = addHouseBean.getData().getRyList();
                if (zuHuAdapter == null) {
                    zuHuAdapter = new ZuHuAdapter(mContext, list, fz_id, addressname, floorCode, addressUp);
                    ((ImageViewHolder) holder).recyclerView1.setAdapter(zuHuAdapter);
                } else {
                    zuHuAdapter.setData(list, fz_id, addressname, floorCode, addressUp);
                }

            } else {
                ((ImageViewHolder) holder).no_data_relative.setVisibility(View.VISIBLE);
                ((ImageViewHolder) holder).recyclerView1.setVisibility(View.GONE);
            }

            ((ImageViewHolder) holder).fangzhu_commit_buttons.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, AddUserActivity.class);
                    intent.putExtra("type", "adduser");
                    intent.putExtra("fz_id", fz_id);
                    intent.putExtra("floorCode", floorCode);
                    intent.putExtra("addressname", addressname);
                    intent.putExtra("addressUp", addressUp);
                    mContext.startActivity(intent);
                }
            });

        }

    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return ITEM_TYPE_IMAGE;
        } else {
            return ITEM_TYPE_TEXT;
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    /**
     * 房主
     */
    public static class item1Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.fz_name)
        TextView fz_name;
        @BindView(R.id.fz_type)
        TextView fz_type;
        @BindView(R.id.fangzhu_commit_button)
        ImageView fangzhu_commit_button;

        public item1Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    /**
     * 住户
     */
    public static class ImageViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_lou_cycleview2)
        RecyclerView recyclerView1;
        @BindView(R.id.zhuhu_commit_buttons)
        ImageView fangzhu_commit_buttons;
        @BindView(R.id.no_data_relative)
        RelativeLayout no_data_relative;

        ImageViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }
}
