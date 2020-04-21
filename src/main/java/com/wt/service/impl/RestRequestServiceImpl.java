package com.wt.service.impl;

import com.wt.entity.JsonData;
import com.wt.entity.RestRequest;
import com.wt.mapper.RestRequestMapper;
import com.wt.mapper.StaffMapper;
import com.wt.service.RestRequestService;
import com.wt.vo.RestRequestVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class RestRequestServiceImpl implements RestRequestService {
    @Autowired
    private RestRequestMapper restRequestMapper;
    @Autowired
    private StaffMapper staffMapper;

    public List<RestRequest> selectRestRequestByStaffName(String staffName){
        return restRequestMapper.selectRestRequestByStaffName(staffName);
    }

    @Override
    @Transactional
    public Integer addRequest(RestRequestVo restRequestVo) {
        Integer staffId = staffMapper.findStaffId(restRequestVo.getStaffName());
        Date date = new Date();
        int day = date.getDay();
        System.out.println(date);
        restRequestVo.setDay(day);
        restRequestVo.setNowDate(date);
        Integer integer = restRequestMapper.addRequest(restRequestVo, staffId);
        return integer;
    }

    @Override
    public Integer restRequestWithdraw(Integer restId) {
        return restRequestMapper.restRequestWithdraw(restId);
    }
}
