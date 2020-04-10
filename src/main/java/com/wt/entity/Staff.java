package com.wt.entity;

import java.math.BigDecimal;

public class Staff {
    private int staffId;
    private String staffName;
    private int dtId;
    private BigDecimal staffTel;
    private String position;

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public int getDtId() {
        return dtId;
    }

    public void setDtId(int dtId) {
        this.dtId = dtId;
    }

    public BigDecimal getStaffTel() {
        return staffTel;
    }

    public void setStaffTel(BigDecimal staffTel) {
        this.staffTel = staffTel;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }


}
