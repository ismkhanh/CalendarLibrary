package com.github.ik024.ikzCalendar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by ismail.khan2 on 4/22/2016.
 */
public class MonthCalendar extends LinearLayout {

    Context mContext;
    RelativeLayout mRlHeader;
    ImageView mIvLeftArrow, mIvRightArrow;
    TextView mTvMonthName;
    GridView mGvMonth;
    LinearLayout mRootLayout;

    CalendarGridAdapter mCalendarGridAdapter;
    int selectedYear, selectedMonth;

    int currentDayTextColor, daysOfMonthTextColor, daysOfWeekTextColor, monthTextColor,
            eventDayBgColor, eventDayTextColor, calendarBackgroundColor;

    Drawable mLeftArrowDrawable, mRightArrowDrawable;

    ICalendarClickListeners mListener;

    public MonthCalendar(Context context) {
        super(context);
        initLayout(context, null);
    }

    public MonthCalendar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initLayout(context, attributeSet);
    }

    public void registerClickListener(ICalendarClickListeners listener){
        mListener = listener;
    }

    public void unregisterClickListener(){
        mListener = null;
    }

    private void initLayout(Context context, AttributeSet attrs) {

        mContext = context;

        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MonthCalendar);
            currentDayTextColor = typedArray.getColor(R.styleable.MonthCalendar_currentDayTextColor,
                    ContextCompat.getColor(context, R.color.colorAccent));
            daysOfMonthTextColor = typedArray.getColor(R.styleable.MonthCalendar_daysOfMonthTextColor,
                    Color.BLACK);
            monthTextColor = typedArray.getColor(R.styleable.MonthCalendar_monthNameTextColor,
                    Color.RED);
            daysOfWeekTextColor = typedArray.getColor(R.styleable.MonthCalendar_daysOfWeekTextColor,
                    Color.BLACK);
            calendarBackgroundColor = typedArray.getColor(R.styleable.MonthCalendar_calendarBackgroundColor, Color.TRANSPARENT);
            mLeftArrowDrawable = typedArray.getDrawable(R.styleable.MonthCalendar_prevButtonBackgroundResource);
            mRightArrowDrawable = typedArray.getDrawable(R.styleable.MonthCalendar_nextButtonBackgroundResource);
        } else {
            currentDayTextColor = ContextCompat.getColor(context, R.color.colorAccent);
            daysOfMonthTextColor = Color.GRAY;
            monthTextColor = Color.RED;
            daysOfWeekTextColor = Color.BLACK;
            eventDayBgColor = ContextCompat.getColor(context, R.color.colorAccent);
            eventDayTextColor = ContextCompat.getColor(context, android.R.color.white);
        }

        if(mLeftArrowDrawable == null){
            mLeftArrowDrawable = ContextCompat.getDrawable(mContext, R.drawable.ic_left_arrow);
        }
        if(mRightArrowDrawable == null){
            mRightArrowDrawable = ContextCompat.getDrawable(mContext, R.drawable.ic_right_arrow);
        }

        LayoutInflater inflator = LayoutInflater.from(context);
        inflator.inflate(R.layout.calendar_month_view, this);

        mRlHeader = (RelativeLayout) findViewById(R.id.rl_month_view_header);
        mIvLeftArrow = (ImageView) findViewById(R.id.ib_month_view_left);
        mIvRightArrow = (ImageView) findViewById(R.id.ib_month_view_right);
        mTvMonthName = (TextView) findViewById(R.id.tv_month_view_name);
        mGvMonth = (GridView) findViewById(R.id.gv_month_view);
        mRootLayout = (LinearLayout) findViewById(R.id.rl_cm_root);

        //setting image background
        setPreviousButton(mLeftArrowDrawable);
        setNextButton(mRightArrowDrawable);

        mTvMonthName.setTextColor(monthTextColor);

        mRootLayout.setBackgroundColor(calendarBackgroundColor);

        Calendar calendar = Calendar.getInstance();
        selectedYear = calendar.get(Calendar.YEAR);
        selectedMonth = calendar.get(Calendar.MONTH);

        mCalendarGridAdapter = new CalendarGridAdapter(context, selectedYear, selectedMonth, calendar.get(Calendar.DAY_OF_MONTH));
        mGvMonth.setAdapter(mCalendarGridAdapter);

        mCalendarGridAdapter.setCurrentDayTextColor(currentDayTextColor);
        mCalendarGridAdapter.setDaysOfMonthTextColor(daysOfMonthTextColor);
        mCalendarGridAdapter.setDaysOfWeekTextColor(daysOfWeekTextColor);
        mCalendarGridAdapter.setMonthNameTextColor(monthTextColor);

        mTvMonthName.setText(getMonthName(selectedMonth)+" "+selectedYear);

        mIvLeftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedMonth == 0) {
                    selectedMonth = 11;
                    selectedYear = selectedYear - 1;
                } else {
                    selectedMonth = selectedMonth - 1;
                }
                mCalendarGridAdapter.updateCalendar(selectedYear, selectedMonth);
                mTvMonthName.setText(getMonthName(selectedMonth)+" "+selectedYear);
            }
        });

        mIvRightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedMonth == 11) {
                    selectedMonth = 0;
                    selectedYear = selectedYear + 1;
                } else {
                    selectedMonth = selectedMonth + 1;
                }
                mCalendarGridAdapter.updateCalendar(selectedYear, selectedMonth);
                mTvMonthName.setText(getMonthName(selectedMonth)+" "+selectedYear);
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
                        if (mListener != null) {
                            mListener.dateClicked(getDate(selectedYear, selectedMonth, day));
                        }
                    }
                }
            }
        });
    }

    public void setEventList(List<Date> eventList) {
        mCalendarGridAdapter.setEventList(eventList);
    }

    public void setCurrentDayTextColor(int color) {
        currentDayTextColor = color;
        mCalendarGridAdapter.setCurrentDayTextColor(currentDayTextColor);
    }

    public void setDaysOfMonthTextColor(int color) {
        daysOfMonthTextColor = color;
        mCalendarGridAdapter.setDaysOfMonthTextColor(daysOfMonthTextColor);
    }

    public void setDaysOfWeekTextColor(int color) {
        daysOfWeekTextColor = color;
        mCalendarGridAdapter.setDaysOfWeekTextColor(daysOfWeekTextColor);
    }

    public void setMonthNameTextColor(int color) {
        monthTextColor = color;
        mCalendarGridAdapter.setMonthNameTextColor(monthTextColor);
    }

    public void setCalendarBackgroundColor(){
        mRootLayout.setBackgroundColor(calendarBackgroundColor);
    }

    public void setPreviousButton(Drawable leftDrawable) {
        mLeftArrowDrawable = leftDrawable;
        mIvLeftArrow.setBackground(leftDrawable);
    }

    public void setNextButton(Drawable rightDrawable) {
        mRightArrowDrawable = rightDrawable;
        mIvRightArrow.setBackground(rightDrawable);
    }

    public void setEnabledLeftArrow(boolean enabled){
        mIvLeftArrow.setEnabled(enabled);
    }

    public void setEnabledRightArrow(boolean enabled){
        mIvRightArrow.setEnabled(enabled);
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
}
