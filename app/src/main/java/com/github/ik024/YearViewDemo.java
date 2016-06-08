package com.github.ik024;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.github.ik024.calendar_lib.custom.YearView;
import com.github.ik024.calendar_lib.listeners.YearViewClickListeners;

public class YearViewDemo extends AppCompatActivity implements YearViewClickListeners{

    YearView mYearView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_year_view_demo);
        mYearView = (YearView) findViewById(R.id.year_view);
        mYearView.registerYearViewClickListener(this);
    }

    @Override
    public void dateClicked(int year, int month, int day) {
        Toast.makeText(this, "year: "+year+";\nmonth: "+month+";\nday: "+day, Toast.LENGTH_LONG).show();
        //TODO: Perform your actions based on year or month or day clicked
    }
}
