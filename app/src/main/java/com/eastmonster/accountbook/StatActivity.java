package com.eastmonster.accountbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Vector;

public class StatActivity extends AppCompatActivity {

    private AccountViewModel mAccountViewModel;
    private Vector<StatItem> statItemList = new Vector<StatItem>();
    private Date date = new Date();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat);

        RecyclerView statView = findViewById(R.id.statRecycleView);
        Button dateSelect = findViewById(R.id.dateSelectButton);
        TextView label = findViewById(R.id.dateDisplayText);

        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1; // 手动加一个月
        calendar.set(year, month - 1, 1, 0, 0, 0);
        long currentMonthMills = calendar.getTimeInMillis();
        calendar.set(year, month, 1, 0, 0, 0);
        long nextMonthMills = calendar.getTimeInMillis();

        label.setText(String.format("%d 年 %d 月", year, month));

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        statView.setLayoutManager(layoutManager);

        mAccountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);

        AsyncTask.execute(() -> {
            System.err.println("AsyncTask Executing");
            for (int i = 0; i < 11; i++) {
            statItemList.add(mAccountViewModel.getOneTypeSum(i,
                    currentMonthMills,
                    nextMonthMills));
            }
        });

        Collections.sort(statItemList);
        System.out.println(statItemList);
        StatListAdapter adapter = new StatListAdapter(statItemList);
        statView.setAdapter(adapter);
    }
}