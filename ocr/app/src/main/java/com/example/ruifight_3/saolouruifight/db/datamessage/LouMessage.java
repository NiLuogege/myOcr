package com.example.ruifight_3.saolouruifight.db.datamessage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ruifight_3.saolouruifight.db.DBOpenHelper;
import com.example.ruifight_3.saolouruifight.ui.bean.HomeBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RuiFight-3 on 2018/6/28.
 * 张振 楼数据库操作 dao
 */

public class LouMessage {
    DBOpenHelper openHelper;
    Context context;

    public LouMessage(Context conetxt) {
        this.context = conetxt;
        openHelper = DBOpenHelper.getInstance(context, false);
    }

    // 添加楼
    public void addLou(HomeBean.DataBean.JlxListBean louBean) {
        SQLiteDatabase db = null;
        try {
            db = openHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
//            values.put("username", louBean.getUsername());
//            values.put("addressName", louBean.getAddressName());
//            values.put("floorCode", louBean.getFloorCode());
            db.insert("loumessgae", null, values);
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    // 街路巷列表查询
    public List<HomeBean.DataBean.JlxListBean> selectLou() {
        List<HomeBean.DataBean.JlxListBean> list = new ArrayList<HomeBean.DataBean.JlxListBean>();
        SQLiteDatabase db = null;
        // 获取一个光标对象
        Cursor cursor = null;
        try {
            db = openHelper.getReadableDatabase();
            cursor = db.query("t_jzw_jlx", null, null, null, null, null, null);
            HomeBean.DataBean.JlxListBean louBean = null;
            while (cursor.moveToNext()) {
                louBean = new HomeBean.DataBean.JlxListBean();
                louBean.setJlxmc(cursor.getString(cursor.getColumnIndex("jlxmc")));
                louBean.setJlxdm(cursor.getString(cursor.getColumnIndex("jlxdm")));
                louBean.setDzysqx(cursor.getString(cursor.getColumnIndex("dzysqx")));
                louBean.setPcsdm(cursor.getString(cursor.getColumnIndex("pcsdm")));
                louBean.setPcsmc(cursor.getString(cursor.getColumnIndex("pcsmc")));
                louBean.setSssq_dm(cursor.getString(cursor.getColumnIndex("sssq_dm")));
                louBean.setSssq_mc(cursor.getString(cursor.getColumnIndex("sssq_mc")));
                louBean.setJwhdm(cursor.getString(cursor.getColumnIndex("jwhdm")));
                louBean.setJwhmc(cursor.getString(cursor.getColumnIndex("jwhmc")));
                louBean.setJwzrqdm(cursor.getString(cursor.getColumnIndex("jwzrqdm")));
                louBean.setJwzrqmc(cursor.getString(cursor.getColumnIndex("jwzrqmc")));
                louBean.setIs_del(cursor.getString(cursor.getColumnIndex("is_del")));
                louBean.setImp_date(cursor.getLong(cursor.getColumnIndex("imp_date")));

                list.add(louBean);
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

    // 楼更新
    public void updateLou(String addressName, String floorCode) {

        SQLiteDatabase db = null;
        try {
            db = openHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("addressName", addressName);
            values.put("floorCode", floorCode);
            db.update("loumessgae", values, "floorCode=" + floorCode, null);
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    //楼删除
    public void deleteLou(String floorCode) {
        SQLiteDatabase db = null;

        try {
            db = openHelper.getWritableDatabase();
//            db.delete("loumessgae","floorCode",new String[]{floorCode});
            db.delete("loumessgae", "floorCode=?", new String[]{floorCode});
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    //请空楼表数据
    public void deleteTable() {
        SQLiteDatabase db = null;

        try {
            db = openHelper.getWritableDatabase();
            db.execSQL("delete from loumessgae");
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    /**
     * 判断某张表是否存在
     *
     * @param
     * @return
     */
    public boolean tabbleIsExist(String tableName) {
        boolean result = false;
        if (tableName == null) {
            return false;
        }
        SQLiteDatabase db = null;
        // 获取一个光标对象
        Cursor cursor = null;
        try {
            db = openHelper.getReadableDatabase();

            String sql = "select count(*) as c from Sqlite_master  where type ='table' and name ='" + tableName.trim() + "' ";
            cursor = db.rawQuery(sql, null);
            if (cursor.moveToNext()) {
                int count = cursor.getInt(0);
                if (count > 0) {
                    result = true;
                }
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
        return result;
    }
}
