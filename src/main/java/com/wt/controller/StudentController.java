package com.wt.controller;

import com.alibaba.fastjson.JSONObject;
import com.wt.entity.Cl;
import com.wt.entity.Group;
import com.wt.entity.JsonData;
import com.wt.entity.Student;
import com.wt.service.impl.StudentServiceImpl;
import com.wt.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/studentInfo")
public class StudentController {
    @Autowired
    private StudentServiceImpl studentService;
    @RequestMapping("/toStudentInfo")
    public String toStudentInfo(){
        return "studentInfo";
    }

    @RequestMapping("/findStudents")
    @ResponseBody
    public String findAllStudents(String name,String className,PageUtils pageUtils){
        int count=studentService.countStu();
        pageUtils.setCount(count);
        pageUtils.setTotalPage(count);
        List<Student> students = studentService.findStudents(name, className, pageUtils);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",0);
        jsonObject.put("msg","分页数据");
        jsonObject.put("count",count);
        jsonObject.put("data",students);
        return jsonObject.toString();
    }

    @RequestMapping("/delStu")
    @ResponseBody
    public JsonData delStu(int stuId){
        int i = studentService.deleteStu(stuId);
        if(i==0){
            return JsonData.buildError("此学生处于在读状态，删除失败",-1);
        }
        return JsonData.buildSuccess(i,1);
    }

    @RequestMapping("/changeStuInfo")
    @ResponseBody
    public JsonData changeStuInfo(Student student, String newClassName,String newGroupName,String oldClassName,String oldGroupName,String newStuStatus,String oldStuStatus){
        Student newStudent = new Student();
        newStudent.setStuId(student.getStuId());
        if(newStuStatus==null||newStuStatus.equals("")){
            newStuStatus=oldStuStatus;
        }
        newStudent.setStuStatus(newStuStatus);
        newStudent.setStuAge(student.getStuAge());
        newStudent.setStuName(student.getStuName());
        newStudent.setStuSex(student.getStuSex());
        newStudent.setStuTel(student.getStuTel());
        Cl cl = new Cl();
        cl.setClassName(newClassName);
        System.out.println(newClassName);
        Group group = new Group();
        group.setGroupName(newGroupName);
        newStudent.setCl(cl);
        newStudent.setGroup(group);
        Integer inf = studentService.UpdateStu(newStudent,oldGroupName,oldClassName);
        if(inf==0){
            return JsonData.buildError("修改失败！");
        }
        return  JsonData.buildSuccess("修改成功");
    }

    @RequestMapping("/addStu")
    @ResponseBody
    public JsonData addStu(Student stu,Integer classId){
        Integer integer = studentService.addStu(stu, classId);
        return  JsonData.buildSuccess(integer,"添加学生成功");
    }
}
