package com.example.ruifight_3.saolouruifight.ui.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.ruifight_3.saolouruifight.MyApi;
import com.example.ruifight_3.saolouruifight.R;
import com.example.ruifight_3.saolouruifight.db.datamessage.LouMessage;
import com.example.ruifight_3.saolouruifight.db.datamessage.RecordMeanage;
import com.example.ruifight_3.saolouruifight.db.datamessage.ZhuHuMeanage;
import com.example.ruifight_3.saolouruifight.db.util.FileUtils;
import com.example.ruifight_3.saolouruifight.db.util.bean.RecordBean;
import com.example.ruifight_3.saolouruifight.db.util.bean.t_jzw_fangjian;
import com.example.ruifight_3.saolouruifight.ui.AddUserActivity;
import com.example.ruifight_3.saolouruifight.ui.BigPictureActivity;
import com.example.ruifight_3.saolouruifight.ui.bean.AddHouseBean;
import com.example.ruifight_3.saolouruifight.ui.bean.DeleteHuzhuBean;
import com.example.ruifight_3.saolouruifight.ui.bean.MainBean;
import com.example.ruifight_3.saolouruifight.util.Base64Utils;
import com.example.ruifight_3.saolouruifight.util.DateUtil;
import com.example.ruifight_3.saolouruifight.util.DiaLogUtil;
import com.example.ruifight_3.saolouruifight.util.ImageUtil;
import com.example.ruifight_3.saolouruifight.util.JsonUtils;
import com.example.ruifight_3.saolouruifight.util.NetWorkUtil;
import com.example.ruifight_3.saolouruifight.util.SPUtils;
import com.example.ruifight_3.saolouruifight.util.ToastUtil;
import com.example.ruifight_3.saolouruifight.util.Validator;
import com.example.ruifight_3.saolouruifight.widget.CommomDialog;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by RuiFight-3 on 2018/5/12.
 */

public class ZuHuAdapter extends RecyclerView.Adapter<ZuHuAdapter.MyHoldere> {

    private LayoutInflater mLayoutInflater;
    private Context mContext;
    protected List<AddHouseBean.DataBean.RyListBean> lists;
    private String addressId;
    private String addressname;
    private String floorCode;//建筑物编码
    private String addressUp; //人员详细地址信息
    private LouMessage louMessage;
    private ZhuHuMeanage zhuHuMeanage;
    //用于查询流动人口数量 用于修改
    private List<MainBean.DataBean.JzwListBean> unitsListBeans;

    /**
     * item点击事件接口，
     */
    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private HouseCountAdapter.OnItemClickListener mOnitemClickListener;

    public HouseCountAdapter.OnItemClickListener getmOnitemClickListener() {
        return mOnitemClickListener;
    }

    public void setmOnitemClickListener(HouseCountAdapter.OnItemClickListener mOnitemClickListener) {
        this.mOnitemClickListener = mOnitemClickListener;
    }

