package com.example.catch_test16;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView listViewNum, listViewTitle, listViewRecommend;       //출력될 리스트뷰
    private int count=1;
    int selectListView;

    DBHelper dbHelper;
    SQLiteDatabase db = null;
    Cursor cursor;
    ArrayAdapter num_adapter, title_adapter, recommend_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewNum = findViewById(R.id.listViewNum);               //글번호 출력 리스트뷰
        listViewTitle = findViewById(R.id.listViewTitle);           //글제목 출력 리스트뷰
        listViewRecommend = findViewById(R.id.listViewRecommend);     //추천수 출력 리스트뷰

        dbHelper = new DBHelper(this, 1);
        db = dbHelper.getWritableDatabase();    // 읽기/쓰기 모드로 데이터베이스를 오픈

        //게시판 메인화면 : 글번호, 제목, 추천수 출력
        cursor = db.rawQuery("SELECT num, title, recommend FROM catchTBL16", null);

        startManagingCursor(cursor);    //엑티비티의 생명주기와 커서의 생명주기를 같게 한다.
        num_adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1);
        title_adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1);
        recommend_adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1);

        while (cursor.moveToNext()) {
            num_adapter.add(cursor.getString(0));
            title_adapter.add(cursor.getString(1));
            recommend_adapter.add(cursor.getString(2));
            count++;
        }

        listViewNum.setAdapter(num_adapter);
        listViewTitle.setAdapter(title_adapter);
        listViewRecommend.setAdapter(recommend_adapter);

        listViewTitle.setOnItemClickListener(new AdapterView.OnItemClickListener() {                //리스트뷰 클릭 이벤트
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(getApplicationContext(), (i+1)+"번째 아이템", Toast.LENGTH_SHORT).show();
                //인텐트 선언 및 정의
                selectListView =  i+1;

                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("selectListView", selectListView);

                //액티비티 이동
                startActivity(intent);
            }
        });


        Button button = findViewById(R.id.btn_write); //xml에서 생성한 id 매치
        //버튼에 클릭 이벤트 적용
        button.setOnClickListener(new View.OnClickListener() {      //글쓰기 버튼
            @Override
            public void onClick(View view) {
                //인텐트 선언 및 정의
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                intent.putExtra("numCount", count);

                //액티비티 이동
                startActivity(intent);
            }
        });

    }
}