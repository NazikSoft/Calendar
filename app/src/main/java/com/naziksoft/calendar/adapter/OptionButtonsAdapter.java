package com.naziksoft.calendar.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;

import com.naziksoft.calendar.R;
import com.naziksoft.calendar.utils.Constants;
import com.naziksoft.calendar.utils.OptionButtons;

import java.util.List;

public class OptionButtonsAdapter extends RecyclerView.Adapter<OptionButtonsAdapter.DayViewHolder> {

    private Context context;
    private List<OptionButtons> optionButtonsList;
    private OnClickButton listener;
    private int lastPosition = -1;
    private int textSize;

    public interface OnClickButton {
        void onClick(OptionButtons buttonType);
    }

    public OptionButtonsAdapter(Context context, List<OptionButtons> buttonsList, OnClickButton listener, int textSize) {
        optionButtonsList = buttonsList;
        this.listener = listener;
        this.context = context;
        this.textSize = textSize;
    }

    public static class DayViewHolder extends RecyclerView.ViewHolder {
        private Button button;

        public DayViewHolder(Button button) {
            super(button);
            this.button = button;
        }
    }

    @Override
    public DayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Button button = new Button(context);
        button.setTextSize(textSize);
        button.setBackground(null);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.height = textSize * 10;
        button.setLayoutParams(lp);

        return new DayViewHolder(button);
    }

    @Override
    public void onBindViewHolder(final DayViewHolder holder, int position) {
        final OptionButtons optionButtons = optionButtonsList.get(position);

        switch (optionButtons) {
            case EDIT:
                holder.button.setId(Constants.ID_BUTTON_EDIT);
                holder.button.setText(R.string.opyion_button_edit);
                break;
            case DELETE:
                holder.button.setId(Constants.ID_BUTTON_DELETE);
                holder.button.setText(R.string.opyion_button_delete);
                break;
            case SPLIT:
                holder.button.setId(Constants.ID_BUTTON_SPLIT);
                holder.button.setText(R.string.opyion_button_split);
                break;
        }
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(optionButtons);
            }
        });
        setAnimation(holder.button, position);
        lastPosition = position;
    }

    @Override
    public int getItemCount() {
        return optionButtonsList.size();
    }

    private void setAnimation(View viewToAnimate, int position) {
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            animation.setInterpolator(new DecelerateInterpolator());
            animation.setStartOffset(position * 100);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }
}
