package com.wt.entity;

public class Cl {
    private int classId;
    private String className;
    private int classPeople;
    private int csId;
    private String classStatus;
    private Integer count1; //缺课数
    private Integer count2;//缺自习数
    private Integer count3;//轮休数
    private boolean flag;
    private GivenLesson givenLesson;

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getClassPeople() {
        return classPeople;
    }

    public void setClassPeople(int classPeople) {
        this.classPeople = classPeople;
    }

    public int getCsId() {
        return csId;
    }

    public void setCsId(int csId) {
        this.csId = csId;
    }

    public String getClassStatus() {
        return classStatus;
    }

    public void setClassStatus(String classStatus) {
        this.classStatus = classStatus;
    }

    public Integer getCount1() {
        return count1;
    }

    public void setCount1(Integer count1) {
        this.count1 = count1;
    }

    public Integer getCount2() {
        return count2;
    }

    public void setCount2(Integer count2) {
        this.count2 = count2;
    }

    public Integer getCount3() {
        return count3;
    }

    public void setCount3(Integer count3) {
        this.count3 = count3;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public GivenLesson getGivenLesson() {
        return givenLesson;
    }

    public void setGivenLesson(GivenLesson givenLesson) {
        this.givenLesson = givenLesson;
    }

    @Override
    public String toString() {
        return "Cl [classId=" + classId + ", className=" + className + ", classPeople=" + classPeople + ", csId=" + csId
                + ", classStatus=" + classStatus + ", count1=" + count1 + ", count2=" + count2 + ", count3=" + count3
                + "]";
    }


}
