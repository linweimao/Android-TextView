package com.lwm.scrollbannerdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnonedata = findViewById(R.id.btn_onedata);
        Button btnmanydata = findViewById(R.id.btn_manydata);
        btnonedata.setOnClickListener(this);
        btnmanydata.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_onedata:
                Intent intent = new Intent(MainActivity.this,Main1Activity.class);
                startActivity(intent);
                break;
            case R.id.btn_manydata:
                Intent intentmany = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intentmany);
                break;
        }
    }
}
