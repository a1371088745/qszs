package com.wt.service.impl;

import com.wt.entity.TimeTable;
import com.wt.mapper.HisTimetableMapper;
import com.wt.service.HisTimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class HisTimetableServiceImpl implements HisTimetableService {
    @Autowired
    private HisTimetableMapper hisTimetableMapper;
    @Override
    public int insertTimetables(List<TimeTable> timeTables) {
       return hisTimetableMapper.insertTimetables(timeTables);
    }

    @Override
    public List<TimeTable> findAllTimetables(Date date1, Date date2) {
        return hisTimetableMapper.findAllTimetables(date1,date2);
    }

    public int editTimetable(TimeTable timeTable) {
       return hisTimetableMapper.editTimetable(timeTable);
    }
}
