package com.naziksoft.calendar.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.naziksoft.calendar.R;
import com.naziksoft.calendar.adapter.WeekAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class WeekActionInformationFragment extends Fragment {
    private WeekAdapter adapter;

    public WeekActionInformationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_weet_action_insformation, container, false);

        RecyclerView rvList = (RecyclerView) rootView.findViewById(R.id.rv_list);
        rvList.setHasFixedSize(true);
        rvList.setLayoutManager(new GridLayoutManager(getActivity(), 1));

        adapter = new WeekAdapter();
        rvList.setAdapter(adapter);

        return rootView;
    }


}
