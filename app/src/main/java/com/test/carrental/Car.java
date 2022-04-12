package com.test.carrental;

import java.io.Serializable;

public class Car implements Serializable {
    private String name;
    private double dailyRate;
    private boolean status;
    private int image;

    public Car(String name, double dailyRate, boolean status, int image) {
        this.name = name;
        this.dailyRate = dailyRate;
        this.status = status;
        this.image = image;
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

    public boolean getStatus() {
        return status;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
