package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ImageView btn;

    private String s1,s2,s3,str;

    private ListView list;

    private EditText et1,et2,et3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1=findViewById(R.id.et1);
        et2=findViewById(R.id.et2);
        et3=findViewById(R.id.et3);

        list=(ListView)findViewById(R.id.list);

        List<String> data=new ArrayList<>();
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,(data));
        list.setAdapter(adapter);

        btn=findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str="계산결과 = ";
                s1=et1.getText().toString();
                s2=et2.getText().toString();
                s3=et3.getText().toString();
                if (s2.equals("+")){
                    int i=(Integer.parseInt(s1)+Integer.parseInt(s3));
                    str+=i;
                }
                if (s2.equals("-")){
                    int i=(Integer.parseInt(s1)-Integer.parseInt(s3));
                    str+=i;
                }
                if (s2.equals("*")){
                    int i=(Integer.parseInt(s1)*Integer.parseInt(s3));
                    str+=i;
                }
                if (s2.equals("%")){
                    int i=(Integer.parseInt(s1)%Integer.parseInt(s3));
                    str+=i;
                }
                if (s2.equals("/")){
                    int i=(Integer.parseInt(s1)/Integer.parseInt(s3));
                    str+=i;
                }
                data.add(str);
                adapter.notifyDataSetChanged();
                Intent intent = new Intent(MainActivity.this,result.class);
                intent.putExtra("str",str);
                startActivities(new Intent[]{intent});
            }
        });
    }
}