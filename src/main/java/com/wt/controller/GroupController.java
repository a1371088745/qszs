package com.wt.controller;

import com.wt.entity.Group;
import com.wt.entity.JsonData;
import com.wt.service.impl.GroupServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/group")
public class GroupController {
    @Autowired
    private GroupServiceImpl groupService;

    @RequestMapping("/selectGroupByClass")
    @ResponseBody
    public JsonData selectGroupByClass(@RequestParam("newClassName") String className){
        System.out.println(className);
        List<Group> groups = groupService.selectByClass(className);
        return  JsonData.buildSuccess(groups);
    }
}
