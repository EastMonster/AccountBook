package com.eastmonster.accountbook;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class StatListAdapter extends RecyclerView.Adapter<StatViewHolder> {

    private List<StatItem> stats;

    public StatListAdapter(List<StatItem> stats) {
        this.stats = stats;
    }

    @NonNull
    @Override
    public StatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return StatViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(StatViewHolder holder, int position) {
        StatItem current = stats.get(position);
        holder.bind(current.getType(), current.getSumAmount());
    }

    @Override
    public int getItemCount() {
        return stats.size();
    }
}
