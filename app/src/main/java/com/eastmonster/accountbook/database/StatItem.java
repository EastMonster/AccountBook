package com.eastmonster.accountbook.database;

public class StatItem implements Comparable<StatItem> {
    private int type;
    private double sumAmount;

    public StatItem(int type, double sumAmount) {
        this.type = type;
        this.sumAmount = sumAmount;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getSumAmount() {
        return sumAmount;
    }

    public void setSumAmount(double sumAmount) {
        this.sumAmount = sumAmount;
    }

    @Override
    public int compareTo(StatItem s) { // 降序显示
        return this.sumAmount >= s.getSumAmount() ? 1 : 0;
    }

    @Override
    public String toString() {
        return String.format("%d, %.2f\n", type, sumAmount);
    }
}
