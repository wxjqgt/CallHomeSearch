package com.weibo.callhomesearch.bean;

/**
 * Created by 巴巴 on 2016/8/15.
 */

public class GetZoneResult {

    /**
     * mts : 1304984
     * province : 广东
     * catName : 中国联通
     * telString : 13049847236
     * areaVid : 30517
     * ispVid : 137815084
     * carrier : 广东联通
     */

    private String mts;
    private String province;
    private String catName;
    private String telString;
    private String areaVid;
    private String ispVid;
    private String carrier;

    @Override
    public String toString() {
        return "__GetZoneResult_{" +
                "mts='" + mts + '\'' +
                ", province='" + province + '\'' +
                ", catName='" + catName + '\'' +
                ", telString='" + telString + '\'' +
                ", areaVid='" + areaVid + '\'' +
                ", ispVid='" + ispVid + '\'' +
                ", carrier='" + carrier + '\'' +
                '}';
    }

    public GetZoneResult() {
    }

    public GetZoneResult(String mts, String province, String catName, String telString, String areaVid, String ispVid, String carrier) {
        this.mts = mts;
        this.province = province;
        this.catName = catName;
        this.telString = telString;
        this.areaVid = areaVid;
        this.ispVid = ispVid;
        this.carrier = carrier;
    }

    public String getMts() {
        return mts;
    }

    public void setMts(String mts) {
        this.mts = mts;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getTelString() {
        return telString;
    }

    public void setTelString(String telString) {
        this.telString = telString;
    }

    public String getAreaVid() {
        return areaVid;
    }

    public void setAreaVid(String areaVid) {
        this.areaVid = areaVid;
    }

    public String getIspVid() {
        return ispVid;
    }

    public void setIspVid(String ispVid) {
        this.ispVid = ispVid;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }
}
