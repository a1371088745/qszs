package com.wt.service.impl;

import com.wt.entity.Student;
import com.wt.mapper.ClMapper;
import com.wt.mapper.GroupMapper;
import com.wt.mapper.StudentMapper;
import com.wt.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private ClMapper clMapper;
    @Autowired
    private GroupMapper groupMapper;
    @Override
    public List<Student> findStudents(String name,String className) {
        return studentMapper.findStudents(name,className);
    }

    @Override
    public int deleteStu(int stuId) {
        return studentMapper.deleteStu(stuId);
    }

    @Override
    @Transactional
    public Integer UpdateStu(Student newStudent, String oldGroupName, String oldClassName) {
        Integer classId=0;
        Integer groupId=0;
        Integer oldClassId = clMapper.selectClassId(oldClassName);
        System.out.println(newStudent.getCl());
        Integer oldGroupId = groupMapper.selectGroupId(oldGroupName,oldClassName);
        if(newStudent.getCl().getClassName()==null||newStudent.getCl().getClassName().equals("")){
            classId=oldClassId;
        }else{
            classId = clMapper.selectClassId(newStudent.getCl().getClassName());
        }
        if(newStudent.getGroup().getGroupName()==null||newStudent.getGroup().getGroupName().equals("")){
            groupId=oldGroupId;
        }else{
            groupId = groupMapper.selectGroupId(newStudent.getGroup().getGroupName(),newStudent.getCl().getClassName());
        }
        newStudent.getCl().setClassId(classId);
        newStudent.getGroup().setGroupId(groupId);
        clMapper.changePeopleCount(classId,1);
        clMapper.changePeopleCount(oldClassId,-1);
        groupMapper.changePeopleCount(groupId,1);
        groupMapper.changePeopleCount(oldGroupId,-1);
        return studentMapper.updateStu(newStudent);
    }
}