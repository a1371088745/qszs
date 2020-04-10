package com.wt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wt.entity.ClassRoom;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRoomMapper {
	//查询教室
	public List<ClassRoom> selectClassRoom();
	public int countBig();
	public int countSmall();
}
