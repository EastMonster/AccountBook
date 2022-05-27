package com.eastmonster.accountbook;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class AccountViewModel extends AndroidViewModel {
    private AccountRepository mRepository;

    private final LiveData<List<Account>> mAllWords;

    public AccountViewModel(Application application) {
        super(application);
        mRepository = new AccountRepository(application);
        mAllWords = mRepository.getAllAccounts();
    }

    LiveData<List<Account>> getAllAccounts() {
        return mAllWords;
    }

    public void insert(Account account) {
        mRepository.insert(account);
    }

    public void update(Account account) {
        mRepository.update(account);
    }

    public void delete(Account account) {
        mRepository.delete(account); }

}
