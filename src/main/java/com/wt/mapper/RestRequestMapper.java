package com.wt.mapper;

import java.util.List;

import com.wt.entity.RestRequest;
import com.wt.vo.RestRequestVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RestRequestMapper {
	public List<Integer> selectRestDay(@Param("classId") int classId);

	public List<RestRequest> selectRestRequestByStaffName(@Param("staffName") String staffName);

	public Integer addRequest(@Param("restRequestVo") RestRequestVo restRequestVo, @Param("staffId") Integer staffId);

    public Integer restRequestWithdraw(@Param("restId") Integer restId);
}
