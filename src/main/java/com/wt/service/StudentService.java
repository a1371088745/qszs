package com.wt.service;

import com.wt.entity.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentService {
    public List<Student> findStudents(String name,String className);

    public int deleteStu(int stuId);

    public Integer UpdateStu( Student newStudent,String oldGroupName, String oldClassName);
}
