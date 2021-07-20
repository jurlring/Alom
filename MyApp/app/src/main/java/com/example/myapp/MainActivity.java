package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Item> arrayList;
    private Adapter adapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private EditText editText;

    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner=(Spinner)findViewById(R.id.spinner);
        editText=(EditText)findViewById(R.id.ed);
        recyclerView=(RecyclerView)findViewById(R.id.rv);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        arrayList = new ArrayList<>();
        adapter = new Adapter(arrayList);
        recyclerView.setAdapter(adapter);

        Button btn=(Button)findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data=editText.getText().toString();

                Item item=new Item(data);
                String a=spinner.getSelectedItem().toString();
                        if (a.equals("월요일")){
                            item.setImage(R.mipmap.monday);
                        }
                        if (a.equals("화요일")){
                            item.setImage(R.mipmap.tuesday);
                        }
                        if (a.equals("수요일")){
                            item.setImage(R.mipmap.wednesday);
                        }
                        if (a.equals("목요일")){
                            item.setImage(R.mipmap.ic_launcher);
                        }
                        if (a.equals("금요일")){
                            item.setImage(R.mipmap.friday);
                        }
                        if (a.equals("토요일")){
                            item.setImage(R.mipmap.saturday);
                        }
                        if (a.equals("일요일")){
                            item.setImage(R.mipmap.sunday);
                        }

                arrayList.add(item);
                adapter.notifyDataSetChanged();
            }
        });

    }
}