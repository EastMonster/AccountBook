package com.eastmonster.accountbook;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

public class AccountListAdapter extends ListAdapter<Account, AccountViewHolder> {

    public AccountListAdapter(@NonNull DiffUtil.ItemCallback<Account> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public AccountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return AccountViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(AccountViewHolder holder, int position) {
        Account current = getItem(position);
        holder.bind(current.getType(), current.getAmount(), current.getTime(), current.getRemark());

        holder.modifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.getContext().startActivity(new Intent(view.getContext(), NewAccountActivity.class).putExtra("account", current));
            }
        });
    }

    static class AccountDiff extends DiffUtil.ItemCallback<Account> { // 似乎是判断重复的

        @Override
        public boolean areItemsTheSame(@NonNull Account oldItem, @NonNull Account newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Account oldItem, @NonNull Account newItem) {
            return oldItem.getId() == newItem.getId();
        }
    }
}
