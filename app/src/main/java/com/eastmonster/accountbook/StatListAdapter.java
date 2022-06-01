package com.eastmonster.accountbook;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


class StatItem implements Comparable<StatItem>{
    private int type;
    private double sumAmount;

    public StatItem(int type, double sumAmount) {
        this.type = type;
        this.sumAmount = sumAmount;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getSumAmount() {
        return sumAmount;
    }

    public void setSumAmount(double sumAmount) {
        this.sumAmount = sumAmount;
    }

    @Override
    public int compareTo(StatItem s) { // 降序显示
        return this.sumAmount >= s.getSumAmount() ? 1 : 0;
    }
}

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
