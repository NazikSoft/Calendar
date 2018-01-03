package com.naziksoft.calendar.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import com.naziksoft.calendar.R;
import com.naziksoft.calendar.utils.Constants;
import com.naziksoft.calendar.utils.StatisticControl;

public class MonthCalendarFragment extends Fragment{
    private CalendarView cvCalendar;
    private StatisticControl statisticControl;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof StatisticControl) {
            statisticControl = (StatisticControl) context;
        } else {
            Log.d(Constants.LOG, "Activity must implement StatisticControl interface");
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        initUI(view);
        initListener();
        updateCalendar();
        return view;
    }

    @Override
    public void onStop() {
        if (statisticControl != null) {
            statisticControl.hideStatisticLayout();
        }
        super.onStop();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (statisticControl != null) {
            String time = getStatisticTime();
            statisticControl.setEventTime(time);
            statisticControl.showStatisticLayout();
        }
    }

    // todo
    public String getStatisticTime() {
        return "00:00";
    }

    private void updateCalendar() {
        cvCalendar.setDate(System.currentTimeMillis());
    }

    private void initUI(View view) {
        cvCalendar = (CalendarView) view.findViewById(R.id.cv_calendar);
    }

    private void initListener() {
        cvCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {

            }
        });
    }
}
