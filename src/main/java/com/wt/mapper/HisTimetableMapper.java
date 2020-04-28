package com.wt.mapper;

import com.wt.entity.TimeTable;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface HisTimetableMapper {
    //记录历史课表
    int insertTimetables(@Param("timeTables") List<TimeTable> timeTables);

    List<TimeTable> findAllTimetables(@Param("date1") Date date1,@Param("date2")Date date2,@Param("className")String className);

   /* List<TimeTable> findAllTimetables(@Param("date1") Date date1,@Param("date2")Date date2,@Param("classNameList") List<String> classNameList);*/

    public int editTimetable(@Param("timeTable") TimeTable timeTable);

    public List<TimeTable> readTimetableByStaffInOneMonthAm(@Param("staffName") String staffName,@Param("firstMonthDay") Date firstMonthDay,@Param("lastMonthDay") Date lastMonthDay);

    public List<TimeTable> readTimetableByStaffInOneMonthPm(@Param("staffName") String staffName,@Param("firstMonthDay") Date firstMonthDay,@Param("lastMonthDay") Date lastMonthDay);
}
