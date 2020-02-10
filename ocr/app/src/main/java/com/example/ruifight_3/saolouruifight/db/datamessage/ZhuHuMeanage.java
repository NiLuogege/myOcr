package com.example.ruifight_3.saolouruifight.db.datamessage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ruifight_3.saolouruifight.db.DBOpenHelper;
import com.example.ruifight_3.saolouruifight.db.util.bean.t_jzw_fangjian;
import com.example.ruifight_3.saolouruifight.ui.bean.AddHouseBean;
import com.example.ruifight_3.saolouruifight.ui.bean.GetUserBean;
import com.example.ruifight_3.saolouruifight.ui.bean.MainBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RuiFight-3 on 2018/7/19.
 */

public class ZhuHuMeanage {

    DBOpenHelper openHelper;
    Context context;

    public ZhuHuMeanage(Context conetxt) {
        this.context = conetxt;
        openHelper = DBOpenHelper.getInstance(context, false);
    }

    //添加住户
    public void addZhuHu(String fz_id, String floorCode, AddHouseBean.DataBean.RyListBean ryListBean) {
        SQLiteDatabase db = null;
        try {
            db = openHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("xm", ryListBean.getXm());
            values.put("sfz", ryListBean.getSfz());
            values.put("bieming", ryListBean.getBieming());
            values.put("xb", ryListBean.getXb());
            values.put("minzu", ryListBean.getMinzu());
            values.put("hj_ssx", ryListBean.getHj_ssx());
            values.put("hj_hjxz", ryListBean.getHj_hjxz());
            values.put("lxdh", ryListBean.getLxdh());
            values.put("jz_fwid", fz_id);
            values.put("jz_jzwbm", floorCode);
            values.put("dizhi_jzwdz", ryListBean.getDizhi_jzwdz());
            values.put("dizhi_xz", ryListBean.getDizhi_xz());
            values.put("rylx", ryListBean.getRylx());
            values.put("ldrk_whcd", ryListBean.getLdrk_whcd());
            values.put("ldrk_hunyin", ryListBean.getLdrk_hunyin());
            values.put("ldrk_zzcs", ryListBean.getLdrk_zzcs());
            values.put("ldrk_zzsy", ryListBean.getLdrk_zzsy());
            values.put("ldrk_ljqcyzk", ryListBean.getLdrk_ljqcyzk());
            values.put("ldrk_ljrq", ryListBean.getLdrk_ljrq());
            values.put("ldrk_lxjzdrq", ryListBean.getLdrk_lxjzdrq());
            values.put("ldrk_fwcs", ryListBean.getLdrk_fwcs());
            values.put("cjsj", ryListBean.getCjsj());
            values.put("is_del", ryListBean.getIs_del());
            values.put("whry", ryListBean.getWhry());
            values.put("whsj", ryListBean.getWhsj());
            values.put("beizhu", ryListBean.getBeizhu());
            values.put("new_picture", ryListBean.getNew_picture() + "");
            if (ryListBean.getPicture() != null && !ryListBean.getPicture().equals("")) {
                values.put("picture", ryListBean.getPicture() + "");
            }

            db.insert("t_ryxx", null, values);

        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    /**
     * t添加人员 先查一遍人数字段 在+1
     *
     * @param hu_id
     * @return
     */
    public t_jzw_fangjian selectFangjian(String hu_id) {
        t_jzw_fangjian t_jzw_fangjia1 = new t_jzw_fangjian();
        SQLiteDatabase db = null;
        // 获取一个光标对象
        Cursor cursor = null;
        try {
            db = openHelper.getReadableDatabase(); //流动  //分离 //户籍人口
            cursor = db.query("t_jzw_fangjian", new String[]{"renshu_ldrk", "renshu_rhfl", "renshu_hjrk"}, "hu_id=?", new String[]{hu_id}, null, null, null);
            t_jzw_fangjian t_jzw_fangjian = null;
            while (cursor.moveToNext()) {
                t_jzw_fangjian = new t_jzw_fangjian();
                t_jzw_fangjian.setRenshu_ldrk(cursor.getInt(cursor.getColumnIndex("renshu_ldrk")));
                t_jzw_fangjian.setRenshu_rhfl(cursor.getInt(cursor.getColumnIndex("renshu_rhfl")));
                t_jzw_fangjian.setRenshu_hjrk(cursor.getInt(cursor.getColumnIndex("renshu_hjrk")));
                t_jzw_fangjia1 = t_jzw_fangjian;
            }
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return t_jzw_fangjia1;
    }


    /**
     * 添加人员成功  房间表人数+1 直接用修改
     */
    public void updateFngjian(int renshu_ldrk, String hu_id) {
        SQLiteDatabase db = null;
        try {
            db = openHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("renshu_ldrk", renshu_ldrk);
            db.update("t_jzw_fangjian", values, "hu_id=?", new String[]{hu_id});
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    /**
     * 添加人员成功  房间表人数+1 直接用修改
     */
    public void updateFngjianrhfl(int renshu_rhfl, String hu_id) {
        SQLiteDatabase db = null;
        try {
            db = openHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("renshu_rhfl", renshu_rhfl);
            db.update("t_jzw_fangjian", values, "hu_id=?", new String[]{hu_id});
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    /**
     * 添加人员成功  房间表人数+1 直接用修改
     */
    public void updateFngjianhjrk1(int renshu_hjrk, String hu_id) {
        SQLiteDatabase db = null;
        try {
            db = openHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("renshu_hjrk", renshu_hjrk);
            db.update("t_jzw_fangjian", values, "hu_id=?", new String[]{hu_id});
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }


    /**
     * 查询住户信息 用于修改回显
     */
    public GetUserBean.DataBean getUser(String userId) {

        GetUserBean.DataBean dataBean = new GetUserBean.DataBean();
        SQLiteDatabase db = null;
        // 获取一个光标对象
        Cursor cursor = null;
        try {
            db = openHelper.getReadableDatabase();
            cursor = db.query("t_ryxx", new String[]{"xm", "xb", "minzu", "sfz", "hj_hjxz", "lxdh", "ldrk_whcd", "ldrk_hunyin", "ldrk_zzcs", "ldrk_ljqcyzk", "cjsj", "beizhu", "ldrk_ljrq", "ldrk_lxjzdrq", "picture", "new_picture", "rylx", "ldrk_fwcs"}, "id=?", new String[]{userId}, null, null, null);
            GetUserBean.DataBean dataBean1 = null;
            while (cursor.moveToNext()) {
                dataBean1 = new GetUserBean.DataBean();
                dataBean1.setXm(cursor.getString(cursor.getColumnIndex("xm")));
                dataBean1.setXb(cursor.getString(cursor.getColumnIndex("xb")));
                dataBean1.setMinzu(cursor.getString(cursor.getColumnIndex("minzu")));
                dataBean1.setSfz(cursor.getString(cursor.getColumnIndex("sfz")));
                dataBean1.setHj_hjxz(cursor.getString(cursor.getColumnIndex("hj_hjxz")));
                dataBean1.setLxdh(cursor.getString(cursor.getColumnIndex("lxdh")));
                dataBean1.setLdrk_whcd(cursor.getString(cursor.getColumnIndex("ldrk_whcd")));
                dataBean1.setLdrk_hunyin(cursor.getString(cursor.getColumnIndex("ldrk_hunyin")));
                dataBean1.setLdrk_zzcs(cursor.getString(cursor.getColumnIndex("ldrk_zzcs")));
                dataBean1.setLdrk_ljqcyzk(cursor.getString(cursor.getColumnIndex("ldrk_ljqcyzk")));
                dataBean1.setCjsj(cursor.getInt(cursor.getColumnIndex("cjsj")));
                dataBean1.setBeizhu(cursor.getString(cursor.getColumnIndex("beizhu")));
                dataBean1.setLdrk_ljrq(cursor.getInt(cursor.getColumnIndex("ldrk_ljrq")));
                dataBean1.setLdrk_lxjzdrq(cursor.getString(cursor.getColumnIndex("ldrk_lxjzdrq")));
                dataBean1.setPicture(cursor.getString(cursor.getColumnIndex("picture")));
                dataBean1.setNew_picture(cursor.getString(cursor.getColumnIndex("new_picture")));
                dataBean1.setRylx(cursor.getString(cursor.getColumnIndex("rylx")));
                dataBean1.setLdrk_fwcs(cursor.getString(cursor.getColumnIndex("ldrk_fwcs")));
                dataBean = dataBean1;
            }
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return dataBean;
    }

    /**
     * 修改住户信息
     */
    public void updateZhuHu(String userId, String idcard, String hu_id, GetUserBean.DataBean dataBean) {
        SQLiteDatabase db = null;
        try {
            db = openHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("xm", dataBean.getXm());
            values.put("xb", dataBean.getXb());
            values.put("minzu", dataBean.getMinzu());
            values.put("sfz", dataBean.getSfz());
            values.put("hj_hjxz", dataBean.getHj_hjxz() + "");
            values.put("lxdh", dataBean.getLxdh());
            values.put("ldrk_whcd", dataBean.getLdrk_whcd());
            values.put("ldrk_hunyin", dataBean.getLdrk_hunyin());
            values.put("ldrk_zzcs", dataBean.getLdrk_zzcs());
            values.put("ldrk_ljqcyzk", dataBean.getLdrk_ljqcyzk());
            values.put("ldrk_ljrq", dataBean.getLdrk_ljrq());
            values.put("ldrk_lxjzdrq", dataBean.getLdrk_lxjzdrq());
            values.put("beizhu", dataBean.getBeizhu());
            values.put("rylx", dataBean.getRylx() + "");
            values.put("ldrk_fwcs", dataBean.getLdrk_fwcs() + "");
            if (dataBean.getPicture() != null && !dataBean.equals("")) {
                values.put("picture", dataBean.getPicture() + "");
            }
            values.put("is_del", dataBean.getIs_del());
            values.put("new_picture", dataBean.getNew_picture() + "");
            db.update("t_ryxx", values, "sfz=? and jz_fwid=?", new String[]{idcard, hu_id});
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    /**
     * 删除住户信息  多条件删除
     */
    public void deleteZhuHu(String IDcard, String hu_id) {
        SQLiteDatabase db = null;

        try {
            db = openHelper.getWritableDatabase();
            db.delete("t_ryxx", "sfz=? and jz_fwid=?", new String[]{IDcard, hu_id});
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    /**
     * id 删除
     */
    public void deleteZhuHuId(String id) {
        SQLiteDatabase db = null;

        try {
            db = openHelper.getWritableDatabase();
            db.delete("t_ryxx", "id=?", new String[]{id});
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    /**
     * 修改街路巷流动人口数 ：流   建筑物编码
     */
    public void updateLiu(int ldrk_count, String floorCode) {
        SQLiteDatabase db = null;
        try {
            db = openHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("ldrk_count", ldrk_count);
            db.update("t_jzw", values, "jzw_bm=?", new String[]{floorCode});
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    /**
     * 查询流动人口数量
     *
     * @param floorCode
     * @return
     */

    // 单元查询  楼查询
    public List<MainBean.DataBean.JzwListBean> selectLiu(String floorCode) {
        List<MainBean.DataBean.JzwListBean> list = new ArrayList<MainBean.DataBean.JzwListBean>();
        SQLiteDatabase db = null;
        // 获取一个光标对象
        Cursor cursor = null;
        try {
            db = openHelper.getReadableDatabase();
            cursor = db.query("t_jzw", new String[]{"ldrk_count"}, "jzw_bm=?", new String[]{floorCode}, null, null, null);
            MainBean.DataBean.JzwListBean danBean = null;
            while (cursor.moveToNext()) {
                danBean = new MainBean.DataBean.JzwListBean();
                danBean.setLdrk_count(cursor.getInt(cursor.getColumnIndex("ldrk_count")));
                list.add(danBean);
            }
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return list;
    }
}
