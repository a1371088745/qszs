package com.wt.service.impl;

import com.wt.entity.Student;
import com.wt.mapper.ClMapper;
import com.wt.mapper.GroupMapper;
import com.wt.mapper.ScoreMapper;
import com.wt.mapper.StudentMapper;
import com.wt.service.StudentService;
import com.wt.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private ClMapper clMapper;
    @Autowired
    private GroupMapper groupMapper;
    @Autowired
    private ScoreMapper scoreMapper;
    @Override
    public List<Student> findStudents(String name, String className, PageUtils pageUtils) {
        return studentMapper.findStudents(name,className,pageUtils);
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
            clMapper.changePeopleCount(classId,1);
            clMapper.changePeopleCount(oldClassId,-1);
        }
        if(newStudent.getGroup().getGroupName()==null||newStudent.getGroup().getGroupName().equals("")){
            groupId=oldGroupId;
        }else{
            groupId = groupMapper.selectGroupId(newStudent.getGroup().getGroupName(),clMapper.selectClassName(classId));
            groupMapper.changePeopleCount(groupId,1);
            groupMapper.changePeopleCount(oldGroupId,-1);
        }
        newStudent.getCl().setClassId(classId);
        newStudent.getGroup().setGroupId(groupId);
        return studentMapper.updateStu(newStudent);
    }

    @Override
    public int countStu() {
        return studentMapper.countStu();
    }

    @Override
    @Transactional
    public Integer addStu(Student stu,Integer classId) {
        ArrayList<Integer> counts = new ArrayList<>();
        counts.add(1);
        counts.add(2);
        counts.add(3);
        counts.add(4);
        studentMapper.addStu(stu,classId);
        clMapper.changePeopleCount(classId,1);
        return scoreMapper.initializeScore(stu, counts);
    }

    @Override
    public List<Student> findStudentsByStaffTel(String staffTel) {
        return studentMapper.findStudentsByStaffTel(staffTel);
    }
}
