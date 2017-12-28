package com.naziksoft.calendar.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.naziksoft.calendar.R;
import com.naziksoft.calendar.adapter.CalendarDayAdapter;
import com.naziksoft.calendar.entity.CalendarEntity;
import com.naziksoft.calendar.utils.Constants;
import com.naziksoft.calendar.utils.OptionButtons;
import com.naziksoft.calendar.utils.StatisticControl;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by nazar on 26.12.17.
 */

public class DayCalendarFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<CalendarEntity> listData = new ArrayList<>();
    private List<OptionButtons> options = new ArrayList<>();
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_day_calendar_day, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        // todo test
        initTestData();

        initRecyclerView();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initStatistic();
    }

    @Override
    public void onStop() {
        if (statisticControl != null) {
            statisticControl.hideStatisticLayout();
        }
        super.onStop();
    }

    private void initStatistic() {
        if (statisticControl != null) {
            String time = getStatisticTime();
            statisticControl.setEventTime(time);
            statisticControl.showStatisticLayout();
        }
    }

    private String getStatisticTime() {
        long timeInMillis = 0;
        for (CalendarEntity entity : listData) {
            Date startDate = entity.getStartDate();
            Date endDate = entity.getEndDate();
            long different = endDate.getTime() - startDate.getTime();
            timeInMillis = +different;
        }
        NumberFormat f = new DecimalFormat("00");
        long hours = TimeUnit.MILLISECONDS.toHours(timeInMillis);
        long min = TimeUnit.MILLISECONDS.toMinutes(timeInMillis);
        return f.format(hours) + ":" + f.format(min);
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        CalendarDayAdapter adapter = new CalendarDayAdapter(getContext(), listData, options, new CalendarDayAdapter.OnClickOption() {
            @Override
            public void onClick(OptionButtons optionType, CalendarEntity item) {
                Toast.makeText(getActivity(), "Do " + optionType.name() + " with " + item.toString(), Toast.LENGTH_LONG).show();
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void initTestData() {
        CalendarEntity entity1 = new CalendarEntity(new Date(10000000), new Date(10200000), "Call", "");
        CalendarEntity entity2 = new CalendarEntity(new Date(10080000), new Date(10300000), "WORK", "Very hard");
        CalendarEntity entity3 = new CalendarEntity(new Date(10100000), new Date(11000000), "REST", "Very hard");
        listData.add(entity1);
        listData.add(entity2);
        listData.add(entity3);
        options.add(OptionButtons.DELETE);
        options.add(OptionButtons.SPLIT);
        options.add(OptionButtons.EDIT);
    }
}
