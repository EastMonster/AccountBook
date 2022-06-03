package com.eastmonster.accountbook;

import androidx.lifecycle.LiveData;
import androidx.room.*;

import java.util.List;

@Dao
public interface AccountDao {
    @Query("SELECT * FROM Account ORDER BY TIME DESC")
    LiveData<List<Account>> getAll();

    @Query("SELECT * FROM ACCOUNT WHERE TYPE = :target AND TIME >= :begin AND TIME < :end")
    List<Account> getTypeAll(int target, long begin, long end);

    @Insert
    void insert(Account account);

    @Delete
    void delete(Account account);

    @Update
    void update(Account account);
}
