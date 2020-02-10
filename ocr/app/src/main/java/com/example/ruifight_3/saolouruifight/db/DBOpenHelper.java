package com.example.ruifight_3.saolouruifight.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.ruifight_3.saolouruifight.MyApi;

/**
 * Created by RuiFight-3 on 2018/6/27.
 * <p>
 * 张振 创建数据库表
 */

public class DBOpenHelper extends SQLiteOpenHelper {
    //ruifightSaoLou.db
    private static final int version = 23;//数据库版本
    private static DBOpenHelper dbOpenHelper;

    public DBOpenHelper(Context context) {
        super(context, MyApi.DBNAME, null, version);
    }

    /**
     * 实例
     *
     * @param
     */
    public static DBOpenHelper getInstance(Context context, boolean isone) {
        if (isone) {
            if (dbOpenHelper != null) {
                dbOpenHelper.close();
                dbOpenHelper = null;
            }
            return dbOpenHelper;
        } else {
            if (dbOpenHelper == null) {
                dbOpenHelper = new DBOpenHelper(context);
            }
            return dbOpenHelper;
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e("DBOpenHelper", "DBOpenHelperDBOpenHelperDBOpenHelperDBOpenHelper");
        //小区表
        //db.execSQL("CREATE TABLE IF NOT EXISTS loumessgae (id integer primary key autoincrement,username varchar(60), addressName varchar(60), floorCode varchar(60))");
//        //单元表
//        db.execSQL("CREATE TABLE IF NOT EXISTS danyuan (id integer primary key autoincrement,username varchar(60),floorCode varchar(60), unit varchar(60))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.e("DBOpenHelper", "onUpgradeonUpgradeonUpgradeonUpgrade");
        // 删除表操作   会重新创建
        // db.execSQL("DROP TABLE IF EXISTS loumessgae");
//        db.execSQL("DROP TABLE IF EXISTS danyuan");
//        onCreate(db);
    }
}
