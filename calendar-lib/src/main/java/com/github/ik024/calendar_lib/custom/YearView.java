package com.github.ik024.calendar_lib.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ik024.calendar_lib.listeners.MonthViewClickListeners;
import com.github.ik024.calendar_lib.R;
import com.github.ik024.calendar_lib.listeners.YearViewClickListeners;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by ismail.khan2 on 5/26/2016.
 */
public class YearView extends LinearLayout implements MonthViewClickListeners {

    Context mContext;
    MonthView jan, feb, march, april, may, june, july, aug, sept, oct, nov, dec;
    ImageView ivLeftArrow, ivRightArrow;
    TextView tvDisplayYear;
    RelativeLayout headerLayout;
    ScrollView calendarLayout;
    Drawable mLeftArrowDrawable, mRightArrowDrawable;
    int mDisplayYear, currentDayTextColor, daysOfMonthTextColor, daysOfWeekTextColor, monthTextColor,
            eventDayBgColor, eventDayTextColor, calendarBackgroundColor, displayYearTextColor,
            headerBackgroundColor;

    YearViewClickListeners mYearClickListeners;
    private List<Date> eventList;

    public YearView(Context context) {
        super(context);
        init(context, null);
    }

    public YearView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs){
        mContext = context;
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.YearView);
            currentDayTextColor = typedArray.getColor(R.styleable.YearView_currentDayTextColorYV,
                    ContextCompat.getColor(context, R.color.colorAccent));
            daysOfMonthTextColor = typedArray.getColor(R.styleable.YearView_daysOfMonthTextColorYV,
                    Color.BLACK);
            monthTextColor = typedArray.getColor(R.styleable.YearView_monthNameTextColorYV,
                    Color.RED);
            daysOfWeekTextColor = typedArray.getColor(R.styleable.YearView_daysOfWeekTextColorYV,
                    Color.BLACK);
            calendarBackgroundColor = typedArray.getColor(R.styleable.YearView_calendarBackgroundColorYV, Color.TRANSPARENT);
            displayYearTextColor = typedArray.getColor(R.styleable.YearView_displayYearTextColorYV,
                    ContextCompat.getColor(context, R.color.colorAccent));
            headerBackgroundColor = typedArray.getColor(R.styleable.YearView_headerBackgroundColorYV, Color.TRANSPARENT);
            mLeftArrowDrawable = typedArray.getDrawable(R.styleable.YearView_prevButtonBackgroundResourceYV);
            mRightArrowDrawable = typedArray.getDrawable(R.styleable.YearView_nextButtonBackgroundResourceYV);
        } else {
            currentDayTextColor = ContextCompat.getColor(context, R.color.colorAccent);
            daysOfMonthTextColor = Color.GRAY;
            monthTextColor = Color.RED;
            daysOfWeekTextColor = Color.BLACK;
            eventDayBgColor = ContextCompat.getColor(context, R.color.colorAccent);
            eventDayTextColor = ContextCompat.getColor(context, android.R.color.white);
            displayYearTextColor = ContextCompat.getColor(context, android.R.color.white);
            headerBackgroundColor = Color.TRANSPARENT;
        }

        if(mLeftArrowDrawable == null){
            mLeftArrowDrawable = ContextCompat.getDrawable(mContext, R.drawable.ic_left_arrow);
        }
        if(mRightArrowDrawable == null){
            mRightArrowDrawable = ContextCompat.getDrawable(mContext, R.drawable.ic_right_arrow);
        }

        View view = LayoutInflater.from(context).inflate(R.layout.year_view, this);

        jan = (MonthView) view.findViewById(R.id.mv_year_view_jan);
        feb = (MonthView) view.findViewById(R.id.mv_year_view_feb);
        march = (MonthView) view.findViewById(R.id.mv_year_view_march);
        april = (MonthView) view.findViewById(R.id.mv_year_view_april);
        may = (MonthView) view.findViewById(R.id.mv_year_view_may);
        june = (MonthView) view.findViewById(R.id.mv_year_view_jun);
        july = (MonthView) view.findViewById(R.id.mv_year_view_july);
        aug = (MonthView) view.findViewById(R.id.mv_year_view_aug);
        sept = (MonthView) view.findViewById(R.id.mv_year_view_sept);
        oct = (MonthView) view.findViewById(R.id.mv_year_view_oct);
        nov = (MonthView) view.findViewById(R.id.mv_year_view_nov);
        dec = (MonthView) view.findViewById(R.id.mv_year_view_dec);

        tvDisplayYear = (TextView) view.findViewById(R.id.tv_year_view_name);

        ivLeftArrow = (ImageView) view.findViewById(R.id.iv_year_view_left);
        ivRightArrow = (ImageView) view.findViewById(R.id.iv_year_view_right);

        headerLayout = (RelativeLayout) view.findViewById(R.id.rl_year_view_header);
        calendarLayout = (ScrollView) view.findViewById(R.id.sv_year_view_calendar_background);

        ivLeftArrow.setBackground(mLeftArrowDrawable);
        ivRightArrow.setBackground(mRightArrowDrawable);

        setCalendarBackgroundColor(calendarBackgroundColor);
        setDisplayYearTextColor(displayYearTextColor);
        setHeaderBackgroundColor(headerBackgroundColor);

        settingUpMonthsOfTheYear();

        Calendar cal = Calendar.getInstance();
        mDisplayYear = cal.get(Calendar.YEAR);;

        updateYearCalendar(mDisplayYear);

        tvDisplayYear.setText(""+mDisplayYear);

        setupClickListeners();


        jan.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Jan", Toast.LENGTH_SHORT).show();
            }
        });
        ivLeftArrow.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mDisplayYear = mDisplayYear - 1;
                tvDisplayYear.setText(""+mDisplayYear);
                updateYearCalendar(mDisplayYear);
            }
        });

        ivRightArrow.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mDisplayYear = mDisplayYear + 1;
                tvDisplayYear.setText(""+mDisplayYear);
                updateYearCalendar(mDisplayYear);
            }
        });

    }

    private void setupClickListeners() {
        jan.registerClickListener(this);
        feb.registerClickListener(this);
        march.registerClickListener(this);
        april.registerClickListener(this);
        may.registerClickListener(this);
        june.registerClickListener(this);
        july.registerClickListener(this);
        aug.registerClickListener(this);
        sept.registerClickListener(this);
        oct.registerClickListener(this);
        nov.registerClickListener(this);
        dec.registerClickListener(this);
    }

    public void registerYearViewClickListener(YearViewClickListeners listener){
        mYearClickListeners = listener;
    }

    public void unregisterYearViewClickListener(){
        mYearClickListeners = null;
    }

    @Override
    public void dateClicked(Date dateClicked) {
        if(mYearClickListeners != null){
            Calendar cal = Calendar.getInstance();
            cal.setTime(dateClicked);
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);
            mYearClickListeners.dateClicked(year, month, day);
        }
    }

    private void updateYearCalendar(int year){

        jan.updateCalendar(year, 0);
        feb.updateCalendar(year, 1);
        march.updateCalendar(year, 2);
        april.updateCalendar(year, 3);
        may.updateCalendar(year, 4);
        june.updateCalendar(year, 5);
        july.updateCalendar(year, 6);
        aug.updateCalendar(year, 7);
        sept.updateCalendar(year, 8);
        oct.updateCalendar(year, 9);
        nov.updateCalendar(year, 10);
        dec.updateCalendar(year, 11);

        updateEventList();
    }

    private void monthViewSetters(MonthView monthView){
        if(monthView != null){
            monthView.setCurrentDayTextColor(currentDayTextColor);
            monthView.setDaysOfMonthTextColor(daysOfMonthTextColor);
            monthView.setDaysOfWeekTextColor(daysOfWeekTextColor);
            monthView.setMonthNameTextColor(monthTextColor);

        }
    }

    public void setDisplayYearTextColor(int displayYearTextColor){
        tvDisplayYear.setTextColor(displayYearTextColor);
    }

    public void setCalendarBackgroundColor(int backgroundColor){
        calendarLayout.setBackgroundColor(calendarBackgroundColor);
    }

    public void setHeaderBackgroundColor(int headerBackgroundColor){
        headerLayout.setBackgroundColor(headerBackgroundColor);
    }

    public void setEventList(List<Date> eventList){
        this.eventList = eventList;
        updateEventList();

    }

    private void updateEventList(){
        if(eventList != null && eventList.size() > 0) {
            jan.setEventList(eventList);
            feb.setEventList(eventList);
            march.setEventList(eventList);
            april.setEventList(eventList);
            may.setEventList(eventList);
            june.setEventList(eventList);
            july.setEventList(eventList);
            aug.setEventList(eventList);
            sept.setEventList(eventList);
            oct.setEventList(eventList);
            nov.setEventList(eventList);
            dec.setEventList(eventList);
        }
    }

    private void settingUpMonthsOfTheYear(){
        jan.setPreviousButtonVisibility(View.GONE);
        jan.setNextButtonVisibility(View.GONE);
        jan.setIsMonthView(false);
        monthViewSetters(jan);

        feb.setPreviousButtonVisibility(View.GONE);
        feb.setNextButtonVisibility(View.GONE);
        feb.setIsMonthView(false);
        monthViewSetters(feb);

        march.setPreviousButtonVisibility(View.GONE);
        march.setNextButtonVisibility(View.GONE);
        march.setIsMonthView(false);
        monthViewSetters(march);

        april.setPreviousButtonVisibility(View.GONE);
        april.setNextButtonVisibility(View.GONE);
        april.setIsMonthView(false);
        monthViewSetters(april);

        may.setPreviousButtonVisibility(View.GONE);
        may.setNextButtonVisibility(View.GONE);
        may.setIsMonthView(false);
        monthViewSetters(may);

        june.setPreviousButtonVisibility(View.GONE);
        june.setNextButtonVisibility(View.GONE);
        june.setIsMonthView(false);
        monthViewSetters(june);

        july.setPreviousButtonVisibility(View.GONE);
        july.setNextButtonVisibility(View.GONE);
        july.setIsMonthView(false);
        monthViewSetters(july);

        aug.setPreviousButtonVisibility(View.GONE);
        aug.setNextButtonVisibility(View.GONE);
        aug.setIsMonthView(false);
        monthViewSetters(aug);

        sept.setPreviousButtonVisibility(View.GONE);
        sept.setNextButtonVisibility(View.GONE);
        sept.setIsMonthView(false);
        monthViewSetters(sept);

        oct.setPreviousButtonVisibility(View.GONE);
        oct.setNextButtonVisibility(View.GONE);
        oct.setIsMonthView(false);
        monthViewSetters(oct);

        nov.setPreviousButtonVisibility(View.GONE);
        nov.setNextButtonVisibility(View.GONE);
        nov.setIsMonthView(false);
        monthViewSetters(nov);

        dec.setPreviousButtonVisibility(View.GONE);
        dec.setNextButtonVisibility(View.GONE);
        dec.setIsMonthView(false);
        monthViewSetters(dec);
    }


}
