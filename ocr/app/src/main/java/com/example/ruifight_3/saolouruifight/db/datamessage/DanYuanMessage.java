package com.example.ruifight_3.saolouruifight.db.datamessage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ruifight_3.saolouruifight.db.DBOpenHelper;
import com.example.ruifight_3.saolouruifight.db.util.bean.jzw_bean;
import com.example.ruifight_3.saolouruifight.db.util.bean.t_jzw_fangjian;
import com.example.ruifight_3.saolouruifight.ui.bean.AddHouseBean;
import com.example.ruifight_3.saolouruifight.ui.bean.MainBean;
import com.example.ruifight_3.saolouruifight.ui.bean.SqlFangzhuBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RuiFight-3 on 2018/6/29.
 * 张振 单元表操作
 */

public class DanYuanMessage {
    DBOpenHelper openHelper;
    Context context;

    public DanYuanMessage(Context conetxt) {
        this.context = conetxt;
        openHelper = DBOpenHelper.getInstance(context, false);
    }

    // 添加单元
    public void addDan(MainBean.DataBean.JzwListBean unitsListBean) {
        SQLiteDatabase db = null;
        try {
            db = openHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
//            values.put("username",unitsListBean.getUsername());
//            values.put("floorCode",unitsListBean.getFloorCode());
//            values.put("unit", unitsListBean.getUnit());
            db.insert("danyuan", null, values);
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    // 单元查询  楼查询
    public List<MainBean.DataBean.JzwListBean> selectDan(String floorCode, int type) {
        List<MainBean.DataBean.JzwListBean> list = new ArrayList<MainBean.DataBean.JzwListBean>();
        SQLiteDatabase db = null;
        // 获取一个光标对象
        Cursor cursor = null;
        try {
            db = openHelper.getReadableDatabase();
            //关键字升序是asc，降序为desc；
            if (type == 1) {
                cursor = db.query("t_jzw", new String[]{"jzw_bm", "jzw_mc", "jzw_jlxdm", "jzw_jlxmc", "jzw_dizhi", "ldrk_count"}, "jzw_jlxdm=?", new String[]{floorCode}, null, null, "ldrk_count asc");
            } else if (type == 2) {
                cursor = db.query("t_jzw", new String[]{"jzw_bm", "jzw_mc", "jzw_jlxdm", "jzw_jlxmc", "jzw_dizhi", "ldrk_count"}, "jzw_jlxdm=?", new String[]{floorCode}, null, null, "ldrk_count desc");
            } else if (type == 4) {
                cursor = db.query("t_jzw", new String[]{"jzw_bm", "jzw_mc", "jzw_jlxdm", "jzw_jlxmc", "jzw_dizhi", "ldrk_count"}, "jzw_jlxdm=? and ldrk_count=?", new String[]{floorCode, "0"}, null, null, null);
            } else {
                cursor = db.query("t_jzw", new String[]{"jzw_bm", "jzw_mc", "jzw_jlxdm", "jzw_jlxmc", "jzw_dizhi", "ldrk_count"}, "jzw_jlxdm=?", new String[]{floorCode}, null, null, null);
            }

            MainBean.DataBean.JzwListBean danBean = null;
            while (cursor.moveToNext()) {
                danBean = new MainBean.DataBean.JzwListBean();
                danBean.setJzw_bm(cursor.getString(cursor.getColumnIndex("jzw_bm")));
                danBean.setJzw_mc(cursor.getString(cursor.getColumnIndex("jzw_mc")));
                danBean.setJzw_jlxdm(cursor.getString(cursor.getColumnIndex("jzw_jlxdm")));
                danBean.setJzw_jlxmc(cursor.getString(cursor.getColumnIndex("jzw_jlxmc")));
                danBean.setJzw_dizhi(cursor.getString(cursor.getColumnIndex("jzw_dizhi")));
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


    /**
     * 层户图查询
     */
    public List<t_jzw_fangjian> selectCeng(String floorCode) {

        List<t_jzw_fangjian> list = new ArrayList<t_jzw_fangjian>();
        SQLiteDatabase db = null;
        // 获取一个光标对象
        Cursor cursor = null;
        try {
            db = openHelper.getReadableDatabase();
            cursor = db.query("t_jzw_fangjian", null, "jzw_dm=?", new String[]{floorCode}, null, null, "dys asc,cs desc,hs asc");
            t_jzw_fangjian t_jzw_fangjian = null;
            while (cursor.moveToNext()) {
                t_jzw_fangjian = new t_jzw_fangjian();
                t_jzw_fangjian.setJzw_dm(cursor.getString(cursor.getColumnIndex("jzw_dm")));
                t_jzw_fangjian.setHu_id(cursor.getString(cursor.getColumnIndex("hu_id")));
                t_jzw_fangjian.setJzw_dizhi(cursor.getString(cursor.getColumnIndex("jzw_dizhi")));
                t_jzw_fangjian.setDys(cursor.getInt(cursor.getColumnIndex("dys")));
                t_jzw_fangjian.setCs(cursor.getInt(cursor.getColumnIndex("cs")));
                t_jzw_fangjian.setHs(cursor.getInt(cursor.getColumnIndex("hs")));
                t_jzw_fangjian.setDymc(cursor.getString(cursor.getColumnIndex("dymc")));
                t_jzw_fangjian.setHumc(cursor.getString(cursor.getColumnIndex("humc")));
                t_jzw_fangjian.setFjlx(cursor.getString(cursor.getColumnIndex("fjlx")));
                t_jzw_fangjian.setSsdwdm(cursor.getString(cursor.getColumnIndex("ssdwdm")));
                t_jzw_fangjian.setSsdwmc(cursor.getString(cursor.getColumnIndex("ssdwmc")));
                t_jzw_fangjian.setWhrybm(cursor.getString(cursor.getColumnIndex("whrybm")));
                //t_jzw_fangjian.setWhsj(cursor.get(cursor.getColumnIndex("whsj"))+"");
                t_jzw_fangjian.setIs_del(cursor.getString(cursor.getColumnIndex("is_del")));
                t_jzw_fangjian.setRenshu_ldrk(cursor.getInt(cursor.getColumnIndex("renshu_ldrk")));
                t_jzw_fangjian.setRenshu_rhfl(cursor.getInt(cursor.getColumnIndex("renshu_rhfl")));
                t_jzw_fangjian.setRenshu_hjrk(cursor.getInt(cursor.getColumnIndex("renshu_hjrk")));
                t_jzw_fangjian.setFz_xm(cursor.getString(cursor.getColumnIndex("fz_xm")));
                t_jzw_fangjian.setFz_lxdh(cursor.getString(cursor.getColumnIndex("fz_lxdh")));
                t_jzw_fangjian.setFz_sfz(cursor.getString(cursor.getColumnIndex("fz_sfz")));
                t_jzw_fangjian.setFz_fwjzlx(cursor.getString(cursor.getColumnIndex("fz_fwjzlx")));
                t_jzw_fangjian.setFz_is_del(cursor.getString(cursor.getColumnIndex("fz_is_del")));
                t_jzw_fangjian.setFz_whry(cursor.getString(cursor.getColumnIndex("fz_whry")));
                //t_jzw_fangjian.setFz_whsh(cursor.getInt(cursor.getColumnIndex("fz_whsh")));
                t_jzw_fangjian.setFz_bz(cursor.getString(cursor.getColumnIndex("fz_bz")));
                list.add(t_jzw_fangjian);
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

    /**
     * 建筑物查询
     */
    public jzw_bean selectJzw(String huid) {

        jzw_bean jzw_bean = new jzw_bean();
        SQLiteDatabase db = null;
        // 获取一个光标对象
        Cursor cursor = null;
        try {
            db = openHelper.getReadableDatabase();
            cursor = db.query("t_jzw", null, "jzw_bm=?", new String[]{huid}, null, null, null);
            // cursor = db.rawQuery("SELECT * from t_jzw where jzw_jlxdm="+"'"+huid+"'",null);
            jzw_bean jzw_bean1 = null;
            while (cursor.moveToNext()) {
                jzw_bean1 = new jzw_bean();
                jzw_bean1.setJzw_bm(cursor.getString(cursor.getColumnIndex("jzw_bm")));
                jzw_bean1.setJwh_mc(cursor.getString(cursor.getColumnIndex("jzw_mc")));
                jzw_bean1.setJzw_jlxdm(cursor.getString(cursor.getColumnIndex("jzw_jlxdm")));
                jzw_bean1.setJzw_jlxmc(cursor.getString(cursor.getColumnIndex("jzw_jlxmc")));
                jzw_bean1.setJzw_dizhi(cursor.getString(cursor.getColumnIndex("jzw_dizhi")));
                jzw_bean1.setDanwei_id(cursor.getString(cursor.getColumnIndex("danwei_id")));
                jzw_bean1.setDanwei_mc(cursor.getString(cursor.getColumnIndex("danwei_mc")));
                jzw_bean1.setSssq_dm(cursor.getString(cursor.getColumnIndex("sssq_dm")));
                jzw_bean1.setSssq_mc(cursor.getString(cursor.getColumnIndex("sssq_mc")));
                jzw_bean1.setLeixing(cursor.getString(cursor.getColumnIndex("leixing")));
                jzw_bean1.setPcsdm(cursor.getString(cursor.getColumnIndex("pcsdm")));
                jzw_bean1.setPcsmc(cursor.getString(cursor.getColumnIndex("pcsmc")));
                jzw_bean1.setJwh_dm(cursor.getString(cursor.getColumnIndex("jwh_dm")));
                jzw_bean1.setJwh_mc(cursor.getString(cursor.getColumnIndex("jwh_mc")));
                jzw_bean1.setJwzrq_dm(cursor.getString(cursor.getColumnIndex("jwzrq_dm")));
                jzw_bean1.setJwzrq_mc(cursor.getString(cursor.getColumnIndex("jwzrq_mc")));
                jzw_bean1.setXt_zhgxsj(cursor.getString(cursor.getColumnIndex("xt_zhgxsj")));
                jzw_bean1.setImp_date(cursor.getString(cursor.getColumnIndex("imp_date")));
                jzw_bean1.setZulin_qssj(cursor.getString(cursor.getColumnIndex("zulin_qssj")));
                jzw_bean1.setZulin_jzsj(cursor.getString(cursor.getColumnIndex("zulin_jzsj")));
                jzw_bean1.setDs_dys(cursor.getInt(cursor.getColumnIndex("ds_dys")));
                jzw_bean1.setDs_cs(cursor.getInt(cursor.getColumnIndex("ds_cs")));
                jzw_bean1.setDs_hs(cursor.getInt(cursor.getColumnIndex("ds_hs")));
                jzw_bean1.setDx_dys(cursor.getInt(cursor.getColumnIndex("dx_dys")));
                jzw_bean1.setDx_hs(cursor.getInt(cursor.getColumnIndex("dx_hs")));
                jzw_bean1.setDx_cs(cursor.getInt(cursor.getColumnIndex("dx_cs")));
                jzw_bean1.setCjryid(cursor.getString(cursor.getColumnIndex("cjryid")));
                jzw_bean1.setCjrymc(cursor.getString(cursor.getColumnIndex("cjrymc")));
                jzw_bean1.setWhrymc(cursor.getString(cursor.getColumnIndex("whrymc")));
                jzw_bean1.setWhsj(cursor.getString(cursor.getColumnIndex("whsj")));
                jzw_bean1.setBeizhu(cursor.getString(cursor.getColumnIndex("beizhu")));
                jzw_bean1.setIs_del(cursor.getString(cursor.getColumnIndex("is_del")));

                jzw_bean = jzw_bean1;
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
        return jzw_bean;
    }

    /**
     * 房主查询
     */
    public SqlFangzhuBean selectHid(String hid) {
        SqlFangzhuBean sqlFangzhuBean = new SqlFangzhuBean();
        SQLiteDatabase db = null;
        // 获取一个光标对象
        Cursor cursor = null;
        try {
            db = openHelper.getReadableDatabase();
            cursor = db.query("t_jzw_fangjian", new String[]{"jzw_dm", "hu_id", "whsj", "fz_xm", "fz_lxdh", "fz_sfz", "fz_fwjzlx", "fz_hjdz", "fz_bz", "jzw_dizhi", "dys", "cs", "hs", "humc"}, "hu_id=?", new String[]{hid}, null, null, null);
            SqlFangzhuBean sqlFangzhuBean1 = null;
            while (cursor.moveToNext()) {
                sqlFangzhuBean1 = new SqlFangzhuBean();
                sqlFangzhuBean1.setJzw_dm(cursor.getString(cursor.getColumnIndex("jzw_dm")));
                sqlFangzhuBean1.setHu_id(cursor.getString(cursor.getColumnIndex("hu_id")));
                sqlFangzhuBean1.setWhsj(cursor.getInt(cursor.getColumnIndex("whsj")));
                sqlFangzhuBean1.setFz_xm(cursor.getString(cursor.getColumnIndex("fz_xm")));
                sqlFangzhuBean1.setFz_lxdh(cursor.getString(cursor.getColumnIndex("fz_lxdh")));
                sqlFangzhuBean1.setFz_sfs(cursor.getString(cursor.getColumnIndex("fz_sfz")));
                sqlFangzhuBean1.setFz_fwjzlx(cursor.getString(cursor.getColumnIndex("fz_fwjzlx")));
                sqlFangzhuBean1.setFz_hjdz(cursor.getString(cursor.getColumnIndex("fz_hjdz")));
                sqlFangzhuBean1.setFz_bz(cursor.getString(cursor.getColumnIndex("fz_bz")));
                sqlFangzhuBean1.setJzw_dizhi(cursor.getString(cursor.getColumnIndex("jzw_dizhi")));
                sqlFangzhuBean1.setDys(cursor.getString(cursor.getColumnIndex("dys")));
                sqlFangzhuBean1.setCs(cursor.getString(cursor.getColumnIndex("cs")));
                sqlFangzhuBean1.setHs(cursor.getString(cursor.getColumnIndex("hs")));
                sqlFangzhuBean1.setHumc(cursor.getString(cursor.getColumnIndex("humc")));

                sqlFangzhuBean = sqlFangzhuBean1;
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
        return sqlFangzhuBean;
    }

    /**
     * 修改房主信息
     */
    public void updateFangzhu(SqlFangzhuBean sqlFangzhuBean, String hu_id) {
        // db.execSQL("update t_jzw_fangjian set fz_xm='?',fz_lxdh='?',fz_sfz='?',fz_fwjzlx='?',fz_bz='?' where hu_id='?'",new Object[]{sqlFangzhuBean.getFz_xm(),sqlFangzhuBean.getFz_lxdh(),sqlFangzhuBean.getFz_sfs(),sqlFangzhuBean.getFz_fwjzlx(),sqlFangzhuBean.getFz_bz(),hu_id});
        SQLiteDatabase db = null;
        try {
            db = openHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("fz_xm", sqlFangzhuBean.getFz_xm());
            values.put("fz_lxdh", sqlFangzhuBean.getFz_lxdh());
            values.put("fz_sfz", sqlFangzhuBean.getFz_sfs());
            values.put("fz_fwjzlx", sqlFangzhuBean.getFz_fwjzlx());
            values.put("fz_hjdz", sqlFangzhuBean.getFz_hjdz());
            values.put("fz_bz", sqlFangzhuBean.getFz_bz());
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
     * 本地添加房主信息
     *
     * @param sqlFangzhuBean
     * @param hu_id
     */
    public void insertFangZhu(SqlFangzhuBean sqlFangzhuBean, String hu_id) {

        SQLiteDatabase db = null;
        try {
            db = openHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("hu_id", hu_id);
            values.put("fz_xm", sqlFangzhuBean.getFz_xm());
            values.put("fz_lxdh", sqlFangzhuBean.getFz_lxdh());
            values.put("fz_sfz", sqlFangzhuBean.getFz_sfs());
            values.put("fz_fwjzlx", sqlFangzhuBean.getFz_fwjzlx());
            values.put("fz_bz", sqlFangzhuBean.getFz_bz());
            db.insert("t_jzw_fangjian", null, values);
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    /**
     * 查询 房间人员
     *
     * @param floorCode
     * @return
     */
    public List<AddHouseBean.DataBean.RyListBean> selectRenYuan(String floorCode) {
        List<AddHouseBean.DataBean.RyListBean> list = new ArrayList<>();
        SQLiteDatabase db = null;
        // 获取一个光标对象
        Cursor cursor = null;
        try {
            db = openHelper.getReadableDatabase();
            cursor = db.query("t_ryxx", null, "jz_fwid=?", new String[]{floorCode}, null, null, null);
            AddHouseBean.DataBean.RyListBean ryListBean = null;
            while (cursor.moveToNext()) {
                ryListBean = new AddHouseBean.DataBean.RyListBean();
                ryListBean.setId(cursor.getInt(cursor.getColumnIndex("id")));
                ryListBean.setXm(cursor.getString(cursor.getColumnIndex("xm")));
                ryListBean.setSfz(cursor.getString(cursor.getColumnIndex("sfz")));
                ryListBean.setBieming(cursor.getString(cursor.getColumnIndex("bieming")));
                ryListBean.setXb(cursor.getString(cursor.getColumnIndex("xb")));
                ryListBean.setMinzu(cursor.getString(cursor.getColumnIndex("minzu")));
                ryListBean.setHj_ssx(cursor.getString(cursor.getColumnIndex("hj_ssx")));
                ryListBean.setHj_hjxz(cursor.getString(cursor.getColumnIndex("hj_hjxz")));
                ryListBean.setLxdh(cursor.getString(cursor.getColumnIndex("lxdh")));
                ryListBean.setJz_fwid(cursor.getString(cursor.getColumnIndex("jz_fwid")));
                ryListBean.setJz_jzwbm(cursor.getString(cursor.getColumnIndex("jz_jzwbm")));
                ryListBean.setDizhi_jzwdz(cursor.getString(cursor.getColumnIndex("dizhi_jzwdz")));
                ryListBean.setDizhi_xz(cursor.getString(cursor.getColumnIndex("dizhi_xz")));
                ryListBean.setRylx(cursor.getString(cursor.getColumnIndex("rylx")));
                ryListBean.setLdrk_whcd(cursor.getString(cursor.getColumnIndex("ldrk_whcd")));
                ryListBean.setLdrk_hunyin(cursor.getString(cursor.getColumnIndex("ldrk_hunyin")));
                ryListBean.setLdrk_zzcs(cursor.getString(cursor.getColumnIndex("ldrk_zzcs")));
                ryListBean.setLdrk_zzsy(cursor.getString(cursor.getColumnIndex("ldrk_zzsy")));
                ryListBean.setLdrk_ljqcyzk(cursor.getString(cursor.getColumnIndex("ldrk_ljqcyzk")));
                ryListBean.setLdrk_xcyzk(cursor.getString(cursor.getColumnIndex("ldrk_xcyzk")));
                ryListBean.setLdrk_ljrq(cursor.getInt(cursor.getColumnIndex("ldrk_ljrq")));
                ryListBean.setLdrk_fwcs(cursor.getString(cursor.getColumnIndex("ldrk_fwcs")));
                ryListBean.setCjsj(cursor.getInt(cursor.getColumnIndex("cjsj")));
                ryListBean.setIs_del(cursor.getString(cursor.getColumnIndex("is_del")));
                ryListBean.setWhry(cursor.getString(cursor.getColumnIndex("whry")));
                ryListBean.setWhsj(cursor.getInt(cursor.getColumnIndex("whsj")));
                ryListBean.setBeizhu(cursor.getString(cursor.getColumnIndex("beizhu")));
                ryListBean.setPicture(cursor.getString(cursor.getColumnIndex("picture")));

                list.add(ryListBean);
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


    //单元删除
    public void deleteLou(String floorCode) {
        SQLiteDatabase db = null;

        try {
            db = openHelper.getWritableDatabase();
//            db.delete("loumessgae","floorCode",new String[]{floorCode});
            db.delete("danyuan", "floorCode=?", new String[]{floorCode});
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    //请空单元表数据
    public void deleteTable() {
        SQLiteDatabase db = null;

        try {
            db = openHelper.getWritableDatabase();
            db.execSQL("delete from danyuan");
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    //小区号模糊搜索
    public List<MainBean.DataBean.JzwListBean> dimSuosuo(String floorCode, String... str) {
        List<MainBean.DataBean.JzwListBean> list = new ArrayList<MainBean.DataBean.JzwListBean>();
        SQLiteDatabase db = null;
        // 获取一个光标对象
        Cursor cursor = null;
        try {
            db = openHelper.getReadableDatabase();
            cursor = db.query("t_jzw", new String[]{"jzw_bm", "jzw_mc", "jzw_jlxdm", "jzw_jlxmc", "jzw_dizhi", "ldrk_count"}, "jzw_jlxdm=? and jzw_dizhi" + "  LIKE ? ",
                    new String[]{floorCode, "%" + str[0] + "%"}, null, null, null);
            MainBean.DataBean.JzwListBean danBean = null;
            while (cursor.moveToNext()) {
                danBean = new MainBean.DataBean.JzwListBean();
                danBean.setJzw_bm(cursor.getString(cursor.getColumnIndex("jzw_bm")));
                danBean.setJzw_mc(cursor.getString(cursor.getColumnIndex("jzw_mc")));
                danBean.setJzw_jlxdm(cursor.getString(cursor.getColumnIndex("jzw_jlxdm")));
                danBean.setJzw_jlxmc(cursor.getString(cursor.getColumnIndex("jzw_jlxmc")));
                danBean.setJzw_dizhi(cursor.getString(cursor.getColumnIndex("jzw_dizhi")));
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
