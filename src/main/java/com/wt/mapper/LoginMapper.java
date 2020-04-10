package com.wt.mapper;

import com.wt.entity.Login;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginMapper {
    //通过电话查询登陆信息
    public Login findLogin(@Param("tel") String tel);
    //注册登陆账号
    public int register(@Param("login") Login login);
}
