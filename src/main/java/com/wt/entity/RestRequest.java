package com.wt.entity;

import java.util.Date;

public class RestRequest {
    private int restId;
    private int staffId;
    private Date nowdate;
    private Date restdate;
    private int requestStatus;
    private int day;
    private String reason;
    private GivenLesson givenLesson;

    public int getRestId() {
        return restId;
    }

    public void setRestId(int restId) {
        this.restId = restId;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public Date getNowdate() {
        return nowdate;
    }

    public void setNowdate(Date nowdate) {
        this.nowdate = nowdate;
    }

    public Date getRestdate() {
        return restdate;
    }

    public void setRestdate(Date restdate) {
        this.restdate = restdate;
    }

    public int getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(int requestStatus) {
        this.requestStatus = requestStatus;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public GivenLesson getGivenLesson() {
        return givenLesson;
    }

    public void setGivenLesson(GivenLesson givenLesson) {
        this.givenLesson = givenLesson;
    }


}
