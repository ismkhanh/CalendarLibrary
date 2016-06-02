package com.github.ik024;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.ik024.calendar_lib.YearView;

public class YearViewDemo extends AppCompatActivity {

    YearView mYearView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_year_view_demo);
        mYearView = (YearView) findViewById(R.id.year_view);
    }
}
