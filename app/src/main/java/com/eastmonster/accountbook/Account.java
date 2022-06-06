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

    @ColumnInfo(name = "remark")
    private String remark;

    @Ignore
    public static final String[] conversion = new String[] {"无", "餐饮", "交通", "购物", "服务", "教育", "娱乐", "生活缴费", "医疗", "发红包", "转账"};

    @Ignore
    public static final int[] imageSet = new int[] {
            R.drawable.ic_type_null,
            R.drawable.ic_type_food,
            R.drawable.ic_type_transportation,
            R.drawable.ic_type_shopping,
            R.drawable.ic_type_service,
            R.drawable.ic_type_education,
            R.drawable.ic_type_fun,
            R.drawable.ic_type_lifefee,
            R.drawable.ic_type_medical,
            R.drawable.ic_type_redenvelopeout,
            R.drawable.ic_type_transfer
    };

    @Ignore
    public static String getTypeName(int type) {
        if (type > 10)
            return null;
        return conversion[type];
    }

    @Ignore
    public static int getImage(int type) {
        if (type > 10)
            return imageSet[0];
        return imageSet[type];
    }

    public Account(int type, double amount, long time, String remark) {
        this.type = type;
        this.amount = amount;
        this.time = time;
        this.remark = remark;
    }

    @Ignore
    public Account(int id, int type, double amount, long time, String remark) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.time = time;
        this.remark = remark;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
