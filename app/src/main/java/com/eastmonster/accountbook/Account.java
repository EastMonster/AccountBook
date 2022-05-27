package com.eastmonster.accountbook;

import androidx.room.*;

import java.io.Serializable;

@Entity
public class Account implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "type")
    private int type;

    @ColumnInfo(name = "amount")
    private double amount;

    @ColumnInfo(name = "time")
    private long time;

    public Account(int type, double amount, long time) {
        this.type = type;
        this.amount = amount;
        this.time = time;
    }

    @Ignore
    public Account(int id, int type, double amount, long time) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

}
