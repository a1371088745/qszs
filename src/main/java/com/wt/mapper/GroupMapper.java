package com.wt.mapper;

import com.wt.entity.Group;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupMapper {
    //根据组名和班级名查询小组id
    public Integer selectGroupId(@Param("groupName") String groupName, @Param("className") String className);

    public List<Group> selectByClass(@Param("className") String className);

    public Integer changePeopleCount(@Param("groupId") Integer groupId,@Param("num") Integer num);
}
