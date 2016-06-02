package com.github.ik024.calendar_lib;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by ismail.khan2 on 3/18/2016.
 */
public class MonthGridAdapter extends BaseAdapter {

    Context mContext;
    LayoutInflater mInflater;

    List<String> mItemList = Collections.EMPTY_LIST;
    List<Date> mEventList = Collections.EMPTY_LIST;
    int mToday, mMonth, mYear, mDisplayMonth, mDisplayYear;
    int mCurrentDayTextColor, mDaysOfMonthTextColor, mDaysOfWeekTextColor, mMonthNameTextColor;

    public MonthGridAdapter(Context context, int year, int month, int today){
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mItemList = new ArrayList<>();

        Calendar calendar = new GregorianCalendar(year, month, 1);
        mItemList = getItemList(calendar);

        mToday = today;
        mMonth = mDisplayMonth = month;
        mYear = mDisplayYear = year;
    }

    /**
     * This method generates the days of the month to be displayed
     *
     * @param calendar Species for which month the days should be generated
     * @return itemsList list of days for the month to be displayed
     */
    private List<String> getItemList(Calendar calendar){
        Log.d("getItemList:", "inside");
        List<String> itemList = new ArrayList<>();
        itemList.add("Sun");
        itemList.add("Mon");
        itemList.add("Tue");
        itemList.add("Wed");
        itemList.add("Thu");
        itemList.add("Fri");
        itemList.add("Sat");

        //getting first day of the month [sun-1; mon-2; tue-3; wed-4; thu-5; fri-6; sat-7;]
        int firstDayOfMonth = calendar.get(Calendar.DAY_OF_WEEK);
        //total number of days in a month
        int numOfDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        //coz array starts with 0 and Month day starts with 1
        if(firstDayOfMonth > 0){
            firstDayOfMonth = firstDayOfMonth -1;
        }

        int day = 1;
        for(int i =0;i<numOfDays+firstDayOfMonth;i++){
            //checking for first day of the day match
            //if first day of month is Tuesday (i.e firstDayOfMonth = 2 [3-1]
            //then [0][1] values in the list will be empty
            if(i >= firstDayOfMonth ) {
                itemList.add("" + day);
                day++;
            }else{
                //adding empty space to list until first day of month ('firstDayOfMonth') is reached
                itemList.add("");
            }
        }

        Log.d("yearview","item size: "+itemList.size());

        return itemList;
    }

    @Override
    public int getCount() {
        return mItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return mItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.row_calendar_month, parent, false);
            mHolder = new MyViewHolder(convertView);
            convertView.setTag(mHolder);
        }else{
            mHolder = (MyViewHolder) convertView.getTag();
        }

        String item = mItemList.get(position);

        if(position < 7){//position 0-6 are reserved for days of the weeks (weekdays & weekends)
            mHolder.tvCalendarMonthDay.setVisibility(View.GONE);
            mHolder.tvCalendarWeekDayName.setVisibility(View.VISIBLE);
            mHolder.tvCalendarWeekDayName.setText(item);
            mHolder.tvCalendarWeekDayName.setTextColor(mDaysOfWeekTextColor);

        }else{ //positions >= 7 are reserved for days of the month (eg 1 to 31)

            Log.d("yearview","pos>7");
            mHolder.tvCalendarWeekDayName.setVisibility(View.GONE);
            mHolder.tvCalendarMonthDay.setVisibility(View.VISIBLE);
            mHolder.tvCalendarMonthDay.setText(mItemList.get(position));

            if(!item.isEmpty()) {
                if (mToday == Integer.parseInt(item) && mDisplayMonth == mMonth && mDisplayYear == mYear) {
                    mHolder.tvCalendarMonthDay.setTextColor(mCurrentDayTextColor);
                }else{
                    Date date = getDate(mDisplayYear, mDisplayMonth, Integer.parseInt(item));
                    if(mEventList.contains(date)){
                        mHolder.tvCalendarMonthDay.setBackgroundResource(R.drawable.textview_background_event);
                        mHolder.tvCalendarMonthDay.setTextColor(Color.WHITE);
                    }else{
                        mHolder.tvCalendarMonthDay.setBackgroundResource(R.drawable.textview_background_no_event);
                        mHolder.tvCalendarMonthDay.setTextColor(mDaysOfMonthTextColor);
                    }
                }
            }

        }
        return convertView;
    }

    /**
     * This method updates the view based on the year and month passed
     * @param year
     * @param month
     */
    public void updateCalendar(int year, int month){
        mDisplayMonth = month;
        mDisplayYear = year;

        Calendar calendar = new GregorianCalendar(year, month, 1);
        mItemList = getItemList(calendar);
        notifyDataSetChanged();
    }

    /**
     * This method will highlight the event dates
     *
     * @param eventList List of dates
     */
    public void setEventList(List<Date> eventList){
        mEventList = eventList;
        notifyDataSetChanged();
    }

    /**
     * @return mItemList - list fo days of a month
     */
    public List<String> getItemList(){
        return mItemList;
    }

    /**
     * This method takes in year, month & day integer values and generates a date object
     *
     * @param year
     * @param month
     * @param day
     * @return Date - a date object
     */
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

    public void setCurrentDayTextColor(int color){
        mCurrentDayTextColor = color;
    }

    public void setDaysOfMonthTextColor(int color){
        mDaysOfMonthTextColor = color;
    }

    public void setDaysOfWeekTextColor(int color){
        mDaysOfWeekTextColor = color;
    }

    public void setMonthNameTextColor(int color){
        mMonthNameTextColor = color;
    }

    MyViewHolder mHolder;
    class MyViewHolder{

        AutoResizeTextView tvCalendarMonthDay, tvCalendarWeekDayName;
        public MyViewHolder(View view){
            tvCalendarWeekDayName = (AutoResizeTextView) view.findViewById(R.id.row_cm_tv_week_day_name);
            tvCalendarMonthDay = (AutoResizeTextView) view.findViewById(R.id.row_cm_tv_day);
        }
    }
}
