package com.wt.controller;

import com.wt.entity.JsonData;
import com.wt.entity.Staff;
import com.wt.service.impl.StaffServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/staff")
@Controller
public class StaffController {
    @Autowired
    private StaffServiceImpl staffService;

    @RequestMapping("/findTeachers")
    @ResponseBody
    public JsonData findTeachers(String classType){
        List<Staff> teachers = staffService.findTeacher(classType);
        if(teachers==null){
            return  JsonData.buildError("无教员",-1);
        }
        return JsonData.buildSuccess(teachers,0);
    }
}
