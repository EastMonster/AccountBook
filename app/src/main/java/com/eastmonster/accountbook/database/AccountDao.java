package com.eastmonster.accountbook.database;

import androidx.lifecycle.LiveData;
import androidx.room.*;

import java.util.List;

@Dao
public interface AccountDao {
    @Query("SELECT * FROM Account ORDER BY TIME DESC")
    LiveData<List<Account>> getAll();

    @Query("SELECT type as type, SUM(amount) AS sumAmount FROM Account " +
           "WHERE TIME >= :begin AND TIME < :end " +
           "GROUP BY type ORDER BY sumAmount DESC ")
    LiveData<List<StatItem>> getSumByType(long begin, long end);

    @Query("SELECT SUM(amount) FROM Account " +
           "WHERE TIME >= :begin AND TIME < :end")
    LiveData<Double> getMonthlySum(long begin, long end);

    @Query("DELETE FROM Account")
    void nukeTable();

    @Insert
    void insert(Account account);

    @Delete
    void delete(Account account);

    @Update
    void update(Account account);
}
