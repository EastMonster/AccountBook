package com.eastmonster.accountbook;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.Date;
import java.util.List;
import java.util.Vector;

public class StatViewModel extends AndroidViewModel {

    private AccountRepository mRepository;

    private LiveData<List<StatItem>> mStatList;

    private LiveData<Double> monthlySum;

    public StatViewModel(Application application) {
        super(application);
        mRepository = new AccountRepository(application);
    }

    LiveData<List<StatItem>> getSumByType(long begin, long end) {
        mStatList = mRepository.getSumByType(begin, end);
        return mStatList;
    }

    public LiveData<Double> getMonthlySum(long begin, long end) {
        monthlySum = mRepository.getMonthlySum(begin, end);
        return monthlySum;
    }

}
