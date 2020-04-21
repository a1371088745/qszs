package com.wt.service;

import com.wt.entity.RestRequest;
import com.wt.vo.RestRequestVo;

import java.util.List;

public interface RestRequestService {
    //根据员工姓名查询请假记录
    public List<RestRequest> selectRestRequestByStaffName(String staffName);

    public Integer addRequest(RestRequestVo restRequestVo);

    public Integer restRequestWithdraw(Integer restId);
}
