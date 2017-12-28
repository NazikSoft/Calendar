package com.naziksoft.calendar.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.naziksoft.calendar.R;
import com.naziksoft.calendar.adapter.CalendarDayAdapter;
import com.naziksoft.calendar.adapter.CalendarWeekAdapter;
import com.naziksoft.calendar.entity.CalendarEntity;
import com.naziksoft.calendar.utils.OptionButtons;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WeekCalendarFragment extends Fragment {
    private CalendarWeekAdapter adapter;
    private List<OptionButtons> options = new ArrayList<>();
    List<List<CalendarEntity>> list = new ArrayList<>();

    public WeekCalendarFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_weet_calendar, container, false);

        initTestData();
        initRecyclerView(rootView);

        return rootView;
    }

    private void initRecyclerView(View rootView) {
        RecyclerView rvList = (RecyclerView) rootView.findViewById(R.id.rv_list);

        rvList.setHasFixedSize(true);
        rvList.setLayoutManager(new GridLayoutManager(getActivity(), 8));

        adapter = new CalendarWeekAdapter(list, options, new CalendarDayAdapter.OnClickOption() {
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
