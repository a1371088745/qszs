package com.wt.controller;

import com.wt.entity.JsonData;
import com.wt.entity.TimeTable;
import com.wt.service.impl.HisTimetableServiceImpl;
import com.wt.util.DateUtil;
import com.wt.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public JsonData readTimetable(){
        Date date=new Date();
        Date thisWeekMonday = DateUtil.getThisWeekMonday(date);
        Calendar instance = Calendar.getInstance();
        instance.setTime(thisWeekMonday);
        instance.add(Calendar.DATE,7);
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

        List<TimeTable> allTimetables = hisTimetableService.findAllTimetables(parse1, parse2);
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
}
