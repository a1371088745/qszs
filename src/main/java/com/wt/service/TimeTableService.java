package com.wt.service;


import com.wt.entity.TimeTable;

import java.util.Date;
import java.util.List;

public interface TimeTableService {
	List<TimeTable> scheduling();
	List<TimeTable> selectTimeTables(Date date1, Date date2);

    int editTimetable(TimeTable timeTable);
}
