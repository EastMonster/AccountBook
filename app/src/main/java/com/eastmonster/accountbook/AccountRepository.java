package com.eastmonster.accountbook;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class AccountRepository {
    private AccountDao mAccountDao;
    private LiveData<List<Account>> mAllAccounts;
    private List<Account> temp;

    AccountRepository(Application application) {
        AccountRoomDatabase db = AccountRoomDatabase.getDatabase(application);
        mAccountDao = db.accountDAO();
        mAllAccounts = mAccountDao.getAll();
    }

    LiveData<List<Account>> getAllAccounts() {
        return mAllAccounts;
    }

    StatItem getOneTypeAccounts(int target, long begin, long end) {
        double sum = 0.0;
        if (temp != null)
            temp.clear();
        temp = mAccountDao.getTypeAll(target, begin, end);
        for (Account account : temp) {
            System.out.println(account.getAmount());
            System.err.println("EXECUTING");
            sum += account.getAmount();
        }
        return new StatItem(target, sum);
    }

    // 方法不能在 UI 相关的线程里面调用
    void insert(Account account) {
        AccountRoomDatabase.databaseWriteExecutor.execute(() -> {
            mAccountDao.insert(account);
        });
    }

    void update(Account account) {
        AccountRoomDatabase.databaseWriteExecutor.execute(() -> {
            mAccountDao.update(account);
        });
    }

    void delete(Account account) {
        AccountRoomDatabase.databaseWriteExecutor.execute(() -> {
            mAccountDao.delete(account);
        });
    }
}
