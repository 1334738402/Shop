package com.zhang.shop.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by 管理员账户 on 2017/6/5.
 * create table user(_id integer primary key autoincrement,name varchar(20),pwd varchar(20))
 */

public class SQL_NewsTitle_Mangner {
    Context context;
    SQL_News sql_newsTitle;

    public SQL_NewsTitle_Mangner(Context context) {
        this.context = context;
        this.sql_newsTitle = new SQL_News(context, "Data");
    }

    /**
     * 查询是否存在数据库
     *
     * @return
     */
    public boolean sql_SelectAll(String name, String pwd) {
        SQLiteDatabase writableDatabase = sql_newsTitle.getWritableDatabase();
        Cursor cursor = writableDatabase.rawQuery("select * from user", null);
        while (cursor.moveToNext()) {
            if (cursor.getString(1).equals(name) && cursor.getString(2).equals(pwd))
                return true;
        }
        writableDatabase.close();
        return false;
    }

    /**
     * 新增Title
     */
    public boolean sql_Insert(String name, String pwd) {
        if (!sql_SelectAll(name, pwd)) {
            SQLiteDatabase sQLiteDatabase = sql_newsTitle.getReadableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", name);
            contentValues.put("pwd", pwd);
            sQLiteDatabase.insert("user", null, contentValues);
            sQLiteDatabase.close();
            return true;
        } else
            return false;
    }


    /**
     * 删除Title
     *
     * @param title
     */
    public void sql_Delete(String title) {
        SQLiteDatabase sQLiteDatabase = sql_newsTitle.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);

        String[] args = {title};
        sQLiteDatabase.delete("newstitle", "title=?", args);
        sQLiteDatabase.close();
    }

}
