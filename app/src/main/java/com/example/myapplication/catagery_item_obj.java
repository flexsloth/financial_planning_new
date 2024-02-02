package com.example.myapplication;

public class catagery_item_obj {
    String catname,catid;
    private int vale,val2;

    public catagery_item_obj() {

    }

    public String getCatname() {
        return catname;
    }

    public void setCatname(String catname) {
        this.catname = catname;
    }

    public int getVale() {
        return vale;
    }

    public void setVale(int vale) {
        this.vale = vale;
    }

    public int getVal2() {
        return val2;
    }

    public void setVal2(int val2) {
        this.val2 = val2;
    }

    public String getCatid() {
        return catid;
    }

    public void setCatid(String catid) {
        this.catid = catid;
    }

    public catagery_item_obj(String catname, int vale, int val2, String catid) {
        this.catname = catname;
        this.vale = vale;
        this.val2 = val2;
        this.catid = catid;
    }

    @Override
    public String toString() {
        return "catagery_item_obj{" +
                "catname='" + catname + '\'' +
                ", vale=" + vale +
                ", val2=" + val2 +
                '}';
    }
}
