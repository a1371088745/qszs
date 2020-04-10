package com.wt.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.wt.entity.TimeTable;
@Repository
public interface TimeTableMapper {
	//向排课表中插入数据
	public int insertTb(@Param("timeTable") TimeTable timeTable);
	//根据参数查询排课记录
	public TimeTable selectTimeTable(@Param("classroomName") String classroomName, @Param("day") Integer day);
	//换教室
	public int changeClassRoom(@Param("timeTable1") TimeTable timeTable1, @Param("timeTable2") TimeTable timeTable2);
	//查询排课表
	public List selectTimeTables(@Param("date1") Date date1,@Param("date2")Date date2);
	//删除表
	public int truncate();
	//修改课程表
	public int updateTimeTable(@Param("timeTable") TimeTable timeTable);
	//批量插入课表
	public int insertTbs(@Param("timeTables") List<TimeTable> timeTables);
}