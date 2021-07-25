package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class result extends AppCompatActivity {

    private TextView tv;

    private Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        bt=findViewById(R.id.bt);

        tv=findViewById(R.id.tv);

        Intent intent=getIntent();
        String str=intent.getStringExtra("str");
        tv.setText(str);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(result.this,MainActivity.class);
                startActivities(new Intent[]{intent});
            }
        });
    }
}