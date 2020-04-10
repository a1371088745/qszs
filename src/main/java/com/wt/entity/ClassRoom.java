package com.wt.entity;

public class ClassRoom {
    private int crId;
    private String crName;
    public int crCapacity;

    public int getCrId() {
        return crId;
    }

    public void setCrId(int crId) {
        this.crId = crId;
    }

    public String getCrName() {
        return crName;
    }

    public void setCrName(String crName) {
        this.crName = crName;
    }

    public int getCrCapacity() {
        return crCapacity;
    }

    public void setCrCapacity(int crCapacity) {
        this.crCapacity = crCapacity;
    }

    @Override
    public String toString() {
        return "ClassRoom [crId=" + crId + ", crName=" + crName + ", crCapacity=" + crCapacity + "]";
    }

}
