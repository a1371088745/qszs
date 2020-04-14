package com.wt.controller;

import com.alibaba.fastjson.JSONObject;
import com.wt.entity.Cl;
import com.wt.entity.JsonData;
import com.wt.service.impl.ClServiceImpl;
import com.wt.util.PageUtils;
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

    @RequestMapping("/toCl")
    public String toCl(){
        return "class";
    }

    @RequestMapping("/selectCl")
    @ResponseBody
    public JSONObject selectAllCl(PageUtils pageUtils,String name,String staffName){
        JSONObject jsonObject = new JSONObject();
        int count=clService.countClass();
        pageUtils.setCount(count);
        pageUtils.setTotalPage(count);
        List<Cl> cls = clService.selectClass(pageUtils,name,staffName);
        if(cls==null){
            jsonObject.put("msg","查询无学生");
            return jsonObject;
        }
        jsonObject.put("code",0);
        jsonObject.put("msg","分页数据");
        jsonObject.put("count",count);
        jsonObject.put("data",cls);
        return jsonObject;
    }

    @RequestMapping("/addClass")
    @ResponseBody
    public JsonData addClass(Cl cl){
        Integer integer = clService.addClass(cl);
        if(integer==-1){
            return JsonData.buildError("班级名重复");
        }
        return JsonData.buildSuccess("","添加成功");
    }

    @RequestMapping("/delClass")
    @ResponseBody
    public JsonData delClass(Cl cl){
        Integer integer = clService.delClass(cl);
        if(integer==-1){
            return JsonData.buildError("该班级不存在");
        }
        return JsonData.buildSuccess("删除成功");
    }

    @RequestMapping("/updateCl")
    @ResponseBody
    public JsonData updateCl(Cl cl){
        Integer integer = clService.updateCl(cl);
        return JsonData.buildSuccess(integer,"修改成功");
    }

}
