package com.wt.controller;

import com.wt.service.impl.ScoreServiceImpl;
import com.wt.vo.StudentScoreVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/score")
public class ScoreController {
    @Autowired
    private ScoreServiceImpl scoreService;

    @RequestMapping("/toInputScoreChoose")
    public String toInputScoreChoose() {
        return "inputScoreChoose";
    }

    @RequestMapping("/findScore")
    public String findScore(String className, Integer count, Model model){
        List<StudentScoreVo> studentAndScore = scoreService.findScore(className, count);
        model.addAttribute("studentAndScore",studentAndScore);
        return "score";
    }
}
