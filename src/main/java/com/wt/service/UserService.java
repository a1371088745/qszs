package com.wt.service;

import com.wt.entity.User;

public interface UserService {
    public User findUser(String tel);

    public User findRoleAndPermission(String tel);
}
