package com.github.ik024;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.github.ik024.calendar_lib.custom.YearView;
import com.github.ik024.calendar_lib.listeners.YearViewClickListeners;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class YearViewDemo extends AppCompatActivity implements YearViewClickListeners{

    YearView mYearView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_year_view_demo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("YearViewDemo");
        mYearView = (YearView) findViewById(R.id.year_view);
        mYearView.registerYearViewClickListener(this);
        //adding events to the calendar
        mYearView.setEventList(getEventList());
    }

    private List<Date> getEventList(){
        //generating dummy event list
        List<Date> eventList = new ArrayList<>();
        eventList.add(getDate(2017, 4, 9));
        eventList.add(getDate(2016, 4, 11));
        eventList.add(getDate(2016, 4, 13));
        eventList.add(getDate(2016, 4, 15));

        return eventList;
    }

    private Date getDate(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    @Override
    public void dateClicked(int year, int month, int day) {
        Toast.makeText(this, "year: "+year+";\nmonth: "+month+";\nday: "+day, Toast.LENGTH_LONG).show();
        //TODO: Perform your actions based on year or month or day clicked
    }
}
