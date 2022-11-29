package com.example.catch_test12;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "catchDB_12.db";
    //static final int DATABASE_VERSION = 2;

    public DBHelper(Context context, int version) {
        super(context, DATABASE_NAME, null, version);
    }
/*
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
*/

    @Override
    public void onCreate(SQLiteDatabase db) {       //생성 : 테이블명, 속성 값
        db.execSQL("CREATE TABLE catchTBL12 ( name TEXT, info TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS catchTBL12");
        onCreate(db);
    }

}