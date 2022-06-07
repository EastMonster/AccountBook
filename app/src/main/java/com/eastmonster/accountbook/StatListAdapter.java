package com.eastmonster.accountbook;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.eastmonster.accountbook.database.StatItem;


public class StatListAdapter extends ListAdapter<StatItem, StatViewHolder> {

    public StatListAdapter(@NonNull DiffUtil.ItemCallback<StatItem> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public StatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return StatViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(StatViewHolder holder, int position) {
        StatItem current = getItem(position);
        holder.bind(current.getType(), current.getSumAmount());
    }

    static class AccountDiff extends DiffUtil.ItemCallback<StatItem> { // 似乎是判断重复的
        @Override
        public boolean areItemsTheSame(@NonNull StatItem oldItem, @NonNull StatItem newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull StatItem oldItem, @NonNull StatItem newItem) {
            return oldItem.getType() == newItem.getType();
        }
    }
}
