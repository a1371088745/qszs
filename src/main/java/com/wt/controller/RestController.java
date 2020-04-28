package com.wt.controller;

import com.wt.entity.JsonData;
import com.wt.entity.RestRequest;
import com.wt.entity.User;
import com.wt.service.impl.RestRequestServiceImpl;
import com.wt.service.impl.StaffServiceImpl;
import com.wt.vo.RestRequestVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("/rest")
@Controller
public class RestController {
    @Autowired
    private RestRequestServiceImpl restRequestService;
    @Autowired
    private StaffServiceImpl staffService;
    @RequestMapping("/toRestRequest")
    public String toRestRequest(){
        return "restRequest";
    }

    @RequestMapping("/toRequestApproval")
    public String toRequestApproval(){
        return "requestApproval";
    }

    @RequestMapping("/findOwnRequest")
    @ResponseBody
    public JsonData findOwnRequest(HttpSession session){
        User user = (User) session.getAttribute("user");
        List<RestRequest> restRequests = restRequestService.selectRestRequestByStaffName(user.getUserName());
        if(restRequests!=null){
            return JsonData.buildSuccess(restRequests);
        }
        return JsonData.buildError("查询无记录");
    }

    @RequestMapping("/addRequest")
    @ResponseBody
    public JsonData addRequest(RestRequestVo restRequestVo){
        Integer integer = restRequestService.addRequest(restRequestVo);
        return JsonData.buildSuccess(integer);
    }

    @RequestMapping("/restRequestWithdraw")
    @ResponseBody
    public JsonData restRequestWithdraw(Integer restId){
        Integer integer = restRequestService.restRequestWithdraw(restId);
        return JsonData.buildSuccess(integer);
    }

    @RequestMapping("/agreeRequest")
    @ResponseBody
    public JsonData agreeRequest(Integer restId){
        Integer integer = restRequestService.agreeRequest(restId);
        if(integer==0){
            return JsonData.buildError("申请已被处理过，无法重复处理");
        }
        return JsonData.buildSuccess(integer);
    }

    @RequestMapping("/refuseRequest")
    @ResponseBody
    public JsonData refuseRequest(Integer restId){
        Integer integer = restRequestService.refuseRequest(restId);
        if(integer==0){
            return JsonData.buildError("申请已被处理过，无法重复处理");
        }
        return JsonData.buildSuccess(integer);
    }
}
