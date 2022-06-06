package com.eastmonster.accountbook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.*;
import android.widget.*;
import androidx.activity.ComponentActivity;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;

public class AccountViewHolder extends RecyclerView.ViewHolder {
    ImageView typeImage;
    TextView timeText;
    TextView typeText;
    TextView amountText;
    Button modifyButton;

    private AccountViewHolder(View itemView) {
        super(itemView);
        typeImage = itemView.findViewById(R.id.accountTypeImage);
        typeText = itemView.findViewById(R.id.typeTextView);
        timeText = itemView.findViewById(R.id.timeTextView);
        amountText = itemView.findViewById(R.id.amountTextView);
        modifyButton = itemView.findViewById(R.id.modifyButton);
    }


    public void bind(int type, double amount, long time, String remark) {
        String timelane;
        typeImage.setImageResource(Account.getImage(type));
        typeText.setText(Account.getTypeName(type));
        timelane = new String(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(time));
        amountText.setText(String.format("-%.2f", amount));
        if (!remark.equals("")) {
            timelane = timelane.concat(" | " + remark);
        }
        timeText.setText(timelane);
    }

    public static AccountViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false); // 这里设置 RecyclerView 里的元素
        return new AccountViewHolder(view);
    }
}
