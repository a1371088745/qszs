package com.wt.service;

import com.wt.entity.Staff;
import com.wt.entity.Student;
import com.wt.util.PageUtils;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentService {
    public List<Student> findStudents(String name, String className, PageUtils pageUtils);

    public int deleteStu(int stuId);

    public Integer UpdateStu( Student newStudent,String oldGroupName, String oldClassName);

    int countStu();
}
