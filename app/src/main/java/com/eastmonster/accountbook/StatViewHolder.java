package com.eastmonster.accountbook;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.eastmonster.accountbook.database.Account;

public class StatViewHolder extends RecyclerView.ViewHolder {
    ImageView typeImage;
    TextView typeText;
    TextView amountText;

    private StatViewHolder(View itemview) {
        super(itemview);
        typeImage = itemview.findViewById(R.id.stat_image);
        typeText = itemview.findViewById(R.id.stat_type);
        amountText = itemview.findViewById(R.id.stat_amount);
    }

    public void bind(int type, double amount) {
        typeImage.setImageResource(Account.getImage(type));
        typeText.setText(Account.getTypeName(type));
        amountText.setText(String.format("%.2f", amount));
    }

    public static StatViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.stat_item, parent, false);
        return new StatViewHolder(view);
    }
}
