package com.lwm.scrollbannerdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class Main1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        ScrollBanner sb_demographic;
        sb_demographic = (ScrollBanner) findViewById(R.id.sb_demographic);
        List<String> demographicsList = new ArrayList<String>();
        for (int i = 0; i < 1; i++) {
            demographicsList.add("第" + i + "条内容");
        }
        if (demographicsList.size() == 1) {
            sb_demographic.stopScroll();
            sb_demographic.setText(demographicsList.get(0));
        } else {
            sb_demographic.setList(demographicsList);
            sb_demographic.startScroll();
        }
//        sb_demographic.setList(demographicsList);
//        sb_demographic.startScroll();
    }
}
