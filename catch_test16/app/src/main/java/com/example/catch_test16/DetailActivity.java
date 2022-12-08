package com.example.catch_test16;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    TextView title_tv, date_tv, countRecommend_tv, content_tv, user_tv;

    DBHelper dbHelper;
    SQLiteDatabase db = null;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        int selectNum = intent.getIntExtra("selectListView",0);

        title_tv = findViewById(R.id.title_tv);                     //글제목 출력 텍스트뷰
        date_tv = findViewById(R.id.date_tv);                       //작성일 출력 텍스트뷰
        countRecommend_tv = findViewById(R.id.countRecommend_tv);   //추천수 출력 텍스트뷰
        content_tv = findViewById(R.id.content_tv);                 //글내용 출력 텍스트뷰
        user_tv = findViewById(R.id.user_tv);                       //작성자 출력 텍스트뷰


        dbHelper = new DBHelper(this, 1);
        db = dbHelper.getWritableDatabase();    // 읽기/쓰기 모드로 데이터베이스를 오픈
        //Toast.makeText(getApplicationContext(), "selectNum="+selectNum, Toast.LENGTH_SHORT).show();

        //클릭한 리스트뷰에 대한 글 출력
        cursor = db.rawQuery("SELECT * FROM catchTBL16 WHERE num = "+selectNum+";", null);

        while (cursor.moveToNext()) {
            title_tv.setText(cursor.getString(1));
            date_tv.setText(cursor.getString(5));
            countRecommend_tv.setText(cursor.getString(4));
            content_tv.setText(cursor.getString(3));
            user_tv.setText(cursor.getString(2));
        }


        Button moveBack_button2 = findViewById(R.id.moveBack_button2); //이전 버튼
        //버튼에 클릭 이벤트 적용
        moveBack_button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //인텐트 선언 및 정의
                Intent intent = new Intent(DetailActivity.this, MainActivity.class);

                //액티비티 이동
                startActivity(intent);
            }
        });

        Button recommend_button = findViewById(R.id.recommend_button); //추천 버튼
        //버튼에 클릭 이벤트 적용
        recommend_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //인텐트 선언 및 정의
                Intent intent = new Intent(DetailActivity.this, MainActivity.class);

                //액티비티 이동
                startActivity(intent);
            }
        });


    }
}
