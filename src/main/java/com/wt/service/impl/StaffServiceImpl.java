package com.wt.service.impl;

import com.wt.entity.Staff;
import com.wt.mapper.StaffMapper;
import com.wt.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffServiceImpl implements StaffService {
    @Autowired
    private StaffMapper staffMapper;
    @Override
    public List<Staff> findTeacher(String classType) {
        return staffMapper.findTeacher(classType);
    }

    @Override
    public List<Staff> selectAllTeacher() {
        return staffMapper.selectAllTeacher();
    }
}
