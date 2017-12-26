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

public class MonthActionsInformationFragment extends Fragment implements View.OnClickListener {
    private CalendarView cvCalendar;
    private TextView tvCallsCount;
    private TextView tvRestCount;
    private TextView tvWorkCount;
    private TextView tvWalkCount;

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

    private void updateUI() {
        int calls = 0;
        int work = 0;
        int rests = 0;
        int walks = 0;

        tvCallsCount.setText(calls + "");
        tvWorkCount.setText(work + "");
        tvWalkCount.setText(walks + "");
        tvRestCount.setText(rests + "");
    }

    @Override
    public void onClick(View view) {
    }


    private void initUI(View view) {
        cvCalendar = (CalendarView) view.findViewById(R.id.cv_calendar);
        tvCallsCount = (TextView) view.findViewById(R.id.tv_calls_count);
        tvRestCount = (TextView) view.findViewById(R.id.tv_rest_count);
        tvWorkCount = (TextView) view.findViewById(R.id.tv_work_count);
        tvWalkCount = (TextView) view.findViewById(R.id.tv_walk_count);
    }

    private void initListener() {
        cvCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {

            }
        });
    }

}
