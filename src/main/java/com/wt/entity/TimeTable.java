package com.wt.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Time;
import java.util.Date;

public class TimeTable implements  Cloneable{
    private int timeTableId;
    private int day;
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date date;
    private String classroomName;
    private String amclassName;
    private String amInfo;
    private String amTname;
    private String pmclassName;
    private String pmTname;
    private String pmInfo;
    private String nightclassName;
    private String nightTname;
    private String nightInfo;
    private int classroomId;
    private String weekday;

    public int getTimeTableId() {
        return timeTableId;
    }

    public void setTimeTableId(int timeTableId) {
        this.timeTableId = timeTableId;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getClassroomName() {
        return classroomName;
    }

    public void setClassroomName(String classroomName) {
        this.classroomName = classroomName;
    }

    public String getAmclassName() {
        return amclassName;
    }

    public void setAmclassName(String amclassName) {
        this.amclassName = amclassName;
    }

    public String getAmInfo() {
        return amInfo;
    }

    public void setAmInfo(String amInfo) {
        this.amInfo = amInfo;
    }

    public String getAmTname() {
        return amTname;
    }

    public void setAmTname(String amTname) {
        this.amTname = amTname;
    }

    public String getPmclassName() {
        return pmclassName;
    }

    public void setPmclassName(String pmclassName) {
        this.pmclassName = pmclassName;
    }

    public String getPmTname() {
        return pmTname;
    }

    public void setPmTname(String pmTname) {
        this.pmTname = pmTname;
    }

    public String getPmInfo() {
        return pmInfo;
    }

    public void setPmInfo(String pmInfo) {
        this.pmInfo = pmInfo;
    }

    public String getNightclassName() {
        return nightclassName;
    }

    public void setNightclassName(String nightclassName) {
        this.nightclassName = nightclassName;
    }

    public String getNightTname() {
        return nightTname;
    }

    public void setNightTname(String nightTname) {
        this.nightTname = nightTname;
    }

    public String getNightInfo() {
        return nightInfo;
    }

    public void setNightInfo(String nightInfo) {
        this.nightInfo = nightInfo;
    }

    public int getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(int classroomId) {
        this.classroomId = classroomId;
    }

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public TimeTable(int day, Date date, String classroomName, String amclassName, String amInfo, String amTname,
                     String pmclassName, String pmTname, String pmInfo, String nightclassName, String nightTname,
                     String nightInfo, int classroomId) {
        super();
        this.day = day;
        this.date = date;
        this.classroomName = classroomName;
        this.amclassName = amclassName;
        this.amInfo = amInfo;
        this.amTname = amTname;
        this.pmclassName = pmclassName;
        this.pmTname = pmTname;
        this.pmInfo = pmInfo;
        this.nightclassName = nightclassName;
        this.nightTname = nightTname;
        this.nightInfo = nightInfo;
        this.classroomId=classroomId;
    }

    public TimeTable() {

    }

    @Override
    public String toString() {
        return "TimeTable [timeTableId=" + timeTableId + ", day=" + day + ", date=" + date + ", classroomName="
                + classroomName + ", amclassName=" + amclassName + ", amInfo=" + amInfo + ", amTname=" + amTname
                + ", pmclassName=" + pmclassName + ", pmTname=" + pmTname + ", pmInfo=" + pmInfo + ", nightclassName="
                + nightclassName + ", nightTname=" + nightTname + ", nightInfo=" + nightInfo + "]";
    }

    @Override
    public  Object clone(){
        TimeTable timeTable=new TimeTable();
        try {
            timeTable=(TimeTable)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return  timeTable;
    }


}
