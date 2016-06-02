package com.github.ik024.calendar_lib;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

/**
 * Created by ismail.khan2 on 5/26/2016.
 */
public class YearView extends LinearLayout implements CalendarClickListeners{

    Context mContext;
    MonthView jan, feb, march, april, may, june, july, aug, sept, oct, nov, dec;
    ImageView ivLeftArrow, ivRightArrow;
    TextView tvDisplayYear;
    Drawable mLeftArrowDrawable, mRightArrowDrawable;
    int mDisplayYear;

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
        if(attrs != null){
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.YearView);
            mLeftArrowDrawable = typedArray.getDrawable(R.styleable.YearView_nextButtonBackgroundResource1);
            mRightArrowDrawable = typedArray.getDrawable(R.styleable.YearView_nextButtonBackgroundResource1);
        }else{

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

        ivLeftArrow.setBackground(mLeftArrowDrawable);
        ivRightArrow.setBackground(mRightArrowDrawable);

        jan.setPreviousButtonVisibility(View.GONE);
        jan.setNextButtonVisibility(View.GONE);

        feb.setPreviousButtonVisibility(View.GONE);
        feb.setNextButtonVisibility(View.GONE);

        march.setPreviousButtonVisibility(View.GONE);
        march.setNextButtonVisibility(View.GONE);

        april.setPreviousButtonVisibility(View.GONE);
        april.setNextButtonVisibility(View.GONE);

        may.setPreviousButtonVisibility(View.GONE);
        may.setNextButtonVisibility(View.GONE);

        june.setPreviousButtonVisibility(View.GONE);
        june.setNextButtonVisibility(View.GONE);

        july.setPreviousButtonVisibility(View.GONE);
        july.setNextButtonVisibility(View.GONE);

        aug.setPreviousButtonVisibility(View.GONE);
        aug.setNextButtonVisibility(View.GONE);

        sept.setPreviousButtonVisibility(View.GONE);
        sept.setNextButtonVisibility(View.GONE);

        oct.setPreviousButtonVisibility(View.GONE);
        oct.setNextButtonVisibility(View.GONE);

        nov.setPreviousButtonVisibility(View.GONE);
        nov.setNextButtonVisibility(View.GONE);

        dec.setPreviousButtonVisibility(View.GONE);
        dec.setNextButtonVisibility(View.GONE);

        //----------------------------------------//
        mDisplayYear = 2016;

        updateYearCalendar(mDisplayYear);

        tvDisplayYear.setText(""+mDisplayYear);

        jan.registerClickListener(this);

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
    }

    @Override
    public void dateClicked(Date dateClicked) {
        Toast.makeText(mContext, "Jan", Toast.LENGTH_SHORT).show();
    }
}
