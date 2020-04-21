package com.wt.mapper;

import com.wt.vo.StudentScoreVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreMapper {
    public List<StudentScoreVo> findScore(@Param("className") String className, @Param("count") Integer count);

    public Integer addScore(@Param("stuId") Integer stuId,@Param("score") Integer score,@Param("count") Integer count);
}
