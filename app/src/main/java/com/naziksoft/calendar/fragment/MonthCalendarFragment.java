package com.naziksoft.calendar.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import com.naziksoft.calendar.R;

public class MonthCalendarFragment extends Fragment implements View.OnClickListener {
    private CalendarView cvCalendar;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        initUI(view);
        initListener();
        updateCalendar();

        return view;
    }

    private void updateCalendar() {
        cvCalendar.setDate(System.currentTimeMillis());
    }

    @Override
    public void onClick(View view) {
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
