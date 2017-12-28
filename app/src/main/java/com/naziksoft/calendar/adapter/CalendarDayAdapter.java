package com.naziksoft.calendar.adapter;

import android.content.Context;
import android.support.transition.TransitionManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.naziksoft.calendar.R;
import com.naziksoft.calendar.entity.CalendarEntity;
import com.naziksoft.calendar.utils.OptionButtons;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

/**
 * Created by nazar on 27.12.17.
 */

public class CalendarDayAdapter extends RecyclerView.Adapter<CalendarDayAdapter.DayViewHolder> {

    private Context context;
    private List<CalendarEntity> entityList;
    private List<OptionButtons> optionList;
    private OnClickOption onClickOptionListener;
    private int mExpandedPosition = -1;
    private RecyclerView expandedRecyclerView;

    public interface OnClickOption {
        void onClick(OptionButtons optionType, CalendarEntity item);
    }


    public CalendarDayAdapter(Context context, List<CalendarEntity> entityList,
                              List<OptionButtons> optionList, OnClickOption listener) {
        onClickOptionListener = listener;
        this.entityList = entityList;
        this.context = context;
        this.optionList = optionList;
        Collections.sort(entityList);
    }

    public static class DayViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private TextView time;
        private TextView type;
        private TextView description;
        private RecyclerView recyclerViewOptions;

        public DayViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.rootCardView);
            time = (TextView) itemView.findViewById(R.id.time);
            type = (TextView) itemView.findViewById(R.id.tvActionType);
            description = (TextView) itemView.findViewById(R.id.tvActionDescription);
            recyclerViewOptions = (RecyclerView) itemView.findViewById(R.id.recyclerViewOptions);
        }
    }

    @Override
    public DayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_calendar_day, parent, false);
        return new DayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DayViewHolder holder, final int position) {
        // time
        CalendarEntity entity = entityList.get(position);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(entity.getStartDate());
        NumberFormat f = new DecimalFormat("00");
        int hour = calendar.get(Calendar.HOUR);
        int min = calendar.get(Calendar.MINUTE);
        String time = f.format(hour)  + ":" + f.format(min);
        holder.time.setText(time);
        // description
        if (entity.getDescription() != null || !entity.getDescription().equals("")) {
            holder.description.setText(entity.getDescription());
        } else {
            holder.description.setText(context.getString(R.string.action_default_description));
        }
        // type
        holder.type.setText(entity.getType());
        // options
        final boolean isExpanded = position == mExpandedPosition;
        if (isExpanded) {
            holder.recyclerViewOptions.setVisibility(View.VISIBLE);
            showOptions(holder.recyclerViewOptions, entity);
        } else {
            holder.recyclerViewOptions.setVisibility(View.GONE);
            holder.recyclerViewOptions.setAdapter(null);
        }
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isExpanded) {
                    mExpandedPosition = -1;
                } else {
                    mExpandedPosition = position;
                }
                TransitionManager.beginDelayedTransition(holder.recyclerViewOptions);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return entityList.size();
    }

    private void showOptions(RecyclerView recyclerView, final CalendarEntity entity) {
        // init RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        OptionButtonsAdapter adapter = new OptionButtonsAdapter(context, optionList, new OptionButtonsAdapter.OnClickButton() {
            @Override
            public void onClick(OptionButtons buttonType) {
                onClickOptionListener.onClick(buttonType, entity);
            }
        }, 12);
        recyclerView.setAdapter(adapter);
    }
}
