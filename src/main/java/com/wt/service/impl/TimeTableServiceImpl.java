package com.wt.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import com.wt.mapper.*;
import com.wt.service.TimeTableService;
import com.wt.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.wt.entity.Cl;
import com.wt.entity.ClassRoom;
import com.wt.entity.TimeTable;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TimeTableServiceImpl implements TimeTableService {
	@Autowired
	private TimeTableMapper timeTableMapper;
	@Autowired
	private ClMapper clMapper;
	@Autowired
	private ClassRoomMapper classRoomMapper;
	@Autowired
	private GivenLessonMapper givenLessonMapper;
	@Autowired
	private RestRequestMapper requestMapper;
	@Autowired
	private StaffMapper staffMapper;
	@Autowired
	private HisTimetableMapper hisTimetableMapper;

	@Transactional(rollbackFor = {Exception.class})
	public List<TimeTable> scheduling() {
		List<Cl> allClass = clMapper.selectClass(null,null,null); //所有班级的集合
		List<ClassRoom> classRooms = classRoomMapper.selectClassRoom();
		List<Cl> list1 = new ArrayList<Cl>(); //存储人数大于35的班级
		List<Cl> list2 = new ArrayList<Cl>();//存储人数小于35的班级
		List<ClassRoom> roomList1 = new ArrayList<ClassRoom>(); //存储小的上课机房
		List<ClassRoom> roomList2 = new ArrayList<ClassRoom>(); //存储大的上课机房
		List<ClassRoom> roomList3= new ArrayList<ClassRoom>(); //存储大的自习机房
		List<ClassRoom> roomList4= new ArrayList<ClassRoom>(); //存储小的自习机房
		List<TimeTable> timeTables = new ArrayList<TimeTable>();
		for (ClassRoom classRoom : classRooms) {
			if(classRoom.getCrCapacity()>30&&classRoom.getCrCapacity()<=35) {
				roomList1.add(classRoom);
			}else if(classRoom.getCrCapacity()>35&&classRoom.getCrCapacity()<=45) {
				roomList2.add(classRoom);
			}else if(classRoom.getCrCapacity()>45){
				roomList3.add(classRoom);
			}else{
				roomList4.add(classRoom);
			}
		}
		HashMap<Integer, List<Integer>> hashMap = new HashMap<Integer, List<Integer>>();
		for (Cl cl : allClass) {       
			if(cl.getClassPeople()>35) {
				list1.add(cl);
			}else if(cl.getClassPeople()>25){
				list2.add(cl);
			}
			List<Integer> RestDays = requestMapper.selectRestDay(cl.getClassId());  //存储每个班老师请假星期数
			System.out.println(cl);
			int StaffId = givenLessonMapper.selectStaffId(cl.getClassId());
			hashMap.put(StaffId, RestDays); 
		}
		for(Integer i=1;i<=7;i++) {
			for (Cl cl:allClass) {
				cl.setFlag(false);
			}
			Date date = new Date();
			Date nextWeekDate = DateUtil.getNextWeekDate(date, i);//获取下周每天的日期
			List<TimeTable> changeBig = new ArrayList<TimeTable>();  //存储可能换大教室的课程安排
			List<TimeTable> changeSmall=new ArrayList<TimeTable>();  //存储可能换小教师的课程安排
			list1.sort((s1,s2)->s1.getCount1().compareTo(s2.getCount1()));  //根据缺课数从大到小排序
			list2.sort((s1,s2)->s1.getCount1().compareTo(s2.getCount1()));
			HashSet<Integer> amTeachers = new HashSet<Integer>();
			HashSet<Integer> pmTeachers = new HashSet<Integer>();
			for(ClassRoom classRoom:roomList4){  //往自习小教室安排小班自习
				for (Cl cl: allClass){
					if(cl.getCount2()>0&& !cl.isFlag() && cl.getClassPeople()<25){
						TimeTable timeTable=new TimeTable(i,nextWeekDate,classRoom.getCrName(),cl.getClassName(),"自习","自习老师",cl.getClassName(),"自习老师","自习",cl.getClassName(),"自习老师","自习",classRoom.getCrId());
						timeTables.add(timeTable);
						cl.setFlag(true);
						cl.setCount2(cl.getCount2()-1);
						break;
					}
				}
			}
			int countBigUsed=0;//记录当天大班占用的大教室数量，如果小于大教室总数，则把剩余大教室分配给小班
			int forBigClass=0;//记录遍历的大班级数量，如果所有大班级都被遍历且无法放入教室，则结束给大教室安排大班级的循环（此处可以优化，用此处记录放入教室的班级在集合中的下标，后续遍历班级集合从此下标开始）
			int forSmallClass=0;//记录遍历小班的数量
			for (ClassRoom classRoom : roomList2) {   //往大教室里安排大班级上课
				for (int j = forBigClass; j < list1.size(); j++) {
					Integer staffId = givenLessonMapper.selectStaffId(list1.get(j).getClassId());
					List<Integer> restDays = hashMap.get(staffId);
					if (list1.get(j).getCount1() > 0 && !list1.get(j).isFlag() && !amTeachers.contains(staffId) && !restDays.contains(i)) {
						TimeTable timeTable = new TimeTable(i, nextWeekDate, classRoom.getCrName(), list1.get(j).getClassName(), clMapper.selectCsName(list1.get(j).getClassId()), staffMapper.selectStaff(staffId).getStaffName(), list1.get(j).getClassName(), "自习老师", "自习", list1.get(j).getClassName(), "自习老师", "自习",classRoom.getCrId());
						timeTables.add(timeTable);
						changeBig.add(timeTable);
						list1.get(j).setCount1(list1.get(j).getCount1() - 1);
						list1.get(j).setFlag(true);
						amTeachers.add(staffId);
						countBigUsed++;
						forBigClass++;
						break;
					}
					forBigClass++;
				}
			}
					for (int j=countBigUsed;j<roomList2.size();j++) {   //如果大教室还有空余 安排小班级上课
						for (int m = forSmallClass; m < list2.size(); m++) {
							Integer staffId = givenLessonMapper.selectStaffId(list2.get(m).getClassId());
							List<Integer> restDays = hashMap.get(staffId);
							if (list2.get(m).getCount1() > 0 && !list2.get(m).isFlag() && !amTeachers.contains(staffId) && !restDays.contains(i)) {
								TimeTable timeTable = new TimeTable(i, nextWeekDate, roomList2.get(j).getCrName(), list2.get(m).getClassName(), clMapper.selectCsName(list2.get(m).getClassId()), staffMapper.selectStaff(staffId).getStaffName(), list2.get(m).getClassName(), "自习老师", "自习", list2.get(m).getClassName(), "自习老师", "自习",roomList2.get(j).getCrId());
								timeTables.add(timeTable);
								changeBig.add(timeTable);
								countBigUsed++;
								list2.get(m).setCount1(list2.get(m).getCount1() - 1);
								list2.get(m).setFlag(true);
								amTeachers.add(staffId);
								forSmallClass++;
								break;
							}
							forSmallClass++;
						}
					}

					for(int j=countBigUsed;j<roomList2.size();j++){  //如果大教室还有空余，则安排自习
						for(Cl cl:allClass){
							if(cl.getCount2()>0&& !cl.isFlag() && cl.getClassPeople()>=25){
								TimeTable timeTable=new TimeTable(i,nextWeekDate,roomList2.get(j).getCrName(),cl.getClassName(),"自习","自习老师",cl.getClassName(),"自习老师","自习",cl.getClassName(),"自习老师","自习",roomList2.get(j).getCrId());
								timeTables.add(timeTable);
								cl.setCount2(cl.getCount2()-1);
								cl.setFlag(true);
							}
						}
					}


			forSmallClass=0;
			for (ClassRoom classRoom:roomList1) {   //往小教室安排小班上课
				Boolean yn = false;
				for (int m = forSmallClass; m < list2.size(); m++) {
					Integer staffId = givenLessonMapper.selectStaffId(list2.get(m).getClassId());
					List<Integer> restDays = hashMap.get(staffId);
					if (list2.get(m).getCount1() > 0 && !list2.get(m).isFlag() && !amTeachers.contains(staffId) && !restDays.contains(i)) {
						TimeTable timeTable = new TimeTable(i, nextWeekDate, classRoom.getCrName(), list2.get(m).getClassName(), clMapper.selectCsName(list2.get(m).getClassId()), staffMapper.selectStaff(staffId).getStaffName(), list2.get(m).getClassName(), "自习老师", "自习", list2.get(m).getClassName(), "自习老师", "自习",classRoom.getCrId());
						timeTables.add(timeTable);
						changeSmall.add(timeTable);
						list2.get(m).setCount1(list2.get(m).getCount1() - 1);
						list2.get(m).setFlag(true);
						amTeachers.add(staffId);
						forSmallClass++;
						yn = true;
						break;
					}
					forSmallClass++;
				}
				if (!yn){
					for (Cl cl : allClass) {   //小教室如果没人上课，则自习
						if (cl.getCount2() > 0 && !cl.isFlag() && cl.getClassPeople() >= 25) {
							TimeTable timeTable = new TimeTable(i, nextWeekDate, classRoom.getCrName(), cl.getClassName(), "自习", "自习老师", cl.getClassName(), "自习老师", "自习", cl.getClassName(), "自习老师", "自习",classRoom.getCrId());
							timeTables.add(timeTable);
							changeSmall.add(timeTable);
							cl.setCount2(cl.getCount2() - 1);
							cl.setFlag(true);
							break;
						}
					}
			}
			}

			for (ClassRoom classRoom:roomList3) {//往大机房安排需要换教室的班级
				int countClass=0;
				int flagChangeNum1=0;//标记所换大教室的序号
				int flagChangeNum2=0; //标记换小教室的序号
				for (Cl cl:allClass) {
					Integer staffId = givenLessonMapper.selectStaffId(cl.getClassId());
					List<Integer> restDays = hashMap.get(staffId);
					if(cl.getCount1()>0&& !cl.isFlag() && !pmTeachers.contains(staffId) && !restDays.contains(i)){
						TimeTable timeTable=new TimeTable(i,nextWeekDate,classRoom.getCrName(),cl.getClassName(),"自习","自习老师",cl.getClassName(),staffMapper.selectStaff(staffId).getStaffName(),clMapper.selectCsName(cl.getClassId()),cl.getClassName(),"自习老师","自习",classRoom.getCrId());
						timeTables.add(timeTable);
						cl.setCount1(cl.getCount1()-1);
						cl.setFlag(true);
						pmTeachers.add(staffId);
						if(cl.getClassPeople()>35){
							changeTimeTable(timeTable,changeBig.get(flagChangeNum1));
							flagChangeNum1++;
						}else if(cl.getClassPeople()<=35){
							changeTimeTable(timeTable,changeSmall.get(flagChangeNum2));
							flagChangeNum2++;
						}
						countClass++;
						if(countClass==2){
							break;
						}
					}
				}
				if(countClass<2){
					for(Cl cl:allClass){
						if(cl.getCount2()>0&&!cl.isFlag()){
							TimeTable timeTable=new TimeTable(i,nextWeekDate,classRoom.getCrName(),cl.getClassName(),"自习","自习老师",cl.getClassName(),"自习老师","自习",cl.getClassName(),"自习老师","自习",classRoom.getCrId());
							timeTables.add(timeTable);
							cl.setCount2(cl.getCount2()-1);
							cl.setFlag(true);
							countClass++;
							if(countClass==2){
								break;
							}
						}
					}
				}
			}
		}
		timeTableMapper.truncate();
		hisTimetableMapper.insertTimetables(timeTables);
		timeTableMapper.insertTbs(timeTables);
		return timeTables;
	}

	@Override
	public List<TimeTable> selectTimeTables(Date date1,Date date2) {
		return timeTableMapper.selectTimeTables(date1,date2);
	}

	@Override
	public int editTimetable(TimeTable timeTable) {
		return timeTableMapper.editTimetable(timeTable);
	}


	public void changeTimeTable(TimeTable timeTable1,TimeTable timeTable2){  //换教室
		/*timetable.pmclassName=#{timeTable1.pmclassName},timetable.pmTname=#{timeTable1.pmTname},timetable.pmInfo=#{timeTable1.pmInfo},
		timetable.nightclassName=#{timeTable1.nightclassName},timetable.nightTname=#{timeTable1.nightTname},timetable.nightInfo=#{timeTable1.nightInfo}*/
		TimeTable tempTimeTable = (TimeTable) timeTable1.clone();
		timeTable1.setPmclassName(timeTable2.getPmclassName());
		timeTable1.setPmTname(timeTable2.getPmTname());
		timeTable1.setPmInfo(timeTable2.getPmInfo());
		timeTable1.setNightclassName(timeTable2.getNightclassName());
		timeTable1.setNightTname(timeTable2.getNightTname());
		timeTable1.setNightInfo(timeTable2.getNightInfo());

		timeTable2.setPmclassName(tempTimeTable.getPmclassName());
		timeTable2.setPmTname(tempTimeTable.getPmTname());
		timeTable2.setPmInfo(tempTimeTable.getPmInfo());
		timeTable2.setNightclassName(tempTimeTable.getNightclassName());
		timeTable2.setNightTname(tempTimeTable.getNightTname());
		timeTable2.setNightInfo(tempTimeTable.getNightInfo());
	}

}
