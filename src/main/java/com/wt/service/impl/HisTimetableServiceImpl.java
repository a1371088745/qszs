package com.wt.service.impl;

import com.wt.entity.Cl;
import com.wt.entity.TimeTable;
import com.wt.mapper.HisTimetableMapper;
import com.wt.service.HisTimetableService;
import com.wt.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.sql.Time;
import java.util.*;

@Service
public class HisTimetableServiceImpl implements HisTimetableService {
    @Autowired
    private HisTimetableMapper hisTimetableMapper;
    @Override
    public int insertTimetables(List<TimeTable> timeTables) {
       return hisTimetableMapper.insertTimetables(timeTables);
    }

    @Override
    public List<TimeTable> findAllTimetables(Date date1, Date date2,String className) {
        System.out.println(className);
        return hisTimetableMapper.findAllTimetables(date1,date2,className);
    }

    public int editTimetable(TimeTable timeTable) {
       return hisTimetableMapper.editTimetable(timeTable);
    }

    @Override
    public List<TimeTable> readTimetableByStaffInOneMonth(String staffName) {
        Date firstMonthDay = DateUtil.getFirstMonthDay();
        Date lastMonthDay = DateUtil.getLastMonthDay();
        List<TimeTable> timeTables = hisTimetableMapper.readTimetableByStaffInOneMonthAm(staffName, firstMonthDay, lastMonthDay);
        List<TimeTable> timeTables1 = hisTimetableMapper.readTimetableByStaffInOneMonthPm(staffName, firstMonthDay, lastMonthDay);
        List<TimeTable> timeTableList = new ArrayList<>();
        HashMap<Date, TimeTable> timeTablesMap = new HashMap<Date, TimeTable>();
        for (TimeTable timeTable:timeTables) {
            for (TimeTable timeTable1:timeTables1) {
                if(timeTable1.getDate().getTime()==timeTable.getDate().getTime()){
                    timeTable.setPmInfo(timeTable1.getPmInfo());
                    timeTable.setPmTname(timeTable1.getPmTname());
                    timeTable.setPmclassName(timeTable1.getPmclassName());
                    break;
                }
            }
            timeTablesMap.put(timeTable.getDate(),timeTable);
        }
        Set<Date> dateSet = timeTablesMap.keySet();
        for(TimeTable timeTable:timeTables1){
            if(!dateSet.contains(timeTable.getDate())){
                timeTablesMap.put(timeTable.getDate(),timeTable);
            }
        }
        dateSet=timeTablesMap.keySet();
        for (Date date:dateSet) {
            timeTableList.add(timeTablesMap.get(date));
        }
        timeTableList.sort(Comparator.comparing(TimeTable::getDate));
        return timeTableList;
    }
}
