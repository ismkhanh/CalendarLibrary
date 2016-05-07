package com.github.ik024.ik_calendar_lib;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

/**
 * Created by ismail.khan2 on 4/22/2016.
 */
public class MonthCalendar extends LinearLayout{

    RelativeLayout mRlHeader;
    ImageButton mIbLeftArrow, mIbRightArrow;
    TextView mTvMonthName;
    GridView mGvMonth;

    CalendarGridAdapter mCalendarGridAdapter;
    int selectedYear, selectedMonth;

    public MonthCalendar(Context context){
        super(context);
        initLayout(context);
    }

    public MonthCalendar(Context context, AttributeSet attributeSet) {
        super(context);
        initLayout(context);
    }

    private void initLayout(Context context) {
        LayoutInflater inflator = LayoutInflater.from(context);
        inflator.inflate(R.layout.calendar_month_view, this);

        mRlHeader = (RelativeLayout) findViewById(R.id.rl_month_view_header);
        mIbLeftArrow = (ImageButton) findViewById(R.id.ib_month_view_left);
        mIbRightArrow = (ImageButton) findViewById(R.id.ib_month_view_right);
        mTvMonthName = (TextView) findViewById(R.id.tv_month_view_name);
        mGvMonth = (GridView) findViewById(R.id.gv_month_view);

        Calendar calendar = Calendar.getInstance();
        selectedYear = calendar.get(Calendar.YEAR);
        selectedMonth = calendar.get(Calendar.MONTH);

        mCalendarGridAdapter = new CalendarGridAdapter(context, selectedYear, selectedMonth, calendar.get(Calendar.DAY_OF_MONTH));
        mGvMonth.setAdapter(mCalendarGridAdapter);

        mIbLeftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedMonth == 0) {
                    selectedMonth = 11;
                    selectedYear = selectedYear - 1;
                } else {
                    selectedMonth = selectedMonth - 1;
                }
                mCalendarGridAdapter.updateCalendar(selectedYear, selectedMonth);
                mTvMonthName.setText(getMonthName(selectedMonth));
            }
        });

        mIbRightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedMonth == 11) {
                    selectedMonth = 0;
                    selectedYear = selectedYear + 1;
                } else {
                    selectedMonth = selectedMonth + 1;
                }
                mCalendarGridAdapter.updateCalendar(selectedYear, selectedMonth);
                mTvMonthName.setText(getMonthName(selectedMonth));
            }
        });

        mGvMonth.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                List<String> itemList = mCalendarGridAdapter.getItemList();
                String item = itemList.get(position);
                if (position >= 7) {
                    if (!item.isEmpty()) {
                        int day = Integer.parseInt(item);
                        /*if (mListener != null) {
                            mListener.onGridViewItemSlected(selectedYear, selectedMonth, day);
                        }*/
                    }
                }
            }
        });
    }

    private String getMonthName(int month) {
        switch (month) {
            case 0:
                return "January";
            case 1:
                return "February";
            case 2:
                return "March";
            case 3:
                return "April";
            case 4:
                return "May";
            case 5:
                return "June";
            case 6:
                return "July";
            case 7:
                return "August";
            case 8:
                return "September";
            case 9:
                return "October";
            case 10:
                return "November";
            case 11:
                return "December";
            default:
                return "";
        }
    }

}
