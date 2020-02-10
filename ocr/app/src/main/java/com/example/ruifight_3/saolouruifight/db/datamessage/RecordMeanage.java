package com.example.ruifight_3.saolouruifight.db.datamessage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ruifight_3.saolouruifight.db.DBOpenHelper;
import com.example.ruifight_3.saolouruifight.db.util.bean.RecordBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RuiFight-3 on 2018/7/18.
 */

public class RecordMeanage {

    DBOpenHelper openHelper;
    Context context;

    public RecordMeanage(Context conetxt) {
        this.context = conetxt;
        openHelper = DBOpenHelper.getInstance(context, false);
    }

    // 添加记录
    public void addRecord(RecordBean recordBean) {
        SQLiteDatabase db = null;
        try {
            db = openHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("type", recordBean.getType());
            values.put("statement", recordBean.getStatement());
            values.put("operateDate", recordBean.getOperateDate());
            values.put("operateUser", recordBean.getOperateUser());
            values.put("tableName", recordBean.getTableName());
            db.insert("operate_log", null, values);
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    //查询记录表上传
    public List<RecordBean> selectRecord() {
        List<RecordBean> list = new ArrayList<>();
        SQLiteDatabase db = null;
        // 获取一个光标对象
        Cursor cursor = null;
        try {
            db = openHelper.getReadableDatabase();
            cursor = db.query("operate_log", null, null, null, null, null, null);
            RecordBean recordBean = null;
            while (cursor.moveToNext()) {
                recordBean = new RecordBean();
                recordBean.setId(cursor.getInt(cursor.getColumnIndex("id")));
                recordBean.setType(cursor.getString(cursor.getColumnIndex("type")));
                recordBean.setStatement(cursor.getString(cursor.getColumnIndex("statement")));
                recordBean.setOperateDate(cursor.getString(cursor.getColumnIndex("operateDate")));
                recordBean.setOperateUser(cursor.getString(cursor.getColumnIndex("operateUser")));
                recordBean.setTableName(cursor.getString(cursor.getColumnIndex("tableName")));
                list.add(recordBean);
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

    //清表
    public void deleTabData() {
        SQLiteDatabase db = null;
        try {
            db = openHelper.getWritableDatabase();
            db.execSQL("delete from operate_log");
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    /**
     * 有多少条操作记录
     *
     * @return
     */
    public Long recordData() {
        SQLiteDatabase db = null;
        // 获取一个光标对象
        Cursor cursor = null;
        Long count1 = null;
        try {
            db = openHelper.getReadableDatabase();
            cursor = db.rawQuery("select count(*)from operate_log", null);
            cursor.moveToFirst();
            Long count = cursor.getLong(0);
            count1 = count;
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
        return count1;
    }

    /**
     * 删除记录表单个数据
     *
     * @param rowid
     */
    public void deleteItemData(String rowid) {
        SQLiteDatabase db = null;
        try {
            db = openHelper.getWritableDatabase();
            db.delete("operate_log", "id=?", new String[]{rowid});
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    /**
     * 根据类型删除记录表数据
     * @param type
     */
    public void deleteType(String type){
        SQLiteDatabase db = null;
        try {
            db = openHelper.getWritableDatabase();
            db.delete("operate_log", "type=?", new String[]{type});
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }
}
