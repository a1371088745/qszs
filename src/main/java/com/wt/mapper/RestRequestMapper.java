package com.wt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RestRequestMapper {
	public List<Integer> selectRestDay(@Param("classId") int classId);
}
