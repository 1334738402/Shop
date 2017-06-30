package com.zhang.shop.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 管理员账户 on 2017/6/5.
 */

public class SQL_News extends SQLiteOpenHelper {
    public SQL_News(Context context, String name ){
        super(context, name, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table user(_id integer primary key autoincrement,name varchar(20),pwd varchar(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
