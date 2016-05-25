package com.github.ik024;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.github.ik024.calendar_lib.ICalendarClickListeners;
import com.github.ik024.calendar_lib.MonthCalendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MonthViewDemo extends AppCompatActivity implements ICalendarClickListeners{

    MonthCalendar monthCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        monthCalendar = (MonthCalendar) findViewById(R.id.calendar_month_view);
        monthCalendar.registerClickListener(this);

        List<Date> eventList = new ArrayList<>();
        eventList.add(getDate(2016, 4, 9));
        eventList.add(getDate(2016, 4, 11));
        eventList.add(getDate(2016, 4, 13));
        eventList.add(getDate(2016, 4, 15));
        monthCalendar.setEventList(eventList);
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
    public void dateClicked(Date dateClicked) {
        Toast.makeText(this, "date: "+dateClicked.toString(), Toast.LENGTH_LONG).show();
    }
}
