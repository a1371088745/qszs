package com.wt.controller;

import com.wt.entity.Cl;
import com.wt.entity.JsonData;
import com.wt.service.impl.ClServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/cl")
public class ClController {
    @Autowired
    private ClServiceImpl clService;

    @RequestMapping("/selectAllCl")
    @ResponseBody
    public JsonData selectAllCl(){
        List<Cl> cls = clService.selectClass();
        return JsonData.buildSuccess(cls);
    }
}