    public ZuHuAdapter(Context mContext, List<AddHouseBean.DataBean.RyListBean> lists, String addressId, String addressname, String floorCode, String addressUp) {
        this.mContext = mContext;
        this.lists = lists;
        this.addressId = addressId;
        this.addressname = addressname;
        this.floorCode = floorCode;
        this.addressUp = addressUp;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    public void setData(List<AddHouseBean.DataBean.RyListBean> lists, String addressId, String addressname, String floorCode, String addressUp) {
        this.lists = lists;
        this.addressId = addressId;
        this.addressname = addressname;
        this.floorCode = floorCode;
        this.addressUp = addressUp;
        notifyDataSetChanged();
    }


    @Override

    public MyHoldere onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mLayoutInflater.inflate(R.layout.item_lou_partic2, parent, false);

        MyHoldere holder = new MyHoldere(view);


        return holder;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(final MyHoldere holder, @SuppressLint("RecyclerView") final int position) {
//        holder.mTextView.setText(lists.get(position).getAddressName());

        if (lists.get(position).getPicture() != null && !lists.get(position).getPicture().equals("")) {
            //  holder._item_tou.setImageBitmap(Base64Utils.base64ToBitmap(lists.get(position).getPicture() + ""));
            if (NetWorkUtil.isConn(mContext) == false) {
                try {
                    holder._item_tou.setImageBitmap(Base64Utils.base64ToBitmap(lists.get(position).getPicture() + ""));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                // ImageLoader.getInstance().displayImage(MyApi.URL + MyApi.IMAGEURL + lists.get(position).getPicture() + "", holder._item_tou);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    if (mContext instanceof Activity && ((Activity) mContext).isDestroyed()) {
                        return;
                    } else {
                        String url = String.valueOf(MyApi.URL + MyApi.IMAGEURL + lists.get(position).getPicture());
                        Glide.with(mContext).load(url)
                                .placeholder(R.drawable.touxiangid)
                                .error(R.drawable.touxiangid)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)//禁用磁盘缓存
                                .skipMemoryCache(false)//跳过内存缓存
                                .dontAnimate()
                                .into(holder._item_tou);
                    }
                }
            }
        } else {
            ImageUtil.displayImage(mContext, R.drawable.touxiangid, holder._item_tou);
        }
        if (NetWorkUtil.isConn(mContext)) {
            try {
                holder.time_itme_lou.setText(DateUtil.getDescriptionTimeFromDateString(DateUtil.getDateToString1(lists.get(position).getCjsj(), DateUtil.FORMAT_DATE_TIME)));
            } catch (Exception e) {
                e.printStackTrace();
                holder.time_itme_lou.setText("异常");
            }
        }
        holder.name_tv.setText(lists.get(position).getXm() != null ? "姓名: " + lists.get(position).getXm() : "姓名: ");
        holder.age_tv.setText(lists.get(position).getXb() != null ? "性别: " + lists.get(position).getXb() : "性别: ");
        holder.phone_tv.setText(lists.get(position).getLxdh() != null ? "手机号: " + lists.get(position).getLxdh() : "手机号: ");

