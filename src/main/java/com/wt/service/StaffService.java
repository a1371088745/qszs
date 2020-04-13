package com.wt.service;

import com.wt.entity.Staff;

import java.util.List;

public interface StaffService {
    //查询所有教员
    public List<Staff> findTeacher(String classType);;
}
