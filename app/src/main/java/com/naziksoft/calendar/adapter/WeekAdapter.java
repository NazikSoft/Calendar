package com.naziksoft.calendar.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.naziksoft.calendar.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by morozione on 12/26/17.
 */

public class WeekAdapter extends RecyclerView.Adapter<WeekAdapter.ViewHolder> {
    private List<String> list = new ArrayList<>();

    public WeekAdapter() {
        for (int i = 0; i < 24; i++) {
            list.add(i + 1 + ":" + "00");
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_week, parent, false);

        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTime;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTime = (TextView) itemView.findViewById(R.id.tv_time);
        }

        public void bind(String time) {
            tvTime.setText(time);
        }
    }
}
