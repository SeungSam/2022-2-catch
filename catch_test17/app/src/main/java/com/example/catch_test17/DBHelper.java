package com.example.catch_test17;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    //서브 액티비티에서 입력받을 내용 DB

    static final String DATABASE_NAME = "catchDB_17.db";

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
    //테이블명(글번호, 글제목, 작성자, 글내용, 추천수, 작성날짜)
    public void onCreate(SQLiteDatabase db) {       //생성 : 테이블명, 속성 값
        db.execSQL("CREATE TABLE catchTBL17 ( num INT, title TEXT, user TEXT, content TEXT, recommend INT, date TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS catchTBL17");
        onCreate(db);
    }

}