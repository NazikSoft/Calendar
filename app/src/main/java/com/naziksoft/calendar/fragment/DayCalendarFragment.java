package com.naziksoft.calendar.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.naziksoft.calendar.R;
import com.naziksoft.calendar.adapter.CalendarDayAdapter;
import com.naziksoft.calendar.entity.CalendarEntity;
import com.naziksoft.calendar.utils.OptionButtons;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by nazar on 26.12.17.
 */

public class DayCalendarFragment extends Fragment {

    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_day_calendar_day, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // todo test
        List<CalendarEntity> listData = new ArrayList<>();
        CalendarEntity entity1 = new CalendarEntity(new Date(10000000), "Call", "");
        CalendarEntity entity2 = new CalendarEntity(new Date(10020000), "WORK", "Very hard");
        CalendarEntity entity3 = new CalendarEntity(new Date(10060000), "REST", "Very hard");
        listData.add(entity1);
        listData.add(entity2);
        listData.add(entity3);

        final List<OptionButtons> options = new ArrayList<>();
        options.add(OptionButtons.DELETE);
        options.add(OptionButtons.SPLIT);
        options.add(OptionButtons.EDIT);

        CalendarDayAdapter adapter = new CalendarDayAdapter(getContext(), listData, options, new CalendarDayAdapter.OnClickOption() {
            @Override
            public void onClick(OptionButtons optionType, CalendarEntity item) {
                Toast.makeText(getActivity(), "Do " + optionType.name() + " with " + item.toString(), Toast.LENGTH_LONG).show();
            }
        });
        recyclerView.setAdapter(adapter);
        return view;
    }
}
