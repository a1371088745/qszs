package com.wt.mapper;

import java.util.List;

import com.wt.util.PageUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.wt.entity.Cl;
@Repository
public interface ClMapper {
	//查询所有班级
	public List<Cl> selectClass(@Param("pageUtils") PageUtils pageUtils,@Param("name") String name,@Param("staffName") String staffName);
	//根据班级id查询当前课程名
	public String selectCsName(@Param("classId") int classId);
	//根据班级名查询班级id
	public Integer selectClassId(@Param("className") String className);
	//根据班级id查询班级名称
	public String selectClassName(@Param("classId") Integer classId);
	//改变班级人数
	public Integer changePeopleCount(@Param("classId") Integer classId,@Param("num") Integer num);
	//统计班级数量
	public Integer countClass();
	//添加班级
	public Integer addClass(@Param("cl") Cl cl);

    public Integer graduateClass(@Param("cl") Cl cl);

	public Integer updateCl(@Param("cl") Cl cl);
}
