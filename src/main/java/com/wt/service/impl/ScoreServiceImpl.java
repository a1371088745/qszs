package com.wt.service.impl;

import com.wt.mapper.ScoreMapper;
import com.wt.service.ScoreService;
import com.wt.vo.StudentScoreVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ScoreServiceImpl implements ScoreService {
    @Autowired
    private ScoreMapper scoreMapper;
    @Override
    public List<StudentScoreVo> findScore(String className, Integer count) {
        return scoreMapper.findScore(className,count);
    }
}
