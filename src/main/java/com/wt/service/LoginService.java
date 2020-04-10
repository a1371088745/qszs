package com.wt.service;

import com.wt.entity.Login;

import java.math.BigDecimal;

public interface LoginService {
    public Login findLogin(String tel);
    public int register(Login login);
}
