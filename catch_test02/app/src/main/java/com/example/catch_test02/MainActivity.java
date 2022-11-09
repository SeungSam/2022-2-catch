package com.example.catch_test02;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    myDBHelper myDBHelper;
    EditText edtNum, edtTitle, edtWriter, edtLike;
    EditText edtNumResult, edtTitleResult, edtWriterResult, edtLikeResult;
    Button btnInit, btnInsert, btnSelect;
    SQLiteDatabase sqlDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("catch02");

        edtNum = (EditText) findViewById(R.id.edtNum);
        edtTitle = (EditText) findViewById(R.id.edtTitle);
        edtWriter = (EditText) findViewById(R.id.edtWriter);
        edtLike = (EditText) findViewById(R.id.edtLike);

        edtNumResult = (EditText) findViewById(R.id.edtNumResult);
        edtTitleResult = (EditText) findViewById(R.id.edtTitleResult);
        edtWriterResult = (EditText) findViewById(R.id.edtWriterResult);
        edtLikeResult = (EditText) findViewById(R.id.edtLikeResult);

        btnInit = (Button) findViewById(R.id.btnInit);
        btnInsert = (Button) findViewById(R.id.btnInsert);
        btnSelect = (Button) findViewById(R.id.btnSelect);

        myDBHelper = new myDBHelper(this);

        btnInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlDB = myDBHelper.getWritableDatabase();
                myDBHelper.onUpgrade(sqlDB,1,2);
                sqlDB.close();
            }
        });
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                sqlDB = myDBHelper.getWritableDatabase();
                sqlDB.execSQL("INSERT INTO catchTBL02 VALUES ( " + edtNum.getText().toString() + ", '" + edtTitle.getText().toString() +
                        "', '" + edtWriter.getText().toString() + "', " + edtLike.getText().toString() + ");");
                sqlDB.close();
                Toast.makeText(getApplicationContext(),"입력됨",0).show();
            }
        });
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlDB = myDBHelper.getReadableDatabase();
                Cursor cursor;
                cursor = sqlDB.rawQuery("SELECT * FROM catchTBL02;",null);

                String strNum = "번호"+"\r\n"+"\r\n";
                String strTitle = "제목"+"\r\n"+"\r\n";
                String strWriter = "작성자"+"\r\n"+"\r\n";
                String strLike = "추천수"+"\r\n"+"\r\n";


                while (cursor.moveToNext()){
                    strNum += cursor.getString(0) + "\r\n";
                    strTitle += cursor.getString(1) + "\r\n";
                    strWriter += cursor.getString(2) + "\r\n";
                    strLike += cursor.getString(3) + "\r\n";
                }
                edtNumResult.setText(strNum);
                edtTitleResult.setText(strTitle);
                edtWriterResult.setText(strWriter);
                edtLikeResult.setText(strLike);

                cursor.close();
                sqlDB.close();
            }
        });
    }

    public class myDBHelper extends SQLiteOpenHelper {
        public myDBHelper(Context context) {
            super(context, "catchDB02", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE catchTBL02 ( cNum INTEGER, cTitle CHAR(20), cWriter CHAR(20), cLike INTEGER);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS catchTBL02");
            onCreate(db);

        }
    }

}