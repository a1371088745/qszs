package com.wt.controller;

import com.wt.entity.JsonData;
import com.wt.entity.TimeTable;
import com.wt.entity.User;
import com.wt.service.impl.HisTimetableServiceImpl;
import com.wt.util.DateUtil;
import com.wt.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/hisTimetable")
public class HisTimetableController {
    @Autowired
    private HisTimetableServiceImpl hisTimetableService;

    @RequestMapping("/toTimetable")
    public String timetable(){
        return "timetable";
    }

    @RequestMapping("/readTimetable")
    @ResponseBody
    public JsonData readTimetable(String className){
        System.out.println(className);
        Date date=new Date();
        Date thisWeekMonday = DateUtil.getThisWeekMonday(date);
        Calendar instance = Calendar.getInstance();
        instance.setTime(thisWeekMonday);
        instance.add(Calendar.DATE,6);
        Date thisWeekSunday = instance.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format1 = simpleDateFormat.format(thisWeekMonday);
        String format2 = simpleDateFormat.format(thisWeekSunday);
        Date parse1=null;
        Date parse2=null;
        try {
           parse1 = simpleDateFormat.parse(format1);
           parse2 = simpleDateFormat.parse(format2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<TimeTable> allTimetables = hisTimetableService.findAllTimetables(parse1, parse2,className);
        return JsonData.buildSuccess(allTimetables,"查看课表成功");
    }

    @RequestMapping("/editTimetable")
    public JsonData editTimetable(TimeTable timeTable){
        int i = hisTimetableService.editTimetable(timeTable);
        if(i==0){
            return JsonData.buildError("更新失败",-1);
        }
        return JsonData.buildSuccess(i,0);
    }

    @RequestMapping("/toTeacherTimeTotalChoose")
    public String toTeacherTimeTotalChoose(){
        return "teacherTimeTotalChoose";
    }

    @RequestMapping(value = "/toTeacherTimeTotal")
    public String toTeacherTimeTotal(){
        return "teacherTimeTotal";
    }

    @RequestMapping(value = "/toOwnTeacherTimeTotal")
    public String toOwnTeacherTimeTotal(HttpSession session,Model model){
        User user = (User) session.getAttribute("user");
        model.addAttribute("staffName",user.getUserName());
        return "teacherTimeTotal";
    }

    @RequestMapping(value = "/readTimetableByStaffInOneMonth")
    @ResponseBody
    public JsonData readTimetableByStaffInOneMonth(String staffName){
        List<TimeTable> timeTableList = hisTimetableService.readTimetableByStaffInOneMonth(staffName);
        return JsonData.buildSuccess(timeTableList);
    }
}
