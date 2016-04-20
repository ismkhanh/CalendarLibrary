package com.ik.calendarlibrary;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
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
public class CalendarGridAdapter extends BaseAdapter {

    Context mContext;
    LayoutInflater mInflater;

    List<String> mItemList = Collections.EMPTY_LIST;
    List<Date> mEventList = Collections.EMPTY_LIST;
    int mToday, mMonth, mYear, mDisplayMonth, mDisplayYear;

    public CalendarGridAdapter(Context context, int year, int month, int today){
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mItemList = new ArrayList<>();

        Calendar calendar = new GregorianCalendar(year, month, 1);
        mItemList = getItemList(calendar);

        mToday = today;
        mMonth = mDisplayMonth = month;
        mYear = mDisplayYear = year;
    }

    public void updateCalendar(int year, int month){
        mDisplayMonth = month;
        mDisplayYear = year;

        Calendar calendar = new GregorianCalendar(year, month, 1);
        mItemList = getItemList(calendar);
        notifyDataSetChanged();
    }

    public void setEventList(List<Date> eventList){
        mEventList = eventList;
        notifyDataSetChanged();
    }
    public List<String> getItemList(){
        return mItemList;
    }
    private List<String> getItemList(Calendar calendar){
        List<String> itemList = new ArrayList<>();
        itemList.add("Sun");
        itemList.add("Mon");
        itemList.add("Tue");
        itemList.add("Wed");
        itemList.add("Thu");
        itemList.add("Fri");
        itemList.add("Sat");

        int firstDayOfMonth = calendar.get(Calendar.DAY_OF_WEEK);
        int numOfDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        if(firstDayOfMonth > 1){
            firstDayOfMonth = firstDayOfMonth -1;
        }
        Log.d("calendar", "firstDayOfMonth: "+firstDayOfMonth+"; numOfDays: "+numOfDays);
        int day = 1;
        for(int i =0;i<numOfDays+firstDayOfMonth;i++){
            if(i >= firstDayOfMonth ) {
                itemList.add(""+day);
                day++;
            }else{
                itemList.add("");
            }
        }

        return itemList;
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

        if(position < 7){
            mHolder.tvCalendarMonthDay.setVisibility(View.GONE);
            mHolder.tvCalendarMonthDayName.setVisibility(View.VISIBLE);
            mHolder.tvCalendarMonthDayName.setText(item);
        }else{

            mHolder.tvCalendarMonthDayName.setVisibility(View.GONE);
            mHolder.tvCalendarMonthDay.setVisibility(View.VISIBLE);
            mHolder.tvCalendarMonthDay.setText(mItemList.get(position));

            if(!item.isEmpty()) {
                if (mToday == Integer.parseInt(item) && mDisplayMonth == mMonth && mDisplayYear == mYear) {
                    mHolder.tvCalendarMonthDay.setTextColor(ContextCompat.getColorStateList(mContext, R.color.colorAccent));
                }else{
                    Date date = getDate(mDisplayYear, mDisplayMonth, Integer.parseInt(item));
                    if(mEventList.contains(date)){
                        mHolder.tvCalendarMonthDay.setBackgroundResource(R.drawable.textview_background_event);
                        mHolder.tvCalendarMonthDay.setTextColor(Color.WHITE);
                    }else{
                        mHolder.tvCalendarMonthDay.setBackgroundResource(R.drawable.textview_background_no_event);
                        mHolder.tvCalendarMonthDay.setTextColor(Color.BLACK);
                    }
                }
            }

        }
        return convertView;
    }

    MyViewHolder mHolder;
    class MyViewHolder{

        TextView tvCalendarMonthDay, tvCalendarMonthDayName;
        public MyViewHolder(View view){
            tvCalendarMonthDayName = (TextView) view.findViewById(R.id.row_cm_tv_day_name);
            tvCalendarMonthDay = (TextView) view.findViewById(R.id.row_cm_tv_day);
        }
    }
}
