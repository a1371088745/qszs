package com.wt.service.impl;

import com.wt.entity.Group;
import com.wt.mapper.GroupMapper;
import com.wt.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    private GroupMapper groupMapper;
    @Override
    public List<Group> selectByClass(String className) {
        return groupMapper.selectByClass(className);
    }
}
