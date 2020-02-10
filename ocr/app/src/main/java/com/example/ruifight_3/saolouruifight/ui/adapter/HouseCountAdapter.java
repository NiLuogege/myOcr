package com.example.ruifight_3.saolouruifight.ui.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ruifight_3.saolouruifight.R;
import com.example.ruifight_3.saolouruifight.ui.bean.DanYuanBean;

import java.util.List;

/**
 * Created by RuiFight-3 on 2018/4/19.
 */

public class HouseCountAdapter extends RecyclerView.Adapter<MyHolder> {

    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<DanYuanBean.DataBean.DyDetailBean.CengListBean.FjListBean> lists;
    private OnItemClickListener mOnitemClickListener;


    public HouseCountAdapter(Context mContext, List<DanYuanBean.DataBean.DyDetailBean.CengListBean.FjListBean> lists) {
        this.mContext = mContext;
        this.lists = lists;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    public OnItemClickListener getmOnitemClickListener() {
        return mOnitemClickListener;
    }

    public void setmOnitemClickListener(OnItemClickListener mOnitemClickListener) {
        this.mOnitemClickListener = mOnitemClickListener;
    }

    public void setDatas(List<DanYuanBean.DataBean.DyDetailBean.CengListBean.FjListBean> lists){
        this.lists = lists;
        notifyDataSetChanged();
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mLayoutInflater.inflate(R.layout.item_item_house_cycleview, parent, false);

        MyHolder holder = new MyHolder(view);


        return holder;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {
        // holder.mTextView.setText(lists.get(position).getHubm());
        int pos = holder.getLayoutPosition();

        String chapterName = lists.get(position).getHubm();
        holder.mTextView.setText(chapterName);

        if (lists.get(position).getFz_fwjzlx() != null && lists.get(position).getFz_fwjzlx().equals("自住房屋")) {
//            holder.mTextView.setBackgroundColor(mContext.getResources().getColor(R.color.colors_fontGreen2));
            holder.image1.setBackground(ContextCompat.getDrawable(mContext,R.mipmap.zi));
//            ImageUtil.displayImage(mContext,R.mipmap.zi,holder.image1);
        } else if (lists.get(position).getFz_fwjzlx() != null && lists.get(position).getFz_fwjzlx().equals("出租房屋")) {
            holder.image1.setBackground(ContextCompat.getDrawable(mContext,R.mipmap.zu));

        } else if (lists.get(position).getFz_fwjzlx() != null && lists.get(position).getFz_fwjzlx().equals("出借房屋")) {
            holder.image1.setBackground(ContextCompat.getDrawable(mContext,R.mipmap.jie));

        } else if (lists.get(position).getFz_fwjzlx() != null && lists.get(position).getFz_fwjzlx().equals("集体宿舍")) {
            holder.image1.setBackground(ContextCompat.getDrawable(mContext,R.mipmap.ji));

        } else if (lists.get(position).getFz_fwjzlx() != null && lists.get(position).getFz_fwjzlx().equals("闲置房屋")) {
            holder.image1.setBackground(ContextCompat.getDrawable(mContext,R.mipmap.xian));

        } else if (lists.get(position).getFz_fwjzlx() != null && lists.get(position).getFz_fwjzlx().equals("其他")) {
            holder.image1.setBackground(ContextCompat.getDrawable(mContext,R.mipmap.qita));

        } else {
            holder.image1.setBackground(ContextCompat.getDrawable(mContext,R.drawable.touming));
        }

        if (lists.get(position).getRenshu_ldrk() > 0) {
            holder.image2.setVisibility(View.VISIBLE);
        } else if (lists.get(position).getRenshu_hjrk() > 0) {
            holder.image2.setVisibility(View.VISIBLE);
        } else if (lists.get(position).getRenshu_rhfl() > 0) {
            holder.image2.setVisibility(View.VISIBLE);
        } else {
            holder.image2.setVisibility(View.GONE);
        }

        //Log.e("Ceshi","CE"+lists.get(pos).getRenshu_ldrk()+"   "+lists.get(pos).getRenshu_hjrk()+"  "+lists.get(pos).getRenshu_rhfl());

        holder.itemView.setTag(position);
        setUpItemEvent(holder);

    }

    /**
     * 点击事件回调接口
     *
     * @param holder
     */
    protected void setUpItemEvent(final MyHolder holder) {
        if (mOnitemClickListener != null) {
            holder.mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //获取最新的item位置
                    int layoutPosition = holder.getLayoutPosition();
                    mOnitemClickListener.onItemClick(holder.mTextView, layoutPosition);
                }
            });

            holder.mTextView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    //获取最新的item位置
                    int layoutPosition = holder.getLayoutPosition();
                    mOnitemClickListener.onItemLongClick(holder.mTextView, layoutPosition);
                    return true;
                }
            });
        }
    }

    public void addData(int pos) {
        // mDatas.add(pos, "insert one");
//    notifyDataSetChanged();这里不用这个
        notifyItemInserted(pos);
    }

    public void deleteData(int pos) {
        //  mDatas.remove(pos);

        notifyItemRemoved(pos);
    }

    @Override
    public int getItemCount() {
        return lists != null && lists.size() > 0 ? lists.size() : 0;
    }

    /**
     * item点击事件接口，
     */
    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }
}

class MyHolder extends RecyclerView.ViewHolder {
    TextView mTextView;
    ImageView image1;
    ImageView image2;

    public MyHolder(View itemView) {

        super(itemView);
        mTextView = itemView.findViewById(R.id.count_tv);
        image1 = itemView.findViewById(R.id.image1);
        image2 = itemView.findViewById(R.id.image2);
    }

}
