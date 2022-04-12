package com.test.carrental;

import java.io.Serializable;

public class Car implements Serializable {
    private String name;
    private double dailyRate;
    private boolean status;

    public Car(String name, double dailyRate, boolean status) {
        this.name = name;
        this.dailyRate = dailyRate;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(double dailyRate) {
        this.dailyRate = dailyRate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
