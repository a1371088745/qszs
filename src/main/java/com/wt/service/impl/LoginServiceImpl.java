package com.wt.service.impl;

import com.wt.entity.Login;
import com.wt.mapper.LoginMapper;
import com.wt.mapper.UserMapper;
import com.wt.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginMapper loginMapper;
    @Autowired
    private UserMapper userMapper;
    @Override
    public Login findLogin(String tel) {
        Login login = loginMapper.findLogin(tel);
        return login;
    }

    @Override
    public int register(Login login) {
        userMapper.insertUser(login);
        return loginMapper.register(login);
    }
}
