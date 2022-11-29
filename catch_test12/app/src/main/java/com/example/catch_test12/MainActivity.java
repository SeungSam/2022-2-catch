package com.example.catch_test12;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editText, editText2;       //글번호 글제목 입력
    ListView listView, listView2;       //출력될 리스트뷰

    DBHelper dbHelper;
    SQLiteDatabase db = null;
    Cursor cursor;
    ArrayAdapter adapter, adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
        listView = findViewById(R.id.listView);
        listView2 = findViewById(R.id.listView2);

        dbHelper = new DBHelper(this, 4);
        db = dbHelper.getWritableDatabase();    // 읽기/쓰기 모드로 데이터베이스를 오픈
    }

    public void listUpdate(View v) {            //DB에 저장된 테이블을 리스트뷰로 출력
        cursor = db.rawQuery("SELECT * FROM catchTBL12", null);
        startManagingCursor(cursor);    //엑티비티의 생명주기와 커서의 생명주기를 같게 한다.
        adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1);
        adapter2 = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1);
        while (cursor.moveToNext()) {
            adapter.add(cursor.getString(0));
            adapter2.add(cursor.getString(1));
        }

        /*
        cursor.moveToFirst();
        cursor.moveToPrevious();
        cursor.moveToPosition(2);
        */

        listView.setAdapter(adapter);
        listView2.setAdapter(adapter2);
    }

    public void insert(View v) {        //입력값을 DB에 추가
        String name = editText.getText().toString();
        String info = editText2.getText().toString();
        db.execSQL("INSERT INTO catchTBL12 VALUES ('" + name + "', '" + info + "');");

        Toast.makeText(getApplicationContext(), "추가 성공", Toast.LENGTH_SHORT).show();

        editText.setText("");
        editText2.setText("");
    }

    public void delete(View v) {        //DB에 저장된 값 삭제
        String name = editText.getText().toString();
        db.execSQL("DELETE FROM catchTBL12 WHERE name = '" + name + "';");
    }
}