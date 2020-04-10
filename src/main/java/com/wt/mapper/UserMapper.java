package com.wt.mapper;

import com.wt.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    //根据电话号码查询用户信息
    public User findUser(@Param("tel") String tel);
    //根据用户电话号码查询用户的角色以及权限
    public User findRoleAndPermission(@Param("tel") String tel);
}
