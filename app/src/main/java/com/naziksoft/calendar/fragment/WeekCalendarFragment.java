package com.naziksoft.calendar.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.naziksoft.calendar.R;
import com.naziksoft.calendar.adapter.CalendarWeekAdapter;
import com.naziksoft.calendar.entity.CalendarEntity;
import com.naziksoft.calendar.utils.Constants;
import com.naziksoft.calendar.utils.OptionButtons;
import com.naziksoft.calendar.utils.StatisticControl;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WeekCalendarFragment extends Fragment {
    private CalendarWeekAdapter adapter;
    private List<OptionButtons> options = new ArrayList<>();
    List<List<CalendarEntity>> list = new ArrayList<>();
    private StatisticControl statisticControl;
    private String statisticTime;

    public WeekCalendarFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof StatisticControl) {
            statisticControl = (StatisticControl) context;
        } else {
            Log.d(Constants.LOG, "Activity must implement StatisticControl interface");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_weet_calendar, container, false);
        RecyclerView rvList = (RecyclerView) rootView.findViewById(R.id.rv_list);

        initTestData();

        initRecyclerView(rvList);

        return rootView;
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

    public String getStatisticTime() {
        long timeInMillis = 0;
        for (List<CalendarEntity> listCalendarEntity :
                list) {
            for (CalendarEntity entity :
                    listCalendarEntity) {
                Date startDate = entity.getStartDate();
                Date endDate = entity.getEndDate();
                long different = endDate.getTime() - startDate.getTime();
                timeInMillis += different;
            }
        }
        NumberFormat f = new DecimalFormat("00");
        long hour = timeInMillis / 1000 / 60 / 60;
        long min = timeInMillis / 1000 / 60 % 60;

        return f.format(hour) + ":" + f.format(min);
    }

    private void initRecyclerView(RecyclerView rvList) {
        rvList.setHasFixedSize(true);
        rvList.setLayoutManager(new GridLayoutManager(getActivity(), 8));

        adapter = new CalendarWeekAdapter(list, options, new CalendarWeekAdapter.OnClickOption() {
            @Override
            public void onClick(OptionButtons optionType, CalendarEntity item) {
                Toast.makeText(getContext(), item.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        rvList.setAdapter(adapter);
    }

    private void initTestData() {
        list.add(new ArrayList<CalendarEntity>());
        for (int i = 0; i < 7; i++) {
            List<CalendarEntity> list = new ArrayList<>();
            list.add(new CalendarEntity(new Date(10020000 - 2000000 * i), new Date(10020000 + 2000000 * i + 2), "WORK", "Very hard"));
            list.add(new CalendarEntity(new Date(10020000 - 2000000 * (3 + i)), new Date(10020000 + 2000000 * (12 - i) + 2), "WORK", "Very easy"));

            this.list.add(list);
        }

        options.add(OptionButtons.DELETE);
        options.add(OptionButtons.SPLIT);
        options.add(OptionButtons.EDIT);
    }
}
