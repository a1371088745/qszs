package com.wt.mapper;

import org.apache.ibatis.annotations.Param;

import com.wt.entity.Staff;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffMapper {
	//查询员工
	public Staff selectStaff(@Param("staffId") int staffId);
}
