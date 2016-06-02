package com.github.ik024;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CalendarDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_demo);
    }

    public void onMonthDemoClick(View view){
        startActivity(new Intent(this, MonthViewDemo.class));
    }

    public void onYearDemoClick(View view){
        startActivity(new Intent(this, YearViewDemo.class));
    }
}
