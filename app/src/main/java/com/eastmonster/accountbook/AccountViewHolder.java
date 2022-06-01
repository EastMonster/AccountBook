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

    public static final String[] convertion = new String[] {"无", "餐饮", "交通", "购物", "服务", "教育", "娱乐", "生活缴费", "医疗", "发红包", "转账"};


    public static String getTypeName(int type) {
        if (type > 10)
            return null;
        return convertion[type];
    }

    private AccountViewHolder(View itemView) {
        super(itemView);
        typeImage = itemView.findViewById(R.id.accountTypeImage);
        typeText = itemView.findViewById(R.id.typeTextView);
        timeText = itemView.findViewById(R.id.timeTextView);
        amountText = itemView.findViewById(R.id.amountTextView);
        modifyButton = itemView.findViewById(R.id.modifyButton);
    }


    public void bind(int type, double amount, long time) {
        typeImage.setImageResource(R.drawable.click_before);
        typeText.setText(getTypeName(type));
        timeText.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(time));
        amountText.setText(String.format("-%.2f", amount));
    }

    public static AccountViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false); // 这里设置 RecyclerView 里的元素
        return new AccountViewHolder(view);
    }
}
