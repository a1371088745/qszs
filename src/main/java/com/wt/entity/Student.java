package com.wt.entity;

public class Student {
    private int stuId;
    private String stuName;
    private String stuSex;
    private int stuAge;
    private Cl cl;
    private Group group;
    private String stuTel;
    private String stuStatus;

    public int getStuId() {
        return stuId;
    }

    public void setStuId(int stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuSex() {
        return stuSex;
    }

    public void setStuSex(String stuSex) {
        this.stuSex = stuSex;
    }

    public int getStuAge() {
        return stuAge;
    }

    public void setStuAge(int stuAge) {
        this.stuAge = stuAge;
    }

    public Cl getCl() {
        return cl;
    }

    public void setCl(Cl cl) {
        this.cl = cl;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getStuTel() {
        return stuTel;
    }

    public void setStuTel(String stuTel) {
        this.stuTel = stuTel;
    }

    public String getStuStatus() {
        return this.stuStatus;
    }
    public void setStuStatus(String stuStatus) {
        this.stuStatus = stuStatus;
    }

    public String getClassName(){
        return cl.getClassName();
    }

    public String getGroupName(){
        return group.getGroupName();
    }

}
