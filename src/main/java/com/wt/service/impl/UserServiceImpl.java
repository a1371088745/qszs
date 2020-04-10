package com.wt.service.impl;

import com.wt.entity.User;
import com.wt.mapper.UserMapper;
import com.wt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUser(String tel) {
        return userMapper.findUser(tel);
    }

    @Override
    public User findRoleAndPermission(String tel) {
        return userMapper.findRoleAndPermission(tel);
    }
}
