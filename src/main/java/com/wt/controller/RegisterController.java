package com.wt.controller;

import com.wt.entity.JsonData;
import com.wt.entity.Login;
import com.wt.service.impl.LoginServiceImpl;
import com.zhenzi.sms.ZhenziSmsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private LoginServiceImpl loginService;
    @RequestMapping(value = "/sendCode",method = RequestMethod.POST)
    @ResponseBody
    public  JsonData getVerificationCode(String tel, HttpSession session){
        Login login = loginService.findLogin(tel);
        if(login!=null){
            return JsonData.buildError("手机号已注册");
        }else{
            int messageCode= (int) (Math.random()*900000+100000);
            ZhenziSmsClient client = new ZhenziSmsClient("https://sms_developer.zhenzikj.com","103761", "2efeb48b-7ba5-42c2-a970-05f0ba1f3e4d");
            Map<String,String> params=new HashMap<String,String>();
            params.put("message","验证码为："+messageCode);
            params.put("number",tel);
            String result=null;
            try {
                result = client.send(params);
                System.out.println(client.balance());
                session.setAttribute("messageCode",messageCode);
            } catch (Exception e) {
                e.printStackTrace();
                JsonData.buildError("手机号不存在",-2);
            }
            return JsonData.buildSuccess(result,"已发送验证码");
        }
    }

    @RequestMapping("/submit")
    @ResponseBody
    public JsonData register(String tel,String password,int messageCode, HttpSession session){
        int messageCode1 = (int) session.getAttribute("messageCode");
        Login login = new Login();
        login.setTel(tel);
        login.setPassword(password);
        if(messageCode1==messageCode) {
            int register = loginService.register(login);
            return JsonData.buildSuccess(register, "注册成功");
        }else{
            return JsonData.buildError("验证码错误");
        }
    }
}
