package com.eastmonster.accountbook;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class AccountRepository {
    private AccountDao mAccountDao;
    private LiveData<List<Account>> mAllAccounts;

    AccountRepository(Application application) {
        AccountRoomDatabase db = AccountRoomDatabase.getDatabase(application);
        mAccountDao = db.accountDAO();
        mAllAccounts = mAccountDao.getAll();
        System.out.println();
    }

    LiveData<List<Account>> getAllAccounts() {
        return mAllAccounts;
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
