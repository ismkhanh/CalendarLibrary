package com.ik.calendarlibrary;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CalendarMonthFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CalendarMonthFragment.eventDateList( getEventDateList());
    }

    private List<Date> getEventDateList() {
        List<Date> eventList = new ArrayList<>();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            eventList.add(dateFormatter.parse("21/03/2016"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return eventList;
    }

    @Override
    public void onGridViewItemSlected(int year, int month, int day) {
        Toast.makeText(this, "Year: "+year+"; Month: "+month+"; Day: "+day, Toast.LENGTH_LONG).show();
    }
}
