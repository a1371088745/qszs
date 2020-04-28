package com.wt.mapper;

import org.apache.ibatis.annotations.Param;

import com.wt.entity.Staff;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffMapper {
	//查询员工
	public Staff selectStaff(@Param("staffId") int staffId);
	//查询所有教员
	public List<Staff> findTeacher(@Param("classType") String classType);
	//根据名字查id
	public Integer findStaffId(@Param("staffName") String staffName);

    public List<Staff> selectAllTeacher();
}
