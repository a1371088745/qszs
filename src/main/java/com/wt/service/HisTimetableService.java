package com.wt.service;

import com.wt.entity.TimeTable;
import org.apache.ibatis.annotations.Param;


import java.util.Date;
import java.util.List;

public interface HisTimetableService {
    public int insertTimetables(List<TimeTable> timeTables);

    List<TimeTable> findAllTimetables(Date date1,Date date2,String className);

    public int editTimetable(TimeTable timeTable);

    public List<TimeTable> readTimetableByStaffInOneMonth(String staffName);
}
