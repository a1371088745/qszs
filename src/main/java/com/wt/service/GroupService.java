package com.wt.service;

import com.wt.entity.Group;

import java.util.List;

public interface GroupService {
    public List<Group> selectByClass(String className);
}
