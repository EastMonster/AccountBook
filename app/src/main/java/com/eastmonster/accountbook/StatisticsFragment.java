package com.eastmonster.accountbook;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.eastmonster.accountbook.databinding.FragmentStatisticsBinding;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Vector;

public class StatisticsFragment extends Fragment {

    FragmentStatisticsBinding binding;

    private static StatViewModel mStatViewModel;

    private LiveData<List<StatItem>> mStatList;
    private LiveData<Double> monthlySum;

    private StatListAdapter adapter;

    static Calendar calendar = Calendar.getInstance();
    private static int year = calendar.get(Calendar.YEAR);
    private static int month = calendar.get(Calendar.MONTH) + 1; // 手动加一个月
    long currentMonthMills, nextMonthMills;
    private Date date = new Date();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentStatisticsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView statView = binding.statRecycleView;
        Button monthUp = binding.monthUpButton;
        Button monthDown = binding.monthDownButton;
        TextView label = binding.dateDisplayText;
        TextView totalAmountView = binding.totalAmountView;

        adapter = new StatListAdapter(new StatListAdapter.AccountDiff());
        statView.setAdapter(adapter);
        statView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mStatViewModel = new ViewModelProvider(this).get(StatViewModel.class);

        refreshList();

        monthUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                year += (month / 12);
                month = month % 12 + 1;
                refreshList();
            }
        });

        monthDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                year -= (month == 1) ? 1 : 0;
                month = (month == 1) ? 12 : (month - 1);
                refreshList();
            }
        });
        return root;
    }

    public void refreshList() {
        calendar.set(year, month - 1, 1, 0, 0, 0);
        currentMonthMills = calendar.getTimeInMillis();
        calendar.set(year, month, 1, 0, 0, 0);
        nextMonthMills = calendar.getTimeInMillis();

        mStatList = mStatViewModel.getSumByType(currentMonthMills, nextMonthMills); // 获得分类列表
        mStatList.observe(getViewLifecycleOwner(), accounts -> {
            adapter.submitList(accounts);
        });

        monthlySum = mStatViewModel.getMonthlySum(currentMonthMills, nextMonthMills); // 获得月总支出
        monthlySum.observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
                if (aDouble == null) {
                    binding.totalAmountView.setText("共计 ￥0.00");
                } else {
                    binding.totalAmountView.setText(String.format("共计 ￥%.2f", aDouble));
                }
            }
        });

        binding.dateDisplayText.setText(String.format("%d 年 %d 月", year, month));
    }
}