package com.example.ruifight_3.saolouruifight.db.datamessage;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ruifight_3.saolouruifight.db.DBOpenHelper;

/**
 * Created by RuiFight-3 on 2018/7/17.
 */

public class HomeMessage {

    DBOpenHelper openHelper;
    Context context;

    public HomeMessage(Context conetxt) {
        this.context = conetxt;
        openHelper = DBOpenHelper.getInstance(context, false);

    }

    /**
     * 共有建筑物多少
     *
     * @return
     */
    public Long homeData() {
        SQLiteDatabase db = null;
        // 获取一个光标对象
        Cursor cursor = null;
        Long count1 = null;
        try {
            db = openHelper.getReadableDatabase();
            cursor = db.rawQuery("select count(*)from t_jzw", null);
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
     * 房屋多少
     */
    public Long homeFangWuData() {

        SQLiteDatabase db = null;
        // 获取一个光标对象
        Cursor cursor = null;
        Long count2 = null;
        try {
            db = openHelper.getReadableDatabase();
            cursor = db.rawQuery("select count(*)from t_jzw_fangjian", null);
            cursor.moveToFirst();
            Long count = cursor.getLong(0);
            count2 = count;
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
        return count2;
    }

    /**
     * 采集房主信息多少户
     */
    public Long fangZhuMessageCount() {

        SQLiteDatabase db = null;
        // 获取一个光标对象
        Cursor cursor = null;
        Long count2 = null;
        try {
            db = openHelper.getReadableDatabase();
            cursor = db.rawQuery("select count(*)from t_jzw_fangjian", null);
            cursor.moveToFirst();
            Long count = cursor.getLong(0);
            count2 = count;
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
        return count2;
    }
}
