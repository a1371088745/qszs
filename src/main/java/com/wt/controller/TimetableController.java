package com.wt.controller;

import com.wt.entity.JsonData;
import com.wt.entity.TimeTable;
import com.wt.service.impl.TimeTableServiceImpl;
import com.wt.util.DateUtil;
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
@RequestMapping("/timetable")
public class TimetableController {
    @Autowired
    private TimeTableServiceImpl timeTableService;
    //生成课表
    @RequestMapping("/scheduling")
    @ResponseBody
    public JsonData scheduling(){
        List<TimeTable> timeTables = timeTableService.scheduling();
        return  JsonData.buildSuccess(timeTables,0);
    }

    @RequestMapping("/toScheduling")
    public String toScheduling(){
        return "schedulingTimetable";
    }

    @RequestMapping("/readTimetable")
    @ResponseBody
    public JsonData readTimetable(){
        Date date = new Date();
        Date thisWeekMonday = DateUtil.getThisWeekMonday(date);
        Date nextWeekMonday= DateUtil.getNextWeekDate(thisWeekMonday, 1);
        Calendar instance = Calendar.getInstance();
        instance.setTime(nextWeekMonday);
        instance.add(Calendar.DATE,7);
        Date nextWeekSunday = instance.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format1 = simpleDateFormat.format(nextWeekMonday);
        String format2 = simpleDateFormat.format(nextWeekSunday);
        Date parse1=null;
        Date parse2=null;
        try {
            parse1 = simpleDateFormat.parse(format1);
            parse2 = simpleDateFormat.parse(format2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<TimeTable> timeTables = timeTableService.selectTimeTables(parse1,parse2);
        return JsonData.buildSuccess(timeTables);
    }

    @RequestMapping("/editTimetable")
    public JsonData editTimetable(TimeTable timeTable){
        int i = timeTableService.editTimetable(timeTable);
        if(i==0){
            return JsonData.buildError("更新失败",-1);
        }
        return JsonData.buildSuccess(i,0);
    }
}
