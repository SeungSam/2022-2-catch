package com.example.catch_test17;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RegisterActivity extends AppCompatActivity {
    EditText title_et, content_et, user_et;

    DBHelper dbHelper;
    SQLiteDatabase db = null;

    //글 작성 날짜 변수
    long now = System.currentTimeMillis();
    Date date = new Date(now);
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Intent intent = getIntent();
        //글번호 카운트
        int count = intent.getIntExtra("numCount",1);

        title_et = findViewById(R.id.title_et);
        content_et = findViewById(R.id.content_et);
        user_et = findViewById(R.id.user_et);

        dbHelper = new DBHelper(this, 1);
        db = dbHelper.getWritableDatabase();    // 읽기/쓰기 모드로 데이터베이스를 오픈

        Button moveBack_button1 = findViewById(R.id.moveBack_button1); //이전 버튼
        //버튼에 클릭 이벤트 적용
        moveBack_button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //인텐트 선언 및 정의
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);

                //액티비티 이동
                startActivity(intent);
            }
        });


        Button reg_button = findViewById(R.id.reg_button); //xml에서 생성한 id 매치
        //버튼에 클릭 이벤트 적용
        reg_button.setOnClickListener(new View.OnClickListener() {      //글쓰기 버튼
            @Override
            public void onClick(View view) {
                int num = count;

                String title = title_et.getText().toString();   //글제목
                String content = content_et.getText().toString();   //글내용
                String user = user_et.getText().toString();   //작성자
                int recommend = 0;
                String getTime = dateFormat.format(date);

                db.execSQL("INSERT INTO catchTBL17 VALUES (" + num + ", '" + title + "', '"
                        + user + "', '" + content +"', '" + recommend + "', '" + getTime +"');");

                Toast.makeText(getApplicationContext(), "추가 성공", Toast.LENGTH_SHORT).show();

                title_et.setText("");
                content_et.setText("");
                user_et.setText("");

                //인텐트 선언 및 정의
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);

                //액티비티 이동
                startActivity(intent);
            }
        });




    }
}