        if (lists.get(position).getSfz() == null && lists.get(position).getSfz().equals("")) {
        } else {
            if (Validator.isLegalId(lists.get(position).getSfz())) {
                if (lists.get(position).getSfz().length() == 18) {
                    String idca = lists.get(position).getSfz().substring(0, 6) + "**" + lists.get(position).getSfz().substring(14, 18);
                    holder.idcard_tv.setText("身份证号: " + idca);
                } else {
                    String idca = lists.get(position).getSfz().substring(0, 6) + "**" + lists.get(position).getSfz().substring(11, 15);
                    holder.idcard_tv.setText("身份证号: " + idca);
                }
            } else {
                holder.idcard_tv.setText("身份证号: " + lists.get(position).getSfz());
            }
        }
        //查看大图
        holder._item_tou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (lists.get(position).getPicture() != null && !lists.get(position).getPicture().equals("")) {
                    if (NetWorkUtil.isConn(mContext) == false) {

                        if (Build.VERSION.SDK_INT < 21) {
                            Toast.makeText(mContext, "21+ only, keep out", Toast.LENGTH_SHORT).show();
                        } else {
                            MyApi.DAIMAGE = lists.get(position).getPicture() + "";
                            Intent intent = new Intent(mContext, BigPictureActivity.class);
                            ActivityOptionsCompat options = ActivityOptionsCompat.
                                    makeSceneTransitionAnimation((Activity) mContext, v, "test");
                            //没网1
                            intent.putExtra("is", "1");
                            mContext.startActivity(intent, options.toBundle());
                        }

                    } else {
                        if (Build.VERSION.SDK_INT < 21) {
                            Toast.makeText(mContext, "21+ only, keep out", Toast.LENGTH_SHORT).show();
                        } else {
                            MyApi.DAIMAGE = lists.get(position).getPicture() + "";
                            Intent intent = new Intent(mContext, BigPictureActivity.class);
                            ActivityOptionsCompat options = ActivityOptionsCompat.
                                    makeSceneTransitionAnimation((Activity) mContext, v, "test");
                            //有网2
                            intent.putExtra("is", "2");
                            mContext.startActivity(intent, options.toBundle());
                        }
                    }
                } else {
                    Toast.makeText(mContext, "无大图", Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.delete_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new CommomDialog(mContext, R.style.dialog, "您确定要删除住户信息？", new CommomDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm) {
                        if (confirm) {

                            if (NetWorkUtil.isConn(mContext) == false) {

                                if (FileUtils.fileIsExists(MyApi.FileLoad + MyApi.DBNAME) == true) {
                                    if (louMessage == null) {
                                        louMessage = new LouMessage(mContext);
                                    }
                                    if (louMessage.tabbleIsExist("t_jzw_jlx") == true) {
                                        //没网删除
                                        if (position < lists.size()) {
                                            sqDelete(lists.get(position).getSfz(), addressId, position, lists.get(position).getRylx());
                                        }
                                        dialog.dismiss();
                                    }
                                }

                            } else {
                                //有网删除
                                if (position < lists.size()) {
                                    deleteMessage(lists.get(position).getId(), position, lists.get(position).getSfz(), addressId, lists.get(position).getRylx());
                                }
                                dialog.dismiss();
                            }
                        }
                    }
                }).setTitle("提示").show();

            }
        });

        holder.bianji_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, AddUserActivity.class);
                intent.putExtra("type", "update");
                intent.putExtra("UserId", lists.get(position).getId() + "");
                intent.putExtra("fz_id", addressId);
                intent.putExtra("floorCode", floorCode);
                intent.putExtra("addressname", addressname);
                intent.putExtra("addressUp", addressUp);
                mContext.startActivity(intent);

            }
        });

        holder.itemView.setTag(position);
        setUpItemEvent(holder);

    }

    /**
     * 点击事件回调接口
     *
     * @param holder
     */
    protected void setUpItemEvent(final MyHoldere holder) {
        if (mOnitemClickListener != null) {
            holder.delete_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //获取最新的item位置
                    int layoutPosition = holder.getLayoutPosition();
                    mOnitemClickListener.onItemClick(holder.delete_tv, layoutPosition);
                }
            });

            holder.delete_tv.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    //获取最新的item位置
                    int layoutPosition = holder.getLayoutPosition();
                    mOnitemClickListener.onItemLongClick(holder.delete_tv, layoutPosition);
                    return false;
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

    class MyHoldere extends RecyclerView.ViewHolder {

        @BindView(R.id._item_tou)
        ImageView _item_tou;
        @BindView(R.id.name_tv)
        TextView name_tv;
        @BindView(R.id.age_tv)
        TextView age_tv;
        @BindView(R.id.phone_tv)
        TextView phone_tv;
        @BindView(R.id.idcard_tv)
        TextView idcard_tv;
        @BindView(R.id.renkou_tv)
        TextView renkou_tv;
        @BindView(R.id.danwei_tv)
        TextView danwei_tv;
        @BindView(R.id.delete_tv)
        ImageView delete_tv;
        @BindView(R.id.bianji_tv)
        ImageView bianji_tv;
        @BindView(R.id.time_itme_lou)
        TextView time_itme_lou;

        public MyHoldere(View itemView) {

            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    /**
     * 没网删除
     *
     * @param idcard
     * @param hu_id
     * @param position
     */

    public void sqDelete(String idcard, String hu_id, int position, String rylx) {

        int renshu_ldrk1 = 0;
        int renshu_rhfl1 = 0;
        int renshu_hjrk1 = 0;
        if (zhuHuMeanage == null) {
            zhuHuMeanage = new ZhuHuMeanage(mContext);
        }
        zhuHuMeanage.deleteZhuHu(idcard, hu_id);

        RecordMeanage recordMeanag = new RecordMeanage(mContext);
        RecordBean recordBea = new RecordBean();
        recordBea.setType(MyApi.DELETE);
        recordBea.setStatement("delete from t_ryxx where sfz=" + "'" + idcard + "' and " + "jz_fwid=" + "'" + hu_id + "'");
        recordBea.setOperateDate("");
        recordBea.setOperateUser((String) SPUtils.get(mContext, "loginName", ""));
        recordBea.setTableName("t_ryxx");
        recordMeanag.addRecord(recordBea);

        t_jzw_fangjian t_jzw_fangjian = new t_jzw_fangjian();
        t_jzw_fangjian = zhuHuMeanage.selectFangjian(hu_id);

        if (t_jzw_fangjian.getRenshu_ldrk() != null) {
            renshu_ldrk1 = t_jzw_fangjian.getRenshu_ldrk();
        } else if (t_jzw_fangjian.getRenshu_rhfl() != null) {
            renshu_rhfl1 = t_jzw_fangjian.getRenshu_rhfl();
        } else if (t_jzw_fangjian.getRenshu_hjrk() != null) {
            renshu_hjrk1 = t_jzw_fangjian.getRenshu_hjrk();
        }

        if (rylx.equals("流动人口")) {
            if (unitsListBeans == null) {
                unitsListBeans = new ArrayList<>();
            } else {
                unitsListBeans.clear();
            }
            unitsListBeans = zhuHuMeanage.selectLiu(floorCode);
            if (0 < unitsListBeans.size()) {
                if (unitsListBeans.get(0).getLdrk_count() != 0) {
                    int ldrk_count = unitsListBeans.get(0).getLdrk_count() - 1;
                    zhuHuMeanage.updateLiu(ldrk_count, floorCode);
                }
            }
            if (renshu_ldrk1 != 0) {
                zhuHuMeanage.updateFngjian(renshu_ldrk1 - 1, hu_id);
            }
        } else if (rylx.equals("人户分离")) {
            if (renshu_rhfl1 != 0) {
                zhuHuMeanage.updateFngjianrhfl(renshu_rhfl1 - 1, hu_id);
            }
        } else if (rylx.equals("户籍人口")) {
            if (renshu_hjrk1 != 0) {
                zhuHuMeanage.updateFngjianhjrk1(renshu_hjrk1 - 1, hu_id);
            }
        }

        //修改人数插入记录表
        RecordBean recordBea1 = new RecordBean();
        recordBea1.setType(MyApi.UPDATE);
        if (rylx.equals("流动人口")) {
            if (renshu_ldrk1 != 0) {
                int s = renshu_ldrk1 - 1;
                recordBea1.setStatement("update t_jzw_fangjian set renshu_ldrk=" + "'" + s + "' " + "where " + "hu_id=" + "'" + hu_id + "'");
            } else {
                recordBea1.setStatement("update t_jzw_fangjian set renshu_ldrk=" + "'" + 0 + "' " + "where " + "hu_id=" + "'" + hu_id + "'");
            }
        } else if (rylx.equals("人户分离")) {
            if (renshu_rhfl1 != 0) {
                int b = renshu_rhfl1 - 1;
                recordBea1.setStatement("update t_jzw_fangjian set renshu_rhfl=" + "'" + b + "' " + "where " + "hu_id=" + "'" + hu_id + "'");
            } else {
                recordBea1.setStatement("update t_jzw_fangjian set renshu_rhfl=" + "'" + 0 + "' " + "where " + "hu_id=" + "'" + hu_id + "'");
            }
        } else if (rylx.equals("户籍人口")) {
            if (renshu_hjrk1 != 0) {
                int c = renshu_hjrk1 - 1;
                recordBea1.setStatement("update t_jzw_fangjian set renshu_hjrk=" + "'" + c + "' " + "where " + "hu_id=" + "'" + hu_id + "'");
            } else {
                recordBea1.setStatement("update t_jzw_fangjian set renshu_hjrk=" + "'" + 0 + "' " + "where " + "hu_id=" + "'" + hu_id + "'");
            }
        }
        recordBea1.setOperateDate("");
        recordBea1.setOperateUser((String) SPUtils.get(mContext, "loginName", ""));
        recordBea1.setTableName("t_jzw_fangjian");
        recordMeanag.addRecord(recordBea1);
        if (position < lists.size()) {
            lists.remove(position);
        }
        notifyDataSetChanged();
        SPUtils.put(mContext, "isResune", true);
        SPUtils.put(mContext, "updateIs", "yes");
        ToastUtil.showInfo(mContext, "操作成功");
    }


    /**
     * 有网删除
     *
     * @param id
     * @param position
     */
    public void deleteMessage(final int id, final int position, final String idcard, final String hu_id, final String rylx) {
        DiaLogUtil.showDiaLog(mContext, "正在删除");
        OkHttpUtils
                .post()
                .url(MyApi.URL + MyApi.DELETEZUHU)
                .tag(mContext)
                .addParams("id", id + "")
                .build()
                .execute(new StringCallback() {

                    @Override
                    public void onError(Request request, Exception e) {
                        DiaLogUtil.dismissDiaLog();
                        ToastUtil.showInfo(mContext, "无法连接服务器");
                    }

                    @Override
                    public void onResponse(String response) {
                        DiaLogUtil.dismissDiaLog();
                        try {
                            DeleteHuzhuBean deleteHuzhuBean = (DeleteHuzhuBean) JsonUtils.stringToObject(response, DeleteHuzhuBean.class);
                            if (deleteHuzhuBean.isStatus() == false) {
                                if (mContext != null) {
                                    ToastUtil.showInfo(mContext, deleteHuzhuBean.getMsg() + "");

                                }
                            } else {
                                SPUtils.put(mContext, "isResune", true);
                                if (FileUtils.fileIsExists(MyApi.FileLoad + MyApi.DBNAME) == true) {
                                    if (louMessage == null) {
                                        louMessage = new LouMessage(mContext);
                                    }
                                    if (louMessage.tabbleIsExist("t_jzw_jlx") == true) {

                                        int renshu_ldrk1 = 0;
                                        int renshu_rhfl1 = 0;
                                        int renshu_hjrk1 = 0;
                                        if (zhuHuMeanage == null) {
                                            zhuHuMeanage = new ZhuHuMeanage(mContext);
                                        }
                                        zhuHuMeanage.deleteZhuHu(idcard, hu_id);

                                        t_jzw_fangjian t_jzw_fangjian = new t_jzw_fangjian();
                                        t_jzw_fangjian = zhuHuMeanage.selectFangjian(addressId);

                                        if (t_jzw_fangjian.getRenshu_ldrk() != null) {
                                            renshu_ldrk1 = t_jzw_fangjian.getRenshu_ldrk();
                                        } else if (t_jzw_fangjian.getRenshu_rhfl() != null) {
                                            renshu_rhfl1 = t_jzw_fangjian.getRenshu_rhfl();
                                        } else if (t_jzw_fangjian.getRenshu_hjrk() != null) {
                                            renshu_hjrk1 = t_jzw_fangjian.getRenshu_hjrk();
                                        }
                                        if (rylx.equals("流动人口")) {
                                            if (unitsListBeans == null) {
                                                unitsListBeans = new ArrayList<>();
                                            } else {
                                                unitsListBeans.clear();
                                            }
                                            unitsListBeans = zhuHuMeanage.selectLiu(floorCode);
                                            if (0 < unitsListBeans.size()) {
                                                if (unitsListBeans.get(0).getLdrk_count() != 0) {
                                                    int ldrk_count = unitsListBeans.get(0).getLdrk_count() - 1;
                                                    zhuHuMeanage.updateLiu(ldrk_count, floorCode);
                                                }
                                            }
                                            if (renshu_ldrk1 != 0) {
                                                zhuHuMeanage.updateFngjian(renshu_ldrk1 - 1, hu_id);
                                            }
                                        } else if (rylx.equals("人户分离")) {
                                            if (renshu_rhfl1 != 0) {
                                                zhuHuMeanage.updateFngjianrhfl(renshu_rhfl1 - 1, hu_id);
                                            }
                                        } else if (rylx.equals("户籍人口")) {
                                            if (renshu_hjrk1 != 0) {
                                                zhuHuMeanage.updateFngjianhjrk1(renshu_hjrk1 - 1, hu_id);
                                            }
                                        }
                                        if (position < lists.size()) {
                                            lists.remove(position);
                                        }
                                        ToastUtil.showInfo(mContext, "操作成功");
                                        notifyDataSetChanged();
                                    } else {
                                        if (position < lists.size()) {
                                            lists.remove(position);
                                        }
                                        ToastUtil.showInfo(mContext, "操作成功");
                                        notifyDataSetChanged();
                                    }
                                } else {
                                    if (position < lists.size()) {
                                        lists.remove(position);
                                    }
                                    ToastUtil.showInfo(mContext, "操作成功");
                                    notifyDataSetChanged();
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            if (mContext != null) {
                                ToastUtil.showInfo(mContext, "解析服务器意图异常");
                            }
                        }
                    }
                });
    }

}
