package com.eastmonster.accountbook.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Account.class}, version = 1, exportSchema = false)
public abstract class AccountRoomDatabase extends RoomDatabase {
    public abstract AccountDao accountDAO();

    private static volatile AccountRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static AccountRoomDatabase getDatabase(final Context context) { // Classic Singleton
        if (INSTANCE == null) {
            synchronized (AccountRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AccountRoomDatabase.class, "Account").addCallback(sRoomDatabaseCallback).build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(() -> {
                AccountDao dao = INSTANCE.accountDAO(); // 建表
//                dao.insert(new Account(1, 5.50, 1672544119000L, "")); //2022-12-31
//                dao.insert(new Account(2, 5.50, 1672457719000L, "")); //2023-01-01
            });
        }
    };
}


