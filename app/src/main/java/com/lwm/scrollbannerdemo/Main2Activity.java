package com.lwm.scrollbannerdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ScrollBanner sb_demographic;
        sb_demographic = (ScrollBanner) findViewById(R.id.sb_demographic);
        List<String> demographicsList = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            demographicsList.add("第" + i + "条内容");
        }
        sb_demographic.setList(demographicsList);
        sb_demographic.startScroll();
    }
}
