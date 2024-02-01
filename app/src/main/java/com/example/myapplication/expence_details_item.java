package com.example.myapplication;

import android.widget.ImageView;

public class expence_details_item {
    private String name,value;
    private int iv;

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "expence_details_item{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", iv=" + iv +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getIv() {
        return iv;
    }

    public void setIv(int iv) {
        this.iv = iv;
    }

    public expence_details_item(String name, String value, int iv) {
        this.name = name;
        this.value = value;
        this.iv = iv;
    }
}
