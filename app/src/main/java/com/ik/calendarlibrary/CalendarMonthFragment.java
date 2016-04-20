package com.ik.calendarlibrary;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class CalendarMonthFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public CalendarMonthFragment() {
    }


    public static CalendarMonthFragment newInstance() {
        CalendarMonthFragment fragment = new CalendarMonthFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    GridView mGvMonthCalendar;
    Button mBtPrevious, mBtNext;
    TextView mTvCurrentMonth;
    static CalendarGridAdapter mCalendarGridAdapter;

    int selectedYear, selectedMonth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar_month, container, false);
        Calendar calendar = Calendar.getInstance();
        selectedYear = calendar.get(Calendar.YEAR);
        selectedMonth = calendar.get(Calendar.MONTH);

        mGvMonthCalendar = (GridView) view.findViewById(R.id.gv_mn_frag_calendar);
        mTvCurrentMonth = (TextView) view.findViewById(R.id.tv_mn_frag_current_month);
        mBtPrevious = (Button) view.findViewById(R.id.bt_mn_frag_previous);
        mBtNext = (Button) view.findViewById(R.id.bt_mn_frag_next);

        mCalendarGridAdapter = new CalendarGridAdapter(getActivity(), selectedYear, selectedMonth, calendar.get(Calendar.DAY_OF_MONTH));
        mGvMonthCalendar.setAdapter(mCalendarGridAdapter);

        mTvCurrentMonth.setText(getMonthName(selectedMonth));

        mBtPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedMonth == 0) {
                    selectedMonth = 11;
                    selectedYear = selectedYear - 1;
                } else {
                    selectedMonth = selectedMonth - 1;
                }
                mCalendarGridAdapter.updateCalendar(selectedYear, selectedMonth);
                mTvCurrentMonth.setText(getMonthName(selectedMonth));
            }
        });

        mBtNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedMonth == 11) {
                    selectedMonth = 0;
                    selectedYear = selectedYear + 1;
                } else {
                    selectedMonth = selectedMonth + 1;
                }
                mCalendarGridAdapter.updateCalendar(selectedYear, selectedMonth);
                mTvCurrentMonth.setText(getMonthName(selectedMonth));
            }
        });

        mGvMonthCalendar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                List<String> itemList = mCalendarGridAdapter.getItemList();
                String item = itemList.get(position);
                if (position >= 7) {
                    if (!item.isEmpty()) {
                        int day = Integer.parseInt(item);
                        if (mListener != null) {
                            mListener.onGridViewItemSlected(selectedYear, selectedMonth, day);
                        }
                    }
                }
            }
        });

        return view;
    }

    public static void eventDateList(List<Date> eventList){
        if(mCalendarGridAdapter != null) {
            mCalendarGridAdapter.setEventList(eventList);
        }else{
            Log.d("date", "adapter null");
        }
    }

    public interface OnFragmentInteractionListener {
        void onGridViewItemSlected(int year, int month, int day);
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
