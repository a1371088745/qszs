package com.wt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.wt.entity.Cl;
@Repository
public interface ClMapper {
	//查询所有班级
	public List<Cl> selectClass();
	//根据班级id查询班级名
	public String selectCsName(@Param("classId") int classId);
	//根据班级名查询班级id
	public Integer selectClassId(@Param("className") String className);

	public Integer changePeopleCount(@Param("classId") Integer classId,@Param("num") Integer num);
}
