package com.wt.controller;

import com.wt.entity.JsonData;
import com.wt.service.impl.ScoreServiceImpl;
import com.wt.vo.StudentScoreVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping("/toScore")
    public String toScore(String className,Integer count,Model model) {
        model.addAttribute("className",className);
        model.addAttribute("count",count);
        return "score";
    }
    @RequestMapping(value = "/findScore",method = RequestMethod.GET)
    @ResponseBody
    public JsonData findScore(String className, Integer count){
        List<StudentScoreVo> studentAndScore = scoreService.findScore(className, count);
        return JsonData.buildSuccess(studentAndScore);
    }
    @RequestMapping("addScore")
    @ResponseBody
    public JsonData addScore(Integer stuId,Integer score,Integer count){
        Integer integer = scoreService.addScore(stuId, score,count);
        return  JsonData.buildSuccess(integer);
    }
}
