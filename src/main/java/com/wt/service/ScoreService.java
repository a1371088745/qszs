package com.wt.service;

import com.wt.vo.StudentScoreVo;

import java.util.List;

public interface ScoreService {
    public List<StudentScoreVo> findScore(String className, Integer count);

    public Integer addScore(Integer stuId,Integer score,Integer count);

    public List<StudentScoreVo> studentFindScore(String stuTel);
}
