package com.example.myapplication;

public class data {
    int year;
    float future_cost,current_invest_future_cost, anual_saving;

    public data()
    { }
    public data(int year, float future_cost, float current_invest_future_cost, float anual_saving) {
        this.year = year;
        this.future_cost = future_cost;
        this.current_invest_future_cost = current_invest_future_cost;
        this.anual_saving = anual_saving;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public float getFuture_cost() {
        return future_cost;
    }

    public void setFuture_cost(float future_cost) {
        this.future_cost = future_cost;
    }

    public float getCurrent_invest_future_cost() {
        return current_invest_future_cost;
    }

    public void setCurrent_invest_future_cost(float current_invest_future_cost) {
        this.current_invest_future_cost = current_invest_future_cost;
    }

    public float getAnual_saving() {
        return anual_saving;
    }

    public void setAnual_saving(float anual_saving) {
        this.anual_saving = anual_saving;
    }
}
