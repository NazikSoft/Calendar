package com.naziksoft.calendar.adapter;

import android.graphics.Color;
import android.support.transition.TransitionManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.QuickContactBadge;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.naziksoft.calendar.R;
import com.naziksoft.calendar.entity.CalendarEntity;
import com.naziksoft.calendar.utils.OptionButtons;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CalendarWeekAdapter extends RecyclerView.Adapter<CalendarWeekAdapter.ViewHolder> {
    private List<List<CalendarEntity>> entityList = new ArrayList<>();
    private List<String> listTime = new ArrayList<>();
    private List<OptionButtons> optionList;
    private CalendarDayAdapter.OnClickOption listener;
    private int mExpandedPosition = -1;

    public interface OnClickOption {
        void onClick(OptionButtons optionType, CalendarEntity item);
    }

    public CalendarWeekAdapter(List<List<CalendarEntity>> entityList, List<OptionButtons> optionList,
                               CalendarDayAdapter.OnClickOption listener) {
        this.entityList = entityList;
        this.optionList = optionList;
        this.listener = listener;

        createListTime();
    }

    private void createListTime() {
        for (int i = 0; i < 25; i++) {
            listTime.add(i + ":00");
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_calendar_week, parent, false);

        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return entityList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private RecyclerView rvOptions;

        public ViewHolder(View itemView) {
            super(itemView);
            rvOptions = (RecyclerView) itemView.findViewById(R.id.rv_options);
        }

        public void bind(int position) {
            if (position == 0) {
                for (int i = 0; i < 24; i++) {
                    makeListHour(i, listTime.get(i));
                }
            } else {
                itemView.setBackgroundColor(Color.parseColor("#f2f2f2"));

                for (CalendarEntity entity :
                        entityList.get(position)) {
                    makeListEntity(entity, position);
                }
            }
        }

        private void makeListHour(int i, String text) {
            RelativeLayout.LayoutParams layoutParams =
                    new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.topMargin = i * 100;

            TextView tvHour = new TextView(itemView.getContext());
            tvHour.setLayoutParams(layoutParams);
            tvHour.setText(text);
            ((RelativeLayout) itemView).addView(tvHour);
        }

        private void makeListEntity(CalendarEntity entity, final int position) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(entity.getStartDate().getTime());

            RelativeLayout.LayoutParams lp =
                    new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.topMargin = calendar.get(Calendar.HOUR) * 100 + calendar.get(Calendar.MINUTE);

            int height = (int) ((entity.getEndDate().getTime() - entity.getStartDate().getTime()) / 1000 / 60);
            if (height > 20) {
                lp.height = height;
            }

            final TextView tvText = new TextView(itemView.getContext());
            tvText.setText(entity.getType() + "\n" + entity.getDescription());
            tvText.setLayoutParams(lp);
            tvText.setTextSize(8);
            tvText.setBackgroundColor(Color.parseColor("#71adc5"));

            tvText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    notifyItemChanged(position);
                }
            });

            ((RelativeLayout) itemView).addView(tvText);

//            final boolean isExpanded = position == mExpandedPosition;
//            if (isExpanded) {
//                lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
//                        ViewGroup.LayoutParams.MATCH_PARENT);
//
//                rvOptions.setVisibility(View.VISIBLE);
//                showOptions(rvOptions, entity);
//                lp.topMargin = height + calendar.get(Calendar.HOUR) * 100 + calendar.get(Calendar.MINUTE);
//                rvOptions.setLayoutParams(lp);
//            } else {
//                rvOptions.setVisibility(View.GONE);
//                rvOptions.setAdapter(null);
//                rvOptions.setLayoutManager(null);
//            }
//            tvText.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (isExpanded) {
//                        mExpandedPosition = -1;
//                    } else {
//                        mExpandedPosition = position;
//                    }
//                    TransitionManager.beginDelayedTransition(rvOptions);
//                    notifyItemChanged(position);
//                }
//            });
        }
    }

    private void showOptions(RecyclerView recyclerView, final CalendarEntity entity) {
        // init RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        OptionButtonsAdapter adapter = new OptionButtonsAdapter(recyclerView.getContext(),
                optionList, new OptionButtonsAdapter.OnClickButton() {
            @Override
            public void onClick(OptionButtons buttonType) {
                listener.onClick(buttonType, entity);
            }
        }, 7);
        recyclerView.setAdapter(adapter);
    }
}
