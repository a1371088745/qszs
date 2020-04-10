package com.wt.service.impl;

import com.wt.entity.Cl;
import com.wt.mapper.ClMapper;
import com.wt.service.ClService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClServiceImpl implements ClService {
    @Autowired
    private ClMapper clMapper;
    @Override
    public List<Cl> selectClass() {
        return clMapper.selectClass();
    }
}
