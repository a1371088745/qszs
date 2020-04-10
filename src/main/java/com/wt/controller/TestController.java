package com.wt.controller;

import com.wt.service.impl.TimeTableServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/test")
public class TestController {
    @Resource
    private TimeTableServiceImpl timeTableService;
    @RequestMapping("/test1")
    @ResponseBody
    public  String test(){
        timeTableService.scheduling();
        System.out.println("1");
        return  "aaa";
    }

}
