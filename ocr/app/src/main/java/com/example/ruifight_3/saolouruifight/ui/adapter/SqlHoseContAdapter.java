package com.example.ruifight_3.saolouruifight.ui.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ruifight_3.saolouruifight.R;
import com.example.ruifight_3.saolouruifight.db.util.bean.view_ceng;
import com.example.ruifight_3.saolouruifight.db.util.bean.view_fangJian;
import com.example.ruifight_3.saolouruifight.ui.AddHouseMessageActivity;
import com.example.ruifight_3.saolouruifight.util.ToastUtil;
import com.example.ruifight_3.saolouruifight.widget.CustomRecyclerView;
import com.example.ruifight_3.saolouruifight.widget.MyGridLayoutManager;

import java.util.List;

/**
 * Created by RuiFight-3 on 2018/7/13.
 */

public class SqlHoseContAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<view_ceng> list;
    private String addressname; //头部地址
    private String floorCode;//编号
    private String jiedaoCode;//街路巷编码

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

    public SqlHoseContAdapter(Context mContext, List<view_ceng> list, String floorCode, String jiedaoCode, String addressname) {
        this.mContext = mContext;
        this.list = list;
        this.floorCode = floorCode;
        this.jiedaoCode = jiedaoCode;
        this.addressname = addressname;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override

    public MyHolderss onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mLayoutInflater.inflate(R.layout.item_house_message_adapter, parent, false);
        MyHolderss holder = new MyHolderss(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyHolderss viewholder = (MyHolderss) holder;

        viewholder.lou_count_tv.setText(list.get(position).getCengShu() + " 层");
        viewholder.lou_count_tv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ToastUtil.showInfo(mContext, "离线暂时无法操作");
                return true;
            }
        });
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
//        viewholder.recyclerView.setLayoutManager(linearLayoutManager);
        MyGridLayoutManager gridLayoutManager = new MyGridLayoutManager(mContext, 4);
        gridLayoutManager.setScrollEnabled(false);
        viewholder.recyclerView.setNestedScrollingEnabled(false);
        viewholder.recyclerView.setLayoutManager(gridLayoutManager);

        final List<view_fangJian> lists = list.get(position).getFjList();

        SqlHouseMessageAdapter sqlHouseMessageAdapter = new SqlHouseMessageAdapter(mContext, lists);
        viewholder.recyclerView.setAdapter(sqlHouseMessageAdapter);

        sqlHouseMessageAdapter.setmOnitemClickListener(new SqlHouseMessageAdapter.OnItemClickListener() {
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

            @Override
            public void onItemLongClick(View view, int position) {
                ToastUtil.showInfo(mContext, "离线暂时无法操作");
            }
        });

//        viewholder.lou_count_tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
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
    protected void setUpItemEvent(final MyHolderss holder) {
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

    public void setData(List<view_ceng> list2) {
        this.list = list2;
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
}

class MyHolderss extends RecyclerView.ViewHolder {
    TextView lou_count_tv;
    CustomRecyclerView recyclerView;

    public MyHolderss(View itemView) {
        super(itemView);

        recyclerView = itemView.findViewById(R.id.house_recycle);
        lou_count_tv = itemView.findViewById(R.id.lou_count_tv);
    }
}
