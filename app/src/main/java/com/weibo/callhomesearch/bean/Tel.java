package com.weibo.callhomesearch.bean;

/**
 * Created by 巴巴 on 2016/8/15.
 */

public class Tel {
    private String number;

    public Tel(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Tel{" +
                "number='" + number + '\'' +
                '}';
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
