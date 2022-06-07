package com.eastmonster.accountbook.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class AccountRepository {
    private AccountDao mAccountDao;
    private LiveData<List<Account>> mAllAccounts;
    private LiveData<List<StatItem>> mStatList;
    private LiveData<Double> monthlySum;

    public AccountRepository(Application application) {
        AccountRoomDatabase db = AccountRoomDatabase.getDatabase(application);
        mAccountDao = db.accountDAO();
        mAllAccounts = mAccountDao.getAll();
    }

    public LiveData<List<Account>> getAllAccounts() {
        return mAllAccounts;
    }

    public LiveData<List<StatItem>> getSumByType(long begin, long end) {
        mStatList = mAccountDao.getSumByType(begin, end);
        return mStatList;
    }

    public LiveData<Double> getMonthlySum(long begin, long end) {
        monthlySum = mAccountDao.getMonthlySum(begin, end);
        return monthlySum;
    }

    // 方法不能在 UI 相关的线程里面调用
    public void insert(Account account) {
        AccountRoomDatabase.databaseWriteExecutor.execute(() -> {
            mAccountDao.insert(account);
        });
    }

    public void update(Account account) {
        AccountRoomDatabase.databaseWriteExecutor.execute(() -> {
            mAccountDao.update(account);
        });
    }

    public void delete(Account account) {
        AccountRoomDatabase.databaseWriteExecutor.execute(() -> {
            mAccountDao.delete(account);
        });
    }

    public void nukeTable() {
        AccountRoomDatabase.databaseWriteExecutor.execute(() -> {
            mAccountDao.nukeTable();
        });
    }

//    void deleteAll(AccountViewModel model) {
//        AccountRoomDatabase.databaseWriteExecutor.execute(() -> {
//            mAccountDao.deleteAll(model);
//        });
//    }
}
