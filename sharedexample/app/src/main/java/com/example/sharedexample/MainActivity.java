package com.example.sharedexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText et_save;
    String shared="file";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_save=(EditText)findViewById(R.id.et_save);

        SharedPreferences sharedPreferences=getSharedPreferences(shared,0);

        String value = sharedPreferences.getString("jurl","");
        et_save.setText(value);

    }

    @Override
    protected void onDestroy() {//앱이 종료됐을때 앱이 파괴될 때 실행할 수 있는
        super.onDestroy();

        SharedPreferences sharedPreferences=getSharedPreferences(shared,0);

        SharedPreferences.Editor editor=sharedPreferences.edit();//연결

        String value=et_save.getText().toString();//안드로이드 키보드에 입력한 값을 스트링형태로 빼겠다

        editor.putString("jurl",value);//jurl이라는 이름으로 value를 불러오겠다

        editor.commit();//save를 완료해라

    }
}
