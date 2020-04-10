package com.wt.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GivenLessonMapper {
	public Integer selectStaffId(@Param("classId") int classId);
}
